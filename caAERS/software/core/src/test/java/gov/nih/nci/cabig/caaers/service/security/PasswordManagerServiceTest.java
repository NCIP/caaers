/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyServiceImpl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceTest extends AbstractTestCase {

    private PasswordManagerServiceImpl passwordManagerService;
    private PasswordPolicyServiceImpl passwordPolicyService;
    private PasswordPolicyDao passwordPolicyDao;

    private User user;
    private gov.nih.nci.security.authorization.domainobjects.User csmUser;
    private UserRepository userRepository;
    
   
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userRepository = registerMockFor(UserRepository.class);
        passwordPolicyDao = registerDaoMockFor(PasswordPolicyDao.class);
        passwordManagerService = new PasswordManagerServiceImpl();
        passwordPolicyService = new PasswordPolicyServiceImpl();
    }

    public void testRequestToken() throws Exception {
       
    	String userName = "user@domain";
        user = new User();
        user.setTokenTime(new Timestamp(new Date().getTime()));
        
        
        passwordManagerService.setUserRepository(userRepository);
        expect(userRepository.getUserByLoginName(userName)).andReturn(user).anyTimes();
        userRepository.save(user);
        
        replayMocks();
        User returnedUser = passwordManagerService.requestToken(userName);
        verifyMocks();
        assertNotNull(returnedUser);
        assertNotNull(returnedUser.getToken());
        assertTrue(StringUtils.isNotEmpty(returnedUser.getToken()));
    }

    public void testSetPassword() throws Exception {
    	
    	String userName = "user@domain";
        user = new User();
        user.setLoginName(userName);
        csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
        csmUser.setLoginName(userName);
        csmUser.setPassword("v@l1d_Password");
    	user.generateNewToken();
    	user.setCsmUser(csmUser);
    	
        passwordPolicyService.setUserRepository(userRepository);
        passwordPolicyService.setPasswordPolicyDao(passwordPolicyDao);
        
        passwordManagerService.setUserRepository(userRepository);
        passwordManagerService.setPasswordPolicyService(passwordPolicyService);
        
        expect(passwordPolicyDao.getById(1)).andReturn(Fixtures.createPasswordPolicy()).anyTimes();
        expect(userRepository.getUserByLoginName(userName)).andReturn(user).anyTimes();
        expect(userRepository.getUserByLoginName("Invalid_user")).andReturn(null).anyTimes();
        userRepository.userChangePassword(user, "v@l1d_Password", 1);
        expectLastCall().anyTimes();
        
        replayMocks();
        passwordManagerService.setPassword(userName, "v@l1d_Password", user.getToken());
        try {
        	user.generateNewToken();
            passwordManagerService.setPassword(userName, "v@l1d_Password", "123");
            fail("Shouldn't accept invalid token.");
        } catch (CaaersSystemException e) { /* good */
        }
        try {
        	user.generateNewToken();
            passwordManagerService.setPassword(userName, "invalid_Password", user.getToken());
            fail("Shouldn't accept invalid password.");
        } catch (CaaersSystemException e) { /* good */
        }
        try {
            passwordManagerService.setPassword("Invalid_user", "v@l1d_Password", user.getToken());
            fail("No Such User");
        } catch (CaaersNoSuchUserException e) { /* good */
        }
        verifyMocks();
        
    }
}
