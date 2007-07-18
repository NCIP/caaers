package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class SurgeryInterventionTabTest extends AeTabTestCase {
    @Override
    protected SurgeryInterventionTab createTab() {
        return new SurgeryInterventionTab();
    }

    public void testFields() throws Exception {
        assertFieldProperties("desc",
            "aeReport.surgeryIntervention.treatmentArm",
            "aeReport.surgeryIntervention.description",
            "aeReport.surgeryIntervention.anatomicSite",
            "aeReport.surgeryIntervention.interventionDate"
        );
    }
}
