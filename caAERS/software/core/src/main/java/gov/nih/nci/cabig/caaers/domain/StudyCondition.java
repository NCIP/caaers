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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

 
/**
 * The Class StudyCondition.
 *
 * @author Ion C. Olaru
 */

@Entity
@DiscriminatorValue("dcp")
public class StudyCondition extends AbstractStudyDisease<Condition> {

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTerm()
     */
    @ManyToOne(optional=false)
    @JoinColumn(name = "term_id", nullable = false)
    @Override
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.LOCK, CascadeType.EVICT})
    public Condition getTerm() {
        return super.getTerm();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractStudyDisease#getTermName()
     */
    @Override
    @Transient
    public String getTermName() {
        return getTerm().getConditionName();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof StudyCondition)) return false;

        if (this.getId() != null && ((StudyCondition)obj).getId() != null) {
            return (this.getId().intValue() == ((StudyCondition)obj).getId().intValue()); 
        } else {
            return this.getTerm().getConditionName().equals(((StudyCondition)obj).getTerm().getConditionName());
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return getTermName();
    }
    
}
