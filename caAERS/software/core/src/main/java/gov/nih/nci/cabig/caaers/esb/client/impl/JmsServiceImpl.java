package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.ESBMessageConsumer;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.Vector;

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

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Code taken from c3pr. Needs refactoring
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class JmsServiceImpl implements MessageListener {
    public ConnectionFactory connectionFactory = null;

    public Destination sendQueue = null;

    public Destination recvQueue = null;

    private Connection connection = null;

    private Session session = null;

    public Vector messages = new Vector();

    private MessageConsumer consumer = null;

    private MessageProducer producer = null;

    private ESBMessageConsumer messageConsumer;
    
    protected Configuration configuration;

    public void setMessageConsumer(ESBMessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    /*
    public void sendJms(String xml) throws BroadcastException {
 
        try {
            initialize();
            TextMessage message = session.createTextMessage();
            System.out.println("XML Payload....");
            message.setText(xml);

             //Send a non-text control message indicating end of messages.

            System.out.println("sending jms....");
            producer.send(message);
            System.out.println("jms sent....");
        } catch (javax.jms.IllegalStateException e) {
            // System.out.print("IN EXCEPTION ...");

            // throw new BroadcastException(e.getMessage(),e);
            // If service server is restarted the created session will get corrupted.
            // Client uses the corrupted session , so create a new session.
            session = null;
            try {
                initialize();
                sendJms(xml);
            } catch (JMSException e1) {
                throw new BroadcastException(e1.getMessage(), e1);
            }
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            throw new BroadcastException(e.getMessage(), e);
        }
    }
    */
	public void sendJms(String xml) throws BroadcastException{
        
         //Create sender and text message.
         
        try {
            TextMessage message = session.createTextMessage();
            System.out.println("XML Payload....");
			message.setText(xml);
            
            //Send a non-text control message indicating end of messages.
             
			System.out.println("sending jms....");
            producer.send(message);
            System.out.println("jms sent....");
        }catch(Exception e){
        	throw new BroadcastException(e.getMessage(),e);
        }
	}
    public void close() throws BroadcastException {
        try {
            connection.close();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            throw new BroadcastException(e.getMessage(), e);
        }
    }

    public void onMessage(Message msg) {
        // TODO Auto-generated method stub
        System.out.println("jms recieved..");
        TextMessage message = null;
        if (msg instanceof TextMessage) {
            message = (TextMessage) msg;
            System.out.println("XML Payload....");
            try {
                System.out.println(message.getText());
                messages.add(message.getText());
                if (this.messageConsumer != null) {
                    this.messageConsumer.processMessage(message.getText());
                } else {
                    System.out.println("No Message Consumer Provided...");
                }
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("not a kind of text message..");
        }
    }

    public void initialize() throws BroadcastException, JMSException {

        if (this.session != null) {
            // System.out.println("SESSION IS NOT NULL .." );
            return;
        }
        System.out.println("initializing esb jms client....");

        if (connectionFactory == null) {        	
        	//Monish Dombla CAAERS-145
        	String esbURL = configuration.get(Configuration.ESB_URL);
        	if("".equals(esbURL) || esbURL == null){
        		throw new BroadcastException("ESB URL not specified");
        	}
        	ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
            mqConnectionFactory.setBrokerURL(configuration.get(Configuration.ESB_URL));
            this.connectionFactory = mqConnectionFactory;            
        }
        try {
            System.out.println("creating connection and session....");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if (sendQueue != null) {
                producer = session.createProducer(sendQueue);
            } else {
                System.out.println("no send queue provided....");
            }
            if (recvQueue != null) {
                consumer = session.createConsumer(recvQueue);
                consumer.setMessageListener(this);
                System.out.println("starting connection....");
                connection.start();
                System.out.println("connection started and subscriber registered....");
            } else {
                System.out.println("no recieve queue provided....");
            }
        } catch (JMSException e) {
            throw new BroadcastException("Exception occurred while registering: ", e);
        } catch (Exception e) {
            throw new BroadcastException("Exception occurred while registering: ", e);
        }
    }

    public boolean isConsumer() {
        if (consumer == null) {
            return false;
        }
        return true;
    }

    public boolean isProvider() {
        if (producer == null) {
            return false;
        }
        return true;
    }
}