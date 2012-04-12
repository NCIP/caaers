package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.service.SafetyMonitoringService;

import org.easymock.classextension.EasyMock;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;
/**
 * 
 * @author Biju Joseph
 *
 */
public class SafetySignallingReportJobTest extends AbstractTestCase {

	JobExecutionContext jobContext;
	Scheduler scheduler;
	ApplicationContext appContext;
	SchedulerContext schedulerContext;
	SafetyMonitoringService safetyMonitoringService;
	
	SafetySignallingReportJob job;
	
	protected void setUp() throws Exception {
		super.setUp();
		jobContext = registerMockFor(JobExecutionContext.class);
		scheduler = registerMockFor(Scheduler.class);
		appContext = registerMockFor(ApplicationContext.class);
		schedulerContext = registerMockFor(SchedulerContext.class);
		safetyMonitoringService = registerMockFor(SafetyMonitoringService.class);
		
		job = new SafetySignallingReportJob();
	}

	public void testExecute() throws Exception{
		EasyMock.expect(jobContext.getScheduler()).andReturn(scheduler);
		EasyMock.expect(scheduler.getContext()).andReturn(schedulerContext);
		EasyMock.expect(schedulerContext.get("applicationContext")).andReturn(appContext);
		EasyMock.expect(appContext.getBean("safetyMonitoringService")).andReturn(safetyMonitoringService);
		safetyMonitoringService.generateSafetyAlerts();
		replayMocks();
		job.execute(jobContext);
		verifyMocks();
	}
	
	public void testExecute_ThrowingException() throws Exception{
		job.execute(null);
	}

}
