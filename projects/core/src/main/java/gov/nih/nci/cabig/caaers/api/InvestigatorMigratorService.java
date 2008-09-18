package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.investigator.InvestigatorType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="InvestigatorMigratorServiceIntf",targetNamespace="http://investigator.schema.integration.caaers.cabig.nci.nih.gov")

public interface InvestigatorMigratorService {
	
	@WebMethod
	public CaaersServiceResponse saveInvestigator(@WebParam(name="Staff") gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff staff) ;//throws RemoteException;
	
	public List<DomainObjectImportOutcome<Investigator>> getImportableInvestigators();
	
	public List<DomainObjectImportOutcome<Investigator>> getNonImportableInvestigators();
	
	public DomainObjectImportOutcome<Investigator> processInvestigator(InvestigatorType xmlInvestigator);
	
	//public List<InvestigatorType> getNonImportableInvestigators();
	
	//public void deleteInvestigator(Staff staff) throws RemoteException;
}
