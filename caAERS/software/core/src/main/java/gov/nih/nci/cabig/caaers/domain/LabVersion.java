package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    private String name;

    private List<LabCategory> categories;

    // //// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "labVersion")
    @OrderBy
    // by ID for consistency
    @Cascade(value = { CascadeType.LOCK })
    public List<LabCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<LabCategory> categories) {
        this.categories = categories;
    }
}
