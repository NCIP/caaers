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

        Event e = new Event("x", "n", "j" , createdOn, "T", "NEW", 1, "xxx");
        e.complete("SUCCESS");
        System.out.println(e.toString()) ;


        monitor.addEvent("x", "y", 1);

        int i = monitor.getAllEvents().size();
        assertEquals(1,i);

    }

    public void testMarkCompletion() throws Exception {
       Event e = monitor.addEvent("x", "y", 1);
       monitor.markSuccess(e.getEventId());
       assertNotNull(e.getCompletedOn()  );
    }

    public void testGetAllEvents() throws Exception {
        Event e = monitor.addEvent("x", "y",1);
        int i = monitor.getAllEvents().size();
        assertEquals(1,i);

        //check LRU behaviour
        for(int j = 0; j < 60; j++){
          e = monitor.addEvent("x" + j, "y" + j, j) ;
          Thread.sleep(2);
        }
        i = monitor.getAllEvents().size();
        assertEquals(50,i);

    }
}
