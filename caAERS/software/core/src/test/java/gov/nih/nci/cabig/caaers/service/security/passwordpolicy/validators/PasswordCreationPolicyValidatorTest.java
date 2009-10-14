//package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators; 
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

/**
 * @author Ram Seethiraju
 */
public class PasswordCreationPolicyValidatorTest extends AbstractTestCase {    
	
		private PasswordCreationPolicyValidator passwordCreationPolicyValidator;    
		private PasswordCreationPolicy passwordCreationPolicy;    
		private PasswordPolicy passwordPolicy;   
		private CombinationValidator combinationValidator;
		private CombinationPolicy combinationPolicy;
		private CSMUserRepository csmUserRepository;    
		private Credential credential;    
		private String userName;    
		private String password;    
		private User user;
		private long age;
	
	@Override
	protected void setUp() throws Exception {        
		super.setUp();        
		userName = "xyz";        
		password = "Password1!";  
		
		Organization org = Fixtures.createOrganization("test");        
		user =  Fixtures.createResearchStaff(org, Arrays.asList(new UserGroupType[] {UserGroupType.caaers_admin, UserGroupType.caaers_ae_cd}) , "Test");
		
		csmUserRepository = registerMockFor(CSMUserRepository.class);       
		passwordPolicy = new PasswordPolicy();   
		combinationPolicy = new CombinationPolicy();
		combinationValidator = new CombinationValidator();
		passwordCreationPolicy = new PasswordCreationPolicy();				
		passwordCreationPolicyValidator = new PasswordCreationPolicyValidator();    
		passwordCreationPolicyValidator.setCsmUserRepository(csmUserRepository);  
		
		credential = new Credential(userName, password);  
		
		passwordPolicy.setPasswordCreationPolicy(passwordCreationPolicy);
		passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
		passwordCreationPolicy.setMinPasswordAge(180);
		expect(csmUserRepository.getUserByName(userName)).andReturn(user).anyTimes();
	}  
	
	/**
	 * 1. This testcase checks if the all the validations return "true" in a positive case.
	 */
	public void testAllValidations_Success() {
		expect(csmUserRepository.userHadPassword(userName, password)).andReturn(false).anyTimes();
		expect(csmUserRepository.userHasPassword(userName, password)).andReturn(false).anyTimes();
		replayMocks();
		assertTrue(passwordCreationPolicyValidator.validate(passwordPolicy, credential, new ValidationErrors()));
		verifyMocks();   
	}

	/**
	 * 2. This testcase checks if the validate method returns false if the user tries to set a password which is already used.
	 */
	public void testPasswordHistoryValidation_Failure() {
		expect(csmUserRepository.userHasPassword(userName, password)).andReturn(false).anyTimes();
		expect(csmUserRepository.userHadPassword(userName, password)).andReturn(true).anyTimes();
		replayMocks();
		assertFalse(passwordCreationPolicyValidator.validate(passwordPolicy, credential, new ValidationErrors()));
		verifyMocks();   
	}
	
	public void testAllPasswordFormatValidations() {
		replayMocks();
		assertFalse(tryThisPassword("password"));
		verifyMocks(); 
	}
	
	public boolean tryThisPassword(String thisPassword) {
	//	Credential c
		return passwordCreationPolicyValidator.validate(passwordPolicy, credential, new ValidationErrors());
	}
	
} 
