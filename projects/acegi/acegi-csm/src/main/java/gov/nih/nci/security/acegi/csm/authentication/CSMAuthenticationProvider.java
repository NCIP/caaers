package gov.nih.nci.security.acegi.csm.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;

import java.util.Iterator;
import java.util.Set;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.InsufficientAuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.User;
import org.acegisecurity.userdetails.UserDetails;

public class CSMAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	private AuthenticationManager csmAuthenticationManager;
	private UserProvisioningManager csmUserProvisioningManager;
	private String rolePrefix = "ROLE_";

	@Override
	protected void additionalAuthenticationChecks(UserDetails userName,
			UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		//Nothing to do here since authentication happens in retrieveUser
	}

	@Override
	protected UserDetails retrieveUser(String userName,
			UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

		try{
			getCsmAuthenticationManager().authenticate(userName, (String)token.getCredentials());
		}catch(CSInsufficientAttributesException ex){
			throw new InsufficientAuthenticationException(ex.getMessage(), ex);
		}catch(CSLoginException ex){
			throw new BadCredentialsException(ex.getMessage(), ex);
		}catch(Exception ex){
			throw new AuthenticationServiceException("Error authenticating: " + ex.getMessage(), ex);
		}

		GrantedAuthority[] authorities = null;
		
		UserProvisioningManager mgr = getCsmUserProvisioningManager();
		Set groups;
        try {
        	groups = mgr.getGroups(mgr.getUser(userName).getUserId().toString());
        } catch (CSObjectNotFoundException ex) {
            throw new AuthenticationServiceException("Error getting groups: " + ex.getMessage(), ex);
        }
		if(groups == null || groups.size() == 0){
			authorities = new GrantedAuthority[0];
		}else{
			String prefix = getRolePrefix();
			if(prefix == null){
				prefix = "";
			}
			authorities = new GrantedAuthority[groups.size()];
            int idx = 0;
			for(Iterator i = groups.iterator(); i.hasNext(); idx++){
				Group group = (Group)i.next();
				authorities[idx] = new GrantedAuthorityImpl(prefix + group.getGroupName());
			}
		}
		
		return new User(userName, "ignored", true, true, true, true, authorities);
	}

	public AuthenticationManager getCsmAuthenticationManager() {
		return csmAuthenticationManager;
	}

	public void setCsmAuthenticationManager(
			AuthenticationManager csmAuthenticationManager) {
		this.csmAuthenticationManager = csmAuthenticationManager;
	}

	public UserProvisioningManager getCsmUserProvisioningManager() {
		return csmUserProvisioningManager;
	}

	public void setCsmUserProvisioningManager(
			UserProvisioningManager csmUserProvisioningManager) {
		this.csmUserProvisioningManager = csmUserProvisioningManager;
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

}
