package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.titleCasedName;
import static gov.nih.nci.cabig.caaers.domain.CodedEnumHelper.toStringHelper;

/**
 * @author Rhett Sutphin
 */
public enum Hospitalization implements CodedEnum {
    NONE(0),
    HOSPITALIZATION(1),
    PROLONGED_HOSPITALIZATION(2, "Prolonged hospitalization");

    private int code;
    private String displayName;

    Hospitalization(int code) {
        this(code, null);
    }

    Hospitalization(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    public static Hospitalization getByCode(int code) {
        return getByClassAndCode(Hospitalization.class, code);
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        if (displayName == null) {
            return titleCasedName(this);
        } else {
            return displayName;
        }
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    public String toString() {
        return toStringHelper(this);
    }
}
