package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.domain.ajax.AbstractAjaxableDomainObject;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.security.constants.Constants;
import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.afterinvocation.AfterInvocationProvider;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA. User: Biju Joseph Date: Oct 8, 2007 Time: 11:39:45 AM To change this
 * template use File | Settings | File Templates.
 */
public class SiteSecurityAfterInvocationBasicAuthorizationCheckProvider implements
                AfterInvocationProvider {

    private String accessPrivilege = Constants.CSM_ACCESS_PRIVILEGE; // default

    private String processConfigAttribute;

    private LinkedHashMap domainObjectSiteSecurityAuhthorizationCheckProvidersMap;

    private Class processDomainObjectClass = AbstractMutableDomainObject.class;
    
    private gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch authorizationSwitch;

    private Logger log = Logger
                    .getLogger(SiteSecurityAfterInvocationCollectionFilteringProvider.class);

    public Object decide(Authentication authentication, Object object,
                    ConfigAttributeDefinition configAttributeDefinition, Object returnedObject)
                    throws AccessDeniedException {
        if (!authorizationSwitch.isOn()) {
        	return returnedObject;
        }
        
        if (returnedObject == null) {
            if (log.isDebugEnabled()) {
                log.debug("Return object is null, skipping");
            }

            return null;
        }
        if (returnedObject instanceof Collection || returnedObject.getClass().isArray()) {
            log.debug("Return object is collection, skipping");
            return returnedObject;
        }
        log.debug("Checking authorization on object " + returnedObject.getClass().getName());

        if (!getProcessDomainObjectClass().isAssignableFrom(returnedObject.getClass())) {
            if (log.isDebugEnabled()) {
                log.debug("Return object is not applicable for this provider, skipping");
            }

            return returnedObject;
        }
        boolean hasPermission = false;

        //if (returnedObject == null || !(returnedObject instanceof AbstractMutableDomainObject)) {
        if (  (returnedObject == null || !(returnedObject instanceof AbstractMutableDomainObject)) && !(returnedObject instanceof AbstractAjaxableDomainObject)   ) {
            hasPermission = true;
        }

        DomainObjectSiteSecurityAuthorizationCheckProvider auth = (DomainObjectSiteSecurityAuthorizationCheckProvider) domainObjectSiteSecurityAuhthorizationCheckProvidersMap
                        .get(returnedObject.getClass().getName());
        if (auth != null) {
            hasPermission = auth.checkAuthorization(authentication, "ACCESS", returnedObject);
        }
        if (hasPermission) {
            return returnedObject;
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Denying access");
            }

            throw new AccessDeniedException("User does not have permission to view to this Study Site");
        }
    }

    public String getAccessPrivilege() {
        return accessPrivilege;
    }

    public void setAccessPrivilege(String accessPrivilege) {
        this.accessPrivilege = accessPrivilege;
    }

    public Class getProcessDomainObjectClass() {
        return processDomainObjectClass;
    }

    public boolean supports(ConfigAttribute config) {
        return config.getAttribute().equals(getProcessConfigAttribute());
    }

    public boolean supports(Class aClass) {
        return true;
    }

    public String getProcessConfigAttribute() {
        return processConfigAttribute;
    }

    public void setProcessConfigAttribute(String processConfigAttribute) {
        this.processConfigAttribute = processConfigAttribute;
    }

    public LinkedHashMap getDomainObjectSiteSecurityAuhthorizationCheckProvidersMap() {
        return domainObjectSiteSecurityAuhthorizationCheckProvidersMap;
    }

    public void setDomainObjectSiteSecurityAuhthorizationCheckProvidersMap(
                    LinkedHashMap domainObjectSiteSecurityAuhthorizationCheckProvidersMap) {
        this.domainObjectSiteSecurityAuhthorizationCheckProvidersMap = domainObjectSiteSecurityAuhthorizationCheckProvidersMap;
    }

	public void setAuthorizationSwitch(
			gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}
}
