package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;

/**
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("ctep")
public class AdverseEventCtcTerm extends AbstractAdverseEventTerm<CtcTerm> {
	
	@OneToOne
    @JoinColumn(name = "term_id")
    @Override
    public CtcTerm getTerm() {
        return super.getTerm();
    }
	
	@Transient
	public CtcTerm getCtcTerm() {
		return super.getTerm();
	}
	
	@Transient
	public void setCtcTerm(CtcTerm ctcTerm) {
	       super.setTerm(ctcTerm);
	    }
	
	
}
