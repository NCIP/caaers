package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import javax.persistence.Embeddable;

@Embeddable
public class LoginPolicy {
    
    private int allowedFailedLoginAttempts;
    private int lockOutDuration;
    private int maxPasswordAge;
    
    public int getAllowedFailedLoginAttempts() {
	return allowedFailedLoginAttempts;
    }
    
    public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
	this.allowedFailedLoginAttempts = allowedFailedLoginAttempts;
    }

    public int getLockOutDuration() {
	return lockOutDuration;
    }
    
    public void setLockOutDuration(int lockOutDuration) {
	this.lockOutDuration = lockOutDuration;
    }
    
    public int getMaxPasswordAge() {
	return maxPasswordAge;
    }
    
    public void setMaxPasswordAge(int maxPasswordAge) {
	this.maxPasswordAge = maxPasswordAge;
    }
}
