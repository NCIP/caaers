package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.*;

import gov.nih.nci.cabig.caaers.domain.ReprocessedDevice;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the MedicalDevice domain object associated with the Adverse event report.
 *
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Entity
@Table(name = "ae_medical_devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_medical_devices_id")})
public class MedicalDevice extends AbstractExpeditedReportCollectionElementChild {

    private StudyDevice studyDevice;

    private String lotNumber;

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
    @ManyToOne
    @JoinColumn(name = "study_device_id")
    public StudyDevice getStudyDevice() {
        return studyDevice;
    }

    public void setStudyDevice(StudyDevice studyDevice) {
        this.studyDevice = studyDevice;
    }

    @Transient
    public String getBrandName() {
       return getStudyDevice().getBrandName();
    }

    public void setBrandName(String brandName) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }
    @Transient
    public String getCatalogNumber() {
        return getStudyDevice().getCatalogNumber();
    }

    public void setCatalogNumber(String catalogNumber) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }
    
    @Transient
    public String getCommonName() {
        return getStudyDevice().getCommonName();
    }

    public void setCommonName(String commonName) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }

    @Transient
    public String getDeviceType() {
        return getStudyDevice().getDeviceType();
    }

    public void setDeviceType(String deviceType) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }

    @Transient
    public String getManufacturerName() {
        return getStudyDevice().getManufacturerName();
    }

    public void setManufacturerName(String manufacturerName) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }
    @Transient
    public String getManufacturerState() {
        return getStudyDevice().getManufacturerState();
    }

    public void setManufacturerState(String manufacturerState) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
    }
    @Transient
    public String getModelNumber() {
        return getStudyDevice().getModelNumber();
    }

    public void setModelNumber(String modelNumber) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
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
    
    @Transient
    public String getManufacturerCity() {
        return getStudyDevice().getManufacturerCity();
    }

    public void setManufacturerCity(String manufacturerCity) {
        throw new UnsupportedOperationException("Need to make equivalent call in StudyDevice");
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
