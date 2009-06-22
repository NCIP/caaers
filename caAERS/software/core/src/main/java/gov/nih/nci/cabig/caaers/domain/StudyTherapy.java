package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Domain object representing Study Therapy
 *
 * @author Saurabh Agrawal
 */
@Entity
@Table(name = "study_therapy")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_therapy_id")})
public class StudyTherapy extends AbstractMutableDomainObject implements StudyChild {

    private Study study;

    private StudyTherapyType studyTherapyType;

    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(final Study study) {
        this.study = study;
    }

    @Column(name = "STUDY_THERAPY_TYPE")
    @Type(type = "studyTherapyType")
    public StudyTherapyType getStudyTherapyType() {
        return studyTherapyType;
    }

    public void setStudyTherapyType(final StudyTherapyType studyTherapyType) {
        this.studyTherapyType = studyTherapyType;
    }

    public StudyTherapy() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (study == null || study.getId() == null ? 0 : study.getId().hashCode());
        result = prime * result + (studyTherapyType == null ? 0 : studyTherapyType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudyTherapy other = (StudyTherapy) obj;
        if (study == null) {
            if (other.study != null) {
                return false;
            }
        } else if (study.getId() == null) {
            if (other.study == null ||other.study.getId() != null){
                return false;
            }
        } else if (!study.getId().equals(other.study.getId())) {
            return false;
        }
        if (studyTherapyType == null) {
            if (other.studyTherapyType != null) {
                return false;
            }
        } else if (!studyTherapyType.equals(other.studyTherapyType)) {
            return false;
        }
        return true;
    }

}
