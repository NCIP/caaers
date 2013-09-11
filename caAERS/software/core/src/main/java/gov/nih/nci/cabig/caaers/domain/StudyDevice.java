/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;


/**
 * Represents the Study Device Intervention.
 * 
 * @author: Biju Joseph
 */
@Entity
@Table(name = "study_devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_devices_id") })
public class StudyDevice extends StudyIntervention {

    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;

    /** The device. */
    private Device device;
    
    /** The brand name. */
    private String brandName;
    
    /** The common name. */
    private String commonName;
    
    /** The catalog number. */
    private String catalogNumber;
    
    /** The manufacturer name. */
    private String manufacturerName;
    
    /** The manufacturer city. */
    private String manufacturerCity;
    
    /** The manufacturer state. */
    private String manufacturerState;
    
    /** The model number. */
    private String modelNumber;
    
    /** The device type. */
    private String deviceType;

    /**
     * Instantiates a new study device.
     */
    public StudyDevice(){
       this(null);
       this.setStudyTherapyType(StudyTherapyType.DEVICE);
    }

    /**
     * Instantiates a new study device.
     *
     * @param device the device
     */
    public StudyDevice(Device device){
        this.device = device;
        lazyListHelper = new LazyListHelper();
        lazyListHelper.add(StudyDeviceINDAssociation.class, new StudyDeviceChildInstantiateFactory<StudyDeviceINDAssociation>(this,StudyDeviceINDAssociation.class));

    }

