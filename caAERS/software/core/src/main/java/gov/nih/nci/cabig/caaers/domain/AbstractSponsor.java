package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Biju Joseph
 */
public abstract class AbstractSponsor {
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

    public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {
        return organizationAssignedIdentifier;
    }

    public void setOrganizationAssignedIdentifier(
            OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        this.organizationAssignedIdentifier = organizationAssignedIdentifier;
    }

    public abstract StudyOrganization getStudyOrganization();

}
