/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;

//TODO:MONISH Retire this class when appropriate
public class CSMUserRepositoryImpl implements CSMUserRepository {

    private UserProvisioningManager userProvisioningManager;
    private MailSender mailSender;
    private String authenticationMode;
    private UserDao userDao;
    private MessageSource messageSource;
    private Logger log = Logger.getLogger(CSMUserRepositoryImpl.class);
    
    
	@SuppressWarnings("unchecked")
	public List searchCsmUser(String firstName,String lastName,String userName){
		throw new UnsupportedOperationException();
    }
    
    public gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCSMUser(final User user, String changeURL) {
    	throw new UnsupportedOperationException();
    }
    
    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff, String changeURL) {
    	throw new UnsupportedOperationException();
    }

    public void createOrUpdateCSMUserAndGroupsForInvestigator(Investigator investigator, String changeURL) {
    	throw new UnsupportedOperationException();
    }
    
    public String getGroupIdByName(final String groupName) throws CSObjectNotFoundException {
    	throw new UnsupportedOperationException();
    }

    
    public boolean loginIDInUse(String loginId) {
    	throw new UnsupportedOperationException();
    }

    public gov.nih.nci.security.authorization.domainobjects.User getCSMUserByName(String userName) {
    	throw new UnsupportedOperationException();
    }
    
    public void saveCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
    	throw new UnsupportedOperationException();
    }
    
    public List<UserGroupType> getUserGroups(String userName) {
    	throw new UnsupportedOperationException();
    }

    public User getUserByName(String userName) {
    	throw new UnsupportedOperationException();
    }

    public String userCreateToken(User user) {
    	throw new UnsupportedOperationException();
    }

    public void userChangePassword(User user, String password, int maxHistorySize) {
    	throw new UnsupportedOperationException();
    }

    public boolean userHasPassword(String userName, String password) {
    	throw new UnsupportedOperationException();
    }

    public boolean userHadPassword(String userName, String password) {
    	throw new UnsupportedOperationException();
    }

    public void sendUserEmail(String emailAddress, String subject, String text) {
    	throw new UnsupportedOperationException();

    }

    // end

    public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }
    
	public UserProvisioningManager getUserProvisioningManager() {
		return userProvisioningManager;
	}

    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
   
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
