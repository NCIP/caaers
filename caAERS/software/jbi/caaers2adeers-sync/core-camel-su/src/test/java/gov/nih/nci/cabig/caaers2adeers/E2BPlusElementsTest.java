/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

public class E2BPlusElementsTest extends SafetyReportE2BXslTest {

	public void testNameOfReport() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/e2b_plus_Rave_changes.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:name>CTEP 24 Hour SAE Notification</ae:name>"));

	}
	
	public void testSenderDetails() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/e2b_plus_Rave_changes.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:firstName>Alfred-sender</ae:firstName>"));
		assertTrue(result.contains("<ae:lastName>Nobel-sender</ae:lastName>"));
		assertTrue(result.contains("<ae:middleName>C-sender</ae:middleName>"));
		assertTrue(result.contains("<ae:value>703-787-9656-sender-ph</ae:value>"));
		assertTrue(result.contains("<ae:value>703-787-9656-sender-fax</ae:value>"));
		assertTrue(result.contains("<ae:value>alfred.nobel.sender@dynamite.com</ae:value>"));

	}
	
	public void testParticipantHeightAndWeight() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/e2b_plus_Rave_changes.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:quantity>183</ae:quantity>"));
		assertTrue(result.contains("<ae:quantity>74</ae:quantity>"));
	}
	
	public void testDrugCumulativeDosage() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/e2b_plus_Rave_changes.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:units>million iu</ae:units>"));
		assertTrue(result.contains("<ae:units>Gtt</ae:units>"));
	}
	
	public void testNarrativeIncludeClinicalExtended() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/e2b_plus_Rave_changes.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:eventDescription>the description or narrative of the event extended</ae:eventDescription>"));
	}
	

}
