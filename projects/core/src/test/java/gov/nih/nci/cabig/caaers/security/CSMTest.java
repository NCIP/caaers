/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;
import junit.framework.TestCase;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class CSMTest extends TestCase {

	/**
	 * 
	 */
	public CSMTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public CSMTest(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	
	public void testCSMLoad(){
		
		try{
//			String userId = "study_cd1";
//			String objectId = "gov.nih.nci.cabig.caaers.domain.Study";
//			String privilege = "CREATE";
//			Authentication auth = new TestingAuthenticationToken(userId,
//					"ignored", new GrantedAuthority[]{new GrantedAuthorityImpl("ignored")});
//				
//			ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml"});
//			CSMAuthorizationCheck check = (CSMAuthorizationCheck)ctx.getBean("testCsmGroupAuthorizationCheck");
//			for(int i = 0; i < 500; i++){
//				check.checkAuthorizationForObjectId(auth, privilege, objectId);
//			}
		}catch(Exception ex){
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}
		
	}

}
