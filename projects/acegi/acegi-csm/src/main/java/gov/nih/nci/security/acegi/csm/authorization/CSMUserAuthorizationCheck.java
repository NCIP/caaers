package gov.nih.nci.security.acegi.csm.authorization;

import org.acegisecurity.Authentication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CSMUserAuthorizationCheck extends AbstractCSMAuthorizationCheck {
	
	private String requiredPermission;
	
	private static final Log logger = LogFactory.getLog(CSMUserAuthorizationCheck.class);
	
	public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
		boolean isAuthorized = false;
		try{
			if(getCsmUserProvisioningManager().checkPermission(authentication.getName(), objectId, privilege)){
				isAuthorized = true;
			}
		}catch(Exception ex){
			logger.debug(ex);
		}
		return isAuthorized;
	}

	public String getRequiredPermission() {
		return requiredPermission;
	}

	public void setRequiredPermission(String requiredPermission) {
		this.requiredPermission = requiredPermission;
	}

}
