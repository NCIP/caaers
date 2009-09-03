package gov.nih.nci.cabig.caaers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.classextension.EasyMock;
import org.springframework.mail.SimpleMailMessage;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ScheduledNotificationProcessServiceIntegrationTest extends CaaersDbNoSecurityTestCase {
	
	ScheduledNotificationDao scheduledNotificationDao;
	
	ScheduledNotificationProcessService service;
	CaaersJavaMailSender mailer;
	
	//expected emails, subject, body
	final String body = "Test body line";
	final String subject = "Test Subject Line";
	final List<String> emails = Arrays.asList("ab@nci.com","pinto@nci.com", "reporter@kk.com", "submitter@kk.com", 
			"jerry@rediffmail.com", "big2@cc.com", "sam@site2.com", "sunny@site2.com"); 
	
	//will store the addresses to which email is sent out to.
	final List<String> emailedTo = new ArrayList<String>();
	
	
	protected void setUp() throws Exception {
		super.setUp();
		scheduledNotificationDao = (ScheduledNotificationDao) getDeployedApplicationContext().getBean("scheduledNotificationDao");
		service = (ScheduledNotificationProcessService)getDeployedApplicationContext().getBean("scheduledNotificationProcessService");
		mailer = new CaaersJavaMailSender(){
			
			@Override
			public void send(SimpleMailMessage message) {
				assertNotNull(message);
				assertNotNull(message.getTo());
				assertEquals(1, message.getTo().length);
				
				emailedTo.add(message.getTo()[0]);
				System.out.println(" Emailing to : " + message.getTo()[0]);
				
				assertTrue(emails.contains(message.getTo()[0]));
				assertEquals(subject, message.getSubject());
				assertEquals(body, message.getText());
				
				
				
			}
		};
		service.setCaaersJavaMailSender(mailer);
	}

	
	@Override
	protected void setUpSession() {
		//interceptor should take care of opening session.
	}
	@Override
	protected void tearDownSession() {
		//interceptor should take care of closing session.
	}
	
	public void testProcess() {
		ScheduledNotification scheduledNotification = scheduledNotificationDao.getById(-222);
		assertNotNull(scheduledNotification);
		assertEquals(DeliveryStatus.SCHEDULED, scheduledNotification.getDeliveryStatus());
		
		//make sure hibernate session is closed
		try{
			scheduledNotification.getPlanedNotificaiton().getRecipients();
			fail("No Hibernate session is closed, should have thrown lazy exception");
		}catch(Exception e){
			//ignore
		}
		
		service.process(-14, -222);
		assertEquals( 5,emailedTo.size() );
		scheduledNotification = scheduledNotificationDao.getById(-222);
		assertEquals(DeliveryStatus.DELIVERED, scheduledNotification.getDeliveryStatus());
	}
	
	

}
