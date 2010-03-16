package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.RenderDecisionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class EditExpeditedAdverseEventCommand extends AbstractExpeditedAdverseEventInputCommand {
	
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.

    
    
    // //// LOGIC

    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao reportDao){
    	this.reportDao = reportDao;
    }
    
    public EditExpeditedAdverseEventCommand(ExpeditedAdverseEventReportDao expeditedAeReportDao,
            ReportDefinitionDao reportDefinitionDao,
            StudyParticipantAssignmentDao assignmentDao,
            AdverseEventReportingPeriodDao reportingPeriodDao,
            ExpeditedReportTree expeditedReportTree, 
            RenderDecisionManager renderDecisionManager, ReportRepository reportRepository,
            AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository,
            EvaluationService evaluationService) {
    	super(expeditedAeReportDao, reportDefinitionDao, reportingPeriodDao, expeditedReportTree , renderDecisionManager, reportRepository, assignmentDao, adverseEventRoutingAndReviewRepository);
    		this.evaluationService = evaluationService;
    }

    
    public void saveReportingPeriod() {
    	reportingPeriodDao.save(aeReport.getReportingPeriod());
    }
    
    @Override
    public void reassociate() {
        super.reassociate();
        assignmentDao.reassociate(getAssignment());
    }
    
    /**
     * This method returns the type of the command object (aeReport)
     */
    public String getCommandType(){
    	return "aeReport";
    }
    
	/**
	 * This method will check if the study selected is a DCP sponsored study and is AdEERS submittable.
	 * @return
	 */
	public boolean isDCPNonAdeersStudy(){
		if(getStudy() == null) return false;
		return (!getStudy().getAdeersReporting()) && getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode().equals("DCP");
	}
	
	
	///BEAN METHODS

    @Override
    public StudyParticipantAssignment getAssignment() {
        return getAeReport().getAssignment();
    }

    @Override
    public Participant getParticipant() {
        return getAssignment().getParticipant();
    }

    @Override
    public Study getStudy() {
        return getAssignment().getStudySite().getStudy();
    }
    
    /**
     * Will tell which subitem that we are dealing with. 
     * @return
     */
    public String getCurrentItem() {
		return currentItem;
	}
    /**
     * Which tell which subitem that we are dealing with. 
     * @param currentItem
     */
    public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}
    
    public String getTask() {
		return task;
	}
    public void setTask(String task) {
		this.task = task;
	}
    
   
	public AdverseEventReportingPeriod getAdverseEventReportingPeriod(){
		if(getAeReport() != null)
			return getAeReport().getReportingPeriod();
		return null;
	}

}
