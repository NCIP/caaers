package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.Agent;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/**
 * @author Rhett Sutphin
 */
public class AgentsTab extends StudyTab {
    public AgentsTab() {
        super("Study Agents", "Agents", "study/study_agents");
    }

    @Override
    public void postProcess(HttpServletRequest request, Study command, Errors errors) {
        handleStudyAgentAction(command, request.getParameter("_action"),
            request.getParameter("_selected"));
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
