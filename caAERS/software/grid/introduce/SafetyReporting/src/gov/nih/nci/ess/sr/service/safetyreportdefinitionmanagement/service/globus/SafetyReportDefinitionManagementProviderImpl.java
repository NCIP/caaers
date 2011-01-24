package gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.service.globus;

import gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.service.SafetyReportDefinitionManagementImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the SafetyReportingImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correctly mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.4
 * 
 */
public class SafetyReportDefinitionManagementProviderImpl{
	
	SafetyReportDefinitionManagementImpl impl;
	
	public SafetyReportDefinitionManagementProviderImpl() throws RemoteException {
		impl = new SafetyReportDefinitionManagementImpl();
	}
	

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.CreateSafetyReportDefinitionResponse createSafetyReportDefinition(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.CreateSafetyReportDefinitionRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.CreateSafetyReportDefinitionResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.CreateSafetyReportDefinitionResponse();
    impl.createSafetyReportDefinition();
    return boxedResult;
  }

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDetailsResponse updateSafetyReportDefinitionDetails(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDetailsRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDetailsResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDetailsResponse();
    impl.updateSafetyReportDefinitionDetails();
    return boxedResult;
  }

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDeliveryDetailsResponse updateSafetyReportDefinitionDeliveryDetails(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDeliveryDetailsRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDeliveryDetailsResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionDeliveryDetailsResponse();
    impl.updateSafetyReportDefinitionDeliveryDetails();
    return boxedResult;
  }

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionMandatoryFieldsResponse updateSafetyReportDefinitionMandatoryFields(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionMandatoryFieldsRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionMandatoryFieldsResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportDefinitionMandatoryFieldsResponse();
    impl.updateSafetyReportDefinitionMandatoryFields();
    return boxedResult;
  }

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.DeactivateSafetyReportDefinitionResponse deactivateSafetyReportDefinition(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.DeactivateSafetyReportDefinitionRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.DeactivateSafetyReportDefinitionResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.DeactivateSafetyReportDefinitionResponse();
    impl.deactivateSafetyReportDefinition();
    return boxedResult;
  }

    public gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportTerminologyForStudyResponse updateSafetyReportTerminologyForStudy(gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportTerminologyForStudyRequest params) throws RemoteException {
    gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportTerminologyForStudyResponse boxedResult = new gov.nih.nci.ess.sr.service.safetyreportdefinitionmanagement.stubs.UpdateSafetyReportTerminologyForStudyResponse();
    impl.updateSafetyReportTerminologyForStudy();
    return boxedResult;
  }

}
