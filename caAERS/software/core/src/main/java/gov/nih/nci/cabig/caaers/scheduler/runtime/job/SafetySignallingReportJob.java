package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.audit.AuditUtils;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import gov.nih.nci.cabig.caaers.service.SafetyMonitoringService;
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
/**
 * Will delegate the call to {@link ReportVersionRepository#updateInProcessReports()}
 * @author Srini Akkala
 * @author Biju Joseph
 *
 */

@SuppressWarnings("serial")
public class SafetySignallingReportJob  implements Job, Serializable {
	
	protected static final Log logger = LogFactory.getLog(SafetySignallingReportJob.class);

	public void execute(JobExecutionContext jobContext) {
		
		logger.debug("********************************");
		if (logger.isDebugEnabled()) logger.debug("Running SafetySignallingReportJob at "+ new SimpleDateFormat("MM/dd/yyyy h:mm a").format(new Date()));
		logger.debug("********************************");
		try {

            DataAuditInfo auditInfo = AuditUtils.generateDataAuditInfo("SYSTEM", "127.0.0.1","quartz-job");
            DataAuditInfo.setLocal(auditInfo);
                    
			//fetch the scheduler
			Scheduler scheduler = jobContext.getScheduler();
			
			//fetch the applicationContext
			ApplicationContext applicationContext = (ApplicationContext) scheduler.getContext().get( "applicationContext");
			
			//fetch the report version repo bean
			SafetyMonitoringService safetyMonitoringService = (SafetyMonitoringService) applicationContext.getBean("safetyMonitoringService");
			
			safetyMonitoringService.generateSafetyAlerts();
			
		} catch (Exception e) {
			//no need to send exception back, the job is run periodically.
			logger.error(e);
		}
		
	}

}
