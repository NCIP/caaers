package gov.nih.nci.cabig.caaers.web.rule;

public class RuleAjaxObject {

    private String value;

    private String displayName;

    public RuleAjaxObject(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
