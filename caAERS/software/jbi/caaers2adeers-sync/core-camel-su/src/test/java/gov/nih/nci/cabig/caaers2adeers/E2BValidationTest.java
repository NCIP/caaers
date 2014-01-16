/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

import org.apache.cxf.helpers.IOUtils;


public class E2BValidationTest extends SafetyReportE2BXslTest {
	
	private static String SCH_XSL="xslt/e2b/request/safetyreport_e2b_schematron.xsl";		
	
	public void testSchematronValidationForLookups() throws Exception {
		String expected = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("xslt/e2b/request/testout/e2b_lookups_sch_validation_output.xml"));
		String results= transform("xslt/e2b/request/testXMLs/e2b_lookups_sch_validation_input.xml", SCH_XSL);
		System.out.println(">>>>" + results + "<<<");
		assertEquals(expected, results);
	}
	

}
