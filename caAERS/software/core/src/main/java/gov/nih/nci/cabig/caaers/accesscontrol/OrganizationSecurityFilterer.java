package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.*;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class OrganizationSecurityFilterer extends BaseSecurityFilterer implements DomainObjectSecurityFilterer {
	
	private UserDao userDao;
	private StudySiteDao studySiteDao;

    public List<String> getUserOrganizations(gov.nih.nci.cabig.caaers.domain.User caaersUser) {
        List<String> userOrganizationCodes = new ArrayList<String>();
        userOrganizationCodes.addAll(super.getUserOrganizations(caaersUser));
        userOrganizationCodes.addAll(studySiteDao.getSitesOfCoordinatedStudiesByOrganizationCodes(userOrganizationCodes));
        return userOrganizationCodes;
    }

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
		// get assigned studies 

        
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

    public StudySiteDao getStudySiteDao() {
        return studySiteDao;
    }

    public void setStudySiteDao(StudySiteDao studySiteDao) {
        this.studySiteDao = studySiteDao;
    }
}
