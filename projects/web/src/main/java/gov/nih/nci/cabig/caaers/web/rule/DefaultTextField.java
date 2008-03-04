package gov.nih.nci.cabig.caaers.web.rule;

/**
 * @author Rhett Sutphin
 */
public class DefaultTextField extends AbstractInputField {

    public DefaultTextField() {
    }

    public DefaultTextField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.TEXT;
    }
}
