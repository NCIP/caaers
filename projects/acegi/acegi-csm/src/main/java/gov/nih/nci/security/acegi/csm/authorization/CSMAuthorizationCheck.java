package gov.nih.nci.security.acegi.csm.authorization;

import org.acegisecurity.Authentication;

public interface CSMAuthorizationCheck {
	
	boolean checkAuthorization(Authentication authentication, String privilege, Object object);
	
	boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId);
	
	boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege, String[] objectId);

}
