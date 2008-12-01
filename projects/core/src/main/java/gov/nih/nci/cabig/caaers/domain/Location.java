package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Sameer Sawant
 */
public enum Location implements CodedEnum<Integer> {
	STUDY_SITE(1), COORDINATING_CENTER(2), ALL(3);

    private Integer code;

    private String displayName;

    Location(Integer code) {
        this(code, null);
    }

    Location(Integer code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Location getByCode(int code) {
        return getByClassAndCode(Location.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    // ditto
    public String getString() {
        return toString();
    }

    @Override
    public String toString() {
        return toStringHelper(this);
    }
}
