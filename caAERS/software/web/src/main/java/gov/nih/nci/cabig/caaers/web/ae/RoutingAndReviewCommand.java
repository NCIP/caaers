package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReviewStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.dto.RoutingAndReviewSearchResultsDTO;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Sameer Sawant
 * @author Biju Joseph
 */
public class RoutingAndReviewCommand{
	private Study study;

    private Participant participant;
    
    private StudySite studySite;
    
    private ReviewStatus reviewStatus;
    
    private boolean workflowEnabled = false;
    
    private HashMap<Object, Object> reviewStatusOptionsMap = new LinkedHashMap<Object,Object>();
    protected static final Collection<ReviewStatus> REVIEW_STATUS = new ArrayList<ReviewStatus>(7);
    
    private RoutingAndReviewSearchResultsDTO searchResultsDTO; 
    
    public RoutingAndReviewCommand() {
    	REVIEW_STATUS.addAll(Arrays.asList(ReviewStatus.values()));
    	reviewStatusOptionsMap.put("", "Please select");
    	reviewStatusOptionsMap.putAll(WebUtils.collectCustomOptions(REVIEW_STATUS, "name", "code", "displayName", ":  "));
    	reviewStatusOptionsMap.putAll(WebUtils.collectOptions(REVIEW_STATUS, "name", "displayName"));
        
    }
    
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
 
    public boolean isSearchCriteriaStudyCentric(){
    	return participant == null && study != null;
    }
    public boolean isSearchCriteriaParticipantCentric() {
    	return participant != null;
    }
    
    public boolean criteriaHasParticipant() {
    	return participant != null;
    }
    public boolean criteriaHasStudy(){
    	return study != null;
    }
    public boolean criteriaHasSite(){
    	return studySite != null;
    }
    public boolean criteriaHasReviewStatus() {
    	return reviewStatus != null;
    }
    public HashMap<Object, Object> getReviewStatusOptionsMap() {
		return reviewStatusOptionsMap;
	}
    public void setReviewStatusOptionsMap(
			HashMap<Object, Object> reviewStatusOptionsMap) {
		this.reviewStatusOptionsMap = reviewStatusOptionsMap;
	}
    
    public boolean getIgnoreCompletedStudy() {
        return false;
    }
    public RoutingAndReviewSearchResultsDTO getSearchResultsDTO() {
		return searchResultsDTO;
	}
    public void setSearchResultsDTO(
			RoutingAndReviewSearchResultsDTO searchResultsDTO) {
		this.searchResultsDTO = searchResultsDTO;
	}
    
    public boolean getWorkflowEnabled(){
    	return workflowEnabled;
    }
    
    public void setWorkflowEnabled(boolean workflowEnabled){
    	this.workflowEnabled = workflowEnabled;
    }
}