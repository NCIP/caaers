package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReportStatusResetJob implements Job {
	private ReportVersionDao reportVersionDao;
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		reportVersionDao.updateInProcessReports();
	}

	public void setReportVersionDao(ReportVersionDao reportVersionDao) {
		this.reportVersionDao = reportVersionDao;
	}

}
