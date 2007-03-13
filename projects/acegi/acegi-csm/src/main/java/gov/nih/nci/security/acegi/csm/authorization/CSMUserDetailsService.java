package gov.nih.nci.security.acegi.csm.authorization;

import java.util.Iterator;
import java.util.Set;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

public class CSMUserDetailsService implements UserDetailsService {

	private String rolePrefix = "ROLE_";

	private UserProvisioningManager csmUserProvisioningManager;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		GrantedAuthority[] authorities = null;

		UserProvisioningManager mgr = getCsmUserProvisioningManager();
		Set groups;
		try {
			groups = mgr
					.getGroups(mgr.getUser(userName).getUserId().toString());
		} catch (CSObjectNotFoundException ex) {
			throw new AuthenticationServiceException("Error getting groups: "
					+ ex.getMessage(), ex);
		}
		if (groups == null || groups.size() == 0) {
			authorities = new GrantedAuthority[0];
		} else {
			String prefix = getRolePrefix();
			if (prefix == null) {
				prefix = "";
			}
			authorities = new GrantedAuthority[groups.size()];
			int idx = 0;
			for (Iterator i = groups.iterator(); i.hasNext(); idx++) {
				Group group = (Group) i.next();
				authorities[idx] = new GrantedAuthorityImpl(prefix
						+ group.getGroupName());
			}
		}

		return new User(userName, "ignored", true, true, true, true,
				authorities);

	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public UserProvisioningManager getCsmUserProvisioningManager() {
		return csmUserProvisioningManager;
	}

	public void setCsmUserProvisioningManager(
			UserProvisioningManager userProvisioningManager) {
		this.csmUserProvisioningManager = userProvisioningManager;
	}

}
