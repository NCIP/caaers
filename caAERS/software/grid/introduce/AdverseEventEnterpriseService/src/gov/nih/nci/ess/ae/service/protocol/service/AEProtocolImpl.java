package gov.nih.nci.ess.ae.service.protocol.service;

import gov.nih.nci.ess.ae.service.misc.SpringContextCreator;
import gov.nih.nci.ess.ae.service.protocol.common.AEProtocolI;

import java.rmi.RemoteException;

import org.springframework.context.ApplicationContext;

/** 
 * TODO:I am the service side implementation class.  IMPLEMENT AND DOCUMENT ME
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class AEProtocolImpl extends AEProtocolImplBase {

	private static final String BEAN_NAME = "adverseEventProtocolImpl";
	
	private AEProtocolI aeProtocolI;
	
	
	public AEProtocolImpl() throws RemoteException {
		super();
	}
	
	/**
	 * Will try to locate a pre-existent {@link ApplicationContext}; if failed,
	 * will create it explicitly.
	 * 
	 * @see http://jira.semanticbits.com/browse/CAAERS-4291
	 */
	private synchronized void initialize() {
		if (aeProtocolI==null) {
			ApplicationContext ctx = SpringContextCreator.getApplicationContext();
			aeProtocolI = (AEProtocolI) ctx.getBean(BEAN_NAME);			
		}
	}
	
  public void updateCodingTerminologyForStudy(ess.caaers.nci.nih.gov.AeTerminology aeTerminology) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	initialize();
    aeProtocolI.updateCodingTerminologyForStudy(aeTerminology);
  }

  public ess.caaers.nci.nih.gov.AeTerminology getCodingTerminologyForStudy(ess.caaers.nci.nih.gov.Id studyId) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	initialize();
    return aeProtocolI.getCodingTerminologyForStudy(studyId);
  }

  public void updateExpectedAdverseEventsForStudy(ess.caaers.nci.nih.gov.Id studyId,_21090.org.iso.DSET_II ctcOrMeddraCode) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  initialize();
	  aeProtocolI.updateExpectedAdverseEventsForStudy(studyId, ctcOrMeddraCode);
  }

  public ess.caaers.nci.nih.gov.DSET_ExpectedAdverseEvent getExpectedAdverseEventsForStudy(ess.caaers.nci.nih.gov.Id studyId) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  initialize();
	  return aeProtocolI.getExpectedAdverseEventsForStudy(studyId);
	  
  }

  public void updateSolicitedAdverseEventsForStudyEpoch(ess.caaers.nci.nih.gov.Id studyId,_21090.org.iso.ST epochName,_21090.org.iso.DSET_II ctcOrMeddraCode) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  initialize();
	  aeProtocolI.updateSolicitedAdverseEventsForStudyEpoch(studyId, epochName, ctcOrMeddraCode);
  }

  public ess.caaers.nci.nih.gov.DSET_SolicitedAdverseEvent getSolicitedAdverseEventsForStudyEpoch(ess.caaers.nci.nih.gov.Id studyId,_21090.org.iso.ST epochName) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	  initialize();
	  return aeProtocolI.getSolicitedAdverseEventsForStudyEpoch(studyId, epochName);
  }

}

