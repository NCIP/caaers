package gov.nih.nci.cabig.caaers.accesscontrol;

import gov.nih.nci.cabig.caaers.utils.ArrayFilterer;
import gov.nih.nci.cabig.caaers.utils.CollectionFilterer;
import gov.nih.nci.cabig.caaers.utils.Filterer;

import java.util.Collection;
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
public class SiteSecurityAfterInvocationCollectionFilteringProvider implements AfterInvocationProvider {

    private String processConfigAttribute;
    private gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch authorizationSwitch;
    private LinkedHashMap domainObjectSiteSecurityAuhthorizationCheckProvidersMap;
	private Logger log = Logger.getLogger(SiteSecurityAfterInvocationCollectionFilteringProvider.class);
    

    public Object decide(Authentication authentication, Object object,
                    ConfigAttributeDefinition configAttributeDefinition, Object resultList)
                    throws AccessDeniedException {
    	
     	
        if (resultList == null) {
            if (log.isDebugEnabled()) {
                log.debug("Return object is null, skipping");
            }

            return null;
        }

        Filterer filterer = null;
        Object searchedObject = null;

        if (resultList instanceof Collection) {
            Collection collection = (Collection) resultList;
            filterer = new CollectionFilterer(collection);            
        } else if (resultList.getClass().isArray()) {
            Object[] array = (Object[]) resultList;
            filterer = new ArrayFilterer(array);
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Return object is not a collection, skipping");
            }
            return resultList;
        }
        
        if (!authorizationSwitch.isOn() || !filterer.iterator().hasNext()) {
        	return filterer.getFilteredObject();
        }
        searchedObject = filterer.iterator().next();
        
        // load objects from domainObjectSiteSecurityAuhthorizationCheckProvidersMap , applicationContext-core-security.xml
        DomainObjectSecurityFilterer auth = (DomainObjectSecurityFilterer) domainObjectSiteSecurityAuhthorizationCheckProvidersMap
        .get(searchedObject.getClass().getName());
        
        //no filtering is required if a filterer is not configured.
        if(auth == null) return filterer.getFilteredObject();
        
        return auth.filter(authentication, "ACCESS", filterer);
        //return filteredResults.getFilteredObject();
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
    @Required
	public void setAuthorizationSwitch(
			gov.nih.nci.security.acegi.csm.authorization.AuthorizationSwitch authorizationSwitch) {
		this.authorizationSwitch = authorizationSwitch;
	}

}
