package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.investigator.Staff;

import java.rmi.RemoteException;

public interface InvestigatorMigratorService {
	public void saveInvestigator(Staff staff) throws RemoteException;
}
