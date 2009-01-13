package gov.nih.nci.cabig.caaers.service.security;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
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
    
    private InvestigatorDao investigatorDao;
    
    private ResearchStaffDao researchStaffDao;
    

    public User requestToken(String userName) throws CaaersSystemException {
    	User user = researchStaffDao.getByLoginId(userName);
    	if(user == null){
    		user = investigatorDao.getByLoginId(userName);
    	}
    	if(user == null){
    		throw new CaaersNoSuchUserException("User with login Id :" + userName + " unknowon");
    	}
        //get the token
    	csmUserRepository.userCreateToken(user);
    	if(user instanceof ResearchStaff){
    		researchStaffDao.save((ResearchStaff)user);
    	}else if (user instanceof Investigator){
    		investigatorDao.save((Investigator)user);
    	}
    	return user;
    }

    public void setPassword(String userName, String password, String token)
            throws CaaersSystemException {
    	User user = researchStaffDao.getByLoginId(userName);
    	if(user == null){
    		user = investigatorDao.getByLoginId(userName);
    	}
    	if(user == null){
    		new CaaersNoSuchUserException("User with login Id :" + userName + " unknowon");
    	}
        validateToken(user, token);
        validateAndSetPassword(user, password);
        
        if(user instanceof ResearchStaff){
    		researchStaffDao.save((ResearchStaff)user);
    	}else if (user instanceof Investigator){
    		investigatorDao.save((Investigator)user);
    	}
        
    }

    private boolean validateToken(User user, String token) throws CaaersSystemException {
        if (user.getTokenTime().after(
                new Timestamp(new Date().getTime()
                        - passwordPolicyService.getPasswordPolicy()
                        .getTokenTimeout()))
                && token.equals(user.getToken())) return true;
        throw new CaaersSystemException("Invalid token.");
    }

    private boolean validateAndSetPassword(User user, String password)
            throws CaaersSystemException {
        passwordPolicyService.validatePasswordAgainstCreationPolicy(new Credential(user.getLoginId(),
                password));
        csmUserRepository.userChangePassword(user, password, passwordPolicyService
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
    
    
    @Required
    public void setInvestigatorDao(InvestigatorDao investigatorDao) {
		this.investigatorDao = investigatorDao;
	}
    
    @Required
    public void setResearchStaffDao(ResearchStaffDao researchStaffDao) {
		this.researchStaffDao = researchStaffDao;
	}
}
