package gov.nih.nci.ess.sr.service.safetyreportreview.common;

import java.rmi.RemoteException;

/** 
 * This class is autogenerated, DO NOT EDIT.
 * 
 * This interface represents the API which is accessible on the grid service from the client. 
 * 
 * @created by Introduce Toolkit version 1.4
 * 
 */
public interface SafetyReportReviewI {

  public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy params) throws RemoteException ;

  public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime params) throws RemoteException ;

  public void createSafetyReportReviewProcess() throws RemoteException ;

  public void updateSafetyReportReviewProcess() throws RemoteException ;

  public void deactivateSafetyReportReviewProcess() throws RemoteException ;

  public void getSafetyReportReviewProcess() throws RemoteException ;

  public void reviewSafetyReport() throws RemoteException ;

}

