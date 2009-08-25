package gov.nih.nci.cabig.caaers.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class FreeMarkerServiceTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testApplyRuntimeReplacementsForReport() {
		Map<Object, Object> varMap = new HashMap<Object, Object>();
		varMap.put("REP", "World");
		String text = "Hello ${REP}!!";
		FreeMarkerService service = new FreeMarkerService();
		String replacedText = service.applyRuntimeReplacementsForReport(text, varMap);
		assertEquals("Hello World!!", replacedText);
		
		varMap.put("patientId", "111");
		varMap.put("reportId", "222");
		varMap.put("reportURL", "/pages/ae/edit?aeReport=33&report=44");
		text = "Hello ${patientId}, you got a report ${reportId}, to access go to url ${reportURL}";
		replacedText = service.applyRuntimeReplacementsForReport(text, varMap);
		assertEquals(replacedText, "Hello 111, you got a report 222, to access go to url /pages/ae/edit?aeReport=33&report=44");
		
	}
	public void testApplyRuntimeReplacementsForReport_ThrowException() {
		try {
			Map<Object, Object> varMap = new HashMap<Object, Object>();
			varMap.put("ABCD", "World");
			String text = "Hello ${REP}!!";
			FreeMarkerService service = new FreeMarkerService();
			String replacedText = service.applyRuntimeReplacementsForReport(text, varMap);
			fail("Must throw exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
	}
	
	public void testApplyRuntimeReplacementsForReport_NullRawText() {
		try {
			Map<Object, Object> varMap = new HashMap<Object, Object>();
			varMap.put("ABCD", "World");
			String text = "Hello ${REP}!!";
			FreeMarkerService service = new FreeMarkerService();
			String replacedText = service.applyRuntimeReplacementsForReport(null, varMap);
			assertTrue(replacedText.length() == 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
	}
}
