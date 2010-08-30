package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class StudyAjaxableDomainObject extends AbstractAjaxableDomainObject {

    private String shortTitle;
    private String primaryIdentifierValue;
    private String phaseCode;
    private String primarySponsorCode;
    private String status;

    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
        return  primaryIdentifier + this.getShortTitle();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
