/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.cabig.caaers.domain.repository.UserRepository;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceImpl implements PasswordManagerService {
	
    private PasswordPolicyService passwordPolicyService;
    private UserRepository userRepository;

    public _User requestToken(String userName) throws CaaersSystemException {
    	_User user = userRepository.getUserByLoginName(userName);
    	if(user == null){
    		throw new CaaersNoSuchUserException("User with login Id :" + userName + " unknown");
    	}
        //get the token
    	user.generateNewToken();
    	userRepository.save(user);
    	return user;
    }

    public void setPassword(String userName, String password, String token)
            throws CaaersSystemException {
    	_User user = userRepository.getUserByLoginName(userName);
    	
    	if(user == null){
    		throw new CaaersNoSuchUserException("User with login Id :" + userName + " unknown");
    	}
        validateToken(user, token);
        validateAndSetPassword(user, password);
    }

    private boolean validateToken(_User user, String token) throws CaaersSystemException {
        if (user.getTokenTime().after(
                new Timestamp(new Date().getTime()
                        - passwordPolicyService.getPasswordPolicy()
                        .getTokenTimeout())) 
                &&  StringUtils.equals(user.getToken(), token)) return true;
        throw new CaaersSystemException("Invalid token.");
    }

    private boolean validateAndSetPassword(_User user, String password)
            throws CaaersSystemException {
        passwordPolicyService.validatePasswordAgainstCreationPolicy(new Credential(user.getLoginName(),
                password));
        userRepository.userChangePassword(user, password, passwordPolicyService
                .getPasswordPolicy().getPasswordCreationPolicy().getPasswordHistorySize());
        return true;
    }

    @Required
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
        this.passwordPolicyService = passwordPolicyService;
    }

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
