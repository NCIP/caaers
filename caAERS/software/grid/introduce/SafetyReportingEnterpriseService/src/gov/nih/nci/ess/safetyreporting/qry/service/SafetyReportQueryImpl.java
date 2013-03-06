/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.safetyreporting.qry.service;

import java.rmi.RemoteException;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class SafetyReportQueryImpl extends SafetyReportQueryImplBase {

	
	public SafetyReportQueryImpl() throws RemoteException {
		super();
	}
	
  public gov.nih.nci.ess.safetyreporting.types.DSET_SafetyReportVersion querySafetyReports(gov.nih.nci.ess.safetyreporting.types.SafetyReportVersion safetyReportVersion) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    //TODO: Implement this autogenerated method
    throw new RemoteException("Not yet implemented");
  }

  public gov.nih.nci.ess.safetyreporting.types.DSET_SafetyReportVersion getSafetyReports(_21090.org.iso.DSET_II dSET_II) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    //TODO: Implement this autogenerated method
    throw new RemoteException("Not yet implemented");
  }

  public gov.nih.nci.ess.safetyreporting.types.SafetyReportFormat viewSafetyReport(ess.caaers.nci.nih.gov.Id safetyReportId,_21090.org.iso.ST desiredFormat) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    //TODO: Implement this autogenerated method
    throw new RemoteException("Not yet implemented");
  }

}

