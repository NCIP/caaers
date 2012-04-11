package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.AgentService;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.service.migrator.AgentMigrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;

public class AgentServiceImpl implements AgentService{
	
	private static Log logger = LogFactory.getLog(AgentServiceImpl.class);
	private AgentDao agentDao;
    public void setAgentRepository(AgentRepository agentRepository) {
		this.agentRepository = agentRepository;
	}

	private AgentMigrator agentMigrator;
    private AgentRepository agentRepository;
    private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setAgentDao(AgentDao agentDao) {
		this.agentDao = agentDao;
	}

	public void setAgentMigrator(AgentMigrator agentMigrator) {
		this.agentMigrator = agentMigrator;
	}
	
	public List<EntityErrorMessage> createOrUpdateAgents(List<Agent> agents) {
		List<EntityErrorMessage> errorMessages = new ArrayList<EntityErrorMessage>();
		for (Agent agent:agents){
			errorMessages.add(createOrUpdateAgent(agent));
		}
		return errorMessages;
	}
	
	public EntityErrorMessage createOrUpdateAgent(Agent agent) {
		EntityErrorMessage errorMessage = new EntityErrorMessage();
		errorMessage.setBusinessId(agent.getNscNumber());
		try {
			Agent dbAgent = agentDao.getByNscNumber(agent.getNscNumber());
			// check if db agent with same nsc number exists
			if(dbAgent !=null) {
				logger.info("found db Agent with NSC Number:" + agent.getNscNumber());
				// compare with db agent to see if any property changed
				if(!agent.equals(dbAgent)){
					logger.info("updating db Agent with NSC Number:" + agent.getNscNumber() + " with remote Agent");
					agentMigrator.migrate(agent, dbAgent, null);
					agentRepository.saveAgent(dbAgent);
					} 
			} else {
				// db agent doesn't exist. Create a new agent.
				logger.info("didn't find db Agent with NSC Number:" + agent.getNscNumber() + ". Creating new Agent");
				Agent newAgent = new Agent();
				agentMigrator.migrate(agent, newAgent, null);
				agentRepository.saveAgent(newAgent);
			}
		} catch (Exception e) {
			errorMessage.addMessage(e.getMessage());
			logger.error(e.getMessage());
		}
		
		return errorMessage;
	}


}
