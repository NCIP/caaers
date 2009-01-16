package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.List;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public abstract class BaseSecurityFilterer {
	/**
	 * Check if user is a super user based on ROLE_caaers_super_user
	 * @param user
	 * @return
	 */
	public boolean isSuperUser(User user) {
		boolean superUser = false;
		//no filtering if super user
        GrantedAuthority[] grantedAuthorities = user.getAuthorities();
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals(UserRole.SUPERUSER.getDisplayName())) {
        			return true;
        		
        	}       
        }		
		
		return superUser;
	}
	
	/**
	 * Check if user requires study level filtering or not.
	 * If user role is not the passed list , studyfiltering is required.
	 * @param user
	 * @param rolesToExclude
	 * @return
	 */
	public boolean studyFilteringRequired(User user, List<String> rolesToExclude) {
		boolean studyFilteringRequired = true ; 
		GrantedAuthority[] grantedAuthorities = user.getAuthorities();
		for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if (rolesToExclude.contains(grantedAuthority.toString())) {
        		return false;
        	}
        	/*
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_site_cd") 
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_study_cd") 
        			//|| grantedAuthority.getAuthority().equals("ROLE_caaers_physician")
        			) {
        		return true;
        	}
        	*/
        }	
		
		return studyFilteringRequired;
	}
	
	public gov.nih.nci.cabig.caaers.domain.ResearchStaff getCaaersUser (User user , ResearchStaffDao researchStaffDao) {
		ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	rsQuery.filterByLoginId(user.getUsername());
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        ResearchStaff researchStaff = rsList.get(0);        
        return researchStaff;
	}
	
}
