package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author Ion C. Olaru
 */

@Entity
@DiscriminatorValue("dcp")
public class StudyCondition extends AbstractStudyDisease<Condition> {

    @ManyToOne(optional=false)
    @JoinColumn(name = "term_id", nullable = false)
    @Override
    @Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.LOCK, CascadeType.EVICT})
    public Condition getTerm() {
        return super.getTerm();
    }

    @Override
    @Transient
    public String getTermName() {
        return getTerm().getConditionName();
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof StudyCondition)) return false;

        if (this.getId() != null && ((StudyCondition)obj).getId() != null) {
            return (this.getId().intValue() == ((StudyCondition)obj).getId().intValue()); 
        } else {
            return this.getTerm().getConditionName().equals(((StudyCondition)obj).getTerm().getConditionName());
        }
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return getTermName();
    }
    
}