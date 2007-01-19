package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class BeginTabTest extends AeTabTestCase {
    private BeginTab tab = new BeginTab();

    public void testStudyRequired() throws Exception {
        command.setStudy(null);
        doValidate();
        assertEquals(1, errors.getErrorCount());
        assertEquals(1, errors.getFieldErrorCount("study"));
    }

    public void testParticipantRequired() throws Exception {
        command.setParticipant(null);
        doValidate();
        assertEquals(1, errors.getErrorCount());
        assertEquals(1, errors.getFieldErrorCount("participant"));
    }
    
    public void testAssignmentRequired() throws Exception {
        expectGetAssignment(null);
        doValidate();
        assertEquals(1, errors.getErrorCount());
        assertEquals(1, errors.getGlobalErrorCount());
        assertEquals("NO_ASSIGNMENT", errors.getGlobalError().getCode());
    }

    private void doValidate() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
    }
}
