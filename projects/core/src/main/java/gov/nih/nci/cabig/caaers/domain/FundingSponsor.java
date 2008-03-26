package gov.nih.nci.cabig.caaers.domain;

public class FundingSponsor extends AbstractSponsor {

    private StudyFundingSponsor studyFundingSponsor;

    public StudyFundingSponsor getStudyFundingSponsor() {
        return studyFundingSponsor;
    }

    public void setStudyFundingSponsor(StudyFundingSponsor studyFundingSponsor) {
        this.studyFundingSponsor = studyFundingSponsor;
    }

    public StudyOrganization getStudyOrganization() {
        return studyFundingSponsor;
    }
}
