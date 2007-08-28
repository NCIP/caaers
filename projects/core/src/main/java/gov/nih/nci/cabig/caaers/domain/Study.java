package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.utils.ProjectedList;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections15.functors.InstantiateFactory;
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
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_studies_id") })
public class Study extends AbstractIdentifiableDomainObject implements Serializable {

	private String shortTitle;

	private String longTitle;

	private String description;

	private String precis;

	private String phaseCode;

	private Terminology terminology;

	private String status;

	private Boolean blindedIndicator;

	private Boolean multiInstitutionIndicator;

	private Boolean randomizedIndicator;

	private String diseaseCode;

	private String monitorCode;

	private Integer targetAccrualNumber;

	private List<StudyOrganization> studyOrganizations;

	private List<CtepStudyDisease> ctepStudyDiseases = new ArrayList<CtepStudyDisease>();

	private List<MeddraStudyDisease> meddraStudyDiseases = new ArrayList<MeddraStudyDisease>();

	private final LazyListHelper lazyListHelper;

	private OrganizationAssignedIdentifier organizationAssignedIdentifier;

	private List<StudyTherapy> studyTherapies = new ArrayList<StudyTherapy>();

	// TODO move into Command Object
	private String[] diseaseTermIds;

	private String diseaseCategoryAsText;

	private String diseaseLlt;

	private List<Identifier> identifiersLazy = new ArrayList<Identifier>();

	private List<Identifier> identifiers = new ArrayList<Identifier>();

	private int studySiteIndex = -1; // represents the studysite, selected in the (add Investigators page)

	private Boolean drugAdministrationTherapyType = Boolean.FALSE;

	private Boolean radiationTherapyType = Boolean.FALSE;

	private Boolean deviceTherapyType = Boolean.FALSE;

	private Boolean surgeryTherapyType = Boolean.FALSE;

	public Study() {

		lazyListHelper = new LazyListHelper();

		// register with lazy list helper study site.
		lazyListHelper.add(StudySite.class, new StudyChildInstantiateFactory<StudySite>(this, StudySite.class));
		lazyListHelper.add(StudyFundingSponsor.class, new StudyChildInstantiateFactory<StudyFundingSponsor>(this,
				StudyFundingSponsor.class));
		lazyListHelper.add(Identifier.class, new InstantiateFactory<Identifier>(Identifier.class));
		lazyListHelper.add(StudyAgent.class, new StudyChildInstantiateFactory<StudyAgent>(this, StudyAgent.class));
		lazyListHelper.add(StudyAmendment.class, new InstantiateFactory<StudyAmendment>(StudyAmendment.class));

		lazyListHelper.add(TreatmentAssignment.class, new InstantiateFactory<TreatmentAssignment>(
				TreatmentAssignment.class));

		// mandatory, so that the lazy-projected list is created/managed properly.
		setStudyOrganizations(new ArrayList<StudyOrganization>());
		setStudyAgentsInternal(new ArrayList<StudyAgent>());

		multiInstitutionIndicator = Boolean.FALSE;
	}

	// / LOGIC
	public void addStudyOrganization(final StudyOrganization so) {
		getStudyOrganizations().add(so);
		so.setStudy(this);
	}

	public void addTreatmentAssignment(final TreatmentAssignment treatmentAssignment) {
		getTreatmentAssignments().add(treatmentAssignment);
		treatmentAssignment.setStudy(this);
	}

	public void removeStudyOrganization(final StudyOrganization so) {
		getStudyOrganizations().remove(so);
	}

	public void addAmendment(final StudyAmendment amendment) {
		getStudyAmendments().add(amendment);
	}

	@Transient
	public List<StudySite> getStudySites() {
		return lazyListHelper.getLazyList(StudySite.class);
	}

	public void addStudySite(final StudySite studySite) {
		getStudySites().add(studySite);
		studySite.setStudy(this);
	}

	public void removeStudySite(final StudySite studySite) {
		getStudySites().remove(studySite);
	}

	public void addStudyFundingSponsor(final StudyFundingSponsor studyFundingSponsor) {
		getStudyFundingSponsors().add(studyFundingSponsor);
		studyFundingSponsor.setStudy(this);
	}

	@Transient
	public List<StudyFundingSponsor> getStudyFundingSponsors() {
		return lazyListHelper.getLazyList(StudyFundingSponsor.class);
	}

	@Transient
	public StudyFundingSponsor getPrimaryFundingSponsor() {
		for (StudyFundingSponsor sponsor : getStudyFundingSponsors()) {
			if (sponsor.isPrimary()) {
				return sponsor;
			}
		}
		return null;
	}

