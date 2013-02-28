/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

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

        long l = System.currentTimeMillis()  ;
        String loginName = "x" + l;
        
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNull(x);
        }


        
        {
            User x = new User();
            x.setFirstName("x");
            x.setLastName("y");
            x.setMiddleName("Z");
            x.setLoginName(loginName);
            x.setEmailAddress(loginName+"@xxx.com");
            x.findRoleMembership(UserGroupType.system_administrator);

            userRepository.createOrUpdateUser(x, "www.xxx.com/test");
            userRepository.provisionUser(x);
            assertNotNull(x.getCsmUser().getUserId());
        }
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNotNull(x);
            assertEquals("x", x.getFirstName());
            assertTrue(1 == x.getUserGroupTypes().size());
            assertTrue(x.getUserGroupTypes().contains(UserGroupType.system_administrator));


        }
    }

    public void testGetUserGroups() throws Exception {


        long l = System.currentTimeMillis()  ;
        String loginName = "x" + l;
        {
            User x = new User();
            x.setFirstName("x");
            x.setLastName("y");
            x.setMiddleName("Z");
            x.setLoginName(loginName);
            x.setEmailAddress(loginName+"@xxx.com");
            x.findRoleMembership(UserGroupType.system_administrator);
            x.findRoleMembership(UserGroupType.business_administrator);
            x.findRoleMembership(UserGroupType.ae_rule_and_report_manager);

            userRepository.createOrUpdateUser(x, "www.xxx.com/test");
            userRepository.provisionUser(x);
            assertNotNull(x.getCsmUser().getUserId());
        }
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNotNull(x);
            assertEquals("x", x.getFirstName());
            assertTrue(3 == x.getUserGroupTypes().size());
            assertTrue(x.getUserGroupTypes().contains(UserGroupType.ae_rule_and_report_manager));


        }
    }


    public void testProvisionUser(){


        long l = System.currentTimeMillis()  ;
        String loginName = "x" + l;
        {
             User x = new User();
            x.setFirstName("x");
            x.setLastName("y");
            x.setMiddleName("Z");
            x.setLoginName(loginName);
            x.setEmailAddress(loginName+"@xxx.com");
            x.findRoleMembership(UserGroupType.system_administrator);

            userRepository.createOrUpdateUser(x, "www.xxx.com/test");
            userRepository.provisionUser(x);
            assertNotNull(x.getCsmUser().getUserId());
        }
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNotNull(x);
            assertEquals("x", x.getFirstName());
            assertTrue(1 == x.getUserGroupTypes().size());
            assertTrue(x.getUserGroupTypes().contains(UserGroupType.system_administrator));


        }
        {
           User x = userRepository.getUserByLoginName(loginName);
            RoleMembership rm = x.findRoleMembership(UserGroupType.person_and_organization_information_manager);
            rm.setAllSite(true);
            userRepository.provisionUser(x);

        }

        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNotNull(x);
            assertEquals("x", x.getFirstName());
            assertTrue(x.getUserGroupTypes().contains(UserGroupType.system_administrator));
            assertFalse(x.getUserGroupTypes().contains(UserGroupType.person_and_organization_information_manager));
            assertEquals(1, x.getUserGroupTypes().size());
            assertFalse(x.findRoleMembership(UserGroupType.person_and_organization_information_manager).isAllSite());

        }
        
        {
           User x = userRepository.getUserByLoginName(loginName);
           x.removeRoleMembership(UserGroupType.person_and_organization_information_manager);
           x.removeRoleMembership(UserGroupType.system_administrator);
           RoleMembership rm = x.findRoleMembership(UserGroupType.ae_reporter);
           rm.addOrganizationNCICode("CTEP");
           rm.addStudyIdentifier("5876");
           userRepository.provisionUser(x);
        }


        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNotNull(x);
            assertEquals("x", x.getFirstName());
            assertFalse(x.getUserGroupTypes().contains(UserGroupType.ae_reporter));
            assertFalse(x.findRoleMembership(UserGroupType.ae_reporter).isAllSite());
            assertFalse(x.findRoleMembership(UserGroupType.ae_reporter).getOrganizationNCICodes().contains("CTEP"));
            assertFalse(x.findRoleMembership(UserGroupType.ae_reporter).getStudyIdentifiers().contains("5876"));

        }


    }
    
    public void testUpdateUserEndDate() throws Exception {

        long l = System.currentTimeMillis()  ;
        String loginName = "x" + l;
        
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNull(x);
        }

        
        {
            User x = new User();
            x.setFirstName("x");
            x.setLastName("y");
            x.setMiddleName("Z");
            x.setLoginName(loginName);
            x.setEmailAddress(loginName+"@xxx.com");
            x.findRoleMembership(UserGroupType.system_administrator);

            userRepository.createOrUpdateUser(x, "www.xxx.com/test");
            userRepository.provisionUser(x);
            assertNotNull(x.getCsmUser().getUserId());
        }
        {
            User x = userRepository.getUserByLoginName(loginName);
            assertNull(x.getCsmUser().getEndDate());
            Date today = new Date();
            x.getCsmUser().setEndDate(today);
            
            userRepository.createOrUpdateUser(x, null);
            
            User y = userRepository.getUserByLoginName(loginName);
            assertNotNull(y);
            assertEquals("x", y.getFirstName());
            assertTrue(1 == y.getUserGroupTypes().size());
            assertTrue(y.getUserGroupTypes().contains(UserGroupType.system_administrator));
            assertNotNull(y.getCsmUser().getEndDate());
            assertEquals(0,DateUtils.compateDateAndTime(today, y.getCsmUser().getEndDate()));
            assertTrue(y.isLocked());

        }
    }

}
