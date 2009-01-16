package gov.nih.nci.cabig.caaers.accesscontrol;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public abstract class BaseSecurityFilterer {
	
	public boolean isSuperUser(Authentication authentication) {
		boolean superUser = false;
		// get user
		User user = (User)authentication.getPrincipal();
		
		//no filtering if super user
        GrantedAuthority[] grantedAuthorities = user.getAuthorities();
        for (int i=0; i<grantedAuthorities.length; i++) {
        	GrantedAuthority grantedAuthority = (GrantedAuthority)grantedAuthorities[i];
        	if ( grantedAuthority.getAuthority().equals("ROLE_caaers_super_user")) {
        			return true;
        		
        	}
        }		
		
		return superUser;
	}
}