	@Transient
	public Organization getPrimaryFundingSponsorOrganization() {
		StudyFundingSponsor primarySponsor = getPrimaryFundingSponsor();
		if (primarySponsor == null) {
			return null;
		}
		return primarySponsor.getOrganization();
	}

	@Transient
	public void setPrimaryFundingSponsorOrganization(final Organization org) {
		// if already a primary funding sponsor exist, replace that sponor's organization
		StudyFundingSponsor xprimarySponsor = getPrimaryFundingSponsor();
		if (xprimarySponsor != null) {
			xprimarySponsor.setOrganization(org);
		}
		else {
			// no primary funding sponsor yet exist, so create one
			List<StudyFundingSponsor> sponsors = getStudyFundingSponsors();
			int size = sponsors.size();
			StudyFundingSponsor primarySponsor = sponsors.get(size);
			primarySponsor.setOrganization(org);
		}
	}

	public void addStudyAgent(final StudyAgent studyAgent) {
		getStudyAgents().add(studyAgent);
		studyAgent.setStudy(this);
	}

	public void addCtepStudyDisease(final CtepStudyDisease ctepStudyDisease) {
		ctepStudyDisease.setStudy(this);
		ctepStudyDiseases.add(ctepStudyDisease);
	}

	public void addMeddraStudyDisease(final MeddraStudyDisease meddraStudyDisease) {
		meddraStudyDisease.setStudy(this);
		meddraStudyDiseases.add(meddraStudyDisease);
	}

	@Transient
	public List<StudyCoordinatingCenter> getStudyCoordinatingCenters() {
		// TODO : Convert this to lazy list (if needed)
		List<StudyCoordinatingCenter> centers = new ArrayList<StudyCoordinatingCenter>();
		for (StudyOrganization studyOrg : getStudyOrganizations()) {
			if (studyOrg instanceof StudyCoordinatingCenter) {
				centers.add((StudyCoordinatingCenter) studyOrg);
			}
		}
		return centers;
		// return lazyListHelper.getLazyList(StudyCoordinatingCenter.class);
	}

	@Transient
	public StudyCoordinatingCenter getStudyCoordinatingCenter() {
		return getStudyCoordinatingCenters().isEmpty() ? null : getStudyCoordinatingCenters().get(0);
		// return lazyListHelper.getLazyList(StudyCoordinatingCenter.class);
	}

	@Transient
	public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {

		if (getId() != null && multiInstitutionIndicator) {
			for (Identifier identifier : getIdentifiers()) {

				if (identifier instanceof OrganizationAssignedIdentifier
						&& identifier.getType().equalsIgnoreCase("Co-ordinating Center Identifier")) {
					organizationAssignedIdentifier = (OrganizationAssignedIdentifier) identifier;
					return organizationAssignedIdentifier;
				}

			}
		}
		if (organizationAssignedIdentifier == null) {
			organizationAssignedIdentifier = new OrganizationAssignedIdentifier();
			organizationAssignedIdentifier.setType("Co-ordinating Center Identifier");
			organizationAssignedIdentifier.setPrimaryIndicator(Boolean.FALSE);
		}

		return organizationAssignedIdentifier;
	}

	@Transient
	public void setOrganizationAssignedIdentifier(final OrganizationAssignedIdentifier organizationAssignedIdentifier) {
		this.organizationAssignedIdentifier = organizationAssignedIdentifier;
	}

	@Transient
	public List<StudyAgent> getStudyAgents() {
		return lazyListHelper.getLazyList(StudyAgent.class);
	}

	@Transient
	public void setStudyAgents(final List<StudyAgent> studyAgents) {
		setStudyAgentsInternal(studyAgents);
	}

	// / BEAN PROPERTIES

	// TODO: this stuff should really, really not be in here. It's web-view/entry specific.
	@Transient
	public int getStudySiteIndex() {
		return studySiteIndex; // returns the index of the study site selected in investigators page
	}

	public void setStudySiteIndex(final int studySiteIndex) {
		this.studySiteIndex = studySiteIndex;
	}

	@Transient
	public String[] getDiseaseTermIds() {
		return diseaseTermIds;
	}

	public void setDiseaseTermIds(final String[] diseaseTermIds) {
		this.diseaseTermIds = diseaseTermIds;
	}

	@Transient
	public String getDiseaseCategoryAsText() {
		return diseaseCategoryAsText;
	}

	public void setDiseaseCategoryAsText(final String diseaseCategoryAsText) {
		this.diseaseCategoryAsText = diseaseCategoryAsText;
	}

	@Transient
	public String getDiseaseLlt() {
		return diseaseLlt;
	}

	public void setDiseaseLlt(final String diseaseLlt) {
		this.diseaseLlt = diseaseLlt;
	}

