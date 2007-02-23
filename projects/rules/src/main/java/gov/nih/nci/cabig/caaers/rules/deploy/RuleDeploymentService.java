package gov.nih.nci.cabig.caaers.rules.deploy;

public interface RuleDeploymentService {

	public abstract void registerRuleSet(String bindUri, String ruleSetName);
	
	public abstract void deregisterRuleSet(String bindUri);

}