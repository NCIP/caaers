package gov.nih.nci.cabig.caaers2adeers;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * This bean will pre-process the messages
 */
public class HeaderGeneratorProcessor implements Processor {

    protected static final Log log = LogFactory.getLog(HeaderGeneratorProcessor.class);

    public void process(Exchange exchange) throws Exception {
        Map<String,Object> headers = exchange.getIn().getHeaders();
        for(Map.Entry<String, Object> e : exchange.getProperties().entrySet()){
            if(e.getKey().startsWith("c2a_")) headers.put(e.getKey(), e.getValue());
        }
        if(log.isDebugEnabled()) log.debug("Headers :" + String.valueOf(exchange.getIn().getHeaders()));
    }
}
