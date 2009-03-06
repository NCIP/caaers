package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * This class represents the DiseaseCategory domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
public class DiseaseCategory extends AbstractImmutableDomainObject {
    private String name;

    private List<DiseaseTerm> terms;

    private DiseaseCategory parentCategory;

    private List<DiseaseCategory> childCategories;

    // //// BEAN PROPERTIES

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    @OrderBy
    // by ID for consistency
    public List<DiseaseTerm> getTerms() {
        return terms;
    }

    public void setTerms(List<DiseaseTerm> terms) {
        this.terms = terms;
    }

    @OneToMany(mappedBy = "parentCategory", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public List<DiseaseCategory> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<DiseaseCategory> childCategories) {
        this.childCategories = childCategories;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    public DiseaseCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(DiseaseCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

}
