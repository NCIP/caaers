/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.listener;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;

/**
 * @author: Biju Joseph
 */
public class EventMonitorTest extends AbstractTestCase {
    EventMonitor monitor;
    
    public void setUp() throws Exception {
        super.setUp();
        monitor = new EventMonitor();
    }

    public void testAddEvent() throws Exception {
        Date createdOn = DateUtils.parseDate("12/28/2011");
        Date completedOn = DateUtils.parseDate("12/29/2011");

        Event e = new Event();
        e.setName("n");
        e.setCreateOn(createdOn);
        e.setEventType("x");
        e.setThreadName("j");
        e.setCompletedOn(completedOn);
        assertEquals("2011-12-29T00:00:00, eventType: 'x', time: '86400 seconds'}", e.toString());
        assertNotNull(e.getDisplayName());
        String id = monitor.addEvent("x", "y");
        int i = monitor.getAllEvents().size();
        assertEquals(1,i);

    }

    public void testMarkCompletion() throws Exception {
        String id = monitor.addEvent("x", "y");
        assertTrue(monitor.getAllEvents().toArray(new Event[]{})[0].getCompletedOn() == null);
        monitor.markCompletion(id);
        assertFalse(monitor.getAllEvents().toArray(new Event[]{})[0].getCompletedOn() == null);
    }

    public void testGetAllEvents() throws Exception {
        String id = monitor.addEvent("x", "y");
        int i = monitor.getAllEvents().size();
        assertEquals(1,i);

        //check LRU behaviour
        for(int j = 0; j < 60; j++){
          String x = monitor.addEvent("x" + j, "y" + j) ;
          Thread.sleep(2);
        }
        i = monitor.getAllEvents().size();
        assertEquals(50,i);

    }
}
