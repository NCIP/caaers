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
 */
@Entity
@Table(name = "ae_medical_devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_medical_devices_id")})
public class MedicalDevice extends AbstractExpeditedReportCollectionElementChild {

    /** The study device. */
    private StudyDevice studyDevice;
    
    /** The brand name. */
    private String brandName;

    /** The common name. */
    private String commonName;

    /** The device type. */
    private String deviceType;

    /** The manufacturer name. */
    private String manufacturerName;

    /** The manufacturer city. */
    private String manufacturerCity;

    /** The manufacturer state. */
    private String manufacturerState;

    /** The model number. */
    private String modelNumber;

    /** The lot number. */
    private String lotNumber;

    /** The catalog number. */
    private String catalogNumber;

    /** The expiration date. */
    private Date expirationDate;

    /** The serial number. */
    private String serialNumber;

    /** The other number. */
    private String otherNumber;

    /** The device operator. */
    private DeviceOperator deviceOperator;

    /** The other device operator. */
    private String otherDeviceOperator;

    /** The implanted date. */
    private Date implantedDate;

    /** The explanted date. */
    private Date explantedDate;

    /** The device reprocessed. */
    private ReprocessedDevice deviceReprocessed;

    /** The reprocessor name. */
    private String reprocessorName;

    /** The reprocessor address. */
    private String reprocessorAddress;

    /** The evaluation availability. */
    private Availability evaluationAvailability;

    /** The returned date. */
    private Date returnedDate;

    /**
     * Instantiates a new medical device.
     */
    public MedicalDevice(){
        this(null);
    }

    /**
     * Instantiates a new medical device.
     *
     * @param sd the sd
     */
    public MedicalDevice(StudyDevice sd){
        this.studyDevice = sd;
    }

    // //// LOGIC

    // //// BEAN PROPERTIES

    /**
     * Gets the study device.
     *
     * @return the study device
     */
    @ManyToOne
    @JoinColumn(name = "study_device_id")
    public StudyDevice getStudyDevice() {
        return studyDevice;
    }

    /**
     * Sets the study device.
     *
     * @param studyDevice the new study device
     */
    public void setStudyDevice(StudyDevice studyDevice) {
        this.studyDevice = studyDevice;
        if(studyDevice != null){
            setBrandName(studyDevice.getBrandName());
            setCommonName(studyDevice.getCommonName());
            setDeviceType(studyDevice.getDeviceType());
        }
    }
   
    /**
     * Gets the brand name.
     *
     * @return the brand name
     */
    public String getBrandName() {
        if(getStudyDevice() != null) setBrandName(getStudyDevice().getBrandName());
        return brandName;
    }

    /**
     * Sets the brand name.
     *
     * @param brandName the new brand name
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * Gets the catalog number.
     *
     * @return the catalog number
     */
    public String getCatalogNumber() {
        return catalogNumber;
    }

    /**
     * Sets the catalog number.
     *
     * @param catalogNumber the new catalog number
     */
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    /**
     * Gets the common name.
     *
     * @return the common name
     */
    public String getCommonName() {
        if(getStudyDevice() != null) setCommonName(getStudyDevice().getCommonName());
        return commonName;
    }

    /**
     * Sets the common name.
     *
     * @param commonName the new common name
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * Gets the device reprocessed.
     *
     * @return the device reprocessed
     */
    @Column(name = "device_reprocessed_code")
    @Type(type = "deviceReprocessed")
    public ReprocessedDevice getDeviceReprocessed() {
        return deviceReprocessed;
    }

    /**
     * Sets the device reprocessed.
     *
     * @param deviceReprocessed the new device reprocessed
     */
    public void setDeviceReprocessed(ReprocessedDevice deviceReprocessed) {
        this.deviceReprocessed = deviceReprocessed;
    }

    /**
     * Gets the device operator.
     *
     * @return the device operator
     */
    @Column(name = "device_operator_code")
    @Type(type = "deviceOperator")
    public DeviceOperator getDeviceOperator() {
        return deviceOperator;
    }

    /**
     * Sets the device operator.
     *
     * @param deviceOperator the new device operator
     */
    public void setDeviceOperator(DeviceOperator deviceOperator) {
        this.deviceOperator = deviceOperator;
    }

    /**
     * Gets the device type.
     *
     * @return the device type
     */
    public String getDeviceType() {
        if(getStudyDevice() != null) setDeviceType(getStudyDevice().getDeviceType());
        return deviceType;
    }

