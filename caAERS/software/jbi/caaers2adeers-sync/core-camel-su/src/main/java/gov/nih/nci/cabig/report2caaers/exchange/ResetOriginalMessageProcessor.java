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

public class ResetOriginalMessageProcessor implements Processor {
	
	private static final String ORIGINAL_MSG = "original_message";
	
	protected static final Log log = LogFactory
			.getLog(ResetOriginalMessageProcessor.class);

	public void process(Exchange exchange) throws Exception {
		Map<String, Object> properties = exchange.getProperties();
		exchange.getOut().setBody(properties.get(ORIGINAL_MSG));
	}
}