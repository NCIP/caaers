/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
 * Domain object representing Study Therapy.
 *
 * @author Saurabh Agrawal
 */
@Entity
@Table(name = "study_therapy")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_therapy_id")})
@Deprecated
public class StudyTherapy extends AbstractMutableDomainObject implements StudyChild {

    /** The study. */
    private Study study;
    
    /** The study therapy type. */
    private StudyTherapyType studyTherapyType;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#getStudy()
     */
    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#setStudy(gov.nih.nci.cabig.caaers.domain.Study)
     */
    public void setStudy(final Study study) {
        this.study = study;
    }

    /**
     * Gets the study therapy type.
     *
     * @return the study therapy type
     */
    @Column(name = "STUDY_THERAPY_TYPE")
    @Type(type = "studyTherapyType")
    public StudyTherapyType getStudyTherapyType() {
        return studyTherapyType;
    }

    /**
     * Sets the study therapy type.
     *
     * @param studyTherapyType the new study therapy type
     */
    public void setStudyTherapyType(final StudyTherapyType studyTherapyType) {
        this.studyTherapyType = studyTherapyType;
    }

    /**
     * Instantiates a new study therapy.
     */
    public StudyTherapy() {
    }

    /**
     * Instantiates a new study therapy.
     *
     * @param s the s
     * @param therapyType the therapy type
     */
    public StudyTherapy(Study s, StudyTherapyType therapyType){
        this.study = s;
        this.studyTherapyType = therapyType;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (study == null || study.getId() == null ? 0 : study.getId().hashCode());
        result = prime * result + (studyTherapyType == null ? 0 : studyTherapyType.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
