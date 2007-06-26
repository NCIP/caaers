package gov.nih.nci.cabig.caaers.web.fields;

/**
 * @author Rhett Sutphin
 */
public class DefaultCheckboxField extends AbstractInputField {
    public DefaultCheckboxField(String propertyName, String displayName) {
        // it doesn't make sense for a checkbox to ever be "required"
        super(propertyName, displayName, false);
    }

    @Override
    public Category getCategory() {
        return Category.CHECKBOX;
    }
}
