package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import javax.servlet.http.HttpServletRequest;

import org.easymock.classextension.EasyMock;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ListAdverseEventsControllerTest extends WebTestCase {
    private ListAdverseEventsController controller;

    private ListAdverseEventsCommand mockCommand;

    private StudyParticipantAssignmentDao assignmentDao;

    private ParticipantDao participantDao;

    private StudyDao studyDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        studyDao = registerDaoMockFor(StudyDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);

        mockCommand = registerMockFor(ListAdverseEventsCommand.class);
        controller = new ListAdverseEventsController() {
            @Override
            protected Object formBackingObject(HttpServletRequest request) throws Exception {
                return mockCommand;
            }
        };
        controller.setAssignmentDao(assignmentDao);
        controller.setStudyDao(studyDao);
        controller.setParticipantDao(participantDao);
    }

    public void testIsFormSubmissionWithAssignment() throws Exception {
        request.addParameter("assignment", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testIsFormSubmissionWithParticipantAndStudy() throws Exception {
        request.addParameter("participant", "foo");
        request.addParameter("study", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testIsFormSubmissionWithMrnAndNci() throws Exception {
        request.addParameter("mrn", "foo");
        request.addParameter("nciIdentifier", "foo");
        assertTrue(controller.isFormSubmission(request));
    }

    public void testBindAssignment() throws Exception {
        StudyParticipantAssignment expectedAssignment = Fixtures.setId(3, new StudyParticipantAssignment());
        Participant p = new Participant();
        Study s = new Study();
        String expectedGridId = "a-grid-id";
        request.setParameter("assignment", expectedGridId);
        expect(assignmentDao.getByGridId(expectedGridId)).andReturn(expectedAssignment);
        mockCommand.setAssignment(expectedAssignment);
        mockCommand.setAssignment(expectedAssignment);
        mockCommand.setParticipant(p);
        mockCommand.setStudy(s);
        mockCommand.updateSubmittability();
        expect(mockCommand.getStudy()).andReturn(s).anyTimes();
        expect(mockCommand.getParticipant()).andReturn(p).anyTimes();
        expect(mockCommand.getAssignment()).andStubReturn(expectedAssignment);
        expect(assignmentDao.getAssignment(p, s)).andReturn(expectedAssignment).anyTimes();
        replayMocks();
        controller.handleRequest(request, response);
        verifyMocks();
    }
}
