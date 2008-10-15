package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.security.UserProvisioningManager;
import static org.easymock.EasyMock.expect;

/**
 * @author Jared Flatow
 */
public class CSMUserRepositoryTest extends CaaersTestCase {

    private CSMUserRepositoryImpl csmUserRepository;

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

        csmUserRepository = new CSMUserRepositoryImpl();
        csmUserRepository.setUserDao(userDao);
        csmUserRepository.setUserProvisioningManager(userProvisioningManager);

        userName = "somebody@betterbethere.com";
        user = new ResearchStaff();
        user.setEmailAddress(userName);

        csmUser = registerMockFor(gov.nih.nci.security.authorization.domainobjects.User.class);
    }

    public void testGetUserByName() throws Exception {
        expect(userDao.getByLoginId("nobody@betterbethere.com")).andReturn(null);
        expect(userDao.getByLoginId(userName)).andReturn(user);

        replayMocks();
        try {
            csmUserRepository.getUserByName("nobody@betterbethere.com");
            fail("User service should not have found a user.");
        } catch (CaaersSystemException e) { /* good */
        }
        User actual = csmUserRepository.getUserByName(userName);
        verifyMocks();

        assertSame(user, actual);
    }

    public void testUserChangePassword() throws Exception {
        expect(userDao.getByLoginId(userName)).andReturn(user);
        expect(userProvisioningManager.getUser(userName)).andReturn(csmUser);
        expect(csmUser.getPassword()).andReturn("old_password");
        csmUser.setPassword("new_password");
        userDao.save(user);
        userProvisioningManager.modifyUser(csmUser);

        replayMocks();
        csmUserRepository.userChangePassword(userName, "new_password", 5);
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
        expect(userDao.getByLoginId(userName)).andReturn(user);
        userDao.save(user);

        replayMocks();
        assertNotNull(csmUserRepository.userCreateToken(userName));
        assertNotNull(user.getTokenTime());
        verifyMocks();
    }
}
