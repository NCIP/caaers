package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT})
public class ConcomitantMedicationTest extends CaaersTestCase {
    private ConcomitantMedication medication = new ConcomitantMedication();

    public void testNameWithAgent() throws Exception {
        Agent agent = new Agent(); agent.setName("Jomocillin");
        medication.setAgent(agent);
        assertEquals("Wrong name", "Jomocillin", medication.getName());
    }

    public void testNameWithOther() throws Exception {
        medication.setOther("Jomocillin");
        assertEquals("Wrong name", "Other: Jomocillin", medication.getName());
    }
}
