package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;

public class CaaersSecurityFacadeTest extends CaaersDaoTestCase{
	
	private CaaersSecurityFacade caaersSecurityFacade ; 
	public void setUp() throws Exception {
		super.setUp();
		caaersSecurityFacade = (CaaersSecurityFacade)getApplicationContext().getBean("caaersSecurityFacade");
	}
	
	public void testGetAccessibleOrganizationIds() {
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleOrganizationIds("ln2");
		assertEquals(23,list.size());
	}
	public void testGetProtectionGroupRoleContextForUser() throws Exception {
		
		Date  d1 = new Date();
		Set<ProtectionGroupRoleContext> contexts = caaersSecurityFacade.getProtectionGroupRoleContextForUser("1");
		Date  d2 = new Date();
		System.out.println("execution time : ");
		System.out.println(d2.getTime()-d1.getTime());
		assertEquals(4,contexts.toArray().length);
		
		//from cache...
		Date  d3 = new Date();
		contexts = caaersSecurityFacade.getProtectionGroupRoleContextForUser("1");
		Date  d4 = new Date();
		System.out.println("execution time : ");
		System.out.println(d4.getTime()-d3.getTime());
		assertEquals(4,contexts.toArray().length);
    }
	
	public void testCacheManager () throws Exception {
		String loginId = "1";
		// shud be null , nothing in cache
		Set<ProtectionElementPrivilegeContext> contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_ELEMENT_PRIVILEGE_CONTEXT);
		assertNull(contexts);
		caaersSecurityFacade.getProtectionElementPrivilegeContextForUser(loginId);
		// now data is in cache 
		contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_ELEMENT_PRIVILEGE_CONTEXT);
		assertNotNull(contexts);
		assertEquals(0,contexts.toArray().length);
	}
 
	

	public void testGetAccessibleOrganizationIdsUserWithAllOrgs() {
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleOrganizationIds("ln1");
		assertEquals(23,list.size());
	}
	
	public void testGetAccessibleStudyIds() {
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleStudyIds("ln2");
		assertEquals(23, list.size());
	}
	public void testGetAccessibleStudyIdsUserWithAllStudies() {
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleStudyIds("ln1");
		assertEquals(23, list.size());
		
		list = caaersSecurityFacade.getAccessibleStudyIds("ln");
		assertEquals(23, list.size());
	}
	
	public void testCheckAuthorization() {
		String userName = "test-user";
		String[] roles = {UserGroupType.supplemental_study_information_manager.getSecurityRoleName() , UserGroupType.study_creator.getSecurityRoleName()} ;
		String objectId = "gov.nih.nci.cabig.caaers.domain.Participant";
		String privilege = "CREATE";
		
		Authentication auth =buildAuthentication(userName, roles);
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(false,checkAuth);
		
		String[] roles2 = {UserGroupType.subject_manager.getSecurityRoleName()} ;
		objectId = "gov.nih.nci.cabig.caaers.Admin";
		privilege = "ACCESS";
		auth =buildAuthentication(userName, roles2);
		checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(false,checkAuth);
		
		privilege = "CREATE";
		checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(false,checkAuth);
		

	}
	
    public void _BROKEN_testCheckAuthorization2() throws Exception{
        SecurityTestUtils.switchUser("tester",
                UserGroupType.business_administrator.getSecurityRoleName(),
                UserGroupType.registrar.getSecurityRoleName());
        
        boolean b = caaersSecurityFacade.checkAuthorization(SecurityUtils.getAuthentication(),  "gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition", "READ");
        assertTrue(b);

        SecurityTestUtils.switchUser("tester",
                UserGroupType.system_administrator.getSecurityRoleName(),
                UserGroupType.registrar.getSecurityRoleName());
        b = caaersSecurityFacade.checkAuthorization(SecurityUtils.getAuthentication(),  "gov.nih.nci.cabig.caaers.domain.CaaersFieldDefinition", "READ");
        assertFalse(b);
    }
    
	private Authentication buildAuthentication(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        return auth;
        //SecurityContextHolder.getContext().setAuthentication(auth);
    }


}
