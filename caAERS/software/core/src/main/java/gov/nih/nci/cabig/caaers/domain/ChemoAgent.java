/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    /** The name. */
    private String name;
    
    /** The generic name. */
    private String genericName;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the generic name.
     *
     * @return the generic name
     */
    public String getGenericName() {
		return genericName;
	}
    
    /**
     * Sets the generic name.
     *
     * @param genericName the new generic name
     */
    public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
    
    /**
     * Gets the full name.
     *
     * @return the full name
     */
    @Transient
    public String getFullName(){
    	return name + " (" + genericName + ")";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return getFullName();
    }

}
