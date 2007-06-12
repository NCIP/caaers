package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Agent;
import org.springframework.validation.ObjectError;

/**
 * @author Rhett Sutphin
 */
public class ConcomitantMedicationsTabTest extends AeTabTestCase {

    @Override
    protected ConcomitantMedicationsTab createTab() {
        return new ConcomitantMedicationsTab();
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "conmed7",
            "aeReport.concomitantMedications[7].agent",
            "aeReport.concomitantMedications[7].other"
        );
    }

    public void testConcomitantMedicationValidWithAgent() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setAgent(new Agent());
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }

    public void testConcomitantMedicationValidWithOther() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setOther("Tylenol");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }
    
    public void testEitherAgentOrOtherRequired() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setAgent(null);
        command.getAeReport().getConcomitantMedications().get(0).setOther(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        ObjectError fieldError = getErrors().getFieldError("aeReport.concomitantMedications[0]");
        assertNotNull(fieldError);
        assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong message", "Either a known medication or other is required",
            fieldError.getDefaultMessage());
    }
}
