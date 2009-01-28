package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;

import java.util.HashSet;
import java.util.Set;

import org.easymock.classextension.EasyMock;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;

/**
 * @author Jared Flatow
 */
public class CSMUserRepositoryTest extends AbstractTestCase {

    private CSMUserRepositoryImpl csmUserRepository;

    private UserProvisioningManager userProvisioningManager;

    private User user;
    
    UserDao userDao;
    
    private gov.nih.nci.security.authorization.domainobjects.User csmUser;

    private String userName;
    
    private Set<Group> groups;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userProvisioningManager = registerMockFor(UserProvisioningManager.class);

        csmUserRepository = new CSMUserRepositoryImpl();
        csmUserRepository.setUserProvisioningManager(userProvisioningManager);

        userName = "somebody@betterbethere.com";
        user = new ResearchStaff();
        user.setEmailAddress(userName);
        user.setLoginId(userName);

        csmUser = registerMockFor(gov.nih.nci.security.authorization.domainobjects.User.class);
        
        userDao = registerDaoMockFor(UserDao.class);
        csmUserRepository.setUserDao(userDao);
        
        Group group = new Group();
        group.setGroupId( -2L);
        groups = new HashSet<Group>();
        groups.add(group);
    }

    public void testGetUserByName() throws Exception {
    	String invalidUserId = "nodbody@nobody.com";
        expect(userDao.getByLoginId(invalidUserId)).andReturn(null).anyTimes();
      
        replayMocks();
        try {
            csmUserRepository.getUserByName(invalidUserId);
            fail("User service should not have found a user.");
        } catch (CaaersSystemException e) { /* good */
        }
        verifyMocks();

    }
    
    public void testGetByUserName_validUser() throws Exception{
    	  expect(userDao.getByLoginId(userName)).andReturn(user);
    	  expect(userProvisioningManager.getUser(userName)).andReturn(csmUser);
    	  expect(csmUser.getUserId()).andReturn(5L);
    	  expect(userProvisioningManager.getGroups("5")).andReturn(groups);
    	  replayMocks();
          User actual = csmUserRepository.getUserByName(userName);
          verifyMocks();

          assertSame(user, actual);
    }

    public void testUserChangePassword() throws Exception {
        expect(csmUser.getPassword()).andReturn("old_password");
        csmUser.setPassword("new_password");
        userProvisioningManager.modifyUser(csmUser);
        expect(userProvisioningManager.getUser(userName)).andReturn(csmUser);
        replayMocks();
        csmUserRepository.userChangePassword(user, "new_password", 5);
        verifyMocks();

        assert (user.getPasswordHistory().size() > 0);
    }

    public void testUserHasPassword() throws Exception {
        /*
         * Skip the check password tests because we don't control csmUser encryption...
         * expect(userDao.getByEmailAddress(userName)).andReturn(user).times(2);
         * expect(userProvisioningManager.getUser(userName)).andReturn(csmUser).times(2);
         * expect(csmUser.getPassword()).andReturn("password").times(2);
         *
         * replayMocks(); assertEquals(true, csmUserRepository.userHasPassword(userName, "password"));
         * assertEquals(false, csmUserRepository.userHasPassword(userName, "not_password"));
         * verifyMocks();
         */
    }

    public void testUserHadPassword() throws Exception {
        /* */
    }

    public void testUserCreateToken() throws Exception {
        replayMocks();
        assertNotNull(csmUserRepository.userCreateToken(user));
        assertNotNull(user.getTokenTime());
        verifyMocks();
    }
    
    public void testCreateOrUpdateCSMUserAndGroupsForInvestigator_ExistingCSMUser() throws Exception{
    	Investigator inv = Fixtures.createInvestigator("Joel");
    	Organization org = Fixtures.createOrganization("test", "TEST");
    	SiteInvestigator siteInv = Fixtures.createSiteInvestigator(org, inv);
    	
    	String emailAddress = "abc@kk.com";
    	inv.setEmailAddress(emailAddress);
    	inv.setLoginId(emailAddress);
    	
    	expect(userProvisioningManager.getUser(emailAddress)).andReturn(csmUser);
    	replayMocks();
    	try {
			csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(inv, "www.joe.com");
			fail("should thorw exception");
		} catch (CaaersSystemException e) {
			
		}
    	
    }
    
    
    public void testLoginIDInUse(){
    	expect(userProvisioningManager.getUser(userName)).andReturn(csmUser);
    	
    	replayMocks();
    	
    	boolean inUse = csmUserRepository.loginIDInUse(userName);
    	assertTrue(inUse);
    }
    
    public void testLoginIDInUse_NoUser(){
    	expect(userProvisioningManager.getUser(userName)).andReturn(null);
    	expect(userDao.getByLoginId(userName)).andReturn(null).anyTimes();
    	replayMocks();
    	
    	boolean inUse = csmUserRepository.loginIDInUse(userName);
    	assertFalse(inUse);
    }
    
  
}
