package gov.nih.nci.cabig.caaers.web.rule;


/**
 * @author Rhett Sutphin
 */
public abstract class AbstractInputFieldGroup implements InputFieldGroup {
    private String name;

    private String displayName;

    protected AbstractInputFieldGroup() {
    }

    protected AbstractInputFieldGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
