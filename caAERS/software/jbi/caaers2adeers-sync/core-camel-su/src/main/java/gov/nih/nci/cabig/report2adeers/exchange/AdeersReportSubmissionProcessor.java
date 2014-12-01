package gov.nih.nci.cabig.report2adeers.exchange;

import gov.nih.nci.ctep.adeers.client.AEReportXMLServiceSoapBindingStub;
import gov.nih.nci.ctep.adeers.client.AEReportXMLService_ServiceLocator;
import gov.nih.nci.ctep.adeers.client.ReportingMode;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Will submit the exchange body to AdEERS Report Service
 */
public class AdeersReportSubmissionProcessor implements Processor {

    public static final String AE_REPORT_ID = "c2a_ae_report_id";
    public static final String REPORT_ID = "c2a_caaersr_id";
    public static final String REPORT_WITHDRAW = "c2a_withdraw";
    public static final String REPORT_EXTERNAL_ENDPOINT = "c2a_external_systems";
    public static final String REPORT_SUBMITTER_EMAIL = "c2a_submitter_email";
    public static final String REPORT_MESSAGE_COMBO_ID = "c2a_message_combo_id";
    public static final String REPORT_SUBMISSION_STATUS = "c2a_submission_status";
    public static final String REPORT_SUBMISSION_ERROR_CODE = "c2a_submission_error_code";
    public static final String REPORT_SUBMISSION_ERROR_MESSAGE = "c2a_submission_error_msg";
    public static final String REPORT_SUBMISSION_ERROR_DETAILS = "c2a_submission_error_details";

    protected static final Log log = LogFactory.getLog(AdeersReportSubmissionProcessor.class);

    public void process(Exchange exchange) throws Exception {
        log.debug("Starting AdEERS report submission processing");

        String externalEndpoint = (String)exchange.getProperty(REPORT_EXTERNAL_ENDPOINT);

        String inputXML =  exchange.getIn().getBody(String.class); //get the input from body
        String outputXML = inputXML;


        AEReportXMLServiceSoapBindingStub binding;
        String url = "";
        try {
            String[] endpointParts = StringUtils.splitByWholeSeparator(externalEndpoint, "::");

            url = endpointParts[0];
            String uid = endpointParts[1];
            String pwd = endpointParts[2];
            log.debug(String.format("URL:[%s], uid :[%s], pwd:[%s]", url, uid, pwd));

            binding = (AEReportXMLServiceSoapBindingStub)   new AEReportXMLService_ServiceLocator(url).getAEReportXMLService();
            binding.setTimeout(60000);   // Time out after a minute
            binding.setUsername(uid);
            binding.setPassword(pwd);

            StringReader reader = new StringReader(inputXML);
            Source attachment = new StreamSource(reader,"");
            String withdraw = (String)exchange.getProperty(REPORT_WITHDRAW);

            if(log.isDebugEnabled())   {
               log.debug("MESSAGE TO ADEERS : ======================================================\n" + inputXML + "\n===================================================");
            }

            if(StringUtils.equals("true", withdraw)) {
             binding.withdrawAEReport(attachment);
            } else {
               binding.submitAEDataXMLAsAttachment(ReportingMode.SYNCHRONOUS, attachment);
            }

            outputXML = binding._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();
            if(log.isDebugEnabled()) {
                log.info("RESPONSE FROM ADEERS : ======================================================\n" + outputXML + "\n===================================================");
            }
            exchange.getIn().setHeader(REPORT_SUBMISSION_STATUS, "SUCCESS");
            exchange.setProperty(REPORT_SUBMISSION_STATUS, "SUCCESS");

        } catch (Exception jre) {
            log.error("Unable to access the service URL : " + url, jre);
            populateError(exchange, jre);

        }


        exchange.getOut().setHeaders(exchange.getIn().getHeaders());

        if(StringUtils.isNotEmpty(outputXML))  {
            // we are have a response from AdEERS, so update the body
           if(log.isDebugEnabled()) {
               log.debug("\nChanging out body : \n " + outputXML);
           }
           exchange.getOut().setBody(outputXML);
        }


    }

    private void populateError(Exchange exchange, Exception e) {

        exchange.getIn().setHeader(REPORT_SUBMISSION_STATUS, "ERROR");
        exchange.getIn().setHeader(REPORT_SUBMISSION_ERROR_CODE, "caAERS-adEERS : COMM_ERR");
        exchange.getIn().setHeader(REPORT_SUBMISSION_ERROR_MESSAGE, "adEERS WebService Communication Failure");
        exchange.getIn().setHeader(REPORT_SUBMISSION_ERROR_DETAILS, e.toString());

        exchange.setProperty(REPORT_SUBMISSION_STATUS, "ERROR");
        exchange.setProperty(REPORT_SUBMISSION_ERROR_CODE, "caAERS-adEERS : COMM_ERR");
        exchange.setProperty(REPORT_SUBMISSION_ERROR_MESSAGE, "adEERS WebService Communication Failure");
        exchange.setProperty(REPORT_SUBMISSION_ERROR_DETAILS, e.toString());


    }

}
