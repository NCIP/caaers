package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

public class PasswordTooOldException extends ValidationException {
	private static final long serialVersionUID = 962534692592257767L;

	public PasswordTooOldException(String message) {
		super(message);
	}
	
}
