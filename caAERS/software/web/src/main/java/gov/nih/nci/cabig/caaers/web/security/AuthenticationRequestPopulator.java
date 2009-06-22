/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import javax.servlet.http.HttpServletRequest;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public interface AuthenticationRequestPopulator {
    Authentication populate(HttpServletRequest request) throws AuthenticationException;
}
