package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
/**
 * 
 * @author Biju Joseph
 *
 */
public enum PersonRole implements CodedEnum<Integer>{
	
	
	SITE_PRINCIPAL_INVESTIGATOR(1, "Site Principal Investigator","SPI"),
	SITE_INVESTIGATOR(2, "Site Investigator","SI"),
	PRINCIPAL_INVESTIGATOR(3, "Principal Investigator","PI"),
	PARTICIPANT_COORDINATOR(4, "Participant Coordinator","PC"),
	STUDY_COORDINATOR(5, "Study Coordinator","SC"),
	ADVERSE_EVENT_COORDINATOR(6, "Adverse Event Coordinator", "AEC"),
	REPORTER(7, "Reporter", "PC"),
	PHYSICIAN (8, "Physician", "SI"),
	CENTRAL_OFFICE_SAE_COORDINATOR(9, "Central Office SAE Coordinator","AEC"),
	COORDINATING_CENTER_DATA_COORDINATOR(10, "Coordinating Center Data Coordinator", "AEC"),
	SITE_CRA(11, "Site Clinical Research Assistant", "PC")
	;
	
	private Integer code;
	private String displayName;
	private String roleCode;
	
	private PersonRole(Integer code, String displayName, String roleCode){
		this.code = code;
		this.displayName = displayName;
		this.roleCode = roleCode;
		register(this);
	}
	
	public Integer getCode() {
		return code;
	}
	public String getDisplayName() {
		return displayName;
	}
	
	public static PersonRole getByCode(final int code) {
        return getByClassAndCode(PersonRole.class, code);
    }
	
	public String getRoleCode(){
		return roleCode;
	}
	
}
