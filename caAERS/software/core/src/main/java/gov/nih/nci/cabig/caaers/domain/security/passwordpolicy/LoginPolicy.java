package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.validation.annotation.NumInRange;

import javax.persistence.Embeddable;

 
/**
 * The Class LoginPolicy.
 *
 * @author Ram Seethiraju
 */
@Embeddable
public class LoginPolicy {

    /** The allowed failed login attempts. */
    private int allowedFailedLoginAttempts;
    
    /** The lock out duration. */
    private int lockOutDuration;

    /** The max password age. */
    private int maxPasswordAge;

    /** The allowed login time. */
    private int allowedLoginTime;
    
	/**
	 * Gets the allowed login time.
	 *
	 * @return the allowed login time
	 */
	public int getAllowedLoginTime() {
		return allowedLoginTime;
	}

	/**
	 * Sets the allowed login time.
	 *
	 * @param allowedLoginTime the new allowed login time
	 */
	public void setAllowedLoginTime(int allowedLoginTime) {
		this.allowedLoginTime = allowedLoginTime;
	}

	/**
	 * Gets the allowed failed login attempts.
	 *
	 * @return the allowed failed login attempts
	 */
	@NumInRange(min = 0)
    public int getAllowedFailedLoginAttempts() {
        return allowedFailedLoginAttempts;
    }

	/**
	 * Sets the allowed failed login attempts.
	 *
	 * @param allowedFailedLoginAttempts the new allowed failed login attempts
	 */
	public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
        this.allowedFailedLoginAttempts = allowedFailedLoginAttempts;
    }

    /**
     * Gets the lock out duration.
     *
     * @return the lock out duration
     */
    @NumInRange(min = 0)
    public int getLockOutDuration() {
        return lockOutDuration;
    }

    /**
     * Sets the lock out duration.
     *
     * @param lockOutDuration the new lock out duration
     */
    public void setLockOutDuration(int lockOutDuration) {
        this.lockOutDuration = lockOutDuration;
    }

    /* hard-coded to min at a week for now */
    /**
     * Gets the max password age.
     *
     * @return the max password age
     */
    @NumInRange(min = 604800)
    public int getMaxPasswordAge() {
        return maxPasswordAge;
    }

    /**
     * Sets the max password age.
     *
     * @param maxPasswordAge the new max password age
     */
    public void setMaxPasswordAge(int maxPasswordAge) {
        this.maxPasswordAge = maxPasswordAge;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Maximum password age is " + maxPasswordAge + " (seconds).\n"
                        + "Number of allowed login attempts is " + allowedFailedLoginAttempts
                        + ".\n" + "The lockout duration is " + lockOutDuration + " (seconds).\n";
    }
}
