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
 * This class represents the Ctc domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ctc_versions")
public class Ctc extends AbstractImmutableDomainObject {
    
	public static int CTC_V2 = 2;
	public static int CTC_V3 = 3;
	public static int CTC_V4 = 4;

    private String name;
    private List<CtcCategory> categories;

    // //// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "ctc")
    @OrderBy
    // by ID for consistency
    @Cascade(value = { CascadeType.LOCK })
    public List<CtcCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<CtcCategory> categories) {
        this.categories = categories;
    }
}
