package gov.nih.nci.cabig.caaers.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.IndexColumn;

/**
 * This class represents the User domain object associated with the Adverse event report.
 *
 * @author Saurabh Agrawal
 */
@MappedSuperclass
public abstract class User extends Person implements Comparable<User>{
	
	protected String loginId;

	protected List<UserGroupType> userGroupTypes;

	protected String emailAddress;

	protected String phoneNumber;

	protected String faxNumber;

	protected String salt;

	protected String token;

	protected Timestamp tokenTime;

	protected Timestamp passwordLastSet;

	protected int numFailedLogins;

	protected List<String> passwordHistory;
	
    public User() {
        userGroupTypes = new ArrayList<UserGroupType>();
        passwordHistory = new ArrayList<String>();
    }
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFaxNumber(final String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /* begin password stuff */

    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void resetToken() {
        this.tokenTime = new Timestamp(0);
    }

    @Column(name = "token_time")
    public Timestamp getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Timestamp tokenTime) {
        this.tokenTime = tokenTime;
    }

    @Column(name = "password_last_set")
    public Timestamp getPasswordLastSet() {
        return passwordLastSet == null ? new Timestamp(0) : passwordLastSet;
    }

    public void setPasswordLastSet(Timestamp passwordLastSet) {
        this.passwordLastSet = passwordLastSet;
    }

    @Transient
    public long getPasswordAge() {
        return new Date().getTime() - getPasswordLastSet().getTime();
    }

    @CollectionOfElements
    @JoinTable(name = "password_history", joinColumns = @JoinColumn(name = "user_id"))
    @IndexColumn(name = "list_index")
    @Column(name = "password")
    public List<String> getPasswordHistory() {
        return passwordHistory;
    }

    public void setPasswordHistory(List<String> passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    public void addPasswordToHistory(String password, int maxHistorySize) {
        passwordHistory.add(password);
        while (passwordHistory.size() > maxHistorySize && maxHistorySize > 0)
            passwordHistory.remove(0);
    }

    @Column(name = "num_failed_logins")
    public int getFailedLoginAttempts() {
        return numFailedLogins;
    }

    public void setFailedLoginAttempts(int numFailedLogins) {
        this.numFailedLogins = numFailedLogins;
    }
    
    
    /* end password stuff */

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (emailAddress != null ? !emailAddress.equals(user.emailAddress)
                : user.emailAddress != null) return false;
        if (faxNumber != null ? !faxNumber.equals(user.faxNumber) : user.faxNumber != null) return false;
        if (loginId != null ? !loginId.equals(user.loginId) : user.loginId != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (loginId != null ? loginId.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (faxNumber != null ? faxNumber.hashCode() : 0);
        return result;
    }

    @Transient
    public List<UserGroupType> getUserGroupTypes() {
        return userGroupTypes;
    }

    public void setUserGroupTypes(List<UserGroupType> userGroupTypes) {
        // this.userGroupTypes = new HashSet<UserGroupType>();
        this.userGroupTypes = userGroupTypes;
    }

    public void addUserGroupType(UserGroupType userGroupType) {
        if (!userGroupTypes.contains(userGroupType)) {
            userGroupTypes.add(userGroupType);
        }
    }

    public void removeUserGroupType(UserGroupType userGroupType) {
        this.userGroupTypes.remove(userGroupType);
    }

    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = getFirstName() != null;
        if (getLastName() != null) {
            name.append(getLastName());
            if (hasFirstName) {
                name.append(", ");
            }
        }
        if (hasFirstName) {
            name.append(getFirstName());
        }
        return name.toString();
    }

    @Transient
    public String getFullName() {
        StringBuilder name = new StringBuilder();
        boolean hasLastName = getLastName() != null;
        if (getFirstName() != null) {
            name.append(getFirstName());
            if (hasLastName) {
                name.append(' ');
            }
        }
        if (hasLastName) {
            name.append(getLastName());
        }
        return name.toString();
    }
    
    public int compareTo(User user){
    	return getFullName().compareTo(user.getFullName());
    }
	
}
