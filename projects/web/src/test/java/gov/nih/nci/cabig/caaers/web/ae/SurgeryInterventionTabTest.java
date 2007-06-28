package gov.nih.nci.cabig.caaers.web.ae;

/**
 * @author Krikor Krumlian
 */
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
