package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.domain.ajax.AbstractAjaxableDomainObject;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.afterinvocation.AfterInvocationProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * Will filter collection of caAERS domain objects based on Organization permissions.
 * 
 * Date: Sep 20, 2007 Time: 10:49:51 AM
 */
public class SiteSecurityAfterInvocationCollectionFilteringProvider implements
                AfterInvocationProvider {

    private String processConfigAttribute;

    private LinkedHashMap domainObjectSiteSecurityAuhthorizationCheckProvidersMap;

    private Logger log = Logger
                    .getLogger(SiteSecurityAfterInvocationCollectionFilteringProvider.class);

    public Object decide(Authentication authentication, Object object,
                    ConfigAttributeDefinition configAttributeDefinition, Object returnedObject)
                    throws AccessDeniedException {

        if (returnedObject == null) {
            if (log.isDebugEnabled()) {
                log.debug("Return object is null, skipping");
            }

            return null;
        }

        Filterer filterer = null;

        if (returnedObject instanceof Collection) {
            Collection collection = (Collection) returnedObject;
            filterer = new CollectionFilterer(collection);
        } else if (returnedObject.getClass().isArray()) {
            Object[] array = (Object[]) returnedObject;
            filterer = new ArrayFilterer(array);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Return object is not a collection, skipping");
            }
            return returnedObject;
        }
        // Locate unauthorised Collection elements
        Iterator collectionIter = filterer.iterator();

        log.debug("### Intercepting collection for Site Security check");

        while (collectionIter.hasNext()) {
            Object domainObject = collectionIter.next();

            boolean hasPermission = false;

            if (  (domainObject == null || !(domainObject instanceof AbstractMutableDomainObject)) && !(domainObject instanceof AbstractAjaxableDomainObject)   ) {
                hasPermission = true;
            }
            

            DomainObjectSiteSecurityAuthorizationCheckProvider auth = (DomainObjectSiteSecurityAuthorizationCheckProvider) domainObjectSiteSecurityAuhthorizationCheckProvidersMap
                            .get(domainObject.getClass().getName());
            if (auth != null) {
                hasPermission = auth.checkAuthorization(authentication, "ACCESS", domainObject);
            }
            if (!hasPermission) {
                filterer.remove(domainObject);

                if (log.isDebugEnabled()) {
                    log.debug("### Principal is NOT authorised for element: " + domainObject);
                }
            }
        }

        return filterer.getFilteredObject();
    }

    @Required
    public boolean supports(ConfigAttribute config) {
        return config.getAttribute().equals(processConfigAttribute);
    }

    public boolean supports(Class aClass) {
        return true;
    }

    @Required
    public void setProcessConfigAttribute(String processConfigAttribute) {
        this.processConfigAttribute = processConfigAttribute;
    }

    @Required
    public void setDomainObjectSiteSecurityAuhthorizationCheckProvidersMap(
                    LinkedHashMap domainObjectSiteSecurityAuhthorizationCheckProvidersMap) {
        this.domainObjectSiteSecurityAuhthorizationCheckProvidersMap = domainObjectSiteSecurityAuhthorizationCheckProvidersMap;
    }
}
