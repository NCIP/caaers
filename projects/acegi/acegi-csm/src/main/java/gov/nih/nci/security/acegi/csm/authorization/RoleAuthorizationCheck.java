package gov.nih.nci.security.acegi.csm.authorization;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class RoleAuthorizationCheck implements CSMAuthorizationCheck {

	private String roleName;
	
	public boolean checkAuthorization(Authentication authentication,
			String privilege, Object object) {
		return checkAuthorizationForObjectId(authentication, null, null);
	}

	public boolean checkAuthorizationForObjectId(Authentication authentication,
			String privilege, String objectId) {
		return checkAuthorizationForObjectIds(authentication, null, null);
	}

	public boolean checkAuthorizationForObjectIds(
			Authentication authentication, String privilege, String[] objectId) {
		boolean isAuthenticated = false;
		
		GrantedAuthority[] authorities = authentication.getAuthorities();
		for(int i = 0; i < authorities.length && !isAuthenticated; i++){
			if(authorities[i].getAuthority().equals(getRoleName())){
				isAuthenticated = true;
			}
		}
		
		return isAuthenticated;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
