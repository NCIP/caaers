package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.PriorTherapyLOVService;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityProcessingOutcomes;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;
import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.service.migrator.DomainObjectConverter;
import gov.nih.nci.cabig.caaers.ws.PriorTherapyManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.PriorTherapyManagementWebService", serviceName="PriorTherapyManagementWebService", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class PriorTherapyManagementWebServiceImpl implements PriorTherapyManagementWebService{
	
	private static Log logger = LogFactory.getLog(PriorTherapyManagementWebServiceImpl.class);
	private PriorTherapyLOVService priorTherapyLOVService;
	private DomainObjectConverter domainObjectConverter;

	public void setDomainObjectConverter(DomainObjectConverter domainObjectConverter) {
		this.domainObjectConverter = domainObjectConverter;
	}

	public void setPriorTherapyLOVService(
			PriorTherapyLOVService priorTherapyLOVService) {
		this.priorTherapyLOVService = priorTherapyLOVService;
	}

	public CaaersServiceResponse importPriorTherapies(PriorTherapies xmlPriorTherapies)
			throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = new CaaersServiceResponse();
		ServiceResponse serviceResponse = new ServiceResponse();
		caaersResponse.setServiceResponse(serviceResponse);
		
		List<PriorTherapy> domainTherapies = new ArrayList<PriorTherapy>();
		EntityProcessingOutcomes entityProcessingOutcomeTypes = new EntityProcessingOutcomes();
		serviceResponse.setEntityProcessingOutcomes(entityProcessingOutcomeTypes);
		try {
			for(PriorTherapyType priorTherapyDto: xmlPriorTherapies.getPriorTherapy()){
				PriorTherapy domainTherapy = new PriorTherapy();
				domainTherapy.setText(priorTherapyDto.getText());
				domainTherapies.add(domainTherapy);
			}
			List<EntityErrorMessage> entityErrorMessages = priorTherapyLOVService.importPriorTherapies(domainTherapies);
			domainObjectConverter.convertEntityProcessingOutcomes(entityErrorMessages, entityProcessingOutcomeTypes);
		} catch (Throwable e) {
			logger.debug(e.getMessage());
			SecurityExceptionFault fault = new SecurityExceptionFault();
			String message = "The user doesn't have access to create new therapies";
			fault.setMessage(message);
			throw new SecurityExceptionFaultMessage(e.getMessage(), fault);
		}
		
		return caaersResponse;
	}

}
