package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.createStudyAgent;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class InterventionsTabTest extends AeTabTestCase {
    @Override
    protected StudyInterventionsTab createTab() {
        ConfigProperty configProperty = new ConfigProperty();
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("agentDoseUMORefData", new ArrayList<Lov>());
        map.put("radiationDoseUMORefData", new ArrayList<Lov>());
        map.put("radiationAdjustmentRefData", new ArrayList<Lov>());
        map.put("stateRefData", new ArrayList<Lov>());
        configProperty.setMap(map);
        StudyInterventionsTab tab = new StudyInterventionsTab();
        tab.setConfigurationProperty(configProperty);
        return tab;
    }

    @SuppressWarnings( { "unchecked" })
    public void testDelayUnitsInField() throws Exception {
        InputField delayComposite = getFieldGroup("courseAgent0").getFields().get(6);

        Map<Object, Object> actualOptions = getActualSelectFieldOptions((List<InputField>) delayComposite.getAttributes().get(InputField.SUBFIELDS), "aeReport.treatmentInformation.courseAgents[0].administrationDelayUnits");
        assertEquals("Wrong number of options: " + actualOptions, DelayUnits.values().length, actualOptions.size());
        for (DelayUnits units : DelayUnits.values()) {
            assertEquals("Missing option for " + units, units.getDisplayName(), actualOptions.get(units.toString()));
        }
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
        cmd.getAeReport().addSurgeryIntervention(new SurgeryIntervention());
        
    }

    public void testCourseAgentFields() throws Exception {
        assertFieldProperties(
                        "courseAgent2",
                        "aeReport.treatmentInformation.courseAgents[2].studyAgent",
                        "aeReport.treatmentInformation.courseAgents[2].formulation",
                        "aeReport.treatmentInformation.courseAgents[2].lotNumber",
                        "aeReport.treatmentInformation.courseAgents[2].dose.amount",
                        "aeReport.treatmentInformation.courseAgents[2].dose.units",
                        "aeReport.treatmentInformation.courseAgents[2].lastAdministeredDate",
                        "aeReport.treatmentInformation.courseAgents[2]", // administration delay
                                                                            // doesn't have a base
                                                                            // prop
                        "aeReport.treatmentInformation.courseAgents[2].comments",
                        "aeReport.treatmentInformation.courseAgents[2].agentAdjustment");
    }

    public void testTotalDoseAdministered() throws Exception {
        InputField totalDoseField = getFieldGroup("courseAgent0").getFields().get(3);
        assertEquals("Wrong field name, it should be 'Total dose administered this course'", "aeReport.treatmentInformation.courseAgents[0].dose.amount", totalDoseField.getPropertyName());
        totalDoseField = getFieldGroup("courseAgent0").getFields().get(4);
        assertEquals("Wrong field name, it should be 'Total dose administered (UOM)'", "aeReport.treatmentInformation.courseAgents[0].dose.units", totalDoseField.getPropertyName());
    }

    @SuppressWarnings( { "unchecked" })
    public void testAdminDelaySubfields() throws Exception {
        InputField delayField = getFieldGroup("courseAgent7").getFields().get(6); // admindelay
        List<InputField> subfields = (List<InputField>) delayField.getAttributes().get(InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 2, subfields.size());
        assertEquals("Wrong subfield 0","aeReport.treatmentInformation.courseAgents[7].administrationDelayAmount",subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1","aeReport.treatmentInformation.courseAgents[7].administrationDelayUnits",subfields.get(1).getPropertyName());
    }

    public void testStudyAgentsOptionsInField() throws Exception {
        command.getStudy().addStudyAgent(setId(166, createStudyAgent("Agent 1")));
        command.getStudy().addStudyAgent(setId(267, createStudyAgent("Agent 2")));
        InputFieldGroup group = getFieldGroup("courseAgent0");
        Map<Object, Object> actualOptions = getActualSelectFieldOptions(group.getFields(), "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertEquals("Wrong number of options: " + actualOptions, 3, actualOptions.size());
        assertEquals("Missing option for sa 0", "Agent 1", actualOptions.get(166));
        assertEquals("Missing option for sa 1", "Agent 2", actualOptions.get(267));
    }

    public void testRadiationFieldProperties() throws Exception {
        assertFieldProperties("radiationIntervention6",
                        "aeReport.radiationInterventions[6].administration",
                        "aeReport.radiationInterventions[6].dosage",
                        "aeReport.radiationInterventions[6].dosageUnit",
                        "aeReport.radiationInterventions[6].lastTreatmentDate",
                        "aeReport.radiationInterventions[6].fractionNumber",
                        "aeReport.radiationInterventions[6].daysElapsed",
                        "aeReport.radiationInterventions[6].adjustment");
    }

    public void testSurgeryFieldProperties() throws Exception {
        assertFieldProperties("surgeryIntervention7",
                        "aeReport.surgeryInterventions[7].treatmentArm",
                        "aeReport.surgeryInterventions[7].description",
                        "aeReport.surgeryInterventions[7].interventionSite",
                        "aeReport.surgeryInterventions[7].interventionDate");
    }
    
}