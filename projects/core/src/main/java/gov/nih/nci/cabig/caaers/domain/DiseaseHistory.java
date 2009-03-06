package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

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
import org.springframework.beans.BeanUtils;

/**
 * This class represents the DiseaseHistory domain object associated with the Adverse event report.
 *
 * @author Kulasekaran
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@Entity
@Table(name = "disease_histories")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_disease_histories_id")})
public class DiseaseHistory extends AbstractExpeditedReportSingleChild {
    private String otherPrimaryDisease;

    private String otherPrimaryDiseaseSite;

    //private Date diagnosisDate;
    private DateValue diagnosisDate;

    // private CtepStudyDisease ctepStudyDisease;
    private AbstractStudyDisease abstractStudyDisease;

    private AnatomicSite codedPrimaryDiseaseSite;

    private LazyListHelper listHelper = new LazyListHelper();

    public DiseaseHistory() {
        listHelper = new LazyListHelper();
        listHelper.add(MetastaticDiseaseSite.class);
        this.diagnosisDate = new DateValue();
    }

    public void addMetastaticDiseaseSite(MetastaticDiseaseSite site) {
        getMetastaticDiseaseSites().add(site);
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "coded_primary_disease_site_id")
    @Cascade(value = {CascadeType.ALL})
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
    public CtepStudyDisease getCtepStudyDisease() {
        if (this.abstractStudyDisease instanceof CtepStudyDisease) {
            return (CtepStudyDisease) abstractStudyDisease;
        } else {
            return null;
        }
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

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "study_disease_id")
    @Cascade(value = {CascadeType.ALL})
    public AbstractStudyDisease getAbstractStudyDisease() {
        return abstractStudyDisease;
    }

    public void setAbstractStudyDisease(AbstractStudyDisease abstractStudyDisease) {
        this.abstractStudyDisease = abstractStudyDisease;
    }

    @Transient
    @UniqueObjectInCollection(message = "Duplicate metastatic disease site")
    public List<MetastaticDiseaseSite> getMetastaticDiseaseSites() {
        return listHelper.getLazyList(MetastaticDiseaseSite.class);
    }

    @OneToMany
    @JoinColumn(name = "disease_history_id")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<MetastaticDiseaseSite> getMetastaticDiseaseSitesInternal() {
        return listHelper.getInternalList(MetastaticDiseaseSite.class);
    }

    public void setMetastaticDiseaseSitesInternal(List<MetastaticDiseaseSite> metastaticDiseaseSite) {
        listHelper.setInternalList(MetastaticDiseaseSite.class, metastaticDiseaseSite);
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
     *
     * @param diagnosisDate
     */
    @Transient
    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = new DateValue(diagnosisDate);
    }

    public static DiseaseHistory createDiseaseHistory(StudyParticipantDiseaseHistory studyParticipantDiseaseHistory) {
        if (studyParticipantDiseaseHistory != null) {
            DiseaseHistory saeReportDiseaseHistory = copyBasicProperties(studyParticipantDiseaseHistory);

            for (StudyParticipantMetastaticDiseaseSite metastaticDiseaseSite : studyParticipantDiseaseHistory.getMetastaticDiseaseSites()) {
                saeReportDiseaseHistory.addMetastaticDiseaseSite(MetastaticDiseaseSite.
                        createReportMetastaticDiseaseSite(metastaticDiseaseSite));
            }

            return saeReportDiseaseHistory;

        }
        return null;
    }

    private static DiseaseHistory copyBasicProperties(Object object) {
        DiseaseHistory saeReportDiseaseHistory = new DiseaseHistory();
        BeanUtils.copyProperties(object, saeReportDiseaseHistory, new String[]{"id", "gridId", "version", "report", "metastaticDiseaseSitesInternal", "metastaticDiseaseSites", "meddraStudyDisease", "ctepStudyDisease", "otherCondition"});
        return saeReportDiseaseHistory;
    }

    public DiseaseHistory copy() {

        DiseaseHistory saeReportDiseaseHistory = copyBasicProperties(this);

        for (MetastaticDiseaseSite metastaticDiseaseSite : this.getMetastaticDiseaseSites()) {
            saeReportDiseaseHistory.addMetastaticDiseaseSite(metastaticDiseaseSite.copy());
        }
        return saeReportDiseaseHistory;

    }
}
