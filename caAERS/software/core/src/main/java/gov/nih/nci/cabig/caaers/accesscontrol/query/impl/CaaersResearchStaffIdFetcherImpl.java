package gov.nih.nci.cabig.caaers.accesscontrol.query.impl;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteResearchStaff;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;

import java.util.ArrayList;
import java.util.List;

import com.semanticbits.security.contentfilter.IdFetcher;
/**
 * 
 * @author akkalas
 *
 */
public class CaaersResearchStaffIdFetcherImpl extends BaseSecurityFilterer implements IdFetcher {
	
	private UserDao userDao;
	private ResearchStaffRepository researchStaffRepository;
	
	/**
	 * 
	 */
	public List<String> fetch(String loginId) {
		List<String> allowedResearchStaffIds = new ArrayList<String>();
		gov.nih.nci.cabig.caaers.domain.User caaersUser = userDao.getByLoginId(loginId);
		List<String> userOrganizationCodes = getUserOrganizations(caaersUser);
		
		List<ResearchStaff> allResearchStaff = researchStaffRepository.getAll();
		for (ResearchStaff researchStaff:allResearchStaff) {
			for(SiteResearchStaff siteResearchStaff : researchStaff.getSiteResearchStaffs()){
				if (userOrganizationCodes.contains(siteResearchStaff.getOrganization().getNciInstituteCode())) {
					allowedResearchStaffIds.add(researchStaff.getId()+"");
            	}
			}
		}		
		return allowedResearchStaffIds;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setResearchStaffRepository(
			ResearchStaffRepository researchStaffRepository) {
		this.researchStaffRepository = researchStaffRepository;
	}

	public List fetch(String id, Object query) {
		// TODO Auto-generated method stub
		return null;
	}

}