    /**
     * Sets the device type.
     *
     * @param deviceType the new device type
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Gets the evaluation availability.
     *
     * @return the evaluation availability
     */
    @Column(name = "evaluation_availability_code")
    @Type(type = "availability")
    public Availability getEvaluationAvailability() {
        return evaluationAvailability;
    }

    /**
     * Sets the evaluation availability.
     *
     * @param evaluationAvailability the new evaluation availability
     */
    public void setEvaluationAvailability(Availability evaluationAvailability) {
        this.evaluationAvailability = evaluationAvailability;
    }

    /**
     * Gets the expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date.
     *
     * @param expirationDate the new expiration date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the explanted date.
     *
     * @return the explanted date
     */
    public Date getExplantedDate() {
        return explantedDate;
    }

    /**
     * Sets the explanted date.
     *
     * @param explantedDate the new explanted date
     */
    public void setExplantedDate(Date explantedDate) {
        this.explantedDate = explantedDate;
    }

    /**
     * Gets the implanted date.
     *
     * @return the implanted date
     */
    public Date getImplantedDate() {
        return implantedDate;
    }

    /**
     * Sets the implanted date.
     *
     * @param implantedDate the new implanted date
     */
    public void setImplantedDate(Date implantedDate) {
        this.implantedDate = implantedDate;
    }

    /**
     * Gets the lot number.
     *
     * @return the lot number
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the lot number.
     *
     * @param lotNumber the new lot number
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Gets the manufacturer city.
     *
     * @return the manufacturer city
     */
    public String getManufacturerCity() {
        return manufacturerCity;
    }

    /**
     * Sets the manufacturer city.
     *
     * @param manufacturerCity the new manufacturer city
     */
    public void setManufacturerCity(String manufacturerCity) {
        this.manufacturerCity = manufacturerCity;
    }

    /**
     * Gets the manufacturer name.
     *
     * @return the manufacturer name
     */
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * Sets the manufacturer name.
     *
     * @param manufacturerName the new manufacturer name
     */
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * Gets the manufacturer state.
     *
     * @return the manufacturer state
     */
    public String getManufacturerState() {
        return manufacturerState;
    }

    /**
     * Sets the manufacturer state.
     *
     * @param manufacturerState the new manufacturer state
     */
    public void setManufacturerState(String manufacturerState) {
        this.manufacturerState = manufacturerState;
    }

    /**
     * Gets the model number.
     *
     * @return the model number
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * Sets the model number.
     *
     * @param modelNumber the new model number
     */
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    /**
     * Gets the other number.
     *
     * @return the other number
     */
    public String getOtherNumber() {
        return otherNumber;
    }

    /**
     * Sets the other number.
     *
     * @param otherNumber the new other number
     */
    public void setOtherNumber(String otherNumber) {
        this.otherNumber = otherNumber;
    }

    /**
     * Gets the reprocessor address.
     *
     * @return the reprocessor address
     */
    public String getReprocessorAddress() {
        return reprocessorAddress;
    }

    /**
     * Sets the reprocessor address.
     *
     * @param reprocessorAddress the new reprocessor address
     */
    public void setReprocessorAddress(String reprocessorAddress) {
        this.reprocessorAddress = reprocessorAddress;
    }

    /**
     * Gets the reprocessor name.
     *
     * @return the reprocessor name
     */
    public String getReprocessorName() {
        return reprocessorName;
    }

    /**
     * Sets the reprocessor name.
     *
     * @param reprocessorName the new reprocessor name
     */
    public void setReprocessorName(String reprocessorName) {
        this.reprocessorName = reprocessorName;
    }

    /**
     * Gets the returned date.
     *
     * @return the returned date
     */
    public Date getReturnedDate() {
        return returnedDate;
    }

    /**
     * Sets the returned date.
     *
     * @param returnedDate the new returned date
     */
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    /**
     * Gets the serial number.
     *
     * @return the serial number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the serial number.
     *
     * @param serialNumber the new serial number
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the other device operator.
     *
     * @return the other device operator
     */
    public String getOtherDeviceOperator() {
        return otherDeviceOperator;
    }

    /**
     * Sets the other device operator.
     *
     * @param otherDeviceOperator the new other device operator
     */
    public void setOtherDeviceOperator(String otherDeviceOperator) {
        this.otherDeviceOperator = otherDeviceOperator;
    }

}
