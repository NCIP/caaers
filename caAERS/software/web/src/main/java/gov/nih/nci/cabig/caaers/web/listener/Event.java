/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

public class Event{
    private String eventKey;
    private String loginName;
    private String threadName;
    private Date createOn;
    private Date completedOn;
    private String eventType;
    private String status;
    private int entityId;
    private String eventId;

    public Event(String eventKey, String loginName, String threadName, Date createOn, String eventType, String status, int entityId, String eventId) {
        this.eventKey = eventKey;
        this.loginName = loginName;
        this.threadName = threadName;
        this.createOn = createOn;
        this.eventType = eventType;
        this.status = status;
        this.entityId = entityId;
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
    
    public String getDisplayName(){
        StringBuilder sb = new StringBuilder();
        sb.append(threadName)
            .append(" : ").append(loginName)
            .append(" for ").append(eventType).append("(").append(entityId).append(")");
        if(createOn != null) sb.append(", on  : ").append(DateUtils.formatDate(createOn, "MMM-dd at HH:mm:ss a"));
        sb.append(", took ").append(runningTime()) .append(" ").append(status) ;
        return sb.toString() ;
    }

    public String runningTime(){
        if(completedOn == null) return "running";
        long l = completedOn.getTime() - createOn.getTime()   ;
        if(l < 1000) return l + " ms";
        return (l/1000) + " seconds";
    }
    public void complete(String status){
        completedOn = new Date();
        this.status = status;
    }


    public Date getCompletedOn() {
        return completedOn;
    }

    public String getEventKey(){
        return eventKey;
    }
    public String getEventId() {
        return eventId;
    }


}