	@Deprecated
	@Transient
	public Ctc getCtcVersion() {
		return getTerminology().getCtcVersion();
	}

	@Deprecated
	@Transient
	public void setCtcVersion(final Ctc ctcVersion) {
		Terminology t = getTerminology();
		t.setTerm(Term.CTC);
		t.setCtcVersion(ctcVersion);
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "study")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public Terminology getTerminology() {
		if (terminology == null) {
			terminology = new Terminology();
			terminology.setStudy(this);
		}
		return terminology;
	}

	public void setTerminology(final Terminology terminology) {
		this.terminology = terminology;
	}

	@Override
	@OneToMany
	@Cascade( { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "STU_ID")
	public List<Identifier> getIdentifiers() {
		// return lazyListHelper.getInternalList(Identifier.class);
		return identifiers;
	}

	@Override
	public void setIdentifiers(final List<Identifier> identifiers) {
		this.identifiers = identifiers;
		// lazyListHelper.setInternalList(Identifier.class, identifiers);
	}

	@Transient
	public List<Identifier> getIdentifiersLazy() {

		if (getIdentifiers() != null && !getIdentifiers().isEmpty() && identifiersLazy.isEmpty()) {
			for (Identifier identifier : getIdentifiers()) {
				if (!identifier.getType().equalsIgnoreCase("Co-ordinating Center Identifier")) {
					identifiersLazy.add(identifier);
				}

			}
		}
		return identifiersLazy;
	}

	@Transient
	public void setIdentifiersLazy(final List<Identifier> identifiers) {
		identifiersLazy = identifiers;
		// setIdentifiers(identifiers);
	}

	@OneToMany(mappedBy = "study", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<StudyAgent> getStudyAgentsInternal() {
		return lazyListHelper.getInternalList(StudyAgent.class);
	}

	public void setStudyAgentsInternal(final List<StudyAgent> studyAgents) {
		lazyListHelper.setInternalList(StudyAgent.class, studyAgents);
	}

	@OneToMany
	@JoinColumn(name = "study_id", nullable = false)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@Where(clause = "term_type = 'ctep'")
	// it is pretty lame that this is necessary
	public List<CtepStudyDisease> getCtepStudyDiseases() {
		return ctepStudyDiseases;
	}

	public void setCtepStudyDiseases(final List<CtepStudyDisease> ctepStudyDiseases) {
		this.ctepStudyDiseases = ctepStudyDiseases;
	}

	@OneToMany
	@JoinColumn(name = "study_id", nullable = false)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@Where(clause = "term_type = 'meddra'")
	// it is pretty lame that this is necessary
	public List<MeddraStudyDisease> getMeddraStudyDiseases() {
		return meddraStudyDiseases;
	}

	public void setMeddraStudyDiseases(final List<MeddraStudyDisease> meddraStudyDiseases) {
		this.meddraStudyDiseases = meddraStudyDiseases;
	}

	public Boolean getBlindedIndicator() {
		return blindedIndicator;
	}

	public void setBlindedIndicator(final Boolean blindedIndicator) {
		this.blindedIndicator = blindedIndicator;
	}

	public Boolean getMultiInstitutionIndicator() {
		return multiInstitutionIndicator;
	}

	public void setMultiInstitutionIndicator(final Boolean multiInstitutionIndicator) {
		this.multiInstitutionIndicator = multiInstitutionIndicator;
	}

	public Boolean getRandomizedIndicator() {
		return randomizedIndicator;
	}

	public void setRandomizedIndicator(final Boolean randomizedIndicator) {
		this.randomizedIndicator = randomizedIndicator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String descriptionText) {
		description = descriptionText;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(final String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getLongTitle() {
		return longTitle;
	}

	public void setLongTitle(final String longTitleText) {
		longTitle = longTitleText;
	}

	public String getMonitorCode() {
		return monitorCode;
	}

	public void setMonitorCode(final String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public String getPhaseCode() {
		return phaseCode;
	}

	public void setPhaseCode(final String phaseCode) {
		this.phaseCode = phaseCode;
	}

	public String getPrecis() {
		return precis;
	}

	public void setPrecis(final String precisText) {
		precis = precisText;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(final String shortTitleText) {
		shortTitle = shortTitleText;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Integer getTargetAccrualNumber() {
		return targetAccrualNumber;
	}

	public void setTargetAccrualNumber(final Integer targetAccrualNumber) {
		this.targetAccrualNumber = targetAccrualNumber;
	}

	@OneToMany(mappedBy = "study", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<StudyOrganization> getStudyOrganizations() {
		return studyOrganizations;
	}

	public void setStudyOrganizations(final List<StudyOrganization> studyOrganizations) {
		this.studyOrganizations = studyOrganizations;
		// initialize projected list for StudySite, StudyFundingSponsor and StudyCoordinatingCenter
		lazyListHelper.setInternalList(StudySite.class, new ProjectedList<StudySite>(this.studyOrganizations,
				StudySite.class));
		lazyListHelper.setInternalList(StudyFundingSponsor.class, new ProjectedList<StudyFundingSponsor>(
				this.studyOrganizations, StudyFundingSponsor.class));
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "stu_id", nullable = false)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<StudyAmendment> getStudyAmendmentsInternal() {
		return lazyListHelper.getInternalList(StudyAmendment.class);
	}

	public void setStudyAmendmentsInternal(final List<StudyAmendment> amendments) {
		lazyListHelper.setInternalList(StudyAmendment.class, amendments);
	}

	@Transient
	public List<StudyAmendment> getStudyAmendments() {
		return lazyListHelper.getLazyList(StudyAmendment.class);
	}

	public void setStudyAmendments(final List<StudyAmendment> amendments) {
		setStudyAmendmentsInternal(amendments);
	}

	@OneToMany(mappedBy = "study", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<TreatmentAssignment> getTreatmentAssignmentsInternal() {
		return lazyListHelper.getInternalList(TreatmentAssignment.class);
	}

	public void setTreatmentAssignmentsInternal(final List<TreatmentAssignment> treatmentAssignments) {
		lazyListHelper.setInternalList(TreatmentAssignment.class, treatmentAssignments);
	}

	@Transient
	public List<TreatmentAssignment> getTreatmentAssignments() {
		return lazyListHelper.getLazyList(TreatmentAssignment.class);
	}

	public void setTreatmentAssignments(final List<TreatmentAssignment> treatmentAssignments) {
		setTreatmentAssignmentsInternal(treatmentAssignments);
	}

	// TODO Why rules is still using primarySponsorCode... (check)
	@Transient
	public String getPrimarySponsorCode() {
		Organization sponsorOrg = getPrimaryFundingSponsorOrganization();
		if (sponsorOrg != null) {
			return sponsorOrg.getNciInstituteCode();
		}
		return null;
	}

	// ------------------------------------------------------------------------------------------------------------

	// TODO Below methods are to be removed.....
	/*
	 * public void addStudyDisease(StudyDisease studyDisease) { studyDisease.setStudy(this); studyDiseases.add(studyDisease); }
	 */

	// TODO check how to get rid of this???? (Admin module require this method)
	public void setPrimarySponsorCode(final String sponsorCode) {
		throw new UnsupportedOperationException("'setPrimarySponsorCode', one should not access this method!");
	}

	/*
	 * @OneToMany (mappedBy="study", fetch=FetchType.LAZY) @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN}) public List<StudyDisease>
	 * getStudyDiseases() { return studyDiseases; } public void setStudyDiseases(List<StudyDisease> studyDiseases) { this.studyDiseases =
	 * studyDiseases; }
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "study")
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<StudyTherapy> getStudyTherapies() {
		return studyTherapies;
	}

	public void setStudyTherapies(final List<StudyTherapy> studyTherapies) {
		this.studyTherapies = studyTherapies;
	}

	@Transient
	public void addStudyTherapy(final StudyTherapy studyTherapy) {
		studyTherapies.add(studyTherapy);
	}

	public void removeIdentifier(final Identifier identifier) {
		getIdentifiers().remove(identifier);
	}

	@Transient
	public Boolean getDrugAdministrationTherapyType() {
		return drugAdministrationTherapyType;
	}

	public void setDrugAdministrationTherapyType(final Boolean drugAdministrationTherapyType) {
		this.drugAdministrationTherapyType = drugAdministrationTherapyType;
	}

	@Transient
	public Boolean getRadiationTherapyType() {
		return radiationTherapyType;
	}

	public void setRadiationTherapyType(final Boolean radiationTherapyType) {
		this.radiationTherapyType = radiationTherapyType;
	}

	@Transient
	public Boolean getDeviceTherapyType() {
		return deviceTherapyType;
	}

	public void setDeviceTherapyType(final Boolean deviceTherapyType) {
		this.deviceTherapyType = deviceTherapyType;
	}

	@Transient
	public Boolean getSurgeryTherapyType() {
		return surgeryTherapyType;
	}

	public void setSurgeryTherapyType(final Boolean surgeryTherapyType) {
		this.surgeryTherapyType = surgeryTherapyType;
	}

	@Transient
	public StudyTherapy getStudyTherapy(final StudyTherapyType studyTherapyType) {

		for (StudyTherapy studyTherapy : studyTherapies) {
			if (studyTherapy.getStudyTherapyType().equals(studyTherapyType)) {
				return studyTherapy;
			}

		}
		return null;
	}

}
