package gov.nih.nci.cabig.caaers.rules.deploy;

import gov.nih.nci.cabig.caaers.domain.DeviceOperator;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

public class MedicalDeviceBusinessRulesTest extends AbstractBusinessRulesExecutionTestCase {

    @Override
    public String getBindUri() {
        return "gov.nih.nci.cabig.caaers.rules.reporting_medical_device_section";
    }

    @Override
    public String getRuleFile() {
        return "rules_reporting_medical_device.xml";
    }

    /**
     * RuleName : SME_BR1_CHK Logic : Either 'Brand name' or 'Common name' must be provided Error
     * Code : SME_BR1_ERR Error Message : Either BRAND_NAME or COMMON_NAME must be provided
     */
    public void testBrandName_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setCommonName(null);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is BrandName");
    }

    /**
     * RuleName : SME_BR1_CHK Logic : Either 'Brand name' or 'Common name' must be provided Error
     * Code : SME_BR1_ERR Error Message : Either BRAND_NAME or COMMON_NAME must be provided
     */
    public void testCommonName_Alone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setBrandName(null);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is CommonName");
    }

    /**
     * RuleName : SME_BR1_CHK Logic : Either 'Brand name' or 'Common name' must be provided Error
     * Code : SME_BR1_ERR Error Message : Either BRAND_NAME or COMMON_NAME must be provided
     */
    public void testNoBrandName_NoCommonName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setCommonName(null);
        aeReport.getMedicalDevices().get(0).setBrandName(null);

        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR1_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : SME_BR1_CHK Logic : Either 'Brand name' or 'Common name' must be provided Error
     * Code : SME_BR1_ERR Error Message : Either BRAND_NAME or COMMON_NAME must be provided
     */
    public void testBoth_BrandName_CommonName() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When there is CommonName and Brand Name");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testNo_Devices() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().clear();
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "When no devices present");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testNo_Model_Serial_Catalog_Lot_Number() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber(null);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "SME_BR2_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModelNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "ModelNumber is present");

    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testSerialNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setSerialNumber("33");
        aeReport.getMedicalDevices().get(0).setModelNumber(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "SerialNumber is present");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testCatalogNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setCatalogNumber("33");
        aeReport.getMedicalDevices().get(0).setModelNumber(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "CatalogNumber is present");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testLotNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setLotNumber("33");
        aeReport.getMedicalDevices().get(0).setModelNumber(null);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "LotNumber is present");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModelAndSerialNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        aeReport.getMedicalDevices().get(0).setSerialNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "ModelNumber and Serial is present");
    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModelAndCatalogNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        aeReport.getMedicalDevices().get(0).setCatalogNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "ModelNumber and Catalog Number is present");

    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModelAndLotNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        aeReport.getMedicalDevices().get(0).setLotNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "ModelNumber and Lot Number is present");

    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModel_Serial_CatalogNumberAlone() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        aeReport.getMedicalDevices().get(0).setSerialNumber("33");
        aeReport.getMedicalDevices().get(0).setCatalogNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "ModelNumber , Catalog Number and Serial Number is present");

    }

    /**
     * RuleName : SME_BR2_CHK Logic : Either 'Model#', 'Serial#', 'Catalog#' or 'Lot#' must be
     * provided Error Code : SME_BR2_ERR Error Message : Either MODEL_NUMBER, SERIAL_NUMBER,
     * CATALOG_NUMBER or LOT_NUMBER must be provided.
     */
    public void testModel_Serial_Catalog_LotNumber_Available() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setModelNumber("33");
        aeReport.getMedicalDevices().get(0).setSerialNumber("33");
        aeReport.getMedicalDevices().get(0).setLotNumber("33");
        aeReport.getMedicalDevices().get(0).setCatalogNumber("33");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "Model, Serial, Catalog and Lot numbers are present");

    }

    /**
     * RuleName : ADO_BR1_CHK Logic : "Device Operator Description" must be provided if "Device
     * Operator" is 'Other' Error Code : ADO_BR1_ERR Error Message : DEVICE_OPERATOR_DESCRIPTION
     * must be provided if (and only if) DEVICE_OPERATOR is "Other"
     */
    public void testDeviceOperatorOTHER_And_OtherOperator() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setDeviceOperator(DeviceOperator.OTHER);
        aeReport.getMedicalDevices().get(0).setOtherDeviceOperator("Other");
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors, "OTHER device operator and other operator's information is present");

    }

    /**
     * RuleName : ADO_BR1_CHK Logic : "Device Operator Description" must be provided if "Device
     * Operator" is 'Other' Error Code : ADO_BR1_ERR Error Message : DEVICE_OPERATOR_DESCRIPTION
     * must be provided if (and only if) DEVICE_OPERATOR is "Other"
     */
    public void testDeviceOperatorOTHER_And_NoOtherOperator() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setDeviceOperator(DeviceOperator.OTHER);
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "ADO_BR1_ERR");
        assertSameErrorCount(errors, 1);
    }

    /**
     * RuleName : ADO_BR1_CHK Logic : "Device Operator Description" must be provided if "Device
     * Operator" is 'Other' Error Code : ADO_BR1_ERR Error Message : DEVICE_OPERATOR_DESCRIPTION
     * must be provided if (and only if) DEVICE_OPERATOR is "Other"
     */
    public void testDeviceOperatorNotOTHER_And_NoOtherOperator() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setDeviceOperator(DeviceOperator.HEALTH_PROFESSIONAL);
        ValidationErrors errors = fireRules(aeReport);
        assertNoErrors(errors,
                        "Device Operator is HEALTHY PROFESSIONAL, and there is no other operator description");
    }

    /**
     * RuleName : ADO_BR1_CHK Logic : "Device Operator Description" must be provided if "Device
     * Operator" is 'Other' Error Code : ADO_BR1_ERR Error Message : DEVICE_OPERATOR_DESCRIPTION
     * must be provided if (and only if) DEVICE_OPERATOR is "Other"
     */
    public void testDeviceOperatorNotOTHER_And_OtherOperator() throws Exception {
        ExpeditedAdverseEventReport aeReport = createAEReport();
        MedicalDevice device = new MedicalDevice();
        device.setBrandName("Brand Name");
        device.setCommonName("Common Name");
        device.setModelNumber("abcd");
        aeReport.addMedicalDevice(device);

        aeReport.getMedicalDevices().get(0).setDeviceOperator(DeviceOperator.HEALTH_PROFESSIONAL);
        aeReport.getMedicalDevices().get(0).setOtherDeviceOperator("Other");
        ValidationErrors errors = fireRules(aeReport);
        assertCorrectErrorCode(errors, "ADO_BR1_ERR");
        assertSameErrorCount(errors, 1,
                        "Other Operator information must not be provied for non-OTHER operators");

    }
}
