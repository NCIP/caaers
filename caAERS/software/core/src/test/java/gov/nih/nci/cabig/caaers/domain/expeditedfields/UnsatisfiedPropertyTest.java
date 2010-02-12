package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import junit.framework.TestCase;

/**
 * 
 * @author Biju Joseph
 *
 */
public class UnsatisfiedPropertyTest extends CaaersTestCase {
	
	ExpeditedReportTree tree;
	protected void setUp() throws Exception {
		super.setUp();
		tree = (ExpeditedReportTree)getDeployedApplicationContext().getBean("expeditedReportTree");
	}

	public void testGetDisplayName() {
		TreeNode node = tree.find("reporter.contactMechanisms[phone]");
		UnsatisfiedProperty property = new UnsatisfiedProperty(node, "test");
		assertEquals("Reporter details~Phone", property.getDisplayName());
		
		node = tree.find("reporter.address.zip");
		property = new UnsatisfiedProperty(node, "");
		assertEquals("Reporter details~Address~Zip", property.getDisplayName());
	}

}
