package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.accesscontrol.BaseSecurityFilterer;

import java.util.List;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.userdetails.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.semanticbits.security.contentfilter.IdFetcher;
import com.semanticbits.security.contentfilter.cache.QueryCacheManager;

/**
 * Event Listener
 * @author akkalas
 *
 */
public class AuthenticationSuccessListener  extends BaseSecurityFilterer implements ApplicationListener {
	
	private IdFetcher participantIdFetcher;
	
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
			// need to change - to load data assynchrosly 
			List listOfIds = participantIdFetcher.fetch(userName);
			String className = participantIdFetcher.getClass().getName();
			//cache the data .. 
			QueryCacheManager.addDataToCache(sessionId, className, listOfIds);

		}		
	}



	public void setParticipantIdFetcher(IdFetcher participantIdFetcher) {
		this.participantIdFetcher = participantIdFetcher;
	}


}