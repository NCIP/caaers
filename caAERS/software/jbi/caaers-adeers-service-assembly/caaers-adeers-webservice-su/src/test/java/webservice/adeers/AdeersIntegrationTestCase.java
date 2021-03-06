/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package webservice.adeers;

import gov.nih.nci.ctep.adeers.client.*;
import gov.nih.nci.ctep.adeers.client.Error;
import junit.framework.TestCase;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Properties;

public abstract class AdeersIntegrationTestCase extends TestCase{
	private String adeersWebServiceAddress = "";
   	private String adeersUserName = "";
   	private String adeersPassword = "";
	private String caaersAdeersXslt = "caaers-adeers-xslt-su/src/main/resources/adeers-transformer.xsl";
//	private String trustStore = "caaers-adeers-webservice-su/src/main/resources/caAERs-AdEERS";

	@Override
	public void setUp() throws Exception{
		super.setUp();
		PropertiesConfiguration testprops = new PropertiesConfiguration("test-caaers-adeers-ws-su.properties");
		adeersWebServiceAddress = (String) testprops.getProperty("adeers.report.service.url");
		adeersUserName= (String) testprops.getProperty("adeers.username");
		adeersPassword= (String) testprops.getProperty("adeers.password");
	}
	protected void displayErrors(Error[] errors) {
       if (errors != null ) {
           System.out.println("---- Exceptions -----:");
	        for (int i=0;i<errors.length;i++) {
	        	System.out.println(errors[i].getCode() + " : " + errors[i].getDescription());
	        }
           System.out.println("---- -------- -----");
        }
	}

	protected void displayResult(AEReportJobInfo aeReportJobInfo) {
	       if (aeReportJobInfo != null ) {
		        System.out.println("Report Status     : " + aeReportJobInfo.getReportStatus().toString()); 
		        System.out.println("AdEERS Report URL : " + aeReportJobInfo.getReportURL().toString()); 
		        System.out.println("AdEERS Ticket # : " + aeReportJobInfo.getTicketNumber());
	        }
    }


    protected void displayResult(AEReportCancelInfo aeReportJobInfo) {
        if (aeReportJobInfo != null ) {
            System.out.println("Report Status     : " + aeReportJobInfo.getReportStatus().toString());
            System.out.println("AdEERS Ticket # : " + aeReportJobInfo.getTicketNumber());
        }
    }



    public String loadFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
        }

        return stringBuilder.toString();
    }
    
    protected String transformContent(String xml) throws Exception {

        Source xmlSource = new StreamSource(new StringReader(xml));

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
        aeReport = aeReport.replaceAll("<ADDITIONAL_INFORMATION/>", "");
        aeReport = aeReport.replaceAll("<MESSAGE_COMBO_ID/>", "");
        aeReport = aeReport.replaceAll("<WITHDRAW>true</WITHDRAW>", "");
		return aeReport;
	}
    private AEReportXMLServiceSoapBindingStub bind() {
//    	System.setProperty("javax.net.ssl.trustStore", trustStore);
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
        
        System.out.println("----XML to AdEERS --------------------------------------------------------------------------------\r\n");
        System.out.println(xmlToSubmit);
        System.out.println("\n-----------------------------------------------------------------------------------------");

    	AEReportXMLServiceSoapBindingStub binding = this.bind();
    	StringReader reader = new StringReader(xmlToSubmit);
        Source attachment = new StreamSource(reader,"");
        AEReportJobInfo aeReportJobInfo = binding.submitAEDataXMLAsAttachment(ReportingMode.SYNCHRONOUS, attachment);
        return aeReportJobInfo;
    }

    protected AEReportCancelInfo withdraw(String xmlToSubmit) throws Exception{

        System.out.println("----XML to AdEERS --------------------------------------------------------------------------------\r\n");
        System.out.println(xmlToSubmit);
        System.out.println("\n-----------------------------------------------------------------------------------------");

        AEReportXMLServiceSoapBindingStub binding = this.bind();
        StringReader reader = new StringReader(xmlToSubmit);
        Source attachment = new StreamSource(reader,"");
        AEReportCancelInfo aeReportJobInfo = binding.withdrawAEReport(attachment);
        return aeReportJobInfo;
    }

    protected void assertSuccess(AEReportJobInfo aeReportJobInfo){
        displayErrors(aeReportJobInfo.getJobExceptions());
        assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
        displayResult(aeReportJobInfo);
    }


    protected void assertSuccess(AEReportCancelInfo aeReportJobInfo){
        displayErrors(aeReportJobInfo.getJobExceptions());
        assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
        displayResult(aeReportJobInfo);
    }
}
