package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.PreExistingConditionLOVService;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessages;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditionType;
import gov.nih.nci.cabig.caaers.integration.schema.common.PreExistingConditions;
import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.service.migrator.DomainObjectConverter;
import gov.nih.nci.cabig.caaers.ws.PreExistingConditionManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.PreExistingConditionManagementWebService", serviceName="PreExistingConditionManagementWebService", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/precondition")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class PreExistingConditionManagementWebServiceImpl implements PreExistingConditionManagementWebService{
	
	private static Log logger = LogFactory.getLog(PreExistingConditionManagementWebServiceImpl.class);
	private PreExistingConditionLOVService preExistingConditionLOVService;
	private DomainObjectConverter domainObjectConverter;

	public void setDomainObjectConverter(DomainObjectConverter domainObjectConverter) {
		this.domainObjectConverter = domainObjectConverter;
	}

	public void setPreExistingConditionLOVService(
			PreExistingConditionLOVService preExistingConditionLOVService) {
		this.preExistingConditionLOVService = preExistingConditionLOVService;
	}

	public CaaersServiceResponse importPreExistingConditions(PreExistingConditions xmlPreExistingConditions)
			throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = new CaaersServiceResponse();
		ServiceResponse serviceResponse = new ServiceResponse();
		caaersResponse.setServiceResponse(serviceResponse);
		
		List<PreExistingCondition> domainConditions = new ArrayList<PreExistingCondition>();
		EntityErrorMessages entityErrorMessageTypes = new EntityErrorMessages();
		serviceResponse.setEntityErrorMessages(entityErrorMessageTypes);
		try {
			for(PreExistingConditionType conditionDto: xmlPreExistingConditions.getPreExistingCondition()){
				PreExistingCondition domainCondition = new PreExistingCondition();
				domainCondition.setText(conditionDto.getText());
				domainConditions.add(domainCondition);
			}
			List<EntityErrorMessage> entityErrorMessages = preExistingConditionLOVService.importPreExistingConditions(domainConditions);
			domainObjectConverter.convertEntityErrorMessages(entityErrorMessages, entityErrorMessageTypes);
		} catch (Throwable e) {
			logger.debug(e.getMessage());
			SecurityExceptionFault fault = new SecurityExceptionFault();
			String message = "The user doesn't have access to create new Pre-Existing Conditions";
			fault.setMessage(message);
			throw new SecurityExceptionFaultMessage(e.getMessage(), fault);
		}
		
		return caaersResponse;
	}

}
