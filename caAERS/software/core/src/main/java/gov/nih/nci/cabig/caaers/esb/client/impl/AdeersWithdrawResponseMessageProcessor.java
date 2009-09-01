package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.Namespace;
/**
 * Processes withdraw message responses. 
 * 
 * 
 * @author Srini
 * @author Biju Joseph
 *
 */

/*
 * Note : BJ : TODO modify the testcases checked in at r10219 to suite this class. 
 * 
 * BJ : Made to read text messages from properties file. 
 * BJ : changed all exception printing to log statements
 * BJ : changed to call at the end to "sendWithdrawNotificationToReporter"
 * BJ : Removed unwanted TO-DO comments. 
 */
public class AdeersWithdrawResponseMessageProcessor extends ResponseMessageProcessor{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public void processMessage(String message) throws CaaersSystemException {
		
		log.debug("AdeersWithdrawResponseMessageProcessor - message recieved");
        
        Element cancelInfo = this.getResponseElement(message,"withdrawAEReportResponse","AEReportCancelInfo");
        Namespace emptyNS=null;
        Namespace ctepNS = null;
        for (Object obj:cancelInfo.getChildren()) {
				Element e = (Element)obj;
				if (e.getName().equals("CAEERS_AEREPORT_ID")) {
					emptyNS = e.getNamespace();
				}
 				if (e.getName().equals("reportStatus")) {
 					ctepNS = e.getNamespace();
 				} 	
		}
        
        String caaersAeReportId = cancelInfo.getChild("CAEERS_AEREPORT_ID",emptyNS).getValue();
        String reportId = cancelInfo.getChild("REPORT_ID",emptyNS).getValue();
        String submitterEmail = cancelInfo.getChild("SUBMITTER_EMAIL",emptyNS).getValue();
        
//      buld error messages
        StringBuffer sb = new StringBuffer();

        boolean success = true;
        boolean communicationError = false;
        String ticketNumber = "";
        String url = "";

        try {

            List<Element> exceptions = cancelInfo.getChildren("jobExceptions",ctepNS);

            if (cancelInfo.getChild("reportStatus",ctepNS).getValue().equals("SUCCESS")) {
                String withdrawSuccessMessage = messageSource.getMessage("successful.reportWithdraw.message", new Object[]{reportId, ticketNumber}, Locale.getDefault());
                sb.append(withdrawSuccessMessage);
            }else{
            	StringBuffer exceptionMsgBuffer = new StringBuffer();
            	if (CollectionUtils.isNotEmpty(exceptions)) {
                    success = false;
                    for (Element ex : exceptions) {
                    	exceptionMsgBuffer.append(ex.getChild("code").getValue()).append( "  -  ").append(ex.getChild("description").getValue()).append("\n");
                       
                    	if (ex.getChild("code").getValue().equals("caAERS-adEERS : COMM_ERR")) {
                    		communicationError=true;
                        }
                    }
                }
            	
            	String withdrawFailureMessage = messageSource.getMessage("failed.reportWithdraw.message", new Object[]{reportId, exceptionMsgBuffer.toString()}, Locale.getDefault());
                sb.append(withdrawFailureMessage);
            	
            }

            
            if (cancelInfo.getChild("comments",ctepNS) != null) {
            	 String commentsMessage = messageSource.getMessage("comments.reportWithdraw.message", new Object[]{cancelInfo.getChild("comments").getValue()}, Locale.getDefault());
                 sb.append(commentsMessage);
            }

        } catch (Exception e) {
           log.error(e);
        }

        String messages = sb.toString();

        // Notify submitter

        try {
        	
            log.debug("Calling notfication service ..");
            getMessageNotificationService().sendWithdrawNotificationToReporter(submitterEmail, messages, caaersAeReportId, reportId, success, ticketNumber, url, communicationError);
            
        } catch (Exception e) {
           log.error(e);
        }       
	}


}
