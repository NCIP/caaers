package gov.nih.nci.cabig.caaers.domain.meddra;

import java.util.List;

import gov.nih.nci.cabig.caaers.domain.AbstractImmutableDomainObject;
import gov.nih.nci.cabig.caaers.domain.DiseaseCategory;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Participation;

import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.Table;

@Entity
@Table(name="meddra_hlgt")
public class HighLevelGroupTerm extends AbstractMeddraDomainObject {
	
	private List<SystemOrganClass> systemOrganClasses;
	
	private List<HighLevelTerm> highlevelterms;
	
	
	@ManyToMany(
	        cascade={CascadeType.PERSIST, CascadeType.MERGE},
	        mappedBy="highLevelGroupTerms"
	    )
	public List<SystemOrganClass> getSystemOrganClasses() {
		return systemOrganClasses;
	}

	public void setSystemOrganClasses(List<SystemOrganClass> systemOrganClasses) {
		this.systemOrganClasses = systemOrganClasses;
	}

	@ManyToMany(
	        cascade={CascadeType.PERSIST, CascadeType.MERGE}
	    )
	    @JoinTable(
	        name="meddra_hlgt_hlt",
	        joinColumns={@JoinColumn(name="meddra_hlgt_id")},
	        inverseJoinColumns={@JoinColumn(name="meddra_hlt_id")}
	    )
	public List<HighLevelTerm> getHighlevelterms() {
		return highlevelterms;
	}

	public void setHighlevelterms(List<HighLevelTerm> highlevelterms) {
		this.highlevelterms = highlevelterms;
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