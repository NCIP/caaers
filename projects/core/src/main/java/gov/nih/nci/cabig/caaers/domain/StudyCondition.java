package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

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
}