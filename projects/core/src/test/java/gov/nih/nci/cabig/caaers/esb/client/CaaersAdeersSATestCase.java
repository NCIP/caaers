package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

public class CaaersAdeersSATestCase extends CaaersTestCase {
	
//	private Configuration configuration = null;
//	private ConnectionFactory connectionFactory = null;
//	private Connection connection = null;
//	private Session session = null;
//	private MessageConsumer consumer = null;
//	private MessageProducer producer = null;
//	
//	@Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        configuration = (Configuration)getDeployedApplicationContext().getBean("configuration");
//        setUpConnectionAndSession();
//        setUpConsumer();
//        setUpMessageListner();
//        setUpProducer();
//    }
//	
//    public void testHappyPath() throws Exception {
//    	StringBuilder sb = new StringBuilder();
//    	try {
//            BufferedReader in = new BufferedReader(new FileReader("testdata/HappyPath.xml"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                sb.append(str);
//            }
//            in.close();
//            
//            TextMessage message = session.createTextMessage();
//            message.setText(sb.toString());
//            producer.send(message);
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }            
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    }
//    
//    public void testInvalidWSLogin() throws Exception {
//    	StringBuilder sb = new StringBuilder();
//    	try {
//            BufferedReader in = new BufferedReader(new FileReader("testdata/InvalidWSLogin.xml"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                sb.append(str);
//            }
//            in.close();
//            
//            TextMessage message = session.createTextMessage();
//            message.setText(sb.toString());
//            producer.send(message);
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }            
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    }
//    
//    public void testInvalidWSPassword() throws Exception {
//    	StringBuilder sb = new StringBuilder();
//    	try {
//            BufferedReader in = new BufferedReader(new FileReader("testdata/InvalidWSPassword.xml"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                sb.append(str);
//            }
//            in.close();
//            
//            TextMessage message = session.createTextMessage();
//            message.setText(sb.toString());
//            producer.send(message);
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }            
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    }
//    
//    public void testInvalidWSUrl() throws Exception {
//    	StringBuilder sb = new StringBuilder();
//    	try {
//            BufferedReader in = new BufferedReader(new FileReader("testdata/InvalidWSUrl.xml"));
//            String str;
//            while ((str = in.readLine()) != null) {
//                sb.append(str);
//            }
//            in.close();
//            
//            TextMessage message = session.createTextMessage();
//            message.setText(sb.toString());
//            producer.send(message);
//            
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//
//            }            
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//    }
//    
//    private class ConsumerMessageListenerTest implements MessageListener {
//    	public void onMessage(Message message) {
//    		TextMessage txtMsg = (TextMessage)(message);
//    		try {
//    			System.out.println("----------Response Received---------------");
//				System.out.println(txtMsg.getText());
//			} catch (Exception e) {				
//				e.printStackTrace();
//			}
//        }
//    }
//    
//    
//    private void setUpConnectionAndSession(){
//    	try{
//    		ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
//            mqConnectionFactory.setBrokerURL("tcp://localhost:61616");
//            connection = mqConnectionFactory.createConnection();
//            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}    	
//    }
//    
//    private void setUpConsumer(){    	
//    	try{
//    		ActiveMQQueue receiveQueue = (ActiveMQQueue)getDeployedApplicationContext().getBean("recvQueueDestination");
//        	consumer = session.createConsumer(receiveQueue);        	
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}    	
//    }
//    
//    private void setUpMessageListner(){
//    	try{
//    		ConsumerMessageListenerTest listener = new ConsumerMessageListenerTest();
//        	consumer.setMessageListener(listener);
//        	connection.start();
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}	
//    }
//    
//    private void setUpProducer(){
//    	try{
//    		ActiveMQQueue sendQueue = (ActiveMQQueue)getDeployedApplicationContext().getBean("sendQueueDestination");
//        	producer = session.createProducer(sendQueue);
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	}    	
//    }	
}
