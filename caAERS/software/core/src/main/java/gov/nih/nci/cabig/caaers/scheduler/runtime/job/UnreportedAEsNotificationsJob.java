/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.service.ScheduledNotificationProcessService;
import gov.nih.nci.cabig.caaers.service.UnreportedSAENotificationProcessService;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;

/**
 * This Job will send unreported SAE notifications based on the recommended reports of SAE evaluation .
 * 
 * @author Ramakrishna Gundala
 * 
 */
@SuppressWarnings("serial")
public class UnreportedAEsNotificationsJob implements Job, Serializable {

	protected static final Log logger = LogFactory.getLog(UnreportedAEsNotificationsJob.class);
	/**
	 * This job will retrieve the scheduled notification details from context and delegates the call to {@link ScheduledNotificationProcessService}
	 */
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notifications Job.... [started]");
		
		try {
			Scheduler scheduler = jobContext.getScheduler();
			ApplicationContext applicationContext = (ApplicationContext) scheduler.getContext().get("applicationContext");
			
			UnreportedSAENotificationProcessService unreportedSAENotificationProcessService = 
					(UnreportedSAENotificationProcessService) applicationContext.getBean("unreportedSAENotificationProcessService");
			unreportedSAENotificationProcessService.process();
			
			if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notifications Job.... [ended]");
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notification Job.... [finished]");
	}

}
