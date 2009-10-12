package gov.nih.nci.cabig.caaers.esb.client;

import edu.duke.cabig.c3pr.esb.Metadata;

import java.util.List;

/**
 * This is the base interface, to send messages to external resources from caAERS.
 *  
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public interface MessageBroadcastService {
	
	/**
	 * Will brodcast a interoperability message.
	 * @param message
	 * @throws BroadcastException
	 */
    void broadcast(String message) throws BroadcastException;
    
    /**
     * Will broadcast a message to COPPA services.
     * @param messages
     * @param metaData
     * @return
     * @throws BroadcastException
     */
    String broadcastCOPPA(List<String> messages,Metadata metaData) throws BroadcastException;
    /**
     * Will tell the present status of the last broadcasted message. 
     * @return
     */
    List<String> getBroadcastStatus();

}
