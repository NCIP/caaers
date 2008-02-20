package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

/**
 * This class represents the Ctc domain object associated with the Adverse event report.
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ctc_versions")
public class Ctc extends AbstractImmutableDomainObject {
    private String name;
    private List<CtcCategory> categories;

    ////// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "ctc")
    @OrderBy // by ID for consistency
    @Cascade(value = { CascadeType.LOCK })
    public List<CtcCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CtcCategory> categories) {
        this.categories = categories;
    }
}
