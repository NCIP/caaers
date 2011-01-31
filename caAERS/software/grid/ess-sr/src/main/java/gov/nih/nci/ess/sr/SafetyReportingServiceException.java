/**
 * 
 */
package gov.nih.nci.ess.sr;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Denis G. Krylov
 * 
 */
public class SafetyReportingServiceException extends CaaersSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024352905735588089L;

	public SafetyReportingServiceException(String code, String message,
			Throwable cause) {
		super(code, message, cause);
		// TODO Auto-generated constructor stub
	}

	public SafetyReportingServiceException(String code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public SafetyReportingServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SafetyReportingServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SafetyReportingServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
