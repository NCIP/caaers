package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.rules.RulesTestCase;

import com.semanticbits.rules.impl.RuleDeploymentServiceImpl;

public class RuleDeploymentServiceTest extends RulesTestCase {
    RuleDeploymentServiceImpl deploymentService;

    @Override
    public Class<? extends RulesTestCase> getTestClass() {
        return RuleDeploymentServiceTest.class;
    }

    protected void setUp() throws Exception {
        super.setUp();
        deploymentService = (RuleDeploymentServiceImpl)getDeployedApplicationContext().getBean("ruleDeploymentService");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }



    public void testRegisterRuleXml() throws Exception {
        String xml = getFileContext("test_rule.xml");
        String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting.description_section";
        
        try {
        	deploymentService.deregisterRuleSet(bindUri);
        } catch (Exception ex) {
            System.out.println("This is first time registration");
        }
        deploymentService.registerRuleXml(bindUri, xml);

    }

}
