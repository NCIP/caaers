package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.Authentication;

/**
 * Holds the original unmodified {@link Authentication} populated by Acegi. See http://jira.semanticbits.com/browse/CAAERS-4098.
 * @author dkrylov
 * @see http://jira.semanticbits.com/browse/CAAERS-4098
 */
public final class OriginalAuthenticationHolder {

	
	private static final ThreadLocal<Authentication> holder = new ThreadLocal<Authentication>();
	
	private OriginalAuthenticationHolder() {
	}
	
	/**
	 * Gets the {@link Authentication}.
	 * @return
	 */
	public static Authentication getAuthentication()  {
		return holder.get();
	}
	
	/**
	 * Sets {@link Authentication}. It will be bound to the current {@link Thread}.
	 * @param authentication
	 */
	public static void setAuthentication(Authentication authentication) {
		holder.set(authentication);
	}
	
	
}
