/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import org.acegisecurity.Authentication;

/**
 * Delegates to the supplied CSMAuthorizationCheck.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class DelegatingObjectPrivilegeCSMAuthorizationCheck extends
		AbstractObjectPrivilegeCSMAuthorizationCheck {
	
	private CSMAuthorizationCheck csmAuthorizationCheck;
	
	public CSMAuthorizationCheck getCsmAuthorizationCheck() {
		return csmAuthorizationCheck;
	}

	public void setCsmAuthorizationCheck(CSMAuthorizationCheck csmAuthorizationCheck) {
		this.csmAuthorizationCheck = csmAuthorizationCheck;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck#checkAuthorizationForObjectId(org.acegisecurity.Authentication, java.lang.String, java.lang.String)
	 */
	public boolean checkAuthorizationForObjectId(Authentication authentication,
			String privilege, String objectId) {
		return getCsmAuthorizationCheck().checkAuthorizationForObjectId(authentication, privilege, objectId);
	}

}
