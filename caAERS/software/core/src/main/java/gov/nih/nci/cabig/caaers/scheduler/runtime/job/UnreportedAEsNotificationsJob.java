/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.scheduler.runtime.job;

import gov.nih.nci.cabig.caaers.dao.AdverseEventRecommendedReportDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEventRecommendedReport;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.service.FreeMarkerService;
import gov.nih.nci.cabig.caaers.service.ScheduledNotificationProcessService;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;

/**
 * This Job will send an email reminder notification, based on a Report.
 * 
 * @author Ramakrishna Gundala
 * 
 */
@SuppressWarnings("serial")
public class UnreportedAEsNotificationsJob implements Job, Serializable {

	protected static final Log logger = LogFactory.getLog(UnreportedAEsNotificationsJob.class);
	private FreeMarkerService freeMarkerService;
	private CaaersJavaMailSender caaersJavaMailSender;
	private AdverseEventRecommendedReportDao adverseEventRecommendedReportDao;
	
	/**
	 * This job will retrieve the scheduled notification details from context and delegates the call to {@link ScheduledNotificationProcessService}
	 */
	public void execute(JobExecutionContext jobContext) throws JobExecutionException {
		if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notifications Job.... [started]");
		
		try {
			Scheduler scheduler = jobContext.getScheduler();
			ApplicationContext applicationContext = (ApplicationContext) scheduler.getContext().get("applicationContext");
			
			FreeMarkerService freeMarkerService = (FreeMarkerService)applicationContext.getBean("freeMarkerService");
			CaaersJavaMailSender caaersJavaMailSender = (CaaersJavaMailSender)applicationContext.getBean("mailer");
			AdverseEventRecommendedReportDao adverseEventRecommendedReportDao = (AdverseEventRecommendedReportDao)applicationContext.getBean("adverseEventRecommendedReportDao");
			
			List<AdverseEventRecommendedReport> unreportedAERecomeReports = adverseEventRecommendedReportDao.getAllUnreportedRecords();
			
			// get all the distinct reporting periods
			List<ReportDefinition> rds = adverseEventRecommendedReportDao.getAllRecommendedReportsNotReported();
			
			// get planned notifications of the report definitions
			for(ReportDefinition rd1 : rds){
				for(PlannedNotification plannedNotification : rd1.getUnreportedAePlannedNotification()){
						Set<String> emailAddresses = new HashSet<String>();
			            //find emails of direct recipients
			            if(CollectionUtils.isNotEmpty(plannedNotification.getContactMechanismBasedRecipients())){
			          	  for(ContactMechanismBasedRecipient recipient : plannedNotification.getContactMechanismBasedRecipients()){
			          		  String contact = recipient.getContact();
			          		  if(GenericValidator.isEmail(contact)) emailAddresses.add(contact);  
			    	      }
			            }
			            
		            	 //now process the notifications. 
		        		PlannedEmailNotification plannedemailNotification = (PlannedEmailNotification) plannedNotification;
		                String rawSubjectLine = plannedemailNotification.getSubjectLine();
		                String rawBody = plannedNotification.getNotificationBodyContent().getBody();
		                Integer dayOfNotification = plannedemailNotification.getIndexOnTimeScale();
		                List<AdverseEventRecommendedReport> aeRecomReports = adverseEventRecommendedReportDao.getAllAdverseEventsGivenReportDefinition(rd1);
		                for(AdverseEventRecommendedReport aeRecomReport : aeRecomReports){
		                	if(aeRecomReport.getAdverseEvent().getGradedDate() != null){
		                		long daysPassedSinceGradedDate = DateUtils.differenceInDays(new Date(),aeRecomReport.getAdverseEvent().getGradedDate());
		                		if(daysPassedSinceGradedDate != dayOfNotification){
		                			continue;
		                		}
		                	}
		                	// add adverse event reporter email for email notification
		                	if(aeRecomReport.getAdverseEvent().getReporterEmail() != null){
		                		emailAddresses.add(aeRecomReport.getAdverseEvent().getReporterEmail());
		                	}
		                	// get the graded date and compare with the day of notification to check if notificatino is configured on this day
		                	
		                	
		                	  Map<Object, Object> contextVariableMap = aeRecomReport.getAdverseEvent().getContextVariables();
				                //get the AE reporting deadline
				                contextVariableMap.put("aeReportingDeadline", aeRecomReport.getDueDate().toString());
				                
				                //apply the replacements. 
				                String subjectLine = freeMarkerService.applyRuntimeReplacementsForReport(rawSubjectLine, contextVariableMap);
				                String body = freeMarkerService.applyRuntimeReplacementsForReport(rawBody, contextVariableMap);
				                
				                //create the message
				                SimpleMailMessage mailMsg = new SimpleMailMessage();
				                mailMsg.setSentDate(new Date());
				                mailMsg.setSubject(subjectLine);
				                mailMsg.setText(body);
				                
				                //send email to each recipient
				                for(String email : emailAddresses){
				                	mailMsg.setTo(email);
				                	
				                	try{
				                		caaersJavaMailSender.send(mailMsg);
				                	}catch(Exception e){
				                		//no need to throw and rollback
				                		logger.warn("Error while emailing to [" + email + "]", e);
				                	}
				                }
		                }
		                
				}
			
			}
				  
			
			if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notifications Job.... [ended]");
			
		} catch (Exception e) {
			logger.error(e);
		}
		
		if(logger.isDebugEnabled()) logger.debug("Processing Unreported AEs Notification Job.... [finished]");
	}

}
