package gov.nih.nci.security.acegi.csm.authorization;


import gov.nih.nci.security.authorization.domainobjects.Group;

import java.util.Iterator;
import java.util.List;

import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CSMGroupAuthorizationCheck extends AbstractCSMAuthorizationCheck {

	private String requiredPermission;

	private static final Log logger = LogFactory
			.getLog(CSMGroupAuthorizationCheck.class);
	
	public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
		
		String checkPrivilege = privilege;
		if(checkPrivilege == null){
			checkPrivilege = getRequiredPermission();
		}

		boolean isAuthorized = false;
		try {
			List groups = getCsmAuthorizationManager().getAccessibleGroups(
					objectId, checkPrivilege);
			if (groups == null) {
				logger.debug("######### found no groups for " + objectId); 
			} else {
				logger.debug("######### found " + groups.size()
						+ " groups for " + objectId);
				for (Iterator i = groups.iterator(); i.hasNext();) {
					Group group = (Group) i.next();
					if (isMember(authentication.getName(), group.getGroupName())) {
						isAuthorized = true;
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug(ex);
		}
		return isAuthorized;
	}

	protected boolean isMember(String userId, String groupName) {
		boolean isMember = false;
		ExtendedCSMAuthorizationManager mgr = (ExtendedCSMAuthorizationManager) getCsmAuthorizationManager();
		String[] groupNames = mgr.getGroupNames(userId);

		if (groupNames != null) {
			logger.debug("######### found " + groupNames.length
					+ " groups for user " + userId);
			for (int i = 0; i < groupNames.length; i++) {
				logger.debug("############ Comparing '" + groupNames[i]
						+ "' to '" + groupName + "'");
				if (groupNames[i].equals(groupName)) {
					isMember = true;
					break;
				}
			}
			logger.debug("########## isMember? " + isMember);
		} else {
			logger.debug("######### found no groups for user " + userId);
		}
		return isMember;
	}

	public String getRequiredPermission() {
		return requiredPermission;
	}

	public void setRequiredPermission(String requiredPermission) {
		this.requiredPermission = requiredPermission;
	}

}
