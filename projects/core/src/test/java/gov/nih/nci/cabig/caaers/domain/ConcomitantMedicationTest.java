package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({CREATE_EXPEDITED_REPORT})
public class ConcomitantMedicationTest extends AbstractTestCase {
    private ConcomitantMedication medication = new ConcomitantMedication();

    private StudyParticipantConcomitantMedication studyParticipantConcomitantMedication;

    private String agentName;
    private DateValue endDate;
    private DateValue startDate;


    public void testNameWithAgent() throws Exception {
        medication.setAgentName("Jomocillin");
        assertEquals("Wrong name", "Jomocillin", medication.getName());
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();


        studyParticipantConcomitantMedication = new StudyParticipantConcomitantMedication();
        studyParticipantConcomitantMedication.setId(1);
        studyParticipantConcomitantMedication.setGridId("grid id");
        studyParticipantConcomitantMedication.setVersion(2);
        agentName = "agent name";
        studyParticipantConcomitantMedication.setAgentName(agentName);
        studyParticipantConcomitantMedication.setStillTakingMedications(Boolean.TRUE);
        endDate = new DateValue();
        studyParticipantConcomitantMedication.setEndDate(endDate);
        startDate = new DateValue(2010);
        studyParticipantConcomitantMedication.setStartDate(startDate);

        studyParticipantConcomitantMedication.setAssignment(new StudyParticipantAssignment());

    }

    public void testCreateConcomitantMedicationForBasicProperties() {

        ConcomitantMedication concomitantMedication = ConcomitantMedication.
                createConcomitantMedication(studyParticipantConcomitantMedication);

        assertNotNull(concomitantMedication);

        assertNull("must not copy id ", concomitantMedication.getId());
        assertNull("must not copy grid id ", concomitantMedication.getGridId());
        assertNull("must not copy version no ", concomitantMedication.getVersion());
        assertNull("must not copy report ", concomitantMedication.getReport());

        assertEquals(startDate, concomitantMedication.getStartDate());
        assertEquals(endDate, concomitantMedication.getEndDate());
        assertTrue(concomitantMedication.getStillTakingMedications());
        assertEquals(concomitantMedication.getName(), concomitantMedication.getName());
        assertEquals(agentName, concomitantMedication.getAgentName());


    }


}
