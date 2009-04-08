package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.ResearchStaffQuery;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.ResearchStaffRepository;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.utils.CollectionFilterer;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class ResearchStaffSecurityFiltererTest extends DaoTestCase {
	
	private ResearchStaffRepository researchStaffRepository = (ResearchStaffRepository)getApplicationContext().getBean("researchStaffRepository");
	
	private ResearchStaffSecurityFilterer researchStaffSecurityFilterer = 
		(ResearchStaffSecurityFilterer) getApplicationContext().getBean("researchStaffSecurityFilterer");
	/**
	 * disable authorization before querying .
	 * Reason : Authorization checks for various roles to invoke search methods on various classes.
	 * For this we need to load whole CSM database. We are filtering the results after executing full query anyway,
	 * so , execute query without security and , then enable for filtering.
	 *
	 */
	private void disableAuthorization(){
		SecurityTestUtils.enableAuthorization(false, applicationContext);
	}
	private void enableAuthorization(){
		SecurityTestUtils.enableAuthorization(true, applicationContext);
	}
	
	public void testFilterAsSiteCoordinator() {
		//disable security before query 
		disableAuthorization();
		ResearchStaffQuery query = new ResearchStaffQuery();
		List<ResearchStaff> researchStaffs = researchStaffRepository.getResearchStaff(query);
		assertEquals(4,researchStaffs.size());
		
		//enable security 
		enableAuthorization();		
		//login as Site Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", UserGroupType.caaers_site_cd.getSecurityRoleName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(researchStaffs); 
		ArrayList filteredList = (ArrayList)researchStaffSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),3);
	
	}
}