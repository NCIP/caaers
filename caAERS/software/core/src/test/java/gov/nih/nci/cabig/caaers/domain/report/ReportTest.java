package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import java.util.List;

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
		r = Fixtures.createReport("joel");
		
	}
	public void testHasSystemDeliveries() {
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		
		assertTrue(r.hasSystemDeliveries());
	}
	
	public void testHasDeliveriesButNoSystemDelivery() {
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_FAX, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		
		assertFalse(r.hasSystemDeliveries());
	}
	
	public void testGetExternalSystemDeliveries(){
		ReportDeliveryDefinition rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_URL, ReportDeliveryDefinition.ENTITY_TYPE_SYSTEM);
		r.addReportDelivery(rdd.createReportDelivery());
		rdd = Fixtures.createReportDeliveryDefinition(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL, ReportDeliveryDefinition.ENTITY_TYPE_PERSON);
		r.addReportDelivery(rdd.createReportDelivery());
		List<ReportDelivery> systemDeliveries = r.getExternalSystemDeliveries();
		assertEquals(1, systemDeliveries.size());
	}
	
	public void testGetEmailRecipients(){
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
	/**
	 * This method tests {@link Report#isActive()}
	 */
	public void testIsActive(){
		assertTrue(r.isActive());
		r.setStatus(ReportStatus.WITHDRAWN);
		assertFalse(r.isActive());
		r.setStatus(ReportStatus.REPLACED);
		assertFalse(r.isActive());
		r.setStatus(ReportStatus.FAILED);
		assertTrue(r.isActive());
	}
	
	public void testGetPhysicianSignOff(){
		Report report = Fixtures.createReport("testReport");
		report.addReportVersion(new ReportVersion());
		report.getLastVersion().setPhysicianSignoff(true);
		assertTrue("getPhysicianSignOff incorrect", report.getPhysicianSignoff());
	}

	public void testIsAmended(){
		Report report = Fixtures.createReport("test report");
		ReportVersion reportVersion = new ReportVersion();
		reportVersion.setReportStatus(ReportStatus.AMENDED);
		report.addReportVersion(reportVersion);
		assertTrue("isAmended is returning and incorrect value", report.isAmended());
	}
}
