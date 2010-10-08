package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * The parent class for all study interventions
 * @author: Biju Joseph
 */
@MappedSuperclass
public abstract class StudyIntervention extends AbstractMutableRetireableDomainObject implements StudyChild {
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }
}
