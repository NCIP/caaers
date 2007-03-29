package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class AutocompleterField extends AbstractInputField {
    public static final String TEXTFIELD_ID = "textfieldId";
    public static final String CHOICES_ID = "choicesId";

    public AutocompleterField() { }

    public AutocompleterField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
        getAttributes().put(TEXTFIELD_ID, createTextfieldId());
        getAttributes().put(CHOICES_ID, createChoicesId());
    }

    private String createTextfieldId() {
        return getPropertyName() + "-input";
    }

    private String createChoicesId() {
        return getPropertyName() + "-choices";
    }

    @Override
    public Category getCategory() {
        return Category.AUTOCOMPLETER;
    }
}
