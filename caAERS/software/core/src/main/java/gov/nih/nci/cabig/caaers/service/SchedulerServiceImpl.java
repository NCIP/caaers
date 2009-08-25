package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;

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
 * This service will schedule the ScheduledNotification objects present in the Report, using Quartz
 * scheduling engine.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 29, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public class SchedulerServiceImpl implements SchedulerService {

    private static final Log logger = LogFactory.getLog(SchedulerServiceImpl.class);

    private Scheduler scheduler;

    private ReportDao reportScheduleDao;

    private Map<String, String> jobClassMapping;

    /**
     * This method will scheduled the notifications, based on the Report.
     * 
     */
    public void scheduleNotification(Report report) {

        assert report != null : "report should not be null";
        assert report.getId() != null : "report must have a valid id";
        assert report.getScheduledNotifications() != null : "report must have valid scheduled notifications";

        if (logger.isDebugEnabled()) {
            logger.debug(String.valueOf(report));
            logReportSchedule(report);
        }

        try {

            // TODO: Audit Logging.
            List<ScheduledNotification> notifications = report.getScheduledNotifications();
            int curIndex = 0;
            assert notifications.size() > 0 : "report must have atleast one valid schedulded notification";
            // for each notification creat job detail, and associate with the scheduler
            for (ScheduledNotification nf : notifications) {

            	if(nf.isActive()){
            		assert nf != null : "report must not contain invalid ScheduledNotificaiton objects";
            		assert nf.getId() != null : "report must contain ScheduledNotification object, that has valid id";
                
            		// create a trigger
            		Trigger trigger = makeTrigger(nf);

            		// create job detail and set the map values
            		String jobName = "J-" + nf.getId().toString();
            		Class jobClass = findJobClass(nf);
            		if (logger.isDebugEnabled()) logger.debug("jobClass :" + String.valueOf(jobClass));
            		JobDetail jobDetail = new JobDetail(jobName,"JG-" + String.valueOf(report.getId()), jobClass);
            		JobDataMap jobDataMap = jobDetail.getJobDataMap();
            		jobDataMap.put("report.id", report.getId());
            		jobDataMap.put("scheduledNotifiction.id", nf.getId());
            		jobDataMap.put("curIndex", curIndex);

            		// schedule the jobs
            		logger.info("Scheduling the job (jobFullName : " + jobDetail.getFullName() + ")");
            		scheduler.scheduleJob(jobDetail, trigger);

            		// update the delivery status of the ScheduledNotification
            		nf.setDeliveryStatus(DeliveryStatus.SCHEDULED);

            	}
            	curIndex++;
            }// for each nf
            reportScheduleDao.save(report);
        } catch (SchedulerException e) {
            logger.error("Exception while scheduling ", e);
            throw new CaaersSystemException("Exception while scheduling report{"
                            + String.valueOf(report) + "}", e);
        }

    }

    /**
     * Will unschedule a scheduled notification
     */
    public void unScheduleNotification(Report report) {
        assert report != null : "report should not be null";
        assert report.getId() != null : "report must have a valid id";

        try {
            // delete all the jobs configured for ScheduledNotificaiton
            String jobName;
            String groupName = "JG-" + String.valueOf(report.getId());
            boolean status = false;
            for (ScheduledNotification nf : report.getScheduledNotifications()) {
            	assert nf != null : "report must not contain invalid ScheduledNotificaiton objects";
            	assert nf.getId() != null : "report must contain ScheduledNotification object, that has valid id";
            	jobName = "J-" + nf.getId().toString();
            	status = scheduler.deleteJob(jobName, groupName);
            	if (status) nf.setDeliveryStatus(DeliveryStatus.RECALLED);
            	logger.info("Deleted job[jobName : " + jobName + ", groupName :" + groupName
            			+ "], sucessful? = " + status);
            	
            }
        } catch (SchedulerException e) {
            logger.error("Exception while unscheduling ", e);
            throw new CaaersSystemException("Exception while unscheduling report{"
                            + String.valueOf(report) + "}", e);
        }
    }

    /**
     * This function makes a triggerr based on ScheduledNotification.scheduledOn value
     * 
     * @param nf -
     *                A ScheduledNotification
     * @return - A trigger.
     */
    private Trigger makeTrigger(ScheduledNotification nf) {
        String strId = nf.getId().toString();
        Trigger t = TriggerUtils.makeMinutelyTrigger("T-" + strId, 1, 0);
        t.setGroup("TG-" + strId);
        t.setStartTime(nf.getScheduledOn());

        return t;
    }

    /**
     * This method returns the JobClass type to be used inside the scheduler. The
     * ScheduledNotificaiton - JobClass mapping is available in <code>jobClassMapping</code>,
     * which is configured in the <code>applicationContext-scheduler.xml</code>.
     * 
     * @param nf -
     *                A {@link ScheduledNotification}
     * @return - A class, that is representing the job implementation.
     */
    private Class findJobClass(ScheduledNotification nf) {
        String className = null;
        if (nf instanceof ScheduledEmailNotification) className = jobClassMapping.get(nf.getClass()
                        .getName());
        try {
            logger.debug("Loading class :" + className);
            return Class.forName(className, true, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new CaaersSystemException("Error while loading job class", e);
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setJobClassMapping(Map<String, String> map) {
        this.jobClassMapping = map;
    }

    public Map<String, String> getJobClassMapping() {
        return jobClassMapping;
    }

    /**
     * @return the reportScheduleDao
     */
    public ReportDao getReportScheduleDao() {
        return reportScheduleDao;
    }

    /**
     * @param reportDao
     *                the reportScheduleDao to set
     */
    public void setReportScheduleDao(ReportDao reportDao) {
        this.reportScheduleDao = reportDao;
    }

    public void logReportSchedule(Report s) {
        StringBuffer sb = new StringBuffer();
        sb.append(s.getId()).append("type:").append(String.valueOf(s.getName()));
        sb.append("emailNotifications:[").append(String.valueOf(s.getScheduledNotifications()))
                        .append("]");
        logger.debug("Report :[" + sb.toString() + "]");
    }

}
