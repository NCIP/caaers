package gov.nih.nci.cabig.caaers.esb.client.impl;

import edu.duke.cabig.c3pr.esb.CaXchangeMessageResponseHandlerSet;
import edu.duke.cabig.c3pr.esb.Metadata;
import edu.duke.cabig.c3pr.esb.impl.CaXchangeMessageBroadcasterImpl;
import gov.nih.nci.cabig.caaers.accesscontrol.SecurityContextCredentialProvider;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class CaaersCaXchangeMessageBroadcastServiceImpl implements MessageBroadcastService {
    private static final Log log = LogFactory
                    .getLog(CaaersCaXchangeMessageBroadcastServiceImpl.class);

    private Configuration configuration;

    private Map<String, String> messageTypesMapping;

    private SecurityContextCredentialProvider delegatedCredentialProvider;

    /**
     * Will route the request to the C3PR CaXchangeMessageBroadCaster
     */
    public void broadcast(String message)
                    throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {

        try {
            CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
            System.out.println("ca exchage URL + " + configuration.get(Configuration.CAEXCHANGE_URL));
            broadCaster.setCaXchangeURL(configuration.get(Configuration.CAEXCHANGE_URL));
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
    
    public String broadcastCOPPA(String message,Metadata metaData) throws gov.nih.nci.cabig.caaers.esb.client.BroadcastException {    	
        String result = null;
        try {
        	CaXchangeMessageBroadcasterImpl broadCaster = new CaXchangeMessageBroadcasterImpl();
        	System.out.println("ca exchage URL + " + configuration.get(Configuration.CAEXCHANGE_URL));
            broadCaster.setCaXchangeURL(configuration.get(Configuration.CAEXCHANGE_URL));
            /*
             	broadCaster.setMessageTypesMapping(messageTypesMapping);
                broadCaster.setDelegatedCredentialProvider(delegatedCredentialProvider);
                broadCaster.setMessageResponseHandlers(new CaXchangeMessageResponseHandlerSet());
             */
        	result = broadCaster.broadcastCoppaMessage(message, metaData);
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

    public Configuration getConfiguration() {
        return configuration;
    }

    @Required
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Map<String, String> getMessageTypesMapping() {
        return messageTypesMapping;
    }

    @Required
    public void setMessageTypesMapping(Map<String, String> messageTypesMapping) {
        this.messageTypesMapping = messageTypesMapping;
    }

    @Required
    public void setDelegatedCredentialProvider(
                    SecurityContextCredentialProvider delegatedCredentialProvider) {
        this.delegatedCredentialProvider = delegatedCredentialProvider;
    }

}
