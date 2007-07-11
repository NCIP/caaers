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
 * @version 1.0
 */
@Entity
@Table (name="metastatic_disease_site")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_metastatic_disease_site_id")
    }
)
public class MetastaticDiseaseSite extends AbstractIdentifiableDomainObject {
    // TODO: these properties should be renamed to be less redundant
    private String otherMetastaticDiseaseSite;
    // TODO: and more specific.  And consistent.
    private AnatomicSite anatomicSite;
	
	public String getOtherMetastaticDiseaseSite() {
		return otherMetastaticDiseaseSite;
	}

	public void setOtherMetastaticDiseaseSite(String otherMetastaticDiseaseSite) {
		this.otherMetastaticDiseaseSite = otherMetastaticDiseaseSite;
	}

	@OneToOne
	@JoinColumn(name="anatomic_site_id")
	@Cascade(value = { CascadeType.ALL })
	public AnatomicSite getAnatomicSite() {
		return anatomicSite;
	}

	public void setAnatomicSite(AnatomicSite anatomicSite) {
		this.anatomicSite = anatomicSite;
	}
}

