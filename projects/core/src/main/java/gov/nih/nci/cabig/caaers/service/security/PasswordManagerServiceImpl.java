package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.service.UserService;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;

import java.util.Date;
import java.sql.Timestamp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceImpl implements PasswordManagerService {

    private PasswordPolicyService passwordPolicyService;
    private UserService userService;

    public String requestToken(String userName) throws CaaersSystemException {
	return userService.userCreateToken(userName);
    }

    public void setPassword(String userName, String password, String token) throws CaaersSystemException {
	validateToken(userName, token);
	validateAndSetPassword(userName, password);
    }
    
    private boolean validateToken(String userName, String token) throws CaaersSystemException {
	User user = userService.getUserByName(userName);
	if (user.getTokenTime().after(new Timestamp(new Date().getTime() - passwordPolicyService.getPasswordPolicy().getTokenTimeout())) 
	    && token.equals(user.getToken())) return true;
	throw new CaaersSystemException("Invalid token.");	
    }

    private boolean validateAndSetPassword(String userName, String password) throws CaaersSystemException {
	passwordPolicyService.validatePasswordAgainstCreationPolicy(userName, password);
	userService.userChangePassword(userName, password,
				       passwordPolicyService.getPasswordPolicy().getPasswordCreationPolicy().getPasswordHistorySize());
	return true;
    }

    @Required
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
	this.passwordPolicyService = passwordPolicyService;
    }

    @Required
    public void setUserService(UserService userService) {
	this.userService = userService;
    }
}

