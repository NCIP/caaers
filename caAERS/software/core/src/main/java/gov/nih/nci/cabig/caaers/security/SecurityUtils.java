/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.ctms.suite.authorization.SuiteRole;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Biju Joseph
 *
 */
public final class SecurityUtils {

    public static String ORGANIZATION_PE="HealthcareSite";
	public static String STUDY_PE="Study";

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
		// commented out according to my discussion with Biju regarding CAAERS-4135
		/**Object principal  =  authentication.getPrincipal();
		GrantedAuthority[] grantedAuthorities = null;
		if (principal instanceof User) {
			grantedAuthorities = ((User)principal).getAuthorities();
		} else {
			grantedAuthorities = authentication.getAuthorities();
		}
		
		return grantedAuthorities;
		**/
		return authentication.getAuthorities();
	}

    /**
     * Will return the roles of the logged-in user. 
     * @return
     */
    public static UserGroupType[] getRoles(){
        return getRoles(getAuthentication());
    }

    /**
     * Will return the roles of the logged-in user. 
     * @param auth - Authentication
     * @return
     */
    public static UserGroupType[] getRoles(Authentication auth){

        List<UserGroupType> roles = new ArrayList<UserGroupType>();
        GrantedAuthority[] authorities = getGrantedAuthorities(auth);
        if(authorities != null){
           for(GrantedAuthority ga : authorities){
              for(UserGroupType role : UserGroupType.values()){
                if (ga.getAuthority().equals(role.getSecurityRoleName())) roles.add(role);
              }
           }
        }

        return roles.toArray(new UserGroupType[0]);
    }

    /**
     * 
     * Determines whether or not the logged in user has the needed role
     * @param   role - the role to be checked for the logged in user
     * @return  boolean
     *
     * */
    public static boolean hasAuthorityOf(UserGroupType role) {
        for (GrantedAuthority ga : getGrantedAuthorities()) {
            if (ga.getAuthority().equals(role.getSecurityRoleName())) return true;
        }
        return false;
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
	 * Will check whether, the authentication, has at least one the roleNames
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
    public static boolean hasGlobalScopedRoles(){
    	SuiteRole suiteRole;
    	Authentication authentication = getAuthentication();
        
    	//caaers_super_user is globally scoped.
    	if(checkAuthorization(authentication, UserGroupType.caaers_super_user)){
    		return true;
    	}
        
    	GrantedAuthority[] authorities = getGrantedAuthorities(authentication);
    	for (int i = 0; i < authorities.length; i++) {
    		suiteRole = SuiteRole.getByCsmName(authorities[i].getAuthority());
            if(!suiteRole.isScoped()) return true;
    	}
        
    	return false;
    }

    /**
     * Will return true, if the roles is study scoped.
     * @param roleName
     * @return
     */
    public static boolean isStudyScoped(String roleName){
        SuiteRole suiteRole = SuiteRole.getByCsmName(roleName);
        if(suiteRole != null) return suiteRole.isStudyScoped();
        return false;
    }



    /**
     * Will return true, if the roles is ONLY site scoped.
     *
     * Note : By default all study scoped roles are site scoped.
     * 
     * @param roleName
     * @return
     */
    public static boolean isSiteScoped(String roleName){
        SuiteRole suiteRole = SuiteRole.getByCsmName(roleName);
        if(suiteRole != null) return suiteRole.isSiteScoped() && (!isStudyScoped(roleName));
        return false;
    }


    /**
     * Will split the protection group into 2 parts.
     * Eg: Study.NCCTG N06C6.1 will result in {Study, NCCTG N06C6.1}
     * @param pgName
     * @return
     */
    public static String[] splitProtectionGroup(String pgName){
        int index = StringUtils.indexOf(pgName, '.');
        if(index > 0){
            String part1 = pgName.substring(0, index);
            String part2 = pgName.substring(index+1);

            if(part1.equals(ORGANIZATION_PE) || part1.equals(STUDY_PE)){
                return new String[]{part1, part2};
            }

        }
        
        return new String[0];

    }
}
