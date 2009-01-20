package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class OrganizationSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private UserDao userDao;


	public Object filter(Authentication authentication, String permission, Object returnObject) {
		
		String userName = getUserName(authentication);
		GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(authentication);
		
		//no filtering if super user
		if (isSuperUser(grantedAuthorities)) {
    		if (returnObject instanceof Filterer) {
    			return ((Filterer)returnObject).getFilteredObject();
    		} else {
    			return returnObject;
    		}			
		}
             
        
        // get research staff and associated organization.
		//ResearchStaffQuery rsQuery = new ResearchStaffQuery();
    	//rsQuery.filterByLoginId(user.getUsername());
        //List<ResearchStaff> rsList = researchStaffDao.searchResearchStaff(rsQuery);
        
		gov.nih.nci.cabig.caaers.domain.User caaersUser = getCaaersUser(userName,userDao);
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);

        
		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	Organization organization = (Organization)domainObject;
        	//if (!organization.getNciInstituteCode().equals(userOrganization.getNciInstituteCode())) {
        	if (!userOrganizationCodes.contains(organization.getNciInstituteCode())) {
        		filterer.remove(organization);
        	}
        }
		
		return filterer.getFilteredObject();
		
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
