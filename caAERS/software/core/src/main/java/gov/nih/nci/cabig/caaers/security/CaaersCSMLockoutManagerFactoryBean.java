package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.authentication.LockoutManager;

import org.springframework.beans.factory.FactoryBean;

public class CaaersCSMLockoutManagerFactoryBean implements FactoryBean{
    
    private String lockoutTime = "60000";
    private String allowedLoginTime = "60000";
    private String allowedAttempts = "3";
    
    public Object getObject() throws Exception {
        LockoutManager.initialize("60000", "60000", "3");
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
