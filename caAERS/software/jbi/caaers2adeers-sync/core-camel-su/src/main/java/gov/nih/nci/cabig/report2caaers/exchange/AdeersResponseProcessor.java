/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;

import java.util.Date;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdeersResponseProcessor implements Processor {

    public static final String CORRELATION_ID_ATTR_NAME = "correlationId";
	public static final String CORRELATION_ID = "c2r_correlation_id";
	public static final String TODAY_DT = "c2r_today_204";
	
	protected static final Log log = LogFactory
			.getLog(AdeersResponseProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		log.info("inside AdeersResponseProcessor...");
		
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		log.debug("adding correlationId.");
		Date cDt = new Date();
		String correlationId = XPathBuilder.xpath("//payload/@"+CORRELATION_ID_ATTR_NAME).evaluate(exchange, String.class);
        if(StringUtils.isBlank(correlationId)){
        	log.debug("No correlationId found in payload. Adding current time as correlationId.");
        	correlationId = System.currentTimeMillis()+"##"+System.currentTimeMillis();
        }
		properties.put(CORRELATION_ID, correlationId);
		properties.put(TODAY_DT, cDt.toString());
				
		exchange.getOut().setBody(body);
	}

	
}