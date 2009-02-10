package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import junit.framework.TestCase;

public class AdverseEventReportingPeriodTest extends TestCase {
	
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
		assertEquals("Start Date :03/02/00;TAC :acd;Cycle # :1",  reportingPeriod1.getName());
		assertEquals("TAC :acd;Cycle # :5", reportingPeriod2.getName());
		assertEquals("", reportingPeriod3.getName());
		
	}

}
