package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.*;

/**
 * @author Rhett Sutphin
 */
public enum Grade implements CodedEnum {
    NORMAL(0),
    MILD(1),
    MODERATE(2),
    SEVERE(3),
    LIFE_THREATENING(4, "Life-threatening or disabling"),
    DEATH(5);

    private int code;
    private String displayName;
    Grade(int code) {
        this(code, null);
    }

    Grade(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Grade getByCode(int code) {
        return getByClassAndCode(Grade.class, code);
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? titleCasedName(this) : displayName;
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    public String toString() {
        return toStringHelper(this);
    }
}
