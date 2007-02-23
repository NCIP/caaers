package gov.nih.nci.cabig.caaers.web.study;


import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.dao.AgentDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Rhett Sutphin
 * @author Krikor Krumlian
 */
public class CreateStudyAjaxFacade {
    private AgentDao agentDao;

   
    
    
    public List<Agent> matchAgents(String text) {
        List<Agent> agents = agentDao.getBySubnames(extractSubnames(text));
        return agents;
    }
   
    private String[] extractSubnames(String text) {
        return text.split("\\s+");
    }


    ////// CONFIGURATION

    @Required
    public AgentDao getAgentDao() {
		return agentDao;
	}
    
    @Required
    public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}
}
