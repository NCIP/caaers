package gov.nih.nci.cabig.caaers2adeers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import javax.jbi.messaging.MessageExchange;
import java.util.Map;

/**
 * Will set the correlation id in the protocol headers from Message exchange.
 */
public class CorrelationHeaderInterceptor extends AbstractPhaseInterceptor<Message> {
    protected static final Log log = LogFactory.getLog(CorrelationHeaderInterceptor.class);
    public static final String CORRELATION_ID = "c2a_correlation_id";

    public CorrelationHeaderInterceptor() {
        super(Phase.PRE_LOGICAL);
    }

    public void handleMessage(Message message) throws Fault {
        try {

            MessageExchange me = message.get(MessageExchange.class);

            String correlationId = (String)me.getProperty(CORRELATION_ID);
            System.out.println("correlationId : " + correlationId);
            if(!StringUtils.isEmpty(correlationId)) {
                Map<String, Object> headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
                System.out.println("Headers : " + String.valueOf(headers));
                if(headers != null) {
                    headers.put(CORRELATION_ID, correlationId);
                }
            }

        } catch (Exception ignore) {
            log.debug("Ignoring error : ", ignore);
        }
    }
}
