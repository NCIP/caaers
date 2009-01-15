package webservice.adeers;

import caaers.client.AEReportJobInfo;

public class SubmitToAdeersTest extends BaseCaller {
	private String tempPath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/";
	public void test5876AgentOnly() throws Exception {
		String caaersXMLFile = tempPath+"Study-5876-agent-only.xml"; 		
    	String detachedXML = "";
		try {
			detachedXML = transform(caaersXMLFile);
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
