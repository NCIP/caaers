package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class SimpleJMSClient {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        String usr = args[0];
        String pwd = args[1];
        String brokerUrl = "tcp://localhost:61616";
        String inQueue = "ctms.input.queue";
        String outQueue = "ctms.output.queue";

        // String fileName = "test/resources/query_1.xml";
        String fileName = "proxy.txt";
        StringBuilder sb = new StringBuilder();
        sb.append("<msg>");
        // sb.append("<auth>");
        // sb.append("<usr>").append(usr).append("</usr>");
        // sb.append("<pwd>").append(pwd).append("</pwd>");
        // sb.append("</auth>");
        sb.append("<credentials>");
        sb.append("<proxy>");
        File f = new File(fileName);
        BufferedReader fr = new BufferedReader(new FileReader(f));
        String line = null;
        while ((line = fr.readLine()) != null) {
            sb.append(line).append("\n");
        }
        sb.append("</proxy>");
        sb.append("</credentials>");
        sb.append("<content><greeting>Howdy Pardner!</greeting>");
        // File f = new File(fileName);
        //
        // BufferedReader fr = new BufferedReader(new FileReader(f));
        // String line = null;
        // while ((line = fr.readLine()) != null) {
        // sb.append(line);
        // }
        sb.append("</content>");
        sb.append("</msg>");

        int timeout = 5000;

        Thread producer = new Thread(new MyProducer(brokerUrl, inQueue, sb.toString()));
        Thread consumer = new Thread(new MyConsumer(brokerUrl, outQueue, timeout));

        consumer.start();
        producer.start();

        Thread.sleep(10000);

    }

    private static class MyProducer implements Runnable {
        private String brokerUrl;

        private String queueName;

        private String msg;

        public MyProducer(String brokerUrl, String queueName, String msg) {
            this.brokerUrl = brokerUrl;
            this.queueName = queueName;
            this.msg = msg;
        }

        public void run() {
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                                this.brokerUrl);
                Connection connection = connectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = new ActiveMQQueue(this.queueName);
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                TextMessage message = session.createTextMessage(this.msg);
                System.out.println("Sent message: " + message.hashCode() + " : "
                                + Thread.currentThread().getName());
                producer.send(message);
                session.close();
                connection.close();
            } catch (Exception ex) {
                System.out.println("Producer caught " + ex.getClass().getName() + ": "
                                + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

    private static class MyConsumer implements Runnable, ExceptionListener {

        private String brokerUrl;

        private String queueName;

        int timeout;

        public MyConsumer(String brokerUrl, String queueName, int timeout) {
            this.brokerUrl = brokerUrl;
            this.queueName = queueName;
            this.timeout = timeout;
        }

        public void run() {
            try {

                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                                this.brokerUrl);
                Connection connection = connectionFactory.createConnection();
                connection.start();
                connection.setExceptionListener(this);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = new ActiveMQQueue(this.queueName);
                MessageConsumer consumer = session.createConsumer(destination);
                Message message = consumer.receive(this.timeout);
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }

                consumer.close();
                session.close();
                connection.close();
            } catch (Exception ex) {
                System.out.println("Consumer caught " + ex.getClass().getName() + ": "
                                + ex.getMessage());
                ex.printStackTrace();
            }
        }

        public void onException(JMSException ex) {
            System.out.println("Caught JMSException: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
