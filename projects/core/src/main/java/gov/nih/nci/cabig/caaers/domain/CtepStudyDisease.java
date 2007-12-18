package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("ctep")
public class CtepStudyDisease extends AbstractStudyDisease<DiseaseTerm> {
    
	private Boolean leadDisease;
	
	public Boolean getLeadDisease() {
		return leadDisease;
	}

	public void setLeadDisease(Boolean leadDisease) {
		this.leadDisease = leadDisease;
	}
	
	@ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public DiseaseTerm getTerm() {
        return super.getTerm();
    }
	
	@Transient
	public DiseaseTerm getDiseaseTerm(){
		return super.getTerm();
	}
	
	@Transient
	public void setDiseaseTerm(DiseaseTerm diseaseTerm){
		super.setTerm(diseaseTerm);
	}
	
	@Transient
	@Override
	public String getTermName() {
		return getTerm().getFullName();
	}
}
