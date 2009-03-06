package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.domain.SAEReportPreExistingCondition;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.CompositeField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.validation.ObjectError;
/**
 * 
 * @author Biju Joseph
 *
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class PatientDetailsTabTest extends AeTabTestCase {
	private ConfigProperty configurationProperty;

    @Override
    protected void setUp() throws Exception {
        // TODO: this makes me stupider for having done it
        configurationProperty = new ConfigProperty();
        configurationProperty.setMap(new HashMap<String, List<Lov>>());
        configurationProperty.getMap().put("bpsRefData", createMirroredLov("0 = zero", "1 = one", "2 = two"));
        configurationProperty.getMap().put("heightUnitsRefData", createMirroredLov("Inch", "Centimeter"));
        configurationProperty.getMap().put("weightUnitsRefData", createMirroredLov("Pound", "Kilogram"));
        
        super.setUp();
    }
    
    private List<Lov> createMirroredLov(String... values) {
        List<Lov> lov = new ArrayList<Lov>(values.length);
        for (String value : values) {
            lov.add(new Lov(value, value));
        }
        return lov;
    }
	
    @Override
	protected AeTab createTab() {
		PatientDetailsTab pdt =  new PatientDetailsTab();
		pdt.setConfigurationProperty(configurationProperty);
		pdt.setPreExistingConditionDao(new PreExistingConditionDao() {
            @Override
            public List<PreExistingCondition> getAll() {
                return new ArrayList<PreExistingCondition>();
            }
        });
		pdt.setPriorTherapyDao(new PriorTherapyDao(){
			@Override
			public List<PriorTherapy> getAll() {
				return new ArrayList<PriorTherapy>();
			}
		});
        return pdt;
	}
    
	@Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        cmd.getAeReport().addSaeReportPreExistingCondition(new SAEReportPreExistingCondition());
        cmd.getAeReport().getDiseaseHistory().addMetastaticDiseaseSite(new MetastaticDiseaseSite());
    }

    public void testFieldProperties() throws Exception {
        assertFieldProperties("conmed7", 
        		"aeReport.concomitantMedications[7].agentName",
        		"aeReport.concomitantMedications[7].stillTakingMedications",
        		"aeReport.concomitantMedications[7].startDate",
        		"aeReport.concomitantMedications[7].endDate");
        assertFieldProperties("preExistingCondition7",
                "aeReport.saeReportPreExistingConditions[7].preExistingCondition",
                "aeReport.saeReportPreExistingConditions[7].other");
    }

//    public void testConcomitantMedicationValidWithAgentName() throws Exception {
//        command.getAeReport().getConcomitantMedications().get(0).setAgentName("agentName");
//        doValidate();
//        assertEquals(0, getErrors().getErrorCount());
//    }
    
//    public void testPreExistingCondWithCondition() throws Exception {
//        command.getAeReport().getSaeReportPreExistingConditions().get(0).setPreExistingCondition(
//                        new PreExistingCondition());
//        doValidate();
//        assertEquals(0, getErrors().getErrorCount());
//    }

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
        assertEquals("Wrong code", "SAE_015", fieldError.getCode());
        assertEquals("Wrong message", "Either a known pre Existing Condition or other is required", fieldError.getDefaultMessage());
    }
    

    public void testParticipantFieldsPresent() throws Exception {
        assertFieldProperties("participant", "aeReport.participantHistory.height", "aeReport.participantHistory.weight");
    }

    public void testStaticDiseaseFieldsPresent() throws Exception {
        assertFieldProperties("disease", "aeReport.diseaseHistory.ctepStudyDisease",
                        "aeReport.diseaseHistory.otherPrimaryDisease",
                        "aeReport.diseaseHistory.codedPrimaryDiseaseSite",
                        "aeReport.diseaseHistory.otherPrimaryDiseaseSite",
                        "aeReport.diseaseHistory.diagnosisDate");
    }

   
//    public void testBaselineOptions() throws Exception {
//        // TODO: baseline status is going to change into an enum
//        Map<Object, Object> actualOptions = getActualSelectFieldOptions("participant",
//                        "aeReport.participantHistory.baselinePerformanceStatus");
//        assertEquals("Wrong number of options: " + actualOptions, 4, actualOptions.size());
//        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
//        Map.Entry<Object, Object> entry = iterator.next();
//        assertKeyAndValue("Null value missing", "", "Please select", entry);
//        entry = iterator.next();
//        assertKeyAndValue("Wrong 0th option", "0 = zero", "0 = zero", entry);
//        entry = iterator.next();
//        assertKeyAndValue("Wrong 1st option", "1 = one", "1 = one", entry);
//        entry = iterator.next();
//        assertKeyAndValue("Wrong 2nd option", "2 = two", "2 = two", entry);
//        assertFalse(iterator.hasNext());
//    }

    public void testHeightOptions() throws Exception {
        Map<Object, Object> actualOptions = getMeasureUnitFieldOptions("height");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
        Map.Entry<Object, Object> entry = iterator.next();
        assertKeyAndValue("Wrong 0th option", "Inch", "Inch", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 1st option", "Centimeter", "Centimeter", entry);
        assertFalse(iterator.hasNext());
    }

    public void testWeightOptions() throws Exception {
        Map<Object, Object> actualOptions = getMeasureUnitFieldOptions("weight");
        assertEquals("Wrong number of options: " + actualOptions, 2, actualOptions.size());
        Iterator<Map.Entry<Object, Object>> iterator = actualOptions.entrySet().iterator();
        Map.Entry<Object, Object> entry = iterator.next();
        assertKeyAndValue("Wrong 0th option", "Pound", "Pound", entry);
        entry = iterator.next();
        assertKeyAndValue("Wrong 1st option", "Kilogram", "Kilogram", entry);
        assertFalse(iterator.hasNext());
    }

    @SuppressWarnings( { "unchecked" })
    private Map<Object, Object> getMeasureUnitFieldOptions(String measure) {
        InputField measureField = findField(getFieldGroup("participant").getFields(),"aeReport.participantHistory." + measure);
        InputField unitField = findField(CompositeField.getSubfields(measureField),"aeReport.participantHistory." + measure + ".unit");
        return InputFieldAttributes.getOptions(unitField);
    }

    private static <K, V> void assertKeyAndValue(String message, K expectedKey, V expectedValue,Map.Entry<K, V> actual) {
        assertEquals(message + ": wrong key", expectedKey, actual.getKey());
        assertEquals(message + ": wrong value", expectedValue, actual.getValue());
    }
}
