/**
 * 
 */
package gov.nih.nci.security.acegi.grid.authentication;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.AbstractAuthenticationToken;
import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class GlobusCredentialAuthenticationToken extends
		AbstractAuthenticationToken {

	private GlobusCredential globusCredential;

	public GlobusCredentialAuthenticationToken(GlobusCredential globusCredential) {
		super(null);
		this.globusCredential = globusCredential;
	}

	public GlobusCredentialAuthenticationToken(
			GlobusCredential globusCredential, GrantedAuthority[] authorities) {
		super(authorities);
		this.globusCredential = globusCredential;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.Authentication#getCredentials()
	 */
	public Object getCredentials() {
		return getGlobusCredential().getCertificateChain();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.acegisecurity.Authentication#getPrincipal()
	 */
	public Object getPrincipal() {
		return getGlobusCredential().getIdentity();
	}

	public GlobusCredential getGlobusCredential() {
		return this.globusCredential;
	}

	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor containing GrantedAuthority[]s instead");
		}

		super.setAuthenticated(false);
	}

}
