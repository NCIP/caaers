package gov.nih.nci.cabig.caaers;

public class CaaersUserProvisioningException extends CaaersSystemException {
	  public CaaersUserProvisioningException(String message) {
		  super(message);
	  }
	  public CaaersUserProvisioningException(String message, Throwable t) {
		  super(message, t);
	  }
}