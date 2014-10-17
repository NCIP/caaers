package gov.nih.nci.cabig.rave2caaers.exchange;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;

/**
 * Will remove the DOS specific line separators
 */
public class CRLFFixProcessor  implements Processor {
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        String fixedBody = StringUtils.chomp(body);
        fixedBody = StringUtils.remove(fixedBody, '\r');
        fixedBody = StringUtils.trim(fixedBody);
        exchange.getOut().setBody(fixedBody);
    }
}
