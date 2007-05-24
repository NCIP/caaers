package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CourseDate;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import static org.easymock.classextension.EasyMock.*;

import java.util.Calendar;
import java.math.BigDecimal;

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
        controller.setNowFactory(nowFactory);
        controller.setParticipantDao(participantDao);
        controller.setStudyDao(studyDao);
        controller.setCtcCategoryDao(ctcCategoryDao);
        controller.setCtcTermDao(ctcTermDao);
        controller.setAssignmentDao(assignmentDao);
        controller.setReportDao(adverseEventReportDao);
        controller.setAgentDao(agentDao);
        controller.setStudyAgentDao(studyAgentDao);
        controller.setCtepStudyDiseaseDao(ctepStudyDiseaseDao);
        controller.setAnatomicSiteDao(anatomicSiteDao);
        controller.setPriorTherapyDao(priorTherapyDao);

        // This can't be a constant b/c it has to be created after the application context is
        // loaded
        assignment = createAssignment();

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

    public void testBindStudyAgent() throws Exception {
        StudyAgent expected = setId(332, createStudyAgent("Zed"));
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].studyAgent", "332");
        expect(studyAgentDao.getById(332)).andReturn(expected);

        CreateAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expected,
            command.getAeReport().getTreatmentInformation().getCourseAgents().get(1).getStudyAgent());
    }

    public void testBindAdverseEventCourse() throws Exception {
        request.setParameter("aeReport.treatmentInformation.adverseEventCourse.date", "7/3/2023");
        request.setParameter("aeReport.treatmentInformation.adverseEventCourse.number", "7");

        CreateAdverseEventCommand command = bindAndReturnCommand();
        CourseDate aeCourse = command.getAeReport().getTreatmentInformation().getAdverseEventCourse();

        assertEquals(7, (int) aeCourse.getNumber());
        assertDayOfDate(2023, Calendar.JULY, 3, aeCourse.getDate());
    }

    public void testBindBigDecimalFields() throws Exception {
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].dose.amount", "432.1");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].totalDoseAdministeredThisCourse", "9433");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].administrationDelayAmount", "12");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].modifiedDose.amount", "");

        CreateAdverseEventCommand command = bindAndReturnCommand();
        CourseAgent courseAgent1 = command.getAeReport().getTreatmentInformation().getCourseAgents().get(1);
        assertEquals(new BigDecimal("432.1"), courseAgent1.getDose().getAmount());
        assertEquals(new BigDecimal("9433"), courseAgent1.getTotalDoseAdministeredThisCourse());
        assertEquals(new BigDecimal("12"), courseAgent1.getAdministrationDelayAmount());
        assertNull(courseAgent1.getModifiedDose().getAmount());
    }

    public void testBindBigDecimalToEmptySetsNull() throws Exception {
        String property = "aeReport.treatmentInformation.courseAgents[1].modifiedDose.amount";

        firstCommand.getAeReport().getTreatmentInformation().getCourseAgents().get(1)
            .getModifiedDose().setAmount(new BigDecimal(44));
        request.setParameter(property, "");

        Object[] objects = bindAndReturnCommandAndErrors();
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) objects[0];
        Errors errors = (Errors) objects[1];
        assertFalse("Should not be any errors: " + errors, errors.hasFieldErrors(property));
        CourseAgent courseAgent1
            = command.getAeReport().getTreatmentInformation().getCourseAgents().get(1);
        assertNull(courseAgent1.getModifiedDose().getAmount());
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

    private Object[] bindAndReturnCommandAndErrors() throws Exception {
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        CreateAdverseEventCommand command = (CreateAdverseEventCommand) mv.getModel().get("command");
        Errors errors = (Errors) mv.getModel().get(BindingResult.MODEL_KEY_PREFIX + "command");
        assertNotNull(command);
        return new Object[] { command, errors };
    }

    private CreateAdverseEventCommand bindAndReturnCommand() throws Exception {
        Object[] objects = bindAndReturnCommandAndErrors();
        Errors errors = (Errors) objects[1];
        assertFalse("No errors expected: " + errors, errors.hasErrors());
        return (CreateAdverseEventCommand) objects[0];
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
