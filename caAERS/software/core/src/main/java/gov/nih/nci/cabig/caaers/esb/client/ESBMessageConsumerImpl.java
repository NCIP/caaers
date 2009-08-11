package gov.nih.nci.cabig.caaers.esb.client;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.jdom.Document;

/**
 * This class handles the AdEERS submission response (from ServiceMix).
 * 
 * @author Biju Joseph (Refactored)
 *
 */

/*
 * BJ :
 *   TODO : How this can handle responses related to non-AdEERS submission ?
 */

public class ESBMessageConsumerImpl implements ESBMessageConsumer {

    protected final Log log = LogFactory.getLog(getClass());
  
	//will delegate further processing to Notification service. 
    private MessageNotificationService messageNotificationService;
    
    //will be used to obtain resource bundle messages
    protected MessageSource messageSource;

    /**
     * This method will parse the XML message obtained from ServiceMix.
     * @param message
     * @return
     */
    protected Element getJobInfo(String message) {

        SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader = new StringReader(message);
        Element jobInfo = null;
        try {
        	
            Document jdomDocument = saxBuilder.build(stringReader);
            Element root = jdomDocument.getRootElement();

            Element body = root.getChild("Body", root.getNamespace());
            Element response = body.getChild("submitAEDataXMLAsAttachmentResponse");
            Namespace n = ((Element) response.getChildren().get(0)).getNamespace();
            jobInfo = response.getChild("AEReportJobInfo", n);

        } catch (Exception e) {
           log.error("Error while parsing the response xml", e);
        }
        return jobInfo;
    }
    
    
    /**
     * This method is invoked by {@link gov.nih.nci.cabig.caaers.esb.client.impl.JmsServiceImpl#onMessage(javax.jms.Message)}, to process the 
     * submission response from AdEERS (from ServiceMix)
     */
    public void processMessage(String message){
    	
    	  boolean success = true;
          boolean communicationError = false;
          String caaersAeReportId =""; 
          String reportId = "";
          String ticketNumber = "";
          String reportURL = "";
          String submitterEmail = "";
          
          StringBuffer sb = new StringBuffer();
          
    	 log.debug("ESB Listner - message recieved");
         log.debug(message);
         
         //find the AEReportJobInfo node from XML message
         Element jobInfo = getJobInfo(message);
         
         if(jobInfo == null){
        	 //Unknown XML format, there is a parsing error.
        	 communicationError = true;
        	 success = false;
        	 
         }else{
        	 
        	 //find the parameters such as caaersAeReportId, reportId, submitterEmail from XML request
        	 caaersAeReportId = jobInfo.getChild("CAEERS_AEREPORT_ID").getValue();
             reportId = jobInfo.getChild("REPORT_ID").getValue();
             submitterEmail = jobInfo.getChild("SUBMITTER_EMAIL").getValue();
        	 
        	 //Is a successful submission ?
        	 if (jobInfo.getChild("reportStatus").getValue().equals("SUCCESS")) {
        		 ticketNumber = jobInfo.getChild("ticketNumber").getValue();
                 reportURL = jobInfo.getChild("reportURL").getValue();
                 
        		 String submissionMessage = messageSource.getMessage("successful.reportSubmission.message",
        				 new Object[]{reportId, ticketNumber,  reportURL}, Locale.getDefault());
        		 
        		 sb.append(submissionMessage);
        		
        	 }else{
        		 success = false;
            	 //find the exception elements
            	 List<Element> exceptions = jobInfo.getChildren("jobExceptions");
            	 if(!exceptions.isEmpty()){
            		 StringBuffer exceptionMsgBuffer = new StringBuffer();
            		 for (Element ex : exceptions) {
            			 exceptionMsgBuffer.append(ex.getChild("code").getValue()).append( "  -  ").append(ex.getChild("description").getValue()).append("\n");

            			 if (ex.getChild("code").getValue().equals("caAERS-adEERS : COMM_ERR")) {
                     		communicationError=true;
                         }
                     }
            		 
            		 String submissionMessage = messageSource.getMessage("failed.reportSubmission.message", new Object[]{reportId, exceptionMsgBuffer.toString()}, Locale.getDefault());
            		 sb.append(submissionMessage);
            		 
            	 }//if exceptions
            	 
        	 }
        	 
        	 if (jobInfo.getChild("comments") != null) {
            	 String commentsMessage = messageSource.getMessage("comments.reportSubmission.message", new Object[]{jobInfo.getChild("comments").getValue()}, Locale.getDefault());
                 sb.append(commentsMessage);
             }
        	 
        	 
         }
         
         
         
         
         try {
             log.debug("Calling notfication service ..");
             messageNotificationService.sendNotificationToReporter(submitterEmail, sb.toString(),caaersAeReportId, 
            		 reportId, success, ticketNumber, reportURL,communicationError);
         } catch (Exception e) {
        	 log.error("Error while notifying ServiceMix response", e);
         }
    }
    
    
/*---------------------------#########----------------------------------------------
 * BJ : Below was the original processMessage() method before refactoring..
 * TODO: Code Reviewer, PLEASE remove the commented code, once the code-review is DONE.
 * -----------------------------******--------------------------------------------
    public void processMessage2(String message) {

        log.debug("ESB Listner - message recieved");
        log.debug(message);

        Element jobInfo = getJobInfo(message);
        String caaersAeReportId = jobInfo.getChild("CAEERS_AEREPORT_ID").getValue();
        String reportId = jobInfo.getChild("REPORT_ID").getValue();
        String submitterEmail = jobInfo.getChild("SUBMITTER_EMAIL").getValue();

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
            messageNotificationService.sendNotificationToReporter(submitterEmail, messages,
                            caaersAeReportId, reportId, success, ticketNumber, url,communicationError);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
*/
    public static void main(String[] ars) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("Report # " + 100 + " was NOT successfully submitted to AdEERS. \n\n");
            sb.append("The following problem was encountered:. \n");
            // for (Element ex:exceptions) {
            sb.append("desc 1" + "\n");
            sb.append("desc 2" + "\n");
            // }
            sb.append("\n");
            sb.append("Please correct the problem and submit the report again.\n\n");
            sb.append("See below for a technical description of the error:\n\n");

