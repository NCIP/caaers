/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author Biju Joseph
 *
 */
public class AdverseEventReportingPeriodTest extends AbstractTestCase {
	
	AdverseEventReportingPeriod reportingPeriod1;
	AdverseEventReportingPeriod reportingPeriod2;
	AdverseEventReportingPeriod reportingPeriod3;
	
	protected void setUp() throws Exception {
		super.setUp();
        SecurityTestUtils.switchToSuperuser();
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
		assertEquals("Cycle #: 1; TAC: acd; Start Date: 03/02/00",  reportingPeriod1.getName());
		assertEquals("Cycle #: 5; TAC: acd; ", reportingPeriod2.getName());
		assertEquals("Other", reportingPeriod3.getName());
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
		
		AdverseEvent ae9 = Fixtures.createAdverseEvent(9, Grade.NOT_EVALUATED);
		
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
		assertEquals(7, aeList.size());
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
		assertEquals(5, aeList.size());
		
	}
	
	public void testGetNumberOfReports() throws Exception{
		reportingPeriod1.addAeReport(new ExpeditedAdverseEventReport());
		Report r = Fixtures.createReport("5 day report");
		r.setStatus(ReportStatus.PENDING);
		reportingPeriod1.getAeReports().get(0).addReport(r);
		
		r = Fixtures.createReport("24 hr report");
		r.setStatus(ReportStatus.COMPLETED);
		reportingPeriod1.getAeReports().get(0).addReport(r);
		
		reportingPeriod1.addAeReport(new ExpeditedAdverseEventReport());
		r = Fixtures.createReport("10 day report");
		r.setStatus(ReportStatus.COMPLETED);
		reportingPeriod1.getAeReports().get(1).addReport(r);
		r = Fixtures.createReport("24 hr report");
		r.setStatus(ReportStatus.REPLACED);
		reportingPeriod1.getAeReports().get(1).addReport(r);
		
		assertEquals("Number of reports computed incorrectly", 3, reportingPeriod1.getNumberOfReports());
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
	public void testFindEarliestPostSubmissionUpdatedDate(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.DEATH);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.DEATH);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.DEATH);
		
		assertNull( AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(Arrays.asList(ae1, ae2, ae3)));
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -2);
		Date d = c.getTime();
		
		ae1.setPostSubmissionUpdatedDate(d);
		assertEquals(d, AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(Arrays.asList(ae1, ae2, ae3)));
	}
	
	public void createNonPopulatedAdverseEvents(){
		AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.NORMAL);
		AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.NOT_EVALUATED);
		AdverseEvent ae3 = Fixtures.createAdverseEvent(3, Grade.NORMAL);
		AdverseEvent ae4 = Fixtures.createAdverseEvent(4, Grade.NOT_EVALUATED);
		
		reportingPeriod1.addAdverseEvent(ae1);
		reportingPeriod1.addAdverseEvent(ae2);
		reportingPeriod1.addAdverseEvent(ae3);
		reportingPeriod1.addAdverseEvent(ae4);
	}
	
	public void testPopulatedAdverseEvents_ForNonPopulatedAdverseEvents(){
		
		createNonPopulatedAdverseEvents();
		// Test for not populated aes.
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList should have been empty", 0, populatedAdverseEventList.size());
	}
	
	public void testPopulatedAdverseEvents_ForGrade(){
		createNonPopulatedAdverseEvents();
		
		reportingPeriod1.getAdverseEvents().get(0).setGrade(Grade.DEATH);
		reportingPeriod1.getAdverseEvents().get(1).setGrade(Grade.LIFE_THREATENING);
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList was expected to contain only 2 graded aes", 2, populatedAdverseEventList.size());
	}
	
	public void testPopulatedAdverseEvents_ForHospitalization(){
		createNonPopulatedAdverseEvents();
		
		reportingPeriod1.getAdverseEvents().get(0).setHospitalization(Hospitalization.YES);
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList was expected to contain only 1 adverse event", 1, populatedAdverseEventList.size());
	}
	
	public void testPopulatedAdverseEvents_ForExpected(){
		createNonPopulatedAdverseEvents();
		
		reportingPeriod1.getAdverseEvents().get(0).setExpected(true);
		reportingPeriod1.getAdverseEvents().get(1).setExpected(false);
		reportingPeriod1.getAdverseEvents().get(2).setExpected(true);
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList was expected to contain only 3 adverse events", 3, populatedAdverseEventList.size());
	}
	
	public void testPopulatedAdverseEvents_ForParticipantAtRisk(){
		createNonPopulatedAdverseEvents();
		
		reportingPeriod1.getAdverseEvents().get(0).setParticipantAtRisk(true);
		reportingPeriod1.getAdverseEvents().get(1).setParticipantAtRisk(false);
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList was expected to contain only 2 adverse events", 2, populatedAdverseEventList.size());
	}
	
	public void testPopulatedAdverseEvent_ForAttribution(){
		createNonPopulatedAdverseEvents();
		
		reportingPeriod1.getAdverseEvents().get(0).setAttributionSummary(Attribution.POSSIBLE);
		List<AdverseEvent> populatedAdverseEventList = reportingPeriod1.getPopulatedAdverseEvents();
		assertEquals("populatedAdverseEventList was expected to contain only 1 ae", 1, populatedAdverseEventList.size());
	}
	
	public void testSetRetiredIndicator_true(){
		createNonPopulatedAdverseEvents();
		reportingPeriod1.setRetiredIndicator(true);
		assertTrue("RetiredIndicator of adverse events are not being set to true", reportingPeriod1.getAdverseEvents().get(0).getRetiredIndicator());
		assertTrue("RetiredIndicator of adverse events are not being set to true", reportingPeriod1.getAdverseEvents().get(1).getRetiredIndicator());
	}

    public void testFindAdverseEventByIdTermAndDates(){
         reportingPeriod1.getAdverseEvents().clear();
         CtcTerm ctcTerm = Fixtures.createCtcTerm("abcd", "123");
         ctcTerm.setId(9);
         AdverseEventCtcTerm aeCtcTerm1 = new AdverseEventCtcTerm();
         aeCtcTerm1.setCtcTerm(ctcTerm);
         aeCtcTerm1.setId(9);

        AdverseEventCtcTerm aeCtcTerm2 = new AdverseEventCtcTerm();
        aeCtcTerm2.setCtcTerm(ctcTerm);
        aeCtcTerm2.setId(19);

         Date startDate = DateUtils.yesterday();
         Date endDate = DateUtils.tomorrow();
         AdverseEvent ae1 = Fixtures.createAdverseEvent(2, Grade.MILD, ctcTerm);
         ae1.setAdverseEventCtcTerm(aeCtcTerm1);
         ae1.setStartDate(startDate);
         ae1.setEndDate(endDate);

         AdverseEvent ae2 = Fixtures.createAdverseEvent(12, Grade.SEVERE, ctcTerm);
         ae2.setAdverseEventCtcTerm(aeCtcTerm2);
         ae2.setStartDate(startDate);
         ae2.setEndDate(endDate);

         reportingPeriod1.addAdverseEvent(ae1);
         AdverseEvent ae3 = reportingPeriod1.findAdverseEventByIdTermAndDates(ae2);
         assertSame(ae1, ae3);

         ae2.setStartDate(DateUtils.today());
         ae3 = reportingPeriod1.findAdverseEventByIdTermAndDates(ae2);
         assertNull(ae3);

    }
}
