package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.integration.schema.researchstaff.Staff;

import java.rmi.RemoteException;

public interface ResearchStaffMigratorService {

	public void saveResearchStaff(Staff staff) throws RemoteException;
}
