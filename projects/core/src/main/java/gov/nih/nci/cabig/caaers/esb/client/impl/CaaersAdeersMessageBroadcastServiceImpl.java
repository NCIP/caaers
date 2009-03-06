package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.Vector;

import javax.jms.Destination;

/**
 * 
 * @author Srini Akkala
 */
public class CaaersAdeersMessageBroadcastServiceImpl extends JmsServiceImpl {
	
	// Monish Dombla CAAERS-145
	public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
	
    public void broadcast(String message) throws BroadcastException {
        // TODO Auto-generated method stub
        if (!isProvider()) {
            System.out.println("no send queue provided ");
        } else {
            System.out.println("calling sendJms method...");
            sendJms(message);
        }
    }

    public Vector getBroadcastStatus() {
        // TODO Auto-generated method stub
        if (!isConsumer()) {
            System.out.println("no recieve queue provided ");
        }
        return messages;
    }

    public void setRecvQueue(Destination recvQueue) {
        this.recvQueue = recvQueue;
    }

    public void setSendQueue(Destination sendQueue) {
        this.sendQueue = sendQueue;
    }
}
