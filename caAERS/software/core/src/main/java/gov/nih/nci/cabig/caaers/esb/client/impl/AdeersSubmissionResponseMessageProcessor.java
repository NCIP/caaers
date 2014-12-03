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
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;
import gov.nih.nci.cabig.caaers.service.ReportSubmissionService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

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
	
	private static final String RESPONSE_MSG_END_TAG = "</submitAEDataXMLAsAttachmentResponse>";
	private static final String RESPONSE_MSG_ST_TAG = "<submitAEDataXMLAsAttachmentResponse";
	protected final Log log = LogFactory.getLog(getClass());
	private Configuration configuration;

	@Override
	@Transactional
	public void processMessage(String message) throws CaaersSystemException {
        log.debug("AdeersSubmissionResponseMessageProcessor - message received");

        
        Element jobInfo = this.getResponseElement(message,"submitAEDataXMLAsAttachmentResponse","AEReportJobInfo");
        Namespace emptyNS=null;
        for (Object obj:jobInfo.getChildren()) {
				Element e = (Element)obj;
				if (e.getName().equals("CAEERS_AEREPORT_ID")) {
					emptyNS = e.getNamespace();
				}
		}
        
        log.debug("got JobInfo");
        
        String caaersAeReportId = jobInfo.getChild("CAEERS_AEREPORT_ID",emptyNS).getValue();
        log.debug("ID 1 : " + caaersAeReportId);
        String reportId = jobInfo.getChild("REPORT_ID",emptyNS).getValue();
        log.debug("ID 2 : " + reportId);
        String submitterEmail = jobInfo.getChild("SUBMITTER_EMAIL",emptyNS).getValue();
        log.debug("email : " + submitterEmail);
        
        Report report = reportDao.getById(Integer.parseInt(reportId));
        
        //FIXME: When updating Caaers to send to multiple systems the below must also be changed.
        //Can just use the first system as that is the only one that is used.
        String sysName = "UNKOWN";
        if(report != null) {
	        List<ReportDelivery> list = report.getExternalSystemDeliveries();
	        if(list != null && list.size() > 0) {
	        	if(list.get(0) != null && list.get(0).getReportDeliveryDefinition() != null) {
	        		sysName = list.get(0).getReportDeliveryDefinition().getEntityName();
	        	}
	        }
        }
        // build error messages
        StringBuffer sb = new StringBuffer();

        boolean success = true;
        boolean communicationError = false;
        String ticketNumber = "";
        String url = "";

        try {

            if (jobInfo.getChild("reportStatus").getValue().equals("SUCCESS")) {
            	
            	 ticketNumber = jobInfo.getChild("ticketNumber").getValue();
                 url = jobInfo.getChild("reportURL").getValue();
                 
        		 String submissionMessage = messageSource.getMessage("successful.reportSubmission.message",
        				 new Object[]{String.valueOf(report.getLastVersion().getId()), ticketNumber,  url}, Locale.getDefault());
        		 
        		sb.append(submissionMessage);
        		
        		// append additional report information
            	String reportDetails = messageSource.getMessage("additional.successful.reportSubmission.information",  new Object[] {report.getSubmitter().getFullName(),
       				 report.getSubmitter().getEmailAddress(), report.getAeReport().getStudy().getPrimaryIdentifier().getValue(), report.getAeReport()
    				 .getParticipant().getPrimaryIdentifierValue(), report.getCaseNumber(),String.valueOf(report.getId()),ticketNumber, configuration.get(Configuration.SYSTEM_NAME)}, Locale.getDefault());
            	sb.append(reportDetails);
            }else{
            	 success = false;
            	 @SuppressWarnings("unchecked")
				List<Element> exceptions = jobInfo.getChildren("jobExceptions");
            	 //find the exception elements
            	 if(CollectionUtils.isNotEmpty(exceptions)){
            		 StringBuffer exceptionMsgBuffer = new StringBuffer();
            		 for (Element ex : exceptions) {
            			 exceptionMsgBuffer.append(ex.getChild("code").getValue()).append( "  -  ").append(ex.getChild("description").getValue()).append("\n");

            			 if (ex.getChild("code").getValue().equals("caAERS-adEERS : COMM_ERR")) {
                     		communicationError=true;
                         }
                     }
            		 
            		 String submissionMessage = messageSource.getMessage("failed.reportSubmission.message", new Object[]{String.valueOf(report.getLastVersion().getId()),
            				 exceptionMsgBuffer.toString(), report.getSubmitter().getFullName(), report.getSubmitter().getEmailAddress(), report.getAeReport().getStudy()
            				 .getPrimaryIdentifier().getValue(), report.getAeReport().getParticipant().getPrimaryIdentifierValue(), report.getCaseNumber(),String.valueOf(report.getId()),
            				 configuration.get(Configuration.SYSTEM_NAME), sysName}, Locale.getDefault());
            		 sb.append(submissionMessage);
            		 
            	 }//if exceptions
            	 
            }
            
            if (jobInfo.getChild("comments") != null) {
           	 	String commentsMessage = messageSource.getMessage("comments.reportSubmission.message", new Object[]{jobInfo.getChild("comments").getValue()}, Locale.getDefault());
           	 	sb.append("\n"); // Move comments section to NextLine.
                sb.append(commentsMessage);
            }
            
         	// append Help Text message
        	String helpText = messageSource.getMessage("helptext.submission.message", new Object[]{}, Locale.getDefault());
        	sb.append(helpText);

            

        } catch (Exception e) {
            log.error("Error while generating email body", e);
        }
        
        
       try {
		//Send to response back to ESB to further routing if necessary
		   //like sending an E2B ack message
		    int stInd = message.indexOf(RESPONSE_MSG_ST_TAG);
		    int endInd = message.indexOf(RESPONSE_MSG_END_TAG);
		    String trimmedMessage = message.substring(stInd, endInd);
            if(!success) {
                int insertPoint = trimmedMessage.lastIndexOf("</description>");
                if(insertPoint > 0){
                    trimmedMessage = trimmedMessage.subSequence(0, insertPoint) + " System Error Occured in: " + sysName + trimmedMessage.substring(insertPoint);
                }
            }
		    trimmedMessage += RESPONSE_MSG_END_TAG;
		    trimmedMessage = trimmedMessage.replaceAll(RESPONSE_MSG_ST_TAG, RESPONSE_MSG_ST_TAG + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		    String routedRes = getProxyWebServiceFacade().routeAdeersReportSubmissionResponse(trimmedMessage, report);
		    log.debug("Routed response is " + routedRes);

		} catch (Exception e) {
           log.error("Error while routing AdEERS response to caAERS Generic Processor", e);
		}

        //handle post submission
        try {
            String messages = sb.toString();
            log.debug("Invoking handleResponse flow in ReportSubmission service ..");
            ReportSubmissionService.ReportSubmissionContext context = ReportSubmissionService.ReportSubmissionContext.getSubmissionContext(report);
            context.submissionURL = url;
            context.responseMessage = messages;
            context.ticketNumber = ticketNumber;
            context.communicationFailure = communicationError;
            context.success = success;
            context.submitterEmail = submitterEmail;

            reportSubmissionService.handleExternalSubmissionResponse(context);

        } catch (Exception e) {
            log.error("Error while submission response processing", e);
        }
		
	}
	
	  public void setConfiguration(Configuration configuration) {
	        this.configuration = configuration;
	    }

}
