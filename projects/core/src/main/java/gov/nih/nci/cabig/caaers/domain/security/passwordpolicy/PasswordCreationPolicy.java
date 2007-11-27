package gov.nih.nci.cabig.caaers.domain.security.passwordpolicy;

import javax.persistence.Embeddable;

@Embeddable
public class PasswordCreationPolicy {
    private int minPasswordAge;
    private int passwordHistorySize;
    private int minPasswordLength;
    private CombinationPolicy combinationPolicy;
	
    public int getMinPasswordAge() {
	return minPasswordAge;
    }
    
    public void setMinPasswordAge(int minPasswordAge) {
	this.minPasswordAge = minPasswordAge;
    }
    
    public int getPasswordHistorySize() {
	return passwordHistorySize;
    }
    
    public void setPasswordHistorySize(int passwordHistorySize) {
	this.passwordHistorySize = passwordHistorySize;
    }
    
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
}
