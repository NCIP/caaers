/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators;

public class TooManyAllowedFailedLoginAttemptsException extends ValidationException {
	private static final long serialVersionUID = 6791910172663632951L;

	public TooManyAllowedFailedLoginAttemptsException(String message) {
		super(message);
	}
}
