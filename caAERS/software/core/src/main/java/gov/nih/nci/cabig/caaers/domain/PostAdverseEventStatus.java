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
 * The Enum PostAdverseEventStatus.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public enum PostAdverseEventStatus implements CodedEnum<Integer> {

    /** The INTERVENTIO n_ continues. */
    INTERVENTION_CONTINUES(1, "Intervention for AE continues"), 
    
    /** The RECOVERING. */
    RECOVERING(2,"Recovering/Resolving"), 
    
    /** The RECOVERE d_ wit h_ sequelae. */
    RECOVERED_WITH_SEQUELAE(3,"Recovered/Resolved with Sequelae"),
    
    /** The RECOVERE d_ withou t_ sequelae. */
    RECOVERED_WITHOUT_SEQUELAE(4,"Recovered/Resolved without Sequelae"), 
    
    /** The NO t_ recovered. */
    NOT_RECOVERED(5,"Not recovered/Not resolved"), 
    
    /** The DEAD. */
    DEAD(6, "Fatal/Died");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new post adverse event status.
     *
     * @param code the code
     */
    PostAdverseEventStatus(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new post adverse event status.
     *
     * @param code the code
     * @param longName the long name
     */
    PostAdverseEventStatus(int code, String longName) {
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
    public static PostAdverseEventStatus getByCode(int code) {
        return getByClassAndCode(PostAdverseEventStatus.class, code);
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
