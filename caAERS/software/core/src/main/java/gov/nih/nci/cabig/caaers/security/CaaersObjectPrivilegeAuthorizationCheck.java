
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.AbstractObjectPrivilegeCSMAuthorizationCheck;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 
 * @author Monish Dombla
 *
 */
public class CaaersObjectPrivilegeAuthorizationCheck  extends AbstractObjectPrivilegeCSMAuthorizationCheck {
	
	private CSMAuthorizationCheck csmAuthorizationCheck;
	private RolePrivilegeDao rolePrivilegeDao;
	private Logger logger = Logger.getLogger(CaaersObjectPrivilegeAuthorizationCheck.class);
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck#checkAuthorizationForObjectId(org.acegisecurity.Authentication, java.lang.String, java.lang.String)
	 */
	public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
		
		//Fetch all the roles of the logged in user.
		//Granted Authorities is populated when user is authenticated. The String is prefixed with a "ROLE_".
		//Hence we have to split.
		try{
			GrantedAuthority[] authorities = authentication.getAuthorities();
			List<String> userRoles;
			List<String> privilegedRoles;
			if (authorities != null) {
				userRoles = new ArrayList<String>();
                for (int i = 0; i < authorities.length; i++) {
/*
                    String[] authoritySplit = authorities[i].getAuthority().split("ROLE_");
                    if (authoritySplit.length == 2) {
                        userRoles.add(authoritySplit[1]);
                    }
*/
                    userRoles.add(authorities[i].getAuthority());
                }
                //Fetch all the roles which have the given privilege on the given objectId
				privilegedRoles = rolePrivilegeDao.getRoles(objectId, privilege);
				if(userRoles != null && privilegedRoles != null){
					for(String userRole : userRoles){
						if(privilegedRoles.contains(userRole)){
							return true;
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public RolePrivilegeDao getRolePrivilegeDao() {
		return rolePrivilegeDao;
	}

	public void setRolePrivilegeDao(RolePrivilegeDao rolePrivilegeDao) {
		this.rolePrivilegeDao = rolePrivilegeDao;
	}
	
	public CSMAuthorizationCheck getCsmAuthorizationCheck() {
		return csmAuthorizationCheck;
	}

	public void setCsmAuthorizationCheck(CSMAuthorizationCheck csmAuthorizationCheck) {
		this.csmAuthorizationCheck = csmAuthorizationCheck;
	}
}