package gov.nih.nci.cabig.caaers.esb.client;

import java.util.List;

/**
 * Refactoring pending.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public interface MessageBroadcastService {

    void broadcast(String message) throws BroadcastException;

    List<String> getBroadcastStatus();

}
