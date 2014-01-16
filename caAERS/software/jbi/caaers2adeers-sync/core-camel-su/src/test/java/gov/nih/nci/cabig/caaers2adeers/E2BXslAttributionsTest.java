/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers;

public class E2BXslAttributionsTest extends SafetyReportE2BXslTest {
	
	public void testFullDeviceAttributionTransformation() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>BrandName</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>CommonName</ae:commonName>" ));
		assertTrue(result.contains("<ae:type>Type</ae:type>" ));
	}
	
	public void testFullDeviceAttributionTransformation2() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>BrandName</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>CommonName</ae:commonName>" ));
		assertTrue(result.contains("<ae:type>Type</ae:type>" ));
	}

	public void testFullSurgeryAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>3</ae:attribution>" ));
		assertTrue(result.contains("<ae:interventionDate>2014-01-03</ae:interventionDate>" ));
		assertTrue(result.contains("<ae:name>Pelvis NOS</ae:name>" ));
	}
	
	public void testFullRadiationAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>5</ae:attribution>" ));
		assertTrue(result.contains("<ae:lastTreatmentDate>2014-01-03</ae:lastTreatmentDate>" ));
		assertTrue(result.contains("<ae:administration>Brachytherapy HDR</ae:administration>" ));
		assertTrue(result.contains("<ae:type>Type</ae:type>" ));
	}
	
	public void testPartialDeviceAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample2.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>BrandName</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>CommonName</ae:commonName>" ));
		//assertTrue(result.contains("<ae:type></ae:type>" ));
	}
	
	public void testPartialDeviceAttributionTransformation2() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample2.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>BrandName</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>CommonName</ae:commonName>" ));
		//assertTrue(result.contains("<ae:type>Type</ae:type>" ));
	}

	public void testPartialSurgeryAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample2.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>3</ae:attribution>" ));
		assertTrue(result.contains("<ae:interventionDate>2014-01-03</ae:interventionDate>" ));
		assertTrue(result.contains("<ae:name/>" ));
	}
	
	public void testPartialRadiationAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample2.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>5</ae:attribution>" ));
		assertTrue(result.contains("<ae:lastTreatmentDate/>" ));
		assertTrue(result.contains("<ae:administration>Brachytherapy HDR</ae:administration>" ));
	}
	
	public void testPartialRadiationAttributionTransformation2() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/attributions_sample3.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>5</ae:attribution>" ));
		assertTrue(result.contains("<ae:lastTreatmentDate/>" ));
		assertTrue(result.contains("<ae:administration/>" ));
	}
	
	public void testFullDeviceAttributionTransformation1() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/device_attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>Cryocare Surgical System</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>cryoprobe</ae:commonName>" ));
		assertTrue(result.contains("<ae:type>Image Guided Intervention (IGI) Device</ae:type>" ));
	}
	
	public void testFullDeviceAttributionTransformationWithNonAlphaNumericCharacters() throws Exception{
		String result = null;
		result = transform("xslt/e2b/request/testXMLs/device_attributions_sample1.xml", "xslt/e2b/request/safetyreport_e2b_sync.xsl");
		assertNotNull(result);
		assertNotSame("", result);
		assertTrue(result.contains("<ae:attribution>1</ae:attribution>" ));
		assertTrue(result.contains("<ae:brandName>N/A</ae:brandName>" ));
		assertTrue(result.contains("<ae:commonName>cryop..</ae:commonName>" ));
		assertTrue(result.contains("<ae:type>;</ae:type>" ));
	}

}
