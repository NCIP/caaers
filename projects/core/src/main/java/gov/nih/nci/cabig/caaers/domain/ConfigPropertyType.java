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
	REPORT_TYPE(1, "Report Definition Type"),
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
