package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;

/**
 * @author Rhett Sutphin
 */
public class CreateAdverseEventCommandTest extends AeWebTestCase {
    public void testGetAssignment() throws Exception {
        StudyParticipantAssignment expectedAssignment = new StudyParticipantAssignment();

        command.setStudy(new Study());
        command.setParticipant(new Participant());
        expectGetAssignment(expectedAssignment);

        replayMocks();
        assertSame(expectedAssignment, command.getAssignment());
        verifyMocks();
    }
    
    public void testGetAssignmentNullWhenStudyNull() throws Exception {
        command.setParticipant(new Participant());
        replayMocks();
        assertNull(command.getAssignment());
        verifyMocks();
    }

    public void testGetAssignmentNullWhenParticipantNull() throws Exception {
        command.setStudy(new Study());
        replayMocks();
        assertNull(command.getAssignment());
        verifyMocks();
    }
}
