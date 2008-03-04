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
    private String name;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
