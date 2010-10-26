package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.AbstractTestCase;

import java.util.Date;

/**
 * @author Biju Joseph
 */
public class MedicalDeviceTest extends AbstractTestCase {

    private MedicalDevice medicalDevice;
    private String brandName;
    private String catalogNumber;
    private String commonName;
    private DeviceOperator other;
    private Availability returned;
    private String deviceType;
    private Availability evaluationAvailability;
    private Date expirationDate;
    private Date explantedDate;
    private Date implantedDate;
    private String lotNumber;
    private String manufacturerCity;
    private String manufacturerName;
    private String manufacturerState;
    private String modelNumber;
    private String serialNumber;
    private String otherNumber;
    private String otherDeviceOperator;
    private String reprocessorAddress;
    private Date returnedDate;
    private String reprocessorName;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        medicalDevice = new MedicalDevice(Fixtures.createStudyDevice());

        brandName = "brand name";
        medicalDevice.getStudyDevice().getDevice().setBrandName(brandName);
        catalogNumber = "catalong number";
        medicalDevice.getStudyDevice().setCatalogNumber(catalogNumber);
        commonName = "common name";
        medicalDevice.getStudyDevice().getDevice().setCommonName(commonName);
        other = DeviceOperator.OTHER;
        medicalDevice.setDeviceOperator(other);
        returned = Availability.RETURNED;

        medicalDevice.setDeviceReprocessed(ReprocessedDevice.YES);
        deviceType = "device type";
        medicalDevice.getStudyDevice().getDevice().setType(deviceType);
        evaluationAvailability = Availability.YES;
        medicalDevice.setEvaluationAvailability(evaluationAvailability);
        expirationDate = new Date();
        medicalDevice.setExpirationDate(expirationDate);
        explantedDate = new Date();
        medicalDevice.setExplantedDate(explantedDate);
        medicalDevice.setGridId("grid id");
        medicalDevice.setId(1);
        implantedDate = new Date();
        medicalDevice.setImplantedDate(implantedDate);
        medicalDevice.setVersion(2);
        lotNumber = "lot number";
        medicalDevice.setLotNumber(lotNumber);
        manufacturerCity = "Herndon";
        medicalDevice.getStudyDevice().setManufacturerCity(manufacturerCity);
        manufacturerName = "test";
        medicalDevice.getStudyDevice().setManufacturerName(manufacturerName);
        manufacturerState = "VA";
        medicalDevice.getStudyDevice().setManufacturerState(manufacturerState);
        modelNumber = "mode number";
        medicalDevice.getStudyDevice().setModelNumber(modelNumber);
        serialNumber = "serial number";
        medicalDevice.setSerialNumber(serialNumber);
        otherNumber = "other number";
        medicalDevice.setOtherNumber(otherNumber);
        otherDeviceOperator = "other device op";
        medicalDevice.setOtherDeviceOperator(otherDeviceOperator);
        reprocessorAddress = "reprocess address";
        medicalDevice.setReprocessorAddress(reprocessorAddress);
        reprocessorName = "rep name";
        medicalDevice.setReprocessorName(reprocessorName);
        returnedDate = new Date();
        medicalDevice.setReturnedDate(returnedDate);
    }

    public void testSetDeviceOperator(){
        DeviceOperator deviceOperator = DeviceOperator.HEALTH_PROFESSIONAL;
        medicalDevice.setDeviceOperator(deviceOperator );
        assertSame(deviceOperator, medicalDevice.getDeviceOperator()); 
    }
}
