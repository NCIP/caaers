package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.dao.PersonDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalResearchStaff;
import gov.nih.nci.cabig.caaers.domain.dto.TaskNotificationDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.domain.repository.ReportVersionRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class AEReporterDashboardControllerTest extends WebTestCase {
    AEReporterDashboardController controller;
    ReportVersionRepository reportVersionRepository;
    AdverseEventRoutingAndReviewRepositoryImpl rrRepositry;
    List<ReportVersionDTO> reportActivity = new ArrayList<ReportVersionDTO>();
    List<TaskNotificationDTO> taskNotifications = new ArrayList<TaskNotificationDTO>();
    PersonDao personDao;

    public void setUp() throws Exception {
       super.setUp();
       controller = new AEReporterDashboardController();
       reportVersionRepository = registerMockFor(ReportVersionRepository.class);
       rrRepositry = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);
       controller.setReportVersionRepository(reportVersionRepository);
       controller.setRrRepositry(rrRepositry);
       switchToSuperUser();
       taskNotifications.add(Fixtures.createTaskNotificationDTO("testing"));
       personDao = registerDaoMockFor(PersonDao.class);
       controller.setPersonDao(personDao);

    }

    public void testHandleRequestInternal() throws Exception {
        assertSame(rrRepositry, controller.getRrRepositry());
        assertSame(reportVersionRepository, controller.getReportVersionRepository());
        EasyMock.expect(reportVersionRepository.getReportActivity()).andReturn(reportActivity);
        EasyMock.expect(rrRepositry.getTaskNotificationByUserLogin("SYSTEM_ADMIN")).andReturn(taskNotifications);
        EasyMock.expect(personDao.getByLoginId("SYSTEM_ADMIN")).andReturn(new LocalResearchStaff());
        replayMocks();
        ModelAndView mv = controller.handleRequestInternal(request, response);
        assertSame(reportActivity, mv.getModel().get("reportActivity"));
        assertSame(taskNotifications, mv.getModel().get("taskNotifications"));
        verifyMocks();
    }
}
