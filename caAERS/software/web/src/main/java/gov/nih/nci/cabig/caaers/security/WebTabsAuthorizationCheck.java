package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.dao.security.RolePrivilegeDao;
import gov.nih.nci.cabig.caaers.web.security.WebTabResolver;
import gov.nih.nci.cabig.caaers.web.utils.ObjectPrivilegeParser;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.AbstractObjectPrivilegeCSMAuthorizationCheck;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;
import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Ion C. Olaru
 * 
 */
public class WebTabsAuthorizationCheck implements CSMAuthorizationCheck {

    protected CaaersSecurityFacade caaersSecurityFacade;
    protected WebTabResolver webTabResolver;

    /*
    * @param object - Task or Section
    * 
    * */
    public boolean checkAuthorization(Authentication authentication, String privilege, Object object) {
        String objectPrivilege = webTabResolver.resolve(object);
        if (objectPrivilege == null) return false;
        ObjectPrivilegeParser p = new ObjectPrivilegeParser(objectPrivilege);
        return caaersSecurityFacade.checkAuthorization(authentication, p.getDomainObjectPrivileges()[0][0], p.getDomainObjectPrivileges()[0][1]);
    }

    public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
        return false;
    }

    public boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege, String[] objectId) {
        return false;  
    }

    public void setWebTabResolver(WebTabResolver webTabResolver) {
        this.webTabResolver = webTabResolver;
    }

    public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
        this.caaersSecurityFacade = caaersSecurityFacade;
    }
}