package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import org.springframework.web.servlet.ModelAndView;
import static org.easymock.classextension.EasyMock.*;

import java.util.Calendar;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventControllerTest extends AdverseEventControllerTestCase {
    private StudyParticipantAssignment assignment;

    private CreateAdverseEventController controller;
    private CreateAdverseEventCommand firstCommand;

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
        controller.afterPropertiesSet();

        // This can't be a constant b/c it has to be created after the application context is
        // loaded
        assignment = Fixtures.createAssignment();

        expect(assignmentDao.getAssignment(assignment.getParticipant(), assignment.getStudySite().getStudy()))
            .andReturn(assignment).anyTimes();

        passFirstPage();
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
    
    public void testBindAttributions() throws Exception {
        firstCommand.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
        firstCommand.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        
        request.setParameter("attributionMap[courseAgent][1][1]", Attribution.DEFINITE.name());
        request.setParameter("attributionMap[conMed][0][2]", Attribution.PROBABLE.name());

        CreateAdverseEventCommand command = bindAndReturnCommand();
        assertEquals(Attribution.DEFINITE,
            command.getAttributionMap().get(AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY).get(1).get(1));
        assertEquals(Attribution.PROBABLE,
            command.getAttributionMap().get(AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(0).get(2));
    }

    private CreateAdverseEventCommand bindAndReturnCommand() throws Exception {
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) mv.getModel().get("command");
        assertNotNull(command);
        return command;
    }

    // get the session in place & set study/participant
    private void passFirstPage() throws Exception {
        request.setParameter("_target0", "");
        replayMocks();
        firstCommand
            = (CreateAdverseEventCommand) controller.handleRequest(request, response).getModel().get("command");
        firstCommand.setParticipant(assignment.getParticipant());
        firstCommand.setStudy(assignment.getStudySite().getStudy());
        resetMocks();

        expect(assignmentDao.getAssignment(assignment.getParticipant(), assignment.getStudySite().getStudy()))
            .andReturn(assignment).anyTimes();
    }
}
