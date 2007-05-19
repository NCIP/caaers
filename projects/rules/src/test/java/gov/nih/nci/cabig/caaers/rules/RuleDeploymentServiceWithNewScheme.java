package gov.nih.nci.cabig.caaers.rules;


import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentService;
import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceImpl;
import junit.framework.TestCase;

public class RuleDeploymentServiceWithNewScheme extends TestCase {

	private RuleDeploymentService ruleDeploymentService;

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleDeploymentService = new RuleDeploymentServiceImpl();
	}
	/**
	 * 
	 * @throws Exception
	 * Typically the binduri will be driven from number of factors
	 * Also the package name of the rule set will be driven from 
	 * number of factors
	 * These will be transparent to the users but for or test cases
	 * we will the rule set created for Asses AE Rules
	 */
	public void testRegisterRuleSet() throws Exception{
		
		String bindUri = "gov.nih.nci.cabig.caaers.rules.OUR_DREAM_SPONSOR.Asses_AE_Rule";
		String ruleSetName = "gov.nih.nci.cabig.caaers.rules.OUR_DREAM_SPONSOR.Asses_AE_Rule";
		//ruleSetName = "default";
		ruleDeploymentService.registerRuleSet(bindUri, ruleSetName);
	}
}
