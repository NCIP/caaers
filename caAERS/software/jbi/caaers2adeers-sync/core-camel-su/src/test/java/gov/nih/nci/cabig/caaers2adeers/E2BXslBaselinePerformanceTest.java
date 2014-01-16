/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

public class E2BXslBaselinePerformanceTest extends SafetyReportE2BXslTest {

	public void testBaselinePerformanceTransformation() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/baseline_performance_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:baselinePerformanceStatus>0 = Normal Activity, asymptomatic</ae:baselinePerformanceStatus>"));

	}

}
