package webservice.adeers;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import webservice.AdeersWebService;
import caaers.client.AEReportXMLServiceSoapBindingStub;
import caaers.client.AEReportXMLService_ServiceLocator;
import caaers.client.ReportingMode;


public class AdeersWebServiceImpl implements AdeersWebService {
	private String caaersAeReportId = "";
	private String externalEPRs = "";
	private String reportId = "";
	private String submitterEmail = "";
	Logger log = Logger.getLogger(getClass());
	
	public String callWebService(String aeReport) throws Exception {
		
		return submitAEDataXMLAsAttachment(aeReport);
	}
	
	private String submitAEDataXMLAsAttachment(String aeReportWithCaaersId) throws Exception {
		 
		//log.info(" REPORT ID " + aeReportWithCaaersId);
		String aeReport = detach(aeReportWithCaaersId);	
		//System.out.println("EXTERNAL EPRS " + this.externalEPRs);
		String adeersEPR = externalEPRs.split(",")[0];
		
		String url=adeersEPR.split("::")[0];
		String uid=adeersEPR.split("::")[1];
		String pwd=adeersEPR.split("::")[2];
		
		String clientTrustStore = "caAERs-AdEERS";
		String userDir = System.getProperty("user.home");
		String fileSeparator = System.getProperty("file.separator");
		//String clientAbsoluteTrustStore = System.getenv("SERVICEMIX_HOME") + fileSeparator + clientTrustStore;
		String clientAbsoluteTrustStore = System.getProperty("user.home") + fileSeparator + clientTrustStore;

		log.info("PATH " + clientAbsoluteTrustStore);
		
    	//System.setProperty("javax.net.ssl.trustStore", "/home/caaers_dev/apache-servicemix-3.1.2/conf/caAERs-AdEERS");
		//System.setProperty("javax.net.ssl.trustStore", "/Users/sakkala/temp/certs/caAERs-AdEERS");
		System.setProperty("javax.net.ssl.trustStore", clientAbsoluteTrustStore);
		
		AEReportXMLServiceSoapBindingStub binding;
        try {
            binding = (AEReportXMLServiceSoapBindingStub)   new AEReportXMLService_ServiceLocator(url).getAEReportXMLService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        // Time out after a minute
        binding.setTimeout(60000);
        binding.setUsername(uid);
        binding.setPassword(pwd);
  
        	
        StringReader reader = new StringReader(aeReport);
        
        System.out.println("SUBMITTING TO WEB SERVICE ..." + clientAbsoluteTrustStore);
        Source attachment = new StreamSource(reader,"");
        //call the web service                
        binding.submitAEDataXMLAsAttachment(ReportingMode.SYNCHRONOUS, attachment);
        String s = binding._getCall().getMessageContext().getResponseMessage().getSOAPPartAsString();

        
        //attach the id to the returned message
        s=s.replaceAll("</ns1:AEReportJobInfo>","<CAEERS_AEREPORT_ID>"+caaersAeReportId+"</CAEERS_AEREPORT_ID><REPORT_ID>"+reportId+"</REPORT_ID><SUBMITTER_EMAIL>"+submitterEmail+"</SUBMITTER_EMAIL></ns1:AEReportJobInfo>");
        
        //System.out.println(s);
        
        return s;
		
	}

	private String detach(String aeReportWithCaaersId) throws Exception {
		//detach the id and store it to attach later
		
		int si = aeReportWithCaaersId.indexOf("<CAEERS_AEREPORT_ID>");
		int ei = aeReportWithCaaersId.indexOf("</CAEERS_AEREPORT_ID>");
		caaersAeReportId = aeReportWithCaaersId.substring(si+20, ei);

		si = aeReportWithCaaersId.indexOf("<EXTERNAL_SYSTEMS>");
		ei = aeReportWithCaaersId.indexOf("</EXTERNAL_SYSTEMS>");
		externalEPRs = aeReportWithCaaersId.substring(si+18, ei);
		
		si = aeReportWithCaaersId.indexOf("<REPORT_ID>");
		ei = aeReportWithCaaersId.indexOf("</REPORT_ID>");
		reportId = aeReportWithCaaersId.substring(si+11, ei);
		
		si = aeReportWithCaaersId.indexOf("<SUBMITTER_EMAIL>");
		ei = aeReportWithCaaersId.indexOf("</SUBMITTER_EMAIL>");
		submitterEmail = aeReportWithCaaersId.substring(si+17, ei);
		


		String aeReport = aeReportWithCaaersId.replaceAll("<CAEERS_AEREPORT_ID>"+caaersAeReportId+"</CAEERS_AEREPORT_ID>", "");
		aeReport = aeReport.replaceAll("<EXTERNAL_SYSTEMS>"+externalEPRs+"</EXTERNAL_SYSTEMS>", "");
		aeReport = aeReport.replaceAll("<REPORT_ID>"+reportId+"</REPORT_ID>", "");
		aeReport = aeReport.replaceAll("<SUBMITTER_EMAIL>"+submitterEmail+"</SUBMITTER_EMAIL>", "");
		aeReport = aeReport.replaceAll("<ADDITIONAL_INFORMATION/>", "");
		return aeReport;
	}
	
	public static void main (String[] args){
		AdeersWebServiceImpl a = new AdeersWebServiceImpl();
		String str1 = "";
		try {
			//FileInputStream fs = new FileInputStream("/Users/sakkala/tech/adeers/instance0.xml");
			
			
			//fs.close();
			
			FileReader input = new FileReader("/Users/sakkala/tech/adeers/instance2.xml");
			BufferedReader bufRead = new BufferedReader(input);
			String line = bufRead.readLine();
			
			while (line != null) {
				str1 = str1 + line; 
				line = bufRead.readLine();
			}
			System.out.println(str1);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			a.submitAEDataXMLAsAttachment(str1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}


/*
System.out.print(aeReportJobInfo.getJobID());
String reportStatus = aeReportJobInfo.getReportStatus().getValue();
if (reportStatus.equals(ReportStatus._SUCCESS)) {
	System.out.println("Response is: SUCCESS");
}
if (reportStatus.equals(ReportStatus._REJECTED)) {
	System.out.println("Response is: REJECTED");
}       
if (reportStatus.equals(ReportStatus._ERROR)) {
	System.out.println("Response is: ERROR");
}        
*/
/*
StringWriter         objWriter  = new StringWriter();
org.apache.axis.encoding.Serializer ser = aeReportJobInfo.getSerializer("", AEReportJobInfo.class, AEReportJobInfo.getTypeDesc().getXmlType());
SerializationContext context    = new SerializationContext(objWriter, binding._getCall().getMessageContext());
ser.serialize(AEReportJobInfo.getTypeDesc().getXmlType(), null, aeReportJobInfo, context);
objWriter.flush();
objWriter.close();
System.out.println(objWriter.toString());
return objWriter.toString();
*/