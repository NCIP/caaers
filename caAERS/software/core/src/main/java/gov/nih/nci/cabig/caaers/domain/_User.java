package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.security.util.StringEncrypter;

import java.sql.Timestamp;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.codec.digest.DigestUtils;
import org.drools.util.StringUtils;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;


 
/**
 * This class represents the User Object.
 * @author Monish
 */
@Entity
@Table(name = "caaers_users")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_caaers_users_id") })
public class _User extends AbstractMutableDomainObject{
	
	/** The login name. */
	protected String loginName;
	
	/** The salt. */
	protected String salt;
	
	/** The token. */
	protected String token;
	
	/** The token time. */
	protected Timestamp tokenTime;
	
	/** The password last set. */
	protected Timestamp passwordLastSet;
	
	/** The num failed logins. */
	protected int numFailedLogins;
	
	/** The password history. */
	protected List<String> passwordHistory;
	
	/** The last login attempt time. */
	protected Date lastLoginAttemptTime;

    /** The role membership map. */
    protected Map<UserGroupType, RoleMembership> roleMembershipMap;

	/** The csm user. */
	protected gov.nih.nci.security.authorization.domainobjects.User csmUser;
	
    /**
     * Instantiates a new _ user.
     */
    public _User() {
        this(new gov.nih.nci.security.authorization.domainobjects.User());
    }

