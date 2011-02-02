package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This class represents the Attribution domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
public enum Attribution implements CodedEnum<Integer>{
    
    /** The UNRELATED. */
    UNRELATED(1), 
 /** The UNLIKELY. */
 UNLIKELY(2), 
 /** The POSSIBLE. */
 POSSIBLE(3), 
 /** The PROBABLE. */
 PROBABLE(4), 
 /** The DEFINITE. */
 DEFINITE(5);

    /** The code. */
    private int code;

    /**
     * Instantiates a new attribution.
     *
     * @param code the code
     */
    Attribution(int code) {
        this.code = code;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static Attribution getByCode(int code) {
        return getByClassAndCode(Attribution.class, code);
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
        return sentenceCasedName(this);
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

    // ditto
    /**
     * Gets the string.
     *
     * @return the string
     */
    public String getString() {
        return toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this);
    }
}
