package gov.nih.nci.cabig.caaers.accesscontrol.dataproviders;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.apache.log4j.Logger;

import com.semanticbits.security.contentfilter.IdFetcher;
import com.semanticbits.security.contentfilter.cache.QueryCacheManager;

@Deprecated
public class FilteredDataProvider extends BaseSecurityFilterer {
	
	private Authentication authentication;
	private IdFetcher idFetcher;
	Logger log = Logger.getLogger(FilteredDataProvider.class);
	
	/**
	 * To Get data from Fetcher or Cache .. 
	 * @param authentication
	 * @param idFetcher
	 */
	public  FilteredDataProvider (Authentication authentication, IdFetcher idFetcher) {
		this.authentication =  authentication;
		this.idFetcher = idFetcher;
	}
	
	/**
	 * Get data from cache or fetcher . If it is a web session try to get data from cache and call should be succesfull
	 * 
	 * @return
	 */
	public List fetch() {

		List ids = new ArrayList();
		
		String userName = SecurityUtils.getUserLoginName();
		WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
		String sessionId = null;
		
		// request is  from web .. 
		if (details != null) {
			sessionId = details.getSessionId();
		}
		if (sessionId != null){
			log.info("Getting from Cache ..");
			ids = QueryCacheManager.getFromCache(sessionId, this.getFetcherClassName());
			if (ids != null) {
				return ids;
			} else {
				//Step : 1
				log.error("Got NULL data from Cache ..");
			}
		} 
		//if no session-request is not from web  , get the data realtime from fetcher ...
		log.info("Getting from Fetcher ..");
		ids = idFetcher.fetch(userName);
		
		// incase of sessionId not null and participantIds NULL - This is a problem . .. (Step :1)
		// reasons for this behaviour might be data got expired or lost from the session , so re-cache it .
		if (sessionId != null ) {
			QueryCacheManager.addDataToCache(sessionId, this.getFetcherClassName(), ids);
		}
		return ids;
	}
	
	public boolean filteringNotRequired() {
		GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(authentication);
		if (isSuperUser(grantedAuthorities)) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getFetcherClassName() {
		String className = idFetcher.getClass().getName();
		return className;
	}
	
}
