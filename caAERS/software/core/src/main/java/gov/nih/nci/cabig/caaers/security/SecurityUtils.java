package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;

/**
 * 
 * @author Biju Joseph
 *
 */
public final class SecurityUtils {
	
	private SecurityUtils() {
	}
	
	/**
	 * Returns the original unfabricated {@link Authentication} created by Acegi.
	 * NOTE: Hopefully, this solution -- we need to keep two different {@link Authentication} objects and swap them out when need be -- is temporary, and later
	 * we will figure out a more elegant way. But for now it is what it is. 
	 * @return
	 */
	public static Authentication getOriginalAuthentication() {
		return OriginalAuthenticationHolder.getAuthentication();
	}

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
	 * @param authentication - A valid authentication object
     * @param roles - A list of UserGroupType
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
	 * Will check whether, the authentication, has all the roleNames
	 * @param authentication
	 * @param roleNames
	 * @return
	 */
	public static boolean checkAuthorization(Authentication authentication, String... roleNames){
		GrantedAuthority[] authorities = getGrantedAuthorities(authentication);
		
	    for (int i = 0; i < authorities.length; i++) {
	    	 for (int j = 0; j < roleNames.length; j++) {
	                if (authorities[i].getAuthority().equals(roleNames[j].trim())) {
	                    return true;
	                }
	         }
	    }

	    return false;
	}


    /**
     * Get the context related authentication object
     *
     * */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    /**
     * This method will return false if the logged in user has at least one globally scoped role. 
     * @return
     */
    public static boolean isScoped(){
    	SuiteRole suiteRole;
    	Authentication authentication = getAuthentication(); 
    	GrantedAuthority[] authorities = getGrantedAuthorities(authentication);
    	for (int i = 0; i < authorities.length; i++) {
    		suiteRole = SuiteRole.getByCsmName(authorities[i].getAuthority());
    		if(!suiteRole.isScoped()){
    			return false; 
    		}
    	}
    	return true;
    }
    
    public static boolean isScoped(Authentication authentication){
    	SuiteRole suiteRole;

    	GrantedAuthority[] authorities = getGrantedAuthorities(authentication);
    	for (int i = 0; i < authorities.length; i++) {
    		suiteRole = SuiteRole.getByCsmName(authorities[i].getAuthority());
    		if(!suiteRole.isScoped()){
    			return false; 
    		}
    	}
    	return true;
    }
    /**
     * checks if  a role is scoped or not 
     * @param roleName
     * @return
     */
    public static boolean isScoped(String roleName) {
    	SuiteRole suiteRole = SuiteRole.getByCsmName(roleName);
    	return suiteRole.isScoped();
    }
}
