package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractImmutableDomainObject;
import org.hibernate.annotations.Fetch;

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
    
    /** The name. */
    private String name;

    /** The terms. */
    private List<DiseaseTerm> terms;

    /** The parent category. */
    private DiseaseCategory parentCategory;

    /** The child categories. */
    private List<DiseaseCategory> childCategories;

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
     * Gets the terms.
     *
     * @return the terms
     */
    @OneToMany(mappedBy = "category")
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // by ID for consistency
    public List<DiseaseTerm> getTerms() {
        return terms;
    }

    /**
     * Sets the terms.
     *
     * @param terms the new terms
     */
    public void setTerms(List<DiseaseTerm> terms) {
        this.terms = terms;
    }

    /**
     * Gets the child categories.
     *
     * @return the child categories
     */
    @OneToMany(mappedBy = "parentCategory", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<DiseaseCategory> getChildCategories() {
        return childCategories;
    }

    /**
     * Sets the child categories.
     *
     * @param childCategories the new child categories
     */
    public void setChildCategories(List<DiseaseCategory> childCategories) {
        this.childCategories = childCategories;
    }

    /**
     * Gets the parent category.
     *
     * @return the parent category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    public DiseaseCategory getParentCategory() {
        return parentCategory;
    }

    /**
     * Sets the parent category.
     *
     * @param parentCategory the new parent category
     */
    public void setParentCategory(DiseaseCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

}
