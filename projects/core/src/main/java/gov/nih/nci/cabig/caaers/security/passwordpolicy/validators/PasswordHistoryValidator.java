package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.security.Credential;

import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class PasswordHistoryValidator implements PolicyValidator{

	public boolean validate(PasswordPolicy policy,Credential credential) throws ValidationException {
		// TODO Auto-generated method stub
		/**
		 * Here retrieve the previous passwords and compare for the uniqueness
		 */
		String password = credential.getPassword();
		
		
		int pastHistoryToBeChecked = policy.getPasswordCreationPolicy().getPreviousPasswordCount();
		
		return true;
	}

}
