package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;

/**
 * 
 * @author Biju Joseph
 *
 */
public class SecurityUtils {
	
	

	/**
	 * This method will find the login name, of the user available in 
	 * SecurityContext.
	 * @return
	 */
	public static String getUserLoginName(){
		return getUserLoginName(SecurityContextHolder.getContext().getAuthentication());
	}
	
	/**
	 * This method will find the login name, of the user available in 
	 * SecurityContext.
	 * @return
	 */
	public static String getUserLoginName(Authentication authentication){
		Object principal  =  authentication.getPrincipal();
		String userName = "";
		if (principal instanceof User) {
			userName = ((User)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		
		return userName;
	}
	
	

	/**
	 * Returns the granted authorities
	 * @param authentication
	 * @return
	 */
	public static GrantedAuthority[] getGrantedAuthorities() {
		return getGrantedAuthorities(SecurityContextHolder.getContext().getAuthentication());
	}
	
	/**
	 * Returns the granted authorities
	 * @param authentication
	 * @return
	 */
	public static GrantedAuthority[] getGrantedAuthorities(Authentication authentication) {
		Object principal  =  authentication.getPrincipal();
		GrantedAuthority[] grantedAuthorities = null;
		if (principal instanceof User) {
			grantedAuthorities = ((User)principal).getAuthorities();
		} else {
			grantedAuthorities = authentication.getAuthorities();
		}
		
		return grantedAuthorities;
	}
	
	/**
	 * Checks whether the logged-in user has the roles supplied in the roles parameter
	 * @param roles
	 * @return
	 */
	public static boolean checkAuthorization(UserGroupType... roles){
		return checkAuthorization(SecurityContextHolder.getContext().getAuthentication(), roles);
	}
	
	/**
	 * Checks whether the logged-in user has the roles supplied in the privilege
	 * @param privilege
	 * @return
	 */
	public static boolean checkAuthorization(String... privilege){
		return checkAuthorization(SecurityContextHolder.getContext().getAuthentication(), privilege);
	}
	
	/**
	 * Checks whether the logged-in user has the roles supplied in the privilege
	 * @param privilege
	 * @return
	 */
	public static boolean checkAuthorization(Authentication authentication, UserGroupType... roles){
		String[] privileges = new String[roles.length];
		for(int i = 0; i < roles.length; i++){
			privileges[i] = roles[i].getSecurityRoleName();
		}
		return checkAuthorization(authentication, privileges );
	}
	
	/**
	 * Checks whether the supplied authentication has the required privilege
	 * @param authentication
	 * @param privilege
	 * @return
	 */
	public static boolean checkAuthorization(Authentication authentication, String privilege){
		return checkAuthorization(authentication, privilege );
	}
	
	/**
	 * Will check whether, the authentication, has all the privileges
	 * @param authentication
	 * @param privileges
	 * @return
	 */
	public static boolean checkAuthorization(Authentication authentication, String... privileges){
		GrantedAuthority[] authorities = getGrantedAuthorities(authentication);
		
	    for (int i = 0; i < authorities.length; i++) {
	    	 for (int j = 0; j < privileges.length; j++) {
	                if (authorities[i].getAuthority().equals(privileges[j].trim())) {
	                    return true;
	                }
	         }
	    }

	    return false;
	}
	
	
}
