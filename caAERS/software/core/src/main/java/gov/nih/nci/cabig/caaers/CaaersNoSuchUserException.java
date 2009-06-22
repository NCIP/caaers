package gov.nih.nci.cabig.caaers;

public class CaaersNoSuchUserException extends CaaersSystemException {
  public CaaersNoSuchUserException(String message) {
	  super(message);
  }
  public CaaersNoSuchUserException(String message, Throwable t) {
	  super(message, t);
  }

}
