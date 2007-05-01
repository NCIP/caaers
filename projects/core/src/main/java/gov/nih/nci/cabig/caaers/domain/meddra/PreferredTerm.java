package gov.nih.nci.cabig.caaers.domain.meddra;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AbstractImmutableDomainObject;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Participation;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="meddra_pt")
public class PreferredTerm extends AbstractMeddraDomainObject {
	
	private List<LowLevelTerm> lowLevelTerms;
	
	private List<HighLevelTerm> highLevelTerms;

	@OneToMany
    @JoinColumn(name = "meddra_pt_id")
	public List<LowLevelTerm> getLowLevelTerms() {
		return lowLevelTerms;
	}

	public void setLowLevelTerms(List<LowLevelTerm> lowLevelTerms) {
		this.lowLevelTerms = lowLevelTerms;
	}

	@ManyToMany(
	        cascade={CascadeType.PERSIST, CascadeType.MERGE},
	        mappedBy="preferredTerms"
	    )
	public List<HighLevelTerm> getHighLevelTerms() {
		return highLevelTerms;
	}

	public void setHighLevelTerms(List<HighLevelTerm> highLevelTerms) {
		this.highLevelTerms = highLevelTerms;
	}
	
	
	
	
   

   /* 
    public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final DiseaseTerm that = (DiseaseTerm) o;

		if (ctepTerm != null ? !ctepTerm.equals(that.ctepTerm)
				: that.ctepTerm != null)
			return false;
		if (medraCode != null ? !medraCode.equals(that.medraCode)
				: that.medraCode != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (ctepTerm != null ? ctepTerm.hashCode() : 0);
		result = 29 * result + (medraCode != null ? medraCode.hashCode() : 0);
		return result;
	}
	*/
}