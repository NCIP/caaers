package gov.nih.nci.cabig.caaers.domain;

/**
 * This is a purely fabricated class for XML study import,decorates a {@link StudyFundingSponsor}. 
 * @author Biju Joseph
 * @author Krikor (orignial)
 *
 */
public class FundingSponsor {
	
    private StudyFundingSponsor studyFundingSponsor;
    
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;
    
    public FundingSponsor(){
    }
    
    public FundingSponsor(StudyFundingSponsor sponsor){
    	this.studyFundingSponsor = sponsor;
    }
    
    ///OBJECT METHODS
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
