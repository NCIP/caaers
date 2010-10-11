package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Represents the Study Device Intervention.
 * 
 * @author: Biju Joseph
 */
@Entity
@Table(name = "study_devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_device_id") })
public class StudyDevice extends StudyIntervention {

    private Device device;

    private String otherBrandName;

    private String otherCommonName;

    private String catalogNumber;

    private String manufacturerName;

    private String manufacturerCity;

    private String manufacturerState;

    private String modelNumber;

    private String otherDeviceType;

    @Transient
    public boolean isOtherDevice(){
        //need to change this
       return getDevice() == null;
    }

    @Transient
    public String getBrandName() {
        if(isOtherDevice()) return otherBrandName;
        return getDevice().getBrandName();
    }

    public void setBrandName(String brandName) {
        throw new UnsupportedOperationException("Use the equivalent method in Device");
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }
    @Transient
    public String getCommonName() {
        if(isOtherDevice()) return getOtherCommonName();
        return getDevice().getCommonName();
    }

    public void setCommonName(String commonName) {
         throw new UnsupportedOperationException("Use the equivalent method in Device");
    }
    @Transient
    public String getDeviceType() {
        if(isOtherDevice()) return getOtherDeviceType();
        return getDevice().getType();
    }

    public void setDeviceType(String deviceType) {
         throw new UnsupportedOperationException("Use the equivalent method in Device");
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
    @Column(name = "brand_name")
    public String getOtherBrandName() {
        return otherBrandName;
    }

    public void setOtherBrandName(String otherBrandName) {
        this.otherBrandName = otherBrandName;
    }
    @Column(name = "common_name")
    public String getOtherCommonName() {
        return otherCommonName;
    }

    public void setOtherCommonName(String otherCommonName) {
        this.otherCommonName = otherCommonName;
    }
    @Column(name = "device_type")
    public String getOtherDeviceType() {
        return otherDeviceType;
    }

    public void setOtherDeviceType(String otherDeviceType) {
        this.otherDeviceType = otherDeviceType;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
