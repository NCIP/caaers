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
    
    /** The YES. */
    YES(1), 
 /** The NO. */
 NO(2), 
 /** The UNKNOWN. */
 UNKNOWN(3), 
 /** The RETURNED. */
 RETURNED(4);

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new availability.
     *
     * @param code the code
     */
    Availability(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new availability.
     *
     * @param code the code
     * @param longName the long name
     */
    Availability(int code, String longName) {
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
    public static Availability getByCode(int code) {
        return getByClassAndCode(Availability.class, code);
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
