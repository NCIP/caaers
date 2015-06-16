package gov.nih.nci.cabig.report2adeers.exchange;


import gov.nih.nci.cabig.report2caaers.exchange.EDIMessagePreProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Will remove the DOS specific line separators & extra whitespaces before the XML input
 */
public class ErrorParserProcessor implements Processor {
	protected static final Log log = LogFactory.getLog(ErrorParserProcessor.class);
	public final static SimpleDateFormat msgDF = new SimpleDateFormat("yyyyMMddhhmmss");
	
    public void process(Exchange exchange) throws Exception {
    	log.error("Invalid E2B file, trying to create an error ACK now.");
    	String msg_id = UUID.randomUUID().toString();
		String msg_receiver_id = "UNKNOWN";
		String msg_sender_id = "UNKNOWN";
		String today = msgDF.format(new Date());
		String msg_number = "UNKNOWN";
		String msg_date = today;
		String camelExceptionCaught = "UNKNOWN";
		
		try {
    		
    		Map<String, Object> props = exchange.getProperties();
    		msg_id = populate(props, EDIMessagePreProcessor.MSG_ID, msg_id);
    		msg_receiver_id = populate(props, EDIMessagePreProcessor.MSG_RCVR_ID, msg_receiver_id);
    		msg_sender_id = populate(props, EDIMessagePreProcessor.MSG_SNDR_ID, msg_sender_id);
    		msg_number = populate(props, EDIMessagePreProcessor.MSG_NUMB, msg_number);
    		msg_date = populate(props, EDIMessagePreProcessor.MSG_DT, msg_date);
    		camelExceptionCaught = populate(props,"CamelExceptionCaught", camelExceptionCaught);

    		//TODO: parse the body for information that is still missing.
    	} catch (Exception e) {
    		log.error("Exception while trying to handle exceptions.", e);
    		camelExceptionCaught = e.getMessage();
    	}
		
		camelExceptionCaught += "Error Processing File, not a valid e2B file; " + camelExceptionCaught;
		
		//This will be a very slow processor, it is highly inefficient but robust in the face of junk data.
    	String fixedBody= "<ichicsrack lang=\"en\">\n" +
            "<ichicsrmessageheader>\n" +
                "<messagetype>ichicsrack</messagetype>\n" +
                "<messageformatversion>1.1</messageformatversion>\n" +
                "<messageformatrelease>1.0</messageformatrelease>\n" +
                "<messagenumb>" + msg_id + "</messagenumb>\n" +
                "<messagesenderidentifier>" + msg_receiver_id + "</messagesenderidentifier>\n" +
                "<messagereceiveridentifier>" + msg_sender_id + "</messagereceiveridentifier>\n" +
                "<messagedateformat>204</messagedateformat>\n" +
                "<messagedate>"+ today +"</messagedate>\n" +
            "</ichicsrmessageheader>\n" +
            "<acknowledgment>\n" +
                "<messageacknowledgment>\n" +
                    "<icsrmessagenumb><xsl:value-of select=" + msg_number + " /></icsrmessagenumb>\n" +
                    "<localmessagenumb></localmessagenumb>\n" +
                    "<icsrmessagesenderidentifier>" + msg_sender_id + "</icsrmessagesenderidentifier>\n" +
                    "<icsrmessagereceiveridentifier>" + msg_receiver_id + "</icsrmessagereceiveridentifier>\n" +
                    "<icsrmessagedateformat>204</icsrmessagedateformat>\n" +
                    "<icsrmessagedate>" + msg_date +"</icsrmessagedate>\n" +
                    "<transmissionacknowledgmentcode>03</transmissionacknowledgmentcode>\n" +
                    "<parsingerrormessage>" + camelExceptionCaught + "</parsingerrormessage> \n" +
                "</messageacknowledgment>\n" +
            "</acknowledgment>\n" +
        "</ichicsrack>";
    	
        exchange.getOut().setBody(fixedBody);
    }

	private static String populate(Map<String, Object> props, String key,
			String cur) {
		String str = (String) props.get(key);
		if(!StringUtils.isEmpty(str)) {
			cur = str;
		}
		return cur;
	}
}
