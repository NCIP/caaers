package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the Availability domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum Availability implements CodedEnum<Integer> {
    YES(1), NO(2), UNKNOWN(3), RETURNED(4);

    private int code;

    private String displayName;

    Availability(int code) {
        this(code, null);
    }

    Availability(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Availability getByCode(int code) {
        return getByClassAndCode(Availability.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
