/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SafetyReportRemoveEDIHeadersAndFootersProcessor implements Processor {
	
	public static final String SYNC_HEADER = "sync_mode";
	public static final String CORRELATION_ID = "c2a_correlation_id";
	public static final String OPERATION_NAME = "c2a_operation";
	public static final String ENTITY_NAME = "c2a_entity";
	
	public static final String E2B_ST="<ichicsr";
	public static final String E2B_END="</ichicsr>";
	public static final int E2B_END_OFFSET = E2B_END.length();
	
	protected static final Log log = LogFactory
			.getLog(SafetyReportRemoveEDIHeadersAndFootersProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		log.info("inside SafetyReportRemoveEDIHeadersAndFootersProcessor...");
		int e2bStIndex = body.indexOf(E2B_ST);
		int e2bEndIndex = body.indexOf(E2B_END);
		if(e2bStIndex < 0 || e2bEndIndex < 0) {
            log.warn(String.format("[Start Index : %s, End Index : %s, XML : \n %s]", e2bStIndex, e2bEndIndex, body));
			throw new Exception("E2B message not found");
		}
		body = body.substring(e2bStIndex, e2bEndIndex+E2B_END_OFFSET);
		
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		//TODO change operation name according to E2B message
		properties.put(OPERATION_NAME, "submitSafetyReport");
		properties.put(ENTITY_NAME, "SafetyReport");
		properties.put(SYNC_HEADER, "sync");

		log.debug("adding correlationId.");
		Object correlationId = System.currentTimeMillis() + "";
		properties.put(CORRELATION_ID, correlationId);
				
		exchange.getOut().setBody(body);
	}

	
}