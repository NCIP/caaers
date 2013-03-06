/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum Mandatory.
 */
public enum Mandatory implements CodedEnum<Integer> {
		
		/** The OPTIONAL. */
		OPTIONAL(0, "Optional"),  
  /** The MANDATORY. */
  MANDATORY(1,"Mandatory"), 
 /** The NA. */
 NA(-1 , "Not Applicable");

    /** The code. */
    private Integer code;

    /** The display name. */
    private String displayName;
    
    /**
     * Instantiates a new mandatory.
     *
     * @param code the code
     */
    Mandatory(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new mandatory.
     *
     * @param code the code
     * @param displayName the display name
     */
    Mandatory(Integer code, String displayName) {
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
    public static Mandatory getByCode(int code) {
        return getByClassAndCode(Mandatory.class, code);
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
