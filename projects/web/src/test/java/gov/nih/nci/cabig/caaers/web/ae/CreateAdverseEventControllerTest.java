package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventControllerTest extends WebTestCase {
    private CreateAdverseEventController controller;

    private ParticipantDao participantDao;
    private StudyDao studyDao;
    private CtcDao ctcDao;
    private CtcTermDao ctcTermDao;
    private StudyParticipantAssignmentDao assignmentDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        participantDao = registerDaoMockFor(ParticipantDao.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        ctcTermDao = registerDaoMockFor(CtcTermDao.class);

        controller = new CreateAdverseEventController();
        controller.setParticipantDao(participantDao);
        controller.setStudyDao(studyDao);
        controller.setCtcTermDao(ctcTermDao);
        controller.setCtcDao(ctcDao);
        controller.setAssignmentDao(assignmentDao);
    }

    public void testBindDetectionDate() throws Exception {
        request.setParameter("aeReport.detectionDate", "12/30/1999");
        request.setParameter("_target1", "");
        controller.handleRequest(request, response); // once to get the session in place
        ModelAndView mv = controller.handleRequest(request, response);
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) mv.getModel().get("command");
        assertNotNull(command);
        assertDayOfDate(1999, Calendar.DECEMBER, 30, command.getAeReport().getDetectionDate());
    }
}
