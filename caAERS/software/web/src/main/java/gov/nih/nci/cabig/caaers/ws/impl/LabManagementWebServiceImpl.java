/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.LabService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.LabCategory;
import gov.nih.nci.cabig.caaers.integration.schema.common.AgentType;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.LabCategories;
import gov.nih.nci.cabig.caaers.integration.schema.common.LabCategoryType;
import gov.nih.nci.cabig.caaers.service.migrator.LabConverter;
import gov.nih.nci.cabig.caaers.ws.LabManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.LabManagementWebService", serviceName="LabManagementWebService",
        targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class LabManagementWebServiceImpl implements LabManagementWebService{
	
	private static Log logger = LogFactory.getLog(LabManagementWebServiceImpl.class);
	private LabService labService;

	public CaaersServiceResponse createOrUpdateLabs(LabCategories xmlLabCategories) throws SecurityExceptionFaultMessage {
		CaaersServiceResponse caaersResponse = Helper.createResponse();
		
		List<LabCategory> domainLabCategories = new ArrayList<LabCategory>();
		try {
			for(LabCategoryType labCategoryDto: xmlLabCategories.getLabCategory()){
				LabCategory labCategory = LabConverter.convert(labCategoryDto);
				domainLabCategories.add(labCategory);
			}
			List<ProcessingOutcome> processingOutcomes = labService.createOrUpdateLabs(domainLabCategories);
            for(ProcessingOutcome outcome : processingOutcomes){
                Helper.populateProcessingOutcome(caaersResponse, outcome);
            }
		} catch (Throwable e) {
            logger.error(e);
            Helper.populateError(caaersResponse, "WS_GEN_000", "Unable to process the request :" + e.getMessage());
		}
		
		return caaersResponse;
	}

	public void setLabService(LabService labService) {
		this.labService = labService;
	}

}
