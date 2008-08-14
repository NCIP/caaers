package gov.nih.nci.cabig.caaers.web.participant;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.IndexFixedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class ParticipantInputCommand {
    protected final Log log = LogFactory.getLog(getClass());

    private Participant participant;
    private Study study;
    private StudyParticipantAssignment assignment;
    private Organization organization;
    private String[] studySiteArray;
    private List<StudySite> studySites = new ArrayList<StudySite>();

    private List<Study> studies = new ArrayList<Study>();

    private String searchTypeText;
    private String searchType;
    private String searchText;

    private String studySubjectIdentifier;
    
    //fields for patient medical history
    List<StudyParticipantPriorTherapy> priorTherapies;
    
   
    private String currentItem; //currentItem - corresponds to the item that we are working on now (eg: conmed, priorTherapy). 
    private String task; // will tell the action we perform on the current item.
    private Integer index; //corresponds to the index of the item (eg: conmed[3])
    
    /**
     * This method will initialize the objects that we have to work in the flow.
     * @param identifierType
     */
    void init(String identifierType) {
        this.participant = new Participant();
        this.assignment = new StudyParticipantAssignment();
        this.assignment.setParticipant(this.participant);
        this.assignment.setPriorTherapies(new ArrayList<StudyParticipantPriorTherapy>());
        this.assignment.setDiseaseHistory(new StudyParticipantDiseaseHistory());
        
        OrganizationAssignedIdentifier organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
        organizationAssignedIdentifier.setPrimaryIndicator(Boolean.TRUE);
        organizationAssignedIdentifier.setType(identifierType);
        
        this.participant.addIdentifier(organizationAssignedIdentifier);
    }
    
    /**
     * This method initializes the IndexFixedList. 
     */
    public void refreshIndexFixedLists(){
    	//update the prior therapy list
    	priorTherapies = new IndexFixedList<StudyParticipantPriorTherapy>(assignment.getPriorTherapies());
    	
    }
    
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String[] getStudySiteArray() {
        return studySiteArray;
    }

    public void setStudySiteArray(String[] studySiteArray) {
        this.studySiteArray = studySiteArray;
    }

    public String getSearchTypeText() {
        return searchTypeText;
    }

    public void setSearchTypeText(String searchTypeText) {
        this.searchTypeText = searchTypeText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    public String getStudySubjectIdentifier() {
        return studySubjectIdentifier;
    }

    public void setStudySubjectIdentifier(String studySubjectIdentifier) {
        this.studySubjectIdentifier = studySubjectIdentifier;
    }

    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(List<StudySite> studySites) {
        this.studySites = studySites;
    }
    
    public List<StudyParticipantPriorTherapy> getPriorTherapies() {
		return priorTherapies;
	}
    public void setPriorTherapies(List<StudyParticipantPriorTherapy> priorTherapies) {
		this.priorTherapies = priorTherapies;
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
    
    public Integer getIndex() {
		return index;
	}
    public void setIndex(Integer index) {
		this.index = index;
	}
}