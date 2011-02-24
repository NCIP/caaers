package gov.nih.nci.ess.safetyreporting.ruleseval.client;

import java.io.InputStream;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axis.EngineConfiguration;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.Stub;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.apache.axis.types.URI.MalformedURIException;

import org.oasis.wsrf.properties.GetResourcePropertyResponse;

import org.globus.gsi.GlobusCredential;

import gov.nih.nci.ess.safetyreporting.ruleseval.stubs.SafetyReportRulesEvaluationPortType;
import gov.nih.nci.ess.safetyreporting.ruleseval.stubs.service.SafetyReportRulesEvaluationServiceAddressingLocator;
import gov.nih.nci.ess.safetyreporting.ruleseval.common.SafetyReportRulesEvaluationI;
import gov.nih.nci.cagrid.introduce.security.client.ServiceSecurityClient;

/**
 * This class is autogenerated, DO NOT EDIT GENERATED GRID SERVICE ACCESS METHODS.
 *
 * This client is generated automatically by Introduce to provide a clean unwrapped API to the
 * service.
 *
 * On construction the class instance will contact the remote service and retrieve it's security
 * metadata description which it will use to configure the Stub specifically for each method call.
 * 
 * @created by Introduce Toolkit version 1.3
 */
public class SafetyReportRulesEvaluationClient extends SafetyReportRulesEvaluationClientBase implements SafetyReportRulesEvaluationI {	

	public SafetyReportRulesEvaluationClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);	
	}

	public SafetyReportRulesEvaluationClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	}
	
	public SafetyReportRulesEvaluationClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}
	
	public SafetyReportRulesEvaluationClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
	}

	public static void usage(){
		System.out.println(SafetyReportRulesEvaluationClient.class.getName() + " -url <service url>");
	}
	
	public static void main(String [] args){
	    System.out.println("Running the Grid Service Client");
		try{
		if(!(args.length < 2)){
			if(args[0].equals("-url")){
			  SafetyReportRulesEvaluationClient client = new SafetyReportRulesEvaluationClient(args[1]);
			  // place client calls here if you want to use this main as a
			  // test....
			} else {
				usage();
				System.exit(1);
			}
		} else {
			usage();
			System.exit(1);
		}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

  public org.oasis.wsrf.lifetime.DestroyResponse destroy(org.oasis.wsrf.lifetime.Destroy params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"destroy");
    return portType.destroy(params);
    }
  }

  public org.oasis.wsrf.lifetime.SetTerminationTimeResponse setTerminationTime(org.oasis.wsrf.lifetime.SetTerminationTime params) throws RemoteException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"setTerminationTime");
    return portType.setTerminationTime(params);
    }
  }

  public gov.nih.nci.ess.safetyreporting.types.DSET_ReportDefinition evaluateAgainstSafetyReportingRules(ess.caaers.nci.nih.gov.Id adverseEventId,ess.caaers.nci.nih.gov.Id problemId,ess.caaers.nci.nih.gov.Id studyId,ess.caaers.nci.nih.gov.Id organizationId) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"evaluateAgainstSafetyReportingRules");
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequest params = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequest();
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestAdverseEventId adverseEventIdContainer = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestAdverseEventId();
    adverseEventIdContainer.setId(adverseEventId);
    params.setAdverseEventId(adverseEventIdContainer);
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestProblemId problemIdContainer = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestProblemId();
    problemIdContainer.setId(problemId);
    params.setProblemId(problemIdContainer);
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestStudyId studyIdContainer = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestStudyId();
    studyIdContainer.setId(studyId);
    params.setStudyId(studyIdContainer);
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestOrganizationId organizationIdContainer = new gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesRequestOrganizationId();
    organizationIdContainer.setId(organizationId);
    params.setOrganizationId(organizationIdContainer);
    gov.nih.nci.ess.safetyreporting.ruleseval.stubs.EvaluateAgainstSafetyReportingRulesResponse boxedResult = portType.evaluateAgainstSafetyReportingRules(params);
    return boxedResult.getDSET_ReportDefinition();
    }
  }

}
