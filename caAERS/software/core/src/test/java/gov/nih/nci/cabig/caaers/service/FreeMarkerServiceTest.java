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