    /**
     * Instantiates a new _ user.
     *
     * @param csmUser the csm user
     */
    public _User(gov.nih.nci.security.authorization.domainobjects.User csmUser){
       passwordHistory = new ArrayList<String>();
       roleMembershipMap = new HashMap<UserGroupType, RoleMembership>();
       setCsmUser(csmUser);
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive(){
        for(RoleMembership rm : roleMembershipMap.values()){
            if(rm.isActive()) return true;
        }
        return false;
    }

    /**
     * Find role membership.
     *
     * @param role the role
     * @return the role membership
     */
    public RoleMembership findRoleMembership(UserGroupType role){
        RoleMembership roleMembership = roleMembershipMap.get(role);
        if(roleMembership == null) {
            roleMembership = new RoleMembership(role);
            roleMembershipMap.put(role, roleMembership);
        }
        return roleMembership;
    }

    /**
     * Removes the role membership.
     *
     * @param role the role
     */
    public void removeRoleMembership(UserGroupType role){
        roleMembershipMap.remove(role);
    }
    
    /**
     * Unlock.
     */
    @Transient
    public void unlock() {
    	setFailedLoginAttempts(0);
    	setLastFailedLoginAttemptTime(null);
    }
    
    /**
     * Checks if is locked.
     *
     * @return true, if is locked
     */
    @Transient
    public boolean isLocked() {
    	if(getFailedLoginAttempts()==-1 && getLastFailedLoginAttemptTime()!=null)	return true;
    	return false;
    }
    
    /**
     * Gets the password age.
     *
     * @return the password age
     */
    @Transient
    public long getPasswordAge() {
    	long age = (new Date().getTime() - getPasswordLastSet().getTime())/1000;    
        return age;
    }
    
    /**
     * Adds the password to history.
     *
     * @param password the password
     * @param maxHistorySize the max history size
     */
    public void addPasswordToHistory(String password, int maxHistorySize) {
        passwordHistory.add(password);
        while (passwordHistory.size() > maxHistorySize && maxHistorySize > 0)
            passwordHistory.remove(0);
    }    

    /**
     * Gets the last first.
     *
     * @return the last first
     */
    @Transient
    public String getLastFirst() {
        StringBuilder name = new StringBuilder();
        boolean hasFirstName = csmUser.getFirstName() != null;
        if (csmUser.getLastName() != null) {
            name.append(csmUser.getLastName());
            if (hasFirstName) {
                name.append(", ");
            }
        }
        if (hasFirstName) {
            name.append(csmUser.getFirstName());
        }
        return name.toString();
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public String getFullName() {
        StringBuilder name = new StringBuilder();
        boolean hasLastName = csmUser.getLastName() != null;
        if (csmUser.getFirstName() != null) {
            name.append(csmUser.getFirstName());
            if (hasLastName) {
                name.append(' ');
            }
        }
        if (hasLastName) {
            name.append(csmUser.getLastName());
        }
        return name.toString();
    }    
    
	/**
	 * Calculates the time past last failed login attempt
	 * This property is used in determining the account lock out.
	 *
	 * @return seconds past last failed login attempts
	 */
	@Transient
	public long getSecondsPastLastFailedLoginAttempt(){
    	if(getLastFailedLoginAttemptTime()==null) return -1;
    	return (new Date().getTime()-getLastFailedLoginAttemptTime().getTime())/1000;
    }
	
	
	/**
	 * Encrypt string.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String encryptString(String string){
    	try{
    		return new StringEncrypter().encrypt(string);
    	}catch (StringEncrypter.EncryptionException enX) {
    		throw new CaaersSystemException("Could not encrypt string",enX);
    	}
    }
    
    /**
     * User has password.
     *
     * @param password the password
     * @return true, if successful
     */
    public boolean userHasPassword(String password) {
        return encryptString((StringUtils.isEmpty(getSalt()) ? "" : getSalt() ) + password).equals(getCsmUser().getPassword());
    }

    /**
     * User had password.
     *
     * @param password the password
     * @return true, if successful
     */
    public boolean userHadPassword(String password) {
        return getPasswordHistory().contains(DigestUtils.shaHex(password));
    }
    
    /**
     * Generate new token.
     */
    public void generateNewToken(){
    	setTokenTime(new Timestamp(new Date().getTime()));
    	setToken(encryptString((StringUtils.isEmpty(getSalt()) ? "" : getSalt()) + getTokenTime().toString() + "random_string").replaceAll("\\W", "Q"));
    }
    
	/**
	 * Gets the login name.
	 *
	 * @return the login name
	 */
	@Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the login name.
     *
     * @param loginName the new login name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
        getCsmUser().setLoginName(loginName);
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
     * Reset token.
     */
    public void resetToken() {
        this.tokenTime = new Timestamp(0);
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
    public Timestamp getPasswordLastSet() {
        return passwordLastSet == null ? new Timestamp(0) : passwordLastSet;
    }

    /**
     * Sets the password last set.
     *
     * @param passwordLastSet the new password last set
     */
    public void setPasswordLastSet(Timestamp passwordLastSet) {
        this.passwordLastSet = passwordLastSet;
    }

    /**
     * Gets the password history.
     *
     * @return the password history
     */
    @CollectionOfElements
    @JoinTable(name = "password_history", joinColumns = @JoinColumn(name = "user_id"))
    @IndexColumn(name = "list_index")
    @Column(name = "password")
    public List<String> getPasswordHistory() {
        return passwordHistory;
    }

    /**
     * Sets the password history.
     *
     * @param passwordHistory the new password history
     */
    public void setPasswordHistory(List<String> passwordHistory) {
        this.passwordHistory = passwordHistory;
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
     * Gets the last failed login attempt time.
     *
     * @return the last failed login attempt time
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_login")
	public Date getLastFailedLoginAttemptTime() {
		return lastLoginAttemptTime;
	}

	/**
	 * Sets the last failed login attempt time.
	 *
	 * @param lastLoginAttemptTime the new last failed login attempt time
	 */
	public void setLastFailedLoginAttemptTime(Date lastLoginAttemptTime) {
		this.lastLoginAttemptTime = lastLoginAttemptTime;
	}

    /**
     * Gets the user group types.
     *
     * @return the user group types
     */
    @Transient
    public List<UserGroupType> getUserGroupTypes() {
        ArrayList<UserGroupType> userGroupTypeList = new ArrayList<UserGroupType>();
        if(getRoleMembershipMap().isEmpty()) return userGroupTypeList;
        userGroupTypeList.addAll(getRoleMembershipMap().keySet());

        return userGroupTypeList;
    }

    /**
     * Gets the csm user.
     *
     * @return the csm user
     */
    @Transient
	public gov.nih.nci.security.authorization.domainobjects.User getCsmUser() {
		return csmUser;
	}

	/**
	 * Sets the csm user.
	 *
	 * @param csmUser the new csm user
	 */
	public void setCsmUser(
			gov.nih.nci.security.authorization.domainobjects.User csmUser) {
		this.csmUser = csmUser;
        setLoginName(csmUser.getLoginName());
	}

    /**
     * Gets the role membership map.
     *
     * @return the role membership map
     */
    @Transient
    public Map<UserGroupType, RoleMembership> getRoleMembershipMap() {
        return roleMembershipMap;
    }

    /**
     * Sets the role membership map.
     *
     * @param roleMembershipMap the role membership map
     */
    public void setRoleMembershipMap(Map<UserGroupType, RoleMembership> roleMembershipMap) {
        this.roleMembershipMap = roleMembershipMap;
    }


    //=== methods that are delegated to CSM User


    /**
     * Gets the first name.
     *
     * @return the first name
     */
    @Transient
    public String getFirstName() {
        return getCsmUser().getFirstName();
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        getCsmUser().setFirstName(firstName);
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    @Transient
    public String getLastName() {
        return getCsmUser().getLastName();
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        getCsmUser().setLastName(lastName);
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name
     */
    @Transient
    public String getMiddleName() {
        return null;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the new middle name
     */
    public void setMiddleName(String middleName) {
       //
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    @Transient
	public String getTitle() {
		return getCsmUser().getTitle();
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		getCsmUser().setTitle(title);
	}

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    @Transient
	public String getEmailAddress() {
		return getCsmUser().getEmailId();
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		getCsmUser().setEmailId(emailAddress);
	}

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    @Transient
	public String getPhoneNumber() {
		return getCsmUser().getPhoneNumber();
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		getCsmUser().setPhoneNumber(phoneNumber);
	}

    /**
     * Gets the fax number.
     *
     * @return the fax number
     */
    @Transient
	public String getFaxNumber() {
		return null;
	}

	/**
	 * Sets the fax number.
	 *
	 * @param faxNumber the new fax number
	 */
	public void setFaxNumber(String faxNumber) {
		
	}

    //==

    /**
     * Will tell whether this user has the specific role.
     * @param role - A UserGroupType to check
     * @return  true, if the user is playing the role 
     */
    public boolean hasRole(UserGroupType role){
        return roleMembershipMap.containsKey(role);
    }
    /**
     * Will copy the User details from the supplied User. If the input User is null, all the
     * role memberships of this user will be removed. 
     * @param u - User from which details to be copied. 
     */
    public void sync(_User u){
        getRoleMembershipMap().clear(); //clear so that if the incoming user is NULL, this user atleast have no roles.
        if(u == null) return;
        
        setFirstName(u.getFirstName());
        setMiddleName(u.getMiddleName());
        setLastName(u.getLastName());
        if(u.getRoleMembershipMap() != null) getRoleMembershipMap().putAll(u.getRoleMembershipMap());
        
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString())
           .append("\n, loginName : ").append(getLoginName())
           .append("\n, CsmUser [").append(getCsmUser().getUserId()).append("],")
           .append("\n RoleMemberships {");
        for(RoleMembership roleMembership :roleMembershipMap.values()){
            sb.append(roleMembership.toString()).append(",\n");
        }
        sb.append(" }");
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if(!(obj instanceof _User)) return false;
		_User other = (_User) obj;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		return true;
	}
}
