package gov.nih.nci.cabig.caaers.security.passwordpolicy;

import javax.persistence.Embeddable;

@Embeddable
public class LoginPolicy {
    
    private int allowedFailedLoginAttempts;
    private long lockOutDuration;
    private long maxPasswordAge;
    
    public int getAllowedFailedLoginAttempts() {
	return allowedFailedLoginAttempts;
    }
    
    public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
	this.allowedFailedLoginAttempts = allowedFailedLoginAttempts;
    }

    public long getLockOutDuration() {
	return lockOutDuration;
    }
    
    public void setLockOutDuration(long lockOutDuration) {
	this.lockOutDuration = lockOutDuration;
    }
    
    public long getMaxPasswordAge() {
	return maxPasswordAge;
    }
    
    public void setMaxPasswordAge(long maxPasswordAge) {
	this.maxPasswordAge = maxPasswordAge;
    }
}
