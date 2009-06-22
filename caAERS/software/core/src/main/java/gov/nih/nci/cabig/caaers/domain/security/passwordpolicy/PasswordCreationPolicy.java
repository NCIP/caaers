package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.validation.annotation.NumInRange;

import javax.persistence.Embeddable;

@Embeddable
public class PasswordCreationPolicy {
    private int minPasswordAge;

    private int passwordHistorySize;

    private int minPasswordLength;

    private CombinationPolicy combinationPolicy;

    /* hard-coded to max at a week for now */
    @NumInRange(min = 0, max = 604799)
    public int getMinPasswordAge() {
        return minPasswordAge;
    }

    public void setMinPasswordAge(int minPasswordAge) {
        this.minPasswordAge = minPasswordAge;
    }

    @NumInRange(min = 0)
    public int getPasswordHistorySize() {
        return passwordHistorySize;
    }

    public void setPasswordHistorySize(int passwordHistorySize) {
        this.passwordHistorySize = passwordHistorySize;
    }

    @NumInRange(min = 5)
    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public CombinationPolicy getCombinationPolicy() {
        return combinationPolicy;
    }

    public void setCombinationPolicy(CombinationPolicy combinationPolicy) {
        this.combinationPolicy = combinationPolicy;
    }

    public String toString() {
        return "The password must be at least " + minPasswordAge
                        + " (seconds) old before it can be changed.\n"
                        + "This password must not be the same as one of the last "
                        + passwordHistorySize + " passwords.\n" + "The password must be at least "
                        + minPasswordLength + " characters long.\n" + combinationPolicy.toString();
    }
}
