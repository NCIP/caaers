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
	
	protected String loginName;
	protected String salt;
	protected String token;
	protected Timestamp tokenTime;
	protected Timestamp passwordLastSet;
	protected int numFailedLogins;
	protected List<String> passwordHistory;
	protected Date lastLoginAttemptTime;

    protected Map<UserGroupType, RoleMembership> roleMembershipMap;

	protected gov.nih.nci.security.authorization.domainobjects.User csmUser;
	
    public _User() {
        this(new gov.nih.nci.security.authorization.domainobjects.User());
    }

    public _User(gov.nih.nci.security.authorization.domainobjects.User csmUser){
       passwordHistory = new ArrayList<String>();
       roleMembershipMap = new HashMap<UserGroupType, RoleMembership>();
       setCsmUser(csmUser);
    }

    @Transient
    public boolean isActive(){
        for(RoleMembership rm : roleMembershipMap.values()){
            if(rm.isActive()) return true;
        }
        return false;
    }

    public RoleMembership findRoleMembership(UserGroupType role){
        RoleMembership roleMembership = roleMembershipMap.get(role);
        if(roleMembership == null) {
            roleMembership = new RoleMembership(role);
            roleMembershipMap.put(role, roleMembership);
        }
        return roleMembership;
    }

    public void removeRoleMembership(UserGroupType role){
        roleMembershipMap.remove(role);
    }
    
    @Transient
    public void unlock() {
    	setFailedLoginAttempts(0);
    	setLastFailedLoginAttemptTime(null);
    }
    
    @Transient
    public boolean isLocked() {
    	if(getFailedLoginAttempts()==-1 && getLastFailedLoginAttemptTime()!=null)	return true;
    	return false;
    }
    
    @Transient
    public long getPasswordAge() {
    	long age = (new Date().getTime() - getPasswordLastSet().getTime())/1000;    
        return age;
    }
    
    public void addPasswordToHistory(String password, int maxHistorySize) {
        passwordHistory.add(password);
        while (passwordHistory.size() > maxHistorySize && maxHistorySize > 0)
            passwordHistory.remove(0);
    }    

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
	 * This property is used in determining the account lock out
	 * @return seconds past last failed login attempts
	 */
	@Transient
	public long getSecondsPastLastFailedLoginAttempt(){
    	if(getLastFailedLoginAttemptTime()==null) return -1;
    	return (new Date().getTime()-getLastFailedLoginAttemptTime().getTime())/1000;
    }
	
	
	public String encryptString(String string){
    	try{
    		return new StringEncrypter().encrypt(string);
    	}catch (StringEncrypter.EncryptionException enX) {
    		throw new CaaersSystemException("Could not encrypt string",enX);
    	}
    }
    
    public boolean userHasPassword(String password) {
        return encryptString((StringUtils.isEmpty(getSalt()) ? "" : getSalt() ) + password).equals(getCsmUser().getPassword());
    }

    public boolean userHadPassword(String password) {
        return getPasswordHistory().contains(DigestUtils.shaHex(password));
    }
    
    public void generateNewToken(){
    	setTokenTime(new Timestamp(new Date().getTime()));
    	setToken(encryptString((StringUtils.isEmpty(getSalt()) ? "" : getSalt()) + getTokenTime().toString() + "random_string").replaceAll("\\W", "Q"));
    }
    
	@Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
        getCsmUser().setLoginName(loginName);
    }
    
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

    @Column(name = "num_failed_logins")
    public int getFailedLoginAttempts() {
        return numFailedLogins;
    }

    public void setFailedLoginAttempts(int numFailedLogins) {
        this.numFailedLogins = numFailedLogins;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "last_login")
	public Date getLastFailedLoginAttemptTime() {
		return lastLoginAttemptTime;
	}

	public void setLastFailedLoginAttemptTime(Date lastLoginAttemptTime) {
		this.lastLoginAttemptTime = lastLoginAttemptTime;
	}

    @Transient
    public List<UserGroupType> getUserGroupTypes() {
        ArrayList<UserGroupType> userGroupTypeList = new ArrayList<UserGroupType>();
        if(getRoleMembershipMap().isEmpty()) return userGroupTypeList;
        userGroupTypeList.addAll(getRoleMembershipMap().keySet());

        return userGroupTypeList;
    }

    @Transient
	public gov.nih.nci.security.authorization.domainobjects.User getCsmUser() {
		return csmUser;
	}

	public void setCsmUser(
			gov.nih.nci.security.authorization.domainobjects.User csmUser) {
		this.csmUser = csmUser;
        setLoginName(csmUser.getLoginName());
	}

    @Transient
    public Map<UserGroupType, RoleMembership> getRoleMembershipMap() {
        return roleMembershipMap;
    }

    public void setRoleMembershipMap(Map<UserGroupType, RoleMembership> roleMembershipMap) {
        this.roleMembershipMap = roleMembershipMap;
    }


    //=== methods that are delegated to CSM User


    @Transient
    public String getFirstName() {
        return getCsmUser().getFirstName();
    }

    public void setFirstName(String firstName) {
        getCsmUser().setFirstName(firstName);
    }

    @Transient
    public String getLastName() {
        return getCsmUser().getLastName();
    }

    public void setLastName(String lastName) {
        getCsmUser().setLastName(lastName);
    }

    @Transient
    public String getMiddleName() {
        return null;
    }

    public void setMiddleName(String middleName) {
       //
    }

    @Transient
	public String getTitle() {
		return getCsmUser().getTitle();
	}

	public void setTitle(String title) {
		getCsmUser().setTitle(title);
	}

    @Transient
	public String getEmailAddress() {
		return getCsmUser().getEmailId();
	}

	public void setEmailAddress(String emailAddress) {
		getCsmUser().setEmailId(emailAddress);
	}

    @Transient
	public String getPhoneNumber() {
		return getCsmUser().getPhoneNumber();
	}

	public void setPhoneNumber(String phoneNumber) {
		getCsmUser().setPhoneNumber(phoneNumber);
	}

    @Transient
	public String getFaxNumber() {
		return null;
	}

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

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		return result;
	}

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
