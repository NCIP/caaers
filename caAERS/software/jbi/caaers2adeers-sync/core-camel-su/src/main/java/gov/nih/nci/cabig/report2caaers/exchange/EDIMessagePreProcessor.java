/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EDIMessagePreProcessor implements Processor {

	public final static SimpleDateFormat msgDF = new SimpleDateFormat("yyyyMMddhhmmss");
	public static final String MSG_ID = "c2r_msg_id";
	public static final String CORRELATION_ID_ATTR_NAME = "correlationId";
	public static final String CORRELATION_ID = "c2r_correlation_id";
	public static final String TODAY_DT = "c2r_today_204";
	public static final String MSG_NUMB = "c2r_msg_number";
	public static final String MSG_DT = "c2r_msg_date";
	public static final String MSG_SNDR_ID = "c2r_msg_sender_id";
	public static final String MSG_RCVR_ID = "c2r_msg_receiver_id";
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
		String msgComboId = mIdB.substring(0, mIdB.length()-1); //remove the last '|' char
		
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		properties.put(MSG_COMBO_ID, msgComboId);
		
		String msgNumb = XPathBuilder.xpath("//messagenumb").evaluate(exchange, String.class);
		String msgDt = XPathBuilder.xpath("//messagedate").evaluate(exchange, String.class);
		String msgSndrId = XPathBuilder.xpath("//messagesenderidentifier").evaluate(exchange, String.class);
		String msgRcvrId = XPathBuilder.xpath("//messagereceiveridentifier").evaluate(exchange, String.class);
		
		properties.put(MSG_NUMB, msgNumb);
		properties.put(MSG_DT, msgDt);
		properties.put(MSG_SNDR_ID, msgSndrId);
		properties.put(MSG_RCVR_ID, msgRcvrId);
		
		log.debug("adding correlationId.");
		Date cDt = new Date();
		
        String correlationId = msgNumb+"##"+msgDt;
        
		properties.put(CORRELATION_ID, correlationId);
		properties.put(MSG_ID, UUID.randomUUID().toString());
		properties.put(TODAY_DT, msgDF.format(cDt));
				
		exchange.getOut().setBody(replacedDoubleHash);
	}
}