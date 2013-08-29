/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws.impl;

import gov.nih.nci.cabig.caaers.api.impl.IDServiceImpl;
import gov.nih.nci.cabig.caaers.integration.schema.aereportid.ReportIdCriteria;
import gov.nih.nci.cabig.caaers.integration.schema.aereportid.SafetyReportIdentifer;
import gov.nih.nci.cabig.caaers.ws.SafetyReportIdService;
import gov.nih.nci.cabig.caaers.ws.faults.CaaersFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author: vinodh
 */
@WebService(endpointInterface="gov.nih.nci.cabig.caaers.ws.SafetyReportIdService",
        serviceName="SafetyReportIdService", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereportid")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)

public class SafetyReportIdServiceImpl implements SafetyReportIdService {
	
	private static Log logger = LogFactory.getLog(SafetyReportIdServiceImpl.class);
	
	private IDServiceImpl idServiceImpl;
	
	public IDServiceImpl getIdServiceImpl() {
		return idServiceImpl;
	}

	public void setIdServiceImpl(IDServiceImpl idServiceImpl) {
		this.idServiceImpl = idServiceImpl;
	}


	@WebMethod
	public SafetyReportIdentifer generateSafetyReportId(@WebParam(name = "ReportIdCriteria",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereportid") ReportIdCriteria reportIdCriteria) throws CaaersFault {
		
			return idServiceImpl.generateSafetyReportId(reportIdCriteria);
    }

}
