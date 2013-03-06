/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * Class to hold application wide defined devices.
 *
 * @author Ion C. Olaru
 */
@Entity
@Table(name = "devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_devices_id")})
public class Device extends AbstractMutableRetireableDomainObject implements Serializable {
    
    /** The brand name. */
    String brandName;
    
    /** The common name. */
    String commonName;
    
    /** The type. */
    String type;
    
    Date lastSynchedDate;
    
    String ctepDbIdentifier;

    public Date getLastSynchedDate() {
		return lastSynchedDate;
	}

	public void setLastSynchedDate(Date lastSynchedDate) {
		this.lastSynchedDate = lastSynchedDate;
	}

	/**
     * Gets the display name.
     *
     * @return the display name
     */
    @Transient
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotBlank(getCommonName())){
            sb.append(getCommonName());
        }
        if(StringUtils.isNotBlank(getBrandName())){
            if(sb.length() > 0) sb.append(", ");
            sb.append(getBrandName());
        }

        if(StringUtils.isNotBlank(getType())){
            if(sb.length() > 0) sb.append(", ");
            sb.append(getType());
        }
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (brandName == null || brandName.trim().equals("") ? 0 : brandName.trim().hashCode());
        result = prime * result + (commonName == null || commonName.trim().equals("") ? 0 : commonName.trim().hashCode());
        result = prime * result + (type == null || type.trim().equals("") ? 0 : type.trim().hashCode());
        result = prime * result + (ctepDbIdentifier == null || ctepDbIdentifier.trim().equals("") ? 0 : type.trim().hashCode());
        return result;
    }

    /**
     * A null value of a String field is considered to be equal to an empty String value of the same field.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
    	boolean found = false;
        if (this == obj) return true;
        if (obj == null) return false;
        final Device other = (Device) obj;

        if (!(this.getBrandName() != null ? this.getBrandName().trim() : "").equals(other.getBrandName() != null ? other.getBrandName().trim() : "")) return false;
        if (!(this.getCommonName() != null ? this.getCommonName().trim() : "").equals(other.getCommonName() != null ? other.getCommonName().trim() : "")) return false;
        if (!(this.getType() != null ? this.getType().trim() : "").equals(other.getType() != null ? other.getType().trim() : "")) return false;
        if (!(this.getCtepDbIdentifier() != null ? this.getCtepDbIdentifier().trim() : "").equals(other.getCtepDbIdentifier() != null ? other.getCtepDbIdentifier().trim() : "")) return false;

        return true;
    }
    
    /**
     * Gets the brand name.
     *
     * @return the brand name
     */
    public String getBrandName() {
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
     * Gets the common name.
     *
     * @return the common name
     */
    public String getCommonName() {
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
     * Gets the type.
     *
     * @return the type
     */
    @Column(name = "device_type")
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getCtepDbIdentifier() {
        return ctepDbIdentifier;
    }

    public void setCtepDbIdentifier(String ctepDbIdentifier) {
        this.ctepDbIdentifier = ctepDbIdentifier;
    }
}
