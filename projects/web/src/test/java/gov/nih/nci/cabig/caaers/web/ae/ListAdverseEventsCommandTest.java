package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

import org.easymock.classextension.EasyMock;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class ListAdverseEventsCommandTest extends AbstractTestCase {
    private ListAdverseEventsCommand command;

    private StudyDao studyDao;

    private StudyParticipantAssignmentDao assignmentDao;

    private ParticipantDao participantDao;

    private EvaluationService evaluationService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        participantDao = registerDaoMockFor(ParticipantDao.class);
        evaluationService = registerMockFor(EvaluationService.class);

        command = new ListAdverseEventsCommand(assignmentDao, studyDao, participantDao,
                        evaluationService);
    }

    public void testExplicitStudyTrumps() throws Exception {
        Study expected = new Study();
        command.setStudy(expected);
        command.setNciIdentifier("NCI");

        replayMocks();
        assertSame(expected, command.getStudy());
        verifyMocks();
    }

    public void testGetStudyByNciIdentifier() throws Exception {
        command.setNciIdentifier("NCI");
        Identifier expectedIdentifier = new Identifier();
        expectedIdentifier.setType(null);
        expectedIdentifier.setValue("NCI");
        Study expectedStudy = new Study();

        EasyMock.expect(studyDao.getByIdentifier(matchByProperties(expectedIdentifier))).andReturn(
                        expectedStudy);
        replayMocks();
        assertSame(expectedStudy, command.getStudy());
        verifyMocks();
    }

    public void testExplicitParticipantTrumps() throws Exception {
        Participant expected = new Participant();
        command.setParticipant(expected);
        command.setMrn("Merv");

        replayMocks();
        assertSame(expected, command.getParticipant());
        verifyMocks();
    }

    public void testGetParticipantByMrn() throws Exception {
        command.setMrn("MRGR");
        Identifier expectedIdentifier = new Identifier();
        expectedIdentifier.setType(ListAdverseEventsCommand.MRN_IDENTIFIER_TYPE);
        expectedIdentifier.setValue("MRGR");
        Participant expectedParticipant = new Participant();

        EasyMock.expect(participantDao.getByIdentifier(matchByProperties(expectedIdentifier)))
                        .andReturn(expectedParticipant);
        replayMocks();
        assertSame(expectedParticipant, command.getParticipant());
        verifyMocks();
    }
}
