package gov.nih.nci.cabig.caaers.esb.client;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ESBMessageConsumerImpl implements ESBMessageConsumer {

    private MessageNotificationService messageNotificationService;

    protected final Log log = LogFactory.getLog(getClass());

    private Element getJobInfo(String message) {

        SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader = new StringReader(message);
        Element jobInfo = null;
        try {
            org.jdom.Document jdomDocument = saxBuilder.build(stringReader);
            org.jdom.Element root = jdomDocument.getRootElement();

            Element body = root.getChild("Body", root.getNamespace());
            Element response = body.getChild("submitAEDataXMLAsAttachmentResponse");
            Namespace n = ((Element) response.getChildren().get(0)).getNamespace();
            jobInfo = response.getChild("AEReportJobInfo", n);

        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jobInfo;
    }

    public void processMessage(String message) {

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

    public void setMessageNotificationService(MessageNotificationService messageNotificationService) {
        this.messageNotificationService = messageNotificationService;
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
