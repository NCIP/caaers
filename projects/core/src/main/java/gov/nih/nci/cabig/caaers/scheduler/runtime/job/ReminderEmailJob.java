package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/**
 * This Job will send an email reminder notifaction,based on a Report.
 * @author Biju Joseph
 *
 */
public class ReminderEmailJob extends ScheduledNotificationJobTemplate {

	@Override
	public DeliveryStatus processNotification() {
		logger.debug("\n\r\n\r\nProceeding with emailing...[ \r\n\r\n" + String.valueOf(report) +"\r\n]\r\n\r\n");
		ScheduledEmailNotification sen = (ScheduledEmailNotification) notification;
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(sen.getToAddress());
		msg.setFrom(configuration.get(Configuration.SYSTEM_FROM_EMAIL));
		msg.setReplyTo(configuration.get(Configuration.SYSTEM_FROM_EMAIL));
		msg.setSentDate(sen.getScheduledOn());
		msg.setSubject(sen.getSubjectLine());
		msg.setText(new String(sen.getBody()));

		try{
			JavaMailSenderImpl mailer = (JavaMailSenderImpl)applicationContext.getBean("mailer");
			String username = configuration.get(Configuration.SMTP_USER);
			if(StringUtils.isNotEmpty(username)) mailer.setUsername(username);

            mailer.send(msg);
            return DeliveryStatus.DELIVERED;
        }catch(MailException ex) {
            logger.error(ex);
        }catch(RuntimeException ex){
        	logger.error(ex);
        }
        return DeliveryStatus.ERROR;
	}
}
