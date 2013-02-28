/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.rule.notification.ListNotificationCommand;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class ListSafetyNotificationControllerTest extends WebTestCase {

    List<Notification> nfList = new ArrayList<Notification>();
    ListSafetyNotificationController controller;
    NotificationDao notificationDao;
    public void setUp() throws Exception {
        super.setUp();
        controller = new ListSafetyNotificationController();
        notificationDao = registerDaoMockFor(NotificationDao.class);
        controller.setNotificationDao(notificationDao);
    }
    
    public void testFormBackingObject() throws Exception{
        EasyMock.expect(notificationDao.getAll()).andReturn(nfList);
        replayMocks();
        ListSafetyNotificationCommand cmd = (ListSafetyNotificationCommand) controller.formBackingObject(request);
        assertSame(nfList, cmd.getNotifications());

        verifyMocks();
    }
}
