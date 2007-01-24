package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.service.InteroperationService;
import gov.nih.nci.cabig.caaers.web.ae.CreateAdverseEventAjaxFacade;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.CaaersDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import static org.easymock.classextension.EasyMock.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacadeTest extends CaaersTestCase {
    private CreateAdverseEventAjaxFacade facade;
    private StudyDao studyDao;
    private ParticipantDao participantDao;
    private CtcDao ctcDao;
    private CtcTermDao ctcTermDao;
    private AdverseEventReportDao aeReportDao;
    private InteroperationService interoperationService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        ctcTermDao = registerDaoMockFor(CtcTermDao.class);
        aeReportDao = registerDaoMockFor(AdverseEventReportDao.class);
        interoperationService = registerMockFor(InteroperationService.class);

        facade = new CreateAdverseEventAjaxFacade();
        facade.setParticipantDao(participantDao);
        facade.setStudyDao(studyDao);
        facade.setCtcDao(ctcDao);
        facade.setCtcTermDao(ctcTermDao);
        facade.setAeReportDao(aeReportDao);
        facade.setInteroperationService(interoperationService);
    }

    public void testMatchParticipants() throws Exception {
        List<Participant> expectedList = new ArrayList<Participant>();
        expect(participantDao.getBySubnames(aryEq(new String[] { "foo" })))
            .andReturn(expectedList);

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("foo", null);
        verifyMocks();

        assertSame("Result not forwarded", expectedList, actualList);
    }
    
    public void testMatchParticipantsMultipleSubnames() throws Exception {
        List<Participant> expectedList = new ArrayList<Participant>();
        expect(participantDao.getBySubnames(aryEq(new String[] { "foo", "zappa" })))
            .andReturn(expectedList);

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("foo zappa", null);
        verifyMocks();

        assertSame("Result not forwarded", expectedList, actualList);
    }

    public void testMatchParticipantsFiltersByStudyId() throws Exception {
        List<Participant> expectedList = new ArrayList<Participant>();
        expectedList.add(createParticipant("Joe", "One"));
        expectedList.add(createParticipant("Joe", "Two"));
        Study study = setId(4, new Study());
        assignParticipant(expectedList.get(1), study, new Site());

        expect(participantDao.getBySubnames(aryEq(new String[] { "joe" })))
            .andReturn(expectedList);

        replayMocks();
        List<Participant> actualList = facade.matchParticipants("joe", 4);
        verifyMocks();

        assertEquals("Wrong number of participants returned", 1, actualList.size());
        assertEquals("Wrong participant included", "Two", actualList.get(0).getLastName());
    }

    public void testMatchStudies() throws Exception {
        List<Study> expectedList = new ArrayList<Study>();
        expect(studyDao.getBySubnames(aryEq(new String[] { "jim" })))
            .andReturn(expectedList);

        replayMocks();
        List<Study> actualList = facade.matchStudies("jim", null);
        verifyMocks();

        assertSame("Result not forwarded", expectedList, actualList);
    }

    public void testMatchStudiesMultipleSubnames() throws Exception {
        List<Study> expectedList = new ArrayList<Study>();
        expect(studyDao.getBySubnames(aryEq(new String[] { "jules", "jim" })))
            .andReturn(expectedList);

        replayMocks();
        List<Study> actualList = facade.matchStudies("jules jim", null);
        verifyMocks();

        assertSame("Result not forwarded", expectedList, actualList);
    }

    public void testMatchStudiesFiltersByParticipantId() throws Exception {
        List<Study> expectedList = new ArrayList<Study>();
        expectedList.add(createStudy("Happy"));
        expectedList.add(createStudy("Joyful"));
        Participant p = setId(7, createParticipant("Sad", "Man"));
        assignParticipant(p, expectedList.get(1), new Site());

        expect(studyDao.getBySubnames(aryEq(new String[] { "y" })))
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
        AdverseEventReport report = setId(expectedId, new AdverseEventReport());
        expect(aeReportDao.getById(510)).andReturn(report);
        interoperationService.pushToStudyCalendar(report);

        replayMocks();
        assertTrue(facade.pushAdverseEventToStudyCalendar(expectedId));
        verifyMocks();
    }

    public void testPushToPscAndFail() throws Exception {
        int expectedId = 510;
        AdverseEventReport report = setId(expectedId, new AdverseEventReport());
        expect(aeReportDao.getById(510)).andReturn(report);
        interoperationService.pushToStudyCalendar(report);
        expectLastCall().andThrow(new CaaersSystemException("Turbo bad"));

        replayMocks();
        assertFalse(facade.pushAdverseEventToStudyCalendar(expectedId));
        verifyMocks();
    }
}
