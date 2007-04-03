/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.cabig.ctms.web.sso.AuthenticationErrorException;
import gov.nih.nci.cabig.ctms.web.sso.GridBasicAuthenticationClient;
import gov.nih.nci.cabig.ctms.web.sso.GridProxyValidationException;
import gov.nih.nci.cabig.ctms.web.sso.GridProxyValidator;
import gov.nih.nci.cabig.ctms.web.sso.Utils;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class GridAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private GridBasicAuthenticationClient authenticationClient;
	private UserDetailsService userDetailsService;
	private GridProxyValidator validator;
	
	protected void doAfterPropertiesSet(){
		Assert.notNull(this.authenticationClient, "A GridBasicAuthenticationClient is required.");
		Assert.notNull(this.userDetailsService, "A UserDetailsService is required.");
		Assert.notNull(this.validator, "A GridProxyValidator is required.");
	}
	
	/* (non-Javadoc)
	 * @see org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(org.acegisecurity.userdetails.UserDetails, org.acegisecurity.providers.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0,
			UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// nothing to do here
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String, org.acegisecurity.providers.UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String userName,
			UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

		String gridProxy = null;
		try {
			gridProxy = getAuthenticationClient().authenticate(userName, (String) token.getCredentials());
		} catch (AuthenticationErrorException ex) {
			throw new BadCredentialsException("Error authenticating: " + ex.getMessage(), ex);
		}
		
		String identity = Utils.getGridIdentity(gridProxy);
		
		boolean valid;
		try {
			valid = getValidator().validate(gridProxy);
		} catch (GridProxyValidationException ex) {
			throw new BadCredentialsException("Couldn't validate credentials: "
					+ ex.getMessage(), ex);
		}
		if (!valid) {
			throw new BadCredentialsException("Credentials are not valid.");
		}
		
		return getUserDetailsService().loadUserByUsername(identity);
	}

	public GridBasicAuthenticationClient getAuthenticationClient() {
		return authenticationClient;
	}

	public void setAuthenticationClient(
			GridBasicAuthenticationClient authenticationClient) {
		this.authenticationClient = authenticationClient;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public GridProxyValidator getValidator() {
		return validator;
	}

	public void setValidator(GridProxyValidator validator) {
		this.validator = validator;
	}

}
