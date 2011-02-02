package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

 
/**
 * The Interface StudyOrganizationChild.
 */
public interface StudyOrganizationChild {
    
    /**
     * Sets the study organization.
     *
     * @param studyOrg the new study organization
     */
    public void setStudyOrganization(StudyOrganization studyOrg);
    
    /**
     * Gets the study organization.
     *
     * @return the study organization
     */
    public StudyOrganization getStudyOrganization();
    
    /**
     * Sets the start date.
     *
     * @param termStartDate the new start date
     */
    public void setStartDate(Date termStartDate);
    
    /**
     * Sets the end date.
     *
     * @param termStartDate the new end date
     */
    public void setEndDate(Date termStartDate);
}
