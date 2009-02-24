package gov.nih.nci.cabig.caaers.domain.ajax;


/**
 *
 */
public class StudyAjaxableDomainObject extends AbstractAjaxableDomainObject {

    private String shortTitle;
    private String primaryIdentifierValue;

    public String getDisplayName() {
        String primaryIdentifier = this.getPrimaryIdentifierValue() == null ? "" : " ( " + this.getPrimaryIdentifierValue() + " ) ";
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
