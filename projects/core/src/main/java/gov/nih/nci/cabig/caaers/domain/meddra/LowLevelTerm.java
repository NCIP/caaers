package gov.nih.nci.cabig.caaers.domain.meddra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="meddra_llt")
public class LowLevelTerm extends AbstractMeddraDomainObject {
   
	private String preferredTermId;

	@Column(name= "meddra_pt_id")
	public String getPreferredTermId() {
		return preferredTermId;
	}

	public void setPreferredTermId(String preferredTermId) {
		this.preferredTermId = preferredTermId;
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