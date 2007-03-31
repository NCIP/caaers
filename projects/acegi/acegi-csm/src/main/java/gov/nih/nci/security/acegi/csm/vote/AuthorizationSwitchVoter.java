/**
 * 
 */
package gov.nih.nci.security.acegi.csm.vote;

import gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * If the configured AuthorizationSwitch is on, the vote method
 * returns ACCESS_ABSTAIN, otherwise it returns ACCESS_GRANTED.
 * 
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class AuthorizationSwitchVoter implements AccessDecisionVoter {
	
	private static final Log logger = LogFactory.getLog(AuthorizationSwitchVoter.class);
	
	private AuthorizationSwitch authorizationSwitch = new AuthorizationSwitch();
	
	private boolean requiresAuthentication = true;

	public boolean isRequiresAuthentication() {
		return requiresAuthentication;
	}

	public void setRequiresAuthentication(boolean requiresAuthentication) {
		this.requiresAuthentication = requiresAuthentication;
	}

	public AuthorizationSwitch getAuthorizationSwitch() {
		return authorizationSwitch;
	}

	public void setAuthorizationSwitch(AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(java.lang.Class)
	 */
	public boolean supports(Class arg0) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#vote(org.acegisecurity.Authentication, java.lang.Object, org.acegisecurity.ConfigAttributeDefinition)
	 */
	public int vote(Authentication arg0, Object arg1,
			ConfigAttributeDefinition arg2) {
		int retVal = AccessDecisionVoter.ACCESS_ABSTAIN;
		if(!getAuthorizationSwitch().isOn()){
			
			logger.warn("###### AuthorizationSwitch is OFF #####");
			if(!isRequiresAuthentication() ||
					isRequiresAuthentication() && 
					SecurityContextHolder.getContext().getAuthentication() != null){
				retVal = AccessDecisionVoter.ACCESS_GRANTED;
			}
			
		}
		return retVal;
	}

}
