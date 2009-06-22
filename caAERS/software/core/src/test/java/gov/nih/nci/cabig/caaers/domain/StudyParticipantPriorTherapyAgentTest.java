package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class StudyParticipantPriorTherapyAgentTest extends AbstractTestCase {

    private PriorTherapyAgent priorTherapyAgent;
    private ChemoAgent agent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        priorTherapyAgent = new PriorTherapyAgent();
        priorTherapyAgent.setGridId("grid id");
        agent = new ChemoAgent();
        priorTherapyAgent.setChemoAgent(agent);

        priorTherapyAgent.setSaeReportPriorTherapy(new SAEReportPriorTherapy());
        priorTherapyAgent.setVersion(2);


    }

    public void testcreateAssignmentPriorTherapyAgent() {


        StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent = StudyParticipantPriorTherapyAgent.
                createAssignmentPriorTherapyAgent(priorTherapyAgent);

        assertNotNull(studyParticipantPriorTherapyAgent);

        assertNull("must not copy id ", studyParticipantPriorTherapyAgent.getId());
        assertNull("must not copy grid id ", studyParticipantPriorTherapyAgent.getGridId());
        assertNull("must not copy version no ", studyParticipantPriorTherapyAgent.getVersion());
        assertNull("must not copy prior Therapy ", studyParticipantPriorTherapyAgent.getPriorTherapy());

        assertEquals(priorTherapyAgent.getName(), studyParticipantPriorTherapyAgent.getName());

        assertEquals(priorTherapyAgent.getChemoAgent(), studyParticipantPriorTherapyAgent.getChemoAgent());

        assertSame("agent must refer to same object", agent, studyParticipantPriorTherapyAgent.getChemoAgent());


    }
}
