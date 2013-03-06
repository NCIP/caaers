/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.api.PriorTherapyManagementService;
import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.domain.PriorTherapy;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapies;
import gov.nih.nci.cabig.caaers.integration.schema.common.PriorTherapyType;
import gov.nih.nci.cabig.caaers.ws.PriorTherapyManagementWebService;
import gov.nih.nci.cabig.caaers.ws.faults.SecurityExceptionFaultMessage;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.PriorTherapyManagementWebService", serviceName="PriorTherapyManagementWebService", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class PriorTherapyManagementWebServiceImpl implements PriorTherapyManagementWebService{
	
	private static Log logger = LogFactory.getLog(PriorTherapyManagementWebServiceImpl.class);
	private PriorTherapyManagementService priorTherapyManagementService;

    public PriorTherapyManagementService getPriorTherapyManagementService() {
        return priorTherapyManagementService;
    }

    public void setPriorTherapyManagementService(PriorTherapyManagementService priorTherapyManagementService) {
        this.priorTherapyManagementService = priorTherapyManagementService;
    }

    public CaaersServiceResponse createOrUpdatePriorTherapy(@WebParam(name = "PriorTherapies",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/common") PriorTherapies xmlPriorTherapies)
            throws SecurityExceptionFaultMessage {
        return priorTherapyManagementService.importPriorTherapies(xmlPriorTherapies);
    }

}
