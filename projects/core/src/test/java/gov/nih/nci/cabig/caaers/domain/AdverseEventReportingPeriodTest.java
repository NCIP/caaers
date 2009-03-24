package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventReportingPeriodTest extends AbstractNoSecurityTestCase {
	
	AdverseEventReportingPeriod reportingPeriod1;
	AdverseEventReportingPeriod reportingPeriod2;
	AdverseEventReportingPeriod reportingPeriod3;
	
	protected void setUp() throws Exception {
		super.setUp();
		TreatmentAssignment tac1 = new TreatmentAssignment();
		tac1.setCode("acd");
		
		reportingPeriod1 = Fixtures.createReportingPeriod();
		reportingPeriod1.setStartDate(new Date(2000, 2,2));
		reportingPeriod1.setEndDate(new Date(2000, 2,2));
		reportingPeriod1.setCycleNumber(1);
		reportingPeriod1.setTreatmentAssignment(tac1);
		
		reportingPeriod2 = Fixtures.createReportingPeriod();
		reportingPeriod2.setCycleNumber(5);
		reportingPeriod2.setTreatmentAssignment(tac1);
		
		reportingPeriod3 = Fixtures.createReportingPeriod();
	}

	public void testGetName() {
		assertEquals("Cycle #: 1; Start Date: 03/02/00",  reportingPeriod1.getName());
		assertEquals("Cycle #: 5; ", reportingPeriod2.getName());
		assertEquals("", reportingPeriod3.getName());
	}
	
	/**
	 * This method tests the reportable adverse events.
	 */
	public void testReportableAdverseEvents(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae1.setExpected(true);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.NORMAL);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
		ae3.setExpected(true);
		AdverseEvent ae4 = Fixtures.createAdverseEvent(4, Grade.NORMAL);
		ae4.setExpected(true);
		ae4.setHospitalization(Hospitalization.YES);
		ae4.setReport(new ExpeditedAdverseEventReport());
		
		AdverseEvent ae5 = Fixtures.createAdverseEvent(5, Grade.NORMAL);
		ae5.setExpected(true);
		ae5.setHospitalization(Hospitalization.YES);
		ae5.setAttributionSummary(Attribution.POSSIBLE);
		
		AdverseEvent ae6 = Fixtures.createAdverseEvent(6, Grade.DEATH);
		AdverseEvent ae7 = Fixtures.createAdverseEvent(7, Grade.LIFE_THREATENING);
		ae7.setRequiresReporting(true);
		AdverseEvent ae8 = Fixtures.createAdverseEvent(8, Grade.LIFE_THREATENING);
		ae8.setHospitalization(Hospitalization.YES);
		
		AdverseEvent ae9 = Fixtures.createAdverseEvent(7, Grade.NOT_EVALUATED);
		
		reportingPeriod1.addAdverseEvent(ae1);
		reportingPeriod1.addAdverseEvent(ae2);
		reportingPeriod1.addAdverseEvent(ae3);
		reportingPeriod1.addAdverseEvent(ae4);
		reportingPeriod1.addAdverseEvent(ae5);
		reportingPeriod1.addAdverseEvent(ae6);
		reportingPeriod1.addAdverseEvent(ae7);
		reportingPeriod1.addAdverseEvent(ae8);
		reportingPeriod1.addAdverseEvent(ae9);
		
		List<AdverseEvent> aeList = reportingPeriod1.getReportableAdverseEvents();
		assertEquals(7, aeList.size());
		assertSame(ae7, aeList.get(0));
		assertSame(ae6, aeList.get(1));
		assertSame(ae8, aeList.get(2));
		assertSame(ae1, aeList.get(3));
	}
	
	/**
	 * This method tests the reportable adverse events.
	 */
	public void testEvaluatedAdverseEvents(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae1.setExpected(true);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.NORMAL);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
		ae3.setExpected(true);
		AdverseEvent ae4 = Fixtures.createAdverseEvent(4, Grade.NORMAL);
		ae4.setExpected(true);
		ae4.setHospitalization(Hospitalization.YES);
		AdverseEvent ae5 = Fixtures.createAdverseEvent(5, Grade.NORMAL);
		ae5.setExpected(true);
		ae5.setHospitalization(Hospitalization.YES);
		ae5.setAttributionSummary(Attribution.POSSIBLE);
		
		AdverseEvent ae6 = Fixtures.createAdverseEvent(6, Grade.DEATH);
		AdverseEvent ae7 = Fixtures.createAdverseEvent(7, Grade.LIFE_THREATENING);
		ae7.setRequiresReporting(true);
		AdverseEvent ae8 = Fixtures.createAdverseEvent(8, Grade.LIFE_THREATENING);
		ae8.setHospitalization(Hospitalization.YES);
		
		AdverseEvent ae9 = Fixtures.createAdverseEvent(7, Grade.NOT_EVALUATED);
		
		reportingPeriod1.addAdverseEvent(ae1);
		reportingPeriod1.addAdverseEvent(ae2);
		reportingPeriod1.addAdverseEvent(ae3);
		reportingPeriod1.addAdverseEvent(ae4);
		reportingPeriod1.addAdverseEvent(ae5);
		reportingPeriod1.addAdverseEvent(ae6);
		reportingPeriod1.addAdverseEvent(ae7);
		reportingPeriod1.addAdverseEvent(ae8);
		reportingPeriod1.addAdverseEvent(ae9);
		
		List<AdverseEvent> aeList = reportingPeriod1.getEvaluatedAdverseEvents();
		assertEquals(8, aeList.size());
		assertSame(ae7, aeList.get(0));
		assertSame(ae6, aeList.get(1));
		assertSame(ae8, aeList.get(2));
		assertSame(ae1, aeList.get(3));
		assertSame(ae4, aeList.get(7));
	}
	
	public void testGetNumberOfReports() throws Exception{
		reportingPeriod1.addAeReport(new ExpeditedAdverseEventReport());
		reportingPeriod1.getAeReports().get(0).addReport(Fixtures.createReport("5 day report"));
		reportingPeriod1.getAeReports().get(0).addReport(Fixtures.createReport("24 hr report"));
		reportingPeriod1.addAeReport(new ExpeditedAdverseEventReport());
		reportingPeriod1.getAeReports().get(1).addReport(Fixtures.createReport("10 day report"));
		reportingPeriod1.getAeReports().get(1).addReport(Fixtures.createReport("24 hr report"));
		reportingPeriod1.getAeReports().get(1).addReport(Fixtures.createReport("5 day report"));
		reportingPeriod1.getAeReports().get(1).getReports().get(2).setStatus(ReportStatus.REPLACED);
		assertEquals("Number of reports computed incorrectly", 4, reportingPeriod1.getNumberOfReports());
	}
}
