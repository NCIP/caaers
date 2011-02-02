package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum Design.
 *
 * @author Krikor Krumlian
 */
public enum Design implements CodedEnum<Integer> {
    
    /** The BLIND. */
    BLIND(1, "Blind"), 
 /** The OPE n_ unblind. */
 OPEN_UNBLIND(2, "Open/Unblind"), 
 /** The PARTIAL. */
 PARTIAL(3, "Partial Blind");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new design.
     *
     * @param code the code
     */
    Design(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new design.
     *
     * @param code the code
     * @param longName the long name
     */
    Design(int code, String longName) {
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
    public static Design getByCode(int code) {
        return getByClassAndCode(Design.class, code);
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
