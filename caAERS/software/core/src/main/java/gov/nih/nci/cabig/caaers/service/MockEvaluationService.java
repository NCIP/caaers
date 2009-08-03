package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.ApplicableReportDefinitionsDTO;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper.ActionType;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * A mock implementation of {@link gov.nih.nci.cabig.caaers.service.EvaluationService}, suitable
 * for local testing.
 * 
 * @author Rhett Sutphin
 */
@Transactional(readOnly = true)
public class MockEvaluationService implements EvaluationService {
    private static final Log log = LogFactory.getLog(MockEvaluationService.class);

    private ReportDefinitionDao reportDefinitionDao;

    private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    private ReportRepository reportRepository;

    /**
     * Mock implementation returns true for all grade 5 AEs.
     */
    public boolean isSevere(StudyParticipantAssignment assignment, AdverseEvent adverseEvent) {
        return adverseEvent.getGrade() == Grade.DEATH;
    }

    /**
     * Mock implementation adds a report for the first report def returned by reportDefDao#getAll.
     */
    @Transactional(readOnly = false)
    public void addRequiredReports(ExpeditedAdverseEventReport expeditedData) {
        List<ReportDefinition> allDefs = reportDefinitionDao.getAll();
        if (allDefs.size() == 0) {
            log.warn("Mock evaluation service needs at least one report definition");
            return;
        }

        ReportDefinition def = allDefs.get(0);
        Report report = existingReportWithDef(expeditedData, def);
        if (report == null) {
            report = reportRepository.createReport(def, expeditedData, null);
        }
        report.setRequired(true);

        expeditedAdverseEventReportDao.save(expeditedData);
    }

    private Report existingReportWithDef(ExpeditedAdverseEventReport expeditedData,
                    ReportDefinition def) {
        for (Report report : expeditedData.getReports()) {
            log.debug("Examining Report with def " + report.getReportDefinition().getName()
                            + " (id: " + report.getReportDefinition().getId() + "; hash: "
                            + Integer.toHexString(report.getReportDefinition().hashCode()) + ')');
            if (report.getReportDefinition().getId().equals(def.getId())) {
                log.debug("Matched");
                return report;
            }
        }
        log.debug("No Report with def matching " + def.getName() + " (id: " + def.getId()
                        + "; hash: " + Integer.toHexString(def.hashCode()) + ") found in EAER "
                        + expeditedData.getId());
        return null;
    }

    public void addOptionalReports(ExpeditedAdverseEventReport expeditedData) {
        // TODO Auto-generated method stub

    }

    public List<ReportDefinition> findRequiredReportDefinitions(
                    ExpeditedAdverseEventReport expeditedData, List<AdverseEvent> aeList, Study study) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public List<ReportDefinition> filterAmenableReportDefinitions(Map<String, List<String>> map){
    	return null;
    }
    public Map<ReportDefinition, List<AdverseEvent>> findRequiredReportDefinitions(
    		AdverseEventReportingPeriod reportingPeriod) {
    	// TODO Auto-generated method stub
    	return null;
    }

    public Collection<ExpeditedReportSection> mandatorySections(
                    ExpeditedAdverseEventReport expeditedData, ReportDefinition... reportDefinitions) {
        return new ArrayList<ExpeditedReportSection>();
    }

    public List<Report> addOptionalReports(ExpeditedAdverseEventReport expeditedData,
                    Collection<ReportDefinition> reportDefs, Boolean useDefaultVersion) {
        // TODO Auto-generated method stub
    	return null;

    }

    public List<ReportDefinition> applicableReportDefinitions(StudyParticipantAssignment assignment) {
        // TODO Auto-generated method stub
        return new ArrayList<ReportDefinition>();
    }

    public ReportSubmittability isSubmittable(Report report) {
        // TODO Auto-generated method stub
        return new ReportSubmittability();
    }

    public ValidationErrors validateReportingBusinessRules(ExpeditedAdverseEventReport aeReport,
                    ExpeditedReportSection... sectionName) {
        // TODO Auto-generated method stub
        return new ValidationErrors();
    }

    // //// CONFIGURATION

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public void setReportRepository(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

	public EvaluationResultDTO evaluateSAERules(AdverseEventReportingPeriod reportingPeriod) {
		//ctep
		List<ReportDefinition> ctepReportDefs = new ArrayList<ReportDefinition>();
		ctepReportDefs.add(reportDefinitionDao.getByName("CTEP 10 Calendar Day SAE Report"));
		ctepReportDefs.add(reportDefinitionDao.getByName("Mayo Local Report"));
		
		EvaluationResultDTO dto = new EvaluationResultDTO();
		for(ExpeditedAdverseEventReport aeReport : reportingPeriod.getAeReports()){
			dto.addResult(aeReport, ctepReportDefs);
			List<AdverseEvent> aes = new ArrayList<AdverseEvent>(aeReport.getAdverseEvents());
			aes.addAll(reportingPeriod.getNonExpeditedAdverseEvents());
			dto.addEvaluatedAdverseEvents(aeReport.getId(), aes);
		}
		List<AdverseEvent> aeList = reportingPeriod.getEvaluatedAdverseEvents();
		dto.addEvaluatedAdverseEvents(new Integer(0), aeList);
		
		//update the createMap (for new data collection).
		Set<ReportDefinitionWrapper> rdSet = new HashSet<ReportDefinitionWrapper>();
		for(ReportDefinition def : ctepReportDefs){
			rdSet.add(new ReportDefinitionWrapper(def, null, ActionType.CREATE));
		}
		dto.getCreateMap().put(new Integer(0), rdSet);
		
		if(reportingPeriod.getEvaluatedAdverseEvents().size() > 0)
			reportingPeriod.getEvaluatedAdverseEvents().get(0).setRequiresReporting(true);
		
		return dto;
	}
	
	public ApplicableReportDefinitionsDTO applicableReportDefinitions(Study study) {
		
		//ctep
		List<ReportDefinition> ctepReportDefs = reportDefinitionDao.getAll(6);
		
		//Mayo Rochester
		List<ReportDefinition> mayoRochester = reportDefinitionDao.getAll(104522);
		
		//Mayo Jackson
		List<ReportDefinition> mayoJackson = reportDefinitionDao.getAll(102573);
		
		ApplicableReportDefinitionsDTO dto = new ApplicableReportDefinitionsDTO();
		for(ReportDefinition rd : ctepReportDefs){
			dto.addReportDefinition(rd);
		}
		for(ReportDefinition rd : mayoRochester){
			dto.addReportDefinition(rd);
		}
		for(ReportDefinition rd : mayoJackson){
			dto.addReportDefinition(rd);
		}
		return dto;
	}
}
