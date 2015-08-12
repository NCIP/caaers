/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.LoginPolicy;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.LoginPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.acegi.csm.authentication.CSMAuthenticationProvider;
import gov.nih.nci.cabig.ctms.audit.DataAuditInfo;
import org.acegisecurity.*;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Ram Seethiraju
 */
public class CaaersCSMAuthenticationProvider extends CSMAuthenticationProvider {
	
	private static final Log logger = LogFactory.getLog(CaaersCSMAuthenticationProvider.class);	
	private PasswordPolicyService passwordPolicyService;
	private UserRepository userRepository;
	
	public PasswordPolicyService getPasswordPolicyService() {
		return passwordPolicyService;
	}

	public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
		this.passwordPolicyService = passwordPolicyService;
	}

	/**
	 * This method will do Login policy validations and authentication checks.
	 */
	@Override
    @Transactional
	protected void additionalAuthenticationChecks(UserDetails user, UsernamePasswordAuthenticationToken token) throws AuthenticationException {

        int previousFailedLogins = 0;
        int newFailedLogins = 0;
        Date previousFailedDate = null;
        Date newFailedDate = null;



		User caaersUser = null;
		Credential credential =  new Credential(user.getUsername(), user.getPassword());
        credential.setUserDetails(user);
		PasswordPolicy passwordPolicy = passwordPolicyService.getPasswordPolicy();
		LoginPolicyValidator loginPolicyValidator = new LoginPolicyValidator();

        // CAAERS-7171, preventing oops by setting the data. (BJ - original code of DW)
        populateDataAuditInfoIfNeeded();

        if(logger.isDebugEnabled()) {
            logger.debug((new StringBuilder()).append("Authenticating ").append(user.getUsername()).append("...").toString());
        }

        try {

                //load the user
                caaersUser = userRepository.getUserByLoginName(user.getUsername());


                //check the login policy
                if(caaersUser != null) {
                    previousFailedLogins = caaersUser.getFailedLoginAttempts();
                    previousFailedDate = caaersUser.getLastFailedLoginAttemptTime();

                    credential.setUser(caaersUser);
                    loginPolicyValidator.validate(passwordPolicy, credential, null);

                }
		} catch (DisabledException attemptsEx) {
			// This exception is thrown when too many failed login attempts occur.
            newFailedDate = new Date();
            newFailedLogins = LoginPolicy.MAX_LOGIN_ATTEMPTS_ALLOWED;
			throw attemptsEx;
		} catch (LockedException lockEx) {
			// This exception is thrown when user tries to login while the account is locked.
			throw lockEx;
		}catch (CredentialsExpiredException oldEx) {
			// This exception is thrown when the password is too old.
            newFailedLogins = 0;
            newFailedDate = null;
			throw oldEx;
		} catch (AuthenticationException authEx) {
			// This exception is thrown when invalid credentials are used to login.
            newFailedLogins = previousFailedLogins + 1;
            newFailedDate = new Date();
			throw new BadCredentialsException("Invalid login credentials");
		} finally {
			// save the caAERS user properties.
			if(caaersUser != null) {
                if( (newFailedLogins != previousFailedLogins) || DateUtils.compateDateAndTime(previousFailedDate, newFailedDate) != 0 ) {
                    try {
                        caaersUser.setFailedLoginAttempts(newFailedLogins);
                        caaersUser.setLastFailedLoginAttemptTime(newFailedDate);
                        userRepository.save(caaersUser);
                    }catch (StaleObjectStateException  | ConcurrencyFailureException ignore) {
                        if(logger.isDebugEnabled()) {
                            logger.debug("Error while saving user, generally this can be ignored", ignore);
                        }
                    }
                }

			}
		}
	}

    private void populateDataAuditInfoIfNeeded() {

        //check for oops
        if(DataAuditInfo.getLocal() == null) {
            logger.warn("DataAuditInfor is not set! This is not supposed to happen, emergency setting it to prevent oops.");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null) {
                DataAuditInfo.setLocal(new gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo(auth.getName(), "UNKNOWN", new Date()));
            }
        }

    }

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
