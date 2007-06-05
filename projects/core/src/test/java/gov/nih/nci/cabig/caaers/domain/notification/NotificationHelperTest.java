package gov.nih.nci.cabig.caaers.domain.notification;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ContactMechanism;
import gov.nih.nci.cabig.caaers.domain.Reporter;

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
public class NotificationHelperTest extends CaaersTestCase {
	AdverseEventReport mockAEReport;
	ReportSchedule rs;
	ReportCalendarTemplate rct;
	
	protected void setUp() throws Exception {
		super.setUp();
		ContactMechanism mockContactMechanism  = registerMockFor(ContactMechanism.class);
		expect(mockContactMechanism.getType()).andReturn("email").anyTimes();
		expect(mockContactMechanism.getValue()).andReturn("visu.patlolla@semanticbits.com").anyTimes();
		List<ContactMechanism> cmList = new ArrayList<ContactMechanism>();
		cmList.add(mockContactMechanism);
		
		Reporter mockReporter = registerMockFor(Reporter.class);
		expect(mockReporter.getContactMechanisms()).andReturn(cmList).anyTimes();
		
		mockAEReport =  registerMockFor(AdverseEventReport.class);
		expect(mockAEReport.getReporter()).andReturn(mockReporter).anyTimes();
		replayMocks();
		
		
		List<Recipient> rList = new ArrayList<Recipient>();
		rList.add(new RoleBasedRecipient("Reporter"));
		rList.add(new ContactMechanismBasedRecipient("biju@gmail.com"));
		
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
		
		rct = new ReportCalendarTemplate();
		rct.setDescription("a rct description");
		rct.setDuration(5);
		rct.setId(333);
		rct.setName("An RCT");
		rct.setTimeScaleUnitType(TimeScaleUnit.DAY);
		rct.setPlannedNotifications(pnfList);
		
		rs = new ReportSchedule();
		rs.setName("ReportSchedule-Name");
		rs.setCreatedOn(new Date());
		rs.setId(9999);
		rs.setReportCalendarTemplate(rct);
		rs.setAeReport(mockAEReport);

		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		verifyMocks();
	}

	public void testFindToAddresses() {
		ReportCalendarTemplate calendarTemplate = rs.getReportCalendarTemplate();
		PlannedNotification pnf = calendarTemplate.getPlannedNotifications().get(0);
		List<String> addresses = NotificationHelper.findToAddresses(pnf, rs);
		System.out.println(addresses);
		assertTrue("Recipient should be present",addresses.contains("biju@gmail.com"));
		assertTrue("Recipient should be present", addresses.contains("visu.patlolla@semanticbits.com"));
	}

	public void testFindContactValueOfType() {
		
		List<String> addresses = NotificationHelper.findContactValuesOfType("email", rs.getAeReport().getReporter().getContactMechanisms());
		assertTrue("email should be same",  addresses.contains("visu.patlolla@semanticbits.com"));
	}

	public void testApplyCalendarTemplate() {
		Date now = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		Calendar caltemp = GregorianCalendar.getInstance();
		NotificationHelper.applyCalendarTemplate(rct, rs);
		assertNotNull(rs.getScheduledNotifications());
		assertNotNull("due date should be there", rs.getDueOn());
		cal.setTime(now);
		cal.add(rct.getTimeScaleUnitType().getCalendarTypeCode(), rct.getDuration());
		caltemp.setTime(rs.getDueOn());
		
		assertEquals("due date should match", 
				caltemp.get(rct.getTimeScaleUnitType().getCalendarTypeCode()), cal.get(rct.getTimeScaleUnitType().getCalendarTypeCode()) );
		
	
		for(PlannedNotification pnf : rct.getPlannedNotifications()){
			for(ScheduledNotification snf : rs.getScheduledNotifications()){
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
						cal.add(rct.getTimeScaleUnitType().getCalendarTypeCode(), penf.getIndexOnTimeScale());
						caltemp.setTime(senf.getScheduledOn());
						assertEquals("The scheduled on date should match",
								caltemp.get(rct.getTimeScaleUnitType().getCalendarTypeCode()), cal.get(rct.getTimeScaleUnitType().getCalendarTypeCode()));

					}
				}
			}//for each snf
		}//for each pnf
		
	}

}
