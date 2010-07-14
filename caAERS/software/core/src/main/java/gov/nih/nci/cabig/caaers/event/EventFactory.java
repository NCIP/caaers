package gov.nih.nci.cabig.caaers.event;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author: Biju Joseph
 */
public class EventFactory implements ApplicationContextAware{
    
    private ApplicationContext ctx;

    public  void publishEntityModifiedEvent(final DomainObject entity){

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
        }


        //publish the event
        if(event != null){
            ctx.publishEvent(event);
        }


    }

    public void setApplicationContext(ApplicationContext ctx){
        this.ctx = ctx;
    }
}
