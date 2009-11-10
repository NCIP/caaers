package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.validation.annotation.NumInRange;

import javax.persistence.Embeddable;

/**
 * @author Ram Seethiraju
 */
@Embeddable
public class LoginPolicy {

    private int allowedFailedLoginAttempts;
    
    private int lockOutDuration;

    private int maxPasswordAge;

    private int allowedLoginTime;
    
	public int getAllowedLoginTime() {
		return allowedLoginTime;
	}

	public void setAllowedLoginTime(int allowedLoginTime) {
		this.allowedLoginTime = allowedLoginTime;
	}

	@NumInRange(min = 0)
    public int getAllowedFailedLoginAttempts() {
        return allowedFailedLoginAttempts;
    }

	public void setAllowedFailedLoginAttempts(int allowedFailedLoginAttempts) {
        this.allowedFailedLoginAttempts = allowedFailedLoginAttempts;
    }

    @NumInRange(min = 0)
    public int getLockOutDuration() {
        return lockOutDuration;
    }

    public void setLockOutDuration(int lockOutDuration) {
        this.lockOutDuration = lockOutDuration;
    }

    /* hard-coded to min at a week for now */
    @NumInRange(min = 604800)
    public int getMaxPasswordAge() {
        return maxPasswordAge;
    }

    public void setMaxPasswordAge(int maxPasswordAge) {
        this.maxPasswordAge = maxPasswordAge;
    }

    public String toString() {
        return "Maximum password age is " + maxPasswordAge + " (seconds).\n"
                        + "Number of allowed login attempts is " + allowedFailedLoginAttempts
                        + ".\n" + "The lockout duration is " + lockOutDuration + " (seconds).\n";
    }
}
