package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.index.IndexEntry;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;

public class CaaersSecurityFacadeTest extends CaaersDaoTestCase{
	
	private CaaersSecurityFacadeImpl caaersSecurityFacade ;
	private ProvisioningSessionFactory provisioningSessionFactory;
	private User csmUser = null;
	private UserProvisioningManager userProvisioningManager;
	private UserRepository userRepository;
	
	
	public void setUp() throws Exception {
		super.setUp();
		caaersSecurityFacade = (CaaersSecurityFacadeImpl)getApplicationContext().getBean("caaersSecurityFacade");
		provisioningSessionFactory = (ProvisioningSessionFactory)getDeployedApplicationContext().getBean("provisioningSessionFactory");
		userProvisioningManager = (UserProvisioningManager)getDeployedApplicationContext().getBean("userProvisioningManager");
		userRepository = (UserRepository)getDeployedApplicationContext().getBean("userRepository");
	}
	
	public void testGetAccessibleOrganizationIds() {
		csmUser = userProvisioningManager.getUser("testuser1");
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		session.replaceRole(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.AE_REPORTER).forSites("A","B","C","D","E","F","G","H","I","J"));
		
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleOrganizationIds("testuser1");
		assertEquals(1,list.size());
		assertEquals(10, list.get(0).getEntityIds().size());
		
		session.deleteRole(SuiteRole.AE_REPORTER);
	}
	
	public void testGetAccessibleOrganizationIdsUserWithAllOrgs() {
		csmUser = userProvisioningManager.getUser("testuser1");
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		session.replaceRole(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.AE_REPORTER).forAllSites());
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleOrganizationIds("testuser1");
		assertEquals(1,list.size());
		assertEquals(1,list.get(0).getEntityIds().size());
		assertTrue(list.get(0).getEntityIds().get(0).equals(Integer.MIN_VALUE));
		
		session.deleteRole(SuiteRole.AE_REPORTER);
	}

	public void testGetAccessibleStudyIds() {
		csmUser = userProvisioningManager.getUser("testuser1");
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		session.replaceRole(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.AE_REPORTER).forAllSites().forStudies("N7028","6307"));
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleStudyIds("testuser1");
		assertEquals(1,list.size());
		assertEquals(2,list.get(0).getEntityIds().size());
		
		session.deleteRole(SuiteRole.AE_REPORTER);
	}
	
	
	public void testGetAccessibleStudyIdsUserWithAllStudies() {
		csmUser = userProvisioningManager.getUser("testuser1");
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		session.replaceRole(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.AE_REPORTER).forAllSites().forAllStudies());
		List<IndexEntry> list = caaersSecurityFacade.getAccessibleStudyIds("testuser1");
		assertEquals(1,list.size());
		assertEquals(1,list.get(0).getEntityIds().size());
		assertTrue(list.get(0).getEntityIds().get(0).equals(Integer.MIN_VALUE));
		
		session.deleteRole(SuiteRole.AE_REPORTER);
	}
	
	
	public void testCacheManager () throws Exception {
		String loginId = "-1";
		// shud be null , nothing in cache
		Set<ProtectionElementPrivilegeContext> contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_ELEMENT_PRIVILEGE_CONTEXT);
		assertNull(contexts);
		caaersSecurityFacade.getProtectionElementPrivilegeContextForUser(loginId);
		// now data is in cache 
		contexts = CSMCacheManager.getContextFromCache(loginId, loginId, CSMCacheManager.PROTECTION_ELEMENT_PRIVILEGE_CONTEXT);
		assertNotNull(contexts);
		assertEquals(0,contexts.toArray().length);
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
	
    public void testCheckAuthorization2() throws Exception{
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
    

    public void testProvisionRoleMemberships(){
		
		List<SuiteRoleMembership> roleMemberships = new ArrayList<SuiteRoleMembership>();
		roleMemberships.add(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.SYSTEM_ADMINISTRATOR));
		roleMemberships.add(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.BUSINESS_ADMINISTRATOR));
		roleMemberships.add(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.AE_REPORTER).forAllSites().forAllStudies());
		roleMemberships.add(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.SUBJECT_MANAGER).forSites("MAYO"));
		roleMemberships.add(provisioningSessionFactory.createSuiteRoleMembership(SuiteRole.REGISTRAR).forSites("WAKE").forStudies("5876","1234","54321"));
		
		csmUser = userProvisioningManager.getUser("testuser2");
		
		caaersSecurityFacade.provisionRoleMemberships(csmUser, roleMemberships);
		
		List<UserGroupType> userGroups = userRepository.getUserGroups("testuser2");
		assertEquals(5,userGroups.size());
		
		ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
		SuiteRoleMembership sysAd = session.getProvisionableRoleMembership(SuiteRole.SYSTEM_ADMINISTRATOR);
		assertNotNull(sysAd);
		assertTrue(!sysAd.getRole().isScoped());
		
		SuiteRoleMembership bizAd = session.getProvisionableRoleMembership(SuiteRole.BUSINESS_ADMINISTRATOR);
		assertNotNull(bizAd);
		assertTrue(!bizAd.getRole().isScoped());

		SuiteRoleMembership aeRep = session.getProvisionableRoleMembership(SuiteRole.AE_REPORTER);
		assertNotNull(aeRep);
		assertTrue(aeRep.getRole().isSiteScoped());
		assertTrue(aeRep.getRole().isStudyScoped());
		assertTrue(aeRep.isAllSites());
		assertTrue(aeRep.isAllStudies());
		
		SuiteRoleMembership subMgr = session.getProvisionableRoleMembership(SuiteRole.SUBJECT_MANAGER);
		assertNotNull(subMgr);
		assertTrue(subMgr.getRole().isSiteScoped());
		assertFalse(subMgr.getRole().isStudyScoped());
		assertEquals(1,subMgr.getSiteIdentifiers().size());
		assertTrue(subMgr.getSiteIdentifiers().contains("MAYO"));
		
		SuiteRoleMembership reg = session.getProvisionableRoleMembership(SuiteRole.REGISTRAR);
		assertNotNull(reg);
		assertTrue(reg.getRole().isSiteScoped());
		assertTrue(reg.getRole().isStudyScoped());
		assertEquals(1,reg.getSiteIdentifiers().size());
		assertTrue(reg.getSiteIdentifiers().contains("WAKE"));
		assertEquals(3,reg.getStudyIdentifiers().size());
		assertTrue(reg.getStudyIdentifiers().contains("5876"));
		assertTrue(reg.getStudyIdentifiers().contains("1234"));
		assertTrue(reg.getStudyIdentifiers().contains("54321"));

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