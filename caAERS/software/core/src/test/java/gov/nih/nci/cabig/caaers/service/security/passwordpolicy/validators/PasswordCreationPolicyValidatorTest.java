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
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;

import java.sql.Timestamp;
import java.util.Date;

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
		private int age;    
	
	@Override
	protected void setUp() throws Exception {        
		super.setUp();        
		userName = "xyz";        
		password = "Abcdef1!";        
		age = 5;                
		
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
	}  
	
	public void testValidate() {    
		passwordPolicy.setPasswordCreationPolicy(passwordCreationPolicy);
		passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
		passwordCreationPolicy.setMinPasswordAge(age);
		expect(csmUserRepository.userHasPassword(userName, password)).andReturn(false).anyTimes();
		expect(csmUserRepository.userHadPassword(userName, password)).andReturn(false).anyTimes();
		expect(csmUserRepository.getUserByName(userName)).andReturn(user).anyTimes();
		
		replayMocks();      
		 		
		assertTrue(passwordCreationPolicyValidator.validate(passwordPolicy,credential));   
		verifyMocks();   
	}
} 
