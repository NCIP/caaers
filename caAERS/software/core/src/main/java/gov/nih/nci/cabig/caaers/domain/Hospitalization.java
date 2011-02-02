package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This class represents the Hospitalization domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
public enum Hospitalization implements CodedEnum<Integer> {
	
    /** The NONE. */
    NONE(0, "Please select"), /** The YES. */
 YES(1, "Yes"), /** The NO. */
 NO(2, "No");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new hospitalization.
     *
     * @param code the code
     */
    Hospitalization(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new hospitalization.
     *
     * @param code the code
     * @param displayName the display name
     */
    Hospitalization(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static Hospitalization getByCode(int code) {
        return getByClassAndCode(Hospitalization.class, code);
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
        if (displayName == null) {
            return sentenceCasedName(this);
        } else {
            return displayName;
        }
    }

    // for bean-property access
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return toStringHelper(this);
    }
}
