package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.List;

public class FetcherUtils {

	/**
	 * Check if user requires study level filtering or not.
	 * If user role is not the passed list , studyfiltering is required.
	 * @param user
	 * @param rolesToExclude
	 * @return
	 */
	public boolean studyFilteringRequired(List<UserGroupType> userGroupTypes, List<String> rolesToExclude) {
		boolean studyFilteringRequired = true ; 
	//	userGroupTypes = new ArrayList<UserGroupType>();
	//	userGroupTypes.add(UserGroupType.caaers_study_cd);
		for (int i=0; i<userGroupTypes.size(); i++) {
			UserGroupType userGroupType = (UserGroupType)userGroupTypes.get(i);
        	if (rolesToExclude.contains(userGroupType.getSecurityRoleName())) {
        		return false;
        	}
        }	
		
		return studyFilteringRequired;
	}
	

	
}
