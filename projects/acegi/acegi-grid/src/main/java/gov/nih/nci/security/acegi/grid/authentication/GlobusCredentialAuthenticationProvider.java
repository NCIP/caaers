/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authentication;

import gov.nih.nci.security.acegi.grid.Utils;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.LockedException;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class GlobusCredentialAuthenticationProvider implements
		AuthenticationProvider {

	private static final Log logger = LogFactory
			.getLog(GlobusCredentialAuthenticationProvider.class);

	private GridProxyValidator validator;

	private UserDetailsService userDetailsService;

	private boolean hideUsernameNotFoundException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.providers.AuthenticationProvider#authenticate(org.acegisecurity.Authentication)
	 */
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {

		GlobusCredentialAuthenticationToken token = (GlobusCredentialAuthenticationToken) auth;
		
		UserDetails user = null;
		try{
			user = retrieveUser(token);
		}catch(UsernameNotFoundException ex){
			if(isHideUsernameNotFoundException()){
				logger.info("Hiding UsernameNotFoundException for " + token.getName() + ". Throwing BadCredentialsException.");
				throw new BadCredentialsException("Bad credentials");
			}else{
				throw ex;
			}
		}catch(Exception ex){
			logger.error("Unexpected error getting user. Throwing BadCredentialsException.", ex);
			throw new BadCredentialsException("Bad credentials", ex);
		}

		if (!user.isAccountNonLocked()) {
            throw new LockedException("User account is locked.");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("User account is disabled.");
        }

        if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("User accound has expired.");
        }

        additionalAuthenticationChecks(user, token);

        if (!user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("User credentials have expired");
        }
        
        Authentication success = createSuccessAuthentication(user, token);

		return success;
	}
	
	
	protected Authentication createSuccessAuthentication(UserDetails user, GlobusCredentialAuthenticationToken token){
		GlobusCredentialAuthenticationToken auth = new GlobusCredentialAuthenticationToken(token.getGlobusCredential(), user.getAuthorities());
		auth.setDetails(user);
		return auth;
	}

	protected void additionalAuthenticationChecks(UserDetails user,
			GlobusCredentialAuthenticationToken token) {
		boolean valid;
		try {
			valid = getValidator().validate(Utils.toString(token.getGlobusCredential()));
		} catch (GridProxyValidationException ex) {
			throw new BadCredentialsException("Couldn't validate credentials: "
					+ ex.getMessage(), ex);
		}catch(Exception ex){
			logger.error("Unexpected error validating proxy. Throwing BadCredentialsException.", ex);
			throw new BadCredentialsException("Bad credentials.", ex);
		}
		if (!valid) {
			throw new BadCredentialsException("Credentials are not valid.");
		}
	}

	protected UserDetails retrieveUser(GlobusCredentialAuthenticationToken token)
			throws AuthenticationException {
		UserDetails user = getUserDetailsService().loadUserByUsername(
				token.getName());
		if (user == null) {
			throw new UsernameNotFoundException("User " + token.getName()
					+ " not found.");
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.providers.AuthenticationProvider#supports(java.lang.Class)
	 */
	public boolean supports(Class klass) {
		return GlobusCredentialAuthenticationToken.class
				.isAssignableFrom(klass);
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

	public boolean isHideUsernameNotFoundException() {
		return hideUsernameNotFoundException;
	}

	public void setHideUsernameNotFoundException(
			boolean hideUsernameNotFoundException) {
		this.hideUsernameNotFoundException = hideUsernameNotFoundException;
	}

}
