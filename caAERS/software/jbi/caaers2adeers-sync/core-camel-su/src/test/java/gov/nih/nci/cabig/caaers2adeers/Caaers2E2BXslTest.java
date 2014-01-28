/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

public class Caaers2E2BXslTest extends SafetyReportE2BXslTest {

	public void testDeviceAttributionTransformation() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_device_radiation_attributions1.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>CN=PET-CT^^BN=^^TYPE=Imaging Device^^</factor>"));
		assertTrue(result.contains("<attribution>3</attribution>"));
		assertTrue(result.contains("<factor>TYPE=BT_LDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	public void testDeviceAttributionTransformationBrandNameAndTypeNull() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_device_radiation_attributions1.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>CN=PET-CT-NT^^BN=^^TYPE=^^</factor>"));
		assertTrue(result.contains("<attribution>3</attribution>"));
		assertTrue(result.contains("<factor>TYPE=BT_LDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	public void testDeviceAttributionTransformationNoType() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_device_radiation_attributions1.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>CN=PET-CT-NT^^BN=BN-NT^^TYPE=^^</factor>"));
		assertTrue(result.contains("<attribution>3</attribution>"));
		assertTrue(result.contains("<factor>TYPE=BT_LDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	public void testSurgeryAttributionTransformation() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_surgery_radiation_attributions.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>SITE=Pelvis NOS^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>2</attribution>"));
		assertTrue(result.contains("<factor>TYPE=BT_HDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	public void testSurgeryAttributionTransformationNoSurgeryDate() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_surgery_radiation_attributions.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>SITE=Stomach^^DATE=^^</factor>"));
		assertTrue(result.contains("<attribution>2</attribution>"));
		assertTrue(result.contains("<factor>TYPE=BT_HDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	
	public void testRadiationAttributionTransformationNoTreatmentDate() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_surgery_radiation_attributions.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>TYPE=BT_HDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<factor>TYPE=BT_HDR1^^DATE=^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}
	
	
	public void testRadiationAttributionTransformationNoTreatmentDateBlank() throws Exception{

		String result = null;
		result = transform("xslt/e2b/request/testXMLs/ae_report_surgery_radiation_attributions.xml", "xslt/e2b/request/caaersToE2b.xslt");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<factor>TYPE=BT_HDR^^DATE=01/03/2014^^</factor>"));
		assertTrue(result.contains("<factor>TYPE=BT_HDR2^^DATE=^^</factor>"));
		assertTrue(result.contains("<attribution>4</attribution>"));

	}

}
