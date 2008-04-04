package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import gov.nih.nci.cabig.caaers.service.security.user.Credential;
import org.springframework.beans.factory.annotation.Required;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Jared Flatow
 */
public class PasswordManagerServiceImpl implements PasswordManagerService {

    private PasswordPolicyService passwordPolicyService;

    private CSMUserRepository csmUserRepository;

    public String requestToken(String userName) throws CaaersSystemException {
        return csmUserRepository.userCreateToken(userName);
    }

    public void setPassword(String userName, String password, String token)
            throws CaaersSystemException {
        validateToken(userName, token);
        validateAndSetPassword(userName, password);
    }

    private boolean validateToken(String userName, String token) throws CaaersSystemException {
        User user = csmUserRepository.getUserByName(userName);
        if (user.getTokenTime().after(
                new Timestamp(new Date().getTime()
                        - passwordPolicyService.getPasswordPolicy()
                        .getTokenTimeout()))
                && token.equals(user.getToken())) return true;
        throw new CaaersSystemException("Invalid token.");
    }

    private boolean validateAndSetPassword(String userName, String password)
            throws CaaersSystemException {
        passwordPolicyService.validatePasswordAgainstCreationPolicy(new Credential(userName,
                password));
        csmUserRepository.userChangePassword(userName, password, passwordPolicyService
                .getPasswordPolicy().getPasswordCreationPolicy().getPasswordHistorySize());
        return true;
    }

    @Required
    public void setPasswordPolicyService(PasswordPolicyService passwordPolicyService) {
        this.passwordPolicyService = passwordPolicyService;
    }

    @Required
    public void setCsmUserRepository(final CSMUserRepository csmUserRepository) {
        this.csmUserRepository = csmUserRepository;
    }
}
