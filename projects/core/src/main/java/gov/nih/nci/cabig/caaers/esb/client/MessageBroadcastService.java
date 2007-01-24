package gov.nih.nci.cabig.caaers.esb.client;


import java.util.Vector;

/**
 * Refactoring pending.
 * @author Sujith Vellat Thayyilthodi
 * */
public interface MessageBroadcastService {
	
	public void broadcast(String message) throws BroadcastException;
	
	public Vector getBroadcastStatus();

}
