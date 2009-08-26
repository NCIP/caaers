package gov.nih.nci.cabig.caaers.security;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class creates a security context based on the configuration parameters. 
 * Used by SYSTEM tasks (eg: Quartz Jobs, Workflow processing , JMS message handlers), to populate authentication in the Thread context. 
 * @author Biju Joseph
 *
 */
public class RunAsAuthenticationPopulator {
	
	private static final Log log = LogFactory.getLog(RunAsAuthenticationPopulator.class);

	private String username;

	private String password;

	private List<String> authorities;
	
	public void populate() {

        log.debug("populating authentication [userName : " + username + ", password :" + password +", authorities : " + String.valueOf(authorities) + "]");

        List<String> l = getAuthorities();
        GrantedAuthority[] auths = new GrantedAuthority[l.size()];
        int idx = 0;
        for (String auth : l) {
            auths[idx++] = new GrantedAuthorityImpl(auth);
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(getUsername(), getPassword(), auths);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
	
	///OBJECT METHODS
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	
	
}
