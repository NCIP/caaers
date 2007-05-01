package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class MeddraStudyDisease extends AbstractStudyDisease<LowLevelTerm> {
    
	private String meddraCode;
	
	public String getMeddraCode() {
		return meddraCode;
	}

	public void setMeddraCode(String meddraCode) {
		this.meddraCode = meddraCode;
	}

	@ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }
}
