package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.web.security.WebTabResolver;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;
import org.acegisecurity.Authentication;


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
        gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser p = new gov.nih.nci.cabig.caaers.utils.ObjectPrivilegeParser(objectPrivilege);
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