package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "metastatic_disease_sites")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_metastatic_disease_sites_id")
    }
)

// TODO: this sort of class might be better as a component rather than another domain object
public class MetastaticDiseaseSite extends AbstractIdentifiableDomainObject {
    private String otherSite;
    private AnatomicSite codedSite;

    public String getOtherSite() {
        return otherSite;
    }

    public void setOtherSite(String otherSite) {
        this.otherSite = otherSite;
    }

    @OneToOne
    @JoinColumn(name = "coded_site_id")
    public AnatomicSite getCodedSite() {
        return codedSite;
    }

    public void setCodedSite(AnatomicSite codedSite) {
        this.codedSite = codedSite;
    }
}

