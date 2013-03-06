/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import javax.persistence.Embeddable;

 
/**
 * The Class CombinationPolicy.
 */
@Embeddable
public class CombinationPolicy {

    /** The minimum required. */
    private int minimumRequired;

    /** The upper case alphabet required. */
    private boolean upperCaseAlphabetRequired;

    /** The lower case alphabet required. */
    private boolean lowerCaseAlphabetRequired;

    /** The non alpha numeric required. */
    private boolean nonAlphaNumericRequired;

    /** The base ten digit required. */
    private boolean baseTenDigitRequired;

    /** The max substring length. */
    private int maxSubstringLength;

    /**
     * Gets the minimum required.
     *
     * @return the minimum required
     */
    public int getMinimumRequired() {
        return minimumRequired;
    }

    /**
     * Sets the minimum required.
     *
     * @param minimumRequired the new minimum required
     */
    public void setMinimumRequired(int minimumRequired) {
        this.minimumRequired = minimumRequired;
    }

    /**
     * Checks if is upper case alphabet required.
     *
     * @return true, if is upper case alphabet required
     */
    public boolean isUpperCaseAlphabetRequired() {
        return upperCaseAlphabetRequired;
    }

    /**
     * Sets the upper case alphabet required.
     *
     * @param upperCaseAlphabetRequired the new upper case alphabet required
     */
    public void setUpperCaseAlphabetRequired(boolean upperCaseAlphabetRequired) {
        this.upperCaseAlphabetRequired = upperCaseAlphabetRequired;
    }

    /**
     * Checks if is lower case alphabet required.
     *
     * @return true, if is lower case alphabet required
     */
    public boolean isLowerCaseAlphabetRequired() {
        return lowerCaseAlphabetRequired;
    }

    /**
     * Sets the lower case alphabet required.
     *
     * @param lowerCaseAlphabetRequired the new lower case alphabet required
     */
    public void setLowerCaseAlphabetRequired(boolean lowerCaseAlphabetRequired) {
        this.lowerCaseAlphabetRequired = lowerCaseAlphabetRequired;
    }

    /**
     * Checks if is non alpha numeric required.
     *
     * @return true, if is non alpha numeric required
     */
    public boolean isNonAlphaNumericRequired() {
        return nonAlphaNumericRequired;
    }

    /**
     * Sets the non alpha numeric required.
     *
     * @param nonAlphaNumericRequired the new non alpha numeric required
     */
    public void setNonAlphaNumericRequired(boolean nonAlphaNumericRequired) {
        this.nonAlphaNumericRequired = nonAlphaNumericRequired;
    }

    /**
     * Checks if is base ten digit required.
     *
     * @return true, if is base ten digit required
     */
    public boolean isBaseTenDigitRequired() {
        return baseTenDigitRequired;
    }

    /**
     * Sets the base ten digit required.
     *
     * @param baseTenDigitRequired the new base ten digit required
     */
    public void setBaseTenDigitRequired(boolean baseTenDigitRequired) {
        this.baseTenDigitRequired = baseTenDigitRequired;
    }

    /**
     * Gets the max substring length.
     *
     * @return the max substring length
     */
    public int getMaxSubstringLength() {
        return maxSubstringLength;
    }

    /**
     * Sets the max substring length.
     *
     * @param maxSubstringLength the new max substring length
     */
    public void setMaxSubstringLength(int maxSubstringLength) {
        this.maxSubstringLength = maxSubstringLength;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "The password must:\n"
                        + (upperCaseAlphabetRequired ? "Contain at least one uppercase letter.\n"
                                        : "")
                        + (lowerCaseAlphabetRequired ? "Contain at least one lowercase letter.\n"
                                        : "")
                        + (nonAlphaNumericRequired ? "Contain at least one non-alphanumeric character.\n"
                                        : "")
                        + (baseTenDigitRequired ? "Contain at least one base ten digit.\n" : "")
                        + "The password may not contain a substring longer than "
                        + maxSubstringLength + " characters from the username.\n";
    }
}
