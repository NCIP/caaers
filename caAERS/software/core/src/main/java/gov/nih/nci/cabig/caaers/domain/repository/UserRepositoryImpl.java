package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao._UserDao;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain._User;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryImpl implements UserRepository {

	private Logger logger = Logger.getLogger(UserRepositoryImpl.class);
	private _UserDao userDao;
	private UserProvisioningManager userProvisioningManager;
    private MailSender mailSender;
    private String authenticationMode;
    private MessageSource messageSource;
    
	
    @SuppressWarnings("unchecked")
	public List searchCsmUser(String firstName, String lastName, String userName) {
    	if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName) && StringUtils.isEmpty(userName)) firstName = "%";
    	
    	gov.nih.nci.security.authorization.domainobjects.User example = new gov.nih.nci.security.authorization.domainobjects.User();
    	if(StringUtils.isNotEmpty(firstName)) example.setFirstName(firstName);
    	if(StringUtils.isNotEmpty(lastName)) example.setLastName(lastName);
    	if(StringUtils.isNotEmpty(userName)) example.setLoginName(userName);
    	UserSearchCriteria userSearchCriteria = new UserSearchCriteria(example);
    	return userProvisioningManager.getObjects(userSearchCriteria);	
	}
	
    @Transactional(readOnly = false)
    public void save(_User user){
    	userDao.save(user);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, noRollbackFor = MailException.class)
	public void createOrUpdateUser(_User user,String changeURL){
		if(user.getCsmUser().getUserId() == null){
			createCSMUser(user);
			save(user);
			sendCreateAccountEmail(user,changeURL);
		}else{
			updateCSMUser(user);
			sendUpdateAccountEmail(user);
		}
	}
	
    @Transactional(readOnly = true)
	public _User getUserByLoginName(String loginName) {
		_User _user = userDao.getByLoginName(loginName);
		if(_user == null){
			_user = new _User();
		}
		gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(loginName);
		_user.setCsmUser(csmUser);
		if(csmUser != null){
			_user.setUserGroupTypes(getUserGroups(csmUser.getUserId().toString()));
		}
		if(StringUtils.isEmpty(_user.getLoginName()) &&  _user.getCsmUser() == null){
			return null;
		}
		if(! StringUtils.isEmpty(_user.getLoginName()) &&  _user.getCsmUser() == null){
			throw new CaaersSystemException("caAERS user exists and relevant CSM user does not exist : Data Integrity issue");
		}
		return _user;
	}	
	
    /**
     * Fetches the groups associated to users.  
     * @param userName
     * @return
     */
    public List<UserGroupType> getUserGroups(String loginName) {
    	List<UserGroupType> userGroups = new ArrayList<UserGroupType>();
    	try {
			Set groups = userProvisioningManager.getGroups(loginName);
			if(groups != null){
				for(Object obj : groups){
					Group group = (Group) obj;
					UserGroupType userGroupType = UserGroupType.valueOf(group.getGroupName());
					if(userGroupType != null) userGroups.add(userGroupType);
				}
			}
		} catch (CSObjectNotFoundException e) {
			logger.warn("unable to fetch groups for CSM user (" + loginName + "), something is wrong", e);
		}
    	return userGroups;
    }
    
    @Transactional(readOnly = false)
    protected void updateCSMUser(_User user){
        try {
            userProvisioningManager.modifyUser(user.getCsmUser());
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Couldn't update CSM user: ", e);
        }    	
    }
    
    @Transactional(readOnly = false)
    protected void createCSMUser(_User user){
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
    
    public void unlockUser(_User user){
    	user.unlock();
    	save(user);
    }
    
    public void userChangePassword(_User user, String password, int maxHistorySize) {
        user.resetToken();
        user.setPasswordLastSet(new Timestamp(new Date().getTime()));
        user.addPasswordToHistory(DigestUtils.shaHex(password), maxHistorySize);
        user.getCsmUser().setPassword((StringUtils.isEmpty(user.getSalt()) ? "" : user.getSalt() ) + password);
        updateCSMUser(user);
        save(user);
    }
    
    public boolean loginIDInUse(String loginId) {
    	if(userProvisioningManager.getUser(loginId) != null ) return true;
		return false;
    }
    
	public String encryptString(String string){
    	try{
    		return new StringEncrypter().encrypt(string);
    	}catch (StringEncrypter.EncryptionException enX) {
    		throw new CaaersSystemException("Could not encrypt string",enX);
    	}
    }
    
    public void sendUserEmail(String emailAddress, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }
    
    protected void sendCreateAccountEmail(_User user, String changeURL){

        String EMAIL_SUBJECT = getMessageSource().getMessage("createAccountEmail.subject", null, Locale.getDefault());
        String EMAIL_TEXT = getMessageSource().getMessage("createAccountEmail.text", new Object[] {user.getCsmUser().getLoginName(), changeURL + "&token=" + user.getToken()}, Locale.getDefault());;
        
        //send out an email
        if ("local".equals(getAuthenticationMode())) {
            sendUserEmail(user.getCsmUser().getEmailId(), EMAIL_SUBJECT, EMAIL_TEXT);
        }
    }
    
    protected void sendUpdateAccountEmail(_User user){
    	if ("local".equals(getAuthenticationMode())) {
    		sendUserEmail(user.getCsmUser().getEmailId(), "Your updated caAERS account", "Your caAERS account has been updated");  // annoying for development
    	}
    }  
    
    
	public void setUserDao(_UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.userProvisioningManager = userProvisioningManager;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
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

	public MailSender getMailSender() {
		return mailSender;
	}
}
