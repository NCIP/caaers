/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

 
/**
 * This class represents the MeddraStudyDisease domain object associated with the Adverse event
 * report.
 * 
 * @author Krikor Krumlian
 */
@Entity
@DiscriminatorValue("meddra")
public class MeddraStudyDisease extends AbstractStudyDisease<LowLevelTerm> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2191830866356536610L;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTerm()
     */
    @ManyToOne
    @JoinColumn(name = "term_id")
    @Override
    public LowLevelTerm getTerm() {
        return super.getTerm();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTermName()
     */
    @Override
    @Transient
    public String getTermName() {
        if(getTerm() == null) return null;
        return getTerm().getFullName();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof  MeddraStudyDisease)) return false;

        final MeddraStudyDisease that = (MeddraStudyDisease) o;
        
        if(this.isRetired() || that.isRetired()) return false;
        
        if (getTerm() != null ? !getTerm().equals(that.getTerm()) : that.getTerm() != null) return false;

        return true;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.getTerm() == null ? 0 : this.getTerm().hashCode());
        return result;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return this.getTermName();
    }

}
