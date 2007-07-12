package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

/**
 * @author Kulasekaran
 * @author Rhett Sutphin
 */
@Entity
@Table (name="disease_histories")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_disease_histories_id")
    }
)
public class DiseaseHistory extends AbstractExpeditedReportSingleChild {
    private String otherPrimaryDisease;
    private String otherPrimaryDiseaseSite;
    private Date diagnosisDate;
    private CtepStudyDisease ctepStudyDisease;
    private AnatomicSite codedPrimaryDiseaseSite;
    private LazyListHelper listHelper = new LazyListHelper();

    public DiseaseHistory() {
        listHelper = new LazyListHelper();
        listHelper.add(MetastaticDiseaseSite.class);
    }

    public void addMetastaticDiseaseSite(MetastaticDiseaseSite site) {
        getMetastaticDiseaseSites().add(site);
    }

    @OneToOne
    @JoinColumn(name="coded_primary_disease_site_id")
    @Cascade(value = { CascadeType.ALL })
    public AnatomicSite getCodedPrimaryDiseaseSite() {
        return codedPrimaryDiseaseSite;
    }

    public void setCodedPrimaryDiseaseSite(AnatomicSite codedPrimaryDiseaseSite) {
        this.codedPrimaryDiseaseSite = codedPrimaryDiseaseSite;
    }

    @Column(name = "diagnosis_date")
    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(
        Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
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

    @OneToOne
    @JoinColumn(name="study_disease_id")
    @Cascade(value = { CascadeType.ALL })
    public CtepStudyDisease getCtepStudyDisease() {
        return ctepStudyDisease;
    }

    public void setCtepStudyDisease(CtepStudyDisease ctepStudyDisease) {
        this.ctepStudyDisease = ctepStudyDisease;
    }

    @Transient
    public List<MetastaticDiseaseSite> getMetastaticDiseaseSites() {
        return listHelper.getLazyList(MetastaticDiseaseSite.class);
    }

    @OneToMany
    @JoinColumn(name="disease_history_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<MetastaticDiseaseSite> getMetastaticDiseaseSitesInternal() {
        return listHelper.getInternalList(MetastaticDiseaseSite.class);
    }

    public void setMetastaticDiseaseSitesInternal(List<MetastaticDiseaseSite> metastaticDiseaseSite) {
        listHelper.setInternalList(MetastaticDiseaseSite.class, metastaticDiseaseSite);
    }
}

