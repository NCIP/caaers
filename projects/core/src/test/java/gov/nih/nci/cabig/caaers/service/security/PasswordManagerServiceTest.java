package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
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

   // private UserDao userDao;
    
    private InvestigatorDao investigatorDao;
    
    private ResearchStaffDao researchStaffDao;

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
        user.setLoginId(userName);

        csmUser = registerMockFor(gov.nih.nci.security.authorization.domainobjects.User.class);

       // userDao = registerDaoMockFor(UserDao.class);
        //expect(userDao.getByLoginId(userName)).andReturn(user).anyTimes();

        userProvisioningManager = registerMockFor(UserProvisioningManager.class);
        expect(userProvisioningManager.getUser(userName)).andReturn(csmUser).anyTimes();

        csmUserRepository = new CSMUserRepositoryImpl();
        //csmUserRepository.setUserDao(userDao);
        csmUserRepository.setUserProvisioningManager(userProvisioningManager);

        passwordPolicyDao = registerDaoMockFor(PasswordPolicyDao.class);
        expect(passwordPolicyDao.getById(1)).andReturn(Fixtures.createPasswordPolicy()).anyTimes();

        passwordPolicyService = new PasswordPolicyServiceImpl();
        passwordPolicyService.setCsmUserRepository(csmUserRepository);
        passwordPolicyService.setPasswordPolicyDao(passwordPolicyDao);

        passwordManagerService = new PasswordManagerServiceImpl();
        passwordManagerService.setPasswordPolicyService(passwordPolicyService);
        passwordManagerService.setCsmUserRepository(csmUserRepository);
        
       investigatorDao = registerDaoMockFor(InvestigatorDao.class);
       researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);
       passwordManagerService.setInvestigatorDao(investigatorDao);
       passwordManagerService.setResearchStaffDao(researchStaffDao);
       
       csmUserRepository.setResearchStaffDao(researchStaffDao);
       csmUserRepository.setInvestigatorDao(investigatorDao);
       
    }

    public void testRequestToken() throws Exception {
//        userDao.save(user);
        expect(researchStaffDao.getByLoginId(userName)).andReturn(null);
        Investigator inv = Fixtures.createInvestigator("tester");
        inv.setLoginId(userName);
        expect(investigatorDao.getByLoginId(userName)).andReturn(inv);
        investigatorDao.save(inv);
        replayMocks();
        assertNotNull(passwordManagerService.requestToken(userName));
        verifyMocks();
    }

    public void testSetPassword() throws Exception {
        expect(csmUser.getPassword()).andReturn("old_password").atLeastOnce();
        csmUser.setPassword("v@l1d_Password");

        userProvisioningManager.modifyUser(csmUser);
        expect(researchStaffDao.getByLoginId(userName)).andReturn(null).anyTimes();
        Investigator inv = Fixtures.createInvestigator("tester");
        inv.setLoginId(userName);
        inv.setEmailAddress(userName);
        expect(investigatorDao.getByLoginId(userName)).andReturn(inv).anyTimes();
        investigatorDao.save(inv);
        expectLastCall().times(3);
        replayMocks();
        User user = passwordManagerService.requestToken(userName);
        passwordManagerService.setPassword(userName, "v@l1d_Password", user.getToken());
        try {
            passwordManagerService.setPassword(userName, "v@l1d_Password", user.getToken());
            fail("Shouldn't accept invalid token.");
        } catch (CaaersSystemException e) { /* good */
        }
        try {
            User user2 = passwordManagerService.requestToken(userName);
            passwordManagerService.setPassword(userName, "invalid_Password", user2.getToken());
            fail("Shouldn't accept invalid password.");
        } catch (CaaersSystemException e) { /* good */
        }
        verifyMocks();
    }
}
