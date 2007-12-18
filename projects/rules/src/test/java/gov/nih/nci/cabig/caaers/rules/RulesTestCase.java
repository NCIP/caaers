package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.deploy.RuleDeploymentServiceTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class RulesTestCase extends TestCase{
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
