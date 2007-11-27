package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.Duration;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Jared Flatow
 */
@Entity
@Table(name="caaers_user")
public class CaaersUser {

    private String _name;
    private String _salt;
    private String _token;
    private Timestamp _tokenTime;
    private Timestamp _passwordLastSet;
    private String _passwordHistory;
    private int _numFailedLogins;

    public static CaaersUser getUser(String userName) throws CaaersSystemException {
	// throw exception if doesn't exist
	return null;
    }

    public String getName() {
	return _name;
    }

    public void setName(String name) {
	_name = name;
    }

    public String getToken() {
	return _token;
    }

    public void setToken(String token) {
	_token = token;
    }

    public Timestamp getTokenTime() {
	return _tokenTime;
    }

    public void setTokenTime(Timestamp tokenTime) {
	_tokenTime = tokenTime;
    }

    public void setPassword(String hashedPassword) {
	// csm_user
    }

    public void addPasswordToHistory(int maxHistorySize) {
	// expand password history
	// if >= length -> chop to length - 1
	// add password
    }

    public boolean isPassword(String hashedPassword) {
	// compare to csm_user
	return false;
    }

    public Duration getPasswordAge() {
	// current time - last set converted to duration
	return null;
    }

    public int getFailedLoginAttempts() {
	return _numFailedLogins;
    }

    public void setFailedLoginAttempts(int numFailedLogins) {
	_numFailedLogins = numFailedLogins;
    }

    public String getSalt() {
	return _salt;
    }

    public void setSalt(String salt) {
	_salt = salt;
    }
}
