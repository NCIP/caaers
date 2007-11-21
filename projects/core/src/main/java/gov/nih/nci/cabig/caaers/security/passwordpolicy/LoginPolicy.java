package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class LoginPolicy {

	private int allowedFailedLoginAttempts;
	private Duration lockOutDuration;
	private Duration maximumAge;
	

	public LoginPolicy(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	public int getAllowedFailedLoginAttempts() {
		return allowedFailedLoginAttempts;
	}

	public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
		this.allowedFailedLoginAttempts = allowedFailedLoginAttempts;
	}

	public Duration getLockOutDuration() {
		return lockOutDuration;
	}

	public void setLockOutDuration(Duration lockOutDuration) {
		this.lockOutDuration = lockOutDuration;
	}

	public Duration getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(Duration maximumAge) {
		this.maximumAge = maximumAge;
	}

}
