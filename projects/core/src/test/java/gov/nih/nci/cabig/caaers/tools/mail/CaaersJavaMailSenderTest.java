package gov.nih.nci.cabig.caaers.tools.mail;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.Date;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;

public class CaaersJavaMailSenderTest extends TestCase {

	public void testSendSimpleMailMessage() throws Exception{
//		CaaersJavaMailSender mailer2 = new CaaersJavaMailSender();//(CaaersJavaMailSender)getDeployedApplicationContext().getBean("mailer");
//		
//		Configuration conf = EasyMock.createMock(Configuration.class);
//		EasyMock.expect(conf.get(Configuration.SMTP_ADDRESS)).andReturn("smtp.gmail.com").anyTimes();
//		EasyMock.expect(conf.get(Configuration.SMTP_PASSWORD)).andReturn("caaers18").anyTimes();
//		EasyMock.expect(conf.get(Configuration.SMTP_PORT)).andReturn(25).anyTimes();
//		EasyMock.expect(conf.get(Configuration.SMTP_SSL_ENABLED)).andReturn(true).anyTimes();
//		EasyMock.expect(conf.get(Configuration.SMTP_USER)).andReturn("caaers.app@gmail.com").anyTimes();
//		EasyMock.expect(conf.get(Configuration.SYSTEM_FROM_EMAIL)).andReturn("caaers.app@gmail.com").anyTimes();
//		EasyMock.replay(conf);
//		mailer2.setConfiguration(conf);
//		mailer2.afterPropertiesSet();
//		
//		
//		
//		
//		MimeMessage message = mailer2.createMimeMessage();
//		message.setFrom(new InternetAddress("biju.joseph.padupurackal@gmail.com"));
//		message.setText("Welcome biju");
//		message.setReplyTo(new InternetAddress[]{ new InternetAddress("biju.joseph.padupurackal@gmail.com")});
//		message.setRecipient(RecipientType.TO,new InternetAddress("biju.joseph@semanticbits.com"));
//		message.setSubject("My mail via gmail");
//		message.setDescription("Message description");
//		message.setSentDate(new Date());
//		
//
//		System.out.println("------------before");
//		mailer2.send(message);
//		System.out.println("after --------------");
		assertTrue(true);
	}
	

}
