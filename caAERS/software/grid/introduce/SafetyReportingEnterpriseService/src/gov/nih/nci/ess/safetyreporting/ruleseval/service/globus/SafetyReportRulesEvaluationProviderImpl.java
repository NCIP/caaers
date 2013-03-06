/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.ess.safetyreporting.ruleseval.service.globus;

import gov.nih.nci.ess.safetyreporting.ruleseval.service.SafetyReportRulesEvaluationImpl;

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
public class SafetyReportRulesEvaluationProviderImpl{
	
	SafetyReportRulesEvaluationImpl impl;
	
	public SafetyReportRulesEvaluationProviderImpl() throws RemoteException {
		impl = new SafetyReportRulesEvaluationImpl();
	}
	

    public gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesResponse evaluateAgainstSafetyReportingRules(gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequest params) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesResponse boxedResult = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesResponse();
    boxedResult.setDSET_ReportDefinition(impl.evaluateAgainstSafetyReportingRules(params.getAdverseEventId().getId(),params.getProblemId().getId(),params.getStudyId().getId(),params.getOrganizationId().getId()));
    return boxedResult;
  }

}
