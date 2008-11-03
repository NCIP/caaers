package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.rules.RulesTestCase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.core.io.ClassPathResource;

public class XMLUtilTest extends RulesTestCase {
    @Override
    public Class<? extends RulesTestCase> getTestClass() {
        return XMLUtilTest.class;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUnmarshal() throws Exception {
        String xml = getCommonFileContext("test_rule2.xml");
        System.out.println(xml);
        Object p = XMLUtil.unmarshal(xml);
        assertNotNull("Object cannot be null", p);
    	assertTrue(true);

    }

    public void testMarshal() throws Exception {
        String xml = getCommonFileContext("test_rule2.xml");
        Object p = XMLUtil.unmarshal(xml);
        assertNotNull("Object cannot be null", p);
        String xml2 = XMLUtil.marshal(p);
        assertTrue("completed", true);
    	assertTrue(true);
    }

    public void testUnmarshalToPackage() throws Exception {
        String xml = getCommonFileContext("test_rule.xml");
        org.drools.rule.Package p = XMLUtil.unmarshalToPackage(xml);
        assertNotNull("Package cannot be null", p);
    }
    
    private String getCommonFileContext(String fileName) throws Exception {
    	
        File testFile = new ClassPathResource("/gov/nih/nci/cabig/caaers/rules/common/" + fileName).getFile();
        BufferedReader ds = new BufferedReader(new FileReader(testFile));
        String line = null;
        StringBuffer xml = new StringBuffer();
        while ((line = ds.readLine()) != null) {
            xml.append(line);
        }
        assertTrue("Content of the xml should not be null", xml.toString().length() > 0);
        return xml.toString();
    }

}
