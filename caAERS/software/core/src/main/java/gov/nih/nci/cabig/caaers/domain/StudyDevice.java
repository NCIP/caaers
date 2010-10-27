package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
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
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_devices_id") })
public class StudyDevice extends StudyIntervention {

    private Device device;
    private String brandName;
    private String commonName;
    private String catalogNumber;
    private String manufacturerName;
    private String manufacturerCity;
    private String manufacturerState;
    private String modelNumber;
    private String deviceType;

    public StudyDevice(){
       this(null);
       this.setStudyTherapyType(StudyTherapyType.DEVICE);
    }

    public StudyDevice(Device device){
        this.device = device;
    }


    @Transient
    public String getDisplayName() {
        if (!isOtherDevice()) {
            if (getDevice() != null) return getDevice().getDisplayName();
            else return "";
        } else {
            StringBuilder sb = new StringBuilder();
            if (StringUtils.isNotBlank(getCommonName())) {
                sb.append(getCommonName());
            }

            if (StringUtils.isNotBlank(getBrandName())) {
                if(sb.length() > 0) sb.append(", ");
                sb.append(getBrandName());
            }

            if (StringUtils.isNotBlank(getDeviceType())) {
                if(sb.length() > 0) sb.append(", ");
                sb.append(getDeviceType());
            }
            return sb.toString();
        }
    }

    @Transient
    public boolean isOtherDevice() {
       return (getDevice() == null && (StringUtils.isNotEmpty(getOtherCommonName()) || StringUtils.isNotEmpty(getOtherBrandName()) || StringUtils.isNotEmpty(getOtherDeviceType())));
    }

    @Transient
    public String getBrandName() {
        if(isOtherDevice()) return brandName;
        if (getDevice() != null) return getDevice().getBrandName(); else return null;
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
        if (isOtherDevice()) return getOtherCommonName();
        if (getDevice() != null) return getDevice().getCommonName(); else return null;
    }

    public void setCommonName(String commonName) {
         throw new UnsupportedOperationException("Use the equivalent method in Device");
    }
    @Transient
    public String getDeviceType() {
        if (isOtherDevice()) return getOtherDeviceType();
        if (getDevice() != null) return getDevice().getType(); else return null;
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
        return brandName;
    }

    public void setOtherBrandName(String otherBrandName) {
        this.brandName = otherBrandName;
    }
    @Column(name = "common_name")
    public String getOtherCommonName() {
        return commonName;
    }

    public void setOtherCommonName(String otherCommonName) {
        this.commonName = otherCommonName;
    }
    @Column(name = "device_type")
    public String getOtherDeviceType() {
        return deviceType;
    }

    public void setOtherDeviceType(String otherDeviceType) {
        this.deviceType = otherDeviceType;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if (this == o) return true;
        if(!(o instanceof StudyDevice)) return false;
        
        StudyDevice that = (StudyDevice) o;

        if(!StringUtils.equals(getCatalogNumber(), that.getCatalogNumber())) return false;
        if(!StringUtils.equals(getBrandName(), that.getBrandName())) return false;
        if(!StringUtils.equals(getCommonName(), that.getCommonName())) return false;
        if(!StringUtils.equals(getDeviceType(), that.getDeviceType())) return false;
        if(!StringUtils.equals(getManufacturerCity(), that.getManufacturerCity())) return false;
        if(!StringUtils.equals(getManufacturerName(), that.getManufacturerName())) return false;
        if(!StringUtils.equals(getManufacturerState(), that.getManufacturerState())) return false;
        if(!StringUtils.equals(getModelNumber(), that.getModelNumber())) return false;
        if(!ObjectUtils.equals(isOtherDevice(), that.isOtherDevice())) return false;

        return super.equals(that);

    }

    @Override
    public int hashCode() {
        int result = device != null ? device.hashCode() : 0;
        result = 31 * result + (getBrandName() != null ? getBrandName().hashCode() : 0);
        result = 31 * result + (getCommonName() != null ? getCommonName().hashCode() : 0);
        result = 31 * result + (getCatalogNumber() != null ? getCatalogNumber().hashCode() : 0);
        result = 31 * result + (getManufacturerName() != null ? getManufacturerName().hashCode() : 0);
        result = 31 * result + (getManufacturerCity() != null ? getManufacturerCity().hashCode() : 0);
        result = 31 * result + (getManufacturerState() != null ? getManufacturerState().hashCode() : 0);
        result = 31 * result + (getModelNumber() != null ? getModelNumber().hashCode() : 0);
        result = 31 * result + (getDeviceType() != null ? getDeviceType().hashCode() : 0);
        return result;
    }
}
