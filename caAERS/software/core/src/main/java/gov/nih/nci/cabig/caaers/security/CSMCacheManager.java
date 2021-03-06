/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.security;

import gov.nih.nci.cabig.caaers.domain.User;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;

import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CSMCacheManager {
	
	private static volatile CacheManager instance = null;
	public static String PROTECTION_GROUP_ROLE_CONTEXT = "PROTECTION_GROUP_ROLE_CONTEXT";
	public static String PROTECTION_ELEMENT_PRIVILEGE_CONTEXT = "PROTECTION_ELEMENT_PRIVILEGE_CONTEXT";
	private static long TIME_TO_LIVEINCACHE_INSECONDS = 1800;
	private static long IDLE_TIME_INSECONDS = 1200;
    private static long IDLE_TIME_INSECONDS_FOR_USER = 300;
	private static final String ROLE_PRIVILEGE_MAPPING_CACHE_KEY = "gov.nih.nci.cabig.caaers.security.CSMCacheManager.ROLE_PRIVILEGE_MAPPING_CACHE_KEY";
    private static final String USER_CACHE_KEY = "gov.nih.nci.cabig.caaers.domain.User.CACHE_KEY";
	
	/**
	 * Singleton CacheManager
	 * @return
	 */
	public synchronized static CacheManager getCacheManager() {
		if(instance == null) {
			instance = CacheManager.create();
		} else {
			instance = CacheManager.getInstance();
		}
		return instance;
	}

    public static void addUserToCache(String loginName, User user){
       CacheManager cacheManager = getCacheManager();
       if(cacheManager.getCache(USER_CACHE_KEY) == null){
           synchronized (cacheManager){
               //why 40, BJ is anticipating that there will only be 100 concurrent sessions.
               Cache cache = new Cache(USER_CACHE_KEY, 260, false, false, TIME_TO_LIVEINCACHE_INSECONDS, IDLE_TIME_INSECONDS_FOR_USER);
               cacheManager.addCache(cache);
           }
       }
       Cache cache = cacheManager.getCache(USER_CACHE_KEY);
       Element e = new Element(loginName, user);
       cache.put(e);
       
    }

	/**
	 * @param objectId
	 * @param privilege
	 */
	public static void addRolePrivilegeMappingToCache(String objectId, String privilege, List<String> roles) {
		CacheManager cacheManager = getCacheManager();
		if (cacheManager.getCache(ROLE_PRIVILEGE_MAPPING_CACHE_KEY) == null) {
			synchronized (cacheManager) {
				if (cacheManager.getCache(ROLE_PRIVILEGE_MAPPING_CACHE_KEY) == null) {
					Cache cache = new Cache(ROLE_PRIVILEGE_MAPPING_CACHE_KEY, 600, true, false, TIME_TO_LIVEINCACHE_INSECONDS, IDLE_TIME_INSECONDS);
					cacheManager.addCache(cache);					
				}
			}
		}
		cacheManager.getCache(ROLE_PRIVILEGE_MAPPING_CACHE_KEY).put(
				new Element(objectId + "_" + privilege, roles));
	}


    /**
     * Will fetch the user object from cache. 
     * @param loginName
     * @return
     */
    public static User getUserFromCache(String loginName){
        Cache cache = getCacheManager().getCache(USER_CACHE_KEY);
        if(cache != null){
            Element userElement = cache.get(loginName);
            if(userElement != null) return (User) userElement.getObjectValue();
        }

       
        return null;
    }

    /**
     * Will clear off the User identified by the lognName from cache. 
     * @param loginName
     */
    public static void removeUserFromCache(String loginName){       
       Cache cache = getCacheManager().getCache(USER_CACHE_KEY);
        if(cache != null){
            Element userElement = cache.get(loginName);
            if(userElement != null)  cache.removeElement(userElement);
        }
    }

	/**
	 * @param objectId
	 * @param privilege
	 * @return
	 */
	public static List<String> getRolesFromCache(String objectId, String privilege) {
		List<String> list = null;
		CacheManager cacheManager = getCacheManager();
		Cache cache = cacheManager.getCache(ROLE_PRIVILEGE_MAPPING_CACHE_KEY);
		if (cache != null) {
			Element element = cache.get(objectId + "_" + privilege);
			if (element!=null) {
				list = (List<String>) element.getObjectValue();
			}
		}
		return list;
	}

}
