package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.createStudyAgent;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DelayUnits;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT })
public class TreatmentTabTest extends AeTabTestCase {
    @Override
    protected TreatmentTab createTab() {
        ConfigProperty configProperty = new ConfigProperty();
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        map.put("agentDoseUMORefData", new ArrayList<Lov>());
        configProperty.setMap(map);
        TreatmentTab tab = new TreatmentTab();
        tab.setConfigurationProperty(configProperty);
        return tab;
    }

    @Override
    protected void fillInUsedProperties(ExpeditedAdverseEventInputCommand cmd) {
        cmd.getAeReport().getTreatmentInformation().addCourseAgent(new CourseAgent());
    }

    public void testTreatmentInfoFields() throws Exception {
        assertFieldProperties("treatmentInfo", "aeReport.treatmentInformation.treatmentAssignment",
                        "aeReport.treatmentInformation.treatmentAssignmentDescription",
                        "aeReport.treatmentInformation.treatmentDescription",
                        "aeReport.treatmentInformation.firstCourseDate",
                        "aeReport.treatmentInformation.adverseEventCourse.date",
                        "aeReport.treatmentInformation.adverseEventCourse.number",
                        "aeReport.treatmentInformation.totalCourses");
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
                        "aeReport.treatmentInformation.courseAgents[2].modifiedDose");
    }

    public void testTotalDoseAdministered() throws Exception {
        InputField totalDoseField = getFieldGroup("courseAgent0").getFields().get(3);
        assertEquals("Wrong field name, it should be 'Total dose administered this course'",
                        "aeReport.treatmentInformation.courseAgents[0].dose.amount", totalDoseField
                                        .getPropertyName());
        totalDoseField = getFieldGroup("courseAgent0").getFields().get(4);
        assertEquals("Wrong field name, it should be 'Total dose administered (UOM)'",
                        "aeReport.treatmentInformation.courseAgents[0].dose.units", totalDoseField
                                        .getPropertyName());
    }

    /*
     * @SuppressWarnings({ "unchecked" }) public void testDoseSubfields() throws Exception {
     * InputField doseField = getFieldGroup("courseAgent7").getFields().get(6); // dose
     * assertTrue("Field 1 isn't dose (probably a test setup issue): " +
     * doseField.getPropertyName(), doseField.getPropertyName().endsWith("dose")); List<InputField>
     * subfields = (List<InputField>) doseField.getAttributes().get(InputField.SUBFIELDS);
     * assertNotNull("Dose isn't a composite field", subfields); assertEquals("Wrong number of
     * subfields", 3, subfields.size()); assertEquals("Wrong subfield 0",
     * "aeReport.treatmentInformation.courseAgents[7].dose.amount",
     * subfields.get(0).getPropertyName()); assertEquals("Wrong subfield 1",
     * "aeReport.treatmentInformation.courseAgents[7].dose.units",
     * subfields.get(1).getPropertyName()); assertEquals("Wrong subfield 2",
     * "aeReport.treatmentInformation.courseAgents[7].dose.route",
     * subfields.get(2).getPropertyName()); }
     * 
     * @SuppressWarnings({ "unchecked" }) public void testDoseSubfieldsWhenAllPreviousGroupsLoaded()
     * throws Exception { Map<String, InputFieldGroup> map = getTab().createFieldGroups(command);
     * for (int i = 0; i <= 6; i++) map.get("courseAgent" + i);
     * 
     * InputField doseField = map.get("courseAgent7").getFields().get(2); // dose assertTrue("Field
     * 1 isn't dose (probably a test setup issue): " + doseField.getPropertyName(),
     * doseField.getPropertyName().endsWith("dose")); List<InputField> subfields = (List<InputField>)
     * doseField.getAttributes().get(InputField.SUBFIELDS); assertNotNull("Dose isn't a composite
     * field", subfields); assertEquals("Wrong number of subfields", 3, subfields.size());
     * assertEquals("Wrong subfield 0", "aeReport.treatmentInformation.courseAgents[7].dose.amount",
     * subfields.get(0).getPropertyName()); assertEquals("Wrong subfield 1",
     * "aeReport.treatmentInformation.courseAgents[7].dose.units",
     * subfields.get(1).getPropertyName()); assertEquals("Wrong subfield 2",
     * "aeReport.treatmentInformation.courseAgents[7].dose.route",
     * subfields.get(2).getPropertyName()); }
     */
    @SuppressWarnings( { "unchecked" })
    public void testModDoseSubfields() throws Exception {
        InputField modDoseField = getFieldGroup("courseAgent7").getFields().get(8); // modifiedDose
        assertTrue("Field 4 isn't dose (probably a test setup issue): "
                        + modDoseField.getPropertyName(), modDoseField.getPropertyName().endsWith(
                        "modifiedDose"));
        List<InputField> subfields = (List<InputField>) modDoseField.getAttributes().get(
                        InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 2, subfields.size());
        assertEquals("Wrong subfield 0",
                        "aeReport.treatmentInformation.courseAgents[7].modifiedDose.amount",
                        subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
                        "aeReport.treatmentInformation.courseAgents[7].modifiedDose.units",
                        subfields.get(1).getPropertyName());

    }

    @SuppressWarnings( { "unchecked" })
    public void testAdminDelaySubfields() throws Exception {
        InputField delayField = getFieldGroup("courseAgent7").getFields().get(6); // admindelay
        List<InputField> subfields = (List<InputField>) delayField.getAttributes().get(
                        InputField.SUBFIELDS);
        assertNotNull("Dose isn't a composite field", subfields);
        assertEquals("Wrong number of subfields", 2, subfields.size());
        assertEquals("Wrong subfield 0",
                        "aeReport.treatmentInformation.courseAgents[7].administrationDelayAmount",
                        subfields.get(0).getPropertyName());
        assertEquals("Wrong subfield 1",
                        "aeReport.treatmentInformation.courseAgents[7].administrationDelayUnits",
                        subfields.get(1).getPropertyName());
    }

    @SuppressWarnings( { "unchecked" })
    public void testDelayUnitsInField() throws Exception {
        InputField delayComposite = getFieldGroup("courseAgent0").getFields().get(6);

        Map<Object, Object> actualOptions = getActualSelectFieldOptions(
                        (List<InputField>) delayComposite.getAttributes().get(InputField.SUBFIELDS),
                        "aeReport.treatmentInformation.courseAgents[0].administrationDelayUnits");
        assertEquals("Wrong number of options: " + actualOptions, DelayUnits.values().length,
                        actualOptions.size());
        for (DelayUnits units : DelayUnits.values()) {
            assertEquals("Missing option for " + units, units.getDisplayName(), actualOptions
                            .get(units.toString()));
        }
    }

    public void testStudyAgentsOptionsInField() throws Exception {
        command.getStudy().addStudyAgent(setId(166, createStudyAgent("Agent 1")));
        command.getStudy().addStudyAgent(setId(267, createStudyAgent("Agent 2")));

        InputFieldGroup group = getFieldGroup("courseAgent0");
        Map<Object, Object> actualOptions = getActualSelectFieldOptions(group.getFields(),
                        "aeReport.treatmentInformation.courseAgents[0].studyAgent");
        assertEquals("Wrong number of options: " + actualOptions, 3, actualOptions.size());
        assertEquals("Missing option for sa 0", "Agent 1", actualOptions.get(166));
        assertEquals("Missing option for sa 1", "Agent 2", actualOptions.get(267));
    }
}
