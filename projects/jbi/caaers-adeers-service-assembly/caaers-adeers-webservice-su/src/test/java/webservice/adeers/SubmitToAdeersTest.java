package webservice.adeers;

import caaers.client.AEReportJobInfo;

public class SubmitToAdeersTest extends BaseCaller {
	
	public void test5876AgentOnly() throws Exception {
		String caaersXMLFile = "Study-5876-agent-only.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	public void test5876AgentAdjust() throws Exception {
		String caaersXMLFile = "Study-5876-agent-adjust.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	public void testN027DRadiation() throws Exception {
		String caaersXMLFile = "Study-N027D-radiation.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	
	private void transformAndSubmitSuccessPath(String caaersXMLFile) throws Exception {
    	String detachedXML = "";
    	String tempPath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/";
		try {
			detachedXML = transform(tempPath+caaersXMLFile);
    	} catch (Exception e) {
    		throw new Exception ("caAERS to AdEERS transformation Failed ", e);
    	}
    	try {
    		AEReportJobInfo aeReportJobInfo = submit(detachedXML);
    		displayErrors(aeReportJobInfo.getJobExceptions()); 
            assertEquals("SUCCESS", aeReportJobInfo.getReportStatus().toString());
            displayResult(aeReportJobInfo);
            
    	} catch (Exception e) {
    		throw new Exception ("caAERS to AdEERS submission Failed ", e);
    	}		
	}
}
