package gov.nih.nci.ess.sr.service.safetyreportquery.client;

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

import gov.nih.nci.ess.sr.service.safetyreportquery.stubs.SafetyReportQueryPortType;
import gov.nih.nci.ess.sr.service.safetyreportquery.stubs.service.SafetyReportQueryServiceAddressingLocator;
import gov.nih.nci.ess.sr.service.safetyreportquery.common.SafetyReportQueryI;
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
 * @created by Introduce Toolkit version 1.4
 */
public class SafetyReportQueryClient extends SafetyReportQueryClientBase implements SafetyReportQueryI {	

	public SafetyReportQueryClient(String url) throws MalformedURIException, RemoteException {
		this(url,null);	
	}

	public SafetyReportQueryClient(String url, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(url,proxy);
	}
	
	public SafetyReportQueryClient(EndpointReferenceType epr) throws MalformedURIException, RemoteException {
	   	this(epr,null);
	}
	
	public SafetyReportQueryClient(EndpointReferenceType epr, GlobusCredential proxy) throws MalformedURIException, RemoteException {
	   	super(epr,proxy);
	}

	public static void usage(){
		System.out.println(SafetyReportQueryClient.class.getName() + " -url <service url>");
	}
	
	public static void main(String [] args){
	    System.out.println("Running the Grid Service Client");
		try{
		if(!(args.length < 2)){
			if(args[0].equals("-url")){
			  SafetyReportQueryClient client = new SafetyReportQueryClient(args[1]);
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

  public sr.DSET_SafetyReportVersion querySafetyReports(sr.SafetyReportVersion safetyReportVersion) throws RemoteException, gov.nih.nci.ess.sr.service.safetyreportquery.stubs.types.SafetyReportException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"querySafetyReports");
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.QuerySafetyReportsRequest params = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.QuerySafetyReportsRequest();
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.QuerySafetyReportsRequestSafetyReportVersion safetyReportVersionContainer = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.QuerySafetyReportsRequestSafetyReportVersion();
    safetyReportVersionContainer.setSafetyReportVersion(safetyReportVersion);
    params.setSafetyReportVersion(safetyReportVersionContainer);
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.QuerySafetyReportsResponse boxedResult = portType.querySafetyReports(params);
    return boxedResult.getDSET_SafetyReportVersion();
    }
  }

  public sr.DSET_SafetyReportVersion getSafetyReports(_21090.org.iso.DSET_II safetyReportIdentifiers) throws RemoteException, gov.nih.nci.ess.sr.service.safetyreportquery.stubs.types.SafetyReportException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"getSafetyReports");
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.GetSafetyReportsRequest params = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.GetSafetyReportsRequest();
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.GetSafetyReportsRequestSafetyReportIdentifiers safetyReportIdentifiersContainer = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.GetSafetyReportsRequestSafetyReportIdentifiers();
    safetyReportIdentifiersContainer.setDSET_II(safetyReportIdentifiers);
    params.setSafetyReportIdentifiers(safetyReportIdentifiersContainer);
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.GetSafetyReportsResponse boxedResult = portType.getSafetyReports(params);
    return boxedResult.getDSET_SafetyReportVersion();
    }
  }

  public sr.SafetyReportFormat viewSafetyReport(ess.caaers.nci.nih.gov.Id safetyReportIdentifier,_21090.org.iso.ST desiredFormat) throws RemoteException, gov.nih.nci.ess.sr.service.safetyreportquery.stubs.types.SafetyReportException {
    synchronized(portTypeMutex){
      configureStubSecurity((Stub)portType,"viewSafetyReport");
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequest params = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequest();
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequestSafetyReportIdentifier safetyReportIdentifierContainer = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequestSafetyReportIdentifier();
    safetyReportIdentifierContainer.setId(safetyReportIdentifier);
    params.setSafetyReportIdentifier(safetyReportIdentifierContainer);
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequestDesiredFormat desiredFormatContainer = new gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportRequestDesiredFormat();
    desiredFormatContainer.setST(desiredFormat);
    params.setDesiredFormat(desiredFormatContainer);
    gov.nih.nci.ess.sr.service.safetyreportquery.stubs.ViewSafetyReportResponse boxedResult = portType.viewSafetyReport(params);
    return boxedResult.getSafetyReportFormat();
    }
  }

}
