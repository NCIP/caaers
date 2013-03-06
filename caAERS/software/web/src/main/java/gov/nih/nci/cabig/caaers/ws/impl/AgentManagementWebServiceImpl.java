/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.AgentService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.Agents;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.service.migrator.AgentConverter;
import gov.nih.nci.cabig.caaers.ws.AgentManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.AgentManagementWebService", serviceName="AgentManagementWebService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class AgentManagementWebServiceImpl implements AgentManagementWebService{
	
	private static Log logger = LogFactory.getLog(AgentManagementWebServiceImpl.class);
	private AgentConverter agentConverter;
	private AgentService agentService;

	public CaaersServiceResponse createOrUpdateAgent(Agents xmlAgents) throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = Helper.createResponse();
		
		List<Agent> domainAgents = new ArrayList<Agent>();
		try {
			for(AgentType agentDto: xmlAgents.getAgent()){
				Agent agent = AgentConverter.convert(agentDto);
				domainAgents.add(agent);
			}
			List<ProcessingOutcome> processingOutcomes = agentService.createOrUpdateAgents(domainAgents);
            for(ProcessingOutcome outcome : processingOutcomes){
                Helper.populateProcessingOutcome(caaersResponse, outcome);
            }
		} catch (Throwable e) {
            logger.error(e);
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
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
