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
	
  public void updateCodingTerminologyForStudy(ess.caaers.nci.nih.gov.Id studyId,ess.caaers.nci.nih.gov.Oid termCode,ess.caaers.nci.nih.gov.Oid termVersion,ess.caaers.nci.nih.gov.Oid otherMeddra) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
	initialize();
    aeProtocolI.updateCodingTerminologyForStudy(studyId, termCode, termVersion, otherMeddra);
  }

}

