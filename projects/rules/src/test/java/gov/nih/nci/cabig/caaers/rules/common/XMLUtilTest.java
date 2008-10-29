package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.rules.RulesTestCase;
import junit.framework.TestCase;

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
        String xml = getFileContext("test_rule2.xml");
        Object p = XMLUtil.unmarshal(xml);
        assertNotNull("Object cannot be null", p);
    	assertTrue(true);

    }

    public void testMarshal() throws Exception {
        String xml = getFileContext("test_rule2.xml");
        Object p = XMLUtil.unmarshal(xml);
        assertNotNull("Object cannot be null", p);
        String xml2 = XMLUtil.marshal(p);
        assertTrue("completed", true);
    	assertTrue(true);
    }

    public void testUnmarshalToPackage() throws Exception {
        String xml = getFileContext("test_rule.xml");
        org.drools.rule.Package p = XMLUtil.unmarshalToPackage(xml);
        assertNotNull("Package cannot be null", p);
    }

}
