package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersNoSuchUserException;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class CSMUserRepositoryImpl implements CSMUserRepository {

    private UserProvisioningManager userProvisioningManager;
    private CSMObjectIdGenerator siteObjectIdGenerator;
    private MailSender mailSender;
    private SimpleMailMessage accountCreatedTemplateMessage;
    private String authenticationMode;
    private UserDao userDao;
    
    //private ResearchStaffDao researchStaffDao;
    //private InvestigatorDao investigatorDao;
    
    private Logger log = Logger.getLogger(CSMUserRepositoryImpl.class);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, noRollbackFor = MailException.class)
    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff, String changeURL) {
        gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
        MailException mailException = null;
        
        try {
			if (researchStaff.getId() == null) {
			    csmUser = createCSMUser(researchStaff);
			    sendCreateAccountEmail(researchStaff, changeURL);
			} else {
			    csmUser = updateCSMUser(researchStaff);
			    sendUpdateAccountEmail(researchStaff);
			}
		} catch (MailException e) {
			mailException = e;
		}
        List<Organization> associatedOrgList = new ArrayList<Organization>();
        associatedOrgList.add(researchStaff.getOrganization());
        createCSMUserGroups(csmUser, researchStaff, associatedOrgList);
        if(mailException != null) throw mailException;
    }

    public void createOrUpdateCSMUserAndGroupsForInvestigator(Investigator investigator, String changeURL) {
    	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
    	MailException mailException = null;
    	
    	try {
			if(investigator.getId() == null){
				csmUser = createCSMUser(investigator);
				sendCreateAccountEmail(investigator, changeURL);
			}else{
				csmUser = updateCSMUser(investigator);
				sendUpdateAccountEmail(investigator);
			}
		} catch (MailException e) {
			mailException = e;
		}
    	List<Organization> associatedOrgList = new ArrayList<Organization>();
    	for(SiteInvestigator siteInv : investigator.getSiteInvestigatorsInternal()){
    		associatedOrgList.add(siteInv.getOrganization());
    	}
        createCSMUserGroups(csmUser, investigator, associatedOrgList);
        if(mailException != null) throw mailException;
    }
    
    private void createCSMUserGroups(final gov.nih.nci.security.authorization.domainobjects.User csmUser, final User user, List<Organization> allowedOrgs) {
        try {
            List<String> groupIds = new ArrayList<String>();
            for (UserGroupType group : user.getUserGroupTypes()) {
                groupIds.add(group.getCode().toString());
            }
            for(Organization org :  allowedOrgs){
            	String organizationGroupId = getGroupIdByName(siteObjectIdGenerator.generateId(org.getNciInstituteCode()));
                groupIds.add(organizationGroupId);
            }
            
            if (csmUser.getUserId() == null) {
                throw new CaaersSystemException("ID has not been assigned to CSM user.");
            }
            assignUserToGroup(String.valueOf(csmUser.getUserId()), groupIds.toArray(new String[groupIds.size()]));
        } catch (CSObjectNotFoundException e) {
            throw new CaaersSystemException("Could not assign user to organization group.", e);
        }
        log.debug("Successfully assigned user to organization");
    }

    private void copyUserToCSMUser(User user, gov.nih.nci.security.authorization.domainobjects.User csmUser) {
        String emailId = user.getEmailAddress();
        if (user.getLoginId() == null || user.getLoginId().trim().length() == 0) {
            csmUser.setLoginName(emailId);
        } else {
            csmUser.setLoginName(user.getLoginId());
        }
        csmUser.setEmailId(emailId);
// this line is causing issues, the CSM_USER table has the phone length as 15 chars, and the phone can be wider, as it can have spaces and extension        
//        csmUser.setPhoneNumber(user.getPhoneNumber());
        csmUser.setFirstName(user.getFirstName());
        csmUser.setLastName(user.getLastName());
        // psc does not use these
        // do we really need this? csmUser.setOrganization(researchStaff.getOrganization().getName());
        // or this? csmUser.setOrganization(researchStaff.getOrganization().getNciInstituteCode());
    }

    private gov.nih.nci.security.authorization.domainobjects.User createCSMUser(final User user) {
        // assumes research staff id is null
        String loginId = user.getLoginId();
        gov.nih.nci.security.authorization.domainobjects.User csmUser;
        
    	csmUser = getCSMUserByName(loginId);
    	if(csmUser != null)  throw new CaaersSystemException("Couldn't add user: " + loginId + ": already exists.");
    	
    	//we can create a new user
    	csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
        copyUserToCSMUser(user, csmUser);
        csmUser.setPassword(encryptString(user.getSalt() + "obscurity"));
        userCreateToken(user);
        //create a csm user
        try {
            userProvisioningManager.createUser(csmUser);
        } catch (CSTransactionException e2) {
        	log.error("Error creating csm user",e2);
            throw new CaaersSystemException("Could not create user", e2);
        }
        
        return csmUser;
    }
    
    private void sendCreateAccountEmail(User user, String changeURL){

        //send out an email
        if ("local".equals(getAuthenticationMode())) {
            sendUserEmail(user.getEmailAddress(), "Your new caAERS account", "A new caAERS account has been created for you.\n"
                    + "Your username is follows:\n"
                    + "Username: " + user.getLoginId()
                    + "\n"
                    + "You must change your password before you can login. In order to do so please visit this URL:\n"
                    + "\n"
                    + changeURL + "&token=" + user.getToken() + "\n"
                    + "\n"
                    + "Regards\n"
                    + "The caAERS Notification System.\n");
        }
    }
    private void sendUpdateAccountEmail(User user){
    	sendUserEmail(user.getEmailAddress(), "Your updated caAERS account", "Your caAERS account has been updated");  // annoying for development
    }
    private gov.nih.nci.security.authorization.domainobjects.User updateCSMUser(final User user) {
        String loginId = user.getLoginId();
        gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(loginId);
        copyUserToCSMUser(user, csmUser);
        saveCSMUser(csmUser);
        return csmUser;
    }

    private void assignUserToGroup(final String userId, final String[] groupIds) throws CaaersSystemException {
        try {
            userProvisioningManager.assignGroupsToUser(userId, groupIds);
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Could not add user to group", e);
        }
    }

    private String getGroupIdByName(final String groupName) throws CSObjectNotFoundException {
        Group search = new Group();
        search.setGroupName(groupName);
        GroupSearchCriteria sc = new GroupSearchCriteria(search);
        Group returnGroup = (Group) userProvisioningManager.getObjects(sc).get(0);
        return returnGroup.getGroupId().toString();
    }

    
    public boolean loginIDInUse(String loginId) {
    	if(getCSMUserByName(loginId) != null ) return true;
    	try {
			if(getUserByName(loginId) != null)
				return true;
		} catch (CaaersNoSuchUserException e) {
		}
		return false;
    }
    // jf
  

    private gov.nih.nci.security.authorization.domainobjects.User getCSMUserByName(String userName) {
        return userProvisioningManager.getUser(userName);
    }
    
   

    private void saveCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
        try {
            userProvisioningManager.modifyUser(csmUser);
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Couldn't save CSM user: ", e);
        }
    }

    public User getUserByName(String userName) {
    	User user = userDao.getByLoginId(userName);
    	if(user == null){
    		throw new CaaersNoSuchUserException("User with login Id :" + userName + " unknowon");
    	}
    	
    	//populate the UserGroupTypes
    	try {
			gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(user.getLoginId());
			if(csmUser != null){
				Set groups = userProvisioningManager.getGroups(csmUser.getUserId().toString());
				if(groups != null){
					for(java.util.Iterator it = groups.iterator(); it.hasNext(); ){
						Group group = (Group) it.next();
						UserGroupType userGroupType = UserGroupType.getByCode(group.getGroupId().intValue());
						if(userGroupType != null) user.addUserGroupType(userGroupType);
					}
				}
			}
		} catch (CSObjectNotFoundException e) {
			log.warn("The groups for csmUser (" + user.getLoginId() + ") unable to fetch, something is wrong", e);
		}
    	
        return user;
    }

    public String userCreateToken(User user) {
    	user.setTokenTime(new Timestamp(new Date().getTime()));
        user.setToken(encryptString(user.getSalt() + user.getTokenTime().toString()
                + "random_string").replaceAll("\\W", "Q"));
        return user.getToken();
    }

    

    public void userChangePassword(User user, String password, int maxHistorySize) {
        gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(user.getLoginId());
        user.resetToken();
        user.setPasswordLastSet(new Timestamp(new Date().getTime()));
        user.addPasswordToHistory(csmUser.getPassword(), maxHistorySize);
        csmUser.setPassword(user.getSalt() + password);
        saveCSMUser(csmUser);
    }

    public boolean userHasPassword(String userName, String password) {
        return encryptString(getUserByName(userName).getSalt()
                + password).equals(getCSMUserByName(userName).getPassword());
    }

    public boolean userHadPassword(String userName, String password) {
        return getUserByName(userName).getPasswordHistory().contains(encryptString(password));
    }

    public void sendUserEmail(String emailAddress, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }

    private String encryptString(String string) {
        try {
            return new StringEncrypter().encrypt(string);
        } catch (StringEncrypter.EncryptionException e) {
            throw new CaaersSystemException(e);
        }
    }
    
  
    public boolean isSuperUser(String loginId) {
       try {
		if(StringUtils.isEmpty(loginId)) return false;
		
		gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(loginId);
		if(csmUser == null) return false;
		
		Set groups = userProvisioningManager.getGroups(csmUser.getUserId().toString());
		if(groups != null){
			for(java.util.Iterator it = groups.iterator() ; it.hasNext();){
				Group group = (Group) it.next();
				if(StringUtils.containsIgnoreCase(group.getGroupName(), UserGroupType.caaers_admin.getCsmName()) || 
				   StringUtils.containsIgnoreCase(group.getGroupName(), UserGroupType.caaers_super_user.getCsmName())	){
					return true;
				}
			}
		}
			
	} catch (CSObjectNotFoundException e) {
		log.warn("The login " + loginId + ", not found in CSM, something is wrong in the way the login ID is fetched");
	}
    	
    	return false;
    }

    // end

    @Required
    public void setUserProvisioningManager(final UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }

    @Required
    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Required
    public void setAccountCreatedTemplateMessage(final SimpleMailMessage accountCreatedTemplateMessage) {
        this.accountCreatedTemplateMessage = accountCreatedTemplateMessage;
    }

    @Required
    public void setSiteObjectIdGenerator(final CSMObjectIdGenerator siteObjectIdGenerator) {
        this.siteObjectIdGenerator = siteObjectIdGenerator;
    }
    @Required
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
   
    @Required
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }
}
