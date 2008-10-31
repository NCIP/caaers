package gov.nih.nci.cabig.caaers.domain.report;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import junit.framework.TestCase;

/** 
 * 
 * @author Biju Joseph
 *
 */
public class ReportTest extends TestCase {
	Report r;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		
	}
	public void testHasSystemDeliveries() {
		Report r = Fixtures.createReport("biju");
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		
		assertTrue(r.hasSystemDeliveries());
	}
	
	public void testHasDeliveriesButNoSystemDelivery() {
		Report r = Fixtures.createReport("biju");
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_FAX, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		
		assertFalse(r.hasSystemDeliveries());
	}
	
	public void testGetExternalSystemDeliveries(){
		Report r = Fixtures.createReport("biju");
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		List<ReportDelivery> systemDeliveries = r.getExternalSystemDeliveries();
		assertEquals(1, systemDeliveries.size());
	}
	
	public void testGetEmailRecipients(){
		Report r = Fixtures.createReport("biju");
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		ReportDelivery rd = rdd.createReportDelivery();
		rd.setEndPoint("biju.joseph@semanticbits.com");
		r.addReportDelivery(rd);
		
		ReportVersion rv = new ReportVersion();
		rv.setCcEmails("biju.joseph.padupurackal@gmail.com,, , biju@gmail.com,");
		r.addReportVersion(rv);
		
		List<String> emails = r.getEmailRecipients();
		System.out.println(emails);
		assertEquals(3,emails.size());
	}

}
