package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.easymock.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class SecurityUtilsTest extends AbstractTestCase {

	Authentication authentication;
	User user;
	
	protected void setUp() throws Exception {
		super.setUp();
		authentication = registerMockFor(Authentication.class);
		user = registerMockFor(User.class);
	}

	public void testGetUserLoginName(){
		SecurityTestUtils.switchToSuperuser();
		assertEquals("SYSTEM", SecurityUtils.getUserLoginName());
	}

	public void testGetUserLoginNameAuthentication() {
		EasyMock.expect(authentication.getPrincipal()).andReturn(user);
		EasyMock.expect(user.getUsername()).andReturn("test");
		replayMocks();
		assertEquals("test", SecurityUtils.getUserLoginName(authentication) );
		verifyMocks();
	}
	
	public void testGetUserLoginNameAuthentication_WhenPrincipalIsString() {
		EasyMock.expect(authentication.getPrincipal()).andReturn("hello");
		replayMocks();
		assertEquals("hello", SecurityUtils.getUserLoginName(authentication) );
		verifyMocks();
	}

	public void testGetGrantedAuthorities() {
		SecurityTestUtils.switchToSuperuser();
		assertEquals(1, SecurityUtils.getGrantedAuthorities().length);
		assertEquals("caaers_super_user", SecurityUtils.getGrantedAuthorities()[0].getAuthority());
	}

	public void testGetGrantedAuthoritiesAuthentication() {
		//EasyMock.expect(authentication.getPrincipal()).andReturn(user);
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("jank");
		EasyMock.expect(authentication.getAuthorities()).andReturn(authorities);
		replayMocks();
		assertEquals("jank", SecurityUtils.getGrantedAuthorities(authentication)[0].getAuthority());
		verifyMocks();
	}

	public void testCheckAuthorizationUserGroupTypeArray() {
		SecurityTestUtils.switchToSuperuser();
		assertTrue(SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user, UserGroupType.study_team_administrator));
		assertFalse(SecurityUtils.checkAuthorization(UserGroupType.ae_expedited_report_reviewer, UserGroupType.study_team_administrator));
	}


    public void testHasAuthorityOf() {
        SecurityTestUtils.switchToSuperuser();
        assertEquals(true, SecurityUtils.hasAuthorityOf(UserGroupType.caaers_super_user));
        assertEquals(false, SecurityUtils.hasAuthorityOf(UserGroupType.ae_reporter));

        SecurityTestUtils.switchUser("JOHN", UserGroupType.ae_expedited_report_reviewer.getCsmName(), UserGroupType.registrar.getCsmName());
        assertEquals(true, SecurityUtils.hasAuthorityOf(UserGroupType.ae_expedited_report_reviewer));
        assertEquals(true, SecurityUtils.hasAuthorityOf(UserGroupType.registrar));
        assertEquals(false, SecurityUtils.hasAuthorityOf(UserGroupType.caaers_super_user));
    }


    public void testHasGlobalScopedRoles(){
        SecurityTestUtils.switchToSuperuser();
        assertTrue(SecurityUtils.hasGlobalScopedRoles());
        SecurityTestUtils.switchUser("JOHN", UserGroupType.ae_expedited_report_reviewer.getCsmName(), UserGroupType.registrar.getCsmName());
        assertFalse(SecurityUtils.hasGlobalScopedRoles());
    }


    public void testIsStudyScopedRole(){
        assertTrue(SecurityUtils.isStudyScoped(UserGroupType.registrar.getCsmName()));
        assertFalse(SecurityUtils.isStudyScoped(UserGroupType.study_creator.getCsmName()));
    }


    public void testIsSiteScopedRole(){
        assertTrue(SecurityUtils.isSiteScoped(UserGroupType.study_creator.getCsmName()));
        assertFalse(SecurityUtils.isSiteScoped(UserGroupType.registrar.getCsmName()));
    }


    public void testSplitProtectionGroup(){
        String[] s = SecurityUtils.splitProtectionGroup("x.y");
        assertNotNull(s);
        assertEquals(0, s.length);


        s = SecurityUtils.splitProtectionGroup(" ");
        assertNotNull(s);
        assertEquals(0, s.length);


         s = SecurityUtils.splitProtectionGroup("Study.NCCTG N06C6.1");
        assertNotNull(s);
        assertEquals(2, s.length);
        assertEquals("NCCTG N06C6.1", s[1]);


         s = SecurityUtils.splitProtectionGroup("HealthcareSite.MN026");
        assertNotNull(s);
        assertEquals(2, s.length);
        assertEquals("MN026", s[1]);

    }
}
