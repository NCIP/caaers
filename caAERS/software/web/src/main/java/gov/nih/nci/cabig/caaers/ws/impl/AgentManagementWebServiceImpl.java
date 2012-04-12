package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.AgentService;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.Agents;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessages;
import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
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
		CaaersServiceResponse caaersResponse = new CaaersServiceResponse();
		ServiceResponse serviceResponse = new ServiceResponse();
		caaersResponse.setServiceResponse(serviceResponse);
		
		List<Agent> domainAgents = new ArrayList<Agent>();
		EntityErrorMessages entityErrorMessageTypes = new EntityErrorMessages();
		serviceResponse.setEntityErrorMessages(entityErrorMessageTypes);
		try {
			for(AgentType agentDto: xmlAgents.getAgent()){
				Agent agent = new Agent();
				agentConverter.convertAgentDtoToDomainAgent(agentDto, agent);
				domainAgents.add(agent);
			}
			List<EntityErrorMessage> entityErrorMessages = agentService.createOrUpdateAgents(domainAgents);
			agentConverter.convertEntityErrorMessages(entityErrorMessages, entityErrorMessageTypes);
		} catch (Throwable e) {
			logger.debug(e.getMessage());
			SecurityExceptionFault fault = new SecurityExceptionFault();
			String message = "The user doesn't have access to do create/update agent operations";
			fault.setMessage(message);
			throw new SecurityExceptionFaultMessage(e.getMessage(), fault);
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
