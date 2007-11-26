package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class PasswordCreationPolicy {
    private Duration _minPasswordAge;
    private int _passwordHistorySize;
    private int _minPasswordLength;
    private CombinationPolicy _combinationPolicy;
	
    public Duration getMinPasswordAge() {
	return _minPasswordAge;
    }
    
    public void setMinPasswordAge(Duration minPasswordAge) {
	_minPasswordAge = minPasswordAge;
    }
    
    public int getPasswordHistorySize() {
	return _passwordHistorySize;
    }
    
    public void setPasswordHistorySize(int passwordHistorySize) {
	_passwordHistorySize = passwordHistorySize;
    }
    
    public int getMinPasswordLength() {
	return _minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
	_minPasswordLength = minPasswordLength;
    }

    public CombinationPolicy getCombinationPolicy() {
	return _combinationPolicy;
    }
    
    public void setCombinationPolicy(CombinationPolicy combinationPolicy) {
	_combinationPolicy = combinationPolicy;
    }
}
