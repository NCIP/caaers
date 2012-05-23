package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the Ctc domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ctc_versions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ctc_versions_id")})
public class Ctc extends AbstractMutableDomainObject {
    
	/** The CT c_ v2. */
	public static int CTC_V2 = 2;
	
	/** The CT c_ v3. */
	public static int CTC_V3 = 3;
	
	/** The CT c_ v4. */
	public static int CTC_V4 = 4;

    /** The name. */
    private String name;
    
    /** The categories. */
    private List<CtcCategory> categories= new ArrayList<CtcCategory>();

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
    @OneToMany(mappedBy = "ctc")
    @OrderBy
    // by ID for consistency
    @Cascade(value = { CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<CtcCategory> getCategories() {
        return categories;
    }

    /**
     * Sets the categories.
     *
     * @param categories the new categories
     */
    public void setCategories(List<CtcCategory> categories) {
        this.categories = categories;
    }
    
    public void addCtcCategory(CtcCategory ctcCategory){
    	ctcCategory.setCtc(this);
    	getCategories().add(ctcCategory);
    }
}
