package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDeliveryTest extends TestCase {

	ReportDelivery rd;
	ReportDelivery rdSystem;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		rd = rdd.createReportDelivery();
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		rdSystem = rdd.createReportDelivery();
		
	
	}
	public void testIsEmailType() {
		assertTrue(rd.isEmailType());
		assertFalse(rdSystem.isEmailType());
	}

	public void testIsSystemType() {
		assertFalse(rd.isSystemType());
		assertTrue(rdSystem.isSystemType());
		
	}

}
