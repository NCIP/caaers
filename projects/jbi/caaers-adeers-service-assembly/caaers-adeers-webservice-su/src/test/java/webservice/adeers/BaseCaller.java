package webservice.adeers;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import junit.framework.TestCase;
import caaers.client.AEReportJobInfo;
import caaers.client.AEReportXMLServiceSoapBindingStub;
import caaers.client.AEReportXMLService_ServiceLocator;
import caaers.client.Error;
import caaers.client.ReportingMode;

public abstract class BaseCaller extends TestCase{
	private String adeersWebServiceAddress = "https://capps-ctep.nci.nih.gov/adeersws10gbeta/services/AEReportXMLService";
   	private String adeersUserName = "ADEERSBETA";
   	private String adeersPassword = "testadeers1#";
	private String caaersAdeersXslt = "caaers-adeers-xslt-su/src/main/resources/adeers-transformer.xsl";
	private String trustStore = "caaers-adeers-webservice-su/src/main/resources/caAERs-AdEERS";
	
	public BaseCaller(){
		
	}
	@Override
	public void setUp() throws Exception{
		super.setUp();
	}
	protected void displayErrors(Error[] errors) {
       if (errors != null ) {
	        for (int i=0;i<errors.length;i++) {
	        	System.out.println(errors[i].getCode() + " : " + errors[i].getDescription());
	        }      
        }
	}
	protected void displayResult(AEReportJobInfo aeReportJobInfo) {
	       if (aeReportJobInfo != null ) {
		        System.out.println("Report Status     : " + aeReportJobInfo.getReportStatus().toString()); 
		        System.out.println("AdEERS Report URL : " + aeReportJobInfo.getReportURL().toString()); 
	        }
		}	
    protected String transform(String caaersXMLFile) throws Exception {
    	//File caaersXmlFileF = new ClassPathResource("webservice/adeers/" + caaersXMLFile).getFile();
 
    	 Source xmlSource = new StreamSource(new FileInputStream(caaersXMLFile));

        Source xsltSource = new StreamSource(new FileInputStream(caaersAdeersXslt));
        
        StringWriter outStr = new StringWriter();        
        StreamResult result = new StreamResult(outStr);
                // the factory pattern supports different XSLT processors
        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, result);// transform(xmlSource, new StreamResult(System.out));
        
        //detach XML
        String detachedXML = detach(outStr.toString());  
        return detachedXML;
        //FileWriter fw = new FileWriter(transformedXMLFile);
        //fw.write(s);
        //fw.close();
    }
    
    protected String detach(String aeReportWithCaaersId) throws Exception {
		//detach the id and store it to attach later
		
		int si = aeReportWithCaaersId.indexOf("<CAEERS_AEREPORT_ID>");
		int ei = aeReportWithCaaersId.indexOf("</CAEERS_AEREPORT_ID>");
		String caaersAeReportId = aeReportWithCaaersId.substring(si+20, ei);

		String aeReport = aeReportWithCaaersId.replaceAll("<CAEERS_AEREPORT_ID>"+caaersAeReportId+"</CAEERS_AEREPORT_ID>", "");
		//System.out.print(aeReport);
		aeReport = aeReport.replaceAll("<EXTERNAL_SYSTEMS/>", "");
		aeReport = aeReport.replaceAll("<REPORT_ID/>", "");
		aeReport = aeReport.replaceAll("<SUBMITTER_EMAIL/>", "");
		return aeReport;
	}
    private AEReportXMLServiceSoapBindingStub bind() {
    	System.setProperty("javax.net.ssl.trustStore", trustStore);
    	AEReportXMLServiceSoapBindingStub binding;
    	//String AEReportXMLService_address = "https://eapps.ctisinc.com/adeersws10gtest/services/AEReportXMLService";
         try {
            binding = (AEReportXMLServiceSoapBindingStub)
                          new AEReportXMLService_ServiceLocator(adeersWebServiceAddress).getAEReportXMLService();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);
        binding.setUsername(adeersUserName);
        binding.setPassword(adeersPassword);
        return binding;
    }
    
    protected AEReportJobInfo submit(String xmlToSubmit) throws Exception{
    	AEReportXMLServiceSoapBindingStub binding = this.bind();
    	StringReader reader = new StringReader(xmlToSubmit);
        Source attachment = new StreamSource(reader,"");
        AEReportJobInfo aeReportJobInfo = binding.submitAEDataXMLAsAttachment(ReportingMode.SYNCHRONOUS, attachment);
        return aeReportJobInfo;
    }

}
