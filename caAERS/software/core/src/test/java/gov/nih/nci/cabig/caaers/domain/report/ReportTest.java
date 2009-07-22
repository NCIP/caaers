package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConfigProperty;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;

import java.util.Date;
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
	
	//should create a default report version.
	public void testGetLastVersion(){
		Report report = new Report();
		assertNull(report.getReportVersions());
		assertNotNull(report.getLastVersion());
		assertNotNull(report.getReportVersions());
		
		assertSame(report, report.getLastVersion().getReport());
		assertEquals(1, report.getReportVersions().size());
	}
	
	public void testIsOfSameOrganizationAndType(){
    	Organization org = Fixtures.createOrganization("test",  1);
    	ConfigProperty type = Fixtures.createConfigProperty("abcd");
    	type.setId(1);
    	
    	ConfigProperty typeX = Fixtures.createConfigProperty("x");
    	typeX.setId(3);
    	
    	ReportDefinition rd1 = Fixtures.createReportDefinition("rd1");
    	rd1.setId(1);
    	rd1.setOrganization(org);
    	rd1.setDuration(10);
    	rd1.setGroup(type);
    	rd1.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	
    	ReportDefinition rdx = Fixtures.createReportDefinition("rdx");
    	rdx.setId(22);
    	rdx.setOrganization(org);
    	rdx.setDuration(1);
    	rdx.setTimeScaleUnitType(TimeScaleUnit.DAY);
    	rdx.setGroup(typeX);
    	
    	Report r1 = Fixtures.createReport("r1");
    	r1.setId(1);
    	r1.setReportDefinition(rd1);
    	r1.setStatus(ReportStatus.INPROCESS);
    	r1.getLastVersion().setDueOn(new Date());
    	r1.getLastVersion().setReportStatus(ReportStatus.INPROCESS);
    	
    	assertTrue(r1.isOfSameOrganizationAndType(rd1));
    	assertFalse(r1.isOfSameOrganizationAndType(rdx));
		
	}
	
	public void testIsReported(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.NORMAL);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.NORMAL);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
		
		ae1.setReported(true);
		ae2.setReported(false);
		
		r.getLastVersion().addReportedAdverseEvent(ae1);
		r.getLastVersion().addReportedAdverseEvent(ae2);
		
		assertTrue(r.isReported(ae1));
		assertFalse(r.isReported(ae2));
		assertFalse(r.isReported(ae3));
		
	}
}
