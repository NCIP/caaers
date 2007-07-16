package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.web.fields.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultCheckboxField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultSelectField;
import gov.nih.nci.cabig.caaers.web.fields.DefaultTextField;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class AgentsTab extends StudyTab {
	private RepeatingFieldGroupFactory rfgFactory;
    public AgentsTab() {
        super("Study Agents", "Agents", "study/study_agents");
    }

    @Override
    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        handleStudyAgentAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
    }
    
    @Override
    protected void validate(Study command, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
    	
    	boolean isAgentEmpty = false;
    	for (StudyAgent studyAgent: command.getStudyAgents()){
    		isAgentEmpty = studyAgent.getAgent() == null ? true : false;
    		if (isAgentEmpty) break;
    	}
        if (isAgentEmpty) errors.rejectValue("studyAgents", "REQUIRED", "One or more Agents are missing or not in list");

    }

	@Override
	public Map<String, InputFieldGroup> createFieldGroups(Study command) {
		 if(rfgFactory == null){
			 rfgFactory = new RepeatingFieldGroupFactory("main", "studyAgents");
			 AutocompleterField agentField = new AutocompleterField("agent", "Agent", true);
			 agentField.getAttributes().put(InputField.SIZE, 70);
			 agentField.getAttributes().put(InputField.ENABLE_CLEAR , true);
			 //agentField.getAttributes().put(InputField.DETAILS, "Enter a portion of the agent name you are looking for");
			 rfgFactory.addField(agentField);
			 DefaultTextField indField = new DefaultTextField("investigationalNewDrugIdentifier","IND Identifier", true);
			 indField.getAttributes().put(InputField.HELP, "Investigational new drug identifier");
			 rfgFactory.addField(indField);
			 DefaultCheckboxField indIndicator = new DefaultCheckboxField("investigationalNewDrugIndicator","IND Indicator");
			 indIndicator.getAttributes().put(InputField.HELP, "Investigational new drug indicator");
			 rfgFactory.addField(indIndicator);
		 }
		 Study study = (Study)command;
		 InputFieldGroupMap map = new InputFieldGroupMap();
		 map.addRepeatingFieldGroupFactory(rfgFactory, study.getStudyAgents().size());
		 return map;
	}

	private void handleStudyAgentAction(Study study, String action, String selected) {
        if ("addStudyAgent".equals(action)) {
            StudyAgent studyAgent = new StudyAgent();
            studyAgent.setAgent(new Agent());
            study.addStudyAgent(studyAgent);
        } else if ("removeStudyAgent".equals(action)) {
            study.getStudyAgents().remove(Integer.parseInt(selected));
        }
    }
}
