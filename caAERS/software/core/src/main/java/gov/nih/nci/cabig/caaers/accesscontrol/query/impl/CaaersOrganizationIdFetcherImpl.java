package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudySiteDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
/**
 * 
 * @author akkalas
 *
 */
public class CaaersOrganizationIdFetcherImpl extends BaseSecurityFilterer implements IdFetcher {
	
	private UserDao userDao;
	private StudySiteDao studySiteDao;
	private OrganizationDao organizationDao;
	
	/**
	 * 
	 */
	public List<String> fetch(String loginId) {
		List<String> allowedOrganizationIds = new ArrayList<String>();
		gov.nih.nci.cabig.caaers.domain.User caaersUser = userDao.getByLoginId(loginId);
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
		
		List<Organization> allOrganizations = organizationDao.getAll();
		for (Organization organization:allOrganizations) {
			if (userOrganizationCodes.contains(organization.getNciInstituteCode())) {
				allowedOrganizationIds.add(organization.getId()+"");
        	}
		}
		return allowedOrganizationIds;
	}
	
	@Override
    public List<String> getUserOrganizations(gov.nih.nci.cabig.caaers.domain.User caaersUser) {
        List<String> userOrganizationCodes = new ArrayList<String>();
        userOrganizationCodes.addAll(super.getUserOrganizations(caaersUser));
        userOrganizationCodes.addAll(studySiteDao.getSitesOfCoordinatedStudiesByOrganizationCodes(userOrganizationCodes));
        return userOrganizationCodes;
    }
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setStudySiteDao(StudySiteDao studySiteDao) {
		this.studySiteDao = studySiteDao;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public List fetch(String id, Object query) {
		// TODO Auto-generated method stub
		return null;
	}

}
