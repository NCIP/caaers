package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

import java.util.Collection;

/**
 * @author: Biju Joseph
 */
public class EventFactory implements ApplicationContextAware{
    
    private ApplicationContext ctx;
    
    private static final Log logger = LogFactory.getLog(EventFactory.class);
    
    public  void publishEntityModifiedEvent(final DomainObject entity){
        publishEntityModifiedEvent(entity, true);
    }

    public void publishEntityModifiedEvent(final DomainObject entity, boolean async){

        EntityModificationEvent event = null;
        
        if(entity instanceof Study){
           event = new StudyModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof ResearchStaff){
           event = new ResearchStaffModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof Investigator){
           event = new InvestigatorModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof ExpeditedAdverseEventReport){
           event = new ReportModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof AdverseEventReportingPeriod){
           event = new CourseModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof Participant){
           event = new SubjectModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof Organization) {
           event = new OrganizationModificationEvent(SecurityUtils.getAuthentication(), entity);
        }else if (entity instanceof AdverseEvent) {
        	event = new AdverseEventModificationEvent(SecurityUtils.getAuthentication(), entity);
        }

        if(async){
            publishAsync(event);
        } else{
           publishSynch(event);
        }


    }

    private void publishAsync(EntityModificationEvent event){
        if(event != null) ctx.publishEvent(event);
    }

    private void publishSynch(EntityModificationEvent event){
       if (event == null || ctx == null) return;
       Collection listenerBeans = ctx.getBeansOfType(ApplicationListener.class, true, false).values();
       for(Object listenerObj : listenerBeans){
           ((ApplicationListener)listenerObj).onApplicationEvent(event);
       }
    }

    public void setApplicationContext(ApplicationContext ctx){
        this.ctx = ctx;
    }
}
