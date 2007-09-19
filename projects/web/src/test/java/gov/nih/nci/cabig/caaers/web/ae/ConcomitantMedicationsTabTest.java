package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.*;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import org.springframework.validation.ObjectError;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class ConcomitantMedicationsTabTest extends AeTabTestCase {
    @Override
    protected ConcomitantMedicationsTab createTab() {
        return new ConcomitantMedicationsTab();
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addConcomitantMedication(new ConcomitantMedication());
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "conmed7",
            "aeReport.concomitantMedications[7].agentName"
        );
    }

    public void testConcomitantMedicationValidWithAgentName() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setAgentName("agentName");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }



    public void testAgentNameRequired() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setAgentName(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        ObjectError fieldError = getErrors().getFieldError("aeReport.concomitantMedications[0].agentName");
        assertNotNull(fieldError);
        assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong message", "Missing Agent name",
            fieldError.getDefaultMessage());
    }
}
