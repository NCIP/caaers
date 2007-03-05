package gov.nih.nci.security.acegi.csm.authorization;

import org.acegisecurity.Authentication;

public class CSMOwnershipAuthorizationCheck extends
		AbstractCSMAuthorizationCheck {

	public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
		return getCsmUserProvisioningManager().checkOwnership(authentication.getName(), objectId);
	}

}
