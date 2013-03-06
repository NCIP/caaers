/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * The Class AbstractSponsor.
 *
 * @author Biju Joseph
 */
public abstract class AbstractSponsor {
    
    /** The organization assigned identifier. */
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

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
     * Gets the study organization.
     *
     * @return the study organization
     */
    public abstract StudyOrganization getStudyOrganization();

}
