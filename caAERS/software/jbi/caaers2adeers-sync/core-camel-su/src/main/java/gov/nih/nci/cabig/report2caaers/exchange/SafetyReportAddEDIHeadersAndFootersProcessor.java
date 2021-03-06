/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SafetyReportAddEDIHeadersAndFootersProcessor implements Processor {	

	protected static final Log log = LogFactory
			.getLog(SafetyReportAddEDIHeadersAndFootersProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);		
		log.info("inside SafetyReportAddEDIHeadersAndFootersProcessor...");
		exchange.getOut().setBody(body);
	}
	
}