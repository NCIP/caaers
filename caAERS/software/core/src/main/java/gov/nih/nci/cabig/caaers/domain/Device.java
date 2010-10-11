package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (brandName == null ? 0 : brandName.hashCode());
        result = prime * result + (commonName == null ? 0 : commonName.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
    	boolean found = false;
        if (this == obj) return true;
        if (obj == null) return false;
        final Device other = (Device) obj;

        if (!this.getBrandName().equals(other.getBrandName())) return false;
        if (!this.getCommonName().equals(other.getCommonName())) return false;
        if (!this.getType().equals(other.getType())) return false;

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
