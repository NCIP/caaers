package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.PreExistingConditionLOVService;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
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

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.PreExistingConditionManagementWebService", serviceName="PreExistingConditionManagementWebService", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
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
		EntityProcessingOutcomes entityProcessingOutcomeTypes = new EntityProcessingOutcomes();
		serviceResponse.setEntityProcessingOutcomes(entityProcessingOutcomeTypes);
		try {
			for(PreExistingConditionType conditionDto: xmlPreExistingConditions.getPreExistingCondition()){
				PreExistingCondition domainCondition = new PreExistingCondition();
				domainCondition.setText(conditionDto.getText());
				domainConditions.add(domainCondition);
			}
			List<EntityErrorMessage> entityErrorMessages = preExistingConditionLOVService.importPreExistingConditions(domainConditions);
			domainObjectConverter.convertEntityProcessingOutcomes(entityErrorMessages, entityProcessingOutcomeTypes);
		} catch (Throwable e) {
			logger.warn(e);
		}
		
		return caaersResponse;
	}

}
