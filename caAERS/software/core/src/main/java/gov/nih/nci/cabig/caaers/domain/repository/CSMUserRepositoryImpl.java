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
/**
 * The Class CSMUserRepositoryImpl.
 */
public class CSMUserRepositoryImpl implements CSMUserRepository {

    /** The user provisioning manager. */
    private UserProvisioningManager userProvisioningManager;
    
    /** The mail sender. */
    private MailSender mailSender;
    
    /** The authentication mode. */
    private String authenticationMode;
    
    /** The user dao. */
    private UserDao userDao;
    
    /** The message source. */
    private MessageSource messageSource;
    
    /** The log. */
    private Logger log = Logger.getLogger(CSMUserRepositoryImpl.class);
    
    
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#searchCsmUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List searchCsmUser(String firstName,String lastName,String userName){
		throw new UnsupportedOperationException();
    }
    
    /**
     * Creates the or update csm user.
     *
     * @param user the user
     * @param changeURL the change url
     * @return the gov.nih.nci.security.authorization.domainobjects. user
     */
    public gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCSMUser(final User user, String changeURL) {
    	throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#createOrUpdateCSMUserAndGroupsForResearchStaff(gov.nih.nci.cabig.caaers.domain.ResearchStaff, java.lang.String)
     */
    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff, String changeURL) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#createOrUpdateCSMUserAndGroupsForInvestigator(gov.nih.nci.cabig.caaers.domain.Investigator, java.lang.String)
     */
    public void createOrUpdateCSMUserAndGroupsForInvestigator(Investigator investigator, String changeURL) {
    	throw new UnsupportedOperationException();
    }
    
    /**
     * Gets the group id by name.
     *
     * @param groupName the group name
     * @return the group id by name
     * @throws CSObjectNotFoundException the cS object not found exception
     */
    public String getGroupIdByName(final String groupName) throws CSObjectNotFoundException {
    	throw new UnsupportedOperationException();
    }

    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#loginIDInUse(java.lang.String)
     */
    public boolean loginIDInUse(String loginId) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#getCSMUserByName(java.lang.String)
     */
    public gov.nih.nci.security.authorization.domainobjects.User getCSMUserByName(String userName) {
    	throw new UnsupportedOperationException();
    }
    
    /**
     * Save csm user.
     *
     * @param csmUser the csm user
     */
    public void saveCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
    	throw new UnsupportedOperationException();
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#getUserGroups(java.lang.String)
     */
    public List<UserGroupType> getUserGroups(String userName) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#getUserByName(java.lang.String)
     */
    public User getUserByName(String userName) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#userCreateToken(gov.nih.nci.cabig.caaers.domain.User)
     */
    public String userCreateToken(User user) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#userChangePassword(gov.nih.nci.cabig.caaers.domain.User, java.lang.String, int)
     */
    public void userChangePassword(User user, String password, int maxHistorySize) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#userHasPassword(java.lang.String, java.lang.String)
     */
    public boolean userHasPassword(String userName, String password) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#userHadPassword(java.lang.String, java.lang.String)
     */
    public boolean userHadPassword(String userName, String password) {
    	throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.CSMUserRepository#sendUserEmail(java.lang.String, java.lang.String, java.lang.String)
     */
    public void sendUserEmail(String emailAddress, String subject, String text) {
    	throw new UnsupportedOperationException();

    }

    // end

    /**
     * Sets the user provisioning manager.
     *
     * @param userProvisioningManager the new user provisioning manager
     */
    public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }
    
	/**
	 * Gets the user provisioning manager.
	 *
	 * @return the user provisioning manager
	 */
	public UserProvisioningManager getUserProvisioningManager() {
		return userProvisioningManager;
	}

    /**
     * Sets the mail sender.
     *
     * @param mailSender the new mail sender
     */
    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sets the user dao.
     *
     * @param userDao the new user dao
     */
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
   
    /**
     * Gets the authentication mode.
     *
     * @return the authentication mode
     */
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    /**
     * Sets the authentication mode.
     *
     * @param authenticationMode the new authentication mode
     */
    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }

    /**
     * Gets the message source.
     *
     * @return the message source
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

    /**
     * Sets the message source.
     *
     * @param messageSource the new message source
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}