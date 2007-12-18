package gov.nih.nci.cabig.caaers.rules.common;

import gov.nih.nci.cabig.caaers.rules.RulesTestCase;
import junit.framework.TestCase;

public class XMLUtilTest extends RulesTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testUnmarshal() {
		fail("Not yet implemented");
	}

	public void testMarshal() {
		fail("Not yet implemented");
	}

	public void testUnmarshalToPackage() throws Exception{
		String xml = getFileContext("test_rule.xml");
		org.drools.rule.Package p = XMLUtil.unmarshalToPackage(xml);
		assertNotNull("Package cannot be null", p);
	}

}
