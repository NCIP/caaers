/**
 * 
 */
package gov.nih.nci.ess.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Denis G. Krylov
 *
 */
public class AdverseEventServiceException extends CaaersSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024352905735588089L;

	public AdverseEventServiceException(String code, String message,
			Throwable cause) {
		super(code, message, cause);
		// TODO Auto-generated constructor stub
	}

	public AdverseEventServiceException(String code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	public AdverseEventServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AdverseEventServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AdverseEventServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
