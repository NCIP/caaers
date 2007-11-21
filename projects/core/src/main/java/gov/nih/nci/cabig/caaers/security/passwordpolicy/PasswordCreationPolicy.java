package gov.nih.nci.cabig.caaers.security.passwordpolicy;

public class PasswordCreationPolicy {

	
	private Duration minimumAge;
	private int previousPasswordCount;
	private int minimumLength;
	private String hashingAlgorithm;
	private CombinationPolicy combinationPolicy;
	
	public PasswordCreationPolicy(){

	}


	

	
	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize()
	  throws Throwable {

	}





	public Duration getMinimumAge() {
		return minimumAge;
	}





	public void setMinimumAge(Duration minimumAge) {
		this.minimumAge = minimumAge;
	}





	public int getPreviousPasswordCount() {
		return previousPasswordCount;
	}





	public void setPreviousPasswordCount(int previousPasswordCount) {
		this.previousPasswordCount = previousPasswordCount;
	}





	public int getMinimumLength() {
		return minimumLength;
	}





	public void setMinimumLength(int minimumLength) {
		this.minimumLength = minimumLength;
	}





	public String getHashingAlgorithm() {
		return hashingAlgorithm;
	}





	public void setHashingAlgorithm(String hashingAlgorithm) {
		this.hashingAlgorithm = hashingAlgorithm;
	}





	public CombinationPolicy getCombinationPolicy() {
		return combinationPolicy;
	}





	public void setCombinationPolicy(CombinationPolicy combinationPolicy) {
		this.combinationPolicy = combinationPolicy;
	}

}
