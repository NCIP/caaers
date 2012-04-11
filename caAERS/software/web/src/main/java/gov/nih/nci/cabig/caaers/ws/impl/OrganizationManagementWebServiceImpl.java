package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.OrganizationManagementService;
import gov.nih.nci.cabig.caaers.domain.EntityErrorMessage;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.EntityErrorMessages;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
import gov.nih.nci.cabig.caaers.integration.schema.common.SecurityExceptionFault;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.organization.Organizations;
import gov.nih.nci.cabig.caaers.service.migrator.OrganizationConverter;
import gov.nih.nci.cabig.caaers.ws.OrganizationManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Exposes the OrganizationManagementWebservie, and will delegate all the requests to OrganizationServiceImpl
 * @author Ramakrishna
 */

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.OrganizationManagementWebService", serviceName="OrganizationManagementWebService", targetNamespace="http://webservice.caaers.cabig.nci.nih.gov/organization")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class OrganizationManagementWebServiceImpl implements OrganizationManagementWebService {
    
	private OrganizationManagementService organizationManagementService;
	private OrganizationConverter organizationConverter;
    private static Log logger = LogFactory.getLog(OrganizationManagementWebServiceImpl.class);
    
	public CaaersServiceResponse createOrUpdateOrganization(
			Organizations xmlOrganizations) throws SecurityExceptionFaultMessage {
		
		CaaersServiceResponse caaersResponse = new CaaersServiceResponse();
		ServiceResponse serviceResponse = new ServiceResponse();
		caaersResponse.setServiceResponse(serviceResponse);
		
		List<Organization> domainOrganizations = new ArrayList<Organization>();
		EntityErrorMessages entityErrorMessageTypes = new EntityErrorMessages();
		serviceResponse.setEntityErrorMessages(entityErrorMessageTypes);
		try {
			for(OrganizationType organizationDto: xmlOrganizations.getOrganization()){
				Organization organization = new LocalOrganization();
				organizationConverter.convertOrganizationDtoToDomainOrganization(organizationDto, organization);
				domainOrganizations.add(organization);
			}
			List<EntityErrorMessage> entityErrorMessages = organizationManagementService.createOrUpdateOrganizations(domainOrganizations);
			organizationConverter.convertEntityErrorMessages(entityErrorMessages, entityErrorMessageTypes);
		} catch (Throwable e) {
			logger.debug(e.getMessage());
			SecurityExceptionFault fault = new SecurityExceptionFault();
			String message = "The user doesn't have access to do create/update organization operations";
			fault.setMessage(message);
			throw new SecurityExceptionFaultMessage(e.getMessage(), fault);
		}
		
		return caaersResponse;
	}
	

	public void setOrganizationManagementService(
			OrganizationManagementService organizationManagementService) {
		this.organizationManagementService = organizationManagementService;
	}



	public void setOrganizationConverter(OrganizationConverter organizationConverter) {
		this.organizationConverter = organizationConverter;
	}


}
