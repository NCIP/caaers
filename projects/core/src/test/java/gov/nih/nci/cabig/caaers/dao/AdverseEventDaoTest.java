package gov.nih.nci.cabig.caaers.dao;

import edu.nwu.bioinformatics.commons.DateUtils;
import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.*;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class AdverseEventDaoTest extends DaoTestCase<AdverseEventDao> {
    public void testGet() throws Exception {
        AdverseEvent loaded = getDao().getById(-2);
        assertNotNull("AE not found", loaded);
        assertDayOfDate("Wrong day for AE time", 2007, Calendar.APRIL, 3, loaded.getTime());
        assertTimeOfDate("Wrong time for AE time", 21, 43, 56, 987, loaded.getTime());
    }
    
    public void testSave() throws Exception {
        Integer savedId;
        {
            AdverseEvent newEvent = new AdverseEvent();
            newEvent.setTime(new Timestamp(DateUtils.createDate(2004, Calendar.APRIL, 25).getTime() + 600000));
            getDao().save(newEvent);
            assertNotNull("No ID for newly saved event", newEvent.getId());
            savedId = newEvent.getId();
        }

        interruptSession();

        {
            AdverseEvent reloaded = getDao().getById(savedId);
            assertNotNull("Saved AE not found", reloaded);
            assertDayOfDate("Wrong day for loaded time", 2004, Calendar.APRIL, 25, reloaded.getTime());
            assertTimeOfDate("Wrong time for loaded time", 12, 10, 0, 0, reloaded.getTime());
        }
    }
}
