/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum Location.
 *
 * @author Sameer Sawant
 */
public enum Location implements CodedEnum<Integer> {
	
	/** The STUD y_ site. */
	STUDY_SITE(1), 
	
	/** The COORDINATIN g_ center. */
	COORDINATING_CENTER(2), 
	
	/** The ALL. */
	ALL(3),
	
	SPONSOR(4);

    /** The code. */
    private Integer code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new location.
     *
     * @param code the code
     */
    Location(Integer code) {
        this(code, null);
    }

    /**
     * Instantiates a new location.
     *
     * @param code the code
     * @param longName the long name
     */
    Location(Integer code, String longName) {
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
    public static Location getByCode(int code) {
        return getByClassAndCode(Location.class, code);
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
