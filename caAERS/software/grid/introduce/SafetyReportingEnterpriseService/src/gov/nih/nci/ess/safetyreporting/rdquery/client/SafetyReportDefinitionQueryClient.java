package gov.nih.nci.ess.safetyreporting.rdquery.client;

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

import gov.nih.nci.ess.safetyreporting.rdquery.stubs.SafetyReportDefinitionQueryPortType;
import gov.nih.nci.ess.safetyreporting.rdquery.stubs.service.SafetyReportDefinitionQueryServiceAddressingLocator;
import gov.nih.nci.ess.safetyreporting.rdquery.common.SafetyReportDefinitionQueryI;
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
public class SafetyReportDefinitionQueryClient extends SafetyReportDefinitionQueryClientBase implements SafetyReportDefinitionQueryI {	

	public SafetyReportDefinitionQueryClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);	
	}

	public SafetyReportDefinitionQueryClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	}
	
	public SafetyReportDefinitionQueryClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}
	
	public SafetyReportDefinitionQueryClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
	}

	public static void usage(){
		System.out.println(SafetyReportDefinitionQueryClient.class.getName() + " -url <service url>");
	}
	
	public static void main(String [] args){
	    System.out.println("Running the Grid Service Client");
		try{
		if(!(args.length < 2)){
			if(args[0].equals("-url")){
			  SafetyReportDefinitionQueryClient client = new SafetyReportDefinitionQueryClient(args[1]);
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

  public gov.nih.nci.ess.safetyreporting.types.DSET_ReportDefinition querySafetyReportDefinition(gov.nih.nci.ess.safetyreporting.types.ReportDefinition reportDefinition) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"querySafetyReportDefinition");
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionRequest params = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionRequest();
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionRequestReportDefinition reportDefinitionContainer = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionRequestReportDefinition();
    reportDefinitionContainer.setReportDefinition(reportDefinition);
    params.setReportDefinition(reportDefinitionContainer);
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.QuerySafetyReportDefinitionResponse boxedResult = portType.querySafetyReportDefinition(params);
    return boxedResult.getDSET_ReportDefinition();
    }
  }

  public gov.nih.nci.ess.safetyreporting.types.ReportDefinition getSafetyReportDefinition(ess.caaers.nci.nih.gov.Id reportDefinitionId) throws RemoteException, gov.nih.nci.ess.safetyreporting.management.stubs.types.SafetyReportingServiceException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getSafetyReportDefinition");
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionRequest params = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionRequest();
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionRequestReportDefinitionId reportDefinitionIdContainer = new gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionRequestReportDefinitionId();
    reportDefinitionIdContainer.setId(reportDefinitionId);
    params.setReportDefinitionId(reportDefinitionIdContainer);
    gov.nih.nci.ess.safetyreporting.rdquery.stubs.GetSafetyReportDefinitionResponse boxedResult = portType.getSafetyReportDefinition(params);
    return boxedResult.getReportDefinition();
    }
  }

}
