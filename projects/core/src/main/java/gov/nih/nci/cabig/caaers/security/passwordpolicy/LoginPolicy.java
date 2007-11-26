package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class LoginPolicy {
    
    private int _allowedFailedLoginAttempts;
    private Duration _lockOutDuration;
    private Duration _maxPasswordAge;
    
    public int getAllowedFailedLoginAttempts() {
	return _allowedFailedLoginAttempts;
    }
    
    public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
	_allowedFailedLoginAttempts = allowedFailedLoginAttempts;
    }

    public Duration getLockOutDuration() {
	return _lockOutDuration;
    }
    
    public void setLockOutDuration(Duration lockOutDuration) {
	_lockOutDuration = lockOutDuration;
    }
    
    public Duration getMaxPasswordAge() {
	return _maxPasswordAge;
    }
    
    public void setMaxPasswordAge(Duration maxPasswordAge) {
	_maxPasswordAge = maxPasswordAge;
    }
}
