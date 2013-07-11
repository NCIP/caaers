/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7943849778109284695L;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTerm()
     */
    @ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public DiseaseTerm getTerm() {
        return super.getTerm();
    }

    /**
     * Gets the disease term.
     *
     * @return the disease term
     */
    @Transient
    public DiseaseTerm getDiseaseTerm() {
        return super.getTerm();
    }

    /**
     * Sets the disease term.
     *
     * @param diseaseTerm the new disease term
     */
    @Transient
    public void setDiseaseTerm(DiseaseTerm diseaseTerm) {
        super.setTerm(diseaseTerm);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTermName()
     */
    @Transient
    @Override
    public String getTermName() {
        if(getTerm() == null) return null;
        return getTerm().getFullName();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || !(o instanceof CtepStudyDisease)) return false;

        final CtepStudyDisease that = (CtepStudyDisease) o;
        
        if(this.isRetired() || that.isRetired()) return false;
        
        if (this.getTerm() != null ? !this.getTerm().equals(that.getTerm()) : that.getTerm() != null) return false;

        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        int result;
        result = (this.getTerm() != null ? this.getTerm().hashCode() : 0);
        result = 29 * result ;
        return result;
    }
}
