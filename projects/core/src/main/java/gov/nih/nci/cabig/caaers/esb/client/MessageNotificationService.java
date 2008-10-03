package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.service.SchedulerService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

public class MessageNotificationService {
    protected Configuration configuration;

    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    protected OpenSessionInViewInterceptor openSessionInViewInterceptor;
    
    private SchedulerService schedulerService;

    protected final Log log = LogFactory.getLog(getClass());

    private ReportDao reportDao;
    
	public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    private WebRequest preProcess() {

        WebRequest stubWebRequest = new StubWebRequest();
        openSessionInViewInterceptor.preHandle(stubWebRequest);
        return stubWebRequest;
    }

    private void postProcess(WebRequest stubWebRequest) {
        openSessionInViewInterceptor.afterCompletion(stubWebRequest, null);
    }

    public void sendNotificationToReporter(String submitterEmail, String messages,
                    String aeReportId, String reportId, boolean success, String ticketNumber,
                    String url) throws Exception {
        // get AEreport by using this id

        WebRequest stubWebRequest = preProcess();
      //  ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer
        //                .parseInt(aeReportId));

        Report r = reportDao.getById(Integer.parseInt(reportId));
        reportDao.initialize(r.getScheduledNotifications());
        ReportVersion rv = r.getLastVersion();
        
        
        //
        List<String> emails = new ArrayList<String>();
        
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
        // get submitter info
        /*
         * Reporter reporter = aeReport.getReporter(); Map contact =
         * reporter.getContactMechanisms();
         * 
         * 
         * //get email String email = contact.get(Reporter.EMAIL).toString();
         */

        postProcess(stubWebRequest);

        log.debug("Saving data into report versions table");
        if (success) {
            r.setAssignedIdentifer(ticketNumber);
            r.setSubmissionUrl(url);
            r.setSubmittedOn(new Date());
            r.setStatus(ReportStatus.COMPLETED);

            rv.setAssignedIdentifer(ticketNumber);
            rv.setSubmissionUrl(url);
            rv.setSubmittedOn(new Date());
            rv.setReportStatus(ReportStatus.COMPLETED);
            
            // Unschedule all the notifications, once the report is submitted successfully.
            schedulerService.unScheduleNotification(r);

        } else {
            r.setSubmittedOn(new Date());
            r.setStatus(ReportStatus.FAILED);

            rv.setSubmittedOn(new Date());
            rv.setReportStatus(ReportStatus.FAILED);
        }
        rv.setSubmissionMessage(messages);
        r.setSubmissionMessage(messages);
        reportDao.save(r);
        
        String subject = "";
        String attachment = null;
        if (success) {
            messages = messages + url;
            subject = "Submission of Expedited Report(" + aeReportId + ") to AdEERS";  
            //this pdf has already been generated in AdeersReportGenerator , we are just attching here incase of successfull submission.
            String tempDir = System.getProperty("java.io.tmpdir");
            attachment = tempDir + "/expeditedAdverseEventReport-" + aeReportId + ".pdf";
        } else {
        	subject = "Problem with Submission of Expedited Report(" + aeReportId + ") to AdEERS";
        	// send only to submitter incase of failure
        	emails = new ArrayList<String>();
        	emails.add(submitterEmail);
        }
        
        log.debug("send email ");
        sendMail(emails.toArray(new String[0]), subject, messages, attachment);
    }

	public void sendMail(String[] to, String subject, String content, String attachment) throws Exception {
		
		try {		
			JavaMailSenderImpl caaersJavaMailSender = new JavaMailSenderImpl();
			caaersJavaMailSender.setUsername(configuration.get(Configuration.SMTP_USER));
			caaersJavaMailSender.setPassword(configuration.get(Configuration.SMTP_PASSWORD));
            //System.out.println("host .." + mailHost);
            caaersJavaMailSender.setHost(configuration.get(Configuration.SMTP_ADDRESS));
            
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
		    
		} catch (Exception e) {
		    throw new Exception(" Error in sending email , please check the confiuration " , e);
		}
	
	 }
	
/*
    private void sendMail(String mailHost, String user, String pwd, String from, String[] to,
                    String messages, boolean success, String aeReportId) throws Exception {
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            // sender.setHost("smtp.comcast.net");
            sender.setUsername(user);
            sender.setPassword(pwd);
            System.out.println("host .." + mailHost);
            sender.setHost(mailHost);
            MimeMessage message = sender.createMimeMessage();
            // message.setFrom(new InternetAddress(from));
            if (success) {
                message.setSubject("Submission of Expedited Report(" + aeReportId + ") to AdEERS");
            } else {
                message.setSubject("Problem with Submission of Expedited Report(" + aeReportId
                                + ") to AdEERS");
            }
            message.setFrom(new InternetAddress(from));

            // use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setTo(to);
            message.setText(messages);
            sender.send(message);

            System.out.println("sent . . ");
        } catch (Exception e) {
            throw new Exception(" Error in sending email , please check the confiuration " + e);
        }

    }
*/
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setExpeditedAdverseEventReportDao(
                    ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao) {
        this.expeditedAdverseEventReportDao = expeditedAdverseEventReportDao;
    }

    public void setOpenSessionInViewInterceptor(
                    org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor openSessionInViewInterceptor) {
        this.openSessionInViewInterceptor = openSessionInViewInterceptor;
    }
    
    public void setSchedulerService(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

    private static class StubWebRequest implements WebRequest {
        public String getParameter(final String paramName) {
            return null;
        }

        public String[] getParameterValues(final String paramName) {
            return null;
        }

        public Map getParameterMap() {
            return Collections.emptyMap();
        }

        public Locale getLocale() {
            return null;
        }

        public Object getAttribute(final String name, final int scope) {
            return null;
        }

        public void setAttribute(final String name, final Object value, final int scope) {
        }

        public void removeAttribute(final String name, final int scope) {
        }

        public void registerDestructionCallback(final String name, final Runnable callback,
                        final int scope) {
        }

        public String getSessionId() {
            return null;
        }

        public Object getSessionMutex() {
            return null;
        }
    }

}
