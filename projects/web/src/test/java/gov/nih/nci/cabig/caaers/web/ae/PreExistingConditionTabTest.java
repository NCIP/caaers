package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;

import java.util.List;
import java.util.ArrayList;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;

import org.springframework.validation.ObjectError;

/**
 * @author Krikor Krumlian
 */
@CaaersUseCases({ CREATE_EXPEDITED_REPORT })
public class PreExistingConditionTabTest extends AeTabTestCase {

    @Override
    protected PreExistingConditionsTab createTab() {
    	PreExistingConditionsTab pct = new PreExistingConditionsTab();
    	pct.setPreExistingConditionDao(new PreExistingConditionDao(){
    		@Override
    		 public List<PreExistingCondition> getAll() {
    		        return new ArrayList<PreExistingCondition>();
    		    }    
    	});
        return pct;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties(
            "conmed7",
            "aeReport.saeReportPreExistingConditions[7].preExistingCondition",
            "aeReport.saeReportPreExistingConditions[7].other"
        );
    }

    public void testPreExistingCondWithCondition() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setPreExistingCondition(new PreExistingCondition());
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }

    public void testPreExistingCondWithValidOther() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setOther("Headache");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }
    
    public void testEitherConditionOrOtherRequired() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setPreExistingCondition(null);
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setOther(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        ObjectError fieldError = getErrors().getFieldError("aeReport.saeReportPreExistingConditions[0]");
        assertNotNull(fieldError);
        assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong message", "Either a known pre Existing Condition or other is required",
            fieldError.getDefaultMessage());
    }
}
