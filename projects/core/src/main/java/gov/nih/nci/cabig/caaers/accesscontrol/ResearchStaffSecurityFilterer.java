package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;

import java.util.Iterator;

import org.acegisecurity.Authentication;
import org.acegisecurity.userdetails.User;

public class ResearchStaffSecurityFilterer  extends BaseSecurityFilterer  implements DomainObjectSecurityFilterer {
	
	private ResearchStaffDao researchStaffDao;


	public Object filter(Authentication authentication, String permission, Object returnObject) {
		//get user
		User user = (User)authentication.getPrincipal();
		
		//no filtering if super user
		if (isSuperUser(user)) {
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
        
		ResearchStaff researchStaff = getCaaersUser(user,researchStaffDao);
        Organization userOrganization = researchStaff.getOrganization();
        
		Filterer filterer = (Filterer)returnObject;
		Iterator collectionIter = filterer.iterator();
		
		while (collectionIter.hasNext()) {
        	Object domainObject = collectionIter.next();
        	ResearchStaff researchStaffResultObject = (ResearchStaff)domainObject;
        	if (!researchStaffResultObject.getOrganization().getNciInstituteCode().equals(userOrganization.getNciInstituteCode())) {
        		filterer.remove(researchStaffResultObject);
        	}
        }
		
		return filterer.getFilteredObject();
		
	}

	public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}


}
