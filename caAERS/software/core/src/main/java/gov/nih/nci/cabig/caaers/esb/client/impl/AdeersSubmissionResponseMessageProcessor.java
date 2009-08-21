package gov.nih.nci.cabig.caaers.esb.client.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.Namespace;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.esb.client.MessageNotificationService;
import gov.nih.nci.cabig.caaers.esb.client.ResponseMessageProcessor;

public class AdeersSubmissionResponseMessageProcessor extends ResponseMessageProcessor{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	
	@Override
	public void processMessage(String message) throws CaaersSystemException {
        log.debug("AdeersSubmissionResponseMessageProcessor - message recieved");
        
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

        // buld error messages
        StringBuffer sb = new StringBuffer();

        boolean success = true;
        boolean communicationError = false;
        String ticketNumber = "";
        String url = "";

        try {

            List<Element> exceptions = jobInfo.getChildren("jobExceptions");
            // sb.append("REPORT STATUS : " + jobInfo.getChild("reportStatus").getValue()+"\n\n\n");
            

            if (jobInfo.getChild("reportStatus").getValue().equals("SUCCESS")) {
                sb.append("Report # " + reportId
                                + " has been successfully submitted to AdEERS. \n\n");

                sb.append("TICKET NUMBER :. \n");
                sb.append("---------------.\n");
                sb.append("Your AdEERS ticket number is "
                                + jobInfo.getChild("ticketNumber").getValue() + ".\n\n");

                sb.append("VIEWING THE REPORT IN ADEERS:.\n");
                sb.append("-------------------------------.\n");

                sb.append("To access the report in AdEERS, simply point your browser to the following URL:.\n\n");

                // sb.append(jobInfo.getChild("reportURL").getValue()+"\n");

                ticketNumber = jobInfo.getChild("ticketNumber").getValue();
                url = jobInfo.getChild("reportURL").getValue();
                sb.append(url);

            }

            if (exceptions.size() > 0) {
                sb.append("Report # " + reportId
                                + " was NOT successfully submitted to AdEERS. \n\n");
                sb.append("The following problem was encountered:. \n");
                for (Element ex : exceptions) {
                    sb.append(ex.getChild("description").getValue() + ".\n");
                }
                sb.append("\n");
                sb.append("Please correct the problem and submit the report again.\n\n");
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

            if (jobInfo.getChild("comments") != null) {
                sb.append("COMMENTS : ." + jobInfo.getChild("comments").getValue() + "\n");
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
            this.getMessageNotificationService().sendNotificationToReporter(submitterEmail, messages,
                            caaersAeReportId, reportId, success, ticketNumber, url,communicationError);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}



}
