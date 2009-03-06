package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.CombinationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordCreationPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;

public class PasswordPolicyDaoTest extends DaoNoSecurityTestCase<PasswordPolicyDao>{
	
	public void testGetById() {
		PasswordPolicy passwordPolicy = getDao().getById(-6);
		assertNotNull("PasswordPolicy not found", passwordPolicy);
        assertEquals("MaxPasswordAge is wrong ", 7776000, passwordPolicy.getLoginPolicy().getMaxPasswordAge());
        assertEquals("LockOutDuration is wrong ", 86400, passwordPolicy.getLoginPolicy().getLockOutDuration());
        assertEquals("AllowedFailedLoginAttempts is wrong ", 5, passwordPolicy.getLoginPolicy().getAllowedFailedLoginAttempts());
        assertEquals("MinPasswordAge is wrong ", 3600, passwordPolicy.getPasswordCreationPolicy().getMinPasswordAge());
        assertEquals("MinPasswordLength is wrong ", 7, passwordPolicy.getPasswordCreationPolicy().getMinPasswordLength());
        assertEquals("PasswordHistorySize is wrong ", 3, passwordPolicy.getPasswordCreationPolicy().getPasswordHistorySize());

	}
	
	public void testSave() {
		Integer savedId;
		{
			PasswordPolicy passwordPolicy = createPasswordPolicy();
			getDao().save(passwordPolicy);
			savedId = passwordPolicy.getId();
			
		}
		
		interruptSession();
		
		{
			PasswordPolicy passwordPolicyReloaded = getDao().getById(savedId);
			assertNotNull("PasswordPolicy not found", passwordPolicyReloaded);
	        assertEquals("MaxPasswordAge is wrong ", 86400, passwordPolicyReloaded.getLoginPolicy().getMaxPasswordAge());
	        assertEquals("LockOutDuration is wrong ", 86400, passwordPolicyReloaded.getLoginPolicy().getLockOutDuration());
	        assertEquals("AllowedFailedLoginAttempts is wrong ", 1, passwordPolicyReloaded.getLoginPolicy().getAllowedFailedLoginAttempts());
	        assertEquals("MinPasswordAge is wrong ", 1, passwordPolicyReloaded.getPasswordCreationPolicy().getMinPasswordAge());
	        assertEquals("MinPasswordLength is wrong ", 6, passwordPolicyReloaded.getPasswordCreationPolicy().getMinPasswordLength());
	        assertEquals("PasswordHistorySize is wrong ", 1, passwordPolicyReloaded.getPasswordCreationPolicy().getPasswordHistorySize());
			
		}
		
	}

	   public static PasswordPolicy createPasswordPolicy() {
	        LoginPolicy loginPolicy = new LoginPolicy();
	        loginPolicy.setAllowedFailedLoginAttempts(1);
	        loginPolicy.setLockOutDuration(86400);
	        loginPolicy.setMaxPasswordAge(60 * 60 * 24);

	        CombinationPolicy combinationPolicy = new CombinationPolicy();
	        combinationPolicy.setMinimumRequired(5);
	        combinationPolicy.setUpperCaseAlphabetRequired(true);
	        combinationPolicy.setLowerCaseAlphabetRequired(true);
	        combinationPolicy.setNonAlphaNumericRequired(true);
	        combinationPolicy.setBaseTenDigitRequired(true);
	        combinationPolicy.setMaxSubstringLength(3);

	        PasswordCreationPolicy passwordCreationPolicy = new PasswordCreationPolicy();
	        passwordCreationPolicy.setCombinationPolicy(combinationPolicy);
	        passwordCreationPolicy.setMinPasswordAge(1);
	        passwordCreationPolicy.setPasswordHistorySize(1);
	        passwordCreationPolicy.setMinPasswordLength(6);

	        PasswordPolicy passwordPolicy = new PasswordPolicy();
	        passwordPolicy.setLoginPolicy(loginPolicy);
	        passwordPolicy.setPasswordCreationPolicy(passwordCreationPolicy);
	        return passwordPolicy;
	    }
}
