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
import gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo;
import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.hibernate3.support.OpenSessionInViewInterceptor;
import org.springframework.web.context.request.WebRequest;

public class MessageNotificationService implements ApplicationContextAware{
    protected Configuration configuration;

    protected ExpeditedAdverseEventReportDao expeditedAdverseEventReportDao;

    protected OpenSessionInViewInterceptor openSessionInViewInterceptor;
    
    private SchedulerService schedulerService;
    
    private ReportRepository reportRepository;

    protected final Log log = LogFactory.getLog(getClass());

    private ReportDao reportDao;
    
    protected CaaersJavaMailSender caaersJavaMailSender;
    
    private ApplicationContext applicationContext;
    


    
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
    
    private List<String> getEmailList(Report r , String submitterEmail){
    	List<String> emails = new ArrayList<String>();
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
    public void sendWithdrawNotificationToReporter(String submitterEmail, String messages,
            String aeReportId, String reportId, boolean success, String ticketNumber,
            String url,boolean communicationError) throws Exception {
    	
    	WebRequest stubWebRequest = preProcess();
        
		boolean authorizationOnByDefault = enableAuthorization(false);
		switchUser("SYSTEM_ADMIN", "ROLE_caaers_super_user");
        
        String info = configuration.get(Configuration.ESB_URL);
        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo.setLocal(new DataAuditInfo("SYSTEM_ADMIN",
                info, new Date(), info));   
        
        Report r = reportDao.getById(Integer.parseInt(reportId));
        List<String> emails = getEmailList(r,submitterEmail);
        
        
        if (success) {
        	reportRepository.withdrawReport(r);
        } else {
            r.setStatus(ReportStatus.WITHDRAW_FAILED);
            r.setSubmissionMessage(messages);

            reportDao.save(r);
        }
        
        postProcess(stubWebRequest);
        
        String subject = "";
        String attachment = null;
        if (success) {
            subject = "Withdraw of Expedited Report(" + r.getLastVersion().getId() + ") to AdEERS";  
            
        } else {
        	subject = "Problem with Withdraw of Expedited Report(" + r.getLastVersion().getId() + ") to AdEERS";
        	// send only to submitter incase of failure
        	emails = new ArrayList<String>();
        	emails.add(submitterEmail);
        }
        
        log.debug("send email ");
        try {
        	sendMail(emails.toArray(new String[0]), subject, messages, attachment);

        } catch (Exception  e ) {

        	throw new Exception(" Error in sending email , please check the confiuration " , e);
        }
        
        enableAuthorization(authorizationOnByDefault);
		switchUser(null);
    	
    }
    public void sendNotificationToReporter(String submitterEmail, String messages,
                    String aeReportId, String reportId, boolean success, String ticketNumber,
                    String url,boolean communicationError) throws Exception {
        // get AEreport by using this id

        WebRequest stubWebRequest = preProcess();
        
		boolean authorizationOnByDefault = enableAuthorization(false);
		switchUser("SYSTEM_ADMIN", "ROLE_caaers_super_user");
        
        String info = configuration.get(Configuration.ESB_URL);
        gov.nih.nci.cabig.ctms.audit.domain.DataAuditInfo.setLocal(new DataAuditInfo("SYSTEM_ADMIN",
                info, new Date(), info));
        
      //  ExpeditedAdverseEventReport aeReport = expeditedAdverseEventReportDao.getById(Integer
        //                .parseInt(aeReportId));

        Report r = reportDao.getById(Integer.parseInt(reportId));
        reportDao.initialize(r.getScheduledNotifications());
        ReportVersion rv = r.getLastVersion();
        messages = messages.replace("Report # "+reportId, "Report # "+rv.getId());
        //
        List<String> emails = getEmailList(r,submitterEmail);
        
        
       // List<ReportTracking> rts = rv.getReportTrackings();
        ReportTracking rtToUpdate = rv.getLastReportTracking();
        System.out.println("ATTEMPT NUMBER" + rtToUpdate.getAttemptNumber());
        
        
        
        /*
        for (ReportTracking rt:rts) {
        	if (rt.getAttemptNumber() == rts.size()) {
        		rtToUpdate = rt;
        		break;
        	}
        }*/
        
        boolean ableToSubmitToWS = true;
        String submissionMessage = "";
        if (communicationError) {
        	ableToSubmitToWS = false;
        	submissionMessage = messages;
        }
        


        	Tracker.logConnectionToExternalSystem(rtToUpdate, ableToSubmitToWS, submissionMessage, new Date());

        	reportDao.save(r);


        

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
            ReportSubmissionContext context = ReportSubmissionContext.getSubmissionContext(r);
            doPostSubmitReport(context);

            Tracker.logSubmissionToExternalSystem(rtToUpdate, true, messages, new Date());

        } else {
            r.setSubmittedOn(new Date());
            r.setStatus(ReportStatus.FAILED);

            rv.setSubmittedOn(new Date());
            rv.setReportStatus(ReportStatus.FAILED);
            if (ableToSubmitToWS) {

            	Tracker.logSubmissionToExternalSystem(rtToUpdate, false, messages, new Date());

            }
            //reportTrackingDao.save(rtToUpdate);
        }
        rv.setSubmissionMessage(messages);
        r.setSubmissionMessage(messages);

        reportDao.save(r);
        
        postProcess(stubWebRequest);
        
        String subject = "";
        String attachment = null;
        if (success) {
            messages = messages + url;
            subject = "Submission of Expedited Report(" + rv.getId() + ") to AdEERS";  
            //this pdf has already been generated in AdeersReportGenerator , we are just attching here incase of successfull submission.
            String tempDir = System.getProperty("java.io.tmpdir");
            attachment = tempDir + "/expeditedAdverseEventReport-" + rv.getId() + ".pdf";
            System.out.println("attachement is "+ attachment);
        } else {
        	subject = "Problem with Submission of Expedited Report(" + rv.getId() + ") to AdEERS";
        	// send only to submitter incase of failure
        	emails = new ArrayList<String>();
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

        	reportDao.save(r);
        } catch (Exception  e ) {

        	Tracker.logEmailNotificationToSubmitter(rtToUpdate, false, e.getMessage(), new Date());

        	reportDao.save(r);
        	throw new Exception(" Error in sending email , please check the confiuration " , e);
        }
        
        enableAuthorization(authorizationOnByDefault);
		switchUser(null);
    }

	public void sendMail(String[] to, String subject, String content, String attachment) throws Exception {
		
			//JavaMailSenderImpl caaersJavaMailSender = new JavaMailSenderImpl();
			
			//caaersJavaMailSender.setUsername(configuration.get(Configuration.SMTP_USER));
			//caaersJavaMailSender.setPassword(configuration.get(Configuration.SMTP_PASSWORD));
            //System.out.println("host .." + mailHost);
            //caaersJavaMailSender.setHost(configuration.get(Configuration.SMTP_ADDRESS));
            
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


	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	private boolean enableAuthorization(boolean on) {
        AuthorizationSwitch sw = (AuthorizationSwitch) this.applicationContext.getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(on);
        return current;
    }
	
	private void switchUser(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext= applicationContext;
		
	}

}
