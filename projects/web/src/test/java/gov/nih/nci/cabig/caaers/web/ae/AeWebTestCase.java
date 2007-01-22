package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import org.easymock.EasyMock;

/**
 * @author Rhett Sutphin
 */
public abstract class AeWebTestCase extends WebTestCase {
    protected StudyParticipantAssignmentDao assignmentDao;
    protected CreateAdverseEventCommand command;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignmentDao = registerDaoMockFor(StudyParticipantAssignmentDao.class);
        command = new CreateAdverseEventCommand(assignmentDao);
    }

    protected void expectGetAssignment(StudyParticipantAssignment assignment) {
        EasyMock.expect(assignmentDao.getAssignment(command.getParticipant(), command.getStudy()))
            .andReturn(assignment);
    }
}
