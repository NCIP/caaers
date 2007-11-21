package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.AdverseEventPreExistingCond;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;

import org.springframework.validation.ObjectError;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class PreExistingConditionTabTest extends AeTabTestCase {

    @Override
    protected PreExistingConditionsTab createTab() {
        return new PreExistingConditionsTab();
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addAdverseEventPreExistingCond(new AdverseEventPreExistingCond());
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "conmed7",
            "aeReport.adverseEventPreExistingConds[7].preExistingCondition",
            "aeReport.adverseEventPreExistingConds[7].other"
        );
    }

    public void testPreExistingCondWithCondition() throws Exception {
        command.getAeReport().getAdverseEventPreExistingConds().get(0).setPreExistingCondition(new PreExistingCondition());
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }

    public void testPreExistingCondWithValidOther() throws Exception {
        command.getAeReport().getAdverseEventPreExistingConds().get(0).setOther("Headache");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }
    
    public void testEitherConditionOrOtherRequired() throws Exception {
        command.getAeReport().getAdverseEventPreExistingConds().get(0).setPreExistingCondition(null);
        command.getAeReport().getAdverseEventPreExistingConds().get(0).setOther(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        ObjectError fieldError = getErrors().getFieldError("aeReport.adverseEventPreExistingConds[0]");
        assertNotNull(fieldError);
        assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong message", "Either a known pre Existing Condition or other is required",
            fieldError.getDefaultMessage());
    }
}
