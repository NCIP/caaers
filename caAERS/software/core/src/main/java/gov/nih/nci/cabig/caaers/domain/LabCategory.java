/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the LabCategory domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "lab_categories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "lab_categories_id_seq") })
public class LabCategory extends AbstractMutableRetireableDomainObject {
    
    /** The name. */
    private String name;

    /** The terms. */
    private List<LabTerm> terms = new ArrayList<LabTerm>();

    /** The lab version. */
    private LabVersion labVersion;

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
     * Gets the lab version.
     *
     * @return the lab version
     */
    @ManyToOne
    @JoinColumn(name = "version_id")
    public LabVersion getLabVersion() {
        return labVersion;
    }

    /**
     * Sets the lab version.
     *
     * @param labVersion the new lab version
     */
    public void setLabVersion(LabVersion labVersion) {
        this.labVersion = labVersion;
    }

    /**
     * Gets the terms.
     *
     * @return the terms
     */
    @OneToMany(mappedBy = "category")
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // by ID for consistency
    public List<LabTerm> getTerms() {
        return terms;
    }

    /**
     * Sets the terms.
     *
     * @param terms the new terms
     */
    public void setTerms(List<LabTerm> terms) {
        this.terms = terms;
    }
    
    public void addTerm(LabTerm term){
    	term.setCategory(this);
    	getTerms().add(term);
    }
}
