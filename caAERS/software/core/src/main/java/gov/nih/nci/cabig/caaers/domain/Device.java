package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.lang.ComparisonTools;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Class to hold application wide defined devices
 * @author Ion C. Olaru
 *
 * */
@Entity
@Table(name = "devices")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_devices_id")})
public class Device extends AbstractMutableDomainObject implements Serializable {
    String brandName;
    String commonName;
    String type;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (brandName == null || brandName.trim().equals("") ? 0 : brandName.trim().hashCode());
        result = prime * result + (commonName == null || commonName.trim().equals("") ? 0 : commonName.trim().hashCode());
        result = prime * result + (type == null || type.trim().equals("") ? 0 : type.trim().hashCode());
        return result;
    }

    /**
     * A null value of a String field is considered to be equal to an empty String value of the same field
     * */
    @Override
    public boolean equals(Object obj) {
    	boolean found = false;
        if (this == obj) return true;
        if (obj == null) return false;
        final Device other = (Device) obj;

        if (!(this.getBrandName() != null ? this.getBrandName().trim() : "").equals(other.getBrandName() != null ? other.getBrandName().trim() : "")) return false;
        if (!(this.getCommonName() != null ? this.getCommonName().trim() : "").equals(other.getCommonName() != null ? other.getCommonName().trim() : "")) return false;
        if (!(this.getType() != null ? this.getType().trim() : "").equals(other.getType() != null ? other.getType().trim() : "")) return false;

        return true;
    }
    
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Column(name = "device_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
