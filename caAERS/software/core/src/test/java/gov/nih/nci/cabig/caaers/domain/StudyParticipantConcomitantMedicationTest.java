package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

/**
 * @author Biju Joseph
 */
public class StudyParticipantConcomitantMedicationTest extends AbstractTestCase {


    private ConcomitantMedication saeReportConcomitantMedication;
    private String agentName;
    private DateValue endDate;
    private DateValue startDate;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        saeReportConcomitantMedication = new ConcomitantMedication();
        saeReportConcomitantMedication.setId(1);
        saeReportConcomitantMedication.setGridId("grid id");
        saeReportConcomitantMedication.setVersion(2);
        agentName = "agent name";
        saeReportConcomitantMedication.setAgentName(agentName);
        saeReportConcomitantMedication.setStillTakingMedications(Boolean.TRUE);
        saeReportConcomitantMedication.setReport(new ExpeditedAdverseEventReport());
        endDate = new DateValue();
        saeReportConcomitantMedication.setEndDate(endDate);
        startDate = new DateValue(2010);
        saeReportConcomitantMedication.setStartDate(startDate);

    }

    public void testCreateAssignmentConcomitantMedicationForBasicProperties() {

        StudyParticipantConcomitantMedication studyParticipantConcomitantMedication = StudyParticipantConcomitantMedication.
                createAssignmentConcomitantMedication(saeReportConcomitantMedication);

        assertNotNull(studyParticipantConcomitantMedication);

        assertNull("must not copy id ", studyParticipantConcomitantMedication.getId());
        assertNull("must not copy grid id ", studyParticipantConcomitantMedication.getGridId());
        assertNull("must not copy version no ", studyParticipantConcomitantMedication.getVersion());
        assertNull("must not copy assignment ", studyParticipantConcomitantMedication.getAssignment());


        assertEquals(startDate, studyParticipantConcomitantMedication.getStartDate());
        assertEquals(endDate, studyParticipantConcomitantMedication.getEndDate());
        assertTrue(studyParticipantConcomitantMedication.getStillTakingMedications());
        assertEquals(saeReportConcomitantMedication.getName(), studyParticipantConcomitantMedication.getName());
        assertEquals(agentName, studyParticipantConcomitantMedication.getAgentName());


    }


}