package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

/**
 * Domain object representing Study(Protocol)
 *
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "studies")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_studies_id")
    }
)
public class Study extends AbstractIdentifiableDomainObject implements Serializable {

	private Ctc ctcVersion;
    private Boolean blindedIndicator;
    private Boolean multiInstitutionIndicator;
    private Boolean randomizedIndicator;
    private String shortTitle;
    private String longTitle;
    private String description;
    private String precis;

    private String diseaseCode;
    private String monitorCode;
    private String phaseCode;
    private String primarySponsorCode;
    private String status;

    private Integer targetAccrualNumber;

    //TODO move into Command Object
    private String[] diseaseTermIds;
    private String   diseaseCategoryAsText;
    private String   diseaseLlt;

    private List<StudySite> studySites = new ArrayList<StudySite>();
    private List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
    //private List<StudyDisease> studyDiseases = new ArrayList<StudyDisease>();
    private List<CtepStudyDisease> ctepStudyDiseases = new ArrayList<CtepStudyDisease>();
    private List<MeddraStudyDisease> meddraStudyDiseases = new ArrayList<MeddraStudyDisease>();

    /// LOGIC

    // TODO: this stuff should really, really not be in here.  It's web-view/entry specific.

    @Transient
    public String[] getDiseaseTermIds() {
        return diseaseTermIds;
    }

    public void setDiseaseTermIds(String[] diseaseTermIds) {
        this.diseaseTermIds = diseaseTermIds;
    }

    @Transient
    public String getDiseaseCategoryAsText() {
        return diseaseCategoryAsText;
    }

    public void setDiseaseCategoryAsText(String diseaseCategoryAsText) {
        this.diseaseCategoryAsText = diseaseCategoryAsText;
    }
    
    @Transient
	public String getDiseaseLlt() {
		return diseaseLlt;
	}

	public void setDiseaseLlt(String diseaseLlt) {
		this.diseaseLlt = diseaseLlt;
	}

    public void addStudySite(StudySite studySite) {
        studySites.add(studySite);
        studySite.setStudy(this);
    }

    public void addStudyAgent(StudyAgent studyAgent) {
        studyAgents.add(studyAgent);
        studyAgent.setStudy(this);
    }

    /*
    public void addStudyDisease(StudyDisease studyDisease) {
        studyDisease.setStudy(this);
        studyDiseases.add(studyDisease);
    }
    */
    public void addCtepStudyDisease(CtepStudyDisease ctepStudyDisease) {
    	ctepStudyDisease.setStudy(this);
    	ctepStudyDiseases.add(ctepStudyDisease);
    }
    
    public void addMeddraStudyDisease(MeddraStudyDisease meddraStudyDisease) {
    	meddraStudyDisease.setStudy(this);
    	meddraStudyDiseases.add(meddraStudyDisease);
    }

    /// BEAN PROPERTIES

    @OneToOne
    @JoinColumn(name="ctc_id")
    public Ctc getCtcVersion() {
		return ctcVersion;
	}

	public void setCtcVersion(Ctc ctcVersion) {
		this.ctcVersion = ctcVersion;
	}    


    @Override
    @OneToMany
    @Cascade({ CascadeType.ALL,CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "STU_ID")
    public List<Identifier> getIdentifiers() {
        return super.getIdentifiers();
    }

    @OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudySite> getStudySites() {
        return studySites;
    }

    public void setStudySites(List<StudySite> studySites) {
        this.studySites = studySites;
    }

    @OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudyAgent> getStudyAgents() {
        return studyAgents;
    }

    public void setStudyAgents(List<StudyAgent> studyAgents) {
        this.studyAgents = studyAgents;
    }

    /*
    @OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudyDisease> getStudyDiseases() {
        return studyDiseases;
    }

    public void setStudyDiseases(List<StudyDisease> studyDiseases) {
        this.studyDiseases = studyDiseases;
    }
    */
    
    //@OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    //@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @OneToMany
    @JoinColumn(name="study_id", nullable=false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "term_type = 'ctep'") // it is pretty lame that this is necessary
    public List<CtepStudyDisease> getCtepStudyDiseases() {
		return ctepStudyDiseases;
	}

	public void setCtepStudyDiseases(List<CtepStudyDisease> ctepStudyDiseases) {
		this.ctepStudyDiseases = ctepStudyDiseases;
	}
	
	@OneToMany
    @JoinColumn(name="study_id", nullable=false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "term_type = 'meddra'") // it is pretty lame that this is necessary
    public List<MeddraStudyDisease> getMeddraStudyDiseases() {
		return meddraStudyDiseases;
	}

	public void setMeddraStudyDiseases(List<MeddraStudyDisease> meddraStudyDiseases) {
		this.meddraStudyDiseases = meddraStudyDiseases;
	}

	public Boolean getBlindedIndicator() {
        return blindedIndicator;
    }

    public void setBlindedIndicator(Boolean blindedIndicator) {
        this.blindedIndicator = blindedIndicator;
    }

    public Boolean getMultiInstitutionIndicator() {
        return multiInstitutionIndicator;
    }

    public void setMultiInstitutionIndicator(Boolean multiInstitutionIndicator) {
        this.multiInstitutionIndicator = multiInstitutionIndicator;
    }

    public Boolean getRandomizedIndicator() {
        return randomizedIndicator;
    }

    public void setRandomizedIndicator(Boolean randomizedIndicator) {
        this.randomizedIndicator = randomizedIndicator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descriptionText) {
        this.description = descriptionText;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitleText) {
        this.longTitle = longTitleText;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode;
    }

    public String getPrecis() {
        return precis;
    }

    public void setPrecis(String precisText) {
        this.precis = precisText;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitleText) {
        this.shortTitle = shortTitleText;
    }

    public String getPrimarySponsorCode() {
        return primarySponsorCode;
    }

    public void setPrimarySponsorCode(String sponsorCode) {
        this.primarySponsorCode = sponsorCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTargetAccrualNumber() {
        return targetAccrualNumber;
    }

    public void setTargetAccrualNumber(Integer targetAccrualNumber) {
        this.targetAccrualNumber = targetAccrualNumber;
    }
}
