package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.user.Credential;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.LoginPolicyValidator;
import gov.nih.nci.cabig.caaers.security.passwordpolicy.validators.ValidationException;

public class PasswordPolicyServiceImpl implements PasswordPolicyService {
    
    public static final PasswordPolicyService Singleton = new PasswordPolicyServiceImpl();
    private PasswordPolicyServiceImpl() {}

    public PasswordPolicy getPasswordPolicy() throws CaaersSystemException {
	return null;
    }
    
    public void setPasswordPolicy(PasswordPolicy passwordPolicy) throws CaaersSystemException {
	// TODO
    }
    
    public String publishPasswordPolicy() {
	return "TODO";
    }

    public String publishPasswordPolicy(String xsltFileName) {
	return "TODO";
    }
    
    public boolean validatePasswordAgainstCreationPolicy(Credential credential) throws CaaersSystemException {
	return PasswordCreationPolicyValidator.Singleton.validate(getPasswordPolicy(), credential);
    }

    public boolean validatePasswordAgainstLoginPolicy(Credential credential) throws CaaersSystemException {
	return LoginPolicyValidator.Singleton.validate(getPasswordPolicy(), credential);
    }
}
