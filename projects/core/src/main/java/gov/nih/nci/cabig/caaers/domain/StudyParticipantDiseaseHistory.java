package gov.nih.nci.cabig.caaers.domain;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * This class represents the StudyParticipantDiseaseHistory domain object associated with the StudyParticipantAssignment.
 * 
 * @author Sameer Sawant
 */
@Entity
@Table(name = "spa_disease_histories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_spa_disease_histories_id") })
public class StudyParticipantDiseaseHistory extends AbstractMutableDomainObject {
    private String otherPrimaryDisease;

    private String otherPrimaryDiseaseSite;

    //private Date diagnosisDate;
    private DateValue diagnosisDate;

    // private CtepStudyDisease ctepStudyDisease;
    private AbstractStudyDisease abstractStudyDisease;

    private AnatomicSite codedPrimaryDiseaseSite;
    
    private StudyParticipantAssignment assignment;

    private LazyListHelper listHelper = new LazyListHelper();

    public StudyParticipantDiseaseHistory() {
        listHelper = new LazyListHelper();
        listHelper.add(StudyParticipantMetastaticDiseaseSite.class);
        this.diagnosisDate = new DateValue();
    }

    public void addMetastaticDiseaseSite(StudyParticipantMetastaticDiseaseSite site) {
        getMetastaticDiseaseSites().add(site);
    }

    @OneToOne
    @JoinColumn(name = "coded_primary_disease_site_id")
    @Cascade(value = { CascadeType.LOCK })
    public AnatomicSite getCodedPrimaryDiseaseSite() {
        return codedPrimaryDiseaseSite;
    }

    public void setCodedPrimaryDiseaseSite(AnatomicSite codedPrimaryDiseaseSite) {
        this.codedPrimaryDiseaseSite = codedPrimaryDiseaseSite;
    }

    

    @Column(name = "other_primary_disease")
    public String getOtherPrimaryDisease() {
        return otherPrimaryDisease;
    }

    public void setOtherPrimaryDisease(String otherPrimaryDisease) {
        this.otherPrimaryDisease = otherPrimaryDisease;
    }

    @Column(name = "other_primary_disease_site")
    public String getOtherPrimaryDiseaseSite() {
        return otherPrimaryDiseaseSite;
    }

    public void setOtherPrimaryDiseaseSite(String otherPrimaryDiseaseSite) {
        this.otherPrimaryDiseaseSite = otherPrimaryDiseaseSite;
    }

    @Transient
    public CtepStudyDisease getCtepStudyDisease() {
        if (this.abstractStudyDisease instanceof CtepStudyDisease) {
            return (CtepStudyDisease) abstractStudyDisease;
        } else {
            return null;
        }
    }

    @Transient
    public StudyCondition getOtherCondition() {
        if (this.abstractStudyDisease instanceof StudyCondition) {
            return (StudyCondition) abstractStudyDisease;
        } else {
            return null;
        }
    }

    @Transient
    public void setOtherCondition(StudyCondition studyCondition) {
        this.abstractStudyDisease = studyCondition;
    }

    @Transient
    public void setCtepStudyDisease(CtepStudyDisease ctepStudyDisease) {
        this.abstractStudyDisease = ctepStudyDisease;
    }

    @Transient
    public MeddraStudyDisease getMeddraStudyDisease() {
        if (this.abstractStudyDisease instanceof MeddraStudyDisease) {
            return (MeddraStudyDisease) abstractStudyDisease;
        } else {
            return null;
        }
    }

    @Transient
    public void setMeddraStudyDisease(MeddraStudyDisease meddraStudyDisease) {
        this.abstractStudyDisease = meddraStudyDisease;
    }

    @OneToOne
    @JoinColumn(name = "study_disease_id")
    @Cascade(value = { CascadeType.ALL })
    public AbstractStudyDisease getAbstractStudyDisease() {
        return abstractStudyDisease;
    }

    public void setAbstractStudyDisease(AbstractStudyDisease abstractStudyDisease) {
        this.abstractStudyDisease = abstractStudyDisease;
    }

    @Transient
    @UniqueObjectInCollection(message="Duplicate metastatic disease site")
    public List<StudyParticipantMetastaticDiseaseSite> getMetastaticDiseaseSites() {
        return listHelper.getLazyList(StudyParticipantMetastaticDiseaseSite.class);
    }

    @OneToMany
    @JoinColumn(name = "spa_disease_history_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<StudyParticipantMetastaticDiseaseSite> getMetastaticDiseaseSitesInternal() {
        return listHelper.getInternalList(StudyParticipantMetastaticDiseaseSite.class);
    }

    public void setMetastaticDiseaseSitesInternal(List<StudyParticipantMetastaticDiseaseSite> metastaticDiseaseSite) {
        listHelper.setInternalList(StudyParticipantMetastaticDiseaseSite.class, metastaticDiseaseSite);
    }
    
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
    public void setDiagnosisDate(DateValue diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
    
    /**
     * The below function is added to minimize the code changes, due to changing of
     * diagnosisDate from Date to DateValue
     * @param diagnosisDate
     */
    @Transient
    public void setDiagnosisDate(Date diagnosisDate) {
    	this.diagnosisDate = new DateValue(diagnosisDate);
    }
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    public StudyParticipantAssignment getAssignment(){
    	return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
		this.assignment = assignment;
	}
}
