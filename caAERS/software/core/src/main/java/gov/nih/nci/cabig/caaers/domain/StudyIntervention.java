/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

import javax.persistence.*;

 
/**
 * The parent class for all study interventions.
 *
 * @author: Biju Joseph
 * @author Ion C. Olaru
 */
@MappedSuperclass
public abstract class StudyIntervention extends AbstractMutableRetireableDomainObject implements StudyChild {

    /** The study. */
    private Study study;
    
    /** The study therapy type. */
    private StudyTherapyType studyTherapyType;

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#getStudy()
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#setStudy(gov.nih.nci.cabig.caaers.domain.Study)
     */
    public void setStudy(Study study) {
        this.study = study;
    }

    /**
     * Gets the study therapy type.
     *
     * @return the study therapy type
     */
    @Type(type = "studyTherapyType")
    @Column(name = "study_therapy_type")
    public StudyTherapyType getStudyTherapyType() {
        return studyTherapyType;
    }

    /**
     * Sets the study therapy type.
     *
     * @param studyTherapyType the new study therapy type
     */
    public void setStudyTherapyType(StudyTherapyType studyTherapyType) {
        this.studyTherapyType = studyTherapyType;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = getStudy() != null ? getStudy().hashCode() : 0;
        return result;
    }
    
    @Transient
    public abstract String getInterventionName();

    @Transient
    public String getHashKey() {
        String key = getStudyTherapyType().getName() + getInterventionName();
        return key;
    }

}
