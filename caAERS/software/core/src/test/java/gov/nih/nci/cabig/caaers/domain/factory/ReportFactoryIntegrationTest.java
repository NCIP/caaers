package gov.nih.nci.cabig.caaers.domain.factory;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;


import gov.nih.nci.cabig.caaers.AbstractNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.lang.NowFactory;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportFactoryIntegrationTest extends AbstractNoSecurityTestCase {
	ReportFactory reportFactory;
	Report report;
	ReportDefinition reportDefinition;
	
	protected void setUp() throws Exception {
		super.setUp();
		reportFactory = (ReportFactory)getDeployedApplicationContext().getBean("reportFactory");
		
		reportDefinition = Fixtures.createReportDefinition("test");
		reportDefinition.getPlannedNotifications().clear();
		report = Fixtures.createReport("test");
		report.getScheduledNotifications().clear();
		
	}
	
	//testing 2 planned notifications, on a Hour based report definition
	public void testAddScheduledNotifications() {
		reportDefinition.setTimeScaleUnitType(TimeScaleUnit.DAY);
		
		PlannedNotification pn1 = Fixtures.createPlannedEmailNotification();
		pn1.setIndexOnTimeScale(2);
		reportDefinition.addPlannedNotification(pn1);
		
		PlannedNotification pn2 = Fixtures.createPlannedEmailNotification();
		pn2.setIndexOnTimeScale(3);
		reportDefinition.addPlannedNotification(pn2);
		
		assertEquals(2, reportDefinition.getPlannedNotifications().size());
		reportFactory.addScheduledNotifications(reportDefinition, report);
		assertEquals(2, report.getScheduledNotifications().size());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		Date dayAfterTomorrow = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date dayAfterDayAfterTomorrow = cal.getTime();
		
		assertEquals(DeliveryStatus.CREATED, report.getScheduledNotifications().get(0).getDeliveryStatus());
		assertEquals(0, DateUtils.compareDate(new Date(), report.getScheduledNotifications().get(0).getCreatedOn()));
		
		assertEquals(0, DateUtils.compareDate(dayAfterTomorrow, report.getScheduledNotifications().get(0).getScheduledOn()));
		assertEquals(0, DateUtils.compareDate(dayAfterDayAfterTomorrow, report.getScheduledNotifications().get(1).getScheduledOn()));
		assertTrue(report.hasScheduledNotifications());
		
	}
	
	public void testAddScheduledNotifications_NoPlannedNotifications() {
		assertTrue(CollectionUtils.isEmpty(reportDefinition.getPlannedNotifications()));
		assertTrue(CollectionUtils.isEmpty(report.getScheduledNotifications()));
		
		reportFactory.addScheduledNotifications(reportDefinition, report);
		
		assertTrue(CollectionUtils.isEmpty(report.getScheduledNotifications()));
	}

}
