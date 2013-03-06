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
    private String name;
    private String threadName;
    private Date createOn;
    private Date completedOn;
    private String eventType;

    @Override
    public String toString() {
        return "Event {" +
                "name: '" + name + '\'' +
                ", threadName: '" + threadName + '\'' +
                ", createOn: " + DateUtils.formatDate(createOn, DateUtils.WS_DATE_PATTERN) +
                ", completedOn: " + completedOn == null ? "---" : DateUtils.formatDate(completedOn, DateUtils.WS_DATE_PATTERN) +
                ", eventType: '" + eventType + '\'' +
                ", time: '" + runningTime() + '\'' +
                '}';
    }
    
    public String getDisplayName(){
        StringBuilder sb = new StringBuilder();
        sb.append("Event [ ");
        sb.append("name : ").append(name).append(",");
        sb.append("threadName : ").append(threadName).append(",");
        sb.append("entity : ").append(eventType).append(",");
        if(createOn != null) sb.append("createdON : ").append(DateUtils.formatDate(createOn, DateUtils.WS_DATE_PATTERN)).append(",");
        if(completedOn != null) sb.append("completedOn : ").append(DateUtils.formatDate(completedOn, DateUtils.WS_DATE_PATTERN)).append(",");
        sb.append("time : ").append(runningTime());
        sb.append("]");
        return sb.toString() ;
    }

    public String runningTime(){
        if(completedOn == null) return "running";
        long l = completedOn.getTime() - createOn.getTime()   ;
        if(l < 1000) return l + " ms";
        return (l/1000) + " seconds";
    }
    public void complete(){
        setCompletedOn(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
