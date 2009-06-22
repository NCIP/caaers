package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class StudyParticipantPriorTherapyTest extends AbstractTestCase {


    private SAEReportPriorTherapy saeReportPriorTherapy;
    private DateValue endDateValue;
    private PriorTherapy priorTherapy;
    private String other;
    private DateValue startDateValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        saeReportPriorTherapy = new SAEReportPriorTherapy();
        saeReportPriorTherapy.setId(1);
        saeReportPriorTherapy.setGridId("grid id");
        saeReportPriorTherapy.setVersion(2);
        startDateValue = new DateValue(2008);
        saeReportPriorTherapy.setStartDate(startDateValue);
        endDateValue = new DateValue(2009);
        saeReportPriorTherapy.setEndDate(endDateValue);
        priorTherapy = new PriorTherapy();
        saeReportPriorTherapy.setPriorTherapy(priorTherapy);
        other = "other";
        saeReportPriorTherapy.setOther(other);
        saeReportPriorTherapy.setReport(new ExpeditedAdverseEventReport());
        saeReportPriorTherapy.addPriorTherapyAgent(new PriorTherapyAgent());

    }

    public void testCreateAssignmentPriorTherapyForBasicProperties() {

        StudyParticipantPriorTherapy studyParticipantPriorTherapy = StudyParticipantPriorTherapy.createAssignmentPriorTherapy(saeReportPriorTherapy);

        assertNotNull(studyParticipantPriorTherapy);

        assertNull("must not copy id ", studyParticipantPriorTherapy.getId());
        assertNull("must not copy grid id ", studyParticipantPriorTherapy.getGridId());
        assertNull("must not copy version no ", studyParticipantPriorTherapy.getVersion());
        assertNull("must not copy assignment ", studyParticipantPriorTherapy.getAssignment());

        assertEquals(endDateValue, studyParticipantPriorTherapy.getEndDate());

        assertEquals(saeReportPriorTherapy.getPriorTherapy(), studyParticipantPriorTherapy.getPriorTherapy());

        assertSame(priorTherapy, studyParticipantPriorTherapy.getPriorTherapy());
        assertEquals(saeReportPriorTherapy.getName(), studyParticipantPriorTherapy.getName());
        assertEquals(other, studyParticipantPriorTherapy.getOther());
        assertEquals(startDateValue, studyParticipantPriorTherapy.getStartDate());


    }

    public void testCreateAssignmentPriorTherapyForAgent() {

        StudyParticipantPriorTherapy studyParticipantPriorTherapy = StudyParticipantPriorTherapy.createAssignmentPriorTherapy(saeReportPriorTherapy);

        assertNotNull(studyParticipantPriorTherapy.getPriorTherapyAgents().size());

        assertEquals(saeReportPriorTherapy.getPriorTherapyAgents().size(), studyParticipantPriorTherapy.getPriorTherapyAgents().size());
        assertEquals(saeReportPriorTherapy.getPriorTherapyAgents().size(), studyParticipantPriorTherapy.getPriorTherapyAgentsInternal().size());

        for (StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent : studyParticipantPriorTherapy.getPriorTherapyAgents()) {
            assertSame(studyParticipantPriorTherapy, studyParticipantPriorTherapyAgent.getPriorTherapy());
        }
        for (StudyParticipantPriorTherapyAgent studyParticipantPriorTherapyAgent : studyParticipantPriorTherapy.getPriorTherapyAgentsInternal()) {
            assertSame(studyParticipantPriorTherapy, studyParticipantPriorTherapyAgent.getPriorTherapy());
        }

    }
}
