package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;

/**
 * @author Ion C. Olaru
 *         Date: 4/3/12 -11:49 AM
 */
public class AgentConverter extends DomainObjectConverter{

	public void convertAgentDtoToDomainAgent(AgentType agentDto, Agent agent){
		agent.setName(agentDto.getName());
		agent.setDescription(agentDto.getDescriptionText());
		agent.setRetiredIndicator(agentDto.getStatus().name().equals("AC") ? false:true);
	}

    public static Agent convert(AgentType at) {
        Agent a = new Agent();
        a.setName(at.getName());
        a.setNscNumber(at.getNscNumber());
        a.setDescription(at.getDescriptionText());
        return a;
    }

    public static AgentType convert(Agent a) {
        AgentType at = new AgentType();
        at.setName(a.getName());
        at.setNscNumber(a.getNscNumber());
        at.setDescriptionText(a.getDescription());
        return at;
    }

}
