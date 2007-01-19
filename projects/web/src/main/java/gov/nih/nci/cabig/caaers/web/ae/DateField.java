package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class DateField extends TextField {
    public DateField() { }

    public DateField(String propertyName, String displayName, boolean required) {
        super(propertyName, displayName, required);
    }

    public String getType() {
        return "date";
    }
}
