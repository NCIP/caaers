package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.Agent;
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

    public void testStudyAgentFieldsIncluded() throws Exception {
        ensureStudyAgentCount(2);
        ensureAeCount(4);

        Map<String, InputFieldGroup> map = getTab().createFieldGroups(command);
        assertEquals("Wrong number of study agent groups", 2, map.size());
        InputFieldGroup actualGroup0 = map.get("studyAgent0");
        assertNotNull(actualGroup0);
        assertEquals(4, actualGroup0.getFields().size());
        assertEquals("attributionMap[studyAgent][0][0]", actualGroup0.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[studyAgent][1][0]", actualGroup0.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[studyAgent][2][0]", actualGroup0.getFields().get(2).getPropertyName());
        assertEquals("attributionMap[studyAgent][3][0]", actualGroup0.getFields().get(3).getPropertyName());

        InputFieldGroup actualGroup1 = map.get("studyAgent1");
        assertNotNull(actualGroup1);
        assertEquals(4, actualGroup0.getFields().size());
        assertEquals("attributionMap[studyAgent][0][1]", actualGroup1.getFields().get(0).getPropertyName());
        assertEquals("attributionMap[studyAgent][1][1]", actualGroup1.getFields().get(1).getPropertyName());
        assertEquals("attributionMap[studyAgent][2][1]", actualGroup1.getFields().get(2).getPropertyName());
        assertEquals("attributionMap[studyAgent][3][1]", actualGroup1.getFields().get(3).getPropertyName());
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
        ensureStudyAgentCount(1);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(0);
        assertEquals(1, block.getRows().size());
        assertEquals("Study agent", block.getDisplayName());
    }

    public void testCreateStudyAgentsBlock() throws Exception {
        ensureStudyAgentCount(2);
        ensureAeCount(2);

        AttributionTab.AttributionBlock block = getBlocks().get(0);
        assertEquals(2, block.getRows().size());
        assertEquals("Study agents", block.getDisplayName());
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

    private void ensureStudyAgentCount(int count) {
        Study study = command.getAeReport().getAssignment().getStudySite().getStudy();
        while (study.getStudyAgents().size() < count) {
            StudyAgent sa = new StudyAgent();
            sa.setAgent(new Agent());
            study.addStudyAgent(sa);
        }
    }

    private void ensureConMedCount(int count) {
        while (command.getAeReport().getConcomitantMedications().size() < count) {
            command.getAeReport().addConcomitantMedication(new ConcomitantMedication());
        }
    }
}
