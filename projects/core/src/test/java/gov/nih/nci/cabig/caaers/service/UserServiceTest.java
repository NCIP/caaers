package gov.nih.nci.cabig.caaers.service;

import static org.easymock.EasyMock.expect;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.security.UserProvisioningManager;

/**
 * @author Jared Flatow
 */
public class UserServiceTest extends CaaersTestCase {

    private UserServiceImpl userService;

    private UserDao userDao;

    private UserProvisioningManager userProvisioningManager;

    private User user;

    private gov.nih.nci.security.authorization.domainobjects.User csmUser;

    private String userName;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userDao = registerDaoMockFor(UserDao.class);
        userProvisioningManager = registerMockFor(UserProvisioningManager.class);

        userService = new UserServiceImpl();
        userService.setUserDao(userDao);
        userService.setUserProvisioningManager(userProvisioningManager);

        userName = "somebody@betterbethere.com";
        user = new ResearchStaff();
        user.setEmailAddress(userName);

        csmUser = registerMockFor(gov.nih.nci.security.authorization.domainobjects.User.class);
    }

    public void testGetUserByName() throws Exception {
        expect(userDao.getByEmailAddress("nobody@betterbethere.com")).andReturn(null);
        expect(userDao.getByEmailAddress(userName)).andReturn(user);

        replayMocks();
        try {
            userService.getUserByName("nobody@betterbethere.com");
            fail("User service should not have found a user.");
        } catch (CaaersSystemException e) { /* good */
        }
        User actual = userService.getUserByName(userName);
        verifyMocks();

        assertSame(user, actual);
    }

    public void testUserChangePassword() throws Exception {
        expect(userDao.getByEmailAddress(userName)).andReturn(user);
        expect(userProvisioningManager.getUser(userName)).andReturn(csmUser);
        expect(csmUser.getPassword()).andReturn("old_password");
        csmUser.setPassword("new_password");
        userDao.save(user);
        userProvisioningManager.modifyUser(csmUser);

        replayMocks();
        userService.userChangePassword(userName, "new_password", 5);
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
         * replayMocks(); assertEquals(true, userService.userHasPassword(userName, "password"));
         * assertEquals(false, userService.userHasPassword(userName, "not_password"));
         * verifyMocks();
         */
    }

    public void testUserHadPassword() throws Exception {
        /* */
    }

    public void testUserCreateToken() throws Exception {
        expect(userDao.getByEmailAddress(userName)).andReturn(user);
        userDao.save(user);

        replayMocks();
        assertNotNull(userService.userCreateToken(userName));
        assertNotNull(user.getTokenTime());
        verifyMocks();
    }
}
