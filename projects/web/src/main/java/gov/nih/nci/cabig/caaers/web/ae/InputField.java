package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public abstract class InputField {
    private String displayName;
    private String propertyName;
    private boolean required;
    private String extraInformation;

    protected InputField() { }

    protected InputField(String propertyName, String displayName, boolean required) {
        this.displayName = displayName;
        this.propertyName = propertyName;
        this.required = required;
    }

    public abstract String getType();

    public String getDisplayName() {
        return displayName == null ? propertyName : displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }
}
