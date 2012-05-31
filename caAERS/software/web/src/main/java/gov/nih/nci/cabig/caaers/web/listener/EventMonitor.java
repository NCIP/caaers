package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.*;

/**
 * @author: Biju Joseph
 */
public class EventMonitor {
    private LinkedHashMap<String, Event> lru;
    public EventMonitor() {
        lru = new LinkedHashMap<String, Event>(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 50;
            }
        };
    }

    
    public String addEvent(String name, String eventType){
       Event e = new Event();
       e.setCreateOn(new Date());
       e.setEventType(eventType);
       e.setName(name);
       e.setThreadName(Thread.currentThread().getName());
       
       String id = System.currentTimeMillis() + "-" + e.getThreadName();
       lru.put(id, e);
       return id;
    }
    
    public void markCompletion(String id){
        Event e = (Event)lru.get(id);
        if(e != null) e.complete();
    }
    
    public Collection<Event> getAllEvents(){
       return lru.values();
    }

}

