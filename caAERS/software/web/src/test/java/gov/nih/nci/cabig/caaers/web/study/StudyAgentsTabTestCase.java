package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */

public class StudyAgentsTabTestCase extends AbstractStudyWebTestCase {

    

    protected StudyTab createTab() {
        AgentsTab tab = new AgentsTab();
        tab.setConfigurationProperty(new ConfigProperty());
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);
        return tab;
    }

    public void testCreateFieldGroupWithNoAgent() {
        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertNotNull(map);
        assertEquals(0, map.size());
    }

    public void testCreateFieldGroupWithTwoAgents() {
        List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
        study.setStudyAgents(studyAgents);
        studyAgents.add(new StudyAgent());
        studyAgents.add(new StudyAgent());

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertNotNull(map);

        InputFieldGroup main0 = map.get("main0");
        assertNotNull(main0);

        InputFieldGroup main1 = map.get("main1");
        assertNotNull(main1);

        assertEquals(4, main0.getFields().size());
        assertEquals(4, main1.getFields().size());

        assertEquals("Agent", ((InputFieldFactory.DefaultInputField)main0.getFields().get(0)).getDisplayName());
        assertEquals("Other", ((InputFieldFactory.DefaultInputField)main0.getFields().get(1)).getDisplayName());

        assertEquals("study.studyAgents[0].agent", ((InputFieldFactory.DefaultInputField)main0.getFields().get(0)).getPropertyName());
        assertEquals("study.studyAgents[0].otherAgent", ((InputFieldFactory.DefaultInputField)main0.getFields().get(1)).getPropertyName());

        assertEquals("study.studyAgents[1].agent", ((InputFieldFactory.DefaultInputField)main1.getFields().get(0)).getPropertyName());
        assertEquals("study.studyAgents[1].otherAgent", ((InputFieldFactory.DefaultInputField)main1.getFields().get(1)).getPropertyName());
    }

    public void testPostProcessAddOneAgent() {
        assertEquals(0, study.getStudyAgents().size());

        replayMocks();
        request.setParameter("_action", "addStudyAgent");
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(1, study.getStudyAgents().size());
    }

    public void testPostProcessRemoveOneAgent() {
        assertEquals(0, study.getStudyAgents().size());

        StudyAgent studyAgent = new StudyAgent();
        studyAgent.setAgent(new Agent());
        study.addStudyAgent(studyAgent);
        assertEquals(1, study.getStudyAgents().size());

        replayMocks();
        request.setParameter("_action", "removeStudyAgent");
        request.setParameter("_selected", "0");
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(0, study.getStudyAgents().size());
    }

    public void testPostProcessRemoveInd() {
        assertEquals(0, study.getStudyAgents().size());

        StudyAgent studyAgent = new StudyAgent();
        studyAgent.setAgent(new Agent());
        study.addStudyAgent(studyAgent);

        StudyAgentINDAssociation saIND1 = new StudyAgentINDAssociation();
        InvestigationalNewDrug ind1 = new InvestigationalNewDrug();
        saIND1.setInvestigationalNewDrug(ind1);
        studyAgent.addStudyAgentINDAssociation(saIND1);

        assertEquals(1, study.getStudyAgents().size());
        assertEquals(1, study.getStudyAgents().get(0).getStudyAgentINDAssociations().size());

        replayMocks();
        request.setParameter("_action", "removeInd");
        request.setParameter("_selected", "0");
        request.setParameter("_selectedInd", "0");
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(1, study.getStudyAgents().size());
        assertEquals(0, study.getStudyAgents().get(0).getStudyAgentINDAssociations().size());
    }

    public void testValidation() {
        study.addStudyAgent(new StudyAgent());
        study.getStudyAgents().get(0).setAgent(new Agent());

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }

    public void testRemoveStudyAgent(){
        assertEquals(0, study.getStudyAgents().size());

        StudyAgent studyAgent = new StudyAgent();
        studyAgent.setAgent(new Agent());
        study.addStudyAgent(studyAgent);
        assertEquals(1, study.getStudyAgents().size());

        replayMocks();
        request.setParameter("_action", "removeStudyAgent");
        request.setParameter("_selected", "0");
        request.setParameter("index", "0");
        ((AgentsTab)tab).removeStudyAgent(request, command, getErrors());
        verifyMocks();

        assertEquals(0, study.getStudyAgents().size());
    }

  
}