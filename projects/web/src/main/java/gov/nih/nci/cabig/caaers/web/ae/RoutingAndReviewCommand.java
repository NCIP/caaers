package gov.nih.nci.cabig.caaers.web.ae;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;

/**
 * @author Sameer Sawant
 */
public class RoutingAndReviewCommand{
	private Study study;

    private Participant participant;
    
    private StudySite studySite;
    
    private ReviewStatus reviewStatus;
    
    private List<StudyParticipantAssignment> assignmentList;
    
    
    public Study getStudy() {
		return study;
	}
    public void setStudy(Study study) {
		this.study = study;
	}
    public Participant getParticipant() {
		return participant;
	}
    public void setParticipant(Participant participant) {
		this.participant = participant;
	}
    public StudySite getStudySite(){
    	return studySite;
    }
    public void setStudySite(StudySite studySite){
    	this.studySite = studySite;
    }
    public ReviewStatus getReviewStatus(){
    	return reviewStatus;
    }
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
    }
    public List<StudyParticipantAssignment> getAssignmentList(){
    	if(assignmentList == null)
    		assignmentList = new ArrayList<StudyParticipantAssignment>();
    	return assignmentList;
    }
    public void setAssignmentList(List<StudyParticipantAssignment> assignmentList){
    	this.assignmentList = assignmentList;
    }
    public boolean getIgnoreCompletedStudy() {
        return false;
    }
}