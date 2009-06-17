package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ConfigPropertyTypeTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetCode() {
		assertEquals(new Integer(1), ConfigPropertyType.REPORT_TYPE.getCode());
	}

	public void testGetDisplayName() {
		assertEquals("Report Definition Type", ConfigPropertyType.REPORT_TYPE.getDisplayName());
	}

}
