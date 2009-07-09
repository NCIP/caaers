package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AdeersReportGenerator;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportTrackingDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PersonContact;
import gov.nih.nci.cabig.caaers.domain.ReportFormatType;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportContent;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportTracking;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.esb.client.impl.CaaersAdeersMessageBroadcastServiceImpl;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.caaers.utils.Tracker;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

/**
 * This class is responsible for submission of an {@link ExpeditedAdverseEventReport}
 * @author Biju Joseph
 *
 */
@Transactional
public class ReportSubmissionService {
	
    
    protected final Log log = LogFactory.getLog(getClass());

    private NowFactory nowFactory;
    protected CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService;
    protected CaaersJavaMailSender caaersJavaMailSender;
    private AdeersReportGenerator adeersReportGenerator;
    private SchedulerService schedulerService;
    
    private ReportDao reportDao;
    private ReportTrackingDao reportTrackingDao;

    /**
     * Will do the following.
     *  1. Generate the caaers-xml
     *  2. Identify the status of the report (some reports dont require submission, so they will directly transition to Completed) 
     *  3. Generate the PDF
     *  4. Save the ReportVersion
     *  5a. Notify the report to external system
     *  5b. Notify email recipients
     */
    public void submitReport(Report report){
    	ReportTracking reportTracking = new ReportTracking();
    	// Initiate ...
    	try {    		
    		List<ReportTracking> reportTrackings = report.getLastVersion().getReportTrackings();
    		int attemptNumber = 1;
            if( reportTrackings != null && reportTrackings.size() > 0) {
            	attemptNumber = reportTrackings.size()+1;
            }
            reportTracking.setAttemptNumber(attemptNumber);  
    		Tracker.logInitiation(reportTracking, true, "",nowFactory.getNow());
    		report.getLastVersion().addReportTracking(reportTracking);
    	} catch ( Exception e ) {
    		throw new CaaersSystemException("Unable to submit report [" + e.getMessage() + "]" , e);
    	}
    	
    	// generate caAERS xml
    	Date today = nowFactory.getNow();
    	String caaersXml = "";
    	try {    		
    		caaersXml = adeersReportGenerator.generateCaaersXml(report.getAeReport(),report);
    		Tracker.logXmlGeneration(reportTracking, true, "",nowFactory.getNow());
    	} catch (Exception e ) {
    		Tracker.logXmlGeneration(reportTracking, false, e.getMessage(),nowFactory.getNow());
    	}
    	
    	ReportVersion reportVersion;
    	boolean hasSystemDeliveries;
    	ReportStatus status;
    	try {
    		//if there is no external system to receive, it is assumed that there are no interim report statuses..
    		hasSystemDeliveries = report.hasSystemDeliveries();
    		//step 2
    		status = (hasSystemDeliveries) ? ReportStatus.INPROCESS : ReportStatus.COMPLETED;
            //BJ - Tweak - I dont remember why this code is here, but cutpasted from SubmitReportController
    		if(!hasSystemDeliveries) schedulerService.unScheduleNotification(report);
    		report.setStatus(status);
    		
    		//Generate and save the report version
            report.setSubmissionUrl("");
            report.setSubmissionMessage("");
            report.setSubmittedOn(today);
            
            reportVersion = report.getLastVersion();
            reportVersion.setReportStatus(status);
            reportVersion.setSubmissionUrl("");
            reportVersion.setSubmissionMessage("");
            reportVersion.setSubmittedOn(today);
            
            //remove existing report contents (if any)
            if(CollectionUtils.isNotEmpty(reportVersion.getContents())){
            	reportVersion.getContents().clear();
            }
    	} catch (Exception e ) {
    		throw new CaaersSystemException("Unable to submit report [" + e.getMessage() + "]" , e);
    	}  
    	String[] pdfReportPaths;
    	try {
            //Step 3 - add the report content to report version
            pdfReportPaths = adeersReportGenerator.generateExternalReports(report, caaersXml);   
    		for(String pdfFilePath : pdfReportPaths){
            	File f = new File(pdfFilePath);
            	if(f.exists() && f.canRead()){
            		ReportContent reportContent = new ReportContent("application/pdf", FileCopyUtils.copyToByteArray(f));
            		reportVersion.addReportContent(reportContent);
            	}
            }
    		Tracker.logAttachmentGeneration(reportTracking, true, "",nowFactory.getNow());
            reportVersion.addReportContent(new ReportContent("text/xml", caaersXml.getBytes()));
            reportDao.save(report);
            reportDao.flush();
            
    	} catch (Exception e ) {    		
    		Tracker.logAttachmentGeneration(reportTracking, false, e.getMessage() ,nowFactory.getNow());
            reportDao.save(report);
            reportDao.flush();
    		throw new CaaersSystemException("Unable to submit report [" + e.getMessage() + "]" , e);
    	}  
            
            //Notify the external systems
            if(hasSystemDeliveries){
            	//step 5.a
            	try {
					notifyExternalSystems(report, caaersXml, reportTracking);					
				} catch (Exception e) {
					log.error("Error while sending message to service mix ", e);
					status = ReportStatus.FAILED;
		            report.getLastVersion().setReportStatus(status);
		            report.getLastVersion().setSubmissionMessage("Problem communicating with ESB <br> Please try to resubmit the report <br>" + e.getMessage());
		            reportDao.save(report);
				}
            	
            }else{
            	//step 5.b - notify all email recipients.
            	try {
            		notifyEmailRecipients(report, caaersXml, pdfReportPaths,reportTracking);
            	 } catch (Exception e) {
                 	Tracker.logEmailNotification(reportTracking, false, e.getMessage(),nowFactory.getNow());
     				log.error("Error while sending email ", e);
     	            report.getLastVersion().setReportStatus(ReportStatus.FAILED);
     	            report.getLastVersion().setSubmissionMessage("Error  sending email " + e.getMessage());
     	            reportDao.save(report);

                 }            	
            }
     }
    
