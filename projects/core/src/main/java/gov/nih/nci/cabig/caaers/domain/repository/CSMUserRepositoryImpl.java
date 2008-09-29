package gov.nih.nci.cabig.caaers.domain.repository;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.UserDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSMUserRepositoryImpl implements CSMUserRepository {

    private UserProvisioningManager userProvisioningManager;
    private UserDao userDao;
    private ResearchStaffDao researchStaffDao;
    private CSMObjectIdGenerator siteObjectIdGenerator;
    private MailSender mailSender;
    private SimpleMailMessage accountCreatedTemplateMessage;
    private String authenticationMode;
    private Logger log = Logger.getLogger(CSMUserRepositoryImpl.class);

    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff, String changeURL) {
        gov.nih.nci.security.authorization.domainobjects.User csmUser;
        /* this should be done by a validator */
        if (researchStaff.getEmailAddress() == null) {
            throw new CaaersSystemException("Email address is required");
        } else if (researchStaff.getId() == null) {
            csmUser = createCSMUserForResearchStaff(researchStaff, changeURL);
        } else {
            csmUser = updateCSMUserForResearchStaff(researchStaff);
        }
        createCSMUserGroupsForResearchStaff(csmUser, researchStaff);
    }

    private void createCSMUserGroupsForResearchStaff(final gov.nih.nci.security.authorization.domainobjects.User csmUser, final ResearchStaff researchStaff) {
        try {
            List<String> groupIds = new ArrayList<String>();
            for (UserGroupType group : researchStaff.getUserGroupTypes()) {
                groupIds.add(group.getCode().toString());
            }
            String organizationGroupId = getGroupIdByName(siteObjectIdGenerator.generateId(researchStaff.getOrganization()));
            groupIds.add(organizationGroupId);
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
        csmUser.setPhoneNumber(user.getPhoneNumber());
        csmUser.setFirstName(user.getFirstName());
        csmUser.setLastName(user.getLastName());
        // psc does not use these
        // do we really need this? csmUser.setOrganization(researchStaff.getOrganization().getName());
        // or this? csmUser.setOrganization(researchStaff.getOrganization().getNciInstituteCode());
    }

    private gov.nih.nci.security.authorization.domainobjects.User createCSMUserForResearchStaff(final ResearchStaff researchStaff, String changeURL) {
        // assumes research staff id is null
        String emailId = researchStaff.getEmailAddress();
        gov.nih.nci.security.authorization.domainobjects.User csmUser;
        try {
            getCSMUserByName(emailId);
            throw new CaaersSystemException("Couldn't add user: " + emailId + ": already exists.");
        } catch (CaaersNoSuchUserException e) {
            csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
            copyUserToCSMUser(researchStaff, csmUser);
            csmUser.setPassword(encryptString(researchStaff.getSalt() + "obscurity"));
            createCSMUser(csmUser);
            if (StringUtils.isBlank(researchStaff.getLoginId())) {
                researchStaff.setLoginId(researchStaff.getEmailAddress());
            }
            researchStaffDao.save(researchStaff);
            if ("local".equals(getAuthenticationMode())) {
                sendUserEmail(emailId, "Your new caAERS account", "A new caAERS account has been created for you.\n"
                        + "\n"
                        + "You must change your password before you can login. In order to do so please visit this URL:\n"
                        + "\n"
                        + changeURL + "&token=" + userCreateToken(emailId) + "\n"
                        + "\n"
                        + "Regards\n"
                        + "The caAERS Notification System.\n");
            }
            return csmUser;
        }
    }

    private gov.nih.nci.security.authorization.domainobjects.User updateCSMUserForResearchStaff(final ResearchStaff researchStaff) {
        String emailId = researchStaff.getEmailAddress();
        // FIXME:Biju check for existing research staff with null login id....user must not be able to update the login name
        gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(emailId);
        copyUserToCSMUser(researchStaff, csmUser);
        saveCSMUser(csmUser);
        researchStaffDao.save(researchStaff);
        /* sendUserEmail(emailId, "Your updated caAERS account", "Your caAERS account has been updated"); */ // annoying for development
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

    // jf
    private void createCSMUser(gov.nih.nci.security.authorization.domainobjects.User csmUser) {
        try {
            userProvisioningManager.createUser(csmUser);
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Could not create user", e);
        }
    }

    private gov.nih.nci.security.authorization.domainobjects.User getCSMUserByName(String userName) {
        gov.nih.nci.security.authorization.domainobjects.User csmUser = userProvisioningManager.getUser(userName);
        if (csmUser == null) throw new CaaersNoSuchUserException("No such CSM user.");
        return csmUser;
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
        if (user == null) throw new CaaersNoSuchUserException("No such user.");
        return user;
    }

    public void saveUser(User user) {
        // this should be the way its done, but its not
        userDao.save(user);
        // get the csm user, save or create
    }

    public String userCreateToken(String userName) {
        User user = getUserByName(userName);
        user.setTokenTime(new Timestamp(new Date().getTime()));
        user.setToken(encryptString(user.getSalt() + user.getTokenTime().toString()
                + "random_string").replaceAll("\\W", "Q"));
        userDao.save(user);
        return user.getToken();
    }

    public void userChangePassword(String userName, String password, int maxHistorySize) {
        User user = getUserByName(userName);
        gov.nih.nci.security.authorization.domainobjects.User csmUser = getCSMUserByName(userName);
        user.resetToken();
        user.setPasswordLastSet(new Timestamp(new Date().getTime()));
        user.addPasswordToHistory(csmUser.getPassword(), maxHistorySize);
        csmUser.setPassword(user.getSalt() + password);
        userDao.save(user);
        saveCSMUser(csmUser);
    }

    public boolean userHasPassword(String userName, String password) {
        return encryptString(getUserByName(userName).getSalt()
                + password).equals(getCSMUserByName(userName).getPassword());
    }

    public boolean userHadPassword(String userName, String password) {
        return getUserByName(userName).getPasswordHistory().contains(encryptString(password));
    }

    public void sendUserEmail(String userName, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(getUserByName(userName).getEmailAddress());
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        } catch (MailException e) {
            throw new CaaersSystemException("Could not send email to user.", e);
        }
    }

    private String encryptString(String string) {
        try {
            return new StringEncrypter().encrypt(string);
        } catch (StringEncrypter.EncryptionException e) {
            throw new CaaersSystemException(e);
        }
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
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Required
    public void setResearchStaffDao(final ResearchStaffDao researchStaffDao) {
        this.researchStaffDao = researchStaffDao;
    }

    public class CaaersNoSuchUserException extends CaaersSystemException {
        public CaaersNoSuchUserException(String message) {
            super(message);
        }
    }

    @Required
    public String getAuthenticationMode() {
        return authenticationMode;
    }

    public void setAuthenticationMode(String authenticationMode) {
        this.authenticationMode = authenticationMode;
    }
}
