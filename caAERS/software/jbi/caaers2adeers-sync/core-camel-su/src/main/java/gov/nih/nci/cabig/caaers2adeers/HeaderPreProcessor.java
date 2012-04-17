package gov.nih.nci.cabig.caaers2adeers;

import com.sun.org.apache.xpath.internal.jaxp.XPathExpressionImpl;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

/**
 * This bean will pre-process the messages
 */
public class HeaderPreProcessor implements Processor {

    public static final String SYNC_HEADER = "c2a_sync_mode";
    public static final String CORRELATION_ID = "c2a_correlation_id";
    public static final String CAAERS_WS_USERNAME = "c2a_caaers_ws_username";
    public static final String CAAERS_WS_PASSWORD = "c2a_caaers_ws_password";
    public static final String ADEERS_WS_USERNAME = "c2a_adeers_ws_username";
    public static final String ADEERS_WS_PASSWORD = "c2a_adeers_ws_password";

    
    private String caaersWSUser;
    private String caaersWSPassword;
    private String adeersWSUser;
    private String adeersWSPassword;

    protected static final Log log = LogFactory.getLog(HeaderPreProcessor.class);


    public void process(Exchange exchange) throws Exception {
        log.debug("Processing message headers [caaersWSUser :"  + caaersWSUser
                + "caaersWSPassword :"  + caaersWSPassword
                + ", adeersWSUser :" + adeersWSUser
                + ", adeersWSPassword " + adeersWSPassword);

        Message inMessage = exchange.getIn();
        inMessage.setHeader(CAAERS_WS_USERNAME, caaersWSUser);
        inMessage.setHeader(CAAERS_WS_PASSWORD, caaersWSPassword);
        inMessage.setHeader(ADEERS_WS_USERNAME, adeersWSUser);
        inMessage.setHeader(ADEERS_WS_PASSWORD, adeersWSPassword);
        boolean isSync = XPathBuilder.xpath("/payload/request/operation/@mode = 'sync'").matches(exchange);
        log.debug("syncMode = " + isSync);
        inMessage.setHeader(SYNC_HEADER, isSync ? "sync" : "async");

        //only add corelationId if it is not alreay present.
        Object correlationId = inMessage.getHeader(CORRELATION_ID);
        if(correlationId == null) inMessage.setHeader(CORRELATION_ID, System.currentTimeMillis());

    }

    public String getCaaersWSUser() {
        return caaersWSUser;
    }

    public void setCaaersWSUser(String caaersWSUser) {
        this.caaersWSUser = caaersWSUser;
    }

    public String getCaaersWSPassword() {
        return caaersWSPassword;
    }

    public void setCaaersWSPassword(String caaersWSPassword) {
        this.caaersWSPassword = caaersWSPassword;
    }

    public String getAdeersWSUser() {
        return adeersWSUser;
    }

    public void setAdeersWSUser(String adeersWSUser) {
        this.adeersWSUser = adeersWSUser;
    }

    public String getAdeersWSPassword() {
        return adeersWSPassword;
    }

    public void setAdeersWSPassword(String adeersWSPassword) {
        this.adeersWSPassword = adeersWSPassword;
    }
}
