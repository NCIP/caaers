/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;

import java.util.Iterator;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMTest extends TestCase {

	private ClassPathXmlApplicationContext ctx;

	private UserProvisioningManager mgr;

	public CSMTest() {
		init();
	}

	public CSMTest(String name) {
		super(name);
		init();
	}

	public void init() {
		ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });
		mgr = (UserProvisioningManager) ctx
				.getBean("csmUserProvisioningManager");
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new CSMTest("testSuperUserPolicy"));
		suite.addTest(new CSMTest("testStudyCoordinatorPolicy"));
		suite.addTest(new CSMTest("testParticipantCoordinatorPolicy"));
		return suite;
	}

	public void testCSMLoad() {

		try {
			String userId = "study_cd1";
			String objectId = "gov.nih.nci.cabig.caaers.domain.Study";
			String privilege = "CREATE";
			Authentication auth = new TestingAuthenticationToken(
					userId,
					"ignored",
					new GrantedAuthority[] { new GrantedAuthorityImpl("ignored") });

			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml" });

			BasicDataSource bds = (BasicDataSource) ctx.getBean("dataSource");

			CSMAuthorizationCheck check = (CSMAuthorizationCheck) ctx
					.getBean("testCsmGroupAuthorizationCheck");
			for (int i = 0; i < 500; i++) {
				printStats(bds);
				check.checkAuthorizationForObjectId(auth, privilege, objectId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}

	}

	private void printStats(BasicDataSource bds) {
		System.out.println("##########################################");
		System.out.println("numActive: " + bds.getNumActive());
		System.out.println("numIdle: " + bds.getNumIdle());
		System.out.println("##########################################");
	}

	public void testSuperUserPolicy() {
		try {
			String group = "caaers_super_user";
			String[] privileges = new String[] { "READ", "CREATE", "UPDATE" };
			String[] objectIds = new String[] {
					"gov.nih.nci.cabig.caaers.domain.Study",
					"gov.nih.nci.cabig.caaers.domain.Participant",
					"gov.nih.nci.cabig.caaers.domain.AdverseEventReport",
					"gov.nih.nci.cabig.caaers.domain.Rule" };

			verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds,
					privileges);

		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}
	}

	public void testStudyCoordinatorPolicy() {
		try {
			String group = "caaers_study_cd";
			String[] privileges = new String[] { "READ", "CREATE", "UPDATE" };
			String[] objectIds = new String[] { "gov.nih.nci.cabig.caaers.domain.Study" };

			verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds,
					privileges);

		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}
	}

	public void testParticipantCoordinatorPolicy() {
		try {
			String group = "caaers_participant_cd";
			String[] privileges = new String[] { "READ", "CREATE", "UPDATE" };
			String[] objectIds = new String[] {
					"gov.nih.nci.cabig.caaers.domain.Participant",
					"gov.nih.nci.cabig.caaers.domain.AdverseEventReport" };

			verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds,
					privileges);

		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}
	}

	private void verifyPrivilegesForObjectIdsAndGroup(
			UserProvisioningManager mgr, String group, String[] objectIds,
			String[] privileges) throws Exception {
		for (String objectId : objectIds) {
			verifyPrivilegesForGroup(mgr, group, objectId, privileges);
		}
	}

	private void verifyPrivilegesForGroup(UserProvisioningManager mgr,
			String group, String objectId, String[] privileges)
			throws Exception {
		for (String privilege : privileges) {
			String msg = getGroupAccessErrorMessage(group, privilege, objectId);
			assertTrue(msg, mgr.checkPermissionForGroup(group, objectId,
					privilege));
		}

	}

	private String getGroupAccessErrorMessage(String group, String privilege,
			String objectId) {
		return group + " doesn't have " + privilege + " privilege on "
				+ objectId;
	}

}
