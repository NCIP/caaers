package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public abstract class SelectField extends InputField {
    protected SelectField() { }

    protected SelectField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }
}
