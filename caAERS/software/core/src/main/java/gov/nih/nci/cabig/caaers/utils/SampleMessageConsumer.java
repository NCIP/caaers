package gov.nih.nci.cabig.caaers.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;


/**
 * This Class is a Listener which is configured to receive JMS Messages 
 * from "ctms-caaers.outputQueue".
 * The JMS Messages on this Queue are typically Response Messages from caaers. 
 * @author Monish Dombla
 *
 */
public class SampleMessageConsumer implements MessageListener{
	
	//Required Fields for Connection and Consumer.
	//tcp://localhost:61616 is the ESB url. Meaning the instance of ServiceMix.
	private ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	private Connection connection = null;
	private Session session = null;
	private MessageConsumer consumer = null;
	private ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
	//Queue setup.
	private ActiveMQQueue ctmsCaaersRecvQueue = new ActiveMQQueue("ctms-caaers.outputQueue");
	
	//Used for running this as Stand alone consumer outside of container.
	private static Object m_o = new Object();
	
	//Constructor
	public SampleMessageConsumer() throws Exception{
		//Establish Connection to the ESB
		mqConnectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory = mqConnectionFactory;
        connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Configure the consumer to receive messages from "ctms-caaers.outputQueue" Queue 
		consumer = session.createConsumer(ctmsCaaersRecvQueue);
		//Register the listener.
        consumer.setMessageListener(this);
        System.out.println("starting connection....");
        //Start the Listener
        connection.start();
	}
	
	//This is a call back method.
	//Whenever a message is delivered to ctms-caaers.outputQueue" Queue.
	//This method is invoked with the actual JMS Message as an argument.
	public void onMessage(Message message) {
		try {
			boolean shouldTerminate = message.getBooleanProperty("should-terminate");
			if (shouldTerminate){
				synchronized(m_o){
					m_o.notify();
				}
			}else{
				if (message instanceof TextMessage){
					//caaers sent the type of message it processed.
					String messageType = message.getStringProperty("MESSAGE_TYPE");
					//caaers sent the JMSCorealtionID which it received with the Request Message
					String jmsCorelationID = message.getJMSCorrelationID();
					
					System.out.println("JMSCorelationID="+ jmsCorelationID);
					System.out.println("MESSAGE_TYPE="+ messageType);
					
					//The Pay Load of the JMS Message (Response details from caaers)
					System.out.println(((TextMessage)message).getText());
					
				}
			}
		}catch (JMSException je) {
			System.out.println("onMessage caught "+je);
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		SampleMessageConsumer mr = new SampleMessageConsumer();
		try{
			synchronized(m_o){
				m_o.wait();
			}
		// Clean up the connections in a new MessageConsumer method close()
		mr.close();
		}catch (JMSException jmse){
			System.out.println("main caught "+jmse.getMessage());
		}catch (InterruptedException e) {
			System.out.println("main caught "+ e.getMessage());
		}
	}
	
	public void close () throws Exception{
		System.out.println("closing connection....");
		connection.close();
		// Any other cleanup
	}

}
