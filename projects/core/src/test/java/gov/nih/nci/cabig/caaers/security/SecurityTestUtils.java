/**
 * 
 */
package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class SecurityTestUtils {
	public static void switchUser(String userName, String... roles) {
		GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
		for (int i = 0; i < roles.length; i++) {
			authorities[i] = new GrantedAuthorityImpl(roles[i]);
		}
		Authentication auth = new TestingAuthenticationToken(userName,
				"ignored", authorities);
		auth.setAuthenticated(true);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
