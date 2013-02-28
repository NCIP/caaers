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
 * The Enum ReconciliationAction.
 *
 * @author Ramakrishna
 */
public enum ReconciliationAction implements CodedEnum<Integer> {
	
	ADD(1, "add"),   //new
    
    DELETE(2, "delete"),  //marked for deletion
    
    UPDATE(3, "update"), //updated (merged)
	
	ERROR(4, "error"),   //invalid (term, grade etc)

	LINKED(5, "linked"); //(a perfect match so linked)

    /** The code. */
    private Integer code;
    
    /** The display name. */
    private String displayName;
    
    /**
     * Instantiates a new reconciliation action.
     *
     * @param code the code
     */
    private ReconciliationAction(Integer code) {
        this.code = code;
        register(this);
    }
    
    /**
     * Instantiates a new reconciliation action.
     *
     * @param code the code
     * @param longName the long name
     */
    private ReconciliationAction(Integer code, String longName){
    	this(code);
    	this.displayName = longName;
    	
    }
    
    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReconciliationAction getByCode(int code){
    	return getByClassAndCode(ReconciliationAction.class, code);
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

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }
    
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
