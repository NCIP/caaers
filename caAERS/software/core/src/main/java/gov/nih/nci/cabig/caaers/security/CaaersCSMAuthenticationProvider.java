package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.LoginPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordTooOldException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.TooManyAllowedFailedLoginAttemptsException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.UserLockedOutException;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.security.acegi.csm.authentication.CSMAuthenticationProvider;

import java.util.Date;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ram Seethiraju
 */
public class CaaersCSMAuthenticationProvider extends CSMAuthenticationProvider{
	
	private static final Log logger = LogFactory.getLog(CaaersCSMAuthenticationProvider.class);	
	private UserDao userDao;
	private PasswordPolicyService passwordPolicyService; 
	
	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}

	public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
		this.passwordPolicyService = passwordPolicyService;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails user, UsernamePasswordAuthenticationToken token) 
	throws AccountExpiredException{

		User caaersUser = null;
		Credential credential =  new Credential(user.getUsername(), user.getPassword());
		PasswordPolicy passwordPolicy = passwordPolicyService.getPasswordPolicy();
		LoginPolicyValidator loginPolicyValidator = new LoginPolicyValidator();		
		
		logger.debug((new StringBuilder()).append("Authenticating ").append(user.getUsername()).append("...").toString());
		if(!user.isAccountNonExpired()){
			throw new AccountExpiredException((new StringBuilder()).append("Error authenticating: User is InActive").toString());
		}		
		
		caaersUser = userDao.getByLoginId(user.getUsername());
		
		try {
			if(caaersUser!=null) {				
				credential.setUser(caaersUser);
				loginPolicyValidator.validate(passwordPolicy, credential);
			}
			
			super.additionalAuthenticationChecks(user, token);
			
			if(caaersUser != null){
				caaersUser.setFailedLoginAttempts(0);
				caaersUser.setLastLoginAttemptTime(null);
			}
			
		} catch (AuthenticationException authEx) {
			if(caaersUser!=null) {
				caaersUser.setFailedLoginAttempts(caaersUser.getFailedLoginAttempts()+1);
			}
			throw authEx;
		} catch (TooManyAllowedFailedLoginAttemptsException attemptsEx) {
			caaersUser.setLastLoginAttemptTime(new Date());
			caaersUser.setFailedLoginAttempts(0);
			throw attemptsEx;
		} catch (UserLockedOutException lockEx) {
			throw lockEx;
		}catch (PasswordTooOldException oldEx) {
			caaersUser.setFailedLoginAttempts(0);
			caaersUser.setLastLoginAttemptTime(null);
			throw oldEx;
		} finally {
			if(caaersUser!=null) {
			userDao.save(caaersUser);
			}
		}
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
