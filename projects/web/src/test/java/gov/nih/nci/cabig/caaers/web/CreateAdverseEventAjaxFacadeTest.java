package gov.nih.nci.cabig.caaers.web;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.CodedGrade;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventAjaxFacade;
import gov.nih.nci.cabig.caaers.web.ae.EditExpeditedAdverseEventCommand;
import gov.nih.nci.cabig.caaers.web.ae.EditAdverseEventController;
import gov.nih.nci.cabig.caaers.web.ae.ExpeditedAdverseEventInputCommand;
import static org.easymock.classextension.EasyMock.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacadeTest extends DwrFacadeTestCase {
    private CreateAdverseEventAjaxFacade facade;
    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcDao ctcDao;
    private CtcTermDao ctcTermDao;
    private ExpeditedAdverseEventReportDao aeReportDao;
    private TreatmentAssignmentDao treatmentAssignmentDao;
    private InteroperationService interoperationService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        ctcTermDao = registerDaoMockFor(CtcTermDao.class);
        aeReportDao = registerDaoMockFor(ExpeditedAdverseEventReportDao.class);
        interoperationService = registerMockFor(InteroperationService.class);
        treatmentAssignmentDao = registerDaoMockFor(TreatmentAssignmentDao.class);

        facade = new CreateAdverseEventAjaxFacade();
        facade.setParticipantDao(participantDao);
        facade.setStudyDao(studyDao);
        facade.setCtcDao(ctcDao);
        facade.setCtcTermDao(ctcTermDao);
        facade.setAeReportDao(aeReportDao);
        facade.setTreatmentAssignmentDao(treatmentAssignmentDao);
        facade.setInteroperationService(interoperationService);
        facade.setExpeditedReportTree(new ExpeditedReportTree());

        ConfigProperty configProperty = new ConfigProperty();
    	Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
    	map.put("labUnitsRefData", new ArrayList<Lov>());
    	ArrayList<Lov> list = new ArrayList<Lov>();
    	list.add(new Lov("Chloride","Chloride"));
    	map.put("labTestNamesRefData", list);
    	configProperty.setMap(map);
    	facade.setConfigurationProperty(configProperty);
    }

    public void testMatchParticipants() throws Exception {
        Participant expectedMatch = setId(3, createParticipant("Foo", "B"));
        expectedMatch.setDateOfBirth(new Date());  // set not null so we can be sure it isn't copied
        expect(participantDao.getBySubnamesJoinOnIdentifier(aryEq(new String[] { "foo" })))
            .andReturn(Arrays.asList(expectedMatch));

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("foo", null);
        verifyMocks();

        assertEquals("Wrong number of results", 1, actualList.size());
        Participant actualMatch = actualList.get(0);
        assertNotSame("Returned match is not copy", expectedMatch, actualMatch);
        assertEquals("id not copied", 3, (int) actualMatch.getId());
        assertEquals("first not copied", "Foo", actualMatch.getFirstName());
        assertEquals("last not copied", "B", actualMatch.getLastName());
        assertNull("other field incorrectly copied", actualMatch.getDateOfBirth());
    }

    public void testMatchParticipantsMultipleSubnames() throws Exception {
        Participant expectedMatch = setId(5, new Participant());
        expect(participantDao.getBySubnamesJoinOnIdentifier(aryEq(new String[] { "foo", "zappa" })))
            .andReturn(Arrays.asList(expectedMatch));

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("foo zappa", null);
        verifyMocks();

        assertEquals("Result not forwarded", 1, actualList.size());
    }

    // No Testing needed here cause these tests are covered under DAO testing
    public void testMatchParticipantsFiltersByStudyId() throws Exception {
        List<Participant> expectedList = new ArrayList<Participant>();
        expectedList.add(createParticipant("Joe", "Two"));
        Study study = setId(4, new Study());
        assignParticipant(expectedList.get(0), study, new Organization());

        expect(participantDao.matchParticipantByStudy(4, "oe"))
        .andReturn(expectedList);

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("oe", 4);
        verifyMocks();

        assertEquals("Wrong number of participants returned", 1, actualList.size());
        assertEquals("Wrong participant included", "Two", actualList.get(0).getLastName());
    }

    public void testMatchParticipantsWithBlankToken() throws Exception {
        expect(participantDao.getBySubnamesJoinOnIdentifier(aryEq(new String[] { })))
            .andReturn(Collections.<Participant>emptyList());

        replayMocks();
        List<Participant> actualList = facade.matchParticipants(" ", null);
        verifyMocks();

        assertEquals("Should return nothing", 0, actualList.size());
    }

    public void testMatchStudies() throws Exception {
        Study expectedMatch = setId(22, createStudy("Jim's Study"));
        expect(studyDao.getBySubnamesJoinOnIdentifier(aryEq(new String[] { "jim" })))
            .andReturn(Arrays.asList(expectedMatch));

        replayMocks();
        List<Study> actualList = facade.matchStudies("jim", null);
        verifyMocks();

        assertEquals("Result not forwarded", 1, actualList.size());
        Study actualMatch = actualList.get(0);
        assertNotSame("Returned match is not copy", expectedMatch, actualMatch);
        assertEquals("id not copied", 22, (int) actualMatch.getId());
        assertEquals("shortTitle not copied", "Jim's Study", actualMatch.getShortTitle());
        assertNull("extra field incorrectly copied", actualMatch.getLongTitle());
    }

    public void testMatchStudiesMultipleSubnames() throws Exception {
        Study expectedMatch = setId(22, createStudy("Jim's Study"));
        expect(studyDao.getBySubnamesJoinOnIdentifier(aryEq(new String[] { "jules", "jim" })))
            .andReturn(Arrays.asList(expectedMatch));

        replayMocks();
        List<Study> actualList = facade.matchStudies("jules jim", null);
        verifyMocks();

        assertEquals("Result not forwarded", 1, actualList.size());
    }

    public void testMatchLabTestNames() throws Exception{
    	List<Lov> labNames = facade.matchLabTestNames("Chloride");
    	assertTrue("There should be at least one lab name containing 'Chloride'", labNames.size() > 0);
    	for(Lov lov : labNames){
    		assertContains("The lab test name should contain 'Chloride'", lov.getDesc(), "Chloride");
    	}
    }

    public void testMatchTreatmentAssignment(){
    	List<TreatmentAssignment> expected = new ArrayList<TreatmentAssignment>();
    	TreatmentAssignment a1 = new TreatmentAssignment();
    	a1.setCode("TAC1");
    	a1.setDescription("description");
    	a1.setId(-11);
    	expected.add(a1);
    	expect(treatmentAssignmentDao.getAssignmentsByStudyId("TAC", 1)).andReturn(expected);
    	replayMocks();
    	List<TreatmentAssignment> actualList = facade.matchTreatmentAssignment("TAC", 1);
    	verifyMocks();
    	assertEquals("Treatment assignments size not matching", 1, actualList.size());

    }
    //  No Testing needed here cause these tests are covered under DAO testing
    public void testMatchStudiesFiltersByParticipantId() throws Exception {
        List<Study> expectedList = new ArrayList<Study>();
        expectedList.add(createStudy("Joyful"));
        Participant p = setId(7, createParticipant("Sad", "Man"));
        assignParticipant(p, expectedList.get(0), new Organization());

        expect(studyDao.matchStudyByParticipant(7, "y"))
        .andReturn(expectedList);

        replayMocks();
        List<Study> actualList = facade.matchStudies("y", 7);
        verifyMocks();

        assertEquals("Wrong number of studies returned", 1, actualList.size());
        assertEquals("Wrong study included", "Joyful", actualList.get(0).getShortTitle());
    }

    public void testMatchTermsNoCategory() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[] { "what" }), eq(12), (Integer) isNull()))
            .andReturn(expected);

        replayMocks();
        List<CtcTerm> actual = facade.matchTerms("what", 12, null, 10);
        verifyMocks();

        assertSame("Wrong list", expected, actual);
    }

    public void testMatchTermsWithCategory() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[] { "what" }), eq(12), eq(7)))
            .andReturn(expected);

        replayMocks();
        List<CtcTerm> actual = facade.matchTerms("what", 12, 7, 10);
        verifyMocks();

        assertSame("Wrong list", expected, actual);
    }

    public void testMatchTermsMultipleSubnames() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[] { "what", "happ" }), eq(2), eq(205)))
            .andReturn(expected);

        replayMocks();
        List<CtcTerm> actual = facade.matchTerms("what happ", 2, 205, 10);
        verifyMocks();

        assertSame("Wrong list", expected, actual);
    }

    public void testGetCategories() throws Exception {
        int expectedId = 55;
        Ctc ctc = setId(expectedId, new Ctc());
        ctc.setCategories(new ArrayList<CtcCategory>());
        expect(ctcDao.getById(expectedId)).andReturn(ctc);

        replayMocks();
        List<CtcCategory> actual = facade.getCategories(expectedId);
        verifyMocks();

        assertSame("Wrong list", ctc.getCategories(), actual);
    }

    public void testPushToPsc() throws Exception {
        int expectedId = 510;
        ExpeditedAdverseEventReport report = setId(expectedId, new ExpeditedAdverseEventReport());
        expect(aeReportDao.getById(510)).andReturn(report);
        interoperationService.pushToStudyCalendar(report);

        replayMocks();
        assertTrue(facade.pushAdverseEventToStudyCalendar(expectedId));
        verifyMocks();
    }

    public void testPushToPscAndFail() throws Exception {
        int expectedId = 510;
        ExpeditedAdverseEventReport report = setId(expectedId, new ExpeditedAdverseEventReport());
        expect(aeReportDao.getById(510)).andReturn(report);
        interoperationService.pushToStudyCalendar(report);
        expectLastCall().andThrow(new CaaersSystemException("Turbo bad"));

        replayMocks();
        assertFalse(facade.pushAdverseEventToStudyCalendar(expectedId));
        verifyMocks();
    }

    public void testPushToPscAndFailWithArbitraryException() throws Exception {
        int expectedId = 510;
        ExpeditedAdverseEventReport report = setId(expectedId, new ExpeditedAdverseEventReport());
        expect(aeReportDao.getById(510)).andReturn(report);
        interoperationService.pushToStudyCalendar(report);
        expectLastCall().andThrow(new RuntimeException("Turbo bad"));

        replayMocks();
        assertFalse(facade.pushAdverseEventToStudyCalendar(expectedId));
        verifyMocks();
    }

    public void testAddConcomitantMedications() throws Exception {
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
            .andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addFormSection("conMed", 4, 12));
        verifyMocks();
    }

    public void testAddConcomitantMedicationsNoReport() throws Exception {
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/create");
        expect(webContext.forwardToString("/pages/ae/create?index=4&subview=conMedFormSection"))
            .andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addFormSection("conMed", 4, null));
        verifyMocks();
    }

    public void testAddConcomitantMedicationsRemovesContextPath() throws Exception {
        request.setContextPath("/caaers");
        expect(webContext.getCurrentPage()).andReturn("/caaers/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
            .andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addFormSection("conMed", 4, 12));
        verifyMocks();
    }

    public void testAddConcomitantMedicationsDoesNotRemoveContextPathIfNotThere() throws Exception {
        request.setContextPath("/caaers");
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
            .andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addFormSection("conMed", 4, 12));
        verifyMocks();
    }

    public void testGetGradesForEnumGrades() throws Exception {
        CtcTerm term = registerMockFor(CtcTerm.class);
        expect(ctcTermDao.getById(5)).andReturn(term);
        expect(term.getGrades()).andReturn(Arrays.<CodedGrade>asList(Grade.SEVERE));
        replayMocks();

        List<? extends CodedGrade> actual = facade.getTermGrades(5);
        verifyMocks();
        assertEquals(1, actual.size());
        assertEquals(3, (int) actual.get(0).getCode());
        assertEquals("Severe", actual.get(0).getDisplayName());
    }



    public void testGetGradesForCtcGrades() throws Exception {
        CtcTerm term = registerMockFor(CtcTerm.class);
        expect(ctcTermDao.getById(5)).andReturn(term);
        CtcGrade grade = new CtcGrade();
        grade.setText("Oh, so severe");
        grade.setGrade(Grade.SEVERE);
        grade.setTerm(term);
        expect(term.getGrades()).andReturn(Arrays.<CodedGrade>asList(grade));
        replayMocks();

        List<? extends CodedGrade> actual = facade.getTermGrades(5);
        verifyMocks();
        assertEquals(1, actual.size());
        assertEquals(3, (int) actual.get(0).getCode());
        assertEquals("Oh, so severe", actual.get(0).getDisplayName());
    }

    public void testReorderAdverseEventsWithValidParams() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 1, 2);
        verifyMocks();

        assertAdverseEventsIdOrder(command, 0, 2, 1, 3);
        assertEquals("Wrong changes: " + actual, 2, actual.size());
        assertIndexChange(1, 2, actual.get(0));
        assertIndexChange(2, 1, actual.get(1));
    }

    public void testReorderMoveToEnd() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 0, 3);
        verifyMocks();

        assertAdverseEventsIdOrder(command, 1, 2, 3, 0);
        assertEquals("Wrong changes: " + actual, 4, actual.size());
        assertIndexChange(0, 3, actual.get(0));
        assertIndexChange(1, 0, actual.get(1));
        assertIndexChange(2, 1, actual.get(2));
        assertIndexChange(3, 2, actual.get(3));
    }

    public void testReorderMoveToBeginning() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 2, 0);
        verifyMocks();

        assertAdverseEventsIdOrder(command, 2, 0, 1, 3);
        assertEquals("Wrong changes: " + actual, 3, actual.size());
        assertIndexChange(0, 1, actual.get(0));
        assertIndexChange(1, 2, actual.get(1));
        assertIndexChange(2, 0, actual.get(2));
    }

    public void testReorderReturnsFalseWhenTargetOutOfRange() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 1, 4);
        verifyMocks();

        assertNotMoved(command);
        assertEquals(0, actual.size());
    }

    public void testReorderReturnsFalseWhenObjectOutOfRange() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 4, 1);
        verifyMocks();

        assertNotMoved(command);
        assertEquals(0, actual.size());
    }

    public void testReorderChangeListIncludesDisplayName() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();

        replayMocks();
        List<CreateAdverseEventAjaxFacade.IndexChange> actual
            = facade.reorder("aeReport.adverseEvents", 2, 0);
        verifyMocks();

        assertAdverseEventsIdOrder(command, 2, 0, 1, 3);
        assertEquals("Wrong changes: " + actual, 3, actual.size());
        assertIndexChange(0, 1, "Adverse event 2", actual.get(0));
        assertIndexChange(1, 2, "Adverse event 3", actual.get(1));
        assertIndexChange(2, 0, "Primary adverse event", actual.get(2));
    }

    private static void assertIndexChange(int expectedOriginal, int expectedCurrent, CreateAdverseEventAjaxFacade.IndexChange actual) {
        assertIndexChange(expectedOriginal, expectedCurrent, null, actual);
    }

    private static void assertIndexChange(int expectedOriginal, int expectedCurrent, String expectedDisplay, CreateAdverseEventAjaxFacade.IndexChange actual) {
        assertEquals("Wrong original", expectedOriginal, actual.getOriginal());
        assertEquals("Wrong current", expectedCurrent, actual.getCurrent());
        if (expectedDisplay != null) {
            assertEquals("Wrong displayName", expectedDisplay, actual.getCurrentDisplayName());
        }
    }

    private void assertNotMoved(ExpeditedAdverseEventInputCommand command) {
        assertAdverseEventsIdOrder(command, 0, 1, 2, 3);
    }

    private void assertAdverseEventsIdOrder(ExpeditedAdverseEventInputCommand command, int... expectedOrder) {
        for (int i = 0; i < expectedOrder.length; i++) {
            int id = expectedOrder[i];
            assertTrue("More expected ids than AEs present",
                i < command.getAeReport().getAdverseEvents().size());
            assertEquals("Wrong id at " + i, new Integer(id),
                command.getAeReport().getAdverseEvents().get(i).getId());
        }
    }

    private EditExpeditedAdverseEventCommand createAeCommandAndExpectInSession() {
        EditExpeditedAdverseEventCommand command
            = new EditExpeditedAdverseEventCommand(null, null, null);
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
        report.addAdverseEvent(setId(0, new AdverseEvent()));
        report.addAdverseEvent(setId(1, new AdverseEvent()));
        report.addAdverseEvent(setId(2, new AdverseEvent()));
        report.addAdverseEvent(setId(3, new AdverseEvent()));
        command.setAeReport(report);
        session.setAttribute(EditAdverseEventController.class.getName() + ".FORM.command", command);
        expect(webContext.getSession()).andReturn(session).anyTimes();
        return command;
    }
}
