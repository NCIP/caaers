package gov.nih.nci.cabig.caaers.service.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.LoginPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordCreationPolicyException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import org.springframework.beans.factory.annotation.Required;

public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    PasswordCreationPolicyValidator passwordCreationPolicyValidator;

    LoginPolicyValidator loginPolicyValidator;

    PasswordPolicyDao passwordPolicyDao;

    CSMUserRepository csmUserRepository;

    public PasswordPolicyServiceImpl() {
        passwordCreationPolicyValidator = new PasswordCreationPolicyValidator();
        loginPolicyValidator = new LoginPolicyValidator();
    }

    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicyDao.getById(1);
    }

    public void setPasswordPolicy(PasswordPolicy passwordPolicy) {
        passwordPolicyDao.save(passwordPolicy);
    }

    public String publishPasswordPolicy() {
        return "TODO";
    }

    public String publishPasswordPolicy(String xsltFileName) {
        return "TODO";
    }

    public boolean validatePasswordAgainstCreationPolicy(Credential credential)
            throws PasswordCreationPolicyException {
    	ValidationErrors validationErrors = new ValidationErrors();
		boolean result = passwordCreationPolicyValidator.validate(getPasswordPolicy(), credential, validationErrors);
		if(validationErrors.hasErrors()) throw new PasswordCreationPolicyException("Error while saving password", validationErrors);
		return result;
    }
    
     public boolean validatePasswordAgainstLoginPolicy(Credential credential) throws
      CaaersSystemException { 
    	 return loginPolicyValidator.validate(getPasswordPolicy(),credential, null);
    	 }
     

    @Required
    public void setPasswordPolicyDao(PasswordPolicyDao passwordPolicyDao) {
        this.passwordPolicyDao = passwordPolicyDao;
    }

    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
        passwordCreationPolicyValidator.setCsmUserRepository(csmUserRepository);
        //loginPolicyValidator.setCsmUserRepository(csmUserRepository);

    }


}
