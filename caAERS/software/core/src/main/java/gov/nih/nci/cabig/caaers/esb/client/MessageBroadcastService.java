package gov.nih.nci.cabig.caaers.esb.client;

import java.util.List;

import edu.duke.cabig.c3pr.esb.Metadata;

/**
 * Refactoring pending.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public interface MessageBroadcastService {

    void broadcast(String message) throws BroadcastException;
    
    //String broadcastCOPPA(String message,Metadata metaData) throws BroadcastException;

    List<String> getBroadcastStatus();

}
