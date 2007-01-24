package gov.nih.nci.cabig.caaers.esb.client.impl;

import java.util.Vector;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;

/**
 * MessageBroadCaster taken from c3pr. Refactoring pending.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class MessageBroadcastServiceImpl extends JmsServiceImpl implements MessageBroadcastService {

	public MessageBroadcastServiceImpl() {
		super();
	}
	
	public void broadcast(String message) throws BroadcastException {
		// TODO Auto-generated method stub
		if(!isProvider()){
			System.out.println("no send queue provided ");
		}else{
			System.out.println("calling sendJms method...");
	        sendJms(message);
		}
    }
	
	public Vector getBroadcastStatus() {
		// TODO Auto-generated method stub
		if(!isConsumer()){
			System.out.println("no recieve queue provided ");
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
