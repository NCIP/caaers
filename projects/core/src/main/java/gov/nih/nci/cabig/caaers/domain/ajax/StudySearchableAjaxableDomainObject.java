package gov.nih.nci.cabig.caaers.domain.ajax;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class StudySearchableAjaxableDomainObject extends StudyAjaxableDomainObject {


    private String shortTitle;
    private String primaryIdentifierValue;
    private String primarySponsorCode;
    private String coordinatingCenterCode;
    private String status;
    private String phaseCode;
    List<Integer> studyPersonnelIds = new ArrayList<Integer>();
    List<StudySiteAjaxableDomainObject> studySites = new ArrayList<StudySiteAjaxableDomainObject>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    public void setPrimarySponsorCode(String primarySponsorCode) {
        this.primarySponsorCode = primarySponsorCode;
    }

    protected AbstractAjaxableDomainObject getObjectById(List<? extends AbstractAjaxableDomainObject> ajaxableDomainObjects, Integer id) {
        for (AbstractAjaxableDomainObject object : ajaxableDomainObjects) {
            if (object.getId().equals(id)) {
                return object;
            }
        }
        return null;
    }

    public void addStudySite(StudySiteAjaxableDomainObject studySiteAjaxableDomainObject) {
        if (getObjectById(this.getStudySites(), studySiteAjaxableDomainObject.getId()) == null) {
            getStudySites().add(studySiteAjaxableDomainObject);
        }

    }

    public List<StudySiteAjaxableDomainObject> getStudySites() {
        return studySites;
    }

    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        return this.getShortTitle() + primaryIdentifier;
    }


    public String getPrimaryIdentifierValue() {
        return primaryIdentifierValue;
    }

    public void setPrimaryIdentifierValue(String primaryIdentifierValue) {
        this.primaryIdentifierValue = primaryIdentifierValue;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void addStudyPersonnelId(Integer researchStaffId) {
        if (getObjectById(getStudyPersonnelIds(), researchStaffId) == null) {
        	getStudyPersonnelIds().add(researchStaffId);
        }
    }
    protected Integer getObjectById(List<Integer> studyPersonnelIds, Integer id) {
        for (Integer object : studyPersonnelIds) {
            if (object.equals(id)) {
                return object;
            }
        }
        return null;
    }    
	public List<Integer> getStudyPersonnelIds() {
		return studyPersonnelIds;
	}

	public String getCoordinatingCenterCode() {
		return coordinatingCenterCode;
	}

	public void setCoordinatingCenterCode(String coordinatingCenterCode) {
		this.coordinatingCenterCode = coordinatingCenterCode;
	}
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
}