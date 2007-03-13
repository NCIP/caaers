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
import org.acegisecurity.userdetails.UserDetailsService;

public class CSMAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	private AuthenticationManager csmAuthenticationManager;
	private UserDetailsService userDetailsService;
	

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

		return getUserDetailsService().loadUserByUsername(userName);
	}

	public AuthenticationManager getCsmAuthenticationManager() {
		return csmAuthenticationManager;
	}

	public void setCsmAuthenticationManager(
			AuthenticationManager csmAuthenticationManager) {
		this.csmAuthenticationManager = csmAuthenticationManager;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}