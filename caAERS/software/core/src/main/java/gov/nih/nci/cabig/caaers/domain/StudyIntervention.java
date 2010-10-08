package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * The parent class for all study interventions
 * @author: Biju Joseph
 * @author Ion C. Olaru
 */
@MappedSuperclass
public abstract class StudyIntervention extends AbstractMutableRetireableDomainObject implements StudyChild {

    private Study study;
    private String studyTherapyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    // ToDo @Transient should be removed later when the app will have all the needed changes related to this class
    @Transient
    public String getStudyTherapyType() {
        return studyTherapyType;
    }

    // ToDo @Transient should be removed later when the app will have all the needed changes related to this class
    @Transient
    public void setStudyTherapyType(String studyTherapyType) {
        this.studyTherapyType = studyTherapyType;
    }
}
