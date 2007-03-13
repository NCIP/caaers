package gov.nih.nci.cabig.caaers.email;

import junit.framework.TestCase;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class EmailServiceTest extends TestCase {

	private static final String DEFAULT_SMTP_HOST = "smtp.east.cox.net";
	
	private EmailService emailService;
	
	private SmtpConfig smtpConfig;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.emailService = new EmailServiceImpl();
		smtpConfig = new SmtpConfig();
		smtpConfig.setHost(DEFAULT_SMTP_HOST);
		smtpConfig.setUser("vtsujith@yahoo.co.uk");
		smtpConfig.setAuth("true");
	}

	public void testSendSMTPMAil() {
		EmailInfo emailInfo = new EmailInfo();

		emailInfo.setFrom("vtsujith@yahoo.co.uk");
		emailInfo.getTo().add("vtsujith@yahoo.co.uk");
		emailInfo.setSubject("Test Mail");
		emailInfo.setContent("There is not much content...");

		try {
			this.emailService.send(emailInfo, smtpConfig);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}