package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.service.ScheduledNotificationProcessService;

import org.easymock.classextension.EasyMock;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

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

	protected ReminderEmailJob job;
	
	ScheduledNotificationProcessService service;
	ApplicationContext appContext;
	

	protected void setUp() throws Exception {
		super.setUp();
		scheduler = registerMockFor(Scheduler.class);
		schedulerContext = registerMockFor(SchedulerContext.class);
		jobDetail = registerMockFor(JobDetail.class);
		jobContext = registerMockFor(JobExecutionContext.class);
		service = registerMockFor(ScheduledNotificationProcessService.class);
		appContext = registerMockFor(ApplicationContext.class);
		
		job = new ReminderEmailJob();
	}
	
	public void testExecute() throws Exception{

		EasyMock.expect(jobContext.getScheduler()).andReturn(scheduler);
		EasyMock.expect(jobContext.getJobDetail()).andReturn(jobDetail);
		EasyMock.expect(scheduler.getContext()).andReturn(schedulerContext);
		EasyMock.expect(schedulerContext.get("applicationContext")).andReturn(appContext);
		EasyMock.expect(appContext.getBean("scheduledNotificationProcessService")).andReturn(service);
		
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("report.id", new Integer(5));
		jobDataMap.put("scheduledNotifiction.id", new Integer(6));
		EasyMock.expect(jobDetail.getJobDataMap()).andReturn(jobDataMap);
		service.process(5, 6);
		replayMocks();
		job.execute(jobContext);
		verifyMocks();
	}
	
	public void testExecute_throwingSchedulerException() throws Exception{
		EasyMock.expect(jobContext.getJobDetail()).andReturn(jobDetail);
		EasyMock.expect(jobContext.getScheduler()).andReturn(scheduler);
		EasyMock.expect(scheduler.getContext()).andThrow(new SchedulerException());
		replayMocks();
		job.execute(jobContext);
		verifyMocks();
	}


}
