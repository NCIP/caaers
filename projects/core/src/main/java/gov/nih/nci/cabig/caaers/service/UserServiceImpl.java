package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserProvisioningManager userProvisioningManager;
    //public static final String STUDY_CALENDAR_APPLICATION_ID = "2";
    private CSMObjectIdGenerator siteObjectIdGenerator;

    private MailSender mailSender;
    private SimpleMailMessage accountCreatedTemplateMessage;


    private Logger log = Logger.getLogger(UserServiceImpl.class);

    public void createOrUpdateCSMUserAndGroupsForResearchStaff(final ResearchStaff researchStaff) {

        gov.nih.nci.security.authorization.domainobjects.User csmUser = createOrUpdateCsmUser(researchStaff);
        researchStaff.setLoginId(csmUser.getUserId().toString());
        createCSMUserGroups(researchStaff);

    }
     private void createCSMUserGroups(final ResearchStaff researchStaff){

         try {
            List<String> groupIds = new ArrayList<String>();

            for (UserGroupType group : researchStaff.getUserGroupTypes()) {
                groupIds.add(group.getCode().toString());
            }

            String organizationGroupId = getGroupIdByName(siteObjectIdGenerator.generateId(researchStaff.getOrganization()));
            groupIds.add(organizationGroupId);
            assignUserToGroup(researchStaff.getLoginId(), groupIds.toArray(new String[groupIds.size()]));


        } catch (CSObjectNotFoundException e) {
            new CaaersSystemException("Could not assign user to organization group.");
        }
        log.debug("Successfully assigned user to organization");

     }
    private gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCsmUser(final ResearchStaff researchStaff) {

        String emailId = researchStaff.getEmailAddress();
        if (emailId == null) {
            throw new CaaersSystemException("Email address is required");
        }
        gov.nih.nci.security.authorization.domainobjects.User csmUser = null;

        if (researchStaff.getId() != null) {
            //FIXME:Saurabh check for existing research staff with null login id..
            try {
                csmUser = userProvisioningManager.getUserById(researchStaff.getLoginId());
            } catch (CSObjectNotFoundException e) {
                
            }
        } else {
            csmUser = new gov.nih.nci.security.authorization.domainobjects.User();
        }

        csmUser.setLoginName(emailId);
        csmUser.setEmailId(emailId);
        csmUser.setPhoneNumber(researchStaff.getPhoneNumber());

        csmUser.setFirstName(researchStaff.getFirstName());
        csmUser.setLastName(researchStaff.getLastName());
        //FIXME:Saurabh dont update the password
         csmUser.setPassword(researchStaff.getLastName());
        csmUser.setOrganization(researchStaff.getOrganization().getName());
        csmUser.setOrganization(researchStaff.getOrganization().getNciInstituteCode());

        String text = "";

        try {

            if (researchStaff.getId() != null) {
                //FIXME:Saurabh check for existing research staff with null login id..
                //FIXME:Saurabh chek if user already exists with same email add..
                userProvisioningManager.modifyUser(csmUser);
                log.debug("updating  user");
                text = "Your account has been updated .\n" +
                        " Username:" + csmUser.getLoginName() + " Password:" + csmUser.getPassword() + "" +
                        "\n -caAERS admin";
            } else {
                text = "An account has been created for you.\n" +
                        " Username:" + csmUser.getLoginName() + " Password:" + csmUser.getPassword() + "" +
                        "\n -caaERS admin";
                userProvisioningManager.createUser(csmUser);
                log.debug("Saving  user");
            }

            //researchStaffDao.save(staff);

        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Could not create user", e);
        }

        try {
            SimpleMailMessage msg = new SimpleMailMessage(this.accountCreatedTemplateMessage);
            msg.setTo(emailId);
            msg.setText(text);
            //  this.mailSender.send(msg);
        } catch (MailException e) {
            throw new CaaersSystemException("Could not send confirmation email to user", e);
        }

        return csmUser;

    }


    private void assignUserToGroup(String userId, String[] groupIds) throws CaaersSystemException {
        try {
            userProvisioningManager.assignGroupsToUser(userId, groupIds);
        } catch (CSTransactionException e) {
            throw new CaaersSystemException("Could not add user to group", e);
        }
    }

    private String getGroupIdByName(String groupName) throws CSObjectNotFoundException {
        Group search = new Group();
        search.setGroupName(groupName);
        GroupSearchCriteria sc = new GroupSearchCriteria(search);
        Group returnGroup = (Group) userProvisioningManager.getObjects(sc).get(0);
        return returnGroup.getGroupId().toString();

    }

    
    @Required
    public void setUserProvisioningManager(UserProvisioningManager userProvisioningManager) {
        this.userProvisioningManager = userProvisioningManager;
    }

    @Required
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Required
    public void setAccountCreatedTemplateMessage(SimpleMailMessage accountCreatedTemplateMessage) {
        this.accountCreatedTemplateMessage = accountCreatedTemplateMessage;
    }

    @Required
    public void setSiteObjectIdGenerator(CSMObjectIdGenerator siteObjectIdGenerator) {
        this.siteObjectIdGenerator = siteObjectIdGenerator;
    }
}