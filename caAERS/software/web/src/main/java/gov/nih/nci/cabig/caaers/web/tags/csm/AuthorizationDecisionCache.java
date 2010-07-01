package gov.nih.nci.cabig.caaers.web.tags.csm;

import gov.nih.nci.cabig.caaers.security.CurrentEntityHolder;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
/**
 * This class caches the result of authorization calls made in the current session. 
 * This is to avoid call to CSM, repeatedly about the same domainObject,privilege combination.
 * @author Biju Joseph
 *
 */
@SuppressWarnings("serial")
public class AuthorizationDecisionCache implements Serializable{

    private static final String defaultKey = "0";
	    
    private Ehcache decisionCache;
	

    /**
     * Will add the decision on to the cache. 
     * @param domainObject
     * @param privilege
     * @param allowed
     */
	public void addDecision(Object domainObject, String privilege, Boolean allowed){
        addDecision(getCacheKeyDiscriminator(), domainObject, privilege, allowed);
	}

    public void addDecision(Object key, Object domainObject, String privilege, Boolean allowed){
        if(domainObject == null) return;
		HashMap<Object, AuthorizationDecisionCacheEntry> decisionMap = (HashMap<Object, AuthorizationDecisionCacheEntry>) (decisionCache
				.get(key) != null ? decisionCache.get(key).getObjectValue()
				: null);
        if(decisionMap == null){
            decisionMap = new HashMap<Object, AuthorizationDecisionCacheEntry>();
            Element element = new Element(key, decisionMap);
            decisionCache.put(element);
        }
        
        AuthorizationDecisionCacheEntry entry = decisionMap.get(domainObject);
        if(entry == null){
            entry = new AuthorizationDecisionCacheEntry(domainObject);
            decisionMap.put(domainObject, entry);
        }
        entry.addDecision(privilege, allowed);
    }

    /**
     * Will return the authorization decision from the cache.
     * @param domainObject
     * @param privilege
     * @return
     */
	public Boolean isAuthorized(Object domainObject, String privilege){
		return isAuthorized(getCacheKeyDiscriminator(), domainObject, privilege);
	}

    public Boolean isAuthorized(Object key, Object domainObject, String privilege){


		HashMap<Object, AuthorizationDecisionCacheEntry> decisionMap = (HashMap<Object, AuthorizationDecisionCacheEntry>) (decisionCache
				.get(key) != null ? decisionCache.get(key).getObjectValue()
				: null);
        if(decisionMap == null){
            decisionMap = new HashMap<Object, AuthorizationDecisionCacheEntry>();
            decisionCache.put(new Element(key,decisionMap));
        }
        AuthorizationDecisionCacheEntry entry = null;
		if(decisionMap.containsKey(domainObject)){
			entry = decisionMap.get(domainObject);
		}else{
			entry = new AuthorizationDecisionCacheEntry(domainObject);
			decisionMap.put(domainObject, entry);
		}
		return entry.isAuthorized(privilege);
    }
	
	public void clear(){
		decisionCache.removeAll();
	}

    /**
     * Will return the cache key to use.
     * http://jira.semanticbits.com/browse/CAAERS-4098
     * @return
     */
    public Object getCacheKeyDiscriminator(){
       String key = CurrentEntityHolder.getEntityCacheKeyDiscriminator();
       return StringUtils.isBlank(key) ? defaultKey : key;
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

	public Ehcache getDecisionCache() {
		return decisionCache;
	}

	public void setDecisionCache(Ehcache decisionCache) {
		this.decisionCache = decisionCache;
	}
	
}
