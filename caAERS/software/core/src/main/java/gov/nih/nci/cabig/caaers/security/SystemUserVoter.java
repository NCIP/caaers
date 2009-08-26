package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.springframework.beans.factory.annotation.Required;

/**
 * This voter, will grant SYSTEM user.
 * @author Biju Joseph
 *
 */
public class SystemUserVoter implements AccessDecisionVoter {
	
	private String username;
	private String password;

	@SuppressWarnings("unchecked")
	public boolean supports(Class klass) {
		return true;
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public int vote(Authentication authentication, Object securedObject,ConfigAttributeDefinition config) {
		
		String usr = (username == null) ? "SYSTEM" : username;
		String pwd = (password == null) ? "ignoreme" : password;
		
		//default is log-in is system (SYSTEM/ignoreme), then allow. 
		if(authentication.getPrincipal().equals(usr) && authentication.getCredentials().equals(pwd)){
			return ACCESS_GRANTED;
		}
		return ACCESS_ABSTAIN;
	}
	
	@Required
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Required
	public void setPassword(String password) {
		this.password = password;
	}

}
