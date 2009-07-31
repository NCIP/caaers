package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ReportStatusResetJob extends  QuartzJobBean {
	private ReportVersionRepository reportVersionRepository;
	


	public void setReportVersionRepository(
			ReportVersionRepository reportVersionRepository) {
		this.reportVersionRepository = reportVersionRepository;
	}



	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Running Job");
		reportVersionRepository.updateInProcessReports();
		
	}

}
