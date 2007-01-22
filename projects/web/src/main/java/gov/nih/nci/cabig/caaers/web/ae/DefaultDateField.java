package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class DefaultDateField extends AbstractInputField {
    public DefaultDateField() { }

    public DefaultDateField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.DATE;
    }
}
