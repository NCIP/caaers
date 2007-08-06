package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class AdverseEventMeddraLowLevelTerm extends AbstractAdverseEventTerm<LowLevelTerm> {
    
	private String meddraCode;
	
	public String getMeddraCode() {
		return meddraCode;
	}

	public void setMeddraCode(String meddraCode) {
		this.meddraCode = meddraCode;
	}
	
	@Transient
    public String getUniversalTerm() {
    		return getTerm() == null ? null : getTerm().getMeddraCode();
    }

	@OneToOne
    @JoinColumn(name = "term_id")
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }
	
	@Transient
	public LowLevelTerm getLowLevelTerm() {
		return super.getTerm();
	}
	
	@Transient
	public void setLowLevelTerm(LowLevelTerm lowlevelTerm) {
	       super.setTerm(lowlevelTerm);
	    }
	
}
