package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import static org.easymock.classextension.EasyMock.*;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReminderEmailJobTest extends AbstractTestCase {
	   protected Scheduler scheduler;
	   protected SchedulerContext schedulerContext;

	    protected JobDetail jobDetail;

	    protected JobExecutionContext jobContext;

	    protected Report report;

	    protected ScheduledNotification notification;

	    protected ApplicationContext applicationContext;

	    protected ReportDao reportDao;

	    protected Configuration configuration;

	    protected ScheduledNotificationDao notificationDao;
	    
	    protected JavaMailSenderImpl mailer;
	    
	    
	    protected ReminderEmailJob job;
	    
	protected void setUp() throws Exception {
		super.setUp();
		scheduler = registerMockFor(Scheduler.class);
		schedulerContext = registerMockFor(SchedulerContext.class);
		jobDetail = registerMockFor(JobDetail.class);
		jobContext = registerMockFor(JobExecutionContext.class);
		notification = Fixtures.createScheduledEmailNotification();
		applicationContext = registerMockFor(ApplicationContext.class);
		configuration = registerMockFor(Configuration.class);
		reportDao = registerDaoMockFor(ReportDao.class);
		notificationDao = registerDaoMockFor(ScheduledNotificationDao.class);
		mailer = registerMockFor(JavaMailSenderImpl.class);
		
		report = new Report();
		report.setStatus(ReportStatus.PENDING);
		
		job = new ReminderEmailJob();
		job.setConfiguration(configuration);
		job.setNotificationDao(notificationDao);
		job.setApplicationContext(applicationContext);
		job.setNotification(notification);
		job.setReportSchedule(report);
		
	}

	public void testProcessNotification() {
		expect(applicationContext.getBean("mailer")).andReturn(mailer);
		mailer.send((SimpleMailMessage) anyObject());
		replayMocks();
		DeliveryStatus status = job.processNotification();
		assertEquals(DeliveryStatus.DELIVERED, status);
		verifyMocks();
	}
	
	public void testProcessNotification_ThrowingException() {
		expect(applicationContext.getBean("mailer")).andReturn(null);
		replayMocks();
		DeliveryStatus status = job.processNotification();
		assertEquals(DeliveryStatus.ERROR, status);
		verifyMocks();
	}

	public void testExecute() throws Exception {
		expect(jobContext.getScheduler()).andReturn(scheduler);
		expect(jobContext.getJobDetail()).andReturn(jobDetail);
		expect(scheduler.getContext()).andReturn(schedulerContext);
		expect(schedulerContext.get("applicationContext")).andReturn(applicationContext);
		expect(applicationContext.getBean("reportDao")).andReturn(reportDao);
		expect(applicationContext.getBean("scheduledNotificationDao")).andReturn(notificationDao);
		expect(applicationContext.getBean("configuration")).andReturn(configuration);
		
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("scheduledNotifiction.id", -332);
		jobDataMap.put("report.id", 6);
		expect(jobDetail.getJobDataMap()).andReturn(jobDataMap);
		Report report = Fixtures.createReport("abcd");
		report.setStatus(ReportStatus.PENDING);
		expect(reportDao.getInitializedReportById(6)).andReturn(report);
		expect(applicationContext.getBean("mailer")).andReturn(mailer);
		mailer.send((SimpleMailMessage) anyObject());
		ScheduledNotification notification = report.fetchScheduledNotification(-332);
		notificationDao.updateDeliveryStatus(notification, 
				notification.getDeliveryStatus(), DeliveryStatus.DELIVERED);
		replayMocks();
		
		job.execute(jobContext);
		
		verifyMocks();
	}
	
	public void testExecute_WithDeletionOfJobs() throws Exception{
		expect(jobContext.getScheduler()).andReturn(scheduler);
		expect(jobContext.getJobDetail()).andReturn(jobDetail);
		expect(scheduler.getContext()).andReturn(schedulerContext);
		expect(schedulerContext.get("applicationContext")).andReturn(applicationContext);
		expect(applicationContext.getBean("reportDao")).andReturn(reportDao);
		expect(applicationContext.getBean("scheduledNotificationDao")).andReturn(notificationDao);
		expect(applicationContext.getBean("configuration")).andReturn(configuration);
		
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("scheduledNotifiction.id", -332);
		jobDataMap.put("report.id", 6);
		expect(jobDetail.getJobDataMap()).andReturn(jobDataMap);
		Report report = Fixtures.createReport("abcd");
		report.setStatus(ReportStatus.INPROCESS);
		expect(reportDao.getInitializedReportById(6)).andReturn(report);
		
		String groupName = "g1";
		String jobName = "j1";
		String[] jobNames = {"j1", "j2", "j3"};
		expect(jobDetail.getGroup()).andReturn(groupName);
		expect(jobDetail.getName()).andReturn(jobName);
		expect(scheduler.getJobNames(groupName)).andReturn(jobNames);
		
		expect(scheduler.deleteJob( "j2", groupName)).andReturn(true);
		expect(scheduler.deleteJob("j3", groupName)).andReturn(true);
		
		notificationDao.recallScheduledNotifications(report.getScheduledNotifications());
		replayMocks();
		job.execute(jobContext);
		verifyMocks();
	}
	
	
	public void testExecute_ExceptionThrowing() throws Exception{
		replayMocks();
		job.execute(null);
		verifyMocks();
	}


	public void testVerifyAeReportStatus() {
		
		assertTrue(job.verifyAeReportStatus());
		report.setStatus(ReportStatus.COMPLETED);
		assertFalse(job.verifyAeReportStatus());
	}

	public void testDeleteSubsequentJobs() throws Exception{
		
		String groupName = "g1";
		String jobName = "j1";
		String[] jobNames = {"j1", "j2", "j3"};
		
		job.setJobDetail(jobDetail);
		job.setScheduler(scheduler);
		
		expect(jobDetail.getGroup()).andReturn(groupName);
		expect(jobDetail.getName()).andReturn(jobName);
		expect(scheduler.getJobNames(groupName)).andReturn(jobNames);
		
		expect(scheduler.deleteJob( "j2", groupName)).andReturn(true);
		expect(scheduler.deleteJob("j3", groupName)).andReturn(true);
		
		replayMocks();
		job.deleteSubsequentJobs();
		verifyMocks();
	}

}
