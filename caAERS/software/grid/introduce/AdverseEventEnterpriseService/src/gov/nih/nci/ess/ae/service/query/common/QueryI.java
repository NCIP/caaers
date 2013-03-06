/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.ae.service.query.common;

import java.rmi.RemoteException;

/** 
 * This class is autogenerated, DO NOT EDIT.
 * 
 * This interface represents the API which is accessable on the grid service from the client. 
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public interface QueryI {

  /**
   * find AEs by example
   *
   * @param adverseEvent
   * @throws AdverseEventServiceException
   *	
   */
  public ess.caaers.nci.nih.gov.DSET_AdverseEvent findAdverseEvents(ess.caaers.nci.nih.gov.AdverseEvent adverseEvent) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException ;

  /**
   * get AE data for given id
   *
   * @param adverseEventIdentifier
   * @throws AdverseEventServiceException
   *	
   */
  public ess.caaers.nci.nih.gov.AdverseEvent getAdverseEventData(ess.caaers.nci.nih.gov.Id adverseEventIdentifier) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException ;

}

