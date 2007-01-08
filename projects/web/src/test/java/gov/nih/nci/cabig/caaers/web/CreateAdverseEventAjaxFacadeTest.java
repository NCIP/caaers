package gov.nih.nci.cabig.caaers.web;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.*;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import static org.easymock.classextension.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventAjaxFacadeTest extends CaaersTestCase {
    private CreateAdverseEventAjaxFacade facade;
    private StudyDao studyDao;
    private ParticipantDao participantDao;

    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);

        facade = new CreateAdverseEventAjaxFacade();
        facade.setParticipantDao(participantDao);
        facade.setStudyDao(studyDao);
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

}
