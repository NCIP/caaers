package gov.nih.nci.cabig.caaers.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * This class is a sample Message Producer Class.
 * When executed this class reads the provided xml document from the file system 
 * and wraps it with a JMS Text Message and delivers the JMS Message on the 
 * "ctms-caaers.inputQueue".
 * 
 * The JMS infrastructure is hosted by Servicemix. This is a separate installation.
 * The ctms-caaers-sa needs to be deployed on the ServiceMix Instance for the 2 queues
 * to be up and runing.
 * 
 */
public class SampleMessageProducer {
	
	
	/**
	 * This method establishes a connection to the ESB and also Instantiates  
	 * a producer which can deliver messages to the "ctms-caaers.inputQueue".
	 * 
	 */
    public static void main(String[] args){
    	
    	//Required fields 
    	ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        StringBuilder contents = new StringBuilder();
        BufferedReader input = null;
        File xmlFile = null;
        
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
        //Queue Instantiated with the Queue name from the ESB.
        ActiveMQQueue sendQueue = new ActiveMQQueue("ctms-caaers.inputQueue");
        
    	try{
    		//Connection to the ESB. 
            mqConnectionFactory.setBrokerURL("tcp://localhost:61616");
            connectionFactory = mqConnectionFactory; 
            connection = connectionFactory.createConnection();
            
            //Obtaining a Session.
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Instance of producer configured to deliver JMS Messages to the ""ctms-caaers.inputQueue" Queue
            producer = session.createProducer(sendQueue);
            
            //For testing purpose the Study/Participant Xml is read from the file system.
            //In reality the Local Ctms System generates this Study/Participant Xml Message.
            xmlFile = getResources("classpath*:gov/nih/nci/cabig/caaers/impl/studydata/CreateStudyTest.xml")[0].getFile();
            
            //Reading the File Contents into Memory.
            input =  new BufferedReader(new FileReader(xmlFile));
            String line = null; 
            while (( line = input.readLine()) != null){
              contents.append(line);
            }
            
            //Create anew JMS TextMessage 
            TextMessage message = session.createTextMessage();

            //Here a sample ID is used. In reality the Local Ctms System has to have a 
            //process to generate unique ID's.
            message.setJMSCorrelationID("103");
            
            //Since this sample dealing with creating a Study in caaers the MESSAGE_TYPE is CREATE_STUDY.
            //If we need to update an existing Study in caaers then we have to set the MESSAGE_TYPE to UPDATE_STUDY.
            message.setStringProperty("MESSAGE_TYPE", "CREATE_STUDY");
            
            /*
            message.setStringProperty("MESSAGE_TYPE", "UPDATE_STUDY");
            message.setStringProperty("MESSAGE_TYPE", "CREATE_PARTICIPANT");
            message.setStringProperty("MESSAGE_TYPE", "UPDATE_PARTICIPANT");
            message.setStringProperty("MESSAGE_TYPE", "CREATE_INVESTIGATOR");
            message.setStringProperty("MESSAGE_TYPE", "UPDATE_INVESTIGATOR");
            message.setStringProperty("MESSAGE_TYPE", "CREATE_RESEARCHSTAFF");
            message.setStringProperty("MESSAGE_TYPE", "UPDATE_RESEARCHSTAFF"); 
            */
                
            //Setting the File Contents in memory as the PayLoad of the JMS TextMessage. 
			message.setText(contents.toString());
			
			//Call to deliver the JMS Message to the Queue.
            producer.send(message);
            
            //Close
            producer.close();
                
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}finally {
            try {
            	if(input != null){input.close();}
            	if(connection != null){connection.close();}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
    }
    
    private static Resource[] getResources(String pattern) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(pattern);
        return resources;
    } 
}
