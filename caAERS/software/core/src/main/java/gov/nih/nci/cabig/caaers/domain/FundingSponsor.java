package gov.nih.nci.cabig.caaers.domain;

 
/**
 * This is a purely fabricated class for XML study import,decorates a {@link StudyFundingSponsor}. 
 * @author Biju Joseph
 * @author Krikor (orignial)
 *
 */
public class FundingSponsor {
	
    /** The study funding sponsor. */
    private StudyFundingSponsor studyFundingSponsor;
    
    /** The organization assigned identifier. */
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;
    
    /**
     * Instantiates a new funding sponsor.
     */
    public FundingSponsor(){
    }
    
    /**
     * Instantiates a new funding sponsor.
     *
     * @param sponsor the sponsor
     */
    public FundingSponsor(StudyFundingSponsor sponsor){
    	this.studyFundingSponsor = sponsor;
    }
    
    ///OBJECT METHODS
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
     * Gets the study funding sponsor.
     *
     * @return the study funding sponsor
     */
    public StudyFundingSponsor getStudyFundingSponsor() {
        return studyFundingSponsor;
    }

    /**
     * Sets the study funding sponsor.
     *
     * @param studyFundingSponsor the new study funding sponsor
     */
    public void setStudyFundingSponsor(StudyFundingSponsor studyFundingSponsor) {
        this.studyFundingSponsor = studyFundingSponsor;
    }

}
