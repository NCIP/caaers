package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the MedicalDevice domain object associated with the Adverse event report.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_medical_devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_medical_devices_id")})
public class MedicalDevice extends AbstractExpeditedReportCollectionElementChild {

    private String brandName;

    private String commonName;

    private String deviceType;

    private String manufacturerName;

    private String manufacturerCity;

    private String manufacturerState;

    private String modelNumber;

    private String lotNumber;

    private String catalogNumber;

    private Date expirationDate;

    private String serialNumber;

    private String otherNumber;

    private DeviceOperator deviceOperator;

    private String otherDeviceOperator;

    private Date implantedDate;

    private Date explantedDate;

    private ReprocessedDevice deviceReprocessed;

    private String reprocessorName;

    private String reprocessorAddress;

    private Availability evaluationAvailability;

    private Date returnedDate;

    // //// LOGIC

    // //// BEAN PROPERTIES

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Column(name = "device_reprocessed_code")
    @Type(type = "deviceReprocessed")
    public ReprocessedDevice getDeviceReprocessed() {
        return deviceReprocessed;
    }

    public void setDeviceReprocessed(ReprocessedDevice deviceReprocessed) {
        this.deviceReprocessed = deviceReprocessed;
    }

    @Column(name = "device_operator_code")
    @Type(type = "deviceOperator")
    public DeviceOperator getDeviceOperator() {
        return deviceOperator;
    }

    public void setDeviceOperator(DeviceOperator deviceOperator) {
        this.deviceOperator = deviceOperator;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "evaluation_availability_code")
    @Type(type = "availability")
    public Availability getEvaluationAvailability() {
        return evaluationAvailability;
    }

    public void setEvaluationAvailability(Availability evaluationAvailability) {
        this.evaluationAvailability = evaluationAvailability;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExplantedDate() {
        return explantedDate;
    }

    public void setExplantedDate(Date explantedDate) {
        this.explantedDate = explantedDate;
    }

    public Date getImplantedDate() {
        return implantedDate;
    }

    public void setImplantedDate(Date implantedDate) {
        this.implantedDate = implantedDate;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getManufacturerCity() {
        return manufacturerCity;
    }

    public void setManufacturerCity(String manufacturerCity) {
        this.manufacturerCity = manufacturerCity;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerState() {
        return manufacturerState;
    }

    public void setManufacturerState(String manufacturerState) {
        this.manufacturerState = manufacturerState;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    public String getReprocessorAddress() {
        return reprocessorAddress;
    }

    public void setReprocessorAddress(String reprocessorAddress) {
        this.reprocessorAddress = reprocessorAddress;
    }

    public String getReprocessorName() {
        return reprocessorName;
    }

    public void setReprocessorName(String reprocessorName) {
        this.reprocessorName = reprocessorName;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOtherDeviceOperator() {
        return otherDeviceOperator;
    }

    public void setOtherDeviceOperator(String otherDeviceOperator) {
        this.otherDeviceOperator = otherDeviceOperator;
    }


    public MedicalDevice copy() {
        MedicalDevice medicalDevice = new MedicalDevice();
        BeanUtils.copyProperties(this, medicalDevice, new String[]{"id", "gridId",
                "version", "report"});

        return medicalDevice;

    }
}
