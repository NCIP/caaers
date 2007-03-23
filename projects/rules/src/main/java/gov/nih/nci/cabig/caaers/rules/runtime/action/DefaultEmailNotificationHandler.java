package gov.nih.nci.cabig.caaers.rules.runtime.action;

import gov.nih.nci.cabig.caaers.email.EmailInfo;
import gov.nih.nci.cabig.caaers.email.EmailServiceImpl;
import gov.nih.nci.cabig.caaers.email.SmtpConfig;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleContext;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class DefaultEmailNotificationHandler implements NotificationHandler {
	
	
	private static final String DEFAULT_SMTP_HOST = "smtp.1and1.com"; //"smtp.east.cox.net";

	private static final String DEFAULT_USER = "sujith.vt@semanticbits.com"; //"smtp.east.cox.net";
	
	//private static final String DEFAULT_USER = "sujith.vt"; //"smtp.east.cox.net";
	
	private String message;
	
	private String recipients;
	
	private String from;
	
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public void performNotify(ActionContext actionContext, RuleContext ruleContext) {
		System.out.print("Going to invoke the Notification Service");
		
		try {
			testSendSMTPMAil(Integer.valueOf(actionContext.getAction().getActionId()));
		} catch (Exception e) {
			System.out.println("Notification failed -- Exception " + e.getMessage()); 
		}
		/**
		 * 1. Where is the Email Service?
		 * 2. What is the Email Content?
		 * 3. 
		 * 4. To whom all email needs to be send?
		 * 5. How many times the email needs to be send?
		 * */
		
	}
	
	public void testSendSMTPMAil(Integer actionId) {
		EmailServiceImpl emailService = new EmailServiceImpl();
		SmtpConfig smtpConfig = new SmtpConfig();
		smtpConfig.setHost(DEFAULT_SMTP_HOST);
		smtpConfig.setUser(DEFAULT_USER);
		smtpConfig.setAuth("true");

		EmailInfo emailInfo = null;
		if(actionId.equals(1)) {
			
			System.out.println("***********************************************************************");
			System.out.print("Going to SEND EMAIL NOTIFICATION -  5 DAY REPORT ");
			System.out.println("***********************************************************************");
			
			emailInfo = getFiveDayMail();
		} else if(actionId.equals(2)) {
			System.out.println("***********************************************************************");
			System.out.print("Going to SEND EMAIL NOTIFICATION -  10 DAY REPORT ");
			System.out.println("***********************************************************************");
			emailInfo = getTenDayMail();
		}

		try {
			if(emailInfo != null)
				emailService.send(emailInfo, smtpConfig);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private EmailInfo getFiveDayMail() {
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setFrom(DEFAULT_USER);
		emailInfo.getTo().add(DEFAULT_USER);
		emailInfo.setSubject("Test Mail from Rule Engine - 5 Day Report");
		emailInfo.setContent("Content of the Email ... Needs to be read from DB");		
		return emailInfo;
	}

	private EmailInfo getTenDayMail() {
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setFrom(DEFAULT_USER);
		emailInfo.getTo().add("vtsujith@gmail.com");
		emailInfo.setSubject("Test Mail from Rule Engine - 10 Day Report");
		emailInfo.setContent("Content of the Email ... Needs to be read from DB");		
		return emailInfo;
	}
}