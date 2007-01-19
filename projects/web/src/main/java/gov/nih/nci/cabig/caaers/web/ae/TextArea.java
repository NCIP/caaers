package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class TextArea extends InputField {
    public TextArea() { }

    public TextArea(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    public String getType() {
        return "textarea";
    }
}
