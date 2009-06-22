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
	public void test5876CtepAndNonCtepAgent() throws Exception {
		String caaersXMLFile = "Study-5876-ctep-and-nonCtep-agent.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	public void test5876DuplicatePriorTherapies() throws Exception {
		String caaersXMLFile = "Study-5876-duplicate-prior-therapies.xml"; 		
		AEReportJobInfo aeReportJobInfo = transformAndSubmit(caaersXMLFile);
		displayErrors(aeReportJobInfo.getJobExceptions()); 
		assertEquals("PRIOR_THERAPY must be unique.", aeReportJobInfo.getJobExceptions()[0].getDescription().toString());
	}
	public void test5876MultipleLabs() throws Exception {
		String caaersXMLFile = "Study-5876-labs.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	public void testN027DRadiation() throws Exception {
		String caaersXMLFile = "Study-N027D-radiation.xml"; 		
		transformAndSubmitSuccessPath(caaersXMLFile);
	}
	public void testVerbatimOtherAE() throws Exception {
		String caaersXMLFile = "verbatim-other-ae.xml"; 		
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
	private AEReportJobInfo transformAndSubmit(String caaersXMLFile) throws Exception {
    	String detachedXML = "";
    	String tempPath = "caaers-adeers-webservice-su/src/test/resources/webservice/adeers/";
		try {
			detachedXML = transform(tempPath+caaersXMLFile);
    	} catch (Exception e) {
    		throw new Exception ("caAERS to AdEERS transformation Failed ", e);
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
