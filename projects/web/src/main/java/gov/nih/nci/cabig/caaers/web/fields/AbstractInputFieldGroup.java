package gov.nih.nci.cabig.caaers.web.fields;

/**
 * @author Rhett Sutphin
 */
public abstract class AbstractInputFieldGroup implements InputFieldGroup {
    private String name;

    private String displayName;

    protected AbstractInputFieldGroup() {
        this(null);
    }

    protected AbstractInputFieldGroup(String name) {
        this(name, null);
    }

    protected AbstractInputFieldGroup(String name, String displayName) {
        setName(name);
        setDisplayName(displayName);
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

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append("[name=").append(getName())
                        .append("; displayName=").append(getDisplayName()).append("; fields=")
                        .append(getFields()).toString();
    }
}
