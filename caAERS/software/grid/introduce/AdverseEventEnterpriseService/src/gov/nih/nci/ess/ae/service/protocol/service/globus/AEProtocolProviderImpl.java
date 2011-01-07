package gov.nih.nci.ess.ae.service.protocol.service.globus;

import gov.nih.nci.ess.ae.service.protocol.service.AEProtocolImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the AdverseEventEnterpriseServiceImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class AEProtocolProviderImpl{
	
	AEProtocolImpl impl;
	
	public AEProtocolProviderImpl() throws RemoteException {
		impl = new AEProtocolImpl();
	}
	

    public gov.nih.nci.ess.ae.service.protocol.stubs.UpdateCodingTerminologyForStudyResponse updateCodingTerminologyForStudy(gov.nih.nci.ess.ae.service.protocol.stubs.UpdateCodingTerminologyForStudyRequest params) throws RemoteException, gov.nih.nci.ess.ae.service.management.stubs.types.AdverseEventServiceException {
    gov.nih.nci.ess.ae.service.protocol.stubs.UpdateCodingTerminologyForStudyResponse boxedResult = new gov.nih.nci.ess.ae.service.protocol.stubs.UpdateCodingTerminologyForStudyResponse();
    impl.updateCodingTerminologyForStudy(params.getId().getId(),params.getOid().getOid());
    return boxedResult;
  }

}
