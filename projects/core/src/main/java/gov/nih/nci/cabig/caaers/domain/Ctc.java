package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import java.util.List;

/**
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

    @ManyToMany
    @JoinTable(
        name = "ctc_version_categories",
        joinColumns = @JoinColumn(name = "version_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    public List<CtcCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CtcCategory> categories) {
        this.categories = categories;
    }
}
