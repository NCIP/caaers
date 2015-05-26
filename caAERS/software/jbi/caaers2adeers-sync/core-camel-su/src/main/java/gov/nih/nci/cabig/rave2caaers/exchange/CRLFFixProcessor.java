package gov.nih.nci.cabig.rave2caaers.exchange;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;

/**
 * Will remove the DOS specific line separators & extra whitespaces before the XML input
 */
public class CRLFFixProcessor  implements Processor {
	final static String defaultEncoding = "UTF-8";
	
    public void process(Exchange exchange) throws Exception {
    	String body = null;
    	InputStream inputStream = exchange.getIn().getBody(InputStream.class);
    	try {
    	    BOMInputStream bOMInputStream = new BOMInputStream(inputStream);
    	    ByteOrderMark bom = bOMInputStream.getBOM();
    	    String encoding = bom == null ? defaultEncoding : bom.getCharsetName();
    	    body = IOUtils.toString(bOMInputStream, encoding); 
    	    bOMInputStream.close();
    	} finally {
    	    inputStream.close();
    	}
        String fixedBody = StringUtils.chomp(body);
        fixedBody = StringUtils.remove(fixedBody, '\r');
        fixedBody = StringUtils.trim(fixedBody);
        exchange.getOut().setBody(fixedBody);
    }
}
