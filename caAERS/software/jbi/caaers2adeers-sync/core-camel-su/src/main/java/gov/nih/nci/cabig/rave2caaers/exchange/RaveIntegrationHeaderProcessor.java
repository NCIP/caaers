/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.rave2caaers.exchange;
import static org.apache.camel.builder.xml.XPathBuilder.xpath;
import java.util.Map;

import gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor;
import gov.nih.nci.cabig.caaers2adeers.exchnage.HeaderGeneratorProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Responsible for adding exchange headers based on RAVE integration message
 */
public class RaveIntegrationHeaderProcessor implements Processor {
	
	public static final String SYNC_HEADER = ExchangePreProcessor.SYNC_HEADER;
	public static final String CORRELATION_ID = ExchangePreProcessor.CORRELATION_ID;
	public static final String OPERATION_NAME = ExchangePreProcessor.OPERATION_NAME;
	public static final String ENTITY_NAME = ExchangePreProcessor.ENTITY_NAME;
    public static final String INVALID_MESSAGE = ExchangePreProcessor.INVALID_MESSAGE;


	public static final String SAFETY_REPORT_ID_OPERATION_NAME = "generateSafetyReportId";
	public static final String SAE_EVALUATION_OPERATION_NAME = "saveAndEvaluateAEs";
	public static final String SAE_INITIATION_OPERATION_NAME = "evaluateAndInitiateAEs";
	public static final String INITIATE_SAFETY_REPORT_OPERATION_NAME = "initiateSafetyReportAction";
	
	protected static final Log log = LogFactory.getLog(RaveIntegrationHeaderProcessor.class);

    private String saeNS = "http://schema.integration.caaers.cabig.nci.nih.gov/saerules";
    private String idNS = "http://schema.integration.caaers.cabig.nci.nih.gov/aereportid";
    private String rpNS = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport";

	public void process(Exchange exchange) throws Exception {
        log.debug("Starting Rave Integration Message Header processing");

        exchange.setProperty(SYNC_HEADER , "sync");
        exchange.setProperty(CORRELATION_ID, HeaderGeneratorProcessor.makeCorrelationId());
        exchange.setProperty(ExchangePreProcessor.ENTRED_ON, System.currentTimeMillis());

        try {

            if(xpath("//ns:saveAndEvaluateAEs").namespace("ns", saeNS).matches(exchange)) {
                exchange.setProperty(OPERATION_NAME, SAE_EVALUATION_OPERATION_NAME);
                exchange.setProperty(ENTITY_NAME, "SAEEvaluationService");
            } else if (xpath("//ns:generateSafetyReportId").namespace("ns", idNS).matches(exchange))   {
                exchange.setProperty(OPERATION_NAME, SAFETY_REPORT_ID_OPERATION_NAME);
                exchange.setProperty(ENTITY_NAME, "SafetyReport");
            } else if (xpath("//ns:initiateSafetyReportAction").namespace("ns", rpNS).matches(exchange))   {
                exchange.setProperty(OPERATION_NAME, INITIATE_SAFETY_REPORT_OPERATION_NAME);
                exchange.setProperty(ENTITY_NAME, "SafetyReport");
            } else if (xpath("//ns:EvaluateAndInitiate").namespace("ns", saeNS).matches(exchange)) {
            	exchange.setProperty(OPERATION_NAME, SAE_INITIATION_OPERATION_NAME);
                exchange.setProperty(ENTITY_NAME, "SAEEvaluationService");
            }  else {
                exchange.setProperty(INVALID_MESSAGE, "true");
            }
        } catch (Exception ignore) {
           exchange.setProperty(INVALID_MESSAGE, "true");
           log.debug("Ignoring invalid xml", ignore);
        }

		if (log.isDebugEnabled())
			log.debug("Completing Rave Integration Message Header processing\n Exchange properties :" + String.valueOf(exchange.getProperties()));

	}



}