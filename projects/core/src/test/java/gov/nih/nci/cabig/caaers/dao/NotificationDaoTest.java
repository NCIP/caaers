package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.*;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

public class NotificationDaoTest extends DaoTestCase<NotificationDao> {

    /*
   * Test the loading by ID functionality
   *
   * */
    public void testDomainClass() throws Exception {
        Class<Notification> n = getDao().domainClass();
        assertNotNull(n);
    }

    public void testGetById() throws Exception {
        Notification notification = getDao().getById(-1);
        assertNotNull(notification);
        assertEquals("Wrong Object", -1, (int) notification.getId());
    }

    public void testSave() throws Exception {
        Notification notification = new Notification();
        notification.setContent("C");
        notification.setGridId("");
        getDao().save(notification);
        assertEquals(true, notification.getId() > 0);
    }

    public void testGetAll() throws Exception {
        List<Notification> all = getDao().getAll();
        assertEquals(2, all.size());
    }

}