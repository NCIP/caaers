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

    public void testFields() throws Exception {
        assertFieldProperties("desc",
            "aeReport.radiationIntervention.treatmentArm",
            "aeReport.radiationIntervention.description",
            "aeReport.radiationIntervention.administration",
            "aeReport.radiationIntervention.dosage",
            "aeReport.radiationIntervention.dosageUnit",
            "aeReport.radiationIntervention.lastTreatmentDate",
            "aeReport.radiationIntervention.fractionNumber",
            "aeReport.radiationIntervention.daysElapsed",
            "aeReport.radiationIntervention.adjustment"
            
        );
    }
}