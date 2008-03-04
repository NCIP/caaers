package gov.nih.nci.cabig.caaers.web.rule;

/**
 * @author Rhett Sutphin
 */
public class DefaultTextArea extends AbstractInputField {
    public DefaultTextArea() {
    }

    public DefaultTextArea(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    @Override
    public Category getCategory() {
        return Category.TEXTAREA;
    }
}
