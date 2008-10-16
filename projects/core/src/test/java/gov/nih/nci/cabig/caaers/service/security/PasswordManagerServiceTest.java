package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyServiceImpl;
import gov.nih.nci.security.UserProvisioningManager;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceTest extends CaaersTestCase {

    private PasswordManagerServiceImpl passwordManagerService;

    private PasswordPolicyServiceImpl passwordPolicyService;

    private PasswordPolicyDao passwordPolicyDao;

    private CSMUserRepositoryImpl csmUserRepository;

    private UserDao userDao;

    private UserProvisioningManager userProvisioningManager;

    private User user;

    private gov.nih.nci.security.authorization.domainobjects.User csmUser;

    private String userName;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        userName = "user@domain";
        user = new ResearchStaff();
        user.setEmailAddress(userName);

        csmUser = registerMockFor(gov.nih.nci.security.authorization.domainobjects.User.class);

        userDao = registerDaoMockFor(UserDao.class);
        expect(userDao.getByLoginId(userName)).andReturn(user).anyTimes();

        userProvisioningManager = registerMockFor(UserProvisioningManager.class);
        expect(userProvisioningManager.getUser(userName)).andReturn(csmUser).anyTimes();

        csmUserRepository = new CSMUserRepositoryImpl();
        csmUserRepository.setUserDao(userDao);
        csmUserRepository.setUserProvisioningManager(userProvisioningManager);

        passwordPolicyDao = registerDaoMockFor(PasswordPolicyDao.class);
        expect(passwordPolicyDao.getById(1)).andReturn(Fixtures.createPasswordPolicy()).anyTimes();

        passwordPolicyService = new PasswordPolicyServiceImpl();
        passwordPolicyService.setCsmUserRepository(csmUserRepository);
        passwordPolicyService.setPasswordPolicyDao(passwordPolicyDao);

        passwordManagerService = new PasswordManagerServiceImpl();
        passwordManagerService.setPasswordPolicyService(passwordPolicyService);
        passwordManagerService.setCsmUserRepository(csmUserRepository);
    }

    public void testRequestToken() throws Exception {
        userDao.save(user);
        replayMocks();
        assertNotNull(passwordManagerService.requestToken(userName));
        verifyMocks();
    }

    public void testSetPassword() throws Exception {
        expect(csmUser.getPassword()).andReturn("old_password").atLeastOnce();
        csmUser.setPassword("v@l1d_Password");
        userDao.save(user);
        expectLastCall().times(3);
        userProvisioningManager.modifyUser(csmUser);

        replayMocks();
        String token = passwordManagerService.requestToken(userName);
        passwordManagerService.setPassword(userName, "v@l1d_Password", token);
        try {
            passwordManagerService.setPassword(userName, "v@l1d_Password", token);
            fail("Shouldn't accept invalid token.");
        } catch (CaaersSystemException e) { /* good */
        }
        try {
            token = passwordManagerService.requestToken(userName);
            passwordManagerService.setPassword(userName, "invalid_Password", token);
            fail("Shouldn't accept invalid password.");
        } catch (CaaersSystemException e) { /* good */
        }
        verifyMocks();
    }
}
