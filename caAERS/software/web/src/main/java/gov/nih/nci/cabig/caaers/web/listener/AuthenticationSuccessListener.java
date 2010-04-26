package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.userdetails.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Event Listener
 * @author akkalas
 *
 */
public class AuthenticationSuccessListener  extends BaseSecurityFilterer implements ApplicationListener {
	
	private FilteredDataLoader filteredDataLoader;
	
	/**
	 * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
	 * This data is basically domain object IDs to use in IN clause of Query . 
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AuthenticationSuccessEvent) {
			Authentication source = (Authentication)event.getSource();
			
			// if user per user , no need to cache bacause we dont need to apply filters on original query .
			GrantedAuthority[] grantedAuthorities = getGrantedAuthorities(source);
			if (isSuperUser(grantedAuthorities)) {
				return;	
			}
			// Check if user logging via web GUI , If not web user no need to cache . 
			WebAuthenticationDetails details = (WebAuthenticationDetails)source.getDetails();
			String sessionId = null;
			if (details == null) {
				return;
			} else {
				sessionId = details.getSessionId();
			}
			
			if (sessionId == null) {
				return;
			}
			
			Object principal  =  source.getPrincipal();
			String userName = "";
			if (principal instanceof User) {
				userName = ((User)principal).getUsername();
			} else {
				userName = principal.toString();
			}
			System.out.println("user name " + userName);
			//need to change - to load data assynchrosly 
			//filteredDataLoader.loadByUserName(userName, sessionId);
			filteredDataLoader.updateIndexByUserName(userName);
			
			/*
			
			List listOfIds = participantIdFetcher.fetch(userName);
			String className = participantIdFetcher.getClass().getName();
			//cache the data .. 
			QueryCacheManager.addDataToCache(sessionId, className, listOfIds);
			*/

		}		
	}
	
	public void setFilteredDataLoader(FilteredDataLoader filteredDataLoader) {
		this.filteredDataLoader = filteredDataLoader;
	}


}