/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class CSMTest extends CaaersTestCase {

    /**
     * 
     */
    public CSMTest() {
        // TODO Auto-generated constructor stub
    }



    public void testCSMLoad() {

        try {
            MyThread t = new MyThread();
            t.start();
            t.join(60 * 1000);
            assertTrue(t.getDone());
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

    private class MyThread extends Thread {
        private boolean done = false;

        public boolean getDone() {
            return done;
        }

        public void run() {
            String userId = "study_cd1";
            String objectId = "gov.nih.nci.cabig.caaers.domain.Study";
            String privilege = "CREATE";
            Authentication auth = new TestingAuthenticationToken(userId, "ignored",
                            new GrantedAuthority[] { new GrantedAuthorityImpl("ignored") });

            ApplicationContext ctx = getDeployedApplicationContext();

            // BasicDataSource bds = (BasicDataSource)ctx.getBean("dataSource");

            CSMAuthorizationCheck check = (CSMAuthorizationCheck) ctx
                            .getBean("testCsmGroupAuthorizationCheck");
            for (int i = 0; i < 50; i++) {
                // printStats(bds);
                check.checkAuthorizationForObjectId(auth, privilege, objectId);
            }
            done = true;
        }
    }

}
