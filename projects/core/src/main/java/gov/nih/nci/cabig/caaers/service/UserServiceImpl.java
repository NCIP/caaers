package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.util.ArrayList;
import java.util.List;

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

			String organizationGroupId = getGroupIdByName(siteObjectIdGenerator.generateId(researchStaff
					.getOrganization()));
			groupIds.add(organizationGroupId);
			assignUserToGroup(researchStaff.getLoginId(), groupIds.toArray(new String[groupIds.size()]));

		}
		catch (CSObjectNotFoundException e) {
			log.error("Could not assign user to organization group." + e.getMessage(), e);
			new CaaersSystemException("Could not assign user to organization group.");
		}
		log.debug("Successfully assigned user to organization");

	}

	private gov.nih.nci.security.authorization.domainobjects.User createOrUpdateCsmUser(
			final ResearchStaff researchStaff) {

		String emailId = researchStaff.getEmailAddress();
		User csmUser = null;
		if (emailId == null) {
			throw new CaaersSystemException("Email address is required");
		}
		else if (researchStaff.getId() == null) {

			User user = userProvisioningManager.getUser(emailId);

			if (user != null) {
				log.info("Could not add research staff because email address allready exists.."
						+ researchStaff.toString());
				throw new CaaersSystemException("Email address allready exists..!");
			}

			// create new user
			csmUser = new User();
		}
		else if (researchStaff.getId() != null) {
			// FIXME:Biju check for existing research staff with null login id....user must not be able to update the login name
			csmUser = userProvisioningManager.getUser(emailId);

			if (csmUser == null) {
				log.error("Can not update the research staff because no csm user exists..");
				throw new CaaersSystemException("Can not update the research staff becasue no csm user exists.....!");
			}

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
			}
			else {
				text = "An account has been created for you.\n" + " Username:" + csmUser.getLoginName() + " Password:"
						+ csmUser.getPassword() + "" + "\n -caaERS admin";
				userProvisioningManager.createUser(csmUser);
				log.debug("Saving  user");
			}

		}
		catch (CSTransactionException e) {
			log.error("Could not create user" + e.getMessage(), e);
			throw new CaaersSystemException("Could not create user", e);
		}

		try {
			SimpleMailMessage msg = new SimpleMailMessage(accountCreatedTemplateMessage);
			msg.setTo(emailId);
			msg.setText(text);
			// this.mailSender.send(msg);
		}
		catch (MailException e) {
			log.error("Could not send confirmation email to user" + e.getMessage(), e);
			throw new CaaersSystemException("Could not send confirmation email to user", e);
		}

		return csmUser;

	}

	private void assignUserToGroup(final String userId, final String[] groupIds) throws CaaersSystemException {
		try {
			userProvisioningManager.assignGroupsToUser(userId, groupIds);
		}
		catch (CSTransactionException e) {
			log.error("Could not add user to group" + e.getMessage(), e);
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