package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class SurgeryInterventionTabTest extends AeTabTestCase {
	
    @Override
    protected SurgeryInterventionTab createTab() {
        return new SurgeryInterventionTab();
    }
    
    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addSurgeryIntervention(new SurgeryIntervention());
    }
    
    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "surgeryIntervention7",
            "aeReport.surgeryInterventions[7].treatmentArm",
            "aeReport.surgeryInterventions[7].description",
            "aeReport.surgeryInterventions[7].interventionSite",
            "aeReport.surgeryInterventions[7].interventionDate"
        );
    }
}
