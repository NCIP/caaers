/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDelivery;
import gov.nih.nci.cabig.caaers.domain.report.ReportDeliveryDefinition;
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.logging.api.util.StringUtils;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.Namespace;
import org.springframework.transaction.annotation.Transactional;
/**
 * Will handle the responses related to report submission.
 * 
 * 
 * @author Srini
 * @author Biju Joseph
 *
 */
/*
 * BJ : Messages read from resource files. 
 * BJ : TODO copy the testcases checked in at r10219
 */
public class AdeersSubmissionResponseMessageProcessor extends ResponseMessageProcessor{

	protected final Log log = LogFactory.getLog(getClass());
	private Configuration configuration;

	@Override
	@Transactional
	public void processMessage(String message) throws CaaersSystemException {
        log.debug("AdeersSubmissionResponseMessageProcessor - message recieved");


        Namespace emptyNS = Namespace.NO_NAMESPACE;
        Namespace adeersNS = Namespace.getNamespace("ns2", "http://types.ws.adeers.ctep.nci.nih.gov");
        Element jobInfo = this.getResponseElement(message,"submitAEDataXMLAsAttachmentResponse","AEReportJobInfo");

        log.debug("Got JobInfo; " + message);
        
        String caaersAeReportId = childNodeValue(jobInfo, emptyNS, "CAEERS_AEREPORT_ID");
        log.debug("Data Colleciton ID : " + caaersAeReportId);
        String reportId = childNodeValue(jobInfo, emptyNS, "CAAERSRID");
        log.debug("Report ID : " + reportId);
        String submitterEmail = childNodeValue(jobInfo, emptyNS, "SUBMITTER_EMAIL");
        log.debug("Submitter email : " + submitterEmail);

        String jobStatus = childNodeValue(jobInfo, adeersNS, "reportStatus");
        log.debug("JobStatus: " + jobStatus);
        if(StringUtils.isBlank(jobStatus)) {
        	jobStatus= "ERROR";
        }

        Report r = null;
        try {
        	r =reportDao.getById(Integer.parseInt(reportId));
        } catch (Exception e) {
        	//number format exception, report not found, etc. Doesn't matter report will just be null.
        	r = null;
        }
        
        //FIXME: When updating Caaers to send to multiple systems the below must also be changed.
        //Can just use the first system as that is the only one that is used.
        String sysName = "UNKNOWN";
        if(r != null) {
	        List<ReportDelivery> list = r.getExternalSystemDeliveries();
            ReportDeliveryDefinition deliveryDef = list.isEmpty() ? null : list.get(0).getReportDeliveryDefinition();
            sysName = deliveryDef != null ? deliveryDef.getEntityName() : "UNKNOWN";
        }
        // build error messages
        StringBuffer sb = new StringBuffer();

        boolean success = false;
        boolean communicationError = false;
        String ticketNumber = "";
        String url = "";

        try {
            if (jobStatus.equals("SUCCESS")) {
            	 success = true;
            	 ticketNumber = childNodeValue(jobInfo, adeersNS, "ticketNumber");
                 url = childNodeValue(jobInfo, adeersNS, "reportURL");
                 
        		 String submissionMessage = messageSource.getMessage("successful.reportSubmission.message",
        				 new Object[]{String.valueOf(r.getId()), ticketNumber,  url}, Locale.getDefault());
        		 
        		sb.append(submissionMessage);
        		
        		// append additional report information
            	String reportDetails = messageSource.getMessage("additional.successful.reportSubmission.information",  new Object[] {r.getSubmitter().getFullName(), 
       				 r.getSubmitter().getEmailAddress(), r.getAeReport().getStudy().getPrimaryIdentifier().getValue(), r.getAeReport()
    				 .getParticipant().getPrimaryIdentifierValue(), r.getCaseNumber(),String.valueOf(r.getId()),ticketNumber, configuration.get(Configuration.SYSTEM_NAME)}, Locale.getDefault());
            	sb.append(reportDetails);
            }else{
            	 @SuppressWarnings("unchecked")
				List<Element> exceptions = jobInfo.getChildren("jobExceptions", adeersNS);
            	 log.warn("Got " + exceptions.size() + " errors to report.");
            	 //find the exception elements
            	 if(CollectionUtils.isNotEmpty(exceptions)){
            		 StringBuffer exceptionMsgBuffer = new StringBuffer();
            		 for (Element ex : exceptions) {
            			 final String code = childNodeValue(ex, emptyNS, "code");
            			 final String desc = childNodeValue(ex, emptyNS, "desc");
            			 log.warn("Encountered Error: " + code + " - " + desc);
            			 exceptionMsgBuffer.append(code).append( "  -  ").append(desc).append("\n");

            			 if (code.equals("caAERS-adEERS : COMM_ERR")) {
                     		communicationError=true;
                         }
                     }
            		 
            		 String submissionMessage = messageSource.getMessage("failed.reportSubmission.message", new Object[]{String.valueOf(r.getId()),
            				 exceptionMsgBuffer.toString(), r.getSubmitter().getFullName(), r.getSubmitter().getEmailAddress(), r.getAeReport().getStudy()
            				 .getPrimaryIdentifier().getValue(), r.getAeReport().getParticipant().getPrimaryIdentifierValue(), r.getCaseNumber(),String.valueOf(r.getId()),
            				 configuration.get(Configuration.SYSTEM_NAME), sysName}, Locale.getDefault());
            		 sb.append(submissionMessage);
            		 
            	 }//if exceptions
            	 
            }
            
            if (jobInfo.getChild("comments", adeersNS) != null) {
           	 	String commentsMessage = messageSource.getMessage("comments.reportSubmission.message", new Object[]{childNodeValue(jobInfo, adeersNS, "comments")}, Locale.getDefault());
           	 	sb.append("\n"); // Move comments section to NextLine.
                sb.append(commentsMessage);
            }
            
         	// append Help Text message
        	String helpText = messageSource.getMessage("helptext.submission.message", new Object[]{}, Locale.getDefault());
        	sb.append(helpText);

            

        } catch (Exception e) {
            log.error("Error while generating email body", e);
        }

        // Notify submitter
        try {
        	 String messages = sb.toString();
            log.debug("Calling notfication service ..");
            this.getMessageNotificationService().sendNotificationToReporter(submitterEmail, messages,
                            caaersAeReportId, reportId, success, ticketNumber, url,communicationError);
        } catch (Exception e) {
            log.error("Error while sending out email", e);
        }
		
	}

    private String childNodeValue(Element el, Namespace ns, String nodeName) {
        Element n = el.getChild(nodeName, ns);
        if(n != null) return n.getValue();
        return "";
    }
	
	  public void setConfiguration(Configuration configuration) {
	        this.configuration = configuration;
	    }

}
