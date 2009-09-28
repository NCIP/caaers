package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.authentication.LockoutManager;

import org.springframework.beans.factory.FactoryBean;

public class CaaersCSMLockoutManagerFactoryBean implements FactoryBean{
    
    private String lockoutTime = "0";
    private String allowedLoginTime = "0";
    private String allowedAttempts = "0";
    
    public Object getObject() throws Exception {
        LockoutManager.initialize("0", "0", "0");
        return LockoutManager.getInstance();
    }

    public Class getObjectType() {
        return LockoutManager.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public String getLockoutTime() {
        return lockoutTime;
    }

    public void setLockoutTime(String lockoutTime) {
        this.lockoutTime = lockoutTime;
    }

    public String getAllowedLoginTime() {
        return allowedLoginTime;
    }

    public void setAllowedLoginTime(String allowedLoginTime) {
        this.allowedLoginTime = allowedLoginTime;
    }

    public String getAllowedAttempts() {
        return allowedAttempts;
    }

    public void setAllowedAttempts(String allowedAttempts) {
        this.allowedAttempts = allowedAttempts;
    }
    
}
