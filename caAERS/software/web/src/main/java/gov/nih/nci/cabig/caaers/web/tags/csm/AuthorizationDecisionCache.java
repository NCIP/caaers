package gov.nih.nci.cabig.caaers.web.tags.csm;

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
	
	private HashMap<Object, AuthorizationDecisionCacheEntry> decisionCache;
	
	public AuthorizationDecisionCache(){
		decisionCache = new HashMap<Object, AuthorizationDecisionCacheEntry>();
	}
	
	public void addDecision(Object domainObject, String privilege, Boolean allowed){
		if(domainObject == null) return;
		
		AuthorizationDecisionCacheEntry entry = null;
		if(decisionCache.containsKey(domainObject)){
			entry = decisionCache.get(domainObject);
		}else{
			entry = new AuthorizationDecisionCacheEntry(domainObject);
			decisionCache.put(domainObject, entry);
		}
		entry.addDecision(privilege, allowed);
	}
	
	public Boolean isAuthorized(Object domainObject, String privilege){
		AuthorizationDecisionCacheEntry  entry = decisionCache.get(domainObject);
		if(entry == null) return null;
		return entry.isAuthorized(privilege);
	}
	
	public void clear(){
		decisionCache.clear();
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
	
}
