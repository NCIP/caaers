package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.datamigrator.CaaersDataMigratorDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * The Data Migration related tasks are initiated from here.
 *
 * @author: Biju Joseph
 */
public class ContextRefreshEventListener implements ApplicationListener {

    private CaaersDataMigratorDelegate caaersDataMigratorDelegate;
    
    private static final Log log = LogFactory.getLog(ContextRefreshEventListener.class);

    public void onApplicationEvent(ApplicationEvent event) {
        //Listen on the Context Refresh Event.
      if (event instanceof ContextRefreshedEvent) {
            try {
                caaersDataMigratorDelegate.doMigrate();
            } catch (Exception e) {
                log.error("Error while migrating the database. Alert your caAERS administrator, please.", e);
                throw(new CaaersSystemException("Error while migrating the database. Alert your caAERS administrator, please."));
            }
        }
    }

    public CaaersDataMigratorDelegate getCaaersDataMigratorDelegate() {
        return caaersDataMigratorDelegate;
    }

    public void setCaaersDataMigratorDelegate(CaaersDataMigratorDelegate caaersDataMigratorDelegate) {
        this.caaersDataMigratorDelegate = caaersDataMigratorDelegate;
    }
}
