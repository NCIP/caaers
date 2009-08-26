package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.INDType;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class AgentsTab extends StudyTab {
    @Deprecated
    public static int IND_TYPE_NOT_USED = 0;

    @Deprecated
    public static int IND_TYPE_CTEP = 1;

    @Deprecated
    public static int IND_TYPE_OTHER = 2;

    @Deprecated
    public static int IND_TYPE_NA_COMMERCIAL_AGENT = 3;

    @Deprecated
    public static int IND_TYPE_EXEMPT = 4;

    @Deprecated
    public static int IND_TYPE_DCP_IND = 5;

    @Deprecated
    public static int CTEP_IND = -111;

    private LinkedHashMap<Object, Object> indTypeMap = new LinkedHashMap<Object, Object>();

    public AgentsTab() {
        super("Agents", "Agents", "study/study_agents");
        // setAutoPopulateHelpKey(true);
        for (INDType indType : INDType.values()) {
            indTypeMap.put(indType.name(), indType.getDisplayName());
        }

    }

    @Override
    public void postProcess(final HttpServletRequest request, final StudyCommand command, final Errors errors) {
        handleStudyAgentAction(command, request.getParameter("_action"), request.getParameter("_selected"), request.getParameter("_selectedInd"));
    }

    @Override
    protected void validate(final StudyCommand command, final BeanWrapper commandBean, final Map<String, InputFieldGroup> fieldGroups, final Errors errors) {

        boolean isAgentEmpty = false;
        StudyAgent studyAgent = null;
        List<StudyAgent> studyAgents = command.getStudy().getStudyAgents();

        for (int i = 0; i < command.getStudy().getStudyAgents().size(); i++) {
            studyAgent = studyAgents.get(i);
            
            if(!studyAgent.isRetired()){
                if (studyAgent.getAgent() == null && studyAgent.getOtherAgent() == null) {
                    isAgentEmpty = true;
                    errors.rejectValue("study.studyAgents[" + i + "].otherAgent", "STU_007","Select either Agent or Other ");
                    continue;
                }

                if (studyAgent.getAgent() != null) {
                    studyAgent.setOtherAgent(null);
                }
            }
        }

        if (isAgentEmpty) {
            errors.rejectValue("study.studyAgents", "STU_008", "One or more Agents are missing or not in list");
        }

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(final StudyCommand command) {
        InputFieldGroupMap map = new InputFieldGroupMap();
        String baseName = "study.studyAgents";
        int i = -1;

        for (StudyAgent sa : command.getStudy().getStudyAgents()) {
            i++;
            InputFieldGroup fieldGrp = new DefaultInputFieldGroup("main" + i);
            InputFieldGroup indFieldGroup = new DefaultInputFieldGroup("ind" + i);
            
            List<InputField> fields = fieldGrp.getFields();
            InputField agentField = InputFieldFactory.createAutocompleterField(baseName + "[" + i + "].agent", "Agent", false);
            InputFieldAttributes.setSize(agentField, 70);
            agentField.getAttributes().put(InputField.ENABLE_CLEAR, false);
            fields.add(agentField);

            InputField otherAgentField = InputFieldFactory.createTextField(baseName + "[" + i + "].otherAgent", "Other", false);
            InputFieldAttributes.setSize(otherAgentField, 70);
            fields.add(otherAgentField);

            InputField indTypeField = InputFieldFactory.createSelectField(baseName + "[" + i + "].indType", "Enter IND information", indTypeMap);
            fields.add(indTypeField);
            if (sa.getStudyAgentINDAssociations() != null) {
                int j = -1;
                for (StudyAgentINDAssociation saInd : sa.getStudyAgentINDAssociations()) {
                    // dont show IND field for CTEP unspecified IND
                    InvestigationalNewDrug ind = saInd.getInvestigationalNewDrug();
                    if (ind != null && ind.getIndNumber() != null && ind.getIndNumber() == CTEP_IND) {
                        continue;
                    }
                    j++;
                    
                    InputField indField = InputFieldFactory.createAutocompleterField(baseName + "[" + i + "].studyAgentINDAssociations[" + j + "].investigationalNewDrug", "IND #", true);
                    indField.getAttributes().put(InputField.ENABLE_CLEAR, true);
                    InputFieldAttributes.setSize(indField, 41);
                    indFieldGroup.getFields().add(indField);
                }
            }// ~if

            InputField partOfLeadIND = InputFieldFactory.createBooleanSelectField(baseName + "[" + i + "].partOfLeadIND", "Lead IND ?");
            fields.add(partOfLeadIND);
            map.addInputFieldGroup(fieldGrp);
            map.addInputFieldGroup(indFieldGroup);
            
        }
        return map;
    }

    private void handleStudyAgentAction(final StudyCommand command, final String action, final String selected, final String selectedInd) {
    	Study study = command.getStudy();
    	
        if ("addStudyAgent".equals(action)) {
            StudyAgent studyAgent = new StudyAgent();
            studyAgent.setAgent(new Agent());
            study.addStudyAgent(studyAgent);
        } else if ("removeInd".equals(action)) {
            StudyAgent sa = study.getStudyAgents().get(Integer.parseInt(selected));
            List<StudyAgentINDAssociation> sas = sa.getStudyAgentINDAssociations();
            if (sas.size() > 0) {
                sas.remove(Integer.parseInt(selectedInd));
            }
        }
    }
}
