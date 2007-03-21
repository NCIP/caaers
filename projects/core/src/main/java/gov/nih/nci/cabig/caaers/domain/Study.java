package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
public class Study extends AbstractDomainObject implements Serializable {
	
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
	// private String type;
	
	private Integer targetAccrualNumber;
	
	//TODO move into Command Object
	private String[] diseaseTermIds;
	private String   diseaseCategoryAsText;
			  	
	private List<StudySite> studySites = new ArrayList<StudySite>();			  
	private List<Identifier> identifiers = new ArrayList<Identifier>();
	private List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
	private List<StudyDisease> studyDiseases = new ArrayList<StudyDisease>();

	/// LOGIC	
	
	public void addStudySite(StudySite studySite)
	{
		studySites.add(studySite);
		studySite.setStudy(this);
	}
	
	/*
	public void addParticipation(Participation participation)
	{
		participations.add(participation);
		participation.setStudy(this);
	}
	*/
	
	public void addStudyAgent(StudyAgent studyAgent)
	{
		studyAgents.add(studyAgent);
		studyAgent.setStudy(this);
	}
	
	public void addStudyDisease(StudyDisease studyDisease)
	{
		studyDisease.setStudy(this);
		studyDiseases.add(studyDisease);
		
	}
	
	public void addIdentifier(Identifier identifier)
	{
		identifiers.add(identifier);
	}
	
	/// BEAN PROPERTIES
		
	@OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<StudySite> getStudySites() {
		return studySites;
	}
	
	@OneToMany
    @Cascade({ CascadeType.ALL,CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "STU_ID")
 	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	public void setStudySites(List<StudySite> studySites) {
		this.studySites = studySites;
	}
	
	/*
	@OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<Participation> getParticipations() {
		return participations;
	}
	
	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}
	*/
	
	@OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<StudyAgent> getStudyAgents() {
		return studyAgents;
	}
	
	public void setStudyAgents(List<StudyAgent> studyAgents) {
		this.studyAgents = studyAgents;
	}
	
	@OneToMany (mappedBy="study", fetch=FetchType.LAZY)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	public List<StudyDisease> getStudyDiseases() {
		return studyDiseases;
	}

	public void setStudyDiseases(List<StudyDisease> studyDiseases) {
		this.studyDiseases = studyDiseases;
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
	
	
	
	
	
	
	

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}

    //TODO
//	public int compareTo(Study o) {
//    	return 1;
//	}

	/*@Override
	 public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((diseaseCode == null) ? 0 : diseaseCode.hashCode());		
		result = PRIME * result + ((identifiers == null) ? 0 : identifiers.hashCode());
		result = PRIME * result + ((longTitle == null) ? 0 : longTitle.hashCode());
		result = PRIME * result + ((monitorCode == null) ? 0 : monitorCode.hashCode());
		result = PRIME * result + ((phaseCode == null) ? 0 : phaseCode.hashCode());
		result = PRIME * result + ((primarySponsorCode == null) ? 0 : primarySponsorCode.hashCode());
		result = PRIME * result + ((status == null) ? 0 : status.hashCode());
		result = PRIME * result + ((studySites == null) ? 0 : studySites.hashCode());
		result = PRIME * result + targetAccrualNumber;
		//result = PRIME * result + ((type == null) ? 0 : type.hashCode());
		return result;
	} 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Study other = (Study) obj;
		if (diseaseCode == null) {
			if (other.diseaseCode != null)
				return false;
		} else if (!diseaseCode.equals(other.diseaseCode))
			return false;		
		if (identifiers == null) {
			if (other.identifiers != null)
				return false;
		} else if (!identifiers.equals(other.identifiers))
			return false;
		if (longTitle == null) {
			if (other.longTitle != null)
				return false;
		} else if (!longTitle.equals(other.longTitle))
			return false;
		if (monitorCode == null) {
			if (other.monitorCode != null)
				return false;
		} else if (!monitorCode.equals(other.monitorCode))
			return false;
		if (phaseCode == null) {
			if (other.phaseCode != null)
				return false;
		} else if (!phaseCode.equals(other.phaseCode))
			return false;
		if (primarySponsorCode == null) {
			if (other.primarySponsorCode != null)
				return false;
		} else if (!primarySponsorCode.equals(other.primarySponsorCode))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (studySites == null) {
			if (other.studySites != null)
				return false;
		} else if (!studySites.equals(other.studySites))
			return false;
		if (targetAccrualNumber != other.targetAccrualNumber)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false; 
		return true;
	} */
}
