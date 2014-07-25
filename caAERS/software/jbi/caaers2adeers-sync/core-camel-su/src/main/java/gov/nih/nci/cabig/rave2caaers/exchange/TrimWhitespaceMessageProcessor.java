/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.rave2caaers.exchange;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TrimWhitespaceMessageProcessor implements Processor {
	
	public static final String SYNC_HEADER = "sync_mode";
	public static final String CORRELATION_ID = "c2a_correlation_id";
	public static final String OPERATION_NAME = "c2a_operation";
	public static final String ENTITY_NAME = "c2a_entity";
	private static final String SAFETY_REPORT_ID_OPERATION_NAME = "generateSafetyReportId";
	private static final String SAE_EVALUATION_OPERATION_NAME = "saveAndEvaluateAEs";
	private static final String SUBMIT_SAFETY_REPORT_OPERATION_NAME = "submitSafetyReport";
	private static final String INITIATE_SAFETY_REPORT_OPERATION_NAME = "initiateSafetyReportAction";

	protected static final Log log = LogFactory
			.getLog(TrimWhitespaceMessageProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		int xmlStartIndex = body.indexOf("<soapenv:Envelope");
		int xmlEndIndex = body.indexOf("</soapenv:Envelope>") + 19;
		String fixedBody = body.substring(xmlStartIndex,xmlEndIndex);

		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		if(fixedBody.contains(SAFETY_REPORT_ID_OPERATION_NAME)) {
			properties.put(OPERATION_NAME, SAFETY_REPORT_ID_OPERATION_NAME);
			properties.put(ENTITY_NAME, "SafetyReportId");
		} else if(fixedBody.contains(SAE_EVALUATION_OPERATION_NAME)) {
			properties.put(OPERATION_NAME, SAE_EVALUATION_OPERATION_NAME);
			properties.put(ENTITY_NAME, "SAEEvaluationService");
		} else if(fixedBody.contains(SUBMIT_SAFETY_REPORT_OPERATION_NAME)) {
			properties.put(OPERATION_NAME, SUBMIT_SAFETY_REPORT_OPERATION_NAME);
			properties.put(ENTITY_NAME, "safetyReport");
		} if(fixedBody.contains(INITIATE_SAFETY_REPORT_OPERATION_NAME)) {
			properties.put(OPERATION_NAME, INITIATE_SAFETY_REPORT_OPERATION_NAME);
			properties.put(ENTITY_NAME, "safetyReport");
		}
		
		properties.put(SYNC_HEADER, "sync");

		log.debug("adding correlationId.");
		Object correlationId = System.currentTimeMillis() + "";
		properties.put(CORRELATION_ID, correlationId);

		if (log.isDebugEnabled())
			log.debug("Exchange properties :" + String.valueOf(properties));

		exchange.getOut().setBody(fixedBody);
	}

}