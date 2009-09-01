package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportTracking;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.domain.repository.ReportRepository;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService.ReportSubmissionContext;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.utils.Tracker;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is responsible for the post submission activities (currently only AdEERS-response is assumed)
 * 
 * @author Srini
 * @author Biju Joseph 
 *       
 */
/*
 * BJ : Lacking proper test cases
 * BJ : - Changed the subject in email notification. 
 * BJ : - Reading messages from properties file. 
 * BJ : Bean to use proxy factory as mentioned in  	 https://wiki.nci.nih.gov/x/WoY1AQ
 */

public class MessageNotificationService {
    protected Configuration configuration;

    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    
    private SchedulerService schedulerService;
    
    private ReportRepository reportRepository;

    protected final Log log = LogFactory.getLog(getClass());

    private ReportDao reportDao;
    
    protected CaaersJavaMailSender caaersJavaMailSender;
    
    private MessageSource messageSource;
    
    
    private void doPostSubmitReport(ReportSubmissionContext context){
    	Report report = context.report;
    	
    	//un-schedule all the notifications
    	schedulerService.unScheduleNotification(report);
    	
    	//now update the post submission updated date on submitted adverse events. 
    	report.getAeReport().clearPostSubmissionUpdatedDate();
    	
    	//update the reported flag on the adverse events.
    	report.getAeReport().updateReportedFlagOnAdverseEvents();
    	
    	//create child reports
    	reportRepository.createChildReports(report);
    }
    
    private Set<String> getEmailList(Report r , String submitterEmail){
    	Set<String> emails = new HashSet<String>();
    	ReportVersion rv = r.getLastVersion();
        for (ReportDelivery delivery : r.getReportDeliveries()) {
            ReportDeliveryDefinition rdd = delivery.getReportDeliveryDefinition();
            if (rdd.getEndPointType().equals(ReportDeliveryDefinition.ENDPOINT_TYPE_EMAIL)) {
                String ep = delivery.getEndPoint();
                emails.add(ep);
            }
        }
        
        String[] emailAddresses = rv.getEmailAsArray();
        if (emailAddresses != null) {
            for (String email : emailAddresses) {
                emails.add(email.trim());
            }
        }
        emails.add(submitterEmail);    
        return emails;
    }
    
    @Transactional
    public void sendWithdrawNotificationToReporter(String submitterEmail, String messages,
            String aeReportId, String reportId, boolean success, String ticketNumber,
            String url,boolean communicationError) throws Exception {
    	
        
        Report r = reportDao.getById(Integer.parseInt(reportId));
        Set<String> emails = getEmailList(r,submitterEmail);
        
        
        if (success) {
        	reportRepository.withdrawReport(r);
        } else {
            r.setStatus(ReportStatus.WITHDRAW_FAILED);
            r.setSubmissionMessage(messages);

            reportDao.save(r);
        }
        
        
        String subject = "";
        String attachment = null;
        if (success) {
            subject = messageSource.getMessage("withdraw.success.subject", new Object[]{r.getLabel(), String.valueOf(r.getLastVersion().getId())}, Locale.getDefault());
            
        } else {
        	subject =  messageSource.getMessage("withdraw.failure.subject", new Object[]{r.getLabel(), String.valueOf(r.getLastVersion().getId())}, Locale.getDefault());
        	// send only to submitter incase of failure
        	emails = new HashSet<String>();
        	emails.add(submitterEmail);
        }
        
        log.debug("send email ");
        try {
        	sendMail(emails.toArray(new String[0]), subject, messages, attachment);

        } catch (Exception  e ) {

        	throw new Exception(" Error in sending email , please check the confiuration " , e);
        }
        
    }
    
    @Transactional
    public void sendNotificationToReporter(String submitterEmail, String messages,
                    String aeReportId, String reportId, boolean success, String ticketNumber,
                    String url,boolean communicationError) throws Exception {

        Report report = reportDao.getById(Integer.parseInt(reportId));
        reportDao.initialize(report.getScheduledNotifications());
        ReportVersion reportVersion = report.getLastVersion();
        
        Set<String> emails = getEmailList(report,submitterEmail);
        
        
        ReportTracking rtToUpdate = reportVersion.getLastReportTracking();
        
        boolean ableToSubmitToWS = true;
        String submissionMessage = "";
        if (communicationError) {
        	ableToSubmitToWS = false;
        	submissionMessage = messages;
        }
        


        	Tracker.logConnectionToExternalSystem(rtToUpdate, ableToSubmitToWS, submissionMessage, new Date());

        	reportDao.save(report);


        

        log.debug("Saving data into report versions table");
        if (success) {
            report.setAssignedIdentifer(ticketNumber);
            report.setSubmissionUrl(url);
            report.setSubmittedOn(new Date());
            report.setStatus(ReportStatus.COMPLETED);

            reportVersion.setAssignedIdentifer(ticketNumber);
            reportVersion.setSubmissionUrl(url);
            reportVersion.setSubmittedOn(new Date());
            reportVersion.setReportStatus(ReportStatus.COMPLETED);
            ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(report);
            doPostSubmitReport(context);

            Tracker.logSubmissionToExternalSystem(rtToUpdate, true, messages, new Date());

        } else {
            report.setSubmittedOn(new Date());
            report.setStatus(ReportStatus.FAILED);

            reportVersion.setSubmittedOn(new Date());
            reportVersion.setReportStatus(ReportStatus.FAILED);
            if (ableToSubmitToWS) {

            	Tracker.logSubmissionToExternalSystem(rtToUpdate, false, messages, new Date());

            }
            //reportTrackingDao.save(rtToUpdate);
        }
        reportVersion.setSubmissionMessage(messages);
        report.setSubmissionMessage(messages);

        reportDao.save(report);
        
        
        String subject = "";
        String attachment = null;
        if (success) {
            messages = messages + url;
            subject = messageSource.getMessage("submission.success.subject", new Object[]{report.getLabel()}, Locale.getDefault());  
            //this pdf has already been generated in AdeersReportGenerator , we are just attching here incase of successfull submission.
            String tempDir = System.getProperty("java.io.tmpdir");
            attachment = tempDir + "/expeditedAdverseEventReport-" + reportVersion.getId() + ".pdf";
        } else {
        	subject = messageSource.getMessage("submission.failure.subject", new Object[]{report.getLabel()}, Locale.getDefault());
        	// send only to submitter incase of failure
        	emails = new HashSet<String>();
        	emails.add(submitterEmail);
        }
        
        log.debug("send email ");
        try {
        	sendMail(emails.toArray(new String[0]), subject, messages, attachment);
        	String msg = "Notified to : " ;
        	for (String e:emails) {
        		msg = msg + "," + e;
        	}

        	Tracker.logEmailNotificationToSubmitter(rtToUpdate, true, msg, new Date());

        	reportDao.save(report);
        } catch (Exception  e ) {

        	Tracker.logEmailNotificationToSubmitter(rtToUpdate, false, e.getMessage(), new Date());

        	reportDao.save(report);
        	throw new Exception(" Error in sending email , please check the confiuration " , e);
        }
        
    }

	public void sendMail(String[] to, String subject, String content, String attachment) throws Exception {
		
            
		    MimeMessage message = caaersJavaMailSender.createMimeMessage();
		    message.setSubject(subject);
		    message.setFrom(new InternetAddress(configuration.get(Configuration.SYSTEM_FROM_EMAIL)));
		
		    // use the true flag to indicate you need a multipart message
		    MimeMessageHelper helper = new MimeMessageHelper(message, true);
		    helper.setTo(to);
		    helper.setText(content);
		    
			if (attachment != null) {
			    File f = new File(attachment);
			    FileSystemResource file = new FileSystemResource(f);
			    helper.addAttachment(file.getFilename(), file);
			}
		    
		    caaersJavaMailSender.send(message);

	
	 }

	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender) {
		this.caaersJavaMailSender = caaersJavaMailSender;
	}
	
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    
    public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}


	public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }


	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	


}
