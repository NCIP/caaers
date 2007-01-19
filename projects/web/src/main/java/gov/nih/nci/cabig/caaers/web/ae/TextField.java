package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class TextField extends InputField {

    public TextField() { }

    public TextField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    public String getType() {
        return "text";
    }
}
