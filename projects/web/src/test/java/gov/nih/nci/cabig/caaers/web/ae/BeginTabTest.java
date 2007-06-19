package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Rhett Sutphin
 */
public class BeginTabTest extends AeWebTestCase {
    private BeginTab<ExpeditedAdverseEventInputCommand> tab;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        tab = new BeginTab<ExpeditedAdverseEventInputCommand>();
    }

    @Override
    protected CreateExpeditedAdverseEventCommand createCommand() {
        return createMinimallyValidMockCommand();
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
        assignment = null;
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        assertEquals(1, getErrors().getGlobalErrorCount());
        assertEquals("NO_ASSIGNMENT", getErrors().getGlobalError().getCode());
    }

    protected void doValidate() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
    }
}
