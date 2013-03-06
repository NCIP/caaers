/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;

 
/**
 * This class represents the MeddraVersion domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */

// No Direct conncection to Meddra tables implemented yet - Will need to be
// If or when we start supporting multiple versions of MedDRA
@Entity
@Table(name = "meddra_versions")
public class MeddraVersion extends AbstractImmutableDomainObject {
    
    /** The name. */
    private String name;

    // //// BEAN PROPERTIES

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
}
