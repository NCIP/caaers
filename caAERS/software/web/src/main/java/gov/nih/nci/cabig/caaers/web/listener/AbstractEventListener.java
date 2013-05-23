/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    protected static final Log log = LogFactory.getLog(AbstractEventListener.class);

    public void preProcess(ApplicationEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void postProcess(ApplicationEvent event){

    }


    private Event addEvent(ApplicationEvent event){
        if(eventMonitor != null){
            String loginName = null;
            int entityId = 0;
            String eventType = "AUTHENTICATION";
            if(event instanceof AuthenticationSuccessEvent){
                loginName = SecurityUtils.getUserLoginName(((AuthenticationSuccessEvent) event).getAuthentication());
            }
            if(event instanceof EntityModificationEvent) {
                EntityModificationEvent entityModificationEvent = (EntityModificationEvent)event;
                loginName = SecurityUtils.getUserLoginName(entityModificationEvent.getAuthentication());
                entityId = entityModificationEvent.getEntity().getId() != null ? entityModificationEvent.getEntity().getId() : -1;
                eventType = entityModificationEvent.getEventType().name();
            }

            return eventMonitor.addEvent(loginName, eventType, entityId);
        }
        return null;
    }
    /**
     * Capture AuthenticationSuccessEvent event . THis is the entry point to get the data from DB and cache.
     * This data is basically domain object IDs to use in IN clause of Query .
     */
    public final void  onApplicationEvent(ApplicationEvent event) {
        if (isSupported(event)) {
            Event monitorEvent = addEvent(event);
            if(monitorEvent.getCompletedOn() != null){
                log.info("Ignoring event as already another event is running");
                log.info(monitorEvent.toString());
                return;
            }

            try{
                long start = System.currentTimeMillis();
                log.info("Indexing started for " + SecurityUtils.getUserLoginName(getAuthentication(event)));
                preProcess(event);
                log.info("pre processing finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
                filteredDataLoader.updateIndexByUserName(getAuthentication(event));
                log.info("data loading finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
                postProcess(event);
                log.info("Indexing finished for " + SecurityUtils.getUserLoginName(getAuthentication(event)) + " took " + (System.currentTimeMillis() - start) + " milliseconds");
                if(monitorEvent != null){
                    eventMonitor.markSuccess(monitorEvent.getEventId());
                }
            }catch (Exception ex){
                log.error(ex);
                if(monitorEvent != null) eventMonitor.markFailure(monitorEvent.getEventId());
            }

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
