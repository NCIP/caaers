package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

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
 * This class serves as the parent of all the job classes scheduled by <code>scheduler</code>
 * component.
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 30, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public abstract class ScheduledNotificationJobTemplate implements Job {

    protected static final Log logger = LogFactory.getLog(ScheduledNotificationJobTemplate.class);

    protected Scheduler scheduler;

    protected JobDetail jobDetail;

    protected JobExecutionContext jobContext;

    protected Report report;

    protected ScheduledNotification notification;

    protected ApplicationContext applicationContext;

    protected ReportDao reportDao;

    protected Configuration configuration;

    protected ScheduledNotificationDao notificationDao;

    public ScheduledNotificationJobTemplate() {
        super();
    }

    public JobExecutionContext getJobContext() {
        return jobContext;
    }

    public JobDetail getJobDetail() {
        return jobDetail;
    }
    public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

    public Report getReportSchedule() {
        return report;
    }
    
    public void setReportSchedule(Report report) {
		this.report = report;
	}

    public ScheduledNotification getScheduledNotification() {
        return notification;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

    public ReportDao getReportScheduleDao() {
        return reportDao;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public ScheduledNotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(ScheduledNotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }
    
    public ScheduledNotification getNotification() {
		return notification;
	}
    public void setNotification(ScheduledNotification notification) {
		this.notification = notification;
	}
    
    public Scheduler getScheduler() {
		return scheduler;
	}
    public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
    
    /**
     * Will reload the {@link Report} identified by <code>report.id</code> and
     * {@link ScheduledNotification} identified by <code>scheduledNotification.id</code>, in
     * {@link JobDetail} data map({@link JobDataMap}). This method will then check the present
     * status of the AdverseEventReport, and if found {@link ReportStatus}.PENDING, will call the
     * <code>processNotification()</code> function, otherwise will delete all the scheduled
     * notifications and sets the ScheduledNotification status as{@link ReportStatus}.RECALL.
     */
    public final void execute(JobExecutionContext context) throws JobExecutionException {
        if (logger.isDebugEnabled()) logger.debug("Executing ScheduledNotification Job");
        try {

            // init the member variables
            scheduler = context.getScheduler();
            jobDetail = context.getJobDetail();
            applicationContext = (ApplicationContext) scheduler.getContext().get(
                            "applicationContext");
            reportDao = (ReportDao) applicationContext.getBean("reportDao");
            notificationDao = (ScheduledNotificationDao) applicationContext
                            .getBean("scheduledNotificationDao");
            configuration = (Configuration) applicationContext.getBean("configuration");

            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            Integer scheduledNFId = jobDataMap.getInt("scheduledNotifiction.id");
            Integer reportId = jobDataMap.getInt("report.id");
            report = reportDao.getInitializedReportById(reportId);
            notification = report.fetchScheduledNotification(scheduledNFId);

            boolean reportStatus = verifyAeReportStatus();
            if (reportStatus) {
                DeliveryStatus deliveryStatus = processNotification();
                logger.info("Delivery status of the notification :" + deliveryStatus);
                // update the delivery status.
                notificationDao.updateDeliveryStatus(notification,
                                notification.getDeliveryStatus(), deliveryStatus);

            } else {
                deleteSubsequentJobs();
                // mark the status of all jobs which are in SCHEDULED status to RECALLED
                notificationDao.recallScheduledNotifications(report.getScheduledNotifications());
            }

        } catch (Exception e) {
            logger.error("execution of job failed", e);
        }

    }

    /**
     * This method will return true, if the status of the Report is still pending.
     * 
     * @return true - when stautus is {@link Report}.PENDING
     * @see Report
     */
    public boolean verifyAeReportStatus() {
        return report.getStatus().equals(ReportStatus.PENDING);
    }

    /**
     * This method will delete all the jobs that are available(scheduled for later execution) in the
     * same group (associated to the same ScheduleReport).
     * 
     * @param context -
     *                The JobExecutionContext
     */
    public void deleteSubsequentJobs() {

        // delete all the open jobs in the scheduler under the same group
        String groupName = jobDetail.getGroup();
        String currentJobName = jobDetail.getName();
        String[] jobNames = new String[0];
        // fetch all the active jobs belonging to present job group
        try {
            jobNames = scheduler.getJobNames(groupName);
            // delete all jobs, other than the current job.
            for (String jobName : jobNames) {
                if (currentJobName.equals(jobName)) continue;
                if (logger.isInfoEnabled()) logger.info("Deleting job [" + jobDetail.getFullName()
                                + "]");
                // delete this job
                boolean status = scheduler.deleteJob(jobName, groupName);
                if (!status) logger.warn("Unbale to delete job [" + jobDetail.getFullName()
                                + "], status:" + status);
            }// for each jobName
        } catch (SchedulerException e) {
            logger.warn("Error while deleting job[" + jobDetail.getFullName() + "]", e);
        }

    }

    /**
     * This method is to be overriden in the subclasses,and should contain the logic for processing
     * the notification.
     */
    public abstract DeliveryStatus processNotification();
}