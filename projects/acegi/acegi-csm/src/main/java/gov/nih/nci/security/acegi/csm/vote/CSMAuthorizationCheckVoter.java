/**
 * 
 */
package gov.nih.nci.security.acegi.csm.vote;

import gov.nih.nci.security.acegi.csm.authorization.CSMAuthorizationCheck;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 *
 */
public class CSMAuthorizationCheckVoter implements AccessDecisionVoter {
	
	private String processConfigAttribute;
	
	private CSMAuthorizationCheck authorizationCheck;
	
	private String requiredPrivilege;

	public String getRequiredPrivilege() {
		return requiredPrivilege;
	}

	public void setRequiredPrivilege(String requiredPrivilege) {
		this.requiredPrivilege = requiredPrivilege;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute config) {
		return config.getAttribute().equals(getProcessConfigAttribute());
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
	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition configDef) {

		if(getAuthorizationCheck().checkAuthorization(authentication, getRequiredPrivilege(), object)){
			return AccessDecisionVoter.ACCESS_GRANTED;
		}else{
			return AccessDecisionVoter.ACCESS_DENIED;
		}
	}

	public String getProcessConfigAttribute() {
		return processConfigAttribute;
	}

	public void setProcessConfigAttribute(String processConfigAttribute) {
		this.processConfigAttribute = processConfigAttribute;
	}

	public CSMAuthorizationCheck getAuthorizationCheck() {
		return authorizationCheck;
	}

	public void setAuthorizationCheck(CSMAuthorizationCheck authorizationCheck) {
		this.authorizationCheck = authorizationCheck;
	}

}
