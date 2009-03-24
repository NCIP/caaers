package gov.nih.nci.cabig.caaers.web;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isNull;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.CodedGrade;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportTree;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepositoryImpl;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventAjaxFacade;
import gov.nih.nci.cabig.caaers.web.ae.EditAdverseEventController;
import gov.nih.nci.cabig.caaers.web.ae.EditExpeditedAdverseEventCommand;
import gov.nih.nci.cabig.caaers.web.ae.ExpeditedAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.dwr.IndexChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class CreateAdverseEventAjaxFacadeTest extends DwrFacadeTestCase {
    private CreateAdverseEventAjaxFacade facade;

    private StudyDao studyDao;

    private ParticipantDao participantDao;

    private CtcDao ctcDao;

    private CtcTermDao ctcTermDao;
    
    private LowLevelTermDao lowLevelTermDao;

    private ExpeditedAdverseEventReportDao aeReportDao;

    private TreatmentAssignmentDao treatmentAssignmentDao;

    private InteroperationService interoperationService;

    private StudyParticipantAssignmentDao assignmentDao;

    private StudyParticipantAssignment assignment;

    private StudySite studySite;
    
    private AdverseEventReportingPeriodDao reportingPeriodDao;
    
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

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
        reportingPeriodDao = registerDaoMockFor(AdverseEventReportingPeriodDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        assignment = registerMockFor(StudyParticipantAssignment.class);
        studySite = registerMockFor(StudySite.class);
        lowLevelTermDao = registerDaoMockFor(LowLevelTermDao.class);
        adverseEventRoutingAndReviewRepository = registerMockFor(AdverseEventRoutingAndReviewRepositoryImpl.class);

        facade = new CreateAdverseEventAjaxFacade();
        facade.setParticipantDao(participantDao);
        facade.setStudyDao(studyDao);
        facade.setCtcDao(ctcDao);
        facade.setCtcTermDao(ctcTermDao);
        facade.setAeReportDao(aeReportDao);
        facade.setTreatmentAssignmentDao(treatmentAssignmentDao);
        facade.setLowLevelTermDao(lowLevelTermDao);
        facade.setInteroperationService(interoperationService);
        facade.setExpeditedReportTree(new ExpeditedReportTree());

        ConfigProperty configProperty = new ConfigProperty();
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("labUnitsRefData", new ArrayList<Lov>());
        List<Lov> list = new ArrayList<Lov>();
        list.add(new Lov("Chloride", "Chloride"));
        map.put("labTestNamesRefData", list);
        configProperty.setMap(map);
        facade.setConfigurationProperty(configProperty);
    }


    public void testMatchLabTestNames() throws Exception {
        List<Lov> labNames = facade.matchLabTestNames("Chloride");
        assertTrue("There should be at least one lab name containing 'Chloride'",
                labNames.size() > 0);
        for (Lov lov : labNames) {
            assertContains("The lab test name should contain 'Chloride'", lov.getDesc(), "Chloride");
        }
    }

    public void testMatchTreatmentAssignment() {
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

    public void testMatchTermsNoCategory() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[]{"what"}), eq(12), (Integer) isNull()))
                .andReturn(expected);

        replayMocks();
        List<CtcTerm> actual = facade.matchTerms("what", 12, null, 10);
        verifyMocks();

        assertSame("Wrong list", expected, actual);
    }

    public void testMatchTermsWithCategory() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[]{"what"}), eq(12), eq(7))).andReturn(
                expected);

        replayMocks();
        List<CtcTerm> actual = facade.matchTerms("what", 12, 7, 10);
        verifyMocks();

        assertSame("Wrong list", expected, actual);
    }

    public void testMatchTermsMultipleSubnames() throws Exception {
        List<CtcTerm> expected = new LinkedList<CtcTerm>();
        expect(ctcTermDao.getBySubname(aryEq(new String[]{"what", "happ"}), eq(2), eq(205)))
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
        expect(
                webContext
                        .forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
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
        expect(
                webContext
                        .forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
                .andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addFormSection("conMed", 4, 12));
        verifyMocks();
    }

    public void testAddConcomitantMedicationsDoesNotRemoveContextPathIfNotThere() throws Exception {
        request.setContextPath("/caaers");
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(
                webContext
                        .forwardToString("/pages/ae/edit?index=4&aeReport=12&subview=conMedFormSection"))
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
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 1, 2).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 0, 2, 1, 3);
        assertEquals("Wrong changes: " + actual, 2, actual.size());
        assertIndexChange(1, 2, actual.get(0));
        assertIndexChange(2, 1, actual.get(1));
    }

    public void testReorderMoveToEnd() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 0, 3).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 1, 2, 3, 0);
        assertEquals("Wrong changes: " + actual, 4, actual.size());
        assertIndexChange(0, 3, actual.get(0));
        assertIndexChange(1, 0, actual.get(1));
        assertIndexChange(2, 1, actual.get(2));
        assertIndexChange(3, 2, actual.get(3));
    }
    
    /**
     * Currently there is no validation for AeReport. So this method simply returns a new (empty) AjaxOutput object.
     * If there are validations to be done, this is the method where the logic should go and the unit-test needs to be updated.
     * @throws Exception
     */
    public void testValidateAndAdvanceWorkflow() throws Exception {
    	AjaxOutput output = facade.validateAndAdvanceWorkflow("testTransition");
    	assertNotNull("Null output object is returned incorrectly.", output);
    	assertNull("There no validations for aeReport workflow so null object was expected here.", output.getObjectContent());
    }

    public void testReorderMoveToBeginning() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 2, 0).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 2, 0, 1, 3);
        assertEquals("Wrong changes: " + actual, 3, actual.size());
        assertIndexChange(0, 1, actual.get(0));
        assertIndexChange(1, 2, actual.get(1));
        assertIndexChange(2, 0, actual.get(2));
    }

    public void testReorderReturnsFalseWhenTargetOutOfRange() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 1, 4).getChanges();
        verifyMocks();

        assertNotMoved(command);
        assertEquals(0, actual.size());
    }

    public void testReorderReturnsFalseWhenObjectOutOfRange() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 4, 1).getChanges();
        verifyMocks();

        assertNotMoved(command);
        assertEquals(0, actual.size());
    }

    public void testReorderChangeListIncludesDisplayName() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        assignmentDao.reassociate(command.getAeReport().getAssignment());
        replayMocks();
        List<IndexChange> actual = facade.reorder("aeReport.adverseEvents", 2, 0).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 2, 0, 1, 3);
        assertEquals("Wrong changes: " + actual, 3, actual.size());
        assertIndexChange(0, 1, "Adverse event 2", actual.get(0));
        assertIndexChange(1, 2, "Adverse event 3", actual.get(1));
        assertIndexChange(2, 0, "Primary adverse event", actual.get(2));
    }

    public void testDeleteFromEnd() throws Exception {

        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(assignment.getStudySite()).andReturn(studySite);
        expect(studySite.getStudy()).andReturn(new Study());
        AdverseEvent ae = command.getAeReport().getAdverseEvents().get(3);
        expect(aeReportDao.cascaeDeleteToAttributions(ae, command.getAeReport())).andReturn(true);
        replayMocks();
        List<IndexChange> actual = facade.remove("aeReport.adverseEvents", 3).getChanges();
        verifyMocks();
        assertAdverseEventsIdOrder(command, 0, 1, 2);
        assertEquals("Wrong changes: " + actual, 1, actual.size());
        assertIndexChange(3, null, actual.get(0));
    }

    public void testDeleteFromTop() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(assignment.getStudySite()).andReturn(studySite);
        expect(studySite.getStudy()).andReturn(new Study());
        AdverseEvent ae = command.getAeReport().getAdverseEvents().get(0);
        expect(aeReportDao.cascaeDeleteToAttributions(ae, command.getAeReport())).andReturn(true);
        replayMocks();
        List<IndexChange> actual = facade.remove("aeReport.adverseEvents", 0).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 1, 2, 3);
        assertEquals("Wrong changes: " + actual, 4, actual.size());
        assertIndexChange(0, null, actual.get(0));
        assertIndexChange(1, 0, actual.get(1));
        assertIndexChange(2, 1, actual.get(2));
        assertIndexChange(3, 2, actual.get(3));
    }

    public void testDeleteInMiddle() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(assignment.getStudySite()).andReturn(studySite);
        expect(studySite.getStudy()).andReturn(new Study());
        AdverseEvent ae = command.getAeReport().getAdverseEvents().get(2);
        expect(aeReportDao.cascaeDeleteToAttributions(ae, command.getAeReport())).andReturn(true);
        replayMocks();
        List<IndexChange> actual = facade.remove("aeReport.adverseEvents", 2).getChanges();
        verifyMocks();

        assertAdverseEventsIdOrder(command, 0, 1, 3);
        assertEquals("Wrong changes: " + actual, 2, actual.size());
        assertIndexChange(2, null, actual.get(0));
        assertIndexChange(3, 2, actual.get(1));
    }

    public void testDeleteOutOfRange() throws Exception {
        EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(assignment.getStudySite()).andReturn(studySite);
        expect(studySite.getStudy()).andReturn(new Study());

        replayMocks();
        List<IndexChange> actual = facade.remove("aeReport.adverseEvents", 4).getChanges();
        verifyMocks();

        assertNotMoved(command);
        assertEquals(0, actual.size());
    }
    
    public void testAddAdverseEventWithTermId() throws Exception{
    	EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
    	Integer aeTermId = 5;
    	 CtcTerm term = registerMockFor(CtcTerm.class);
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(ctcTermDao.getById(aeTermId)).andReturn(term);
        reportingPeriodDao.save(command.getAeReport().getReportingPeriod());
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=1&aeReport=12&subview=adverseEventFormSection")).andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addAdverseEventWithTerms( 1, 12, aeTermId));
        verifyMocks();
        
    }
    
    public void testAddNewAdverseEvent_CTCTerm() throws Exception{
    	EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
    	Integer aeTermId = 5;
    	 CtcTerm term = registerMockFor(CtcTerm.class);
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(ctcTermDao.getById(aeTermId)).andReturn(term);
        reportingPeriodDao.save(command.getAeReport().getReportingPeriod());
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=1&aeReport=12&subview=adverseEventFormSection")).andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addNewAdverseEvent("adverseEventFormSection", 1, 12, true, aeTermId));
        verifyMocks();
        
    }
    
    public void testAddNewAdverseEvent_MeddraTerm() throws Exception{
    	EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
    	Integer aeTermId = 5;
    	 LowLevelTerm term = registerMockFor(LowLevelTerm.class);
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(lowLevelTermDao.getById(aeTermId)).andReturn(term);
        reportingPeriodDao.save(command.getAeReport().getReportingPeriod());
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=1&aeReport=12&subview=adverseEventMeddraFormSection")).andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addNewAdverseEvent("adverseEventMeddraFormSection", 1, 12, false, aeTermId));
        verifyMocks();
        
    }
    /**
     * This function tests the {@link CreateAdverseEventAjaxFacade#addAdverseEventWithTermsMeddra(int, Integer, Integer)}
     */
    public void testAddAdverseEventWithTermsMeddra() throws Exception{
    	EditExpeditedAdverseEventCommand command = createAeCommandAndExpectInSession();
    	Integer aeTermId = 5;
    	 LowLevelTerm term = registerMockFor(LowLevelTerm.class);
        // mock - expectations
        assignmentDao.reassociate(assignment);
        expect(lowLevelTermDao.getById(aeTermId)).andReturn(term);
        reportingPeriodDao.save(command.getAeReport().getReportingPeriod());
        expect(webContext.getCurrentPage()).andReturn("/pages/ae/edit");
        expect(webContext.forwardToString("/pages/ae/edit?index=1&aeReport=12&subview=adverseEventMeddraFormSection")).andReturn("The HTML");

        replayMocks();
        assertEquals("The HTML", facade.addAdverseEventWithTermsMeddra( 1, 12, aeTermId));
        verifyMocks();
    }

    private static void assertIndexChange(Integer expectedOriginal, Integer expectedCurrent,
                                          IndexChange actual) {
        assertIndexChange(expectedOriginal, expectedCurrent, null, actual);
    }

    private static void assertIndexChange(Integer expectedOriginal, Integer expectedCurrent,
                                          String expectedDisplay, IndexChange actual) {
        assertEquals("Wrong original", expectedOriginal, actual.getOriginal());
        assertEquals("Wrong current", expectedCurrent, actual.getCurrent());
        if (expectedDisplay != null) {
            assertEquals("Wrong displayName", expectedDisplay, actual.getCurrentDisplayName());
        }
    }

    private void assertNotMoved(ExpeditedAdverseEventInputCommand command) {
        assertAdverseEventsIdOrder(command, 0, 1, 2, 3);
    }

    private void assertAdverseEventsIdOrder(ExpeditedAdverseEventInputCommand command,
                                            int... expectedOrder) {
        for (int i = 0; i < expectedOrder.length; i++) {
            int id = expectedOrder[i];
            assertTrue("More expected ids than AEs present", i < command.getAeReport()
                    .getAdverseEvents().size());
            assertEquals("Wrong id at " + i, new Integer(id), command.getAeReport()
                    .getAdverseEvents().get(i).getId());
        }
    }

    private EditExpeditedAdverseEventCommand createAeCommandAndExpectInSession() {

        EditExpeditedAdverseEventCommand command = new EditExpeditedAdverseEventCommand(null, null,
                assignmentDao, reportingPeriodDao, null, null, null, null, null);

        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
        report.addAdverseEvent(setId(0, new AdverseEvent()));
        report.addAdverseEvent(setId(1, new AdverseEvent()));
        report.addAdverseEvent(setId(2, new AdverseEvent()));
        report.addAdverseEvent(setId(3, new AdverseEvent()));
        AdverseEventReportingPeriod reportingPeriod = new AdverseEventReportingPeriod();
        reportingPeriod.setAssignment(assignment);
        report.setReportingPeriod(reportingPeriod);
        // This has changed to handle Many-To-One relationship between ReportingPeriod and ExpeditedReport
        // TODO: fix it when use case is ready.
        reportingPeriod.addAeReport(report);

        command.setAeReport(report);
        session.setAttribute(EditAdverseEventController.class.getName() + ".FORM.command", command);
        expect(webContext.getSession()).andReturn(session).anyTimes();
        return command;
    }
}
