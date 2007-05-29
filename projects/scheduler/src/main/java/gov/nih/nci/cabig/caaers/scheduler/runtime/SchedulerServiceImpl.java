package gov.nih.nci.cabig.caaers.scheduler.runtime;

import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;
import gov.nih.nci.cabig.caaers.scheduler.runtime.job.ReminderEmailJob;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 29, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class SchedulerServiceImpl implements SchedulerService {
	
	private static final Log logger = LogFactory.getLog(SchedulerServiceImpl.class);
	Scheduler scheduler;
	
	public SchedulerServiceImpl(){
		
	}
	/**
	 * Start the scheduler
	 */
	private void startScheduler(){
		try {
			if(!scheduler.isStarted())
				scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Shutdown the scheduler
	 */
	private void shutdownScheduler(){
		try {
			if(scheduler.isShutdown())
				scheduler.shutdown(false);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will scheduled the notifications, based on the ReportSchedule.type.
	 * 
	 */
	public void scheduleNotification(ReportSchedule reportSchedule){
		
		if(logger.isDebugEnabled()){
			logger.debug("ReportSchedule : (" + reportSchedule.getId() + ")" + String.valueOf(reportSchedule));
			logger.debug("In scheduleNotificaton:");
			logReportSchedule(reportSchedule);
		}
		//for each notification creat job detail, and associate with the scheduler
		List<ScheduledNotification> notifications = reportSchedule.getScheduledNotifications();
		int i = 1;
		for(ScheduledNotification nf: notifications){
			
			//create a trigger
			Trigger trigger = makeTrigger(nf);
			
			//create the JobDetail and associate it with the scheduler
			String jobName = "J-" + ((nf.getId() == null)? (System.currentTimeMillis() % 1000000) : nf.getId().toString());
			JobDetail jobDetail = new JobDetail(jobName,
					"JG-" + String.valueOf(reportSchedule.getId()),
					ReminderEmailJob.class);
			
			//set all the necessary values in job data map.
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			jobDataMap.put("report.schedule", reportSchedule);
			jobDataMap.put("email.notification",nf);
			jobDataMap.put("i", i);
			
			i++;
			try {
				if(logger.isInfoEnabled()){
					logger.info("Scheduling the job(Notificaiton ID:" + nf.getId() + 
							    ", jobFullName : "+ jobDetail.getFullName() + ")");
					logger.info("JobDetails [" + jobDetail.getFullName() + "]");
				}
				
				//schedule the job
				//if(false)
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				logger.warn("Exception while scheduling " +
						"[jobDetails :" + String.valueOf(jobDetail) +
						", trigger :" + String.valueOf(trigger) + 
						", EmailNotificaiton {" + String.valueOf(nf) +"}]", e);
			}
			
		}//for nf
		
	}
	
	/**
	 * This function makes a triggerr based on EmailNotificaiton.scheduledOn value
	 * @param nf - A ScheduledNotification
	 * @return - A trigger.
	 */
	private Trigger makeTrigger(ScheduledNotification nf){
		String strId = "" + ((nf.getId() == null)? (System.currentTimeMillis() % 1000000) : nf.getId().toString());
		Trigger t = TriggerUtils.makeMinutelyTrigger("T-"+ strId, 1, 0);
		t.setGroup("TG-" + strId);
		t.setStartTime(nf.getScheduledOn());
		return t;
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	public void logReportSchedule(ReportSchedule s){
		StringBuffer sb = new StringBuffer();
		sb.append(s.getId()).append("type:").append(String.valueOf(s.getName()));
		sb.append("emailNotifications:[").append(String.valueOf(s.getScheduledNotifications())).append("]");
		logger.debug("ReportSchedule :[" + sb.toString()+"]");
	}
	
	
}
