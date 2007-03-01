package gov.nih.nci.security.acegi.csm.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.acegi.csm.authorization.ExtendedCSMAuthorizationManager;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;

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
	private ExtendedCSMAuthorizationManager csmAuthorizationManager;
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
		
		ExtendedCSMAuthorizationManager mgr = getCsmAuthorizationManager();
		String[] groupNames = mgr.getGroupNames(userName);
		if(groupNames == null || groupNames.length == 0){
			authorities = new GrantedAuthority[0];
		}else{
			String prefix = getRolePrefix();
			if(prefix == null){
				prefix = "";
			}
			authorities = new GrantedAuthority[groupNames.length];
			for(int i = 0; i < groupNames.length; i++){
				
				authorities[i] = new GrantedAuthorityImpl(prefix + groupNames[i]);
			}
		}
		
		UserDetails details = new User(userName, "ignored", true, true, true, true, authorities);
		return details;
	}

	public AuthenticationManager getCsmAuthenticationManager() {
		return csmAuthenticationManager;
	}

	public void setCsmAuthenticationManager(
			AuthenticationManager csmAuthenticationManager) {
		this.csmAuthenticationManager = csmAuthenticationManager;
	}

	public ExtendedCSMAuthorizationManager getCsmAuthorizationManager() {
		return csmAuthorizationManager;
	}

	public void setCsmAuthorizationManager(
			ExtendedCSMAuthorizationManager csmAuthorizationManager) {
		this.csmAuthorizationManager = csmAuthorizationManager;
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

}
