package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * This Job will send an email reminder notifaction,based on a Report.
 * 
 * @author Biju Joseph
 * 
 */
public class ReminderEmailJob extends ScheduledNotificationJobTemplate {

    @Override
    public DeliveryStatus processNotification() {
        logger.debug("\n\r\n\r\nProceeding with emailing...[ \r\n\r\n" + String.valueOf(report)
                        + "\r\n]\r\n\r\n");
        ScheduledEmailNotification scheduledNotification = (ScheduledEmailNotification) notification;
        
        SimpleMailMessage mailMsg = new SimpleMailMessage();
        mailMsg.setTo(scheduledNotification.getToAddress());
        mailMsg.setSentDate(scheduledNotification.getScheduledOn());
        mailMsg.setSubject(scheduledNotification.getSubjectLine());
        mailMsg.setText(new String(scheduledNotification.getBody()));

        try {
            JavaMailSenderImpl mailer = (JavaMailSenderImpl) applicationContext.getBean("mailer");
            mailer.send(mailMsg);
            return DeliveryStatus.DELIVERED;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while trying to email", ex);
        }
        return DeliveryStatus.ERROR;
    }
}
