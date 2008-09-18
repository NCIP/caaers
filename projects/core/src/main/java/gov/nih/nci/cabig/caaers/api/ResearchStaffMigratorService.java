package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.ResearchStaffType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="ResearchStaffMigratorServiceIntf",targetNamespace="http://researchstaff.schema.integration.caaers.cabig.nci.nih.gov")
public interface ResearchStaffMigratorService {
	//public void createStudy(@WebParam(name="Study") gov.nih.nci.cabig.caaers.webservice.Study xmlStudy);
	
	@WebMethod
	public CaaersServiceResponse saveResearchStaff(@WebParam(name="Staff") gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff staff) ;//throws RemoteException;
	
	public List<DomainObjectImportOutcome<ResearchStaff>> getImportableResearchStaff();
	
	public List<DomainObjectImportOutcome<ResearchStaff>> getNonImportableResearchStaff();
	
	public DomainObjectImportOutcome<ResearchStaff> processResearchStaff(ResearchStaffType xmlResearchStaff);
	
	//public void deleteResearchStaff(Staff staff) throws RemoteException;
}
