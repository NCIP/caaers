package gov.nih.nci.cabig.caaers.ws;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="ResearchStaffServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov")
public interface ResearchStaffService {

	@WebMethod
	public CaaersServiceResponse saveResearchStaff(@WebParam(name="Staff", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov") gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff);

}
