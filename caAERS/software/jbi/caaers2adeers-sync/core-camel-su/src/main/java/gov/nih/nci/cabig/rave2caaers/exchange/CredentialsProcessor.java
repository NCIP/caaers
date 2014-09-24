package gov.nih.nci.cabig.rave2caaers.exchange;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Will extract the user credentials from incoming message
 */
public class CredentialsProcessor implements Processor  {
    public static final String CAAERS_WS_USERNAME = "c2a_caaers_ws_username";
    public static final String CAAERS_WS_PASSWORD = "c2a_caaers_ws_password";

    protected static final Log log = LogFactory
            .getLog(CredentialsProcessor.class);

    public void process(Exchange exchange) throws Exception {
        //get the message body,
        String body = exchange.getIn().getBody(String.class);
        if(StringUtils.isEmpty(body)) return ;

        //extract the username and password
        int start = body.indexOf("Username>") ;
        if(start < 0) {
            log.error(String.format("Unable to obtain username, start index %s\n message body :%s", String.valueOf(start), body));
            return;
        }
        start+= 9;
        int end = body.indexOf("</", start);
        if(end < start) {
            log.error(String.format("Unable to obtain username, end index %s\n message body :%s", String.valueOf(end), body));
            return;
        }
        String username = body.substring(start,end);

        start = body.indexOf("Password", end);
        start = start > end ?  body.indexOf(">", start) : start;
        if(start < 0) {
            log.error(String.format("Unable to obtain password, start index %s\n message body :%s", String.valueOf(start), body));
            return;
        }
        end = body.indexOf("</", start);
        if(end < start) {
            log.error(String.format("Unable to obtain password, end index %s\n message body :%s", String.valueOf(end), body));
            return;
        }
        String password = body.substring(start+1,end);

        //set it in exchange header

        // set the properties in the exchange
        Map<String, Object> properties = exchange.getProperties();
        properties.put(CAAERS_WS_USERNAME, username);
        properties.put(CAAERS_WS_PASSWORD, password);
    }
}
