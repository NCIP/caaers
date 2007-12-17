package gov.nih.nci.cabig.caaers.rules.runtime.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import gov.nih.nci.cabig.caaers.email.EmailInfo;
import gov.nih.nci.cabig.caaers.email.EmailServiceImpl;
import gov.nih.nci.cabig.caaers.email.SmtpConfig;
import gov.nih.nci.cabig.caaers.rules.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.Notification;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleContext;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class DefaultEmailNotificationHandler implements NotificationHandler {
	private static final Log log = LogFactory.getLog(DefaultEmailNotificationHandler.class);
	private SmtpConfig smtpConfig;
	
	private EmailInfo emailInfo;
	
	private ApplicationContext applicationContext;
	
	public void performNotify(ActionContext actionContext, RuleContext ruleContext) {
		log.info("Going to invoke the Notification Service");
		
		try {
			testSendSMTPMAil(Integer.valueOf(actionContext.getAction().getActionId()), ruleContext);
		} catch (Exception e) {
			log.error("Notification failed -- Exception " + e.getMessage(), e); 
		}
		/**
		 * 1. Where is the Email Service?
		 * 2. What is the Email Content?
		 * 3. 
		 * 4. To whom all email needs to be send?
		 * 5. How many times the email needs to be send?
		 * */
		
	}
	
	public void testSendSMTPMAil(Integer actionId, RuleContext ruleContext) {
		EmailServiceImpl emailService = new EmailServiceImpl();
		this.applicationContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:config/spring/applicationContext-dao.xml" });		
		NotificationDao notificationDao = (NotificationDao)applicationContext.getBean("NotificationDao");
		
		Notification notification = notificationDao.getById(actionId);
		emailInfo.setContent(notification.getContent());
		
		
		if(actionId.equals(1)) {
			System.out.println("***********************************************************************");
			System.out.print("Going to SEND EMAIL NOTIFICATION -  5 DAY REPORT ");
			System.out.println("***********************************************************************");
			emailInfo = getFiveDayMail(ruleContext);
		} else if(actionId.equals(2)) {
			System.out.println("***********************************************************************");
			System.out.print("Going to SEND EMAIL NOTIFICATION -  10 DAY REPORT ");
			System.out.println("***********************************************************************");
			emailInfo = getTenDayMail(ruleContext);
		}

		try {
			if(emailInfo != null)
				emailService.send(emailInfo, smtpConfig);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private EmailInfo getFiveDayMail(RuleContext ruleContext) {
		List inputObjects = ruleContext.getInputObjects();
		AdverseEventSDO adverseEventSDO = null;
		for(int i = 0; i < inputObjects.size(); i++) {
			Object obj = inputObjects.get(i);
			if(obj instanceof AdverseEventSDO) {
				adverseEventSDO = (AdverseEventSDO) obj;
				break;
			}
		}
		emailInfo.setSubject("Adverse Event --- Mail from Rule Engine - 5 Day Report");
		String content = "Sample Content of the Email ... This is a test mail from Rule engine....The real content needs to be read from DB...\n ";
		if(adverseEventSDO != null) {
			content = content + "Adverse Event Category ==== " + adverseEventSDO.getCategory() + "\n";
			content = content + "Adverse Event Term ==== " + adverseEventSDO.getTerm() + "\n";
			content = content + "Adverse Event Phase ==== " + adverseEventSDO.getPhase() + "\n";
			content = content + "Adverse Event Hospitalization ==== " + adverseEventSDO.getHospitalization() + "\n";
		}
		emailInfo.setContent(content);
		return emailInfo;
	}

	private EmailInfo getTenDayMail(RuleContext ruleContext) {
		List inputObjects = ruleContext.getInputObjects();
		AdverseEventSDO adverseEventSDO = null;
		for(int i = 0; i < inputObjects.size(); i++) {
			Object obj = inputObjects.get(i);
			if(obj instanceof AdverseEventSDO) {
				adverseEventSDO = (AdverseEventSDO) obj;
				break;
			}
		}
		
		String content = "Sample Content of the Email ... This is a test mail from Rule engine....The real content needs to be read from DB...\n ";
		if(adverseEventSDO != null)
			content = content + "Adverse Event Category ==== " + adverseEventSDO.getCategory();
		emailInfo.setContent(content);		
		return emailInfo;
	}

	public SmtpConfig getSmtpConfig() {
		return smtpConfig;
	}

	public void setSmtpConfig(SmtpConfig smtpConfig) {
		this.smtpConfig = smtpConfig;
	}

	public EmailInfo getEmailInfo() {
		return emailInfo;
	}

	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}
}