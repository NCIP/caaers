package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.datamigrator.CaaersDataMigratorDelegate;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;

import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Event Listener
 * @author akkalas
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */
public class AuthenticationSuccessListener  implements ApplicationListener {
	
	private FilteredDataLoader filteredDataLoader;
	private CaaersDataMigratorDelegate caaersDataMigratorDelegate;
    private static final Log log = LogFactory.getLog(AuthenticationSuccessListener.class);
    
	/**
	 * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
	 * This data is basically domain object IDs to use in IN clause of Query . 
	 */
	public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            try {
                caaersDataMigratorDelegate.doMigrate();
            } catch (Exception e) {
                log.error("Error while migrating the database. Alert your caAERS administrator, please.", e);
                throw(new CaaersSystemException("Error while migrating the database. Alert your caAERS administrator, please."));
            }
        }

		if (event instanceof AuthenticationSuccessEvent) {
			AuthenticationSuccessEvent authenticationSuccessEvent = (AuthenticationSuccessEvent)event;
			Authentication authentication = authenticationSuccessEvent.getAuthentication();             
            //if(SecurityUtils.checkAuthorization(authentication,UserGroupType.caaers_super_user)) return;
			
			//if global user- ignore indexing.
			//if(!SecurityUtils.isScoped(authentication)) return;
            //String userName = SecurityUtils.getUserLoginName(authentication);
			filteredDataLoader.updateIndexByUserName(authentication);
		}		
	}
	
	public void setFilteredDataLoader(FilteredDataLoader filteredDataLoader) {
		this.filteredDataLoader = filteredDataLoader;
	}

    public CaaersDataMigratorDelegate getCaaersDataMigratorDelegate() {
        return caaersDataMigratorDelegate;
    }

    public void setCaaersDataMigratorDelegate(CaaersDataMigratorDelegate caaersDataMigratorDelegate) {
        this.caaersDataMigratorDelegate = caaersDataMigratorDelegate;
    }
}