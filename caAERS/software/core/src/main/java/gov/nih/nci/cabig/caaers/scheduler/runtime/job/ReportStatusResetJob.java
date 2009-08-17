package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import java.io.Serializable;

import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ReportStatusResetJob implements Job, Serializable {
	private ReportVersionRepository reportVersionRepository;
	


	public void setReportVersionRepository(
			ReportVersionRepository reportVersionRepository) {
		this.reportVersionRepository = reportVersionRepository;
	}

  

	public void execute(JobExecutionContext job) throws JobExecutionException {
		System.out.println("Running Job");
		reportVersionRepository.updateInProcessReports();
		
	}

}
