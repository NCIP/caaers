/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;

import java.io.Serializable;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;
 
/**
 * This object represents a ConfigPropertyType.
 * 
 * @author Biju Joseph
 *
 */
public enum ConfigPropertyType implements CodedEnum<Integer>, Serializable {
	
	/** The REPOR t_ group. */
	REPORT_GROUP(1, "Report Definition Group"),
	
	/** The RESEARC h_ staf f_ rol e_ type. */
	RESEARCH_STAFF_ROLE_TYPE(2, "ResearchStaff role types"),
	
	/** The INVESTIGATO r_ rol e_ type. */
	INVESTIGATOR_ROLE_TYPE(3, "Investigator role types"),
	
	/** The REPOR t_ rol e_ type. */
	REPORT_ROLE_TYPE(4, "Expedited Adverse Event Reporting Roles"),

    AGENT_UOM(5, "Agent Unit Of Measure"),
	
	/** The UNKNOWN. */
	UNKNOWN(100,"Unknown");
	
	/** The code. */
	private Integer code;
	
	/** The display name. */
	private String displayName;
	
	/**
	 * Instantiates a new config property type.
	 *
	 * @param code the code
	 * @param displayName the display name
	 */
	private ConfigPropertyType(Integer code, String displayName) {
		this.code = code;
		this.displayName = displayName;
		register(this);
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
	
	/**
	 * Gets the by code.
	 *
	 * @param code the code
	 * @return the by code
	 */
	public static ConfigPropertyType getByCode(int code) {
	    return getByClassAndCode(ConfigPropertyType.class, code);
	}

}
