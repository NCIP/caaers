package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class AutocompleterField extends AbstractInputField {
    public AutocompleterField() { }

    public AutocompleterField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.AUTOCOMPLETER;
    }
}
