package gov.nih.nci.cabig.caaers.security.passwordpolicy.validators;

import gov.nih.nci.cabig.caaers.security.Credential;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.Duration;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;

public class MinimumAgeValidator implements PolicyValidator{

	public boolean validate(PasswordPolicy policy, Credential credential)
			throws ValidationException {
		Duration minAge = policy.getPasswordCreationPolicy().getMinimumAge();
		if(minAge!=null){
			if(minAge.getValue()>0){
				/**
				 * Implement the logic here to check the validity
				 */
			}
		}
		return true;
	}

}
