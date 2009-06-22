package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Krikor Krumlian
 */
public enum Design implements CodedEnum<Integer> {
    BLIND(1, "Blind"), OPEN_UNBLIND(2, "Open/Unblind"), PARTIAL(3, "Partial Blind");

    private int code;

    private String displayName;

    Design(int code) {
        this(code, null);
    }

    Design(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Design getByCode(int code) {
        return getByClassAndCode(Design.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
