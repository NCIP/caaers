package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the AnatomicSite domain object associated with the Adverse event report.
 * 
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table(name = "anatomic_sites")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_anatomic_sites_id") })
public class AnatomicSite extends AbstractIdentifiableDomainObject {
    
    /** The name. */
    private String name;

    /** The category. */
    private String category;

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }

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
    
    ///OBJECT METHODS

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnatomicSite)) return false;

        AnatomicSite that = (AnatomicSite) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

}
