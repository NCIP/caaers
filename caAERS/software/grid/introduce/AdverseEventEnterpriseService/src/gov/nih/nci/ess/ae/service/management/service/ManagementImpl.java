/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.ae.service.management.service;

import gov.nih.nci.ess.ae.service.management.common.ManagementI;
import gov.nih.nci.ess.ae.service.misc.SpringContextCreator;

import java.rmi.RemoteException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * TODO:I am the service side implementation class. IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class ManagementImpl extends ManagementImplBase {

	private static final String BEAN_NAME = "adverseEventManagementImpl";

	private ManagementI aeManagement;

	public ManagementImpl() throws RemoteException {
		super();		
	}

	/**
	 * @throws BeansException
	 */
	private void initialize() throws BeansException {
		if (aeManagement == null) {
			ApplicationContext ctx = SpringContextCreator.getApplicationContext();
			aeManagement = (ManagementI) ctx.getBean(BEAN_NAME);
		}
	}

	
  public ess.caaers.nci.nih.gov.AdverseEvent initiateAdverseEvent(ess.caaers.nci.nih.gov.Id subjectIdentifier,ess.caaers.nci.nih.gov.Id studyIdentifier,ess.caaers.nci.nih.gov.AdverseEvent adverseEvent,ess.caaers.nci.nih.gov.TsDateTime courseStartDate) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	    initialize();
		return aeManagement.initiateAdverseEvent(subjectIdentifier,
				studyIdentifier, adverseEvent, courseStartDate);
	}

  public ess.caaers.nci.nih.gov.AdverseEvent updateAdverseEvent(ess.caaers.nci.nih.gov.AdverseEvent adverseEvent) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
  	    initialize();
		return aeManagement.updateAdverseEvent(adverseEvent);
	}

  public ess.caaers.nci.nih.gov.AdverseEvent deactivateAdverseEvent(ess.caaers.nci.nih.gov.Id adverseEventIdentifier) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	    initialize();
		return aeManagement.deactivateAdverseEvent(adverseEventIdentifier);
	}

}
