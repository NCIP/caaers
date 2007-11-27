package gov.nih.nci.cabig.caaers.user;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * @author Jared Flatow
 */
@Entity
@Table(name="caaers_user")
public class CaaersUser {

    private String name;
    private String salt;
    private String token;
    private Timestamp tokenTime;
    private Timestamp passwordLastSet;
    private String passwordHistory;
    private int numFailedLogins;

    public static CaaersUser getUser(String userName) throws CaaersSystemException {
	// throw exception if doesn't exist
	return null;
    }

    @Column(name="name")
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column(name="salt")
    public String getSalt() {
	return salt;
    }

    public void setSalt(String salt) {
	this.salt = salt;
    }

    @Column(name="token")
    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    @Column(name="token_time")
    public Timestamp getTokenTime() {
	return tokenTime;
    }

    public void setTokenTime(Timestamp tokenTime) {
	this.tokenTime = tokenTime;
    }

    public void setPassword(String hashedPassword) {
	// csm_user
    }

    public boolean isPassword(String hashedPassword) {
	// compare to csm_user
	return false;
    }

    public void addPasswordToHistory(int maxHistorySize) {
	// expand password history
	// if >= length -> chop to length - 1
	// add password
    }

    @Transient
    public long getPasswordAge() {
	// current time - last set
	return 0;
    }

    @Column(name="num_failed_logins")
    public int getFailedLoginAttempts() {
	return numFailedLogins;
    }

    public void setFailedLoginAttempts(int numFailedLogins) {
	this.numFailedLogins = numFailedLogins;
    }
}
