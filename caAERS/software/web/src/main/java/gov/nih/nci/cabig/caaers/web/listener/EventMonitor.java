/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.*;

/**
 * @author: Biju Joseph
 */
public class EventMonitor {
    private LinkedHashMap<String, Event> lru;
    private Map<String, Event> activeMap;

    public EventMonitor() {
        activeMap = new HashMap<String, Event>();
        lru = new LinkedHashMap<String, Event>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 50;
            }
        };
    }

    
    public Event addEvent(String loginName, String eventType, int entityId){
        StringBuilder sb = new StringBuilder(loginName).append(":").append(eventType).append(':').append(entityId);
        String eventKey = sb.toString();

        String thread = Thread.currentThread().getName();
        Date now = new Date();
        String id = sb.append(':').append(now.getTime()).toString();

        Event e = new Event(eventKey, loginName, thread, now, eventType, "NEW", entityId, id);
        Event active = activeMap.get(eventKey);
        if(active != null){
            e.complete("IGNORED for |" + active.getEventKey() + "|");
        }else{
            activeMap.put(eventKey, e);
        }

        lru.put(id, e);
        return e;
    }
    
    public void markSuccess(String id){
        synchronized (activeMap){
            Event e = lru.get(id);
            if(e == null) return;
            e.complete("SUCCESS");
            activeMap.remove(e.getEventKey());
        }
    }

    public void markFailure(String id){
        synchronized (activeMap){
            Event e = lru.get(id);
            if(e == null) return;
            e.complete("FAILED");
            activeMap.remove(e.getEventKey());
        }
    }
    
    public Collection<Event> getAllEvents(){
       return lru.values();
    }

}

