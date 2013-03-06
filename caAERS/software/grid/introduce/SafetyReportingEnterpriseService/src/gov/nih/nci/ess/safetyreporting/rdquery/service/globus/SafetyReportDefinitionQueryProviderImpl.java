/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.safetyreporting.rdquery.service.globus;

import gov.nih.nci.ess.safetyreporting.rdquery.service.SafetyReportDefinitionQueryImpl;

import java.rmi.RemoteException;

/** 
 * DO NOT EDIT:  This class is autogenerated!
 *
 * This class implements each method in the portType of the service.  Each method call represented
 * in the port type will be then mapped into the unwrapped implementation which the user provides
 * in the SafetyReportingEnterpriseServiceImpl class.  This class handles the boxing and unboxing of each method call
 * so that it can be correclty mapped in the unboxed interface that the developer has designed and 
 * has implemented.  Authorization callbacks are automatically made for each method based
 * on each methods authorization requirements.
 * 
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class SafetyReportDefinitionQueryProviderImpl{
	
	SafetyReportDefinitionQueryImpl impl;
	
	public SafetyReportDefinitionQueryProviderImpl() throws RemoteException {
		impl = new SafetyReportDefinitionQueryImpl();
	}
	

    public gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionResponse querySafetyReportDefinition(gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionRequest params) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionResponse boxedResult = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionResponse();
    boxedResult.setDSET_ReportDefinition(impl.querySafetyReportDefinition(params.getReportDefinition().getReportDefinition()));
    return boxedResult;
  }

    public gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionResponse getSafetyReportDefinition(gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionRequest params) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionResponse boxedResult = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionResponse();
    boxedResult.setReportDefinition(impl.getSafetyReportDefinition(params.getReportDefinitionId().getId()));
    return boxedResult;
  }

}
