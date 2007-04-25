package gov.nih.nci.security.acegi.csm.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.InsufficientAuthenticationException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CSMAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {
	
	private static Log logger = LogFactory.getLog(CSMAuthenticationProvider.class);
	
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
		logger.debug("Authenticating " + userName + "...");
		try{
			getCsmAuthenticationManager().authenticate(userName, (String)token.getCredentials());
		}catch(CSInsufficientAttributesException ex){
			logger.debug("authentication failed: insufficient attributes", ex);
			throw new InsufficientAuthenticationException(ex.getMessage(), ex);
		}catch(CSLoginException ex){
			logger.debug("authentication failed: login exception", ex);
			throw new BadCredentialsException(ex.getMessage(), ex);
		}catch(Exception ex){
			logger.debug("authentication failed: unknown", ex);
			throw new AuthenticationServiceException("Error authenticating: " + ex.getMessage(), ex);
		}
		logger.debug("authentication succeeded");

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