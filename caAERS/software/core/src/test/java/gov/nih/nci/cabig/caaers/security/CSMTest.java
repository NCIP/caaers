/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.security.UserProvisioningManager;

import org.acegisecurity.intercept.method.aspectj.AspectJSecurityInterceptor;
import org.aspectj.lang.Aspects;
import org.springframework.context.ApplicationContext;

import com.mchange.v2.c3p0.DriverManagerDataSource;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMTest extends CaaersTestCase {
    private UserProvisioningManager mgr;

    public CSMTest() {
    }

   

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ApplicationContext ctx = getDeployedApplicationContext();
        mgr = (UserProvisioningManager) ctx.getBean("csmUserProvisioningManager");
        AspectJSecurityInterceptor interceptor = (AspectJSecurityInterceptor) ctx.getBean("daoSecurity");
        StudyParticipantAssignmentAspect aspect = Aspects.aspectOf(StudyParticipantAssignmentAspect.class);
        aspect.setSecurityInterceptor(interceptor);
    }



 /*   public void testCSMLoad() {

        try {
            String userId = "study_cd1";
            String objectId = "gov.nih.nci.cabig.caaers.domain.Study";
            String privilege = "CREATE";
            Authentication auth = new TestingAuthenticationToken(userId, "ignored",
                            new GrantedAuthority[] { new GrantedAuthorityImpl("ignored") });

            ApplicationContext ctx = getDeployedApplicationContext();


            CSMAuthorizationCheck check = (CSMAuthorizationCheck) ctx
                            .getBean("testCsmGroupAuthorizationCheck");
            for (int i = 0; i < 500; i++) {
              //  printStats(bds);
                check.checkAuthorizationForObjectId(auth, privilege, objectId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }

    }*/

    private void printStats(DriverManagerDataSource bds) {
        System.out.println("##########################################");
//        System.out.println("numActive: " + bds.getgetNumActive());
//        System.out.println("numIdle: " + bds.getNumIdle());
//        System.out.println("##########################################");
    }

    public void testSuperUserPolicy() {
        try {
            String group = "caaers_super_user";
            String[] privileges = new String[] { "READ", "CREATE", "UPDATE" };
            String[] objectIds = new String[] { "gov.nih.nci.cabig.caaers.domain.Study",
                    "gov.nih.nci.cabig.caaers.domain.Participant",
                    "gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport",
                    "gov.nih.nci.cabig.caaers.domain.Rule" };

            verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds, privileges);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

    public void testStudyCoordinatorPolicy() {
        try {
            String group = "caaers_study_cd";
            String[] privileges = new String[] { "READ", "UPDATE" };
            String[] objectIds = new String[] { "gov.nih.nci.cabig.caaers.domain.Study" };

            verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds, privileges);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

    public void testParticipantCoordinatorPolicy() {
        try {
            String group = "caaers_participant_cd";
            String[] privileges = new String[] { "READ", "CREATE", "UPDATE" };
            String[] objectIds = new String[] { "gov.nih.nci.cabig.caaers.domain.Participant",
                    "gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport" };

            verifyPrivilegesForObjectIdsAndGroup(mgr, group, objectIds, privileges);

        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

    private static void verifyPrivilegesForObjectIdsAndGroup(UserProvisioningManager mgr,
                    String group, String[] objectIds, String[] privileges) throws Exception {
        for (String objectId : objectIds) {
            verifyPrivilegesForGroup(mgr, group, objectId, privileges);
        }
    }

    private static void verifyPrivilegesForGroup(UserProvisioningManager mgr, String group,
                    String objectId, String[] privileges) throws Exception {
        for (String privilege : privileges) {
            String msg = getGroupAccessErrorMessage(group, privilege, objectId);
            assertTrue(msg, mgr.checkPermissionForGroup(group, objectId, privilege));
        }

    }

    private static String getGroupAccessErrorMessage(String group, String privilege, String objectId) {
        return group + " doesn't have " + privilege + " privilege on " + objectId;
    }

}
