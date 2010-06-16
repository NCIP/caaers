package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersDaoTestCase;

import java.util.List;

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
		List<Integer> list = caaersSecurityFacade.getAccessibleOrganizationIds("1");
		assertEquals(2,list.size());
	}

	public void testGetAccessibleOrganizationIdsUserWithAllOrgs() {
		List<Integer> list = caaersSecurityFacade.getAccessibleOrganizationIds("-7");
		assertEquals(3,list.size());
	}
	
	public void testGetAccessibleStudyIds() {
		List<Integer> list = caaersSecurityFacade.getAccessibleStudyIds("1");
		assertEquals(2,list.size());
	}
	public void testGetAccessibleStudyIdsUserWithAllStudies() {
		List<Integer> list = caaersSecurityFacade.getAccessibleStudyIds("-7");
		assertEquals(0,list.size());
		
		list = caaersSecurityFacade.getAccessibleStudyIds("-8");
		assertEquals(1,list.size());
	}
	
	public void testCheckAuthorization() {
		String userName = "test-user";
		String[] roles = {"supplemental_study_information_manager" , "study_creator"} ;
		String objectId = "gov.nih.nci.cabig.caaers.domain.Participant";
		String privilege = "CREATE";
		
		Authentication auth =buildAuthentication(userName, roles);
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(true,checkAuth);
		
		String[] roles2 = {"subject_manager"} ;
		objectId = "gov.nih.nci.cabig.caaers.Admin";
		privilege = "ACCESS";
		auth =buildAuthentication(userName, roles2);
		checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(true,checkAuth);
		
		privilege = "CREATE";
		checkAuth = caaersSecurityFacade.checkAuthorization(auth, objectId, privilege);
		assertEquals(false,checkAuth);
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
