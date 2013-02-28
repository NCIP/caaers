/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.security.CurrentEntityHolder;
import gov.nih.nci.cabig.caaers.web.task.TaskGroup;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import gov.nih.nci.cabig.ctms.web.chrome.Task;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * This class caches the result of authorization calls made in the current session. 
 * This is to avoid call to CSM, repeatedly about the same domainObject,privilege combination.
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class AuthorizationDecisionCache implements Serializable{

    private int maxElementsToCache = 600;
	    
    private LinkedHashMap<String, HashMap<Object,AuthorizationDecisionCacheEntry >> decisionCache;
    
    public AuthorizationDecisionCache(){
        //Initalize the LRU cache.
        decisionCache = new LinkedHashMap<String, HashMap<Object, AuthorizationDecisionCacheEntry>>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, HashMap<Object, AuthorizationDecisionCacheEntry>> eldest) {
                return size() > maxElementsToCache;
            }
        };
    }
	

	/**
	 * Will add the decision on to the cache.
	 * 
	 * @param domainObject
	 * @param privilege
	 * @param allowed
	 */
	public void addDecision(String scopeDiscriminator, Object domainObject,String privilege, Boolean allowed) {
		addDecisionInternal(scopeDiscriminator,domainObject, privilege, allowed);
	}

	private void addDecisionInternal(String key,Object domainObject, String privilege, Boolean allowed) {
		if (domainObject == null)
			return;

        HashMap<Object,AuthorizationDecisionCacheEntry > decisionMap = decisionCache.get(key);
        if(decisionMap == null){
            decisionMap = new HashMap<Object,AuthorizationDecisionCacheEntry >();
            decisionCache.put(key, decisionMap);
        }

        Object decisionMapKey = getEnityContextCacheKey(domainObject);

        AuthorizationDecisionCacheEntry entry = decisionMap.get(decisionMapKey);
        if (entry == null) {
			entry = new AuthorizationDecisionCacheEntry(domainObject);
			decisionMap.put(decisionMapKey, entry);
		}
		entry.addDecision(privilege, allowed);
	}

	/**
	 * Will return the authorization decision from the cache.
	 * 
	 * @param domainObject
	 * @param privilege
	 * @return
	 */
	public Boolean isAuthorized(String scopeDiscriminator, Object domainObject,String privilege) {
		return isAuthorizedInternal(scopeDiscriminator,domainObject,privilege);
	}

    private Boolean isAuthorizedInternal(Object key, Object domainObject, String privilege){

        if (domainObject == null)  return null;

        HashMap<Object,AuthorizationDecisionCacheEntry > decisionMap = decisionCache.get(key);
        if(decisionMap == null) return null;

        Object decisionMapKey = getEnityContextCacheKey(domainObject);
        AuthorizationDecisionCacheEntry entry = decisionMap.get(decisionMapKey);
        if(entry == null) return null;

		return entry.isAuthorized(privilege);
    }
	
	public void clear(){
		decisionCache.clear();
	}

    public void clear(String scopeDiscriminator){
        decisionCache.remove(scopeDiscriminator);
    }

    /**
     * Will return the cache key to use.
     * http://jira.semanticbits.com/browse/CAAERS-4098
     * @return
     */
    public Object getEnityContextCacheKey(Object o){
       if(o instanceof DomainObject || o instanceof Tab) return CurrentEntityHolder.getEntityCacheKeyDiscriminator(o);
       if(o instanceof Task) return Task.class.getName() + ((Task) o) .getUrl();
       if(o instanceof TaskGroup) return TaskGroup.class.getName() + ((TaskGroup) o).getDisplayName();
       return o;
    }
	
	@SuppressWarnings("serial")
	public class AuthorizationDecisionCacheEntry implements Serializable{
		private Object domainObject;
		private HashMap<String, Boolean> privilageHash;
		
		public AuthorizationDecisionCacheEntry(Object domainObject) {
			this.domainObject = domainObject;
			privilageHash = new HashMap<String, Boolean>();
		}
		public Object getDomainObject() {
			return domainObject;
		}
		
		public void setDomainObject(Object domainObject) {
			this.domainObject = domainObject;
		}
		
		
		public Boolean isAuthorized(String privilege){
			return privilageHash.get(privilege);
		}
		
		public void addDecision(String privilege, Boolean allowed){
			String previlegeKey = String.valueOf(privilege);
			privilageHash.put(previlegeKey, allowed);
		}
		
	}

    public int getMaxElementsToCache() {
        return maxElementsToCache;
    }

    public void setMaxElementsToCache(int maxElementsToCache) {
        this.maxElementsToCache = maxElementsToCache;
    }
}
