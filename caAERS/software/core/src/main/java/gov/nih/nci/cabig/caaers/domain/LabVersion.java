/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;

 
/**
 * This class represents the LabVersion domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 * 
 * NOTE: this class is currently not utilized but it might be helpful for the future when new lists
 * of this lab data are to be imported into caAERS.
 * 
 */
@Entity
@Table(name = "lab_versions")
public class LabVersion extends AbstractImmutableDomainObject {
    
    /** The name. */
    private String name;

    /** The categories. */
    private List<LabCategory> categories;

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

    /**
     * Gets the categories.
     *
     * @return the categories
     */
    @OneToMany(mappedBy = "labVersion")
    @OrderBy
    // by ID for consistency
    @Cascade(value = { CascadeType.LOCK })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<LabCategory> getCategories() {
        return categories;
    }

    /**
     * Sets the categories.
     *
     * @param categories the new categories
     */
    public void setCategories(List<LabCategory> categories) {
        this.categories = categories;
    }
}
