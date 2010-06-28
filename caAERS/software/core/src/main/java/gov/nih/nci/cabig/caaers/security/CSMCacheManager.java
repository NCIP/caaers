package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;

import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CSMCacheManager {
	
	private static CacheManager instance = null;
	public static String PROTECTION_GROUP_ROLE_CONTEXT = "PROTECTION_GROUP_ROLE_CONTEXT";
	public static String PROTECTION_ELEMENT_PRIVILEGE_CONTEXT = "PROTECTION_ELEMENT_PRIVILEGE_CONTEXT";
	private static long TIME_TO_LIVEINCACHE_INSECONDS = 1200;
	
	/**
	 * Singleton CacheManager
	 * @return
	 */
	private synchronized static CacheManager getCacheManager() {
		if(instance == null) {
			instance = CacheManager.create();
		} else {
			instance = CacheManager.getInstance();
		}
		return instance;
	}
	
	/**
	 * 
	 * @param cacheKey - ex: SessionID 
	 * @param loginId- 
	 * @param listOfIds - ex : list of contexts to place in cache . 
	 */
	public static void addProtectionGroupRoleContextToCache(String cacheKey , String loginId , Set<ProtectionGroupRoleContext> protectionGroupRoleContexts) {
		CacheManager cacheManager = getCacheManager();
		if (cacheManager.getCache(cacheKey) == null) {
			Cache cache = new Cache (cacheKey,0,true,false,TIME_TO_LIVEINCACHE_INSECONDS,TIME_TO_LIVEINCACHE_INSECONDS);
			cacheManager.addCache(cache);
			cacheManager.getCache(cacheKey).put(new Element(loginId+"_"+PROTECTION_GROUP_ROLE_CONTEXT , protectionGroupRoleContexts));
		} else {
			cacheManager.getCache(cacheKey).put(new Element(loginId+"_"+PROTECTION_GROUP_ROLE_CONTEXT , protectionGroupRoleContexts));
		}
	}

	/**
	 * 
	 * @param cacheKey - ex: SessionID 
	 * @param loginId- 
	 * @param listOfIds - ex : list of contexts to place in cache . 
	 */
	public static void addProtectionElementPrivilegeContextToCache(String cacheKey , String loginId , Set<ProtectionElementPrivilegeContext> protectionElementPrivilegeContexts) {
		CacheManager cacheManager = getCacheManager();
		if (cacheManager.getCache(cacheKey) == null) {
			Cache cache = new Cache (cacheKey,0,true,false,TIME_TO_LIVEINCACHE_INSECONDS,TIME_TO_LIVEINCACHE_INSECONDS);
			cacheManager.addCache(cache);
			cacheManager.getCache(cacheKey).put(new Element(loginId+"_"+PROTECTION_ELEMENT_PRIVILEGE_CONTEXT , protectionElementPrivilegeContexts));
		} else {
			cacheManager.getCache(cacheKey).put(new Element(loginId+"_"+PROTECTION_ELEMENT_PRIVILEGE_CONTEXT , protectionElementPrivilegeContexts));
		}
	}
	
	/**
	 * 
	 * @param cacheKey - ex: SessionID 
	 * @param loginName- 
	 * @return
	 */
	public static Set getFromCache(String cacheKey , String loginId , String context) {
		CacheManager cacheManager = getCacheManager() ;
		Cache cache = cacheManager.getCache(cacheKey);
		if (cache == null) {
			return null;
		}		
		Element ele = cache.get(loginId+"_"+context);
		if (ele == null) {
			return null;
		}
		return (Set)ele.getObjectValue();
	}

}
