package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author Kulasekaran
 * @version 1.0
 */
@Entity
@Table (name="disease_history")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_disease_history_id")
    }
)
public class DiseaseHistory extends AbstractIdentifiableDomainObject
{			
	private String otherPrimaryDiseaseCode;
	private String otherPrimaryDiseaseSiteCode;
	private Date dateOfInitialPathologicDiagnosis;
	private StudyDisease studyDisease;
	private AnatomicSite anatomicSite;
	private List<MetastaticDiseaseSite> metastaticDiseaseSite = new ArrayList<MetastaticDiseaseSite>(); 

	@OneToOne
	@JoinColumn(name="anatomic_site_id")
	@Cascade(value = { CascadeType.ALL })
	public AnatomicSite getAnatomicSite() {
		return anatomicSite;
	}

	public void setAnatomicSite(AnatomicSite anatomicSite) {
		this.anatomicSite = anatomicSite;
	}

	@Column(name = "diagnosis_date")
	public Date getDateOfInitialPathologicDiagnosis() {
		return dateOfInitialPathologicDiagnosis;
	}
	
	public void setDateOfInitialPathologicDiagnosis(
			Date dateOfInitialPathologicDiagnosis) {
		this.dateOfInitialPathologicDiagnosis = dateOfInitialPathologicDiagnosis;
	}
	
	@Column(name = "other_disease_code")
	public String getOtherPrimaryDiseaseCode() {
		return otherPrimaryDiseaseCode;
	}
	
	public void setOtherPrimaryDiseaseCode(String otherPrimaryDiseaseCode) {
		this.otherPrimaryDiseaseCode = otherPrimaryDiseaseCode;
	}
	
	@Column(name = "other_disease_site_code")
	public String getOtherPrimaryDiseaseSiteCode() {
		return otherPrimaryDiseaseSiteCode;
	}
	
	public void setOtherPrimaryDiseaseSiteCode(String otherPrimaryDiseaseSiteCode) {
		this.otherPrimaryDiseaseSiteCode = otherPrimaryDiseaseSiteCode;
	}
	
	@OneToOne
	@JoinColumn(name="study_disease_id")
	@Cascade(value = { CascadeType.ALL })	  
	public StudyDisease getStudyDisease() {
		return studyDisease;
	}
	
	public void setStudyDisease(StudyDisease studyDisease) {
		this.studyDisease = studyDisease;
	}

	@OneToMany
    @JoinColumn(name="disease_history_id")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN}) 	
	public List<MetastaticDiseaseSite> getMetastaticDiseaseSite() {
		return metastaticDiseaseSite;
	}

	public void setMetastaticDiseaseSite(
			List<MetastaticDiseaseSite> metastaticDiseaseSite) {
		this.metastaticDiseaseSite = metastaticDiseaseSite;
	}
}

