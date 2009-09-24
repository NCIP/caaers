package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;

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
public class ReportStatusResetJobTest extends AbstractTestCase {

	JobExecutionContext jobContext;
	Scheduler scheduler;
	ApplicationContext appContext;
	SchedulerContext schedulerContext;
	ReportVersionRepository repository;
	
	ReportStatusResetJob job;
	
	protected void setUp() throws Exception {
		super.setUp();
		jobContext = registerMockFor(JobExecutionContext.class);
		scheduler = registerMockFor(Scheduler.class);
		appContext = registerMockFor(ApplicationContext.class);
		schedulerContext = registerMockFor(SchedulerContext.class);
		repository = registerMockFor(ReportVersionRepository.class);
		
		job = new ReportStatusResetJob();
	}

	public void testExecute() throws Exception{
		EasyMock.expect(jobContext.getScheduler()).andReturn(scheduler);
		EasyMock.expect(scheduler.getContext()).andReturn(schedulerContext);
		EasyMock.expect(schedulerContext.get("applicationContext")).andReturn(appContext);
		EasyMock.expect(appContext.getBean("reportVersionRepository")).andReturn(repository);
		repository.updateInProcessReports();
		replayMocks();
		job.execute(jobContext);
		verifyMocks();
	}
	
	public void testExecute_ThrowingException() throws Exception{
		job.execute(null);
	}

}
