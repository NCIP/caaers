package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
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

    ConcomitantMedication concomitantMedication;


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


        concomitantMedication = new ConcomitantMedication();
        concomitantMedication.setId(1);
        concomitantMedication.setGridId("grid id");
        concomitantMedication.setVersion(2);
        concomitantMedication.setAgentName(agentName);
        concomitantMedication.setStillTakingMedications(Boolean.TRUE);
        concomitantMedication.setEndDate(endDate);
        concomitantMedication.setStartDate(startDate);

        concomitantMedication.setReport(new ExpeditedAdverseEventReport());

    }

    public void testCreateConcomitantMedicationForBasicProperties() {

        ConcomitantMedication concomitantMedication = ConcomitantMedication.
                createConcomitantMedication(studyParticipantConcomitantMedication);

        validateConcomitantMedication(concomitantMedication);


    }

    public void testCopy() {

        ConcomitantMedication copiedConcomitantMedication = concomitantMedication.copy();

        validateConcomitantMedication(copiedConcomitantMedication);


    }

    private void validateConcomitantMedication(ConcomitantMedication copiedConcomitantMedication) {
        assertNotNull(copiedConcomitantMedication);

        assertNull("must not copy id ", copiedConcomitantMedication.getId());
        assertNull("must not copy grid id ", copiedConcomitantMedication.getGridId());
        assertNull("must not copy version no ", copiedConcomitantMedication.getVersion());
        assertNull("must not copy report ", copiedConcomitantMedication.getReport());

        assertEquals(startDate, copiedConcomitantMedication.getStartDate());
        assertEquals(endDate, copiedConcomitantMedication.getEndDate());
        assertTrue(copiedConcomitantMedication.getStillTakingMedications());
        assertEquals(copiedConcomitantMedication.getName(), copiedConcomitantMedication.getName());
        assertEquals(agentName, copiedConcomitantMedication.getAgentName());
    }

}
