/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.api.AgentService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.domain.repository.AgentRepository;
import gov.nih.nci.cabig.caaers.service.migrator.AgentMigrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

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
	
	public List<ProcessingOutcome> createOrUpdateAgents(List<Agent> agents) {
		List<ProcessingOutcome> errorMessages = new ArrayList<ProcessingOutcome>();
		for (Agent agent:agents){
			errorMessages.add(createOrUpdateAgent(agent));
		}
		return errorMessages;
	}
	
	@Transactional(readOnly=false)
	public ProcessingOutcome createOrUpdateAgent(Agent agent) {
		try {
			Agent dbAgent = agentDao.getByNscNumber(agent.getNscNumber());
			// check if db agent with same nsc number exists
			if(dbAgent !=null) {
                logger.info("updating db Agent with NSC Number:" + agent.getNscNumber() + " with remote Agent");
                agentMigrator.migrate(agent, dbAgent, null);
                agentRepository.saveAgent(dbAgent);
                return Helper.createOutcome(Agent.class, agent.getNscNumber(), false, "Agent with NSC number : " +
                		agent.getNscNumber() + ", id : " + dbAgent.getId() + " is updated");
			} else {
				// db agent doesn't exist. Create a new agent.
				logger.info("didn't find db Agent with NSC Number:" + agent.getNscNumber() + ". Creating new Agent");
				Agent newAgent = new Agent();
				agentMigrator.migrate(agent, newAgent, null);
				agentRepository.saveAgent(newAgent);
                return Helper.createOutcome(Agent.class, agent.getNscNumber(), false, "Agent with NSC number : " +
                		agent.getNscNumber() + ", id : " + newAgent.getId() + " is created");
            }
		} catch (Exception e) {
			logger.error(e);
            return Helper.createOutcome(LabCategory.class, agent.getNscNumber(), true, e.getMessage());
		}
	}
}
