package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.*;

/**
 * @author Rhett Sutphin
 */
public enum Attribution implements CodedEnum {
    UNRELATED(1),
    UNLIKELY(2),
    POSSIBLE(3),
    PROBABLE(4),
    DEFINITE(5);

    private int code;
    Attribution(int code) {
        this.code = code;
        register(this);
    }

    public static Attribution getByCode(int code) {
        return getByClassAndCode(Attribution.class, code);
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return titleCasedName(this);
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    public String toString() {
        return toStringHelper(this);
    }
}
