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
        medicalDevice = new MedicalDevice();

        brandName = "brand name";
        medicalDevice.setBrandName(brandName);
        catalogNumber = "catalong number";
        medicalDevice.setCatalogNumber(catalogNumber);
        commonName = "common name";
        medicalDevice.setCommonName(commonName);
        other = DeviceOperator.OTHER;
        medicalDevice.setDeviceOperator(other);
        returned = Availability.RETURNED;
        medicalDevice.setDeviceReprocessed(returned);
        deviceType = "device type";
        medicalDevice.setDeviceType(deviceType);
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
        medicalDevice.setManufacturerCity(manufacturerCity);
        manufacturerName = "test";
        medicalDevice.setManufacturerName(manufacturerName);
        manufacturerState = "VA";
        medicalDevice.setManufacturerState(manufacturerState);
        modelNumber = "mode number";
        medicalDevice.setModelNumber(modelNumber);
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

    public void testCopy() {

        MedicalDevice device = medicalDevice.copy();

        assertNull("must not coy id", device.getId());

        assertNull("must not coy grid id", device.getGridId());
        assertNull("must not coy version number", device.getVersion());
        assertNull("must not coy report", device.getReport());
        assertEquals(brandName, device.getBrandName());
        assertEquals(catalogNumber, device.getCatalogNumber());
        assertEquals(commonName, device.getCommonName());
        assertEquals(other, device.getDeviceOperator());
        assertEquals(returned, device.getDeviceReprocessed());
        assertEquals(deviceType, device.getDeviceType());
        assertEquals(evaluationAvailability, device.getEvaluationAvailability());
        assertEquals(expirationDate, device.getExpirationDate());
        assertEquals(explantedDate, device.getExplantedDate());
        assertEquals(implantedDate, device.getImplantedDate());
        assertEquals(lotNumber, device.getLotNumber());
        assertEquals(manufacturerCity, device.getManufacturerCity());
        assertEquals(manufacturerName, device.getManufacturerName());
        assertEquals(manufacturerState, device.getManufacturerState());
        assertEquals(modelNumber, device.getModelNumber());
        assertEquals(otherDeviceOperator, device.getOtherDeviceOperator());
        assertEquals(otherNumber, device.getOtherNumber());
        assertEquals(reprocessorAddress, device.getReprocessorAddress());
        assertEquals(reprocessorName, device.getReprocessorName());
        assertEquals(returnedDate, device.getReturnedDate());
        assertEquals(serialNumber, device.getSerialNumber());
    }


}
