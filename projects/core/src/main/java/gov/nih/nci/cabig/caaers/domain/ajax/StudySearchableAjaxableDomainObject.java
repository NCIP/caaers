package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class StudySearchableAjaxableDomainObject extends StudyAjaxableDomainObject {


    private String shortTitle;
    private String primaryIdentifierValue;
    private String primarySponsorCode;

    private String status;
    private String phaseCode;

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

    public String getDisplayName() {

        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" :
                " ( " + this.getPrimaryIdentifierValue() + " ) ";
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
}