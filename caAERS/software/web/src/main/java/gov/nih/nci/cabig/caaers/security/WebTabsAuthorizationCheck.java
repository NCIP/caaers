package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.web.security.ObjectPrivilegeGenerator;
import gov.nih.nci.cabig.ctms.acegi.csm.authorization.CSMAuthorizationCheck;
import org.acegisecurity.Authentication;


/**
 * @author Ion C. Olaru
 * @author Biju Joseph (refactored)
 * 
 */
public class WebTabsAuthorizationCheck implements CSMAuthorizationCheck {

    protected CaaersSecurityFacade caaersSecurityFacade;
    protected ObjectPrivilegeGenerator objectPrivilegeGenerator;

    /*
    * @param object - Task or Section
    * 
    * */
    public boolean checkAuthorization(Authentication authentication, String privilege, Object object) {
        String objectPrivilege = objectPrivilegeGenerator.resolve(object);
        if(objectPrivilege == null) return false;
        return caaersSecurityFacade.checkAuthorization(authentication, objectPrivilege);
    }

    public boolean checkAuthorizationForObjectId(Authentication authentication, String privilege, String objectId) {
        if(objectId == null) return false;
        return caaersSecurityFacade.checkAuthorization(authentication, objectId, privilege);
    }

    public boolean checkAuthorizationForObjectIds(Authentication authentication, String privilege, String[] objectIds) {
       for(String objectId : objectIds){
           if(checkAuthorizationForObjectId(authentication, privilege, objectId)) return true;
       }

       return false;
    }

    public void setObjectPrivilegeGenerator(ObjectPrivilegeGenerator objectPrivilegeGenerator) {
        this.objectPrivilegeGenerator = objectPrivilegeGenerator;
    }

    public void setCaaersSecurityFacade(CaaersSecurityFacade caaersSecurityFacade) {
        this.caaersSecurityFacade = caaersSecurityFacade;
    }
}