package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class CombinationPolicy {

	private int minimumRequired;
	private boolean upperCaseAlphabetRequired;
	private boolean lowerCaseAlphabetRequired;
	private boolean nonAlphaNumericRequired;
	private boolean baseTenDigitRequired;
	private int subStringExclusionLength;

	public CombinationPolicy(){

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}

	public int getMinimumRequired() {
		return minimumRequired;
	}

	public void setMinimumRequired(int minimumRequired) {
		this.minimumRequired = minimumRequired;
	}

	public boolean isUpperCaseAlphabetRequired() {
		return upperCaseAlphabetRequired;
	}

	public void setUpperCaseAlphabetRequired(boolean upperCaseAlphabetRequired) {
		this.upperCaseAlphabetRequired = upperCaseAlphabetRequired;
	}

	public boolean isLowerCaseAlphabetRequired() {
		return lowerCaseAlphabetRequired;
	}

	public void setLowerCaseAlphabetRequired(boolean lowerCaseAlphabetRequired) {
		this.lowerCaseAlphabetRequired = lowerCaseAlphabetRequired;
	}

	public boolean isNonAlphaNumericRequired() {
		return nonAlphaNumericRequired;
	}

	public void setNonAlphaNumericRequired(boolean nonAlphaNumericRequired) {
		this.nonAlphaNumericRequired = nonAlphaNumericRequired;
	}

	public boolean isBaseTenDigitRequired() {
		return baseTenDigitRequired;
	}

	public void setBaseTenDigitRequired(boolean baseTenDigitRequired) {
		this.baseTenDigitRequired = baseTenDigitRequired;
	}

	public int getSubStringExclusionLength() {
		return subStringExclusionLength;
	}

	public void setSubStringExclusionLength(int subStringExclusionLength) {
		this.subStringExclusionLength = subStringExclusionLength;
	}

}
