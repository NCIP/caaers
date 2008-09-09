package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class PriorTherapyAgentTest extends AbstractTestCase {

    private StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent;
    private ChemoAgent agent;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studyParticipantPriorTherapyAgent = new StudyParticipantPriorTherapyAgent();
        studyParticipantPriorTherapyAgent.setGridId("grid id");
        agent = new ChemoAgent();
        studyParticipantPriorTherapyAgent.setChemoAgent(agent);

        studyParticipantPriorTherapyAgent.setPriorTherapy(new StudyParticipantPriorTherapy());
        studyParticipantPriorTherapyAgent.setVersion(2);


    }

    public void testcreateAssignmentPriorTherapyAgent() {


        PriorTherapyAgent priorTherapyAgent = PriorTherapyAgent.
                createSaeReportPriorTherapyAgent(studyParticipantPriorTherapyAgent);

        assertNotNull(priorTherapyAgent);

        assertNull("must not copy id ", priorTherapyAgent.getId());
        assertNull("must not copy grid id ", priorTherapyAgent.getGridId());
        assertNull("must not copy version no ", priorTherapyAgent.getVersion());
        assertNull("must not copy prior Therapy ", priorTherapyAgent.getSaeReportPriorTherapy());

        assertEquals(studyParticipantPriorTherapyAgent.getName(), priorTherapyAgent.getName());

        assertEquals(studyParticipantPriorTherapyAgent.getChemoAgent(), priorTherapyAgent.getChemoAgent());

        assertSame("agent must refer to same object", agent, priorTherapyAgent.getChemoAgent());


    }
}