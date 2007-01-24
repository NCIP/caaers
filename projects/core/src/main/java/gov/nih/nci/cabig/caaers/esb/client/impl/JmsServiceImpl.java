package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;

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


/**
 * Code taken from c3pr. Needs refactoring
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class JmsServiceImpl implements MessageListener{
    public ConnectionFactory connectionFactory = null;
    public Destination sendQueue = null;
    public Destination recvQueue = null;    
	private Connection connection = null;
    private Session session = null;
	public Vector messages=new Vector();
    private MessageConsumer consumer = null;
    private MessageProducer producer = null;
    
    public JmsServiceImpl() {
    	initialize();
    }

    public void sendJms(String xml) throws BroadcastException{
        /*
         * Create sender and text message.
         */
        try {
            TextMessage message = session.createTextMessage();
            System.out.println("XML Payload....");
			message.setText(xml);
            /*
             * Send a non-text control message indicating end of messages.
             */
			System.out.println("sending jms....");
            producer.send(message);
            System.out.println("jms sent....");
        }catch(Exception e){
        	throw new BroadcastException(e.getMessage(),e);
        }
	}
	public void close() throws BroadcastException{
		try {
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
        	throw new BroadcastException(e.getMessage(),e);
		}
	}
	public void onMessage(Message msg){
		// TODO Auto-generated method stub
		System.out.println("jms recieved..");
		TextMessage message=null;
        if (msg instanceof TextMessage) {
			message = (TextMessage) msg;
	        System.out.println("XML Payload....");
	        try {
				System.out.println(message.getText());
				messages.add(message.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("not a kind of text message..");
		}
	}
	
	public void initialize() throws BroadcastException{
        System.out.println("initializing esb jms client....");
		if(connectionFactory==null){
			throw new BroadcastException("JMS Connection Factory not provided..");
		}
        try {
            System.out.println("creating connection and session....");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            if(sendQueue!=null){
            	producer = session.createProducer(sendQueue);
            }else{
                System.out.println("no send queue provided....");
            }
            if(recvQueue!=null){
                consumer = session.createConsumer(recvQueue);
                consumer.setMessageListener(this);
                System.out.println("starting connection....");
                connection.start();
                System.out.println("connection started and subscriber registered....");
            }else{
                System.out.println("no recieve queue provided....");
            }
        }
        catch (JMSException e) {
            throw new BroadcastException("Exception occurred while registering: " ,e);
        }catch (Exception e) {
        	throw new BroadcastException("Exception occurred while registering: " ,e);
        }
	}
	public boolean isConsumer() {
		if(consumer==null){
			return false;
		}
		return true;
	}
	public boolean isProvider() {
		if(producer==null){
			return false;
		}
		return true;
	}

}
