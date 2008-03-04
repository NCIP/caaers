package gov.nih.nci.cabig.caaers.service.security.passwordpolicy;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.service.UserService;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.ValidationException;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.PasswordCreationPolicyValidator;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.validators.LoginPolicyValidator;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.dao.security.passwordpolicy.PasswordPolicyDao;

import org.springframework.beans.factory.annotation.Required;

public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    PasswordCreationPolicyValidator passwordCreationPolicyValidator;

    LoginPolicyValidator loginPolicyValidator;

    PasswordPolicyDao passwordPolicyDao;

    UserService userService;

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
                    throws CaaersSystemException {
        return passwordCreationPolicyValidator.validate(getPasswordPolicy(), credential);
    }

    /*
     * public boolean validatePasswordAgainstLoginPolicy(Credential credential) throws
     * CaaersSystemException { return loginPolicyValidator.validate(getPasswordPolicy(),
     * credential); }
     */

    @Required
    public void setPasswordPolicyDao(PasswordPolicyDao passwordPolicyDao) {
        this.passwordPolicyDao = passwordPolicyDao;
    }

    @Required
    public void setUserService(UserService userService) {
        this.userService = userService;
        passwordCreationPolicyValidator.setUserService(userService);
        loginPolicyValidator.setUserService(userService);
    }
}
