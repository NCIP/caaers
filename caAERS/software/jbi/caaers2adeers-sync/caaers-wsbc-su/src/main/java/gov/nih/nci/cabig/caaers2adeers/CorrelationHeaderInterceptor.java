package gov.nih.nci.cabig.caaers2adeers;

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
    public static final String CORRELATION_ID = "c2a_correlation_id";

    public CorrelationHeaderInterceptor() {
        super(Phase.POST_PROTOCOL);
    }

    public void handleMessage(Message message) throws Fault {
        MessageExchange me = message.get(MessageExchange.class);
        String correlationId = (String)me.getProperty(CORRELATION_ID);
        if(!StringUtils.isEmpty(correlationId)) {
            Map<String, Object> headers = (Map<String, Object>) message.get(Message.PROTOCOL_HEADERS);
            if(headers != null) {
                headers.put(CORRELATION_ID, correlationId);
            }
        }
    }
}