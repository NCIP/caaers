package gov.nih.nci.cabig.caaers.email;


/**
 * 
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class EmailException extends RuntimeException {
	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(Throwable cause) {
		super(cause);
	}
}
