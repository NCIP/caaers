package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.event.EntityModificationEvent;
import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Refactored PulledUp
 * 
 * @author: Biju Joseph
 */
public abstract class AbstractEventListener   implements ApplicationListener {
    private FilteredDataLoader filteredDataLoader;

    private static final Log log = LogFactory.getLog(AuthenticationSuccessListener.class);

    public void preProcess(ApplicationEvent event){
        //dummy
    }

    public void postProcess(ApplicationEvent event){
        //dummy
    }
    /**
     * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
     * This data is basically domain object IDs to use in IN clause of Query .
     */
    public final void  onApplicationEvent(ApplicationEvent event) {

        if(isSupported(event)){
          preProcess(event);
          filteredDataLoader.updateIndexByUserName(getAuthentication(event));
          postProcess(event);
        }

    }

    public abstract boolean isSupported(ApplicationEvent event);

    public Authentication getAuthentication(ApplicationEvent event){
        if(event instanceof AuthenticationSuccessEvent)  {
            return ((AuthenticationSuccessEvent) event).getAuthentication();
        }

        if(event instanceof EntityModificationEvent){
            return ((EntityModificationEvent) event).getAuthentication(); 
        }
        return null;
    }

    public void setFilteredDataLoader(FilteredDataLoader filteredDataLoader) {
        this.filteredDataLoader = filteredDataLoader;
    }

}
