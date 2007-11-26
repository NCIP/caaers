package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class CombinationPolicy {

    private int _minimumRequired;
    private boolean _upperCaseAlphabetRequired;
    private boolean _lowerCaseAlphabetRequired;
    private boolean _nonAlphaNumericRequired;
    private boolean _baseTenDigitRequired;
    private int _maxSubstringLength;
    
    public int getMinimumRequired() {
	return _minimumRequired;
    }
    
    public void setMinimumRequired(int minimumRequired) {
	_minimumRequired = minimumRequired;
    }
    
    public boolean isUpperCaseAlphabetRequired() {
	return _upperCaseAlphabetRequired;
    }
    
    public void setUpperCaseAlphabetRequired(boolean upperCaseAlphabetRequired) {
	_upperCaseAlphabetRequired = upperCaseAlphabetRequired;
    }
    
    public boolean isLowerCaseAlphabetRequired() {
	return _lowerCaseAlphabetRequired;
    }

    public void setLowerCaseAlphabetRequired(boolean lowerCaseAlphabetRequired) {
	_lowerCaseAlphabetRequired = lowerCaseAlphabetRequired;
    }
    
    public boolean isNonAlphaNumericRequired() {
	return _nonAlphaNumericRequired;
    }
    
    public void setNonAlphaNumericRequired(boolean nonAlphaNumericRequired) {
	_nonAlphaNumericRequired = nonAlphaNumericRequired;
    }

    public boolean isBaseTenDigitRequired() {
	return _baseTenDigitRequired;
    }

    public void setBaseTenDigitRequired(boolean baseTenDigitRequired) {
	_baseTenDigitRequired = baseTenDigitRequired;
    }

    public int getMaxSubstringLength() {
	return _maxSubstringLength;
    }

    public void setMaxSubstringLength(int maxSubstringLength) {
	_maxSubstringLength = maxSubstringLength;
    }    
}
