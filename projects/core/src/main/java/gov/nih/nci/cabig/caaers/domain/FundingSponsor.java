package gov.nih.nci.cabig.caaers.domain;

public class FundingSponsor {

    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

    private StudyFundingSponsor studyFundingSponsor;

    public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {
        return organizationAssignedIdentifier;
    }

    public void setOrganizationAssignedIdentifier(
                    OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        this.organizationAssignedIdentifier = organizationAssignedIdentifier;
    }

    public StudyFundingSponsor getStudyFundingSponsor() {
        return studyFundingSponsor;
    }

    public void setStudyFundingSponsor(StudyFundingSponsor studyFundingSponsor) {
        this.studyFundingSponsor = studyFundingSponsor;
    }

}
