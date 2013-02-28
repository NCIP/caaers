/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum EventStatus.
 */
public enum EventStatus implements CodedEnum<Integer> {

    /** The YES. */
    YES(1, "Yes"), 
    
    /** The NO. */
    NO(2,"No"), 
    
    /** The NA. */
    NA(3,"Not applicable");


    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new event status.
     *
     * @param code the code
     */
    EventStatus(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new event status.
     *
     * @param code the code
     * @param longName the long name
     */
    EventStatus(int code, String longName) {
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
    public static EventStatus getByCode(int code) {
        return getByClassAndCode(EventStatus.class, code);
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
        return displayName;
    }
}