    /**
     * This method will generate the message content and forwards it to the caaers mail sender.
     * @param report
     * @param xml
     * @param pdfFilePaths
     * @throws Exception
     */
    
    public void notifyEmailRecipients(Report report, String xml, String[] pdfFilePaths,ReportTracking reportTracking) throws Exception {
    	List<String> emailRecipients = report.getEmailRecipients();
    	if(!emailRecipients.isEmpty()){
    		 //if email recipents are there, notify them.
        	ExpeditedAdverseEventReport expeditedAdverseEventReport = report.getAeReport();
            Participant participant = expeditedAdverseEventReport.getAssignment().getParticipant();
            String firstName = participant.getLastFirst();
            String lastName = participant.getLastName();
            List<Identifier> pIds = participant.getIdentifiers();
            String pid = "";
            for (Identifier identifier:pIds) {
            	if (identifier.getPrimaryIndicator()) {
            		pid = identifier.getValue();
            	}
            }
            
            Study study = expeditedAdverseEventReport.getStudy();
            String shortTitle = study.getShortTitle();
            List<Identifier> sIds = study.getIdentifiers();
            String sid = "";
            for (Identifier identifier:sIds) {
            	if (identifier.getPrimaryIndicator()) {
            		sid = identifier.getValue();
            	}
            }
            
            String content = "";
            ReportFormatType formatType = report.getReportDefinition().getReportFormatType();
            if(formatType.equals(ReportFormatType.ADEERSPDF)){
            	content = "An "+formatType.getDisplayName()+" for "+firstName +" " + lastName+"("+pid+") on "+shortTitle+"("+sid+") has successfully been submitted to AdEERS. Please refer to the attached AdEERS report for complete details.";	
            }else{
            	content = "An "+formatType.getDisplayName()+" for "+firstName +" " + lastName+"("+pid+") on "+shortTitle+"("+sid+") has successfully been created. Please refer to the attached PDF report for complete details.";
            }

            caaersJavaMailSender.sendMail(emailRecipients.toArray(new String[0]), formatType.getDisplayName(), content, pdfFilePaths);
            String msg = "Notified to : " ;
        	for (String e:emailRecipients) {
        		msg = msg + "," + e;
        	}
            Tracker.logEmailNotification(reportTracking, true, msg,nowFactory.getNow());
            
    	}
    }

  
    /**
     * This method will notify the external systems.
     * @param report
     * @param xml
     * @param pdfFilePaths
     * @throws Exception
     */
    
    public void notifyExternalSystems(Report report, String xml, ReportTracking reportTracking) throws Exception {
        List<ReportDelivery> deliveries = report.getExternalSystemDeliveries();
        int reportId = report.getId();
        StringBuilder sb = new StringBuilder();
        sb.append("<EXTERNAL_SYSTEMS>");
        for (ReportDelivery delivery : deliveries) {
            sb.append(delivery.getEndPoint()).append("::").append(delivery.getUserName()).append("::" ).append(delivery.getPassword());
        }
        sb.append("</EXTERNAL_SYSTEMS>");
        sb.append("<REPORT_ID>" + reportId + "</REPORT_ID>");

        String submitterEmail = report.getLastVersion().getSubmitter().getContactMechanisms().get(PersonContact.EMAIL);
        sb.append("<SUBMITTER_EMAIL>" + submitterEmail + "</SUBMITTER_EMAIL>");
        //if there are external systems, send message via service mix
    	String externalXml = xml.replaceAll("<AdverseEventReport>", "<AdverseEventReport>" + sb.toString());
    	
    	try {
    		messageBroadcastService.initialize();
    	} catch (Exception e) {
    		Tracker.logConnectionToESB(reportTracking, false, e.getMessage() + " Error initilizing ESB broadcast",nowFactory.getNow());
    		e.printStackTrace();
    		throw new Exception (e);
    	}
    	
    	try {
    		messageBroadcastService.broadcast(externalXml);
    	} catch (Exception e) {
    		Tracker.logConnectionToESB(reportTracking, false, e.getMessage() + " Error Broadcasting to ESB",nowFactory.getNow());
    		e.printStackTrace();
    		throw new Exception (e);
    	}
    	Tracker.logConnectionToESB(reportTracking, true, "",nowFactory.getNow());
    }

	
	@Required
	public void setSchedulerService(final SchedulerService schedulerService) {
	    this.schedulerService = schedulerService;
	}
	@Required
	public void setAdeersReportGenerator(AdeersReportGenerator adeersReportGenerator) {
		this.adeersReportGenerator = adeersReportGenerator;
	}
	
    @Required
    public void setReportDao(final ReportDao reportDao) {
        this.reportDao = reportDao;
    }
    
    @Required
    public void setMessageBroadcastService(
                    CaaersAdeersMessageBroadcastServiceImpl messageBroadcastService) {
        this.messageBroadcastService = messageBroadcastService;
    }
    @Required
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender) {
		this.caaersJavaMailSender = caaersJavaMailSender;
	}
    
    @Required
    public void setNowFactory(final NowFactory nowFactory) {
        this.nowFactory = nowFactory;
    }

  //  @Required    
	public void setReportTrackingDao(ReportTrackingDao reportTrackingDao) {
		this.reportTrackingDao = reportTrackingDao;
	}

}
