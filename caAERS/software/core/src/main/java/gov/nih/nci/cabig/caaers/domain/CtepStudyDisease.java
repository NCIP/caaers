package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * This class represents the CtepStudyDisease domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("ctep")
public class CtepStudyDisease extends AbstractStudyDisease<DiseaseTerm> {

    
	private static final long serialVersionUID = 7943849778109284695L;

    @ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public DiseaseTerm getTerm() {
        return super.getTerm();
    }

    @Transient
    public DiseaseTerm getDiseaseTerm() {
        return super.getTerm();
    }

    @Transient
    public void setDiseaseTerm(DiseaseTerm diseaseTerm) {
        super.setTerm(diseaseTerm);
    }

    @Transient
    @Override
    public String getTermName() {
        return getTerm().getFullName();
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CtepStudyDisease that = (CtepStudyDisease) o;
        
        if(this.isRetired() || that.isRetired()) return false;
        
        if (this.getTerm() != null ? !this.getTerm().equals(that.getTerm()) : that.getTerm() != null) return false;

        return true;
    }
    
    public int hashCode() {
        int result;
        result = (this.getTerm() != null ? this.getTerm().hashCode() : 0);
        result = 29 * result ;
        return result;
    }
}
