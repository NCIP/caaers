package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

import static org.easymock.EasyMock.expect;
import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import org.easymock.classextension.EasyMock;

/**
 * @author Ram Seethiraju
 */
public class PasswordCreationPolicyValidatorTest extends AbstractTestCase {    
	
		private PasswordCreationPolicyValidator passwordCreationPolicyValidator;    
		private PasswordCreationPolicy passwordCreationPolicy;    
		private PasswordPolicy passwordPolicy;   
		private CombinationPolicy combinationPolicy;
		private CSMUserRepository csmUserRepository;    
		private Credential credential;
		private String userName;    
		private String password;    
		private User user;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setUp() throws Exception {        
		super.setUp();        
		userName = "wxyz1234";        
		password = "Password1!";  
		Organization org = Fixtures.createOrganization("test");        
		user =  Fixtures.createResearchStaff(org, Arrays.asList(new UserGroupType[] {UserGroupType.caaers_admin, UserGroupType.caaers_ae_cd}) , "Test");
		
		csmUserRepository = registerMockFor(CSMUserRepository.class);       
		passwordPolicy = new PasswordPolicy();   
		combinationPolicy = new CombinationPolicy();
		passwordCreationPolicy = new PasswordCreationPolicy();				
		passwordCreationPolicyValidator = new PasswordCreationPolicyValidator();    
		passwordCreationPolicyValidator.setCsmUserRepository(csmUserRepository);  
		credential = new Credential(userName, password);  
		passwordPolicy.setPasswordCreationPolicy(passwordCreationPolicy);
		passwordCreationPolicy.setMinPasswordLength(7);
		passwordCreationPolicy.setMinPasswordAge(180);
		passwordCreationPolicy.setPasswordHistorySize(3);
		passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
		combinationPolicy.setBaseTenDigitRequired(true);
		combinationPolicy.setLowerCaseAlphabetRequired(true);
		combinationPolicy.setMaxSubstringLength(3);
		combinationPolicy.setNonAlphaNumericRequired(true);
		combinationPolicy.setUpperCaseAlphabetRequired(false);

		expect(csmUserRepository.getUserByName(userName)).andReturn(user).anyTimes();
	}  
	
	/**
	 * 1. This testcase checks if the all the validations return "true" in a positive case.
	 */
	public void testAllValidations_Success() {
		expect(csmUserRepository.userHadPassword(userName, password)).andReturn(false);
		expect(csmUserRepository.userHasPassword(userName, password)).andReturn(false);
		replayMocks();
		assertTrue(passwordCreationPolicyValidator.validate(passwordPolicy, credential, new ValidationErrors()));
		verifyMocks();   
	}

	/**
	 * 2. This testcase checks if the validate method returns false if the user tries to set a password which is already used.
	 */
	public void testPasswordHistoryValidation_Failure() {
		expect(csmUserRepository.userHasPassword(userName, password)).andReturn(false);
		expect(csmUserRepository.userHadPassword(userName, password)).andReturn(true);
		replayMocks();
		assertFalse(passwordCreationPolicyValidator.validate(passwordPolicy, credential, new ValidationErrors()));
		verifyMocks();   
	}
	
	/**
	 * 3. This method has various assertions to check the password creation validations on various passwords.
	 */
	public void testAllPasswordFormatValidations() {
		expect(csmUserRepository.userHasPassword((String)EasyMock.anyObject(), (String)EasyMock.anyObject() )).andReturn(false).anyTimes();
		expect(csmUserRepository.userHadPassword((String)EasyMock.anyObject(), (String)EasyMock.anyObject() )).andReturn(false).anyTimes();
		replayMocks();		
		assertFalse(tryThisPassword("Pass1!")); // Purpose of this assertion: To test for password length to be 7
		assertFalse(tryThisPassword("Password1")); // Purpose of this assertion: To test for password to have a special character
		assertFalse(tryThisPassword("Password!")); // Purpose of this assertion: To test for password to have a numeric (0-9)
		assertFalse(tryThisPassword("PASSWORD1!")); // Purpose of this assertion: To test for password to have a lower-case alphabet
		assertFalse(tryThisPassword("wxyzPass1!")); // Purpose of this assertion: To test for password not to have more than 3 characters from userName
		verifyMocks(); 
	}
	
	/**
	 * This method is to call the validate method using various passwords.
	 * @param thisPassword
	 * @return - true if all the validations pass; false if any one of the validations fail
	 */
	public boolean tryThisPassword(String thisPassword) {
		Credential credential_new = new Credential(userName, thisPassword);
		return passwordCreationPolicyValidator.validate(passwordPolicy, credential_new, new ValidationErrors());
	}
	
} 
