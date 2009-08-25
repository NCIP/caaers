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
	REPORT_GROUP(1, "Report Definition Group"),
	RESEARCH_STAFF_ROLE_TYPE(2, "ResearchStaff role types"),
	INVESTIGATOR_ROLE_TYPE(3, "Investigator role types"),
	REPORT_ROLE_TYPE(4, "Expedited Adverse Event Reporting Roles"),
	UNKNOWN(100,"Unknown");
	
	private Integer code;
	private String displayName;
	
	private ConfigPropertyType(Integer code, String displayName) {
		this.code = code;
		this.displayName = displayName;
		register(this);
	}
	
	public Integer getCode() {
		return code;
	}
    
    public String getDisplayName() {
		return displayName;
	}
	
	public static ConfigPropertyType getByCode(int code) {
	    return getByClassAndCode(ConfigPropertyType.class, code);
	}

}
