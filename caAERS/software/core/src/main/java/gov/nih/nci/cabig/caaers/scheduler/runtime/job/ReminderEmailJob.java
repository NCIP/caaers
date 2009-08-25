package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.service.ScheduledNotificationProcessService;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

/**
 * This Job will send an email reminder notification, based on a Report.
 * 
 * @author Biju Joseph
 * 
 */
@SuppressWarnings("serial")
public class ReminderEmailJob implements Job, Serializable {

	protected static final Log logger = LogFactory.getLog(ReminderEmailJob.class);
	
	/**
	 * This job will retrieve the scheduled notification details from context and delegates the call to {@link ScheduledNotificationProcessService}
	 */
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		if(logger.isDebugEnabled()) logger.debug("Processing Reminder Email Job.... [started]");
		
		try {
			Scheduler scheduler = jobContext.getScheduler();
			JobDetail jobDetail = jobContext.getJobDetail();
			ApplicationContext applicationContext = (ApplicationContext) scheduler.getContext().get("applicationContext");
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			Integer scheduledNFId = jobDataMap.getInt("scheduledNotifiction.id");
			Integer reportId = jobDataMap.getInt("report.id");
			
			ScheduledNotificationProcessService scheduledNotificationProcessService = (ScheduledNotificationProcessService)applicationContext.getBean("scheduledNotificationProcessService");
			scheduledNotificationProcessService.process(reportId, scheduledNFId);
			
		} catch (SchedulerException e) {
			logger.error(e);
		}
		
		if(logger.isDebugEnabled()) logger.debug("Processing Reminder Email Job.... [finished]");
	}

}
