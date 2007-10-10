package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import java.util.List;

/**
 * MessageBroadCaster taken from c3pr. Refactoring pending.
 *
 * @author Sujith Vellat Thayyilthodi
 */
public class MessageBroadcastServiceImpl extends JmsServiceImpl implements MessageBroadcastService {
    private static final Log log = LogFactory.getLog(MessageBroadcastServiceImpl.class);

    public MessageBroadcastServiceImpl(ConnectionFactory connectionFactory, Destination sendQueue, Destination recvQueue) {
        super(connectionFactory, sendQueue, recvQueue);
    }

    public void broadcast(String message) throws BroadcastException {
        // TODO Auto-generated method stub
            log.debug("calling sendJms method...");
            sendJms(message);
        
    }

    public List<String> getBroadcastStatus() {
        // TODO Auto-generated method stub
        if (!isConsumer()) {
            log.warn("no receive queue provided");
        }
        return messages;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setRecvQueue(Destination recvQueue) {
        this.recvQueue = recvQueue;
    }

    public void setSendQueue(Destination sendQueue) {
        this.sendQueue = sendQueue;
    }

}
