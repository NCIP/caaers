package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;
 

/**
 * The Enum PersonRole.
 *
 * @author Biju Joseph
 */
public enum PersonRole implements CodedEnum<Integer>{
	
	
	/** The SIT e_ principa l_ investigator. */
	SITE_PRINCIPAL_INVESTIGATOR(1, "Site Principal Investigator","SPI", UserGroupType.ae_reporter),
	
	/** The SIT e_ investigator. */
	SITE_INVESTIGATOR(2, "Site Investigator","SI", UserGroupType.ae_reporter),
	
	/** The PRINCIPA l_ investigator. */
	PRINCIPAL_INVESTIGATOR(3, "Principal Investigator","PI", UserGroupType.ae_reporter),
	
	/** The A e_ reporter. */
	AE_REPORTER(4, "Participant Coordinator","ae_reporter", UserGroupType.ae_reporter),
	
	/** The STUD y_ coordinator. */
	STUDY_COORDINATOR(5, "Study Coordinator","supplemental_study_information_manager", UserGroupType.supplemental_study_information_manager),
	
	/** The ADVERS e_ even t_ coordinator. */
	ADVERSE_EVENT_COORDINATOR(6, "Adverse Event Coordinator", "ae_reporter", UserGroupType.ae_reporter),
	
	/** The REPORTER. */
	REPORTER(7, "Reporter", "PC", UserGroupType.ae_reporter),
	
	/** The PHYSICIAN. */
	PHYSICIAN (8, "Physician", "SI", UserGroupType.ae_reporter),
	
	/** The A e_ expedite d_ repor t_ reviewer. */
	AE_EXPEDITED_REPORT_REVIEWER(9, "Central Office Report Reviewer","ae_expedited_report_reviewer", UserGroupType.ae_expedited_report_reviewer),
	
	/** The A e_ stud y_ dat a_ reviewer. */
	AE_STUDY_DATA_REVIEWER(10, "Data Coordinator", "ae_study_data_reviewer", UserGroupType.ae_study_data_reviewer),
	;
	
	/** The code. */
	private Integer code;
	
	/** The display name. */
	private String displayName;
	
	/** The role code. */
	private String roleCode;
	
	/** The user groups. */
	private UserGroupType[] userGroups;
	
	/**
	 * Instantiates a new person role.
	 *
	 * @param code the code
	 * @param displayName the display name
	 * @param roleCode the role code
	 * @param userGroups the user groups
	 */
	private PersonRole(Integer code, String displayName, String roleCode , UserGroupType... userGroups){
		this.code = code;
		this.displayName = displayName;
		this.roleCode = roleCode;
		this.userGroups = userGroups;
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
	public static PersonRole getByCode(final int code) {
        return getByClassAndCode(PersonRole.class, code);
    }
	
	/**
	 * Gets the role code.
	 *
	 * @return the role code
	 */
	public String getRoleCode(){
		return roleCode;
	}
	
	/**
	 * Gets the user groups.
	 *
	 * @return the user groups
	 */
	public UserGroupType[] getUserGroups() {
		return userGroups;
	}
	
}
