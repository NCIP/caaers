package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.ExpeditedReportPerson;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Jun 1, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class ReportServiceTest extends CaaersTestCase {
	static ApplicationContext appCtx;

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
		service = (ReportServiceImpl) getApplicationContext().getBean("reportService");

		Identifier id = new Identifier();
		id.setPrimaryIndicator(true);
		id.setValue("123ST903");

		Study study = new Study();
		study.setShortTitle("Headache");
		study.addIdentifier(id);

		aeReport = new ExpeditedAdverseEventReport();
		Reporter reporter = new Reporter();
		reporter.getContactMechanisms().put(ExpeditedReportPerson.EMAIL, REPORTER_EMAIL_ADDRESS);
		aeReport.setReporter(reporter);

		Participant p = new Participant();
		Identifier idp = new Identifier();
		idp.setValue("12345");
		idp.setPrimaryIndicator(true);

		p.addIdentifier(idp);
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		// spa.setParticipant(p);
		p.addAssignment(spa);

		StudySite ss = new StudySite();
		ss.setStudy(study);
		aeReport.setAssignment(spa);
		ss.addAssignment(spa);
		spa.setStudySite(ss);

		List<Recipient> rList = new ArrayList<Recipient>();
		rList.add(new RoleBasedRecipient(REPORTER_ROLE));
		rList.add(new ContactMechanismBasedRecipient(CONTACT_MECH_EMAIL_ADDRESS));

		NotificationBodyContent content = new NotificationBodyContent();
		content
				.setBody("The study : ${study.shortTitle}, identified by ${study.primaryIdentifier.value}, has a patient with id :${patientId}");

		List<PlannedNotification> pnfList = new ArrayList<PlannedNotification>();
		PlannedEmailNotification penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(2998);
		penf.setIndexOnTimeScale(2);
		penf.setSubjectLine("Subject Line for day 2");
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);

		penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(3999);
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
		// report.setName("Report-Name");
		report.setCreatedOn(new Date());
		report.setId(9999);
		report.setReportDefinition(reportDef);
		report.setAeReport(aeReport);
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();

		try {
			System.gc();
			Thread.sleep(5000);
		}
		catch (Exception exp) {
		}
	}

	public static ApplicationContext getApplicationContext() {
		String[] locations = new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
				"classpath*:gov/nih/nci/cabig/caaers/testApplicationContext-reportservice.xml" };
		if (appCtx == null) {
			appCtx = new ClassPathXmlApplicationContext(locations);
			SecurityTestUtils.enableAuthorization(false, appCtx);
			SecurityTestUtils.switchToSuperuser();
		}
		return appCtx;
	}

	public void testFindToAddresses() {
		ReportDefinition calendarTemplate = report.getReportDefinition();
		PlannedNotification pnf = calendarTemplate.getPlannedNotifications().get(0);
		List<String> addresses = service.findToAddresses(pnf, report);
		assertTrue("Recipient should be present", addresses.contains(CONTACT_MECH_EMAIL_ADDRESS));
		assertTrue("Recipient should be present", addresses.contains(REPORTER_EMAIL_ADDRESS));
	}

	public void testFindContactValueOfType() {
		String address = service.findContactMechanismValue(REPORTER_ROLE, ExpeditedReportPerson.EMAIL, aeReport);
		assertEquals("email should be same", REPORTER_EMAIL_ADDRESS, address);
	}

	public void testApplyRuntimeReplacements() {
		String rawText = "This is a raw text";
		String s = service.applyRuntimeReplacements(rawText, report);
		assertNotNull("The runtime replacement result should not be null", s);
	}

	public void testCreateReportDefinition() {
		Report actual = service.createReport(reportDef, aeReport);
		assertNotNull("AE actual should not be null", actual.getAeReport());
		assertNotNull("Report Definition should not be null", actual.getReportDefinition());
		assertEquals("Size of ScheduledNotification Size must be 4 ", actual.getScheduledNotifications().size(), 4);
		for (ScheduledNotification snf : actual.getScheduledNotifications()) {
			assertNotNull("Planned Notification must not be null in scheduled notification", snf
					.getPlanedNotificaiton());
		}
		PlannedNotification pnf = actual.getReportDefinition().getPlannedNotifications().get(0);
		ScheduledNotification snf = actual.getScheduledNotifications().get(0);
		System.out.println(snf.getBody());
		assertEquals("The body of ScheduledNotificaiton should be same", snf.getBody(),
				"The study : Headache, identified by 123ST903, has a patient with id :12345");
	}

	/*
	 * public void testApplyCalendarTemplate() { Date now = new Date(); Calendar cal = GregorianCalendar.getInstance(); Calendar caltemp =
	 * GregorianCalendar.getInstance(); service.applyCalendarTemplate(reportDef, report); assertNotNull(report.getScheduledNotifications());
	 * assertNotNull("due date should be there", report.getDueOn()); cal.setTime(now);
	 * cal.add(reportDef.getTimeScaleUnitType().getCalendarTypeCode(), reportDef.getDuration()); caltemp.setTime(report.getDueOn());
	 * assertEquals("due date should match", caltemp.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()),
	 * cal.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()) ); for(PlannedNotification pnf :
	 * reportDef.getPlannedNotifications()){ for(ScheduledNotification snf : report.getScheduledNotifications()){ assertNotNull("Planned
	 * Notificaiton should be present in Scheduled Notificaiton", snf.getPlanedNotificaiton()); if(pnf.getId() ==
	 * snf.getPlanedNotificaiton().getId()){ //this snf is derieved of the pnf (which is in outer context) assertEquals("Notificaiton Body
	 * content must be equal", pnf.getNotificationBodyContent().getBodyAsString(), new String(snf.getBody())); if(pnf instanceof
	 * PlannedEmailNotification){ PlannedEmailNotification penf = (PlannedEmailNotification)pnf; ScheduledEmailNotification senf =
	 * (ScheduledEmailNotification)snf; assertEquals("from address should be equal", penf.getFromAddress(), senf.getFromAddress());
	 * assertEquals("subject line should be same", penf.getSubjectLine(), senf.getSubjectLine()); assertNotNull("ToAddress should not be
	 * null", senf.getToAddress()); cal.setTime(now); cal.add(reportDef.getTimeScaleUnitType().getCalendarTypeCode(),
	 * penf.getIndexOnTimeScale()); caltemp.setTime(senf.getScheduledOn()); assertEquals("The scheduled on date should match",
	 * caltemp.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode()),
	 * cal.get(reportDef.getTimeScaleUnitType().getCalendarTypeCode())); } } }//for each snf }//for each pnf }
	 */
}
