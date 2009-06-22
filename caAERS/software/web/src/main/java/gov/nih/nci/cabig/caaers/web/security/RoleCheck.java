/**
 * 
 */
package gov.nih.nci.cabig.caaers.web.security;

import gov.nih.nci.security.acegi.csm.authorization.AbstractCSMAuthorizationCheck;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

/**
 * Parses the given privilege as comma-separated list of role names. Checks if Authentication has at
 * least one GrantedAuthority with one of the given role names. The objectId is ignored.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class RoleCheck extends AbstractCSMAuthorizationCheck {

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck#checkAuthorizationForObjectId(org.acegisecurity.Authentication,
     *      java.lang.String, java.lang.String)
     */
    public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege,
                    String objectId) {
        String[] roles = privilege.split(",");
        GrantedAuthority[] authorities = authentication.getAuthorities();
        for (int i = 0; i < authorities.length; i++) {
            for (int j = 0; j < roles.length; j++) {
                if (authorities[i].getAuthority().equals(roles[j].trim())) {
                    return true;
                }
            }
        }

        return false;
    }

}