            sb.append("EXCEPTIONS\n");
            sb.append("----------\n");

            // for (Element ex:exceptions) {
            sb.append(100 + "  -  " + "desc1");
            sb.append("\n");
            sb.append(200 + "  -  " + "desc2");
            sb.append("\n");
            // }

            // if (jobInfo.getChild("comments") != null) {
            sb.append("coooooments" + "\n");
            // }
            /*
             * SAXParserFactory factory = SAXParserFactory.newInstance(); factory.setNamespaceAware(
             * true); factory.setValidating( true);
             * 
             * SAXParser parser = factory.newSAXParser(); parser.setProperty(
             * "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
             * "http://www.w3.org/2001/XMLSchema"); parser.setProperty(
             * "http://java.sun.com/xml/jaxp/properties/schemaSource",
             * "file:/Users/sakkala/tech/adeers/test.xsd");
             * 
             * 
             * 
             * XMLReader reader = parser.getXMLReader();
             * 
             * StringReader reader1 = new StringReader("<test>asdes</test>");
             * 
             * //System.out.println("SUBMITTING TO WEB SERVICE ..."); //Source attachment = new
             * StreamSource(reader1,""); ErrorHandler handler = new Validator();
             * reader.setErrorHandler(handler); reader.parse( new InputSource( reader1 ));
             * 
             */
            System.out.println(sb.toString());

            // org.xml.sax.helpers.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Required
    public void setMessageNotificationService(MessageNotificationService messageNotificationService) {
        this.messageNotificationService = messageNotificationService;
    }
    
    @Required
    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}

class Validator implements ErrorHandler {
    public void warning(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Warning**" + "  Line:    " + exception.getLineNumber() + ""
                        + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Warning encountered");
    }

    public void error(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Error**" + "  Line:    " + exception.getLineNumber() + ""
                        + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Error encountered");
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        // Bring things to a crashing halt
        System.out.println("**Parsing Fatal Error**" + "  Line:    " + exception.getLineNumber()
                        + "" + "  URI:     " + exception.getSystemId() + "" + "  Message: "
                        + exception.getMessage());
        throw new SAXException("Fatal Error encountered");
    }
}
