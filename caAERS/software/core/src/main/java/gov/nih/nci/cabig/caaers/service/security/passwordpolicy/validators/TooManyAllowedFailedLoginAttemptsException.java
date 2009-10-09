package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

public class TooManyAllowedFailedLoginAttemptsException extends ValidationException {
	private static final long serialVersionUID = 6791910172663632951L;

	public TooManyAllowedFailedLoginAttemptsException(String message) {
		super(message);
	}
}
