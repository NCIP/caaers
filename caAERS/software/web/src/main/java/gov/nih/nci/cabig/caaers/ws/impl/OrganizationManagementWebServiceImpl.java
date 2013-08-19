/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.api.OrganizationManagementService;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.ActiveInactiveStatus;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.OrganizationType;
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

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.OrganizationManagementWebService",
        serviceName="OrganizationManagementWebService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class OrganizationManagementWebServiceImpl implements OrganizationManagementWebService {
    
	private OrganizationManagementService organizationManagementService;
	private OrganizationConverter organizationConverter;
    private static Log logger = LogFactory.getLog(OrganizationManagementWebServiceImpl.class);
    
	public CaaersServiceResponse createOrUpdateOrganization(Organizations xmlOrganizations) throws SecurityExceptionFaultMessage {
		
		CaaersServiceResponse caaersResponse = Helper.createResponse();

		List<Organization> domainOrganizations = new ArrayList<Organization>();
		try {

            for(OrganizationType organizationDto: xmlOrganizations.getOrganization()){

                if ( ! (organizationDto.getStatus().equalsIgnoreCase(ActiveInactiveStatus.AC.getCode())  || organizationDto.getStatus().equalsIgnoreCase(ActiveInactiveStatus.IN.getCode()))) {
                    // Log the Error.
                    continue;
                  //  Helper.populateError(caaersResponse, "WS_OS_01", "Organization status must be either Active or InActive. Problem with Organization : " + organizationDto.getNciInstituteCode());
                    //return caaersResponse;
                }

				Organization organization = new LocalOrganization();
				organizationConverter.convertOrganizationDtoToDomainOrganization(organizationDto, organization);
				domainOrganizations.add(organization);
			}

			List<ProcessingOutcome> processingOutcomes = organizationManagementService.createOrUpdateOrganizations(domainOrganizations);
            for(ProcessingOutcome outcome : processingOutcomes){
                Helper.populateProcessingOutcome(caaersResponse, outcome);
            }
		} catch (Throwable e) {
            logger.error(e);
			Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
		}
		
		return caaersResponse;
	}
	

	public CaaersServiceResponse mergeOrganization(Organizations xmlOrganizations) throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = Helper.createResponse();
		
		List<Organization> domainOrganizations = new ArrayList<Organization>();
		try {
			for(OrganizationType organizationDto: xmlOrganizations.getOrganization()){

                if ( ! (organizationDto.getStatus().equalsIgnoreCase(ActiveInactiveStatus.AC.getCode())  || organizationDto.getStatus().equalsIgnoreCase(ActiveInactiveStatus.IN.getCode()))) {
                    // Log the Error.
                    continue;
                   // Helper.populateError(caaersResponse, "WS_OS_01", "Organization status must be either Active or InActive. Problem with Organization : " + organizationDto.getNciInstituteCode());
                   // return caaersResponse;
                }


                Organization organization = new LocalOrganization();
				organizationConverter.convertOrganizationDtoToDomainOrganization(organizationDto, organization);
				domainOrganizations.add(organization);
			}
			List<ProcessingOutcome> processingOutcomes = organizationManagementService.mergeOrganizations(domainOrganizations);
            for(ProcessingOutcome outcome : processingOutcomes){
                Helper.populateProcessingOutcome(caaersResponse, outcome);
            }
		} catch (Throwable e) {
            logger.error(e);
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
		}
		
		return caaersResponse;
	}

	

	public void setOrganizationManagementService(OrganizationManagementService organizationManagementService) {
		this.organizationManagementService = organizationManagementService;
	}


	public void setOrganizationConverter(OrganizationConverter organizationConverter) {
		this.organizationConverter = organizationConverter;
	}

}
