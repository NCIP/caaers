package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class BeginTabTest extends AeTabTestCase<BeginTab> {
    private BeginTab tab = new BeginTab();

    @Override
    protected BeginTab createTab() {
        return new BeginTab();
    }

    public void testStudyRequired() throws Exception {
        command.setStudy(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        assertEquals(1, getErrors().getFieldErrorCount("study"));
    }

    public void testParticipantRequired() throws Exception {
        command.setParticipant(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        assertEquals(1, getErrors().getFieldErrorCount("participant"));
    }
    
    public void testAssignmentRequired() throws Exception {
        expectGetAssignment(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        assertEquals(1, getErrors().getGlobalErrorCount());
        assertEquals("NO_ASSIGNMENT", getErrors().getGlobalError().getCode());
    }
}
