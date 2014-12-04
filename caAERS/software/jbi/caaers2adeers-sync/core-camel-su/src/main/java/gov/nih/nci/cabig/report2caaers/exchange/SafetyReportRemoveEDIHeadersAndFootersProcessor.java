/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;


import static gov.nih.nci.cabig.caaers2adeers.exchnage.ExchangePreProcessor.*;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SafetyReportRemoveEDIHeadersAndFootersProcessor implements Processor {

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
            exchange.setProperty(INVALID_MESSAGE, "true");
            log.warn(String.format("[Start Index : %s, End Index : %s, XML : \n %s]", e2bStIndex, e2bEndIndex, body));
			throw new Exception("E2B message not found");
		}
		body = body.substring(e2bStIndex, e2bEndIndex+E2B_END_OFFSET);

		exchange.getOut().setBody(body);
	}

	
}