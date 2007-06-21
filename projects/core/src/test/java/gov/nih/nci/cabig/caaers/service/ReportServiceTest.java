package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : Jun 1, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class ReportServiceTest extends CaaersTestCase {
	ExpeditedAdverseEventReport aeReport;
	Report report;
	ReportDefinition reportDef;
	ReportServiceImpl service;
    private static final String REPORTER_EMAIL_ADDRESS = "visu.patlolla@semanticbits.com";
    private static final String CONTACT_MECH_EMAIL_ADDRESS = "biju@gmail.com";
    private static final String REPORTER_ROLE = "Reporter";

    @Override
    protected void setUp() throws Exception {
		super.setUp();
		service = (ReportServiceImpl) getDeployedApplicationContext().getBean("reportService");

        aeReport = new ExpeditedAdverseEventReport();
		Reporter reporter = new Reporter();
        reporter.getContactMechanisms().put(ExpeditedReportPerson.EMAIL, REPORTER_EMAIL_ADDRESS);
        aeReport.setReporter(reporter);

		List<Recipient> rList = new ArrayList<Recipient>();
		rList.add(new RoleBasedRecipient(REPORTER_ROLE));
		rList.add(new ContactMechanismBasedRecipient(CONTACT_MECH_EMAIL_ADDRESS));
		
		NotificationBodyContent content = new NotificationBodyContent();
		content.setBody("This is my body".getBytes());
		
		List<PlannedNotification> pnfList = new ArrayList<PlannedNotification>();
		PlannedEmailNotification penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(2999);
		penf.setIndexOnTimeScale(2);
		penf.setSubjectLine("Subject Line for day 2");
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);
		
		penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(2999);
		penf.setIndexOnTimeScale(4);
		penf.setSubjectLine("Subject Line for day 4");
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);
		
		reportDef = new ReportDefinition();
		reportDef.setDescription("a rct description");
		reportDef.setDuration(5);
		reportDef.setId(333);
		reportDef.setName("An RCT");
		reportDef.setTimeScaleUnitType(TimeScaleUnit.DAY);
		reportDef.setPlannedNotifications(pnfList);
		
		report = new Report();
		report.setName("Report-Name");
		report.setCreatedOn(new Date());
		report.setId(9999);
		report.setReportDefinition(reportDef);
		report.setAeReport(aeReport);
	}

	public void testFindToAddresses() {
		ReportDefinition calendarTemplate = report.getReportDefinition();
		PlannedNotification pnf = calendarTemplate.getPlannedNotifications().get(0);
		List<String> addresses = service.findToAddresses(pnf, report);
		System.out.println(addresses);
		assertTrue("Recipient should be present",addresses.contains(CONTACT_MECH_EMAIL_ADDRESS));
		assertTrue("Recipient should be present", addresses.contains(REPORTER_EMAIL_ADDRESS));
	}

	public void testFindContactValueOfType() {
		String address = service.findContactMechanismValue(REPORTER_ROLE, ExpeditedReportPerson.EMAIL, aeReport);
		assertEquals("email should be same",  REPORTER_EMAIL_ADDRESS, address);
	}

	public void testApplyCalendarTemplate() {
		Date now = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		Calendar caltemp = GregorianCalendar.getInstance();
		service.applyCalendarTemplate(reportDef, report);
		assertNotNull(report.getScheduledNotifications());
		assertNotNull("due date should be there", report.getDueOn());
		cal.setTime(now);
		cal.add(reportDef.getTimeScaleUnitType().getCalendarTypeCode(), reportDef.getDuration());
		caltemp.setTime(report.getDueOn());
		
		assertEquals("due date should match", 
				caltemp.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()), cal.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()) );
		
	
		for(PlannedNotification pnf : reportDef.getPlannedNotifications()){
			for(ScheduledNotification snf : report.getScheduledNotifications()){
				assertNotNull("Planned Notificaiton should be present in Scheduled Notificaiton", snf.getPlanedNotificaiton());
				if(pnf.getId() == snf.getPlanedNotificaiton().getId()){
					//this snf is derieved of the pnf (which is in outer context)
					assertEquals("Notificaiton Body content must be equal", pnf.getNotificationBodyContent().getBodyAsString(), new String(snf.getBody()));
					if(pnf instanceof PlannedEmailNotification){
						PlannedEmailNotification penf = (PlannedEmailNotification)pnf;
						ScheduledEmailNotification senf = (ScheduledEmailNotification)snf;
						assertEquals("from address should be equal", penf.getFromAddress(), senf.getFromAddress());
						assertEquals("subject line should be same", penf.getSubjectLine(), senf.getSubjectLine());
						assertNotNull("ToAddress should not be null", senf.getToAddress());
						cal.setTime(now);
						cal.add(reportDef.getTimeScaleUnitType().getCalendarTypeCode(), penf.getIndexOnTimeScale());
						caltemp.setTime(senf.getScheduledOn());
						assertEquals("The scheduled on date should match",
								caltemp.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()), cal.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()));

					}
				}
			}//for each snf
		}//for each pnf
		
	}
	
	
}
