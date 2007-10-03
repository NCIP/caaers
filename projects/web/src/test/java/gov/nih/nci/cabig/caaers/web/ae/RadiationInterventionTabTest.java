package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class RadiationInterventionTabTest extends AeTabTestCase {
    @Override
    protected RadiationInterventionTab createTab() {
        return new RadiationInterventionTab();
    }
    
    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "radiationIntervention6",
            "aeReport.radiationInterventions[6].treatmentArm",
            "aeReport.radiationInterventions[6].description",
            "aeReport.radiationInterventions[6].administration",
            "aeReport.radiationInterventions[6].dosage",
            "aeReport.radiationInterventions[6].dosageUnit",
            "aeReport.radiationInterventions[6].lastTreatmentDate",
            "aeReport.radiationInterventions[6].fractionNumber",
            "aeReport.radiationInterventions[6].daysElapsed",
            "aeReport.radiationInterventions[6].adjustment"
        );
    }
}