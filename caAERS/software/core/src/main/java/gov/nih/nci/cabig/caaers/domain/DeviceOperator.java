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
    
    /** The HEALT h_ professional. */
    HEALTH_PROFESSIONAL(1, "Health Professional"), 
 /** The PATIENT. */
 PATIENT(2, "Lay User/Patient"), 
 /** The OTHER. */
 OTHER(3, "Other");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new device operator.
     *
     * @param code the code
     */
    DeviceOperator(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new device operator.
     *
     * @param code the code
     * @param longName the long name
     */
    DeviceOperator(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static DeviceOperator getByCode(int code) {
        return getByClassAndCode(DeviceOperator.class, code);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
