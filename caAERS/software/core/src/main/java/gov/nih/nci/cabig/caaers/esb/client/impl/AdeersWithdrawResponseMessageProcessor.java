package gov.nih.nci.cabig.caaers.esb.client.impl;

import java.util.List;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.esb.client.MessageNotificationService;
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;

public class AdeersWithdrawResponseMessageProcessor extends ResponseMessageProcessor{
	
	protected final Log log = LogFactory.getLog(getClass());
	private MessageNotificationService messageNotificationService;
	
	@Override
	public void processMessage(String message) throws CaaersSystemException {
		// TODO Auto-generated method stub
		
		log.debug("AdeersWithdrawResponseMessageProcessor - message recieved");
        
        Element cancelInfo = this.getResponseElement(message,"withdrawAEReportResponse","AEReportCancelInfo");
        String caaersAeReportId = cancelInfo.getChild("CAEERS_AEREPORT_ID").getValue();
        String reportId = cancelInfo.getChild("REPORT_ID").getValue();
        String submitterEmail = cancelInfo.getChild("SUBMITTER_EMAIL").getValue();
        
//      buld error messages
        StringBuffer sb = new StringBuffer();

        boolean success = true;
        boolean communicationError = false;
        String ticketNumber = "";
        String url = "";

        try {

            List<Element> exceptions = cancelInfo.getChildren("jobExceptions");
            // sb.append("REPORT STATUS : " + jobInfo.getChild("reportStatus").getValue()+"\n\n\n");
            

            if (cancelInfo.getChild("reportStatus").getValue().equals("SUCCESS")) {
                sb.append("Report # " + reportId
                                + " has been successfully withdrawn from AdEERS. \n\n");

                sb.append("TICKET NUMBER :. \n");
                sb.append("---------------.\n");
                sb.append("Your AdEERS ticket number is "
                                + cancelInfo.getChild("ticketNumber").getValue() + ".\n\n");

 
                // sb.append(jobInfo.getChild("reportURL").getValue()+"\n");

                ticketNumber = cancelInfo.getChild("ticketNumber").getValue();


            }

            if (exceptions.size() > 0) {
                sb.append("Report # " + reportId
                                + " was NOT successfully withdrawn from AdEERS. \n\n");
                sb.append("The following problem was encountered:. \n");
                for (Element ex : exceptions) {
                    sb.append(ex.getChild("description").getValue() + ".\n");
                }
                sb.append("\n");
                sb.append("Please correct the problem and withdraw the report again.\n\n");
                sb.append("See below for a technical description of the error.:\n\n");

                sb.append("EXCEPTIONS.\n");
                sb.append("----------.\n");

                success = false;
            }

            for (Element ex : exceptions) {
                sb.append(ex.getChild("code").getValue() + "  -  "
                                + ex.getChild("description").getValue());
                sb.append(".\n");
            	if (ex.getChild("code").getValue().equals("caAERS-adEERS : COMM_ERR")) {
            		communicationError=true;
                }
            }

            if (cancelInfo.getChild("comments") != null) {
                sb.append("COMMENTS : ." + cancelInfo.getChild("comments").getValue() + "\n");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String messages = sb.toString();

        // Notify submitter
        // System.out.println("calling msessageNotifyService 10..");

        try {
            log.debug("Calling notfication service ..");
            messageNotificationService.sendNotificationToReporter(submitterEmail, messages,
                            caaersAeReportId, reportId, success, ticketNumber, url,communicationError);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }       
	}

}
