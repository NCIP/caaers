package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Biju Joseph
 */
public class SAEReportPriorTherapyTest extends AbstractTestCase {


    private StudyParticipantPriorTherapy studyParticipantPriorTherapy;
    private DateValue endDateValue;
    private PriorTherapy priorTherapy;
    private PriorTherapy priorTherapy2;
    private String other;
    private DateValue startDateValue;

    private SAEReportPriorTherapy saeReportPriorTherapy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        studyParticipantPriorTherapy = new StudyParticipantPriorTherapy();
        studyParticipantPriorTherapy.setId(1);
        studyParticipantPriorTherapy.setGridId("grid id");
        studyParticipantPriorTherapy.setVersion(2);
        startDateValue = new DateValue(2008);
        studyParticipantPriorTherapy.setStartDate(startDateValue);
        endDateValue = new DateValue(2009);
        studyParticipantPriorTherapy.setEndDate(endDateValue);
        priorTherapy = new PriorTherapy();
        studyParticipantPriorTherapy.setPriorTherapy(priorTherapy);
        other = "other";
        studyParticipantPriorTherapy.setOther(other);
        studyParticipantPriorTherapy.setAssignment(new StudyParticipantAssignment());
        studyParticipantPriorTherapy.addPriorTherapyAgent(new StudyParticipantPriorTherapyAgent());

        saeReportPriorTherapy = new SAEReportPriorTherapy();
        saeReportPriorTherapy.setId(1);
        saeReportPriorTherapy.setGridId("grid id");
        saeReportPriorTherapy.setVersion(2);
        startDateValue = new DateValue(2008);
        saeReportPriorTherapy.setStartDate(startDateValue);
        endDateValue = new DateValue(2009);
        saeReportPriorTherapy.setEndDate(endDateValue);
        priorTherapy2 = new PriorTherapy();
        saeReportPriorTherapy.setPriorTherapy(priorTherapy2);
        other = "other";
        saeReportPriorTherapy.setOther(other);
        saeReportPriorTherapy.setReport(new ExpeditedAdverseEventReport());
        saeReportPriorTherapy.addPriorTherapyAgent(new PriorTherapyAgent());

    }


    public void testCopySAEReportPriorTherapyForBasicProperties() {

        SAEReportPriorTherapy saeReportPriorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);

        assertNotNull(saeReportPriorTherapy);

        assertNull("must not copy id ", saeReportPriorTherapy.getId());
        assertNull("must not copy grid id ", saeReportPriorTherapy.getGridId());
        assertNull("must not copy version no ", saeReportPriorTherapy.getVersion());
        assertNull("must not copy report ", saeReportPriorTherapy.getReport());

        assertEquals(endDateValue, saeReportPriorTherapy.getEndDate());

        assertEquals(priorTherapy, saeReportPriorTherapy.getPriorTherapy());

        assertSame(priorTherapy, saeReportPriorTherapy.getPriorTherapy());
        assertEquals(saeReportPriorTherapy.getName(), saeReportPriorTherapy.getName());
        assertEquals(other, saeReportPriorTherapy.getOther());
        assertEquals(startDateValue, saeReportPriorTherapy.getStartDate());


    }

    public void testCreateSAEReportPriorTherapyForBasicProperties() {

        SAEReportPriorTherapy saeReportPriorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);

        assertNotNull(saeReportPriorTherapy);

        assertNull("must not copy id ", saeReportPriorTherapy.getId());
        assertNull("must not copy grid id ", saeReportPriorTherapy.getGridId());
        assertNull("must not copy version no ", saeReportPriorTherapy.getVersion());
        assertNull("must not copy report ", saeReportPriorTherapy.getReport());

        assertEquals(endDateValue, saeReportPriorTherapy.getEndDate());

        assertEquals(priorTherapy, saeReportPriorTherapy.getPriorTherapy());

        assertSame(priorTherapy, saeReportPriorTherapy.getPriorTherapy());
        assertEquals(saeReportPriorTherapy.getName(), saeReportPriorTherapy.getName());
        assertEquals(other, saeReportPriorTherapy.getOther());
        assertEquals(startDateValue, saeReportPriorTherapy.getStartDate());


    }

    //will copy if chemo-agent is not null
    public void testCreateSAEReportPriorTherapyForAgent() {
        studyParticipantPriorTherapy.getPriorTherapyAgents().get(0).setChemoAgent(new ChemoAgent());
        SAEReportPriorTherapy saeReportPriorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);

        assertNotNull(saeReportPriorTherapy.getPriorTherapyAgents().size());

        assertEquals(this.studyParticipantPriorTherapy.getPriorTherapyAgents().size(), saeReportPriorTherapy.getPriorTherapyAgents().size());
        assertEquals(this.studyParticipantPriorTherapy.getPriorTherapyAgents().size(), saeReportPriorTherapy.getPriorTherapyAgentsInternal().size());

        for (PriorTherapyAgent priorTherapyAgent : saeReportPriorTherapy.getPriorTherapyAgents()) {
            assertSame(saeReportPriorTherapy, priorTherapyAgent.getSaeReportPriorTherapy());
        }
        for (PriorTherapyAgent studyParticipantPriorTherapyAgent : saeReportPriorTherapy.getPriorTherapyAgentsInternal()) {
            assertSame(saeReportPriorTherapy, studyParticipantPriorTherapyAgent.getSaeReportPriorTherapy());
        }


    }

    //will not copy if chemo-agent is  null
    public void testCreateSAEReportPriorTherapyForAgentWhileChemoAgentIsNull() {
        SAEReportPriorTherapy saeReportPriorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);

        assertEquals(0, saeReportPriorTherapy.getPriorTherapyAgents().size());


    }
}