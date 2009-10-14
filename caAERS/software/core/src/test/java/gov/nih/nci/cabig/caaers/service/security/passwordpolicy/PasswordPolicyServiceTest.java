package gov.nih.nci.cabig.caaers.service.security.passwordpolicy;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.not;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.ValidationException;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.sql.Timestamp; 
import java.util.Date;

/**
 * @author Jared Flatow
 * @author Ram Seethiraju
 */
public class PasswordPolicyServiceTest extends CaaersTestCase {

    private PasswordPolicyServiceImpl passwordPolicyService;

    private PasswordPolicyDao passwordPolicyDao;

    private CSMUserRepository csmUserRepository;

    private User user;

    private String userName;
    
    private DateUtils date;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        userName = "user@domain";
        user = new LocalResearchStaff();
        user.setEmailAddress(userName);
        csmUserRepository = registerMockFor(CSMUserRepository.class);
        expect(csmUserRepository.getUserByName(userName)).andReturn(user).anyTimes();
        expect(csmUserRepository.userHasPassword(userName, "real_password")).andReturn(true).anyTimes();
        expect(csmUserRepository.userHasPassword(eq(userName), not(eq("real_password"))))
                .andReturn(false).anyTimes();
        expect(csmUserRepository.userHadPassword(userName, "0ld_Password")).andReturn(true).anyTimes();
        expect(csmUserRepository.userHadPassword(eq(userName), not(eq("0ld_Password")))).andReturn(false)
                .anyTimes();        
        passwordPolicyDao = registerDaoMockFor(PasswordPolicyDao.class);
        expect(passwordPolicyDao.getById(1)).andReturn(Fixtures.createPasswordPolicy()).anyTimes();

        passwordPolicyService = new PasswordPolicyServiceImpl();
        passwordPolicyService.setCsmUserRepository(csmUserRepository);
        passwordPolicyService.setPasswordPolicyDao(passwordPolicyDao);
                
        //expect(csmUserRepository.getUserByName(userName).getFailedLoginAttempts()).andReturn(3).anyTimes();
        //user.setPasswordLastSet(new Timestamp(date.parseDateString("09/05/2009").toDate().getTime()));
        
    }
    
    public void testValidatePasswordAgainstLoginPolicy() throws Exception {
    	replayMocks();
    	try {
            tryLastSetDateForMaxPasswordAge(DateUtils.parseDateString("09/07/2009").toDate());
            fail("Password is too old.");
        } catch (ValidationException e) { /* good */  }
        
    	try {
           // tryLastSetDateForMaxPasswordAge(DateUtils.parseDateString("09/17/2009").toDate());
        } catch (ValidationException e) { fail("Password is not too old.");  }

        try {
        //	tryNumberOfFailedLoginAttempts(2);
            fail("Too many failed logins.");
        } catch (ValidationException e) { /* good */  }

        verifyMocks();    	
    }
    
    public void testValidatePasswordAgainstCreationPolicy() throws Exception {
        replayMocks();
        
       	try {
            tryLastSetDateForMinPasswordAge(DateUtils.parseDateString("09/16/2009").toDate());
            fail("Passworddddd.");
        } catch (ValidationException e) {/* good */  }

        try {
            tryLastSetDateForMinPasswordAge(DateUtils.parseDateString("09/18/2009").toDate());            
        } catch (ValidationException e) {  fail("Passworddddd.");  }

        try {
            tryPassword("TEST_PASSWORD1");
            fail("Password policy should require a lower case letter.");
        } catch (ValidationException e) { /* good */
        }
        try {
            tryPassword("test_password1");
            fail("Password policy should require an upper case letter.");
        } catch (ValidationException e) { /* good */
        }
        try {
            tryPassword("testPassword1");
            fail("Password policy should require a non-alphanumeric character.");
        } catch (ValidationException e) { /* good */
        }
        try {
            tryPassword("test_P@ssword");
            fail("Password policy should require a base ten digit.");
        } catch (ValidationException e) { /* good */
        }
        try {
            tryPassword("user_P@ssword1");
            fail("Password policy should require username substring not in password.");
        } catch (ValidationException e) { /* good */
        }
        try {
            tryPassword("Sh0r|");
            fail("Password policy should require longer password.");
        } catch (ValidationException e) { /* good */
        	//fail("dont worry did not fail");
        }
        try {
            tryPassword("0ld_Password");
            fail("Password policy should not allow a recent password.");
        } catch (ValidationException e) { /* good */
        }
        tryPassword("good_str0ng_P@ssword");
        verifyMocks();
    }

    private boolean tryPassword(String password) {
        return passwordPolicyService.validatePasswordAgainstCreationPolicy(new Credential(userName, password));
    }
        
    //To test the maxPasswordAge() method of LoginPolicyValidator
    private boolean tryLastSetDateForMaxPasswordAge(Date lastDate) {
    	String password = "Abcdef1!";
    	user.setPasswordLastSet(new Timestamp(lastDate.getTime()));
        return passwordPolicyService.validatePasswordAgainstLoginPolicy(new Credential(userName, password));
    }
    private boolean tryNumberOfFailedLoginAttempts(int number) {
    	String password = "Abcdef1!";
    	user.setFailedLoginAttempts(number);
    	return passwordPolicyService.validatePasswordAgainstLoginPolicy(new Credential(userName, password));
    }
    
    //To test minPasswordAge() method of PasswordCreationPolicyValidator
    private boolean tryLastSetDateForMinPasswordAge(Date lastDate) {
    	String password = "Abcdef1!";
    	user.setPasswordLastSet(new Timestamp(lastDate.getTime()));
    	return passwordPolicyService.validatePasswordAgainstCreationPolicy(new Credential(userName, password));
    }       
}
