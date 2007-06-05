package gov.nih.nci.cabig.caaers.scheduler.runtime;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ReportScheduleDao;
import gov.nih.nci.cabig.caaers.domain.notification.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;

import java.util.List;
import java.util.Map;

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
	private ReportScheduleDao reportScheduleDao;
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
		
		try {

			//TODO: Audit Logging.
			List<ScheduledNotification> notifications = reportSchedule.getScheduledNotifications();
			int curIndex = 0;
			
			//for each notification creat job detail, and associate with the scheduler
			for(ScheduledNotification nf: notifications){
				
				assert nf != null :"reportSchedule must not contain invalid ScheduledNotificaiton objects";
				assert nf.getId() != null : "reportSchedule must contain ScheduledNotification object, that has valid id";
				
				
				//create a trigger
				Trigger trigger = makeTrigger(nf);
				
				//create job detail and set the map values
				String jobName = "J-" +  nf.getId().toString();
				Class jobClass = findJobClass(nf);
				if(logger.isDebugEnabled()) logger.debug("jobClass :" + String.valueOf(jobClass));
				JobDetail jobDetail = new JobDetail(jobName,"JG-" + String.valueOf(reportSchedule.getId()),
						jobClass);
				JobDataMap jobDataMap = jobDetail.getJobDataMap();
				jobDataMap.put("reportSchedule.id", reportSchedule.getId());
				jobDataMap.put("scheduledNotifiction.id",nf.getId());
				jobDataMap.put("curIndex", curIndex);
				
				//schedule the jobs
				logger.info("Scheduling the job (jobFullName : "+ jobDetail.getFullName() + ")");
				scheduler.scheduleJob(jobDetail, trigger);

				//update the delivery status of the ScheduledNotification
				nf.setDeliveryStatus(DeliveryStatus.SCHEDULED);
				
				curIndex++;
			}//for each nf
			reportScheduleDao.save(reportSchedule);
		} catch (SchedulerException e) {
			logger.error("Exception while scheduling " ,e);
			throw new CaaersSystemException("Exception while scheduling report{"+ String.valueOf(reportSchedule)+"}", e);
		}
		
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
	public void setJobClassMapping(Map<String, String> map){
		this.jobClassMapping = map;
	}
	public Map<String, String> getJobClassMapping(){
		return jobClassMapping;
	}
	
	/**
	 * @return the reportScheduleDao
	 */
	public ReportScheduleDao getReportScheduleDao() {
		return reportScheduleDao;
	}

	/**
	 * @param reportScheduleDao the reportScheduleDao to set
	 */
	public void setReportScheduleDao(ReportScheduleDao reportScheduleDao) {
		this.reportScheduleDao = reportScheduleDao;
	}

	public void logReportSchedule(ReportSchedule s){
		StringBuffer sb = new StringBuffer();
		sb.append(s.getId()).append("type:").append(String.valueOf(s.getName()));
		sb.append("emailNotifications:[").append(String.valueOf(s.getScheduledNotifications())).append("]");
		logger.debug("ReportSchedule :[" + sb.toString()+"]");
	}
	
	
}
