package gov.nih.nci.cabig.caaers.security.authorization;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;
import org.acegisecurity.Authentication;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class SiteAuthorizationCheck implements CSMAuthorizationCheck {
    
    protected CaaersSecurityFacade caaersSecurityFacade;

    /**
     * True, if the user can access the object with the specified role. 
     * @param authentication - An Authentication object
     * @param role  - A user role
     * @param o  - The object (an instance of Organization)
     * @return
     */
    public boolean checkAuthorization(Authentication authentication, String role, Object o) {
        if(o instanceof Organization){
            Collection<String> roles = caaersSecurityFacade.getRoles(SecurityUtils.getUserLoginName(authentication), (Organization) o);
            for(String aRole : roles){
                if(StringUtils.equals(role, aRole)) return true;
            }
        }
        return false;
    }

    public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
        if(true) throw new UnsupportedOperationException("Use gov.nih.nci.cabig.caaers.security.authorization.SiteAuthorizationCheck#checkAuthorization(Authentication, String, Object) instead");
        return false;
    }

    public boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege, String[] objectIds) {
        if(true) throw new UnsupportedOperationException("Use gov.nih.nci.cabig.caaers.security.authorization.SiteAuthorizationCheck#checkAuthorization(Authentication, String, Object) instead");
        return false;
    }

    public CaaersSecurityFacade getCaaersSecurityFacade() {
        return caaersSecurityFacade;
    }

    public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
        this.caaersSecurityFacade = caaersSecurityFacade;
    }
}
