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
	
	protected static final Log log = LogFactory
			.getLog(TrimWhitespaceMessageProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		int xmlStartIndex = body.indexOf("<soapenv:Envelope");
		int xmlEndIndex = body.indexOf("</soapenv:Envelope>") + 19;
		String fixedBody = body.substring(xmlStartIndex,xmlEndIndex);
			
		properties.put(SYNC_HEADER, "sync");

		log.debug("adding correlationId.");
		Object correlationId = System.currentTimeMillis() + "";
		properties.put(CORRELATION_ID, correlationId);

		if (log.isDebugEnabled())
			log.debug("Exchange properties :" + String.valueOf(properties));
		
		exchange.getOut().setBody(fixedBody);
	}

}