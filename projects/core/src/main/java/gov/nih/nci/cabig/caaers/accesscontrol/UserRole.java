package gov.nih.nci.cabig.caaers.accesscontrol;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum UserRole implements CodedEnum<Integer> {
	
    PHYSICIAN(1, "ROLE_caaers_physician"), 
    GRIDUSER(2, "ROLE_caaers_grid_user"), 
    ADMIN(3, "ROLE_caaers_admin"), 
    USER(4, "ROLE_caaers_user"), 
    SUPERUSER(5, "ROLE_caaers_super_user"), 
    STUDYCOORDINATOR(6,"ROLE_caaers_study_cd"),
    PARTICIPANTCOORDINATOR(7,"ROLE_caaers_participant_cd"),
    AECOORDINATOR(8,"ROLE_caaers_ae_cd"),
    SITECOORDINATOR(9,"ROLE_caaers_site_cd");
	
	private Integer code;

    private String displayName;
    
    UserRole(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

	public Integer getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}
	

}