    public void addStudyDeviceINDAssociation(StudyDeviceINDAssociation sda){
        if(sda != null) sda.setStudyDevice(this);
        this.getStudyDeviceINDAssociations().add(sda);
    }
    /**
     * Gets the investigational new device indicator.
     *
     * @return the investigational new device indicator
     */
    @Transient
    public boolean getInvestigationalNewDeviceIndicator() {
        return getStudyDeviceINDAssociations() != null && getStudyDeviceINDAssociations().size() > 0;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
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

    /**
     * Checks if is other device.
     *
     * @return true, if is other device
     */
    @Transient
    public boolean isOtherDevice() {
       return (getDevice() == null && (StringUtils.isNotEmpty(getOtherCommonName()) || StringUtils.isNotEmpty(getOtherBrandName()) || StringUtils.isNotEmpty(getOtherDeviceType())));
    }

    /**
     * Gets the brand name.
     *
     * @return the brand name
     */
    @Transient
    public String getBrandName() {
        if(isOtherDevice()) return brandName;
        if (getDevice() != null) return getDevice().getBrandName(); else return null;
    }

    /**
     * Sets the brand name.
     *
     * @param brandName the new brand name
     */
    public void setBrandName(String brandName) {
        throw new UnsupportedOperationException("Use the equivalent method in Device");
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
    @Transient
    public String getCommonName() {
        if (isOtherDevice()) return getOtherCommonName();
        if (getDevice() != null) return getDevice().getCommonName(); else return null;
    }

    /**
     * Sets the common name.
     *
     * @param commonName the new common name
     */
    public void setCommonName(String commonName) {
         throw new UnsupportedOperationException("Use the equivalent method in Device");
    }
    
    /**
     * Gets the device type.
     *
     * @return the device type
     */
    @Transient
    public String getDeviceType() {
        if (isOtherDevice()) return getOtherDeviceType();
        if (getDevice() != null) return getDevice().getType(); else return null;
    }

    /**
     * Sets the device type.
     *
     * @param deviceType the new device type
     */
    public void setDeviceType(String deviceType) {
         throw new UnsupportedOperationException("Use the equivalent method in Device");
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
     * Gets the other brand name.
     *
     * @return the other brand name
     */
    @Column(name = "brand_name")
    public String getOtherBrandName() {
        return brandName;
    }

    /**
     * Sets the other brand name.
     *
     * @param otherBrandName the new other brand name
     */
    public void setOtherBrandName(String otherBrandName) {
        this.brandName = otherBrandName;
    }
    
    /**
     * Gets the other common name.
     *
     * @return the other common name
     */
    @Column(name = "common_name")
    public String getOtherCommonName() {
        return commonName;
    }

    /**
     * Sets the other common name.
     *
     * @param otherCommonName the new other common name
     */
    public void setOtherCommonName(String otherCommonName) {
        this.commonName = otherCommonName;
    }
    
    /**
     * Gets the other device type.
     *
     * @return the other device type
     */
    @Column(name = "device_type")
    public String getOtherDeviceType() {
        return deviceType;
    }

    /**
     * Sets the other device type.
     *
     * @param otherDeviceType the new other device type
     */
    public void setOtherDeviceType(String otherDeviceType) {
        this.deviceType = otherDeviceType;
    }

    /**
     * Gets the device.
     *
     * @return the device
     */
    @ManyToOne
    @JoinColumn(name = "device_id")
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the device.
     *
     * @param device the new device
     */
    public void setDevice(Device device) {
        this.device = device;
    }



    /**
     * Gets the study agent ind associations internal.
     *
     * @return the study agent ind associations internal
     */
    @OneToMany(mappedBy = "studyDevice", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade({ CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyDeviceINDAssociation> getStudyDeviceINDAssociationsInternal() {
        return lazyListHelper.getInternalList(StudyDeviceINDAssociation.class);
    }

    /**
     * Sets the study agent ind associations internal.
     *
     * @param StudyDeviceINDAssociations the new study agent ind associations internal
     */
    public void setStudyDeviceINDAssociationsInternal(List<StudyDeviceINDAssociation> StudyDeviceINDAssociations) {
        lazyListHelper.setInternalList(StudyDeviceINDAssociation.class, StudyDeviceINDAssociations);
    }

    /**
     * Gets the study agent ind associations.
     *
     * @return the study agent ind associations
     */
    @Transient
    public List<StudyDeviceINDAssociation> getStudyDeviceINDAssociations() {
        return lazyListHelper.getLazyList(StudyDeviceINDAssociation.class);
    }

    /**
     * Sets the study agent ind associations.
     *
     * @param StudyDeviceINDAssociations the new study agent ind associations
     */
    @Transient
    public void setStudyDeviceINDAssociations(
            List<StudyDeviceINDAssociation> StudyDeviceINDAssociations) {
        setStudyDeviceINDAssociationsInternal(StudyDeviceINDAssociations);
    }

    public boolean isHavingSameNameAndType(String commonName, String brandName, String deviceType){
        if(StringUtils.equalsIgnoreCase(commonName,  getCommonName()) &&
                StringUtils.equalsIgnoreCase(brandName, getBrandName()) &&
                StringUtils.equalsIgnoreCase(deviceType,  getDeviceType())) return true;

        return false;

    }


    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyIntervention#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyIntervention#hashCode()
     */
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
    
    @Transient
    @Override
    public  String getInterventionName() {
    	return getDisplayName();
    }
    
    /**
     * Gets the investigational new drug indicator.
     *
     * @return the investigational new drug indicator
     */
    @Transient
    public boolean getInvestigationalNewDrugIndicator() {
        return getStudyDeviceINDAssociations() != null && getStudyDeviceINDAssociations().size() > 0;
    }


    /**
     * Returns true if all are inactive
     * @return
     */
    @Transient
    public boolean getInvestigationalNewDrugInactive(){
        return !getInvestigationalNewDrugActive();
    }

    /**
     * Returns true if all the INDs are active
     * @return
     */
    @Transient
    public boolean getInvestigationalNewDrugActive(){
        if(CollectionUtils.isEmpty(getStudyDeviceINDAssociations())) return true;
        for(StudyDeviceINDAssociation ass: getStudyDeviceINDAssociations()){
            if(ass.getInvestigationalNewDrug() == null) continue;
            if(ass.getInvestigationalNewDrug().isInactive()) return false;
        }
        return true;
    }

    @Transient
    public boolean getHasIdeHeldByNci(){
        for(StudyDeviceINDAssociation sdia : getStudyDeviceINDAssociations()){
            if(sdia == null) continue;
            InvestigationalNewDrug ind = sdia.getInvestigationalNewDrug();
            if(ind == null) continue;
            if(ind.isHeldByNCI()) return true;
        }
        return false;
    }
}
