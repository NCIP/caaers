package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import junit.framework.TestCase;

public class RuleDeploymentServiceTest extends TestCase {

	private RuleDeploymentService ruleDeploymentService;

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleDeploymentService = new RuleDeploymentServiceImpl();
	}
	
	public void testRegisterRuleSet() {
		String bindUri = "URI_1";
		String ruleSetName = "gov.nih.nci.cabig.caaers.rules";
		//ruleSetName = "default";
		ruleDeploymentService.registerRuleSet(bindUri, ruleSetName);
	}
	
	public void testDeregisterRuleSet() {
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		ruleDeploymentService.deregisterRuleSet(bindUri);
	}
	
	public void redeployRule() {
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		String ruleSetName = "gov.nih.nci.cabig.caaers.rules";
		ruleDeploymentService.deregisterRuleSet(bindUri);		
		ruleDeploymentService.registerRuleSet(bindUri, ruleSetName);
	}
	
	public void testlistRuleSets() {
		
	}

}