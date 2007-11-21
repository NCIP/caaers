package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Code taken from c3pr. Needs refactoring
 *
 * @author Sujith Vellat Thayyilthodi
 */
public class JmsServiceImpl implements MessageListener {
    private static final Log log = LogFactory.getLog(JmsServiceImpl.class);

    protected ConnectionFactory connectionFactory;
    protected Destination sendQueue;
    protected Destination recvQueue;
    /*
     * TODO: this is a shared singleton -- I'm nearly positive it shouldn't be caching a connection
     * or any related objects (session, messages, maybe others) in a local field. (RMS20070326)
     */
    private Connection connection;
    private Session session;
    protected List<String> messages = new ArrayList<String>();
    private MessageConsumer consumer;
    private MessageProducer producer;

    public JmsServiceImpl(ConnectionFactory connectionFactory, Destination sendQueue, Destination recvQueue) {
        this.connectionFactory = connectionFactory;
        this.sendQueue = sendQueue;
        this.recvQueue = recvQueue;
        //initialize();
    }

    public void sendJms(String xml) throws BroadcastException {
        initialize();
        if (!isProvider()) {
            throw new BroadcastException("no send queue provided");
        }
        /*
        * Create sender and text message.
        */
        try {
            TextMessage message = session.createTextMessage();
            message.setText(xml);
            // Send a non-text control message indicating end of messages.
            producer.send(message);
        } catch (Exception e) {
            throw new BroadcastException("Error while sending message\n" + xml, e);
        }
    }

    public void close() throws BroadcastException {
        try {
            connection.close();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            throw new BroadcastException("Error while closing connection", e);
        }
    }

    public void onMessage(Message msg) {
        // TODO Auto-generated method stub
        TextMessage message = null;
        if (msg instanceof TextMessage) {
            message = (TextMessage) msg;
            try {
                messages.add(message.getText());
            } catch (JMSException e) {
                throw new BroadcastException("Error while retrieving message text", e);
            }
        } else if (msg != null) {
            log.debug(msg.getClass().getName() + " not handled ");
        }
    }

    public void initialize() throws BroadcastException {
        if (connectionFactory == null) {
            throw new BroadcastException("JMS Connection Factory not provided..");
        }
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if (sendQueue != null) {
                producer = session.createProducer(sendQueue);
            } else {
                log.warn("no send queue provided....");
            }
            if (recvQueue != null) {
                consumer = session.createConsumer(recvQueue);
                consumer.setMessageListener(this);
                connection.start();
            } else {
                log.warn("no receive queue provided....");
            }
        } catch (JMSException e) {
            //throw new BroadcastException("Exception occurred while registering: " ,e);
            //Consuming this exception here.... TODO: why?  (RMS20070326)
            log.error("Exception while setting up JmsServiceImpl state", e);
        } catch (Exception e) {
            throw new BroadcastException("Exception occurred while registering: ", e);
        }
    }

    public boolean isConsumer() {
        return consumer != null;
    }

    public boolean isProvider() {
        return producer != null;
    }

}
