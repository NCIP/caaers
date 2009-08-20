package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import java.io.Serializable;

import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * Will delegate the call to {@link ReportVersionRepository#updateInProcessReports()}
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */

@SuppressWarnings("serial")
public class ReportStatusResetJob  implements Job, Serializable {
	
	protected static final Log logger = LogFactory.getLog(ReportStatusResetJob.class);

	public void execute(JobExecutionContext jobContext) {
		
		if(logger.isDebugEnabled()) logger.debug("Running ReportStatusResetJob");
		
		try {
			//fetch the scheduler
			Scheduler scheduler = jobContext.getScheduler();
			
			//fetch the applicationContext
			ApplicationContext applicationContext = (ApplicationContext) scheduler.getContext().get( "applicationContext");
			
			//fetch the report version repo bean
			ReportVersionRepository reportVersionRepository = (ReportVersionRepository) applicationContext.getBean("reportVersionRepository");
			
//			reportVersionRepository.updateInProcessReports();
			
		} catch (Exception e) {
			//no need to send exception back, the job is run periodically.
			logger.error(e);
		}
		
	}

}
