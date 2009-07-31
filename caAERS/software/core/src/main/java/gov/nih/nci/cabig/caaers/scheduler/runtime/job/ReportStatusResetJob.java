package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ReportStatusResetJob extends  QuartzJobBean {
	private ReportVersionDao reportVersionDao;
	
	public void setReportVersionDao(ReportVersionDao reportVersionDao) {
		this.reportVersionDao = reportVersionDao;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Running Job");
		reportVersionDao.updateInProcessReports();
		
	}

}
