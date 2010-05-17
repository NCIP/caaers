package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Event Listener
 * @author akkalas
 * @author Biju Joseph
 *
 */
public class AuthenticationSuccessListener  implements ApplicationListener {
	
	private FilteredDataLoader filteredDataLoader;
	
	/**
	 * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
	 * This data is basically domain object IDs to use in IN clause of Query . 
	 */
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof AuthenticationSuccessEvent) {

            //if super user- ignore indexing. 
            if(SecurityUtils.checkAuthorization(UserGroupType.caaers_super_user)) return;
            String userName = SecurityUtils.getUserLoginName();
			filteredDataLoader.updateIndexByUserName(userName);
			
		}		
	}
	
	public void setFilteredDataLoader(FilteredDataLoader filteredDataLoader) {
		this.filteredDataLoader = filteredDataLoader;
	}


}