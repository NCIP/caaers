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
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EDIMessagePreProcessor implements Processor {
	
	private static final String MSG_COMBO_ID = "msg_combo_id";

	private static String[] msgComboIdPaths = { "//safetyreportid",
										"//messagedate"};
	
	protected static final Log log = LogFactory
			.getLog(EDIMessagePreProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		log.info("inside EDIMessagePreProcessor...");
		
		// replace @@ characters with comma
		String replacedDoubleAt = body.replaceAll("@@", ",");
		
		// replace ## characters with semicolon
		String replacedDoubleHash = replacedDoubleAt.replaceAll("##", ";");
		
		
		StringBuffer mIdB = new StringBuffer();
		for (String path : msgComboIdPaths) {
			String value = XPathBuilder.xpath(path).evaluate(exchange, String.class);
			if(StringUtils.isNotBlank(value)){
        		mIdB.append(value).append("|");
        	}
		}
		String msgId = mIdB.substring(0, mIdB.length()-1); //remove the last '|' char
		
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		properties.put(MSG_COMBO_ID, msgId);
				
		exchange.getOut().setBody(replacedDoubleHash);
	}
}