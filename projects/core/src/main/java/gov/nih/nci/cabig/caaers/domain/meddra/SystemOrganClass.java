package gov.nih.nci.cabig.caaers.domain.meddra;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.Table;

@Entity
@Table(name="meddra_soc")
public class SystemOrganClass extends AbstractMeddraDomainObject {
	
	private List<HighLevelGroupTerm> highLevelGroupTerms;
	
	 @ManyToMany(
		        cascade={CascadeType.PERSIST, CascadeType.MERGE}
		    )
	 @JoinTable(
		        name="meddra_soc_hlgt",
		        joinColumns={@JoinColumn(name="meddra_soc_id")},
		        inverseJoinColumns={@JoinColumn(name="meddra_hlgt_id")}
		    )
	public List<HighLevelGroupTerm> getHighLevelGroupTerms() {
		return highLevelGroupTerms;
	}

	public void setHighLevelGroupTerms(List<HighLevelGroupTerm> highLevelGroupTerms) {
		this.highLevelGroupTerms = highLevelGroupTerms;
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