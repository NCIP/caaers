package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

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
		ae1.setReport(Fixtures.createSavableExpeditedReport());
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
		assertEquals(4, aeList.size());
		assertSame(ae7, aeList.get(0));
		assertSame(ae6, aeList.get(1));
		assertSame(ae8, aeList.get(2));
		
		assertSame(ae1, aeList.get(3));
	
	}
	
	
	/**
	 * This method tests the reportable adverse events.
	 */
	public void testGradedAdverseEvents(){
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
		assertEquals(4, aeList.size());
		assertSame(ae7, aeList.get(0));
		assertSame(ae6, aeList.get(1));
		assertSame(ae8, aeList.get(2));
		assertSame(ae1, aeList.get(3));
	}
	
	
	/**
	 * This method tests the reportable adverse events.
	 */
	public void testAllReportedAndReportableAdverseEvents(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae1.setExpected(true);
		ae1.setReport(Fixtures.createSavableExpeditedReport());
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,null); //not graded
		ae2.setReport(Fixtures.createSavableExpeditedReport());
		
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
		
		List<AdverseEvent> aeList = reportingPeriod1.getAllReportedAndReportableAdverseEvents();
		assertEquals(6, aeList.size());
		assertSame(ae7, aeList.get(0));
		assertSame(ae6, aeList.get(1));
		assertSame(ae8, aeList.get(2));
		assertSame(ae1, aeList.get(3));
		assertSame(ae2, aeList.get(5));
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
	
	
	public void testGetModifiedReportableAdverseEvents(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setAttributionSummary(Attribution.DEFINITE);
		ae1.setExpected(true);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2,Grade.NORMAL);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
		ae3.setExpected(true);
		AdverseEvent ae4 = Fixtures.createAdverseEvent(4, Grade.NORMAL);
		ae4.setExpected(true);
		ae4.setReported(true);
		ae4.setHospitalization(Hospitalization.YES);
		AdverseEvent ae5 = Fixtures.createAdverseEvent(5, Grade.NORMAL);
		ae5.setExpected(true);
		ae5.setReported(true);
		ae5.setHospitalization(Hospitalization.YES);
		ae5.setAttributionSummary(Attribution.POSSIBLE);
		
		AdverseEvent ae6 = Fixtures.createAdverseEvent(6, Grade.DEATH);
		ae6.setReported(true);

		AdverseEvent ae7 = Fixtures.createAdverseEvent(7, Grade.LIFE_THREATENING);
		ae7.setRequiresReporting(true);
		ae7.setReported(true);

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
		
		List<AdverseEvent> aeList = reportingPeriod1.getModifiedReportableAdverseEvents();
		assertEquals(2, aeList.size());
		assertSame(ae8, aeList.get(0));
		assertSame(ae1, aeList.get(1));
		
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
	
	public void testGetEarliestAdverseEventGradedDate(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setReport(Fixtures.createSavableExpeditedReport());
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
		ae7.setGradedDate(DateUtils.parseDateString("02/02/2008").toDate());
		AdverseEvent ae8 = Fixtures.createAdverseEvent(8, Grade.LIFE_THREATENING);
		ae8.setHospitalization(Hospitalization.YES);
		ae8.setGradedDate(DateUtils.parseDateString("01/02/2008").toDate());
		
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
		
		Date d = reportingPeriod1.getEarliestAdverseEventGradedDate();
		assertNotNull(d);
		System.out.println(DateUtils.formatDate(d));
		System.out.println(d);
		assertEquals(DateUtils.formatDate(d), "01/02/2008");
		
	}
	
	public void testGetEarliestAdverseEventGradedDate_WhenGradedDateIsNull(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.MILD);
		ae1.setReport(Fixtures.createSavableExpeditedReport());
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
		
		Date d = reportingPeriod1.getEarliestAdverseEventGradedDate();
		assertNull(d);
		
	}
	
	public void testGetAllSponsorReportsCompleted_WhenNoReports(){
		boolean result = reportingPeriod1.getAllSponsorReportsCompleted();
		assertFalse(result);
	}
	
	public void testGetAllSponsorReportsCompleted_WhenCompletedReports(){
		ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
		Report report = Fixtures.createReport("test");
		report.setStatus(ReportStatus.COMPLETED);
		report.getLastVersion().setReportStatus(ReportStatus.COMPLETED);
		aeReport.addReport(report);
		reportingPeriod1.addAeReport(aeReport);
		report.getReportDefinition().setAmendable(true);
		report.getReportDefinition().getOrganization().setNciInstituteCode(reportingPeriod1.getStudy().getPrimaryFundingSponsorOrganization().getNciInstituteCode());
		
		StudyParticipantAssignment assignment = Fixtures.createAssignment();
		Study study = assignment.getStudySite().getStudy();
		StudyFundingSponsor sponsor = Fixtures.createStudyFundingSponsor(report.getReportDefinition().getOrganization());
		sponsor.setPrimary(true);
		study.addStudyOrganization(sponsor);
		
		aeReport.setAssignment(assignment);
		
		boolean result = reportingPeriod1.getAllSponsorReportsCompleted();
		assertTrue(result);
	}
}
