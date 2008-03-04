package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the DeviceOperator domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum DeviceOperator implements CodedEnum<Integer> {
    HEALTH_PROFESSIONAL(1, "Health Professional"), PATIENT(2, "Lay User/Patient"), OTHER(3, "Other");

    private int code;

    private String displayName;

    DeviceOperator(int code) {
        this(code, null);
    }

    DeviceOperator(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static DeviceOperator getByCode(int code) {
        return getByClassAndCode(DeviceOperator.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
