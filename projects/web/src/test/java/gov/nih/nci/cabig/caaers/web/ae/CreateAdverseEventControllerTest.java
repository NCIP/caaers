package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import org.springframework.web.servlet.ModelAndView;
import static org.easymock.classextension.EasyMock.*;

import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventControllerTest extends AdverseEventControllerTestCase {
    private CreateAdverseEventController controller;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new CreateAdverseEventController();
        controller.setAutowirer(autowirer);
        controller.setParticipantDao(participantDao);
        controller.setStudyDao(studyDao);
        controller.setCtcTermDao(ctcTermDao);
        controller.setAssignmentDao(assignmentDao);
        controller.setReportDao(adverseEventReportDao);
        controller.setAgentDao(agentDao);
    }

    public void testBindDetectionDate() throws Exception {
        request.setParameter("aeReport.detectionDate", "12/30/1999");
        CreateAdverseEventCommand command = bindAndReturnCommand();
        assertDayOfDate(1999, Calendar.DECEMBER, 30, command.getAeReport().getDetectionDate());
    }

    public void testBindCtcTerm() throws Exception {
        CtcTerm expectedTerm = new CtcTerm();
        request.setParameter("aeReport.adverseEvents[2].ctcTerm", "3022");
        expect(ctcTermDao.getById(3022)).andReturn(expectedTerm);

        CreateAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expectedTerm, command.getAeReport().getAdverseEvents().get(2).getCtcTerm());
    }

    public void testBindConcomitantMedAgent() throws Exception {
        Agent expectedAgent = new Agent();
        request.setParameter("aeReport.concomitantMedications[2].agent", "30");
        expect(agentDao.getById(30)).andReturn(expectedAgent);

        CreateAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expectedAgent,
            command.getAeReport().getConcomitantMedications().get(2).getAgent());
    }

    private CreateAdverseEventCommand bindAndReturnCommand() throws Exception {
        request.setParameter("_target1", "");
        controller.handleRequest(request, response); // once to get the session in place
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) mv.getModel().get("command");
        assertNotNull(command);
        return command;
    }
}
