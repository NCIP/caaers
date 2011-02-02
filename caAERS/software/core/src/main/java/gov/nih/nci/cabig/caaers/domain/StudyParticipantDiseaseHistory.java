package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;

 
/**
 * This class represents the StudyParticipantDiseaseHistory domain object associated with the StudyParticipantAssignment.
 * 
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_disease_histories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_spa_disease_histories_id") })
public class StudyParticipantDiseaseHistory extends AbstractMutableDomainObject {
    
    /** The other primary disease. */
    private String otherPrimaryDisease;

    /** The other primary disease site. */
    private String otherPrimaryDiseaseSite;

    //private Date diagnosisDate;
    /** The diagnosis date. */
    private DateValue diagnosisDate;

    // private CtepStudyDisease ctepStudyDisease;
    /** The abstract study disease. */
    private AbstractStudyDisease abstractStudyDisease;

    /** The coded primary disease site. */
    private AnatomicSite codedPrimaryDiseaseSite;
    
    /** The assignment. */
    private StudyParticipantAssignment assignment;

    /** The list helper. */
    private LazyListHelper listHelper = new LazyListHelper();

    /**
     * Instantiates a new study participant disease history.
     */
    public StudyParticipantDiseaseHistory() {
        listHelper = new LazyListHelper();
        listHelper.add(StudyParticipantMetastaticDiseaseSite.class);
        this.diagnosisDate = new DateValue();
    }

    /**
     * Adds the metastatic disease site.
     *
     * @param site the site
     */
    public void addMetastaticDiseaseSite(StudyParticipantMetastaticDiseaseSite site) {
        getMetastaticDiseaseSites().add(site);
    }

    /**
     * Gets the coded primary disease site.
     *
     * @return the coded primary disease site
     */
    @OneToOne
    @JoinColumn(name = "coded_primary_disease_site_id")
    @Cascade(value = { CascadeType.LOCK })
    public AnatomicSite getCodedPrimaryDiseaseSite() {
        return codedPrimaryDiseaseSite;
    }

    /**
     * Sets the coded primary disease site.
     *
     * @param codedPrimaryDiseaseSite the new coded primary disease site
     */
    public void setCodedPrimaryDiseaseSite(AnatomicSite codedPrimaryDiseaseSite) {
        this.codedPrimaryDiseaseSite = codedPrimaryDiseaseSite;
    }

    

    /**
     * Gets the other primary disease.
     *
     * @return the other primary disease
     */
    @Column(name = "other_primary_disease")
    public String getOtherPrimaryDisease() {
        return otherPrimaryDisease;
    }

    /**
     * Sets the other primary disease.
     *
     * @param otherPrimaryDisease the new other primary disease
     */
    public void setOtherPrimaryDisease(String otherPrimaryDisease) {
        this.otherPrimaryDisease = otherPrimaryDisease;
    }

    /**
     * Gets the other primary disease site.
     *
     * @return the other primary disease site
     */
    @Column(name = "other_primary_disease_site")
    public String getOtherPrimaryDiseaseSite() {
        return otherPrimaryDiseaseSite;
    }

    /**
     * Sets the other primary disease site.
     *
     * @param otherPrimaryDiseaseSite the new other primary disease site
     */
    public void setOtherPrimaryDiseaseSite(String otherPrimaryDiseaseSite) {
        this.otherPrimaryDiseaseSite = otherPrimaryDiseaseSite;
    }

    /**
     * Gets the ctep study disease.
     *
     * @return the ctep study disease
     */
    @Transient
    public CtepStudyDisease getCtepStudyDisease() {
        if (this.abstractStudyDisease instanceof CtepStudyDisease) {
            return (CtepStudyDisease) abstractStudyDisease;
        } else {
            return null;
        }
    }

    /**
     * Gets the other condition.
     *
     * @return the other condition
     */
    @Transient
    public StudyCondition getOtherCondition() {
        if (this.abstractStudyDisease instanceof StudyCondition) {
            return (StudyCondition) abstractStudyDisease;
        } else {
            return null;
        }
    }

    /**
     * Sets the other condition.
     *
     * @param studyCondition the new other condition
     */
    @Transient
    public void setOtherCondition(StudyCondition studyCondition) {
        this.abstractStudyDisease = studyCondition;
    }

    /**
     * Sets the ctep study disease.
     *
     * @param ctepStudyDisease the new ctep study disease
     */
    @Transient
    public void setCtepStudyDisease(CtepStudyDisease ctepStudyDisease) {
        this.abstractStudyDisease = ctepStudyDisease;
    }

    /**
     * Gets the meddra study disease.
     *
     * @return the meddra study disease
     */
    @Transient
    public MeddraStudyDisease getMeddraStudyDisease() {
        if (this.abstractStudyDisease instanceof MeddraStudyDisease) {
            return (MeddraStudyDisease) abstractStudyDisease;
        } else {
            return null;
        }
    }

    /**
     * Sets the meddra study disease.
     *
     * @param meddraStudyDisease the new meddra study disease
     */
    @Transient
    public void setMeddraStudyDisease(MeddraStudyDisease meddraStudyDisease) {
        this.abstractStudyDisease = meddraStudyDisease;
    }

    /**
     * Gets the abstract study disease.
     *
     * @return the abstract study disease
     */
    @OneToOne
    @JoinColumn(name = "study_disease_id")
    @Cascade(value = { CascadeType.ALL })
    public AbstractStudyDisease getAbstractStudyDisease() {
        return abstractStudyDisease;
    }

    /**
     * Sets the abstract study disease.
     *
     * @param abstractStudyDisease the new abstract study disease
     */
    public void setAbstractStudyDisease(AbstractStudyDisease abstractStudyDisease) {
        this.abstractStudyDisease = abstractStudyDisease;
    }

    /**
     * Gets the metastatic disease sites.
     *
     * @return the metastatic disease sites
     */
    @Transient
    @UniqueObjectInCollection(message="Duplicate metastatic disease site")
    public List<StudyParticipantMetastaticDiseaseSite> getMetastaticDiseaseSites() {
        return listHelper.getLazyList(StudyParticipantMetastaticDiseaseSite.class);
    }

    /**
     * Gets the metastatic disease sites internal.
     *
     * @return the metastatic disease sites internal
     */
    @OneToMany
    @JoinColumn(name = "spa_disease_history_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantMetastaticDiseaseSite> getMetastaticDiseaseSitesInternal() {
        return listHelper.getInternalList(StudyParticipantMetastaticDiseaseSite.class);
    }

    /**
     * Sets the metastatic disease sites internal.
     *
     * @param metastaticDiseaseSite the new metastatic disease sites internal
     */
    public void setMetastaticDiseaseSitesInternal(List<StudyParticipantMetastaticDiseaseSite> metastaticDiseaseSite) {
        listHelper.setInternalList(StudyParticipantMetastaticDiseaseSite.class, metastaticDiseaseSite);
    }
    
    /**
     * Gets the diagnosis date.
     *
     * @return the diagnosis date
     */
    @Embedded
    @AttributeOverrides({ 
    	@AttributeOverride(name = "day", column = @Column(name = "diagnosis_day")),
        @AttributeOverride(name = "month", column = @Column(name = "diagnosis_month")),
        @AttributeOverride(name = "year", column = @Column(name = "diagnosis_year")),
        @AttributeOverride(name = "zone", column = @Column(name = "diagnosis_zone"))
    })
    public DateValue getDiagnosisDate() {
		return diagnosisDate;
	}
    
    /**
     * Sets the diagnosis date.
     *
     * @param diagnosisDate the new diagnosis date
     */
    public void setDiagnosisDate(DateValue diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
    
    /**
     * The below function is added to minimize the code changes, due to changing of
     * diagnosisDate from Date to DateValue.
     *
     * @param diagnosisDate the new diagnosis date
     */
    @Transient
    public void setDiagnosisDate(Date diagnosisDate) {
    	this.diagnosisDate = new DateValue(diagnosisDate);
    }
    
    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    public StudyParticipantAssignment getAssignment(){
    	return assignment;
    }

    /**
     * Sets the assignment.
     *
     * @param assignment the new assignment
     */
    public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}
}
