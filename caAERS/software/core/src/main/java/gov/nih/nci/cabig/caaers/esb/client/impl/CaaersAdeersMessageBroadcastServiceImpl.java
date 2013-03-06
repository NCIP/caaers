/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client.impl;

import gov.nih.nci.cabig.caaers.esb.client.BroadcastException;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.Vector;

import javax.jms.Destination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Srini Akkala
 * @author Biju Joseph (added Transactional annotation)
 */

public class CaaersAdeersMessageBroadcastServiceImpl extends JmsServiceImpl {
	protected final Log log = LogFactory.getLog(getClass());
	// Monish Dombla CAAERS-145
	public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
	
	@Transactional
    public void broadcast(String message) throws BroadcastException {
        // TODO Auto-generated method stub
        if (!isProvider()) {
            log.warn("no send queue provided ");
        } else {
            log.info("calling sendJms method...");
            sendJms(message);
        }
    }

    public Vector getBroadcastStatus() {
        // TODO Auto-generated method stub
        if (!isConsumer()) {
            log.info("no recieve queue provided ");
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
