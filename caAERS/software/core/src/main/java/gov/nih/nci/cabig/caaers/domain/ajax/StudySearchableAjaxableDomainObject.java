package gov.nih.nci.cabig.caaers.domain.ajax;

import java.util.ArrayList;
import java.util.List;


 
/**
 * The Class StudySearchableAjaxableDomainObject.
 */
public class StudySearchableAjaxableDomainObject extends StudyAjaxableDomainObject {


    /** The short title. */
    private String shortTitle;
    
    /** The primary identifier value. */
    private String primaryIdentifierValue;
    
    /** The primary sponsor code. */
    private String primarySponsorCode;
    
    /** The coordinating center code. */
    private String coordinatingCenterCode;
    
    /** The status. */
    private String status;
    
    /** The phase code. */
    private String phaseCode;
    
    /** The study personnel ids. */
    List<Integer> studyPersonnelIds = new ArrayList<Integer>();
    
    /** The study sites. */
    List<StudySiteAjaxableDomainObject> studySites = new ArrayList<StudySiteAjaxableDomainObject>();
    
    /** The assigned study sites. */
    List<StudySiteAjaxableDomainObject> assignedStudySites = new ArrayList<StudySiteAjaxableDomainObject>();


	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getStatus()
	 */
	public String getStatus() {
        return status;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#setStatus(java.lang.String)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getPhaseCode()
     */
    public String getPhaseCode() {
        return phaseCode;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#setPhaseCode(java.lang.String)
     */
    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getPrimarySponsorCode()
     */
    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#setPrimarySponsorCode(java.lang.String)
     */
    public void setPrimarySponsorCode(String primarySponsorCode) {
        this.primarySponsorCode = primarySponsorCode;
    }

    /**
     * Gets the object by id.
     *
     * @param ajaxableDomainObjects the ajaxable domain objects
     * @param id the id
     * @return the object by id
     */
    protected AbstractAjaxableDomainObject getObjectById(List<? extends AbstractAjaxableDomainObject> ajaxableDomainObjects, Integer id) {
        for (AbstractAjaxableDomainObject object : ajaxableDomainObjects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Adds the study site.
     *
     * @param studySiteAjaxableDomainObject the study site ajaxable domain object
     */
    public void addStudySite(StudySiteAjaxableDomainObject studySiteAjaxableDomainObject) {
        if (getObjectById(this.getStudySites(), studySiteAjaxableDomainObject.getId()) == null) {
            getStudySites().add(studySiteAjaxableDomainObject);
        }

    }

    /**
     * Gets the study sites.
     *
     * @return the study sites
     */
    public List<StudySiteAjaxableDomainObject> getStudySites() {
        return studySites;
    }

    /**
     * Adds the assigned study site.
     *
     * @param studySiteAjaxableDomainObject the study site ajaxable domain object
     */
    public void addAssignedStudySite(StudySiteAjaxableDomainObject studySiteAjaxableDomainObject) {
        if (getObjectById(this.getAssignedStudySites(), studySiteAjaxableDomainObject.getId()) == null) {
        	getAssignedStudySites().add(studySiteAjaxableDomainObject);
        }

    }
    
    /**
     * Gets the assigned study sites.
     *
     * @return the assigned study sites
     */
    public List<StudySiteAjaxableDomainObject> getAssignedStudySites() {
		return assignedStudySites;
	}

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getDisplayName()
     */
    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        return  primaryIdentifier + this.getShortTitle() ;
    }


    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getPrimaryIdentifierValue()
     */
    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#setPrimaryIdentifierValue(java.lang.String)
     */
    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#getShortTitle()
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ajax.StudyAjaxableDomainObject#setShortTitle(java.lang.String)
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * Adds the study personnel id.
     *
     * @param researchStaffId the research staff id
     */
    public void addStudyPersonnelId(Integer researchStaffId) {
        if(!getStudyPersonnelIds().contains(researchStaffId)){
            getStudyPersonnelIds().add(researchStaffId);
        }
    }
   
	/**
	 * Gets the study personnel ids.
	 *
	 * @return the study personnel ids
	 */
	public List<Integer> getStudyPersonnelIds() {
		return studyPersonnelIds;
	}

	/**
	 * Gets the coordinating center code.
	 *
	 * @return the coordinating center code
	 */
	public String getCoordinatingCenterCode() {
		return coordinatingCenterCode;
	}

	/**
	 * Sets the coordinating center code.
	 *
	 * @param coordinatingCenterCode the new coordinating center code
	 */
	public void setCoordinatingCenterCode(String coordinatingCenterCode) {
		this.coordinatingCenterCode = coordinatingCenterCode;
	}
	   
   	/* (non-Javadoc)
   	 * @see java.lang.Object#equals(java.lang.Object)
   	 */
   	public boolean equals(Object arg0) {
	        if (arg0 == null) {
	            return false;
	        }

	        if (!(arg0 instanceof StudySearchableAjaxableDomainObject)) {
	            return false;
	        }

	        StudySearchableAjaxableDomainObject other = (StudySearchableAjaxableDomainObject) arg0;

	        if (this.getId().equals(other.getId())) {
	            return true;
	        }

	        return false;
	    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudySearchableAjaxableDomainObject[" + getId() + ", " + shortTitle +"]";
	}
	   
	   
}