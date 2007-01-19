package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class AutocompleterField extends InputField {
    public AutocompleterField() { }

    public AutocompleterField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    public String getTextfieldId() {
        return getPropertyName() + "-input";
    }

    public String getChoicesId() {
        return getPropertyName() + "-choices";
    }

    public String getType() {
        return "autocompleter";
    }
}
