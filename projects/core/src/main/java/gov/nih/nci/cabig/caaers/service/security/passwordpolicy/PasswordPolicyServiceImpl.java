package gov.nih.nci.cabig.caaers.service.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.ValidationException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.LoginPolicyValidator;

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
