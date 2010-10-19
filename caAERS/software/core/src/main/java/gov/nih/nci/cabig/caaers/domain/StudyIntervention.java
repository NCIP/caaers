package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * The parent class for all study interventions
 * @author: Biju Joseph
 * @author Ion C. Olaru
 */
@MappedSuperclass
public abstract class StudyIntervention extends AbstractMutableRetireableDomainObject implements StudyChild {

    private Study study;
    private StudyTherapyType studyTherapyType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    @Type(type = "studyTherapyType")
    @Column(name = "study_therapy_type")
    public StudyTherapyType getStudyTherapyType() {
        return studyTherapyType;
    }

    public void setStudyTherapyType(StudyTherapyType studyTherapyType) {
        this.studyTherapyType = studyTherapyType;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;
        if(!(o instanceof StudyIntervention)) return false;

        StudyIntervention that = (StudyIntervention) o;
        if(getId() != null && ObjectUtils.equals(getId(), that.getId())) return true;

        if(getStudy() != null && that.getStudy() != null && !getStudy().equals(that.getStudy())) return false;
        
        return true;
    }

    @Override
    public int hashCode() {
        int result = getStudy() != null ? getStudy().hashCode() : 0;
        return result;
    }
}
