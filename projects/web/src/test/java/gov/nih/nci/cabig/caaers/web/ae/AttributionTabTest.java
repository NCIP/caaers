package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Map;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class AttributionTabTest extends AeTabTestCase<AttributionTab> {
    @Override
    protected AttributionTab createTab() {
        return new AttributionTab();
    }

    public void testCourseAgentFieldsIncluded() throws Exception {
        ensureCourseAgentCount(2);
        ensureAeCount(4);

        Map<String, InputFieldGroup> map = getTab().createFieldGroups(command);
        assertEquals("Wrong number of study agent groups", 2, map.size());
        InputFieldGroup actualGroup0 = map.get("courseAgent0");
        assertNotNull(actualGroup0);
        assertEquals(4, actualGroup0.getFields().size());
        assertEquals("attributionMap[courseAgent][0][0]", actualGroup0.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[courseAgent][1][0]", actualGroup0.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[courseAgent][2][0]", actualGroup0.getFields().get(2).getPropertyName());
        assertEquals("attributionMap[courseAgent][3][0]", actualGroup0.getFields().get(3).getPropertyName());

        InputFieldGroup actualGroup1 = map.get("courseAgent1");
        assertNotNull(actualGroup1);
        assertEquals(4, actualGroup0.getFields().size());
        assertEquals("attributionMap[courseAgent][0][1]", actualGroup1.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[courseAgent][1][1]", actualGroup1.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[courseAgent][2][1]", actualGroup1.getFields().get(2).getPropertyName());
        assertEquals("attributionMap[courseAgent][3][1]", actualGroup1.getFields().get(3).getPropertyName());
    }

    public void testConMedFieldsIncluded() throws Exception {
        ensureConMedCount(3);
        ensureAeCount(3);

        Map<String, InputFieldGroup> map = getTab().createFieldGroups(command);
        assertEquals("Wrong number of con med groups", 3, map.size());

        InputFieldGroup actualGroup0 = map.get("conMed0");
        assertNotNull(actualGroup0);
        assertEquals(3, actualGroup0.getFields().size());
        assertEquals("attributionMap[conMed][0][0]", actualGroup0.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[conMed][1][0]", actualGroup0.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[conMed][2][0]", actualGroup0.getFields().get(2).getPropertyName());

        InputFieldGroup actualGroup1 = map.get("conMed1");
        assertNotNull(actualGroup1);
        assertEquals(3, actualGroup1.getFields().size());
        assertEquals("attributionMap[conMed][0][1]", actualGroup1.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[conMed][1][1]", actualGroup1.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[conMed][2][1]", actualGroup1.getFields().get(2).getPropertyName());

        InputFieldGroup actualGroup2 = map.get("conMed2");
        assertNotNull(actualGroup2);
        assertEquals(3, actualGroup2.getFields().size());
        assertEquals("attributionMap[conMed][0][2]", actualGroup2.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[conMed][1][2]", actualGroup2.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[conMed][2][2]", actualGroup2.getFields().get(2).getPropertyName());
    }

    public void testCreateStudyAgentBlock() throws Exception {
        ensureCourseAgentCount(1);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(0);
        assertEquals(1, block.getRows().size());
        assertEquals("Course", block.getDisplayName());
    }

    public void testCreateStudyAgentsBlock() throws Exception {
        ensureCourseAgentCount(2);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(0);
        assertEquals(2, block.getRows().size());
        assertEquals("Course", block.getDisplayName());
    }

    public void testCreateConMedBlock() throws Exception {
        ensureConMedCount(1);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(1);
        assertEquals(1, block.getRows().size());
        assertEquals("Concomitant medication", block.getDisplayName());
    }

    public void testCreateConMedsBlock() throws Exception {
        ensureConMedCount(2);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(1);
        assertEquals(2, block.getRows().size());
        assertEquals("Concomitant medications", block.getDisplayName());
    }

    @SuppressWarnings({ "unchecked" })
    private List<AttributionTab.AttributionBlock> getBlocks() {
        return (List<AttributionTab.AttributionBlock>) getTab()
            .referenceData(command).get("blocks");
    }

    private void ensureAeCount(int count) {
        while (command.getAeReport().getAdverseEvents().size() < count) {
            command.getAeReport().addAdverseEvent(new AdverseEvent());
        }
    }

    private void ensureCourseAgentCount(int count) {
        TreatmentInformation info = command.getAeReport().getTreatmentInformation();
        while (info.getCourseAgents().size() < count) {
            CourseAgent ca = new CourseAgent();
            info.addCourseAgent(ca);
        }
    }

    private void ensureConMedCount(int count) {
        while (command.getAeReport().getConcomitantMedications().size() < count) {
            command.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        }
    }
}
