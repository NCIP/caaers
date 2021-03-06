/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.esb.client.impl;

import edu.duke.cabig.c3pr.esb.CaXchangeMessageResponseHandlerSet;
import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import gov.nih.nci.cabig.caaers.accesscontrol.SecurityContextCredentialProvider;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

/**
 * This implementation will broadcast messages to caXchange via C3PR implemented message broadcasters.
 * @author Sujith V
 * @author Srini Akkala
 * @author Monish Dombla
 * @author Biju Joseph
 */

public class CaaersCaXchangeMessageBroadcastServiceImpl implements MessageBroadcastService {

	/*
	 * Refactoring Question:
	 *  BJ : Why we are initalizing in every call CaXchangeMessageBroadcasterImpl, is it statefull?
	 */
	private static final Log log = LogFactory.getLog(CaaersCaXchangeMessageBroadcastServiceImpl.class);
    private Map<String, String> messageTypesMapping;
    private SecurityContextCredentialProvider delegatedCredentialProvider;

    /**
     * Will route the request to the C3PR CaXchangeMessageBroadCaster
     */
    public void broadcast(String message) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {

        try {
            CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
            String iHubURL =  Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAEXCHANGE_URL);
            log.info("ca exchage URL + " + iHubURL);
            iHubURL = StringUtils.trim(iHubURL);
            broadCaster.setCaXchangeURL(iHubURL);
            broadCaster.setMessageTypesMapping(messageTypesMapping);
            broadCaster.setDelegatedCredentialProvider(delegatedCredentialProvider);
            broadCaster.setMessageResponseHandlers(new CaXchangeMessageResponseHandlerSet());
            broadCaster.broadcast(message);
            log.info("Broadcasted the message to PSC( url :" + broadCaster.getCaXchangeURL() + ")");
        } catch (Throwable e) {
            log.error("Error while broadcasting the message to PSC", e);
            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
        }
    }
    
    /**
     * Will send a COPPA message
     */
    public String broadcastCOPPA(List<String> messages,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
            String iHubURL =  Configuration.LAST_LOADED_CONFIGURATION.get(Configuration.CAEXCHANGE_URL);
            log.info("ca exchage URL + " + iHubURL);
            iHubURL = StringUtils.trim(iHubURL);
            broadCaster.setCaXchangeURL(iHubURL);
        	broadCaster.setDelegatedCredentialProvider(delegatedCredentialProvider);
        	broadCaster.setMessageResponseHandlers(new CaXchangeMessageResponseHandlerSet());
        	result = broadCaster.broadcastCoppaMessage(messages, metaData);
		} catch (edu.duke.cabig.c3pr.esb.BroadcastException e) {
			log.error("Error while broadcasting the message to COPPA", e);
            throw new gov.nih.nci.cabig.caaers.esb.client.BroadcastException(e);
		}
    	return result;
    }

    public List<String> getBroadcastStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<String, String> getMessageTypesMapping() {
        return messageTypesMapping;
    }

    @Required
    public void setMessageTypesMapping(Map<String, String> messageTypesMapping) {
        this.messageTypesMapping = messageTypesMapping;
    }

    @Required
    public void setDelegatedCredentialProvider(SecurityContextCredentialProvider delegatedCredentialProvider) {
        this.delegatedCredentialProvider = delegatedCredentialProvider;
    }
}
