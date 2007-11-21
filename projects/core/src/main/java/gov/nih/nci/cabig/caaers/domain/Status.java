package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Krikor Krumlian
 */
public enum Status implements CodedEnum<Integer> {
    CURRENT(1,"Current"),
    LEGACY(2,"Legacy")
    ;

    private int code;
    private String displayName;

    Status(int code) {
        this(code, null);
    }

    Status(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Status getByCode(int code) {
        return getByClassAndCode(Status.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
