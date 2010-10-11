package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;

/**
 * StudyDevice Tester.
 *
 * @author Biju Joseph
 * @since <pre>10/11/2010</pre>
 * 
 */
public class StudyDeviceTest extends AbstractTestCase {

    StudyDevice studyDevice;

    public void setUp() throws Exception {
        super.setUp();
        studyDevice = Fixtures.createStudyDevice();
        
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSetGetBrandName() throws Exception {
        assertEquals("Brand", studyDevice.getBrandName());
    }

    public void testSetGetCatalogNumber() throws Exception {
        assertEquals("c1", studyDevice.getCatalogNumber());
    }

    public void testSetGetCommonName() throws Exception {
        assertEquals("CName", studyDevice.getCommonName());
    }

    public void testSetGetDeviceType() throws Exception {
        assertEquals("type", studyDevice.getDeviceType());
    }

    public void testSetGetManufacturerCity() throws Exception {
        assertEquals("mc", studyDevice.getManufacturerCity());
    }

    public void testSetGetManufacturerName() throws Exception {
        assertEquals("ma", studyDevice.getManufacturerName());
    }

    public void testSetGetManufacturerState() throws Exception {
        assertEquals("va", studyDevice.getManufacturerState());
    }

    public void testSetGetModelNumber() throws Exception {
        assertEquals("999", studyDevice.getModelNumber());
    }

    public void testSetGetOtherBrandName() throws Exception {

       studyDevice.setDevice(null);
       studyDevice.setOtherBrandName("x");
       assertEquals("x", studyDevice.getBrandName());
       assertEquals("x", studyDevice.getOtherBrandName());

    }

    public void testSetGetOtherCommonName() throws Exception {
       studyDevice.setDevice(null);
       studyDevice.setOtherCommonName("x");
       assertEquals("x", studyDevice.getCommonName());
       assertEquals("x", studyDevice.getOtherCommonName());
    }

    public void testSetGetOtherDeviceType() throws Exception {
         studyDevice.setDevice(null);
         studyDevice.setOtherDeviceType("x");
         assertEquals("x", studyDevice.getOtherDeviceType());
         assertEquals("x", studyDevice.getOtherDeviceType());
    }


}
