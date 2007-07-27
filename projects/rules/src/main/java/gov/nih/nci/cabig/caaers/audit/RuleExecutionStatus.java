package gov.nih.nci.cabig.caaers.audit;

public class RuleExecutionStatus {
	
	private String ruleName;
	private boolean conditionMet;
	private boolean fired;
	
	public RuleExecutionStatus(){
		
	}
	public boolean isConditionMet() {
		return conditionMet;
	}
	public void setConditionMet(boolean conditionMet) {
		this.conditionMet = conditionMet;
	}
	public boolean isFired() {
		return fired;
	}
	public void setFired(boolean fired) {
		this.fired = fired;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}
