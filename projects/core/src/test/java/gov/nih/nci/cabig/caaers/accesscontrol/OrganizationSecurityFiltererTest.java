package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

public class OrganizationSecurityFiltererTest extends DaoTestCase {
	
	private OrganizationDao organizationDao = 
		(OrganizationDao) getApplicationContext().getBean("organizationDao");

	private OrganizationSecurityFilterer organizationSecurityFilterer = 
		(OrganizationSecurityFilterer) getApplicationContext().getBean("organizationSecurityFilterer");
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
		List<Organization> organizations = organizationDao.getAll();
		assertEquals(organizations.size(),4);
		
		//enable security 
		enableAuthorization();		
		//login as Study Coordinator.
		SecurityTestUtils.switchUser("1000@def.com", "ROLE_caaers_site_cd");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Filterer filterer = new CollectionFilterer(organizations); 
		ArrayList filteredList = (ArrayList)organizationSecurityFilterer.filter(authentication, "ACCESS", filterer);
		assertEquals(filteredList.size(),1);
	
	}
}