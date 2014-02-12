/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.BaseAdverseEventReport;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author: Biju Joseph
 */
@WebService(name="SafetyReportManagementServiceInterface", targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport")
public interface SafetyReportManagementService {
	
    /**
     * Will initiate ExpeditedAdverseEvent reporting action, like, create, update/replace, amend, etc
     * Will schedule notifications as per ReportDefinition
     * @param xmlAdverseEventReport - The XML version of bare minimum ExpeditedAdverseEventReport 
     * @return
     */
	@WebResult(targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common",
			     name="CaaersServiceResponse")
    public CaaersServiceResponse initiateSafetyReportAction(@WebParam(name = "AdverseEventReport",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") BaseAdverseEventReport xmlAdverseEventReport);

	
    /**
     * Will create/update/amend ExpeditedAdverseEvent report and will submit the associated Report to the external recipients. The external recipient details will be picked
     * from the associated report definition.
     * @param xmlAdverseEventReport - The XML version of ExpeditedAdverseEventReport
     * @return
     */
	@WebResult(targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common",
			     name="CaaersServiceResponse")
    public CaaersServiceResponse submitSafetyReport(@WebParam(name = "AdverseEventReport",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport);

    /**
     * Will create/update/amend ExpeditedAdverseEvent report.
     * from the associated report definition.
     * @param xmlAdverseEventReport - The XML version of ExpeditedAdverseEventReport
     * @return
     */
    @WebResult(targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/common",
			     name="CaaersServiceResponse")
    public CaaersServiceResponse saveSafetyReport(@WebParam(name = "AdverseEventReport",targetNamespace = "http://schema.integration.caaers.cabig.nci.nih.gov/aereport") AdverseEventReport xmlAdverseEventReport);


}
