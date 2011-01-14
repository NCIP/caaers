package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * UserRepositoryImpl Tester.
 *
 * @author Biju Joseph
 * @since <pre>01/14/2011</pre>
 * 
 */
public class UserRepositoryIntegrationTest extends CaaersTestCase {

    UserRepository userRepository;

    public void setUp() throws Exception {
        super.setUp();
        userRepository = (UserRepository)getDeployedApplicationContext().getBean("userRepository");
    }

    public void testGetUserByLoginName() throws Exception {
        fail("Not Implemented");
    }

    public void testGetUserGroups() throws Exception {
        fail("Not Implemented");
    }


    public void testProvisionUser(){


        long l = System.currentTimeMillis()  ;
        String loginName = "x" + l;
        {
             _User x = new _User();
            x.setFirstName("x");
            x.setLastName("y");
            x.setMiddleName("Z");
            x.setLoginName(loginName);
            x.setEmailAddress(loginName+"@xxx.com");
            x.findRoleMembership(UserGroupType.system_administrator);

            userRepository.createOrUpdateUser(x, "www.xxx.com/test");
            assertNotNull(x.getCsmUser().getUserId());
        }
    }

}
