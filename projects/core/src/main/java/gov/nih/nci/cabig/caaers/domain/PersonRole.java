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
	
	
	SITE_PRINCIPAL_INVESTIGATOR(1, "Site Principal Investigator","SPI", UserGroupType.caaers_physician),
	SITE_INVESTIGATOR(2, "Site Investigator","SI", UserGroupType.caaers_physician),
	PRINCIPAL_INVESTIGATOR(3, "Principal Investigator","PI", UserGroupType.caaers_physician),
	PARTICIPANT_COORDINATOR(4, "Participant Coordinator","PC", UserGroupType.caaers_participant_cd),
	STUDY_COORDINATOR(5, "Study Coordinator","SC", UserGroupType.caaers_study_cd),
	ADVERSE_EVENT_COORDINATOR(6, "Adverse Event Coordinator", "AEC", UserGroupType.caaers_ae_cd),
	REPORTER(7, "Reporter", "PC", UserGroupType.caaers_participant_cd),
	PHYSICIAN (8, "Physician", "SI", UserGroupType.caaers_physician)
	//CENTRAL_OFFICE_SAE_COORDINATOR(9, "Central Office SAE Coordinator","AEC"),
	//COORDINATING_CENTER_DATA_COORDINATOR(10, "Coordinating Center Data Coordinator", "AEC"),
	//SITE_CRA(11, "Site Clinical Research Assistant", "PC")
	;
	
	private Integer code;
	private String displayName;
	private String roleCode;
	private UserGroupType[] userGroups;
	
	private PersonRole(Integer code, String displayName, String roleCode , UserGroupType... userGroups){
		this.code = code;
		this.displayName = displayName;
		this.roleCode = roleCode;
		this.userGroups = userGroups;
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
	
	public UserGroupType[] getUserGroups() {
		return userGroups;
	}
	
}
