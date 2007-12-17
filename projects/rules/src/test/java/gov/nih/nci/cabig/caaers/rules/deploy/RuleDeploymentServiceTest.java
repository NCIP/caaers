package gov.nih.nci.cabig.caaers.rules.deploy;

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

public class RuleDeploymentServiceTest extends TestCase {
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
		deploymentService.registerRuleXml("CTEP_EGx_RULE", xml);
		
		String xml2 = getFileContext("test_rule2.xml");
		deploymentService.registerRuleXml("CTEP_EG2_RULE", xml2);
	}
	
	public String getFileContext(String fileName) throws Exception{
		File testFile = new ClassPathResource(fileName,RuleDeploymentServiceTest.class).getFile();
		BufferedReader ds = new BufferedReader(new FileReader(testFile));
		String line = null;
		StringBuffer xml = new StringBuffer();
		while((line = ds.readLine()) != null){
			xml.append(line);
		}
		assertTrue("Content of the xml should not be null", xml.toString().length() > 0);
		return xml.toString();
	}
}
