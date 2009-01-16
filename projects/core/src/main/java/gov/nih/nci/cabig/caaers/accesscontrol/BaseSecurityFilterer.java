package gov.nih.nci.cabig.caaers.accesscontrol;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public abstract class BaseSecurityFilterer {
	
	public boolean isSuperUser(User user) {
		boolean superUser = false;
		//no filtering if super user
        GrantedAuthority[] grantedAuthorities = user.getAuthorities();
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_super_user")) {
        			return true;
        		
        	}       
        }		
		
		return superUser;
	}
	/*
	public boolean studyFilteringRequired(User user) {
		boolean studyFilteringRequired = false ; 
		GrantedAuthority[] grantedAuthorities = user.getAuthorities();
		for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_site_cd") 
        			|| grantedAuthority.getAuthority().equals("ROLE_caaers_study_cd") 
        			//|| grantedAuthority.getAuthority().equals("ROLE_caaers_physician")
        			) {
        		return true;
        	}
        }	
		
		return studyFilteringRequired;
	}
	
	public gov.nih.nci.cabig.caaers.domain.User getCaaersUser (User user , ResearchStaffDao researchStaffDao) {
		ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	rsQuery.filterByLoginId(user.getUsername());
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        ResearchStaff researchStaff = rsList.get(0);        
        return researchStaff;
	}
	*/
}
