package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;

/**
 * 
 * @author Biju Joseph
 * @author Sameer Sawant
 *
 */
public class ReportingPeriodCommand {
    
    private AdverseEventReportingPeriod reportingPeriod;
    private Participant participant;
    private Study study;
    private StudyParticipantAssignment assignment;
    private boolean editFlow;
    private boolean workflowEnabled = false;
   
    
    /**
     * Will create / load reporting period
     * @param assignmentId
     * @param reportingPeriodId
     */
    public ReportingPeriodCommand( StudyParticipantAssignment assignment, AdverseEventReportingPeriod reportingPeriod){
    	//store StudyParticipantAssignment
    	this.assignment = assignment;
    	
    	//initialize study and participant
    	study = assignment.getStudySite().getStudy();
    	participant = assignment.getParticipant();
    	
    	//initialize reporting period
    	if(reportingPeriod == null){
    		this.reportingPeriod = new AdverseEventReportingPeriod();
    		TreatmentAssignment treatmentAssignment = new TreatmentAssignment();
        	treatmentAssignment.setStudy(this.study);
        	this.reportingPeriod.setTreatmentAssignment(treatmentAssignment);
    	}else {
    		this.reportingPeriod = reportingPeriod;
    		this.editFlow = true;
    	}
    	
    	
    	
    }
    
    public void setAssignment(StudyParticipantAssignment assignment){
    	this.assignment = assignment;
    }
    
    public StudyParticipantAssignment getAssignment() {
    	return assignment;
    }
    
    public Participant getParticipant(){
    	return participant;
    }
    
    public AdverseEventReportingPeriod getReportingPeriod(){
    	return reportingPeriod;
    }
    
    
    public Study getStudy(){
    	return study;
    }
    
    public boolean isEditFlow() {
		return editFlow;
	}

	public boolean isWorkflowEnabled() {
		return workflowEnabled;
	}

	public void setWorkflowEnabled(boolean workflowEnabled) {
		this.workflowEnabled = workflowEnabled;
	}
    
    
}