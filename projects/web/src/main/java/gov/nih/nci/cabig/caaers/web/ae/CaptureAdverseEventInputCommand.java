package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.SolicitedAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

public class CaptureAdverseEventInputCommand implements	AdverseEventInputCommand {
	
	private StudyParticipantAssignment assignment;
	private StudyParticipantAssignmentDao assignmentDao;
	private Participant participant; 
	private Study study;
	  
	private AdverseEventReportingPeriod adverseEventReportingPeriod;
	
	private List<CtcCategory> ctcCategories;

	// Need to verify..
	// Added to make the aeTermQuery.tag work.
	//this method is added to satisfy the UI requirements, so to be moved to command classs

	private Ctc ctcVersion;
	
	public Ctc getCtcVersion() {
		return ctcVersion;
	}
	
	public void setCtcVersion(Ctc ctcVersion) {
		this.ctcVersion = ctcVersion;
	}
	
	@Transient
	public Integer getTermCode(){
		return null;
	}
	//this method is added to satisfy the UI requirements, so to be moved to the command class
	public void setTermCode(Integer ignore){}
	
	// Till here.
	
	 
	public CaptureAdverseEventInputCommand(StudyParticipantAssignmentDao assignmentDao){
		//this.adverseEventReportingPeriod = new AdverseEventReportingPeriod();
		this.assignmentDao = assignmentDao;
	}
	
    public StudyParticipantAssignment getAssignment() {
        if(assignment != null) return assignment;
        
        //fetch the assignment from DB.
    	if (getParticipant() != null && getStudy() != null) {
            this.assignment =  assignmentDao.getAssignment(getParticipant(), getStudy());
        }
    	
    	return this.assignment;
    }

	public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}

	public boolean getIgnoreCompletedStudy() {
		// TODO Auto-generated method stub
		return false;
	}


	public Participant getParticipant() {
		return participant;
	}

	public Study getStudy() {
		return study;
	}

	public String getTreatmentDescriptionType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void setTreatmentDescriptionType(String type) {
		// TODO Auto-generated method stub
		
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public void setStudy(Study study) {
		this.study = study;
	}

	public AdverseEventReportingPeriod getAdverseEventReportingPeriod() {
		return adverseEventReportingPeriod;
	}

	public void setAdverseEventReportingPeriod(AdverseEventReportingPeriod adverseEventReportingPeriod) {
		this.adverseEventReportingPeriod = adverseEventReportingPeriod;
	}
	
	public void setCtcCategories(List<CtcCategory> ctcCategories) {
		this.ctcCategories = ctcCategories;
	}
	
	public List<CtcCategory> getCtcCategories() {
		if(ctcCategories == null)
			setCtcCategories(adverseEventReportingPeriod.getAssignment().getStudySite().getStudy().getAeTerminology().getCtcVersion().getCategories());
		return ctcCategories;
	}
}
