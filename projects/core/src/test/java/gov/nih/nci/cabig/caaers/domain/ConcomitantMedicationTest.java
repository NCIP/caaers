package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT})
public class ConcomitantMedicationTest extends CaaersTestCase {
    private ConcomitantMedication medication = new ConcomitantMedication();

    public void testNameWithAgent() throws Exception {
        medication.setAgentName("Jomocillin");
        assertEquals("Wrong name", "Jomocillin", medication.getName());
    }

}
