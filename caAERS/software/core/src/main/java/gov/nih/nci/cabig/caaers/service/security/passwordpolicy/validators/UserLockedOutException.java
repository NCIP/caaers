package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

public class UserLockedOutException extends ValidationException {
	private static final long serialVersionUID = 962530092592257767L;

	public UserLockedOutException(String message) {
		super(message);
	}
	
}