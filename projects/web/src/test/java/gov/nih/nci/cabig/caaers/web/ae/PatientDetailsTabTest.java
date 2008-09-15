package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class PatientDetailsTabTest extends AeTabTestCase {

	
	@Override
	protected AeTab createTab() {
		PatientDetailsTab pdt =  new PatientDetailsTab();
		pdt.setPreExistingConditionDao(new PreExistingConditionDao() {
            @Override
            public List<PreExistingCondition> getAll() {
                return new ArrayList<PreExistingCondition>();
            }
        });
        return pdt;
	}
	@Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        cmd.getAeReport().addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties("conmed7", "aeReport.concomitantMedications[7].agentName");
        assertFieldProperties("preExistingCondition7",
                "aeReport.saeReportPreExistingConditions[7].preExistingCondition",
                "aeReport.saeReportPreExistingConditions[7].other");
    }

    public void testConcomitantMedicationValidWithAgentName() throws Exception {
        command.getAeReport().getConcomitantMedications().get(0).setAgentName("agentName");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }
    
    public void testPreExistingCondWithCondition() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setPreExistingCondition(
                        new PreExistingCondition());
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }

    public void testPreExistingCondWithValidOther() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setOther("Headache");
        doValidate();
        assertEquals(0, getErrors().getErrorCount());
    }

    public void testEitherConditionOrOtherRequired() throws Exception {
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setPreExistingCondition(
                        null);
        command.getAeReport().getSaeReportPreExistingConditions().get(0).setOther(null);
        doValidate();
        assertEquals(1, getErrors().getErrorCount());
        ObjectError fieldError = getErrors().getFieldError(
                        "aeReport.saeReportPreExistingConditions[0]");
        assertNotNull(fieldError);
        assertEquals("Wrong code", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong message", "Either a known pre Existing Condition or other is required",
                        fieldError.getDefaultMessage());
    }
}
