package webservice.adeers;

import gov.nih.nci.ctep.adeers.client.AEReportJobInfo;

public class SubmitToAdeersTest extends AdeersIntegrationTestCase {
	
	public void testAgentAndRadiationSubmission() throws Exception {
		String caaersXMLFile = "RTOG-0524_NCI_IND_Agent_And_Radiation.xml";
        transformAndSubmitSuccessPath(caaersXMLFile);
	}

	public void testSurgerySubmission() throws Exception {
		String caaersXMLFile = "T92-0215_NCI_Surgery24Hr.xml";
        transformAndSubmitSuccessPath(caaersXMLFile);
	}

	public void testAgentReportWithLabs() throws Exception {
		String caaersXMLFile = "CALGB-50303_CommercialAgent10DayReport.xml";
        transformAndSubmitSuccessPath(caaersXMLFile);
	}

	public void testAgentReportWithOtherCauses() throws Exception {
		String caaersXMLFile = "N0071_NonNCI_Agent10Day.xml";
        transformAndSubmitSuccessPath(caaersXMLFile);
	}

	private void transformAndSubmitSuccessPath(String caaersXMLFile) throws Exception {

        AEReportJobInfo aeReportJobInfo = transformAndSubmit(caaersXMLFile);
        displayErrors(aeReportJobInfo.getJobExceptions());
        assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
        displayResult(aeReportJobInfo);
	}
	private AEReportJobInfo transformAndSubmit(String caaersXMLFile) throws Exception {
    	String detachedXML = "";
    	String tempPath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/submission/";
		try {
			detachedXML = transform(tempPath+caaersXMLFile);
            System.out.println("=================== XML After transformation ===========");
            System.out.println(detachedXML);
            System.out.println("========================================================");
    	} catch (Exception e) {
    		throw new Exception ("caAERS XML to AdEERS XML transformation failed ", e);
    	}
    	AEReportJobInfo aeReportJobInfo = null;
    	try {
    		aeReportJobInfo = submit(detachedXML);
           
    	} catch (Exception e) {
    		throw new Exception ("caAERS to AdEERS submission Failed ", e);
    	}	
    	return aeReportJobInfo;
	}
}
