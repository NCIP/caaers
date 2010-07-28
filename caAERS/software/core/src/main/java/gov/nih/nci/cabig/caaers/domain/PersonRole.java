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
	
	
	SITE_PRINCIPAL_INVESTIGATOR(1, "Site Principal Investigator","SPI", UserGroupType.ae_reporter),
	SITE_INVESTIGATOR(2, "Site Investigator","SI", UserGroupType.ae_reporter),
	PRINCIPAL_INVESTIGATOR(3, "Principal Investigator","PI", UserGroupType.ae_reporter),
	AE_REPORTER(4, "Participant Coordinator","ae_reporter", UserGroupType.ae_reporter),
	STUDY_COORDINATOR(5, "Study Coordinator","supplemental_study_information_manager", UserGroupType.supplemental_study_information_manager),
	ADVERSE_EVENT_COORDINATOR(6, "Adverse Event Coordinator", "ae_reporter", UserGroupType.ae_reporter),
	REPORTER(7, "Reporter", "PC", UserGroupType.ae_reporter),
	PHYSICIAN (8, "Physician", "SI", UserGroupType.ae_reporter),
	AE_EXPEDITED_REPORT_REVIEWER(9, "Central Office Report Reviewer","ae_expedited_report_reviewer", UserGroupType.ae_expedited_report_reviewer),
	AE_STUDY_DATA_REVIEWER(10, "Data Coordinator", "ae_study_data_reviewer", UserGroupType.ae_study_data_reviewer),
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
