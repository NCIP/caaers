package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.AgentService;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.Agents;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
import gov.nih.nci.cabig.caaers.service.migrator.AgentConverter;
import gov.nih.nci.cabig.caaers.ws.AgentManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.AgentManagementWebService", serviceName="AgentManagementWebService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class AgentManagementWebServiceImpl implements AgentManagementWebService{
	
	private static Log logger = LogFactory.getLog(AgentManagementWebServiceImpl.class);
	private AgentConverter agentConverter;
	private AgentService agentService;

	public CaaersServiceResponse createOrUpdateAgent(Agents xmlAgents)
			throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = Helper.createResponse();
		EntityProcessingOutcomes entityProcessingOutcomeTypes = new EntityProcessingOutcomes();
		caaersResponse.getServiceResponse().setEntityProcessingOutcomes(entityProcessingOutcomeTypes);
		
		List<Agent> domainAgents = new ArrayList<Agent>();
		try {
			for(AgentType agentDto: xmlAgents.getAgent()){
				Agent agent = AgentConverter.convert(agentDto);
				domainAgents.add(agent);
			}
			List<EntityErrorMessage> entityErrorMessages = agentService.createOrUpdateAgents(domainAgents);
			agentConverter.convertEntityProcessingOutcomes(entityErrorMessages, entityProcessingOutcomeTypes);
		} catch (Throwable e) {
			logger.warn(e);
		}
		
		return caaersResponse;
	}

	public void setAgentConverter(AgentConverter agentConverter) {
		this.agentConverter = agentConverter;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

}
