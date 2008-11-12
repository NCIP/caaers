package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 *
 * @author Ion C. Olaru
 */

@Entity
@Table(name = "study_term")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_term_id")})
public class StudyTerm extends AbstractMutableDomainObject {

/*
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
*/
}