package gov.nih.nci.security.acegi.csm.vote;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import org.acegisecurity.Authentication;
import org.acegisecurity.vote.AccessDecisionVoter;

public class CSMBasicAccessDecisionVoter extends AbstractCSMAccessDecisionVoter {

	private CSMAuthorizationCheck authorizationCheck;
	
	public CSMAuthorizationCheck getAuthorizationCheck() {
		return authorizationCheck;
	}

	public void setAuthorizationCheck(CSMAuthorizationCheck authorizationCheck) {
		this.authorizationCheck = authorizationCheck;
	}

	@Override
	protected int checkAuthorization(Authentication authentication, Object object) {

		if(getAuthorizationCheck().checkAuthorization(authentication, null, object)){
			return AccessDecisionVoter.ACCESS_GRANTED;
		}else{
			return AccessDecisionVoter.ACCESS_DENIED;
		}

	}

}
