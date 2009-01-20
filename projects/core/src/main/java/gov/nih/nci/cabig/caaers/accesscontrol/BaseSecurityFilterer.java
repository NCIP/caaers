package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public abstract class BaseSecurityFilterer {
	/**
	 * Check if user is a super user based on ROLE_caaers_super_user
	 * @param user
	 * @return
	 */
	public boolean isSuperUser(GrantedAuthority[] grantedAuthorities) {
		boolean superUser = false;
		//no filtering if super user
        //GrantedAuthority[] grantedAuthorities = user.getAuthorities();
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals(UserGroupType.caaers_super_user.getSecurityRoleName())) {
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
	public boolean studyFilteringRequired(GrantedAuthority[] grantedAuthorities, List<String> rolesToExclude) {
		boolean studyFilteringRequired = true ; 
		//GrantedAuthority[] grantedAuthorities = user.getAuthorities();
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
	/*
	public gov.nih.nci.cabig.caaers.domain.ResearchStaff getCaaersUser (User user , ResearchStaffDao researchStaffDao) {
		ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	rsQuery.filterByLoginId(user.getUsername());
        List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        ResearchStaff researchStaff = rsList.get(0);        
        return researchStaff;
	}*/
	public List<String> getUserOrganizations(gov.nih.nci.cabig.caaers.domain.User caaersUser) {
		List<String> userOrganizationCodes = new ArrayList<String>();
		if (caaersUser instanceof ResearchStaff) {			
			userOrganizationCodes.add(((ResearchStaff)caaersUser).getOrganization().getNciInstituteCode());
		} else if (caaersUser instanceof Investigator) {
			Investigator investigator = (Investigator)caaersUser;
			List<SiteInvestigator> siteInvestigators = investigator.getSiteInvestigators();
			for (SiteInvestigator siteInvestigator:siteInvestigators) {
				userOrganizationCodes.add(siteInvestigator.getOrganization().getNciInstituteCode());
			}
		}
		return userOrganizationCodes;
	}
	public gov.nih.nci.cabig.caaers.domain.User getCaaersUser (String csmUser , UserDao userDao) {
		
		//ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	//rsQuery.filterByLoginId(user.getUsername());
		gov.nih.nci.cabig.caaers.domain.User caaersUser = userDao.getByLoginId(csmUser);
        //List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
        //ResearchStaff researchStaff = rsList.get(0);        
        return caaersUser;
	}	
	
	public String getUserName(Authentication authentication) {
		Object principal  =  authentication.getPrincipal();
		String userName = "";
		if (principal instanceof User) {
			userName = ((User)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		return userName;
	}
	
	public GrantedAuthority[] getGrantedAuthorities(Authentication authentication) {
		Object principal  =  authentication.getPrincipal();
		GrantedAuthority[] grantedAuthorities = null;
		if (principal instanceof User) {
			grantedAuthorities = ((User)principal).getAuthorities();
		} else {
			grantedAuthorities = authentication.getAuthorities();
		}
		
		return grantedAuthorities;
	}
}
