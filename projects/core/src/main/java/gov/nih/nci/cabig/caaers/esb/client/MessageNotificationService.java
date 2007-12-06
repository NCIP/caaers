package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MessageNotificationService {
	protected Configuration configuration;
	//protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;
	//protected org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor openSessionInViewInterceptor;
	

	public void sendNotificationToReporter(String messages, String aeReportId) throws Exception {
		//get AEreport by using this id
		
		System.out.println("in send 000 .." );
		/*
		SessionFactory sf = openSessionInViewInterceptor.getSessionFactory();
		expeditedAdverseEventReportDao.setSessionFactory(sf);
		Session s = sf.openSession();
		
		System.out.println("is connected " + s.isConnected());
		//Session s = hibernateTemplate.getSessionFactory().getCurrentSession();
		//Session s = sessionFactory.getConfiguration().buildSessionFactory().getCurrentSession();
		ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer.parseInt(aeReportId));
		
		//get submitter info
		Reporter reporter = aeReport.getReporter();
		Map contact = reporter.getContactMechanisms();
		s.close();
		*/
		//get email
		//String email = contact.get(Reporter.EMAIL).toString();
		String email = "akkals@gmail.com";
		System.out.println("email  ....." + email);

//		send email .
		sendMail(configuration.get(Configuration.SMTP_ADDRESS), configuration.get(Configuration.SMTP_USER), 
				configuration.get(Configuration.SMTP_PASSWORD) , configuration.get(Configuration.SYSTEM_FROM_EMAIL),
		//sendMail("smtp.comcast.net", "", "" , "caAERS_AdEERS@semanticbits.com",
		email,messages);
		
		
	}

	private void sendMail(String mailHost, String user, String pwd, String from, String to, String messages) throws Exception {
		try {	
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			//sender.setHost("smtp.comcast.net");
			//sender.setUsername(user);
			//sender.setPassword(pwd);
			System.out.println("host .." + mailHost );
			sender.setHost(mailHost);
			MimeMessage message = sender.createMimeMessage();
			//message.setFrom(new InternetAddress(from));
			message.setSubject("Adeers Report Submission");
			message.setFrom(new InternetAddress(from));
			
//			 use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			helper.setTo(to);
			message.setText(messages);
			sender.send(message);
			
			System.out.println("sent . . ");
		} catch (Exception e ) {
			throw new Exception (" Error in sending email , please check the confiuration " + e);
		}

}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

/*
	public void setExpeditedAdverseEventReportDao(
			ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
		this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
	}


	public void setOpenSessionInViewInterceptor(
			org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor openSessionInViewInterceptor) {
		this.openSessionInViewInterceptor = openSessionInViewInterceptor;
	}
*/
	//public void setSessionFactory(AnnotationSessionFactoryBean sessionFactory) {
	//	this.sessionFactory = sessionFactory;
	//}
	
}
