package gov.nih.nci.cabig.caaers.scheduler.runtime;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

/**
 * This service will schedule the ScheduledNotification objects present in 
 * the ReportSchedule, using Quartz scheduling engine. 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 29, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
public class SchedulerServiceImpl implements SchedulerService {
	
	private static final Log logger = LogFactory.getLog(SchedulerServiceImpl.class);
	private Scheduler scheduler;
	private Map<String,String> jobClassMapping;
	
	
	/**
	 * This method will scheduled the notifications, based on the ReportSchedule.
	 * 
	 */
	public void scheduleNotification(ReportSchedule reportSchedule){
		
		assert reportSchedule != null : "reportSchedule should not be null";
		assert reportSchedule.getId() != null : "reportSchedule must have a valid id";
		
		if(logger.isDebugEnabled()){
			logger.debug(String.valueOf(reportSchedule));
			logReportSchedule(reportSchedule);
		}
		
		//TODO: Audit Logging.
		List<ScheduledNotification> notifications = reportSchedule.getScheduledNotifications();
		int curIndex = 0, size = notifications.size();
		List<JobDetail> jobDetailList = new ArrayList<JobDetail>(size);
		List<Trigger> triggerList = new ArrayList<Trigger>(size);
		List<Object> nfIdList = new ArrayList<Object>(); //this list will store the id of all the scheduled notifications.
		
		//for each notification creat job detail, and associate with the scheduler
		for(ScheduledNotification nf: notifications){
			
			assert nf != null :"reportSchedule must not contain invalid ScheduledNotificaiton objects";
			assert nf.getId() != null : "reportSchedule must contain ScheduledNotification object, that has valid id";
			
			//store the notification id
			nfIdList.add(nf.getId());
			
			//create a trigger
			Trigger trigger = makeTrigger(nf);
			triggerList.add(trigger);
			
			//create job detail and set the map values
			String jobName = "J-" +  nf.getId().toString();
			Class jobClass = findJobClass(nf);
			logger.debug("jobClass :" + String.valueOf(jobClass));
			JobDetail jobDetail = new JobDetail(jobName,"JG-" + String.valueOf(reportSchedule.getId()),
					jobClass);
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			jobDataMap.putAsString("reportSchedule.id", reportSchedule.getId());
			jobDataMap.putAsString("scheduledNotifiction.id",nf.getId());
			jobDataMap.putAsString("curIndex", curIndex);
			jobDetailList.add(jobDetail);
			
			curIndex++;
		}//for each nf
		
		//convert the nfIdList, to comma seperated list of strings.
		String nfIds = StringUtils.join(nfIdList, ',');
		
		//schedule all the jobs
		for(int i = 0; i < size; i++){
			Trigger trigger = triggerList.get(i);
			JobDetail jobDetail = jobDetailList.get(i);
			jobDetail.getJobDataMap().put("scheduleNotification.all.id", nfIds);
			logger.info("Scheduling the job (jobFullName : "+ jobDetail.getFullName() + ")");
			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				logger.warn("Exception while scheduling " +
						"[jobDetails :" + String.valueOf(jobDetail) +
						", trigger :" + String.valueOf(trigger) + "]");
				throw new CaaersSystemException("Exception while scheduling report{"+ String.valueOf(reportSchedule)+"}", e);
			}
			
		}//for i
		
	}
	
	/**
	 * This function makes a triggerr based on ScheduledNotification.scheduledOn value
	 * @param nf - A ScheduledNotification
	 * @return - A trigger.
	 */
	private Trigger makeTrigger(ScheduledNotification nf){
		String strId = nf.getId().toString();
		Trigger t = TriggerUtils.makeMinutelyTrigger("T-"+ strId, 1, 0);
		t.setGroup("TG-" + strId);
		t.setStartTime(nf.getScheduledOn());
		return t;
	}
	
	/**
	 * This method returns the JobClass type to be used
	 * inside the scheduler. The ScheduledNotificaiton - JobClass mapping
	 * is available in <code>jobClassMapping</code>, which is configured in
	 * the <code>applicationContext-scheduler.xml</code>.
	 * @param nf - A {@link ScheduledNotification}
	 * @return - A class, that is representing the job implementation.
	 */
	private Class findJobClass(ScheduledNotification nf){
		String className = null;
		if(nf instanceof ScheduledEmailNotification)
			className = jobClassMapping.get(nf.getClass().getName());
		try {
			logger.debug("Loading class :" + className);
			return Class.forName(className,true, this.getClass().getClassLoader());
		} catch (ClassNotFoundException e) {
			throw new CaaersSystemException("Error while loading job class",e);
		}
	}
	
	public Scheduler getScheduler() {
		return scheduler;
	}
	
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	public void setJobClassMapping(Map map){
		this.jobClassMapping = map;
	}
	public Map getJobClassMapping(){
		return jobClassMapping;
	}
	public void logReportSchedule(ReportSchedule s){
		StringBuffer sb = new StringBuffer();
		sb.append(s.getId()).append("type:").append(String.valueOf(s.getName()));
		sb.append("emailNotifications:[").append(String.valueOf(s.getScheduledNotifications())).append("]");
		logger.debug("ReportSchedule :[" + sb.toString()+"]");
	}
	
	
}
