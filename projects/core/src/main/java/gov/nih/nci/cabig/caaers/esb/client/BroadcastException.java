package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * Will be thrown when message cannot be broadcasted to the ESB
 * 
 * Taken from c3pr. Refactoring pending.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class BroadcastException extends CaaersSystemException{


/*    public BroadcastException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }*/

    public BroadcastException(String string) {
        super(string);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public BroadcastException(String string, Throwable throwable) {
        super(string, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public BroadcastException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
