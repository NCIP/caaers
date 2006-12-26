package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Padmaja Vedula
 * @author Rhett Sutphin
 * @author Sujith Vellat Thayyilthodi
 */
public class LoginCommand {
    private static final Log log = LogFactory.getLog(LoginCommand.class);
    private String username;
    private String password;

    private AuthenticationManager authenticationManager;

    
    public boolean login(String ipAddress) {
        // check login credentials using Authentication Manager of CSM
        boolean loginSuccess = false;
        try {
            loginSuccess = authenticationManager.login(getUsername(), getPassword());
        } catch (CSException ex) {
            log.debug("Authentication failed for " + getUsername() + " with exception", ex);
        }
        if (loginSuccess) {
            log.debug("Login successful for " + getUsername());
        } else {
            log.debug("Log in failed for " + getUsername());
        }
        return loginSuccess;
    }

    //
    // BOUND PROPERTIES
    //
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
   
}