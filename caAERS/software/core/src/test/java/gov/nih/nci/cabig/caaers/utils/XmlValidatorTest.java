package gov.nih.nci.cabig.caaers.utils;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class XmlValidatorTest extends TestCase {

	public void testValidateAgainstSchema() throws Exception {
		
		File xmlFile = null;
		xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/api/testdata/CreateResearchStaffTest.xml")[0].getFile();
		String xmlContent = FileUtils.readFileToString(xmlFile);
		StringBuffer sb = new StringBuffer();
		XmlValidator.validateAgainstSchema(xmlContent, "classpath*:schema/integration/ResearchStaff.xsd", sb);
		System.out.println(sb.toString());
	}
	
	private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    }

}
