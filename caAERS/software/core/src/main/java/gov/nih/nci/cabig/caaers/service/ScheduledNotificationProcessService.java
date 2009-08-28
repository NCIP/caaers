package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ScheduledNotificationDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.utils.RoleUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

/**
 * The implementation here will process the {@link ScheduledNotification}
 * @author Biju Joseph
 *
 */
public class ScheduledNotificationProcessService  {
	
protected static final Log logger = LogFactory.getLog(ScheduledNotificationProcessService.class);
	
	 private ReportDao reportDao;
	 private ScheduledNotificationDao scheduledNotificationDao;
	 private FreeMarkerService freeMarkerService;
	 private Configuration configuration;
	 private CaaersJavaMailSender caaersJavaMailSender;

	/**
	 * This method will process and sends notifications associated to a
	 * scheduled notification. 
	 * 
	 *  - Will find the recipients, and their email addresses. 
	 *  - Will generate the subject/body of email
	 *  - Will send notification
	 *  - Will update notification status. 
	 * 
	 * @param reportId - A valid {@link Report#id}
	 * @param scheduledNotificationId - A valid {@link ScheduledNotification#id}
	 */
	@Transactional
	public void process(Integer reportId, Integer scheduledNotificationId) {
		  
        Report report = reportDao.getById(reportId);
        ScheduledEmailNotification scheduledNotification = (ScheduledEmailNotification) report.fetchScheduledNotification(scheduledNotificationId);
        DeliveryStatus newDeliveryStatus = DeliveryStatus.RECALLED;
        
        if(report.isActive()){
        	
        	newDeliveryStatus = DeliveryStatus.DELIVERED;
            
            PlannedEmailNotification plannedNotification = (PlannedEmailNotification) scheduledNotification.getPlanedNotificaiton();
            ExpeditedAdverseEventReport aeReport = report.getAeReport();
            StudySite studySite = aeReport.getStudySite();
            Study study = aeReport.getStudy();
            
            
            Set<String> emailAddresses = new HashSet<String>();
            //find emails of direct recipients
            List<ContactMechanismBasedRecipient> contactRecipients = plannedNotification.getContactMechanismBasedRecipients();
            if(CollectionUtils.isNotEmpty(contactRecipients)){
          	  for(ContactMechanismBasedRecipient recipient : contactRecipients){
          		  String contact = recipient.getContact();
          		  if(GenericValidator.isEmail(contact)) emailAddresses.add(contact);  
    	      }
            }
            
            //find emails of role recipients
            List<RoleBasedRecipient> roleRecipients = plannedNotification.getRoleBasedRecipients();
            if(CollectionUtils.isNotEmpty(roleRecipients)){
    	      	List<String> emails = null;
    	      	for(RoleBasedRecipient recipient : roleRecipients){
    	      		if(ArrayUtils.contains(RoleUtils.reportSpecificRoles, recipient.getRoleName())){
    	      			emails = report.findEmailAddressByRole(recipient.getRoleName());
    	      		}else if(ArrayUtils.contains(RoleUtils.studySiteSpecificRoles, recipient.getRoleName())){
    	      			emails = studySite.findEmailAddressByRole(recipient.getRoleName());
    	      		}else{
    	      			emails =  study.findEmailAddressByRole(recipient.getRoleName());
    	      		}
    	      		
    	      		//now add the valid email addresses obtained
    	      		if(CollectionUtils.isNotEmpty(emails)){
    	      			for(String email : emails){
    	      				if(GenericValidator.isEmail(email)) emailAddresses.add(email);
    	      			}
    	      		}
    	      		
    	      	}
          	  
            }
            
            if(CollectionUtils.isNotEmpty(emailAddresses)){
            	 //now process the notifications. 
                String rawSubjectLine = plannedNotification.getSubjectLine();
                String rawBody = plannedNotification.getNotificationBodyContent().getBody();
                
                Map<Object, Object> contextVariableMap = report.getContextVariables();
                //change the reportURL
                String reportURL = String.valueOf(contextVariableMap.get("reportURL"));
                contextVariableMap.put("reportURL", configuration.get(Configuration.CAAERS_BASE_URL) + reportURL);
                
                //apply the replacements. 
                String subjectLine = freeMarkerService.applyRuntimeReplacementsForReport(rawSubjectLine, contextVariableMap);
                String body = freeMarkerService.applyRuntimeReplacementsForReport(rawBody, contextVariableMap);
                
                //create the message
                SimpleMailMessage mailMsg = new SimpleMailMessage();
                mailMsg.setSentDate(scheduledNotification.getScheduledOn());
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
        
        scheduledNotificationDao.updateDeliveryStatus(scheduledNotification, scheduledNotification.getDeliveryStatus(), newDeliveryStatus);
       
	}
	
	@Required
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	@Required
	public void setFreeMarkerService(FreeMarkerService freeMarkerService) {
		this.freeMarkerService = freeMarkerService;
	}
	
	@Required
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender) {
		this.caaersJavaMailSender = caaersJavaMailSender;
	}
	
	@Required
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	@Required
	public void setScheduledNotificationDao(ScheduledNotificationDao scheduledNotificationDao) {
		this.scheduledNotificationDao = scheduledNotificationDao;
	}
}
