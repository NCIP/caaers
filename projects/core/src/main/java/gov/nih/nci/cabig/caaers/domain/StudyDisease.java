package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class represents the StudyDisease domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */

@Entity
@Table(name = "study_diseases")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_diseases_id") })
public class StudyDisease extends AbstractMutableDomainObject {

    private Study study;

    private DiseaseTerm diseaseTerm;

    private Boolean leadDisease;

    /*
     * Constructor -- Initializes participation at create time
     * 
     */
    public StudyDisease() {
        super();
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "disease_term_id")
    @Cascade(value = { CascadeType.ALL })
    public DiseaseTerm getDiseaseTerm() {
        return diseaseTerm;
    }

    public void setDiseaseTerm(DiseaseTerm diseaseTerm) {
        this.diseaseTerm = diseaseTerm;
    }

    public Boolean getLeadDisease() {
        return leadDisease;
    }

    public void setLeadDisease(Boolean leadDisease) {
        this.leadDisease = leadDisease;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StudyDisease that = (StudyDisease) o;

        if (study != null ? !study.equals(that.study) : that.study != null) return false;
        if (diseaseTerm != null ? !diseaseTerm.getId().equals(that.diseaseTerm.getId())
                        : that.diseaseTerm != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (diseaseTerm != null ? diseaseTerm.hashCode() : 0);
        result = 29 * result + (study != null ? study.hashCode() : 0);
        return result;
    }

}
