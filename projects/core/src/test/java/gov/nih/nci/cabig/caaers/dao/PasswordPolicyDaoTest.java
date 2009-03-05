package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
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
	
	 

}
