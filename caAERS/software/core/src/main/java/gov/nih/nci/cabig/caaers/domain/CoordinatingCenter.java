/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * This purely fabricated class is used for XML import, and decorates a {@link StudyCoordinatingCenter}.
 *
 * @author Krikor
 * @author Biju Joseph
 */
public class CoordinatingCenter  {

	/** The study coordinating center. */
	private StudyCoordinatingCenter studyCoordinatingCenter;
    
    /** The organization assigned identifier. */
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;
    
    /**
     * Instantiates a new coordinating center.
     */
    public CoordinatingCenter() {
	}
    
    /**
     * Instantiates a new coordinating center.
     *
     * @param coordinatingCenter the coordinating center
     */
    public CoordinatingCenter(StudyCoordinatingCenter coordinatingCenter){
    	this.studyCoordinatingCenter = coordinatingCenter;
    }
    
    /**
     * Gets the organization assigned identifier.
     *
     * @return the organization assigned identifier
     */
    public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {
        return organizationAssignedIdentifier;
    }

    /**
     * Sets the organization assigned identifier.
     *
     * @param organizationAssignedIdentifier the new organization assigned identifier
     */
    public void setOrganizationAssignedIdentifier(
            OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        this.organizationAssignedIdentifier = organizationAssignedIdentifier;
    }


    /**
     * Gets the study coordinating center.
     *
     * @return the study coordinating center
     */
    public StudyCoordinatingCenter getStudyCoordinatingCenter() {
        return studyCoordinatingCenter;
    }

    /**
     * Sets the study coordinating center.
     *
     * @param studyCoordinatingCenter the new study coordinating center
     */
    public void setStudyCoordinatingCenter(StudyCoordinatingCenter studyCoordinatingCenter) {
        this.studyCoordinatingCenter = studyCoordinatingCenter;
    }

}
