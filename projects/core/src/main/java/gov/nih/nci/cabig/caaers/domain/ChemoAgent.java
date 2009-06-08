package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * This class represents the ChemoAgent domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
@Entity
public class ChemoAgent extends AbstractImmutableDomainObject {
    private String name;
    private String genericName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getGenericName() {
		return genericName;
	}
    
    public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
    
    @Transient
    public String getFullName(){
    	return name + "(" + genericName + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ChemoAgent other = (ChemoAgent) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }
    
    @Override
    public String toString() {
    	return getFullName();
    }

}
