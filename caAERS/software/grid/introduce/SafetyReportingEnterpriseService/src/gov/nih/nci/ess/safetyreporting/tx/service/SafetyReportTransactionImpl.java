package gov.nih.nci.ess.safetyreporting.tx.service;

import gov.nih.nci.ess.safetyreporting.misc.SpringContextCreator;
import gov.nih.nci.ess.safetyreporting.tx.common.SafetyReportTransactionI;

import java.rmi.RemoteException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class SafetyReportTransactionImpl extends SafetyReportTransactionImplBase {

	private static final String BEAN_NAME = "safetyReportTransactionImpl";
	
	private SafetyReportTransactionI safetyReportTransactionI; 

	
	public SafetyReportTransactionImpl() throws RemoteException {
		super();
	}
	
	/**
	 * @throws BeansException
	 */
	private void initialize() throws BeansException {
		if (safetyReportTransactionI == null) {
			ApplicationContext ctx = SpringContextCreator.getApplicationContext();
			safetyReportTransactionI = (SafetyReportTransactionI) ctx.getBean(BEAN_NAME);
		}
	}
	
	
  public void amendSafetyReport(ess.caaers.nci.nih.gov.Id safetyReportId,ess.caaers.nci.nih.gov.Id reportDefinitionId) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
	  initialize();
	  safetyReportTransactionI.amendSafetyReport(safetyReportId, reportDefinitionId);
  }

  public void submitSafetyReport(ess.caaers.nci.nih.gov.Id safetyReportId,ess.caaers.nci.nih.gov.Id reportDefinitionId,ess.caaers.nci.nih.gov.Id submitterId,_21090.org.iso.DSET_TEL additionalRecipientEmails) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
	  initialize();
	  safetyReportTransactionI.submitSafetyReport(safetyReportId, reportDefinitionId, submitterId, additionalRecipientEmails);
  }

  public void withdrawSafetyReport(ess.caaers.nci.nih.gov.Id safetyReportId,ess.caaers.nci.nih.gov.Id reportDefinitionId) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
	  initialize();
	  safetyReportTransactionI.withdrawSafetyReport(safetyReportId, reportDefinitionId);
  }

}

