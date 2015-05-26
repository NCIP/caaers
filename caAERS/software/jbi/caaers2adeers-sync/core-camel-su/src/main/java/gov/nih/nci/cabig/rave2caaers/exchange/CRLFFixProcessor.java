package gov.nih.nci.cabig.rave2caaers.exchange;


import java.io.InputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Will remove the DOS specific line separators & extra whitespaces before the XML input
 */
public class CRLFFixProcessor implements Processor {
	protected static final Log log = LogFactory.getLog(CRLFFixProcessor.class);
	final static String defaultEncoding = "UTF-8";
	
    public void process(Exchange exchange) throws Exception {
    	String body = null;
    	InputStream inputStream = exchange.getIn().getBody(InputStream.class);
    	try {
    	    BOMInputStream bOMInputStream = new BOMInputStream(inputStream);
    	    ByteOrderMark bom = bOMInputStream.getBOM();
    	    String encoding = bom == null ? defaultEncoding : bom.getCharsetName();
    	    log.debug("Processing XML message with encoding; " + encoding);
    	    body = IOUtils.toString(bOMInputStream, encoding); 
    	    bOMInputStream.close();
    	} finally {
    	    inputStream.close();
    	}
    	log.debug("xml body before linefeed fix; " + body);
        String fixedBody = StringUtils.chomp(body);
        fixedBody = StringUtils.remove(fixedBody, '\r');
        fixedBody = StringUtils.trim(fixedBody);
        exchange.getOut().setBody(fixedBody);
    }
}
