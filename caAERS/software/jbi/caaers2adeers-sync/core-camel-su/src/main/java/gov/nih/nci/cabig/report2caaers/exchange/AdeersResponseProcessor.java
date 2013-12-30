/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.report2caaers.exchange;

import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLog.Stage;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLogMessage;
import gov.nih.nci.cabig.caaers2adeers.track.IntegrationLogMessageDao;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;

public class AdeersResponseProcessor implements Processor {
    
	public final static SimpleDateFormat msgDF = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final String MSG_ID = "c2r_msg_id";
	public static final String TODAY_DT = "c2r_today_204";
	public static final String MSG_NUMB = "c2r_msg_number";
	public static final String MSG_DT = "c2r_msg_date";
	public static final String MSG_SNDR_ID = "c2r_msg_sender_id";
	public static final String MSG_RCVR_ID = "c2r_msg_receiver_id";
	
	XPathFactory factory = XPathFactory.newInstance();
	
	protected static final Log log = LogFactory
			.getLog(AdeersResponseProcessor.class);

	public void process(Exchange exchange) throws Exception {
		// just get the body as a string
		String body = exchange.getIn().getBody(String.class);
		log.info("inside AdeersResponseProcessor...");
		
		// set the properties in the exchange
		Map<String, Object> properties = exchange.getProperties();
		
		String msgComboId = XPathBuilder.xpath("//MESSAGE_COMBO_ID").evaluate(exchange, String.class);
		IntegrationLogMessageDao integrationLogMessageDao = (IntegrationLogMessageDao)exchange.getContext().getRegistry().lookup("integrationLogMessageDao");
		log.info("MESSAGE_COMBO_ID is " + msgComboId);
		IntegrationLogMessage integrationLogMessage = integrationLogMessageDao.findByComboId(msgComboId, Stage.REQUEST_RECEIVED);
		log.info("integrationLogMessage is " + integrationLogMessage.getMessage());
		
		if(StringUtils.isNotBlank(integrationLogMessage.getMessage())) {
			String msgNumb = evaluateXPath("//messagenumb", integrationLogMessage.getMessage());
			String msgDt = evaluateXPath("//messagedate", integrationLogMessage.getMessage());
			String msgSndrId = evaluateXPath("//messagesenderidentifier", integrationLogMessage.getMessage());
			String msgRcvrId = evaluateXPath("//messagereceiveridentifier", integrationLogMessage.getMessage());
			
			properties.put(MSG_NUMB, msgNumb);
			properties.put(MSG_DT, msgDt);
			properties.put(MSG_SNDR_ID, msgSndrId);
			properties.put(MSG_RCVR_ID, msgRcvrId);			
		}
		
		log.debug("adding correlationId.");
		Date cDt = new Date();
		
		properties.put(MSG_ID, UUID.randomUUID().toString());
		properties.put(TODAY_DT, msgDF.format(cDt));
		
		log.info("properties in exchange are " + properties);
				
		exchange.getOut().setBody(body);
	}
	
	private String evaluateXPath(String xpathStr, String xmlStr) throws XPathExpressionException {		
		XPath xpath = factory.newXPath();
		InputSource inputSource = new InputSource(new StringReader(xmlStr));
		return (String) xpath.evaluate(xpathStr, inputSource, XPathConstants.STRING);
	}

	
}