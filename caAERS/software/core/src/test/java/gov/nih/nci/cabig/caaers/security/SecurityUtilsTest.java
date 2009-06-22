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
		assertEquals("SYSTEM_ADMIN", SecurityUtils.getUserLoginName());
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
		assertEquals("ROLE_caaers_super_user", SecurityUtils.getGrantedAuthorities()[0].getAuthority());
	}

	public void testGetGrantedAuthoritiesAuthentication() {
		EasyMock.expect(authentication.getPrincipal()).andReturn(user);
		GrantedAuthority[] authorities = new GrantedAuthority[1];
		authorities[0] = new GrantedAuthorityImpl("jank");
		EasyMock.expect(user.getAuthorities()).andReturn(authorities);
		replayMocks();
		assertEquals("jank", SecurityUtils.getGrantedAuthorities(authentication)[0].getAuthority());
		verifyMocks();
	}

	public void testCheckAuthorizationUserGroupTypeArray() {
		SecurityTestUtils.switchToSuperuser();
		assertTrue(SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user, UserGroupType.caaers_site_cd));
		assertFalse(SecurityUtils.checkAuthorization(UserGroupType.caaers_central_office_sae_cd, UserGroupType.caaers_site_cd));
	}

	public void testCheckAuthorizationStringArray() {
		SecurityTestUtils.switchToSuperuser();
		assertTrue(SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user.getSecurityRoleName(), UserGroupType.caaers_site_cd.getSecurityRoleName()));
		assertFalse(SecurityUtils.checkAuthorization(UserGroupType.caaers_central_office_sae_cd.getSecurityRoleName(), UserGroupType.caaers_site_cd.getSecurityRoleName()));
	}


}
