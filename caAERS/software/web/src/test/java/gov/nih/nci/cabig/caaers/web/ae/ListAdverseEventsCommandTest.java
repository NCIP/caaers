package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.EvaluationService;

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

        command = new ListAdverseEventsCommand(evaluationService);
    }

    public void testExplicitStudyTrumps() throws Exception {
        Study expected = new LocalStudy();
        command.setStudy(expected);

        replayMocks();
        assertSame(expected, command.getStudy());
        verifyMocks();
    }



    public void testExplicitParticipantTrumps() throws Exception {
        Participant expected = new Participant();
        command.setParticipant(expected);

        replayMocks();
        assertSame(expected, command.getParticipant());
        verifyMocks();
    }

  
}
