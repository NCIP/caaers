package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

/**
 * This Job will send an email reminder notifaction,based on a Report.
 * @author Biju Joseph
 *
 */
public class ReminderEmailJob extends ScheduledNotificationJobTemplate {
	
	@Override
	public void processNotification() {
		logger.debug("\n\r\n\r\nProceeding with emailing...[ \r\n\r\n" + String.valueOf(report) +"\r\n]\r\n\r\n");
		
	}
}
