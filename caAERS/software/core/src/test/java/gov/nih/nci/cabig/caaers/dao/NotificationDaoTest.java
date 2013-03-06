/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.NotificationQuery;
import gov.nih.nci.cabig.caaers.domain.Notification;

import java.util.List;

/**
 * @author Ion C. Olaru
 * @author Biju Joseph
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
        notification.setSubject("C");
        notification.setGridId("");
        notification.setName("n");
        notification.setStudy(getDao().getAll().get(0).getStudy());

        interruptSession();

        getDao().save(notification);
        assertEquals(true, notification.getId() > 0);
    }

    public void testGetAll() throws Exception {
        List<Notification> all = getDao().getAll();
        assertEquals(3, all.size());
    }
    
    public void testSearch(){
        NotificationQuery query = new NotificationQuery();
        List<Notification> nfList = (List<Notification>) getDao().search(query);
        assertEquals(3, nfList.size());

        query.filterByStudyId(-1);
        nfList = (List<Notification>) getDao().search(query);
        assertEquals(2, nfList.size());
        
        query.filterByStudyId(-2);
        nfList = (List<Notification>) getDao().search(query);
        assertEquals(1, nfList.size());

        query.filterByStudyId(-999999);
        nfList = (List<Notification>) getDao().search(query);
        assertEquals(0, nfList.size());

        query = new NotificationQuery();
        query.filterByName("no-name1");
        nfList = (List<Notification>) getDao().search(query);
        assertEquals(1, nfList.size());
    }
    
}
