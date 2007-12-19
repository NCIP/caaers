package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.rules.RulesTestCase;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;

import org.apache.axis.utils.ClasspathUtils;
import org.apache.axis.utils.XMLUtils;
import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class RuleDeploymentServiceTest extends RulesTestCase {
	RuleDeploymentServiceImpl deploymentService;
	protected void setUp() throws Exception {
		super.setUp();
		deploymentService = new RuleDeploymentServiceImpl();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLogin() {
		fail("Not yet implemented");
	}

	public void testConfigureRepository() {
		fail("Not yet implemented");
	}

	public void testRegisterRuleSet() {
		fail("Not yet implemented");
	}

	public void testDeregisterRuleSet() {
		fail("Not yet implemented");
	}

	public void testListRegistrations() {
		fail("Not yet implemented");
	}
	
	public void testRegisterRuleXml() throws Exception{
		String xml = getFileContext("test_rule.xml");
		String bindUri = "gov.nih.nci.cabig.caaers.rules.reporting.description_section";
		deploymentService.registerRuleXml(bindUri, xml);
		
	}
	
	
}
