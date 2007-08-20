package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.ReportService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public class EvaluationServiceImpl implements EvaluationService {
    private AdverseEventEvaluationService adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();
    private static final Log log = LogFactory.getLog(EvaluationServiceImpl.class);

    private ReportDefinitionDao reportDefinitionDao;
    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    private ReportService reportService;
    private OrganizationDao  organizationDao;

    /**
     * @return true if the given adverse event is severe in the context of the provided
     *  study, site, and participant
     */
    public boolean isSevere(StudyParticipantAssignment assignment, AdverseEvent adverseEvent) {
        boolean isSevere = false;

        try {
            String msg = adverseEventEvaluationService.assesAdverseEvent(adverseEvent, assignment.getStudySite().getStudy());
            if ("SERIOUS_ADVERSE_EVENT".equals(msg)) {
                isSevere = true;
            }
        } catch (Exception e) {
            throw new CaaersSystemException("Could not assess the given AE", e);
        }

        return isSevere;
    }

    /**
     * Evaluates the provided data and associates new {@link Report}
     * instances with the given {@link ExpeditedAdverseEventReport}.
     * <p>
     * This method may be called multiple times for the same expedited data.  Implementors must be
     * sure not to add multiple {@link Report}s for the same
     * {@link ReportDefinition}.  Implementors must also <em>not</em> remove
     * {@link gov.nih.nci.cabig.caaers.domain.report.Report}s if they don't evaluate as required
     * (e.g., some reports may have been directly selected by the user).  Instead, implementors
     * should update the {@link Report#setRequired} flag.
     *
     * @param expeditedData
     * @return the report definitions which the evaluation indicated were required.
     */
    @Transactional(readOnly=false)
    public void addRequiredReports(ExpeditedAdverseEventReport expeditedData) {
        Map<String,List<String>> map;
        List<String> reportDefinitionNames = new ArrayList<String>();
        try {
            map = adverseEventEvaluationService.evaluateSAEReportSchedule(expeditedData);
        } catch (Exception e) {
            throw new CaaersSystemException(
                "Could not determine the reports necessary for the given expedited adverse event data", e);
        }

        Set<String> keys = map.keySet();
        for (String key : keys) {
            List<String> reportDefNames = map.get(key);
            /* TO-DO need to clarify this ranking incase of multi actions in rules
            if (reportDefNames.size() != 0) {
                String reportDefName = extractTopPriorityReportDefintionName(reportDefNames);
                reportDefinitionNames.add(reportDefName);
            }
            */
            //uncomment the above part and comment the below code after figuring oout ranking.
            for (String reportDefName : reportDefNames) {
            	reportDefinitionNames.add(reportDefName);
            }
        }

        for (Object reportDefinitionName : reportDefinitionNames) {

            ReportDefinition def = reportDefinitionDao.getByName(reportDefinitionName.toString());
            Report report = existingReportWithDef(expeditedData, def);

            if (report == null) {
                report = reportService.createReport(def, expeditedData);
                expeditedAdverseEventReportDao.save(expeditedData);
            }
            report.setRequired(true);
        }

    }
    /**
     * Will create the Report by calling ReportService, then saves the ExpeditedAdverseEventReport
     */
    @Transactional(readOnly=false)
    public void addOptionalReports(ExpeditedAdverseEventReport expeditedData, List<ReportDefinition> reportDefs) {
    	for(ReportDefinition def : reportDefs){
    		reportService.createReport(def, expeditedData);
    	}
    	expeditedAdverseEventReportDao.save(expeditedData);
    }

    private Report existingReportWithDef(ExpeditedAdverseEventReport expeditedData, ReportDefinition def) {
        for (Report report : expeditedData.getReports()) {
            log.debug("Examining Report with def "+ report.getReportDefinition().getName()
                + " (id: " + report.getReportDefinition().getId() + "; hash: "
                + Integer.toHexString(report.getReportDefinition().hashCode()) + ')');
            if (report.getReportDefinition().equals(def)) {
                log.debug("Matched");
                return report;
            }
        }
        log.debug("No Report with def matching " + def.getName()
            + " (id: " + def.getId() + "; hash: "
            + Integer.toHexString(def.hashCode()) + ") found in EAER " + expeditedData.getId());
        return null;
    }

    /**
     * @return All the report definitions which might apply to the given
     *  study, site, and participant
     */
     // TODO: it might more sense for this to go in ReportService
    public List<ReportDefinition> applicableReportDefinitions(StudyParticipantAssignment assignment) {
        List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();

        for (StudyOrganization studyOrganization : assignment.getStudySite().getStudy().getStudyOrganizations()) {
        	//System.out.println(" ORGS : " + studyOrganization.getOrganization().getName());
            reportDefinitions.addAll(reportDefinitionDao.getAll(studyOrganization.getOrganization().getId()));
        }

        // get organaization of sponsor .
        // all sponsors are not in orgs table as of 07/13/2007
        Organization organization = organizationDao.getByName(assignment.getStudySite().getStudy().getPrimaryFundingSponsorOrganization().getName());

        if (organization != null) {
        	reportDefinitions.addAll(reportDefinitionDao.getAll(organization.getId()));
        }

        return reportDefinitions;
    }

    private String extractTopPriorityReportDefintionName(List<String> list){
        List<ReportDefinition> reportDefs = new ArrayList<ReportDefinition>();
        for (String reportDefName : list) {
            ReportDefinition rd = reportDefinitionDao.getByName(reportDefName);
            if (rd != null) {
                reportDefs.add(rd);
            }
        }

        // XXX: You could do this without the array conversion using Collections.sort
        Comparator<ReportDefinition> c = new ReportDefinitionComparator();
        ReportDefinition[] reportDefArray = new ReportDefinition[reportDefs.size()];
        java.util.Arrays.sort(reportDefs.toArray(reportDefArray), c);

        ReportDefinition reportDefinition = reportDefArray[reportDefArray.length-1];
        return reportDefinition.getName();
    }

     ////// CONFIGURATION

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setExpeditedAdverseEventReportDao(
        ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao
    ) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
}
