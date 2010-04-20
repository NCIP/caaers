package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;

import java.util.List;

/**
 * 
 * @author Ion C. Olaru
 * 
 */
public class AgentSpecificAdverseEventListServiceImpl implements AgentSpecificAdverseEventListService {

    private AgentSpecificTermDao agentSpecificTermDao;

    public List<AgentSpecificTerm> getListByAgent(Integer agentID) {
        return agentSpecificTermDao.getByAgentID(agentID);
    }

    public AgentSpecificTermDao getAgentSpecificTermDao() {
        return agentSpecificTermDao;
    }

    public void setAgentSpecificTermDao(AgentSpecificTermDao agentSpecificTermDao) {
        this.agentSpecificTermDao = agentSpecificTermDao;
    }
}
