package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EvaluationServiceImpl implements EvaluationService {
	
	private AdverseEventEvaluationService adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();
    private static final Log log = LogFactory.getLog(EvaluationServiceImpl.class);

    private ReportDefinitionDao reportDefinitionDao;
    //private ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
    //private ReportService reportService;
	
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
			log.error(e);
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
    public void addRequiredReports(ExpeditedAdverseEventReport expeditedData) {
    }


    
    /**
     * @return All the report definitions which might apply to the given
     *  study, site, and participant
     */
     // TODO: it might more sense for this to go in ReportService
    public List<ReportDefinition> applicableReportDefinitions(StudyParticipantAssignment assignment) {
    	
    	
    	List reportDefinitionNames = new ArrayList();
    	List<ReportDefinition> reportDefinitions = new ArrayList<ReportDefinition>();
    	
    	//ExpeditedAdverseEventReport expeditedAdverseEventReport = ((ExpeditedAdverseEventReport)assignment.getAeReports().get(0));
    	
    	try {
    		
    		for (ExpeditedAdverseEventReport expeditedAdverseEventReport : assignment.getAeReports()) {
    			List rds = adverseEventEvaluationService.evaluateSAEReportSchedule(expeditedAdverseEventReport);
    			for (int i=0; i<rds.size(); i++) {
    				if (!reportDefinitionNames.contains(rds.get(i))) {
    					reportDefinitionNames.add(rds.get(i));
    				}
    			}
    			
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
    	
		for(int i=0;i<reportDefinitionNames.size();i++ ) {
			ReportDefinition rd = reportDefinitionDao.getByName(reportDefinitionNames.get(i).toString());
			reportDefinitions.add(rd);
		}
    	
    	
    	return reportDefinitions;
    }
    
    ////// CONFIGURATION

    public void setReportDefinitionDao(ReportDefinitionDao reportDefinitionDao) {
        this.reportDefinitionDao = reportDefinitionDao;
    }


}
