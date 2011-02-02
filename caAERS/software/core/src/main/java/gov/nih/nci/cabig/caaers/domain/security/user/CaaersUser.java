package gov.nih.nci.cabig.caaers.domain.security.user;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * The Class CaaersUser.
 *
 * @author Jared Flatow
 */
@Entity
@Table(name = "caaers_user")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_caaers_user_id") })
public class CaaersUser extends AbstractMutableDomainObject {

    /** The name. */
    private String name;

    /** The salt. */
    private String salt;

    /** The token. */
    private String token;

    /** The token time. */
    private Timestamp tokenTime;

    /** The password last set. */
    private Timestamp passwordLastSet;

    /** The password history. */
    private String passwordHistory;

    /** The num failed logins. */
    private int numFailedLogins;

    /**
     * Gets the user.
     *
     * @param userName the user name
     * @return the user
     * @throws CaaersSystemException the caaers system exception
     */
    public static CaaersUser getUser(String userName) throws CaaersSystemException {
        // throw exception if doesn't exist
        return null;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the salt.
     *
     * @return the salt
     */
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     *
     * @param salt the new salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the token time.
     *
     * @return the token time
     */
    @Column(name = "token_time")
    public Timestamp getTokenTime() {
        return tokenTime;
    }

    /**
     * Sets the token time.
     *
     * @param tokenTime the new token time
     */
    public void setTokenTime(Timestamp tokenTime) {
        this.tokenTime = tokenTime;
    }

    /**
     * Gets the password last set.
     *
     * @return the password last set
     */
    @Column(name = "password_last_set")
    private Timestamp getPasswordLastSet() {
        return passwordLastSet;
    }

    /**
     * Sets the password last set.
     *
     * @param passwordLastSet the new password last set
     */
    private void setPasswordLastSet(Timestamp passwordLastSet) {
        this.passwordLastSet = passwordLastSet;
    }

    /**
     * Gets the password age.
     *
     * @return the password age
     */
    @Transient
    public long getPasswordAge() {
        // current time - last set
        return new Date().getTime() - getPasswordLastSet().getTime();
    }

    /**
     * Gets the password history.
     *
     * @return the password history
     */
    @Column(name = "password_history")
    private String getPasswordHistory() {
        return passwordHistory;
    }

    /**
     * Sets the password history.
     *
     * @param passwordHistory the new password history
     */
    private void setPasswordHistory(String passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    /**
     * Adds the password to history.
     *
     * @param maxHistorySize the max history size
     */
    public void addPasswordToHistory(int maxHistorySize) {
        // expand password history
        // if >= length -> chop to length - 1
        // add password
    }

    /**
     * Gets the failed login attempts.
     *
     * @return the failed login attempts
     */
    @Column(name = "num_failed_logins")
    public int getFailedLoginAttempts() {
        return numFailedLogins;
    }

    /**
     * Sets the failed login attempts.
     *
     * @param numFailedLogins the new failed login attempts
     */
    public void setFailedLoginAttempts(int numFailedLogins) {
        this.numFailedLogins = numFailedLogins;
    }

    /**
     * Sets the password.
     *
     * @param hashedPassword the new password
     */
    public void setPassword(String hashedPassword) {
        // csm_user
        this.setPasswordLastSet(new Timestamp(new Date().getTime()));
    }

    /**
     * Checks if is password.
     *
     * @param hashedPassword the hashed password
     * @return true, if is password
     */
    public boolean isPassword(String hashedPassword) {
        // compare to csm_user
        return false;
    }
}
