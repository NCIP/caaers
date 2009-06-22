package gov.nih.nci.cabig.caaers.web.rule;

/**
 * @author Rhett Sutphin
 */
public class AutocompleterField extends AbstractInputField {
    public AutocompleterField() {
    }

    public AutocompleterField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    public String getTextfieldId() {
        return getPropertyName() + "-input";
    }

    public String getChoicesId() {
        return getPropertyName() + "-choices";
    }

    @Override
    public Category getCategory() {
        return Category.AUTOCOMPLETER;
    }
}
