package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.accesscontrol.dataproviders.FilteredDataLoader;
import gov.nih.nci.cabig.caaers.event.EntityModificationEvent;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
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
    protected EventMonitor eventMonitor;
    protected String correlationId;

    protected static final Log log = LogFactory.getLog(AbstractEventListener.class);

    public void preProcess(ApplicationEvent event){
        if(eventMonitor !=  null){
            if(event instanceof AuthenticationSuccessEvent) {
                correlationId = eventMonitor.addEvent(SecurityUtils.getUserLoginName(), "AUTHENTICATION");
            } else if (event instanceof EntityModificationEvent){
                String eventType = ((EntityModificationEvent) event).getEventType().name();
                correlationId = eventMonitor.addEvent(SecurityUtils.getUserLoginName(), eventType);
            }
        }
    }

    public void postProcess(ApplicationEvent event){
        if(eventMonitor != null && correlationId != null) eventMonitor.markCompletion(correlationId);
    }
    /**
     * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
     * This data is basically domain object IDs to use in IN clause of Query .
     */
    public final void  onApplicationEvent(ApplicationEvent event) {
        if (isSupported(event)) {
            long start = System.currentTimeMillis();
            log.info("Indexing started for " + SecurityUtils.getUserLoginName(getAuthentication(event)));
            preProcess(event);
            log.info("pre processing finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
            filteredDataLoader.updateIndexByUserName(getAuthentication(event));
            log.info("data loading finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
            postProcess(event);
            log.info("Indexing finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
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

    public EventMonitor getEventMonitor() {
        return eventMonitor;
    }

    public void setEventMonitor(EventMonitor eventMonitor) {
        this.eventMonitor = eventMonitor;
    }
}
