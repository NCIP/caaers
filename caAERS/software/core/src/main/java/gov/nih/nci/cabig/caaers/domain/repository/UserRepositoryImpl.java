package gov.nih.nci.cabig.caaers.domain.repository;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.RoleMembership;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.dao.query.UserQuery;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.CSMCacheManager;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSession;
import gov.nih.nci.cabig.ctms.suite.authorization.ProvisioningSessionFactory;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRoleMembership;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;

import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

 
/**
 * The Class UserRepositoryImpl.
 */
public class UserRepositoryImpl implements UserRepository {

	/** The logger. */
	private Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	
	/** The user dao. */
	private UserDao userDao;
	
	/** The user provisioning manager. */
	private UserProvisioningManager userProvisioningManager;
    
    /** The provisioning session factory. */
    private ProvisioningSessionFactory provisioningSessionFactory;
    
    /** The mail sender. */
    private MailSender mailSender;
    
    /** The authentication mode. */
    private String authenticationMode;
    
    /** The message source. */
    private MessageSource messageSource;
    
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#searchCsmUser(java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
	public List searchCsmUser(String firstName, String lastName, String userName) {
    	if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName) && StringUtils.isEmpty(userName)) firstName = "%";
    	
    	gov.nih.nci.security.authorization.domainobjects.User example = new gov.nih.nci.security.authorization.domainobjects.User();
    	if(StringUtils.isNotEmpty(firstName)) example.setFirstName("%"+firstName+"%");
    	if(StringUtils.isNotEmpty(lastName)) example.setLastName("%"+lastName+"%");
    	if(StringUtils.isNotEmpty(userName)) example.setLoginName("%"+userName+"%");
    	UserSearchCriteria userSearchCriteria = new UserSearchCriteria(example);
    	return userProvisioningManager.getObjects(userSearchCriteria);	
	}
	
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#save(gov.nih.nci.cabig.caaers.domain.User)
     */
    @Transactional(readOnly = false)
    public void save(User user){
    	userDao.save(user);
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#createOrUpdateUser(gov.nih.nci.cabig.caaers.domain.User, java.lang.String)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, noRollbackFor = MailException.class)
	public void createOrUpdateUser(User user,String changeURL){
		if(user.getCsmUser().getUserId() == null){
			createCSMUser(user);
			save(user);
			sendCreateAccountEmail(user,changeURL);
		}else{
			updateCSMUser(user,true);
            if(user.getId() == null){
                save(user);
            }
			sendUpdateAccountEmail(user);
		}
	}


    /*
     * (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade#provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User, java.util.List)
     */
    public void provisionRoleMemberships(gov.nih.nci.security.authorization.domainobjects.User csmUser, List<SuiteRoleMembership> roleMemberships) {

        //Fetch all the existing groups of the Given User.
        List<UserGroupType> userGroups = getUserGroups(csmUser.getUserId().toString());

        //Erase all the existing SuiteRoleMemberships of the User
        ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
        for(UserGroupType group : userGroups){
            session.deleteRole(SuiteRole.getByCsmName(group.getCsmName()));
        }
        //Provision the newly provided SuiteRoleMemberships for the User in CSM.
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(roleMemberships)){
            for(SuiteRoleMembership roleMembership : roleMemberships){
                session.replaceRole(roleMembership);
            }
        }


        //user information got updated, so remove from cache.
        CSMCacheManager.removeUserFromCache(csmUser.getLoginName());
    }


    /**
     * Will fetch the user from the DB, along with the CSM related information properly populated.
     *
     * @param loginName the login name
     * @return the user by login name
     */
    @Transactional(readOnly = true)
	public User getUserByLoginName(String loginName) {

        //fetch the fresh user. 
		User _user = userDao.getByLoginName(loginName);

       //fetch the CSM related details from Cache if the user is present there.
        User userFromCache = CSMCacheManager.getUserFromCache(loginName);
        if(_user != null && userFromCache != null){
            //obtain csm information from cache. 
           _user.setCsmUser(userFromCache.getCsmUser());
           _user.getRoleMembershipMap().putAll(userFromCache.getRoleMembershipMap());
            
        }else{
              //fetch CSM information
            gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(loginName);
            if(_user == null && csmUser == null) return null; // may be login-id supplied is incorrect.
            if(csmUser == null){
                logger.error("CSM User with loginName [" + loginName +"] don't exist, a data integrity issue as user exist in caAERS");
                throw new CaaersSystemException("caAERS user [" +  loginName +"] exists and relevant CSM user does not exist : Data Integrity issue");
            }
            if(_user == null){
                _user = new User(csmUser);
                _user.setPasswordLastSet(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            }else{
                _user.setCsmUser(csmUser);
            }

            //populate the role membership
            List<UserGroupType> groups = getUserGroups(csmUser.getUserId().toString());
            if(!CollectionUtils.isEmpty(groups)){
               ProvisioningSession session = provisioningSessionFactory.createSession(csmUser.getUserId());
               for(UserGroupType role : groups){
                   SuiteRole suiteRole = SuiteRole.getByCsmName(role.getCsmName());
                   if(!suiteRole.isScoped()){
                         _user.findRoleMembership(role);
                   }else{
                       SuiteRoleMembership suiteRoleMembership = session.getProvisionableRoleMembership(suiteRole);
                       RoleMembership roleMembership = _user.findRoleMembership(role);
                       if(suiteRoleMembership.isAllSites()){
                           roleMembership.setAllSite(suiteRoleMembership.isAllSites());
                       }else{
                           roleMembership.getOrganizationNCICodes().addAll(suiteRoleMembership.getSiteIdentifiers());
                       }
                       if(suiteRole.isStudyScoped()){
                          if(suiteRoleMembership.isAllStudies()){
                                roleMembership.setAllStudy(suiteRoleMembership.isAllStudies());
                           }else{
                               roleMembership.getStudyIdentifiers().addAll(suiteRoleMembership.getStudyIdentifiers());
                           }
                       }

                   }
               }
            }

            //add the user into the cache.
            CSMCacheManager.addUserToCache(loginName, _user);

        }


		return _user;
	}	
	
    /**
     * Fetches the groups associated to users.
     *
     * @param csmUserId  - The csm Id of the user
     * @return the user groups
     */
    public List<UserGroupType> getUserGroups(String csmUserId) {
    	List<UserGroupType> userGroups = new ArrayList<UserGroupType>();
    	try {
			Set groups = userProvisioningManager.getGroups(csmUserId);
			if(groups != null){
				for(Object obj : groups){
					Group group = (Group) obj;
					UserGroupType userGroupType = UserGroupType.valueOf(group.getGroupName());
					if(userGroupType != null) userGroups.add(userGroupType);
				}
			}
		} catch (CSObjectNotFoundException e) {
			logger.warn("unable to fetch groups for CSM user (" + csmUserId + "), something is wrong", e);
		}
    	return userGroups;
    }
    
    /**
     * Update csm user.
     *
     * @param user the user
     */
    @Transactional(readOnly = false)
    protected void updateCSMUser(User user,boolean updatingDetails){
        try {
        	if (updatingDetails) {
        		user.getCsmUser().setPassword(decryptString(user.getCsmUser().getPassword()));
        	}
            userProvisioningManager.modifyUser(user.getCsmUser());
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Couldn't update CSM user: ", e);
        }    	
    }
    
    /**
     * Creates the csm user.
     *
     * @param user the user
     */
    @Transactional(readOnly = false)
    protected void createCSMUser(User user){
    	gov.nih.nci.security.authorization.domainobjects.User dbCsmUser = userProvisioningManager.getUser(user.getLoginName());
    	if(dbCsmUser != null)  throw new CaaersSystemException("Couldn't add user: " + user.getLoginName() + ": already exists.");
    	try{
        	user.getCsmUser().setPassword(encryptString((StringUtils.isEmpty(user.getSalt()) ? "" : user.getSalt() ) + "obscurity"));
        	user.generateNewToken();
        	userProvisioningManager.createUser(user.getCsmUser());
    	}catch (CSTransactionException e2) {
        	logger.error("Error creating csm user",e2);
            throw new CaaersSystemException("Could not create user", e2);
        }
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#unlockUser(gov.nih.nci.cabig.caaers.domain.User)
     */
    public void unlockUser(User user){
    	user.unlock();
    	save(user);
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#userChangePassword(gov.nih.nci.cabig.caaers.domain.User, java.lang.String, int)
     */
    public void userChangePassword(User user, String password, int maxHistorySize) {
        user.resetToken();
        user.setPasswordLastSet(new Timestamp(new Date().getTime()));
        user.addPasswordToHistory(DigestUtils.shaHex(password), maxHistorySize);
        user.getCsmUser().setPassword((StringUtils.isEmpty(user.getSalt()) ? "" : user.getSalt() ) + password);
        updateCSMUser(user,false);
        save(user);
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#loginIDInUse(java.lang.String)
     */
    public boolean loginIDInUse(String loginId) {
    	if(userProvisioningManager.getUser(loginId) != null ) return true;
		return false;
    }
    
	/**
	 * Encrypt string.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String encryptString(String string){
    	try{
    		return new StringEncrypter().encrypt(string);
    	}catch (StringEncrypter.EncryptionException enX) {
    		throw new CaaersSystemException("Could not encrypt string",enX);
    	}
    }
	
	/**
	 * Decrypt string.
	 *
	 * @param string the string
	 * @return the string
	 */
	public String decryptString(String encryptedString){
    	try{
    		return new StringEncrypter().decrypt(encryptedString);
    	}catch (StringEncrypter.EncryptionException enX) {
    		throw new CaaersSystemException("Could not decrypt string",enX);
    	}
    }
	
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#sendUserEmail(java.lang.String, java.lang.String, java.lang.String)
     */
    public void sendUserEmail(String emailAddress, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }
    
    /**
     * Send create account email.
     *
     * @param user the user
     * @param changeURL the change url
     */
    protected void sendCreateAccountEmail(User user, String changeURL){

        String EMAIL_SUBJECT = getMessageSource().getMessage("createAccountEmail.subject", null,"Your new caAERS account", Locale.getDefault());
        String EMAIL_TEXT = getMessageSource().getMessage("createAccountEmail.text", new Object[] {user.getCsmUser().getLoginName(), changeURL + "&token=" + user.getToken()},
                "A new caAERS account has been created for you.\\nYour username is: {0}\\n You must create your password to login to caAERS. You can do so by clicking on the URL below:\\n\\n{1}\\n\\n Sent by the caAERS Notification System",
                Locale.getDefault());;
        
        //send out an email
        if ("local".equals(getAuthenticationMode())) {
            sendUserEmail(user.getCsmUser().getEmailId(), EMAIL_SUBJECT, EMAIL_TEXT);
        }
    }
    
    /**
     * Send update account email.
     *
     * @param user the user
     */
    protected void sendUpdateAccountEmail(User user){
    	if ("local".equals(getAuthenticationMode())) {
    		sendUserEmail(user.getCsmUser().getEmailId(), "Your updated caAERS account", "Your caAERS account has been updated");  // annoying for development
    	}
    }

    /**
     * Will provision in SuiteCSM all the roles.
     *
     * @param user the user
     */
    public void provisionUser(User user) {

        ProvisioningSession session = provisioningSessionFactory.createSession(user.getCsmUser().getUserId());

        //delete all the roles
        for(SuiteRole suiteRole : SuiteRole.values()){
            logger.debug("Deleting suite role " + suiteRole.name());
            session.deleteRole(suiteRole);
        }

        //add selected roles
        for(Map.Entry<UserGroupType , RoleMembership> membershipEntry : user.getRoleMembershipMap().entrySet()){
            UserGroupType userRole = membershipEntry.getKey();
            RoleMembership userMembership = membershipEntry.getValue();

            //find the SuiteRoleMembership
            SuiteRole suiteRole = SuiteRole.getByCsmName(userRole.getCsmName());
            SuiteRoleMembership suiteRoleMembership = session.getProvisionableRoleMembership(suiteRole);
            if(suiteRole.isScoped()){

                //add new entries
                if(suiteRole.isStudyScoped()){
                    //study scoped roles only can provision study information
                    if(userMembership.isAllStudy()){
                        suiteRoleMembership.forAllStudies();
                    }else{
                        suiteRoleMembership.forStudies(userMembership.getStudyIdentifiers());
                    }
                }
                //both site and study scoped roles, can provision organizations information 
                if(userMembership.isAllSite()){
                    suiteRoleMembership.forAllSites();
                }else{
                    suiteRoleMembership.forSites(userMembership.getOrganizationNCICodes());
                }

            }

            //replace the old role membership with new one. 
            session.replaceRole(suiteRoleMembership);
            logger.debug("provisioned suite role " + suiteRole.name());

        }

    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.repository.UserRepository#search(gov.nih.nci.cabig.caaers.dao.query.UserQuery)
     */
    public List<User> search(UserQuery query) {
        return (List<User>)userDao.search(query);
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
	 * Sets the user provisioning manager.
	 *
	 * @param userProvisioningManager the new user provisioning manager
	 */
	public void setUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}

	/**
	 * Sets the mail sender.
	 *
	 * @param mailSender the new mail sender
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
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

	/**
	 * Gets the mail sender.
	 *
	 * @return the mail sender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

    /**
     * Gets the provisioning session factory.
     *
     * @return the provisioning session factory
     */
    public ProvisioningSessionFactory getProvisioningSessionFactory() {
        return provisioningSessionFactory;
    }

    /**
     * Sets the provisioning session factory.
     *
     * @param provisioningSessionFactory the new provisioning session factory
     */
    public void setProvisioningSessionFactory(ProvisioningSessionFactory provisioningSessionFactory) {
        this.provisioningSessionFactory = provisioningSessionFactory;
    }
}
