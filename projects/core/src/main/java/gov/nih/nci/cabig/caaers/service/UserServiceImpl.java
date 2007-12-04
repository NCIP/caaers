package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class UserServiceImpl implements UserService {

    private UserProvisioningManager userProvisioningManager;
    private CSMObjectIdGenerator siteObjectIdGenerator;
    private MailSender mailSender;
    private SimpleMailMessage accountCreatedTemplateMessage;
    private Logger log = Logger.getLogger(UserServiceImpl.class);
    
    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff) {
	gov.nih.nci.security.authorization.domainobjects.User csmUser = createOrUpdateCsmUser(researchStaff);
	researchStaff.setLoginId(csmUser.getUserId().toString());
	createCSMUserGroups(researchStaff);	
    }
    
    private void createCSMUserGroups(final ResearchStaff researchStaff) {
	try {
	    List<String> groupIds = new ArrayList<String>();
	    for (UserGroupType group : researchStaff.getUserGroupTypes()) {
		groupIds.add(group.getCode().toString());
	    }
	    String organizationGroupId = getGroupIdByName(siteObjectIdGenerator.generateId(researchStaff.getOrganization()));											   
	    groupIds.add(organizationGroupId);
	    assignUserToGroup(researchStaff.getLoginId(), groupIds.toArray(new String[groupIds.size()]));
	} catch (CSObjectNotFoundException e) {
	    throw new CaaersSystemException("Could not assign user to organization group.", e);
	}
	log.debug("Successfully assigned user to organization");
    }

    // should be called createOrUpdateResearchStaff if that's all it does...
    private gov.nih.nci.security.authorization.domainobjects.User  createOrUpdateCsmUser(final ResearchStaff researchStaff) {
	gov.nih.nci.security.authorization.domainobjects.User csmUser = null;
	String emailId = researchStaff.getEmailAddress();
	if (emailId == null) throw new CaaersSystemException("Email address is required");
	else if (researchStaff.getId() == null) {
	    if (userProvisioningManager.getUser(emailId) != null) {
		throw new CaaersSystemException("Couldn't add user " + researchStaff.toString() + ": email address already exists.");
	    }
	    csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
	} else {
	    // FIXME:Biju check for existing research staff with null login id....user must not be able to update the login name
	    csmUser = userProvisioningManager.getUser(emailId);
	    if (csmUser == null) throw new CaaersSystemException("Can not update the research staff becasue no csm user exists.....!");
	}

	csmUser.setLoginName(emailId);
	csmUser.setEmailId(emailId);
	csmUser.setPhoneNumber(researchStaff.getPhoneNumber());
	csmUser.setFirstName(researchStaff.getFirstName());
	csmUser.setLastName(researchStaff.getLastName());
	// FIXME:Biju don't update the password
	csmUser.setPassword(researchStaff.getLastName());
	csmUser.setOrganization(researchStaff.getOrganization().getName());
	csmUser.setOrganization(researchStaff.getOrganization().getNciInstituteCode());

	String text = "";
	try {
	    if (researchStaff.getId() != null) {
		userProvisioningManager.modifyUser(csmUser);
		log.debug("updating  user");
		text = "Your account has been updated .\n" + " Username:" + csmUser.getLoginName() + " Password:"
		    + csmUser.getPassword() + "" + "\n -caAERS admin";
	    } else {
		text = "An account has been created for you.\n" + " Username:" + csmUser.getLoginName() + " Password:"
		    + csmUser.getPassword() + "" + "\n -caaERS admin";
		userProvisioningManager.createUser(csmUser);
		log.debug("Saving  user");
	    }
	} catch (CSTransactionException e) {
	    throw new CaaersSystemException("Could not create user", e);
	}

	try {
	    SimpleMailMessage msg = new SimpleMailMessage(accountCreatedTemplateMessage);
	    msg.setTo(emailId);
	    msg.setText(text);
	    // this.mailSender.send(msg);
	} catch (MailException e) {
	    throw new CaaersSystemException("Could not send confirmation email to user", e);
	}
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

    public User getUserByName(String userName) {
	return null;
    }

    public String userCreateToken(String userName) throws CaaersSystemException {
	User user = getUserByName(userName);
	user.setTokenTime(new Timestamp(new Date().getTime()));
	user.setToken(encryptString(user.getSalt() + user.getTokenTime().toString() + "random_string"));
	return user.getToken();
    }

    public void userChangePassword(String userName, String password, int maxHistorySize) {
	/*
	  csmUser.setPassword(caaersuser.getSalt() + password);	   
	  caaersuser.resetToken();
	  caaersuser.setPasswordLastSet(new Timestamp(new Date().getTime()));
	  add to history (maxHistorySize)
	*/
    }

    public boolean userHasPassword(String userName, String password) {
	// is the password the user's password
	// return encryptString(password).equals(csmUser.getPassword());
	return true;
    }

    public boolean userHadPassword(String userName, String password) {
	// is the password in the user's history
	return true;
    }

    private String encryptString(String string) throws CaaersSystemException {
	try {
	    return new StringEncrypter().encrypt(string);
	} catch (StringEncrypter.EncryptionException e) {
	    throw new CaaersSystemException(e);
	}
    }

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
}
