/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.validation.annotation.NumInRange;

import javax.persistence.Embeddable;

 
/**
 * The Class PasswordCreationPolicy.
 */
@Embeddable
public class PasswordCreationPolicy {
    
    /** The min password age. */
    private int minPasswordAge;

    /** The password history size. */
    private int passwordHistorySize;

    /** The min password length. */
    private int minPasswordLength;

    /** The combination policy. */
    private CombinationPolicy combinationPolicy;

    /* hard-coded to max at a week for now */
    /**
     * Gets the min password age.
     *
     * @return the min password age
     */
    @NumInRange(min = 0, max = 604799)
    public int getMinPasswordAge() {
        return minPasswordAge;
    }

    /**
     * Sets the min password age.
     *
     * @param minPasswordAge the new min password age
     */
    public void setMinPasswordAge(int minPasswordAge) {
        this.minPasswordAge = minPasswordAge;
    }

    /**
     * Gets the password history size.
     *
     * @return the password history size
     */
    @NumInRange(min = 0)
    public int getPasswordHistorySize() {
        return passwordHistorySize;
    }

    /**
     * Sets the password history size.
     *
     * @param passwordHistorySize the new password history size
     */
    public void setPasswordHistorySize(int passwordHistorySize) {
        this.passwordHistorySize = passwordHistorySize;
    }

    /**
     * Gets the min password length.
     *
     * @return the min password length
     */
    @NumInRange(min = 5)
    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    /**
     * Sets the min password length.
     *
     * @param minPasswordLength the new min password length
     */
    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    /**
     * Gets the combination policy.
     *
     * @return the combination policy
     */
    public CombinationPolicy getCombinationPolicy() {
        return combinationPolicy;
    }

    /**
     * Sets the combination policy.
     *
     * @param combinationPolicy the new combination policy
     */
    public void setCombinationPolicy(CombinationPolicy combinationPolicy) {
        this.combinationPolicy = combinationPolicy;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "The password must be at least " + minPasswordAge
                        + " (seconds) old before it can be changed.\n"
                        + "This password must not be the same as one of the last "
                        + passwordHistorySize + " passwords.\n" + "The password must be at least "
                        + minPasswordLength + " characters long.\n" + combinationPolicy.toString();
    }
}
