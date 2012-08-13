package gov.nih.nci.cabig.caaers.web.rule;

import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.FlowFactory;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class CreateSafetyNotificationControllerTest extends WebTestCase {
    CreateSafetyNotificationController controller;
    StudyDao studyDao;
    NotificationDao notificationDao;
    Map<String, String> roles;


    public void setUp() throws Exception {
       super.setUp();
       controller = new CreateSafetyNotificationController();
        studyDao = registerDaoMockFor(StudyDao.class);
        notificationDao = registerDaoMockFor(NotificationDao.class);
        roles = new HashMap<String, String>();

    }

    public void testGetFlowFactory() throws Exception {
        FlowFactory<ManageSafetyNotificationCommand> factory = controller.getFlowFactory();
        Flow<ManageSafetyNotificationCommand> flow = factory.createFlow(new ManageSafetyNotificationCommand());
        assertEquals(2, flow.getTabCount());
        assertSame(SafetyNotificationTab.class, flow.getTab(0).getClass());
        assertSame(SafetyNotificaitonReviewTab.class, flow.getTab(1).getClass());
    }
}
