package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.CourseDate;
import gov.nih.nci.cabig.caaers.domain.PostAdverseEventStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcCategoryDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.CtepStudyDiseaseDao;
import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.ctms.web.tabs.Flow;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;
import gov.nih.nci.cabig.ctms.web.tabs.StaticTabConfigurer;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;
import org.springframework.validation.BindingResult;
import static org.easymock.classextension.EasyMock.*;
import org.apache.commons.collections15.map.LazyMap;
import org.apache.commons.collections15.functors.InstantiateFactory;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventControllerTest extends WebTestCase {
    protected ParticipantDao participantDao;
    protected StudyDao studyDao;
    protected CtcDao ctcDao;
    protected CtcCategoryDao ctcCategoryDao;
    protected CtcTermDao ctcTermDao;
    protected StudyParticipantAssignmentDao assignmentDao;
    protected ExpeditedAdverseEventReportDao adverseEventReportDao;
    protected StudyAgentDao studyAgentDao;
    protected AgentDao agentDao;
    protected CtepStudyDiseaseDao ctepStudyDiseaseDao;
    protected AnatomicSiteDao anatomicSiteDao;
    protected PriorTherapyDao priorTherapyDao;
    protected ReportDefinitionDao reportDefinitionDao;
    protected PreExistingConditionDao preExistingConditionDao;

    private StudyParticipantAssignment assignment;

    private CreateAdverseEventController controller;
    private CreateExpeditedAdverseEventCommand firstCommand;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        StaticTabConfigurer tabConfigurer = new StaticTabConfigurer(
            adverseEventReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class),
            agentDao = registerDaoMockFor(AgentDao.class),
            anatomicSiteDao = registerDaoMockFor(AnatomicSiteDao.class),
            assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class),
            ctcDao = registerDaoMockFor(CtcDao.class),
            ctcCategoryDao = registerDaoMockFor(CtcCategoryDao.class),
            ctcTermDao = registerDaoMockFor(CtcTermDao.class),
            ctepStudyDiseaseDao = registerDaoMockFor(CtepStudyDiseaseDao.class),
            participantDao = registerDaoMockFor(ParticipantDao.class),
            priorTherapyDao = registerDaoMockFor(PriorTherapyDao.class),
            preExistingConditionDao = registerDaoMockFor(PreExistingConditionDao.class),
            reportDefinitionDao = registerDaoMockFor(ReportDefinitionDao.class),
            studyDao = registerDaoMockFor(StudyDao.class),
            studyAgentDao = registerDaoMockFor(StudyAgentDao.class)
        );
        ConfigProperty configProperty = new ConfigProperty();
        configProperty.setMap(LazyMap.decorate(new HashMap<String, List<Lov>>(), new InstantiateFactory(ArrayList.class)));
        tabConfigurer.addBean("configurationProperty", configProperty);

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
        controller.setReportDefinitionDao(reportDefinitionDao);
        controller.setPreExistingConditionDao(preExistingConditionDao);
        controller.setTabConfigurer(tabConfigurer);

        // This can't be a constant b/c it has to be created after the application context is
        // loaded
        assignment = createAssignment();

        expect(assignmentDao.getAssignment(assignment.getParticipant(), assignment.getStudySite().getStudy()))
            .andReturn(assignment).anyTimes();

        passFirstPage();
    }

    public void testBindDetectionDate() throws Exception {
        request.setParameter("aeReport.detectionDate", "12/30/1999");
        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertDayOfDate(1999, Calendar.DECEMBER, 30, command.getAeReport().getDetectionDate());
    }

    public void testBindCtcTerm() throws Exception {
        CtcTerm expectedTerm = new CtcTerm();
        request.setParameter("aeReport.adverseEvents[2].ctcTerm", "3022");
        expect(ctcTermDao.getById(3022)).andReturn(expectedTerm);

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expectedTerm, command.getAeReport().getAdverseEvents().get(2).getCtcTerm());
    }

    public void testBindConcomitantMedAgent() throws Exception {
        Agent expectedAgent = new Agent();
        request.setParameter("aeReport.concomitantMedications[2].agent", "30");
        expect(agentDao.getById(30)).andReturn(expectedAgent);

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expectedAgent,
            command.getAeReport().getConcomitantMedications().get(2).getAgent());
    }

    public void testBindStudyAgent() throws Exception {
        StudyAgent expected = setId(332, createStudyAgent("Zed"));
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].studyAgent", "332");
        expect(studyAgentDao.getById(332)).andReturn(expected);

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertSame(expected,
            command.getAeReport().getTreatmentInformation().getCourseAgents().get(1).getStudyAgent());
    }

    public void testBindAdverseEventCourse() throws Exception {
        request.setParameter("aeReport.treatmentInformation.adverseEventCourse.date", "7/3/2023");
        request.setParameter("aeReport.treatmentInformation.adverseEventCourse.number", "7");

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        CourseDate aeCourse = command.getAeReport().getTreatmentInformation().getAdverseEventCourse();

        assertEquals(7, (int) aeCourse.getNumber());
        assertDayOfDate(2023, Calendar.JULY, 3, aeCourse.getDate());
    }

    public void testBindBigDecimalFields() throws Exception {
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].dose.amount", "432.1");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].totalDoseAdministeredThisCourse", "9433");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].administrationDelayAmount", "12");
        request.setParameter("aeReport.treatmentInformation.courseAgents[1].modifiedDose.amount", "");

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
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
        CreateExpeditedAdverseEventCommand command = (CreateExpeditedAdverseEventCommand) objects[0];
        Errors errors = (Errors) objects[1];
        assertFalse("Should not be any errors: " + errors, errors.hasFieldErrors(property));
        CourseAgent courseAgent1
            = command.getAeReport().getTreatmentInformation().getCourseAgents().get(1);
        assertNull(courseAgent1.getModifiedDose().getAmount());
    }

    public void testBindPostAeStatus() throws Exception {
        request.setParameter("aeReport.responseDescription.presentStatus", "NOT_RECOVERED");

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertSame(PostAdverseEventStatus.NOT_RECOVERED,
            command.getAeReport().getResponseDescription().getPresentStatus());
    }

    public void testBindReportDefinitionKey() throws Exception {
        ReportDefinition expectedReportDefinition = new ReportDefinition();
        request.setParameter("optionalReportDefinitionsMap[45]", "true");
        expect(reportDefinitionDao.getById(45)).andReturn(expectedReportDefinition);

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertEquals(1, command.getOptionalReportDefinitionsMap().size());
        assertTrue(command.getOptionalReportDefinitionsMap().get(expectedReportDefinition));
    }
    
    public void testBindAttributions() throws Exception {
        firstCommand.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
        firstCommand.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        firstCommand.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        
        request.setParameter("attributionMap[courseAgent][1][1]", Attribution.DEFINITE.name());
        request.setParameter("attributionMap[conMed][0][2]", Attribution.PROBABLE.name());

        CreateExpeditedAdverseEventCommand command = bindAndReturnCommand();
        assertEquals(Attribution.DEFINITE,
            command.getAttributionMap().get(ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY).get(1).get(1));
        assertEquals(Attribution.PROBABLE,
            command.getAttributionMap().get(ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY).get(0).get(2));
    }

    public void testNoAlwaysVisibleFieldsPastReporterAreAbsolutelyRequired() throws Exception {
        Flow<ExpeditedAdverseEventInputCommand> flow = controller.getFlow();
        List<Tab<ExpeditedAdverseEventInputCommand>> tabs = flow.getTabs();
        assertTrue("Test expectation violation: tab 0 not begin", tabs.get(0) instanceof BeginTab);
        assertTrue("Test expectation violation: tab 1 not basics", tabs.get(1) instanceof BasicsTab);
        assertTrue("Test expectation violation: tab 2 not reporter", tabs.get(2) instanceof ReporterTab);
        assertTrue("Test expectation violation: tab 3 not checkpoint", tabs.get(3) instanceof CheckpointTab);
        for (int i = 4; i < tabs.size(); i++) {
            if (!(tabs.get(i) instanceof TabWithFields)) continue;
            TabWithFields<ExpeditedAdverseEventInputCommand> tab
                = (TabWithFields<ExpeditedAdverseEventInputCommand>) tabs.get(i);
            Map<String, InputFieldGroup> groups = tab.createFieldGroups(firstCommand);
            for (String groupName : groups.keySet()) {
                InputFieldGroup group = groups.get(groupName);
                for (InputField field : group.getFields()) {
                    assertFalse(
                        field.getDisplayName() + " in group " + groupName +
                        " on tab " + tab.getShortTitle() + " (" + tab.getNumber() +
                        ") is absolutely required",
                        field.isRequired());
                }
            }
        }
    }

    public void testCurrentFormObjectReassociatesIfEAERIsSaved() throws Exception {
        firstCommand.getAeReport().setId(17);
        
        adverseEventReportDao.reassociate(firstCommand.getAeReport());

        replayMocks();
        assertSame(firstCommand, controller.currentFormObject(request, firstCommand));
        verifyMocks();
    }
    
    public void testCurrentFormObjectDoesNothingIfEAERIsNotSaved() throws Exception {
        firstCommand.getAeReport().setId(null);

        replayMocks();
        assertSame(firstCommand, controller.currentFormObject(request, firstCommand));
        verifyMocks();
    }

    private Object[] bindAndReturnCommandAndErrors() throws Exception {
        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();
        CreateExpeditedAdverseEventCommand command = (CreateExpeditedAdverseEventCommand) mv.getModel().get("command");
        Errors errors = (Errors) mv.getModel().get(BindingResult.MODEL_KEY_PREFIX + "command");
        assertNotNull(command);
        return new Object[] { command, errors };
    }

    private CreateExpeditedAdverseEventCommand bindAndReturnCommand() throws Exception {
        Object[] objects = bindAndReturnCommandAndErrors();
        Errors errors = (Errors) objects[1];
        assertFalse("No errors expected: " + errors, errors.hasErrors());
        return (CreateExpeditedAdverseEventCommand) objects[0];
    }

    // get the session in place & set study/participant
    private void passFirstPage() throws Exception {
        request.setParameter("_target0", "");
        replayMocks();
        firstCommand
            = (CreateExpeditedAdverseEventCommand) controller.handleRequest(request, response).getModel().get("command");
        firstCommand.setParticipant(assignment.getParticipant());
        firstCommand.setStudy(assignment.getStudySite().getStudy());
        resetMocks();

        expect(assignmentDao.getAssignment(assignment.getParticipant(), assignment.getStudySite().getStudy()))
            .andReturn(assignment).anyTimes();
    }
}
