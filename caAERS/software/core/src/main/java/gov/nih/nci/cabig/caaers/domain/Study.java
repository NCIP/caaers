/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CollectionUtil;
import gov.nih.nci.cabig.caaers.utils.ProjectedList;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections15.functors.InstantiateFactory;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

 
/**
 * Domain object representing Study(Protocol).
 *
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion Olaru
 * @author Sameer Sawant
 * @author Monish Dombla
 */
@Entity
@Table(name = "studies")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_studies_id")})
@Where(clause = "load_status > 0")
public abstract class Study extends AbstractIdentifiableDomainObject implements Serializable {

	/** The Constant serialVersionUID. */
	protected static final long serialVersionUID = 2524271609924679883L;
	
    /** The Constant STATUS_ACTIVE. */
    public static final String STATUS_ACTIVE = "Active - Trial is open to accrual";

    /** The short title. */
    protected String shortTitle;
    
    /** The long title. */
    @Deprecated protected String longTitle;
    
    /** The description. */
    @Deprecated protected String description;
    
    /** The precis. */
    @Deprecated protected String precis;
    
    /** The phase code. */
    protected String phaseCode;
    
    /** The ae terminology. */
    protected AeTerminology aeTerminology;
    
    /** The disease terminology. */
    protected DiseaseTerminology diseaseTerminology;
    
    /** The status. */
    @Deprecated String status;
    
    /** The other treatment assignment. */
    protected String otherTreatmentAssignment;

    // TODO: Remove
    /** The blinded indicator. */
    protected Boolean blindedIndicator;
    
    /** The multi institution indicator. */
    @Deprecated Boolean multiInstitutionIndicator;


    // TODO: Remove
    /** The randomized indicator. */
    protected Boolean randomizedIndicator;

    // TODO: Remove
    /** The disease code. */
    protected String diseaseCode;

    // TODO: Remove
    /** The monitor code. */
    protected String monitorCode;

    // TODO: Remove
    /** The target accrual number. */
    protected Integer targetAccrualNumber;
    
    /** The other meddra. */
    protected MeddraVersion otherMeddra;
    
    /** The study organizations. */
    protected List<StudyOrganization> studyOrganizations;

    /** The ctep study diseases. */
    protected List<CtepStudyDisease> ctepStudyDiseases = new ArrayList<CtepStudyDisease>();
    
    /** The meddra study diseases. */
    protected List<MeddraStudyDisease> meddraStudyDiseases = new ArrayList<MeddraStudyDisease>();
    
    /** The study conditions. */
    protected List<StudyCondition> studyConditions = new ArrayList<StudyCondition>();

    /** The expected a es. */
    protected List<AbstractExpectedAE> expectedAEs = new ArrayList<AbstractExpectedAE>();
    
    /** The expected ae meddra terms. */
    protected List<ExpectedAEMeddraLowLevelTerm> expectedAEMeddraTerms = new ArrayList<ExpectedAEMeddraLowLevelTerm>();
    
    /** The expected aectc terms. */
    protected List<ExpectedAECtcTerm> expectedAECTCTerms = new ArrayList<ExpectedAECtcTerm>();

    /** The lazy list helper. */
    protected final LazyListHelper lazyListHelper;

    /** The organization assigned identifier. */
    protected OrganizationAssignedIdentifier organizationAssignedIdentifier;
    
    /** The study therapies. */
    protected List<StudyTherapy> studyTherapies = new ArrayList<StudyTherapy>();
    
    /** The report formats. */
    @Deprecated protected List<ReportFormat> reportFormats = new ArrayList<ReportFormat>();
    
    /** The ctc categories. */
    protected List<CtcCategory> ctcCategories = new ArrayList<CtcCategory>();
    
    /** The other interventions. */
    protected List<OtherIntervention> otherInterventions = new ArrayList<OtherIntervention>();
    
    /** The study devices. */
    protected List<StudyDevice> studyDevices = new ArrayList<StudyDevice>();

    // TODO move into Command Object
    // Investigators page)

    /** The drug administration therapy type. */
    protected Boolean drugAdministrationTherapyType = Boolean.FALSE;
    
    /** The radiation therapy type. */
    protected Boolean radiationTherapyType = Boolean.FALSE;
    
    /** The device therapy type. */
    protected Boolean deviceTherapyType = Boolean.FALSE;
    
    /** The surgery therapy type. */
    protected Boolean surgeryTherapyType = Boolean.FALSE;
    
    /** The behavioral therapy type. */
    protected Boolean behavioralTherapyType = Boolean.FALSE;
    
    /** The load status. */
    protected Integer loadStatus = LoadStatus.COMPLETE.getCode();

    // Used to facilitate import of a coordinating center / funding sponsor
    /** The funding sponsor. */
    protected FundingSponsor fundingSponsor;
    
    /** The coordinating center. */
    protected CoordinatingCenter coordinatingCenter;

    // DCP specific properties
    /** The design. */
    @Deprecated protected Design design;

    /** The epochs. */
    protected List<Epoch> epochs=new ArrayList<Epoch>();
    
    /** The data entry status. */
    protected Boolean dataEntryStatus;
    
    /** The verbatim first. */
    protected Boolean verbatimFirst;

    //Added for COPPA integration
    /** The external id. */
    protected String externalId;

    protected String studyPurpose;

    protected Boolean aeTermUnique;
    
    protected Date lastSynchedDate;

    protected String participationType;
    
    /**
     * Instantiates a new study.
     */
    public Study() {

        lazyListHelper = new LazyListHelper();

        // register with lazy list helper study site.
        lazyListHelper.add(StudySite.class, new StudyChildInstantiateFactory<StudySite>(this, StudySite.class));
        lazyListHelper.add(StudyFundingSponsor.class, new StudyChildInstantiateFactory<StudyFundingSponsor>(this, StudyFundingSponsor.class));
        lazyListHelper.add(Identifier.class, new InstantiateFactory<Identifier>(Identifier.class));
        lazyListHelper.add(StudyAgent.class, new StudyChildInstantiateFactory<StudyAgent>(this, StudyAgent.class));
        lazyListHelper.add(StudyDevice.class, new StudyChildInstantiateFactory<StudyDevice>(this, StudyDevice.class));
        lazyListHelper.add(OtherIntervention.class, new StudyChildInstantiateFactory<OtherIntervention>(this, OtherIntervention.class));
        lazyListHelper.add(TreatmentAssignment.class, new StudyChildInstantiateFactory<TreatmentAssignment>(this, TreatmentAssignment.class));

        // mandatory, so that the lazy-projected list is created/managed properly.
        setStudyOrganizations(new ArrayList<StudyOrganization>());
        setAeTermUnique(true);
    }

    public Date getLastSynchedDate() {
		return lastSynchedDate;
	}

	public void setLastSynchedDate(Date lastSynchedDate) {
		this.lastSynchedDate = lastSynchedDate;
	}

	// / LOGIC
    /**
     * Adds the study organization.
     *
     * @param so the so
     */
    public void addStudyOrganization(final StudyOrganization so) {
        getStudyOrganizations().add(so);
        so.setStudy(this);
    }

    /**
     * Adds the treatment assignment.
     *
     * @param treatmentAssignment the treatment assignment
     */
    public void addTreatmentAssignment(final TreatmentAssignment treatmentAssignment) {
        getTreatmentAssignments().add(treatmentAssignment);
        treatmentAssignment.setStudy(this);
    }

    /**
     * Removes the study organization.
     *
     * @param so the so
     */
    public void removeStudyOrganization(final StudyOrganization so) {
        getStudyOrganizations().remove(so);
    }

    /**
     * Gets the study sites.
     *
     * @return the study sites
     */
    @Transient
    public List<StudySite> getStudySites() {
        return lazyListHelper.getLazyList(StudySite.class);
    }

    /**
     * Adds the study site.
     *
     * @param studySite the study site
     */
    public void addStudySite(final StudySite studySite) {
        getStudySites().add(studySite);
        studySite.setStudy(this);
    }

    /**
     * Removes the study site.
     *
     * @param studySite the study site
     */
    public void removeStudySite(final StudySite studySite) {
        getStudySites().remove(studySite);
    }

    /**
     * Adds the study funding sponsor.
     *
     * @param studyFundingSponsor the study funding sponsor
     */
    public void addStudyFundingSponsor(final StudyFundingSponsor studyFundingSponsor) {
        getStudyFundingSponsors().add(studyFundingSponsor);
        studyFundingSponsor.setStudy(this);
    }

    /**
     * Gets the study funding sponsors.
     *
     * @return the study funding sponsors
     */
    @Transient
    public List<StudyFundingSponsor> getStudyFundingSponsors() {
        return lazyListHelper.getLazyList(StudyFundingSponsor.class);
    }

    /**
     * Gets the primary funding sponsor.
     *
     * @return the primary funding sponsor
     */
    @Transient
    public StudyFundingSponsor getPrimaryFundingSponsor() {
        for (StudyFundingSponsor sponsor : getStudyFundingSponsors()) {
            if (sponsor.isPrimary()) {
                return sponsor;
            }
        }
        return null;
    }

    /**
     * Gets the primary funding sponsor organization.
     *
     * @return the primary funding sponsor organization
     */
    @Transient
    public Organization getPrimaryFundingSponsorOrganization() {
        StudyFundingSponsor primarySponsor = getPrimaryFundingSponsor();
        if (primarySponsor == null) {
            return null;
        }
        return primarySponsor.getOrganization();
    }

    /**
     * Sets the primary funding sponsor organization.
     *
     * @param org the new primary funding sponsor organization
     */
    @Transient
    public void setPrimaryFundingSponsorOrganization(final Organization org) {
        // if already a primary funding sponsor exist, replace that sponor's organization
        StudyFundingSponsor xprimarySponsor = getPrimaryFundingSponsor();
        if (xprimarySponsor != null) {
            xprimarySponsor.setOrganization(org);
        } else {
            // no primary funding sponsor yet exist, so create one
            List<StudyFundingSponsor> sponsors = getStudyFundingSponsors();
            int size = sponsors.size();
            StudyFundingSponsor primarySponsor = sponsors.get(size);
            primarySponsor.setOrganization(org);
        }
    }

    /**
     * Will return the primary identifier associated to this study.
     *
     * @return the primary identifier
     */
    @Transient
    public Identifier getPrimaryIdentifier() {
        try {
			for (Identifier id : getIdentifiersLazy()) {
			    if (id.isPrimary()) return id;
			}
		} catch (Exception igonre) {
		}
        return null;
    }
    

    /**
     * Adds the study agent.
     *
     * @param studyAgent the study agent
     */
    public void addStudyAgent(final StudyAgent studyAgent) {
        getStudyAgents().add(studyAgent);
        studyAgent.setStudy(this);
    }

    /**
     * Adds the ctep study disease.
     *
     * @param ctepStudyDisease the ctep study disease
     */
    public void addCtepStudyDisease(final CtepStudyDisease ctepStudyDisease) {
        ctepStudyDisease.setStudy(this);
        ctepStudyDiseases.add(ctepStudyDisease);
    }

    /**
     * Adds the meddra study disease.
     *
     * @param meddraStudyDisease the meddra study disease
     */
    public void addMeddraStudyDisease(final MeddraStudyDisease meddraStudyDisease) {
        meddraStudyDisease.setStudy(this);
        meddraStudyDiseases.add(meddraStudyDisease);
    }
    
    /**
     * Adds the study condition.
     *
     * @param studyCondition the study condition
     */
    public void addStudyCondition(final StudyCondition studyCondition) {
        studyCondition.setStudy(this);
        studyConditions.add(studyCondition);
    }
    
    
    /**
     * Gets the study coordinating centers.
     *
     * @return the study coordinating centers
     */
    @Transient
    public List<StudyCoordinatingCenter> getStudyCoordinatingCenters() {
        return new ProjectedList<StudyCoordinatingCenter>(studyOrganizations, StudyCoordinatingCenter.class);
    }

    /**
     * Gets the study coordinating center.
     *
     * @return the study coordinating center
     */
    @Transient
    public StudyCoordinatingCenter getStudyCoordinatingCenter() {
        return getStudyCoordinatingCenters().isEmpty() ? null : getStudyCoordinatingCenters().get(0);
    }

    /**
     * Gets the organization assigned identifier.
     *
     * @return the organization assigned identifier
     */
    @Transient
    public OrganizationAssignedIdentifier getOrganizationAssignedIdentifier() {

        if (getId() != null) {
            for (Identifier identifier : getIdentifiers()) {

                if (identifier instanceof OrganizationAssignedIdentifier
                        && identifier.getType().equalsIgnoreCase(
                        "Co-ordinating Center Identifier")) {
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
    
    /**
     * Gets the nci assigned identifier.
     *
     * @return the nci assigned identifier
     */
    @Transient
    public OrganizationAssignedIdentifier getNciAssignedIdentifier() {
        for (Identifier identifier : getIdentifiers()) {

            if (identifier instanceof OrganizationAssignedIdentifier
                    && identifier.getType().equalsIgnoreCase(
                    "NCI Assigned Identifier")) {
                organizationAssignedIdentifier = (OrganizationAssignedIdentifier) identifier;
                return organizationAssignedIdentifier;
            }
        }
        return organizationAssignedIdentifier;
    }
    
    /**
     * Gets the funding sponsor identifier.
     *
     * @return the funding sponsor identifier
     */
    @Transient
    public OrganizationAssignedIdentifier getFundingSponsorIdentifier() {
        for (Identifier identifier : getIdentifiers()) {

            if (identifier instanceof OrganizationAssignedIdentifier
                    && identifier.getType().equalsIgnoreCase(
                    		OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE)) {
                organizationAssignedIdentifier = (OrganizationAssignedIdentifier) identifier;
                return organizationAssignedIdentifier;
            }
        }
        return organizationAssignedIdentifier;
    }
    
    /**
     * Gets the coordinating center identifier.
     *
     * @return the coordinating center identifier
     */
    @Transient
    public OrganizationAssignedIdentifier getCoordinatingCenterIdentifier() {
        for (Identifier identifier : getIdentifiers()) {

            if (identifier instanceof OrganizationAssignedIdentifier
                    && identifier.getType().equalsIgnoreCase(
                    		OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE)) {
                organizationAssignedIdentifier = (OrganizationAssignedIdentifier) identifier;
                return organizationAssignedIdentifier;
            }
        }
        return organizationAssignedIdentifier;
    }
    
    /**
     * Gets the funding sponsor identifier value.
     *
     * @return the funding sponsor identifier value
     */
    @Transient
    public String getFundingSponsorIdentifierValue() {
        Identifier id =  getFundingSponsorIdentifier();
        if(id == null) return null;
        return id.getValue();
    }
    
    /**
     * Gets the coordinating center identifier value.
     *
     * @return the coordinating center identifier value
     */
    @Transient
    public String getCoordinatingCenterIdentifierValue() {
        Identifier id =  getCoordinatingCenterIdentifier();
        if(id == null) return null;
        return id.getValue();
    }
    
    /**
     * Gets the coordinating center identifier.
     *
     * @return the coordinating center identifier
     */
    @Transient
    public SystemAssignedIdentifier getCtepEsysIdentifier() {
        for (Identifier identifier : getIdentifiers()) {

            if (identifier instanceof SystemAssignedIdentifier
                    && ((SystemAssignedIdentifier) identifier).getSystemName().equalsIgnoreCase(
                    		SystemAssignedIdentifier.CTEP_ESYS_NAME)) {
                return (SystemAssignedIdentifier) identifier;
            }
        }
        return null;
    }
    
    /**
     * Gets the funding sponsor identifier value.
     *
     * @return the funding sponsor identifier value
     */
    @Transient
    public String getCtepEsysIdentifierValue() {
        Identifier id =  getCtepEsysIdentifier();
        if(id == null) return null;
        return id.getValue();
    }

    public boolean hasCtepEsysIdentifier(){
        return getCtepEsysIdentifier() != null;
    }
    
    /**
     * Gets the identifier containing.
     *
     * @param text the text
     * @return the identifier containing
     */
    @Transient
    public String getIdentifierContaining(String text){
    	for (Identifier identifier : getIdentifiers()) {
    		if(StringUtils.containsIgnoreCase(identifier.getValue(), text)){
    			return identifier.getValue();
    		}
    	}
    	return getPrimaryIdentifierValue();
    }
    

    /**
     * Sets the organization assigned identifier.
     *
     * @param organizationAssignedIdentifier the new organization assigned identifier
     */
    @Transient
    public void setOrganizationAssignedIdentifier(final OrganizationAssignedIdentifier organizationAssignedIdentifier) {
        this.organizationAssignedIdentifier = organizationAssignedIdentifier;
    }

    /**
     * Gets the study agents.
     *
     * @return the study agents
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicates found in Study Agents list")
    public List<StudyAgent> getStudyAgents() {
        return lazyListHelper.getLazyList(StudyAgent.class);
    }

    /**
     * Sets the study agents.
     *
     * @param studyAgents the new study agents
     */
    @Transient
    public void setStudyAgents(final List<StudyAgent> studyAgents) {
        setStudyAgentsInternal(studyAgents);
    }

    /**
     * Gets the study devices.
     *
     * @return the study devices
     */
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyDevice> getStudyDevicesInternal() {
        return lazyListHelper.getInternalList(StudyDevice.class);
    }

    @Transient
    public List<StudyDevice> getStudyDevices(){
        return lazyListHelper.getLazyList(StudyDevice.class);
    }

    /**
     * Sets the study devices.
     *
     * @param studyDevices the new study devices
     */
    public void setStudyDevicesInternal(final List<StudyDevice> studyDevices) {
        lazyListHelper.setInternalList(StudyDevice.class, studyDevices);
    }
    
    public void setStudyDevices(final List<StudyDevice> studyDeviceList){
        setStudyDevicesInternal(studyDeviceList);
    }

    /**
     * Adds the study device.
     *
     * @param sd the sd
     */
    public void addStudyDevice(StudyDevice sd) {
        this.getStudyDevices().add(sd);
        sd.setStudy(this);
    }

    /**
     * Gets the other interventions.
     *
     * @return the other interventions
     */
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<OtherIntervention> getOtherInterventionsInternal() {
        return lazyListHelper.getInternalList(OtherIntervention.class);
    }

    @Transient
    public List<OtherIntervention> getOtherInterventions() {
        return lazyListHelper.getLazyList(OtherIntervention.class);
    }

    /**
     * Adds the other intervention.
     *
     * @param oi the oi
     */
    public void addOtherIntervention(OtherIntervention oi) {
        this.getOtherInterventions().add(oi);
        oi.setStudy(this);
    }

    /**
     * Sets the other interventions.
     *
     * @param otherInterventions the new other interventions
     */
    public void setOtherInterventionsInternal(final List<OtherIntervention> otherInterventions) {
        lazyListHelper.setInternalList(OtherIntervention.class, otherInterventions);
    }

    public void setOtherInterventions(final List<OtherIntervention> otherInterventions) {
        setOtherInterventionsInternal(otherInterventions);
    }



    /**
     * Will return the {@link StudyAgent}s that are not retired.
     *
     * @return the active study agents
     */
    @Transient
    public List<StudyAgent> getActiveStudyAgents(){
    	List<StudyAgent> agents = new ArrayList<StudyAgent>();
    	for(StudyAgent sa : getStudyAgents()){
    		if(!sa.isRetired()) agents.add(sa);
    	}
    	return agents;
    }
    
    /**
     * Will return the {@link StudyDevice}s that are not retired.
     *
     * @return the active study devices
     */
    @Transient
    public List<StudyDevice> getActiveStudyDevices() {
    	List<StudyDevice> devices = new ArrayList<StudyDevice>();
    	for(StudyDevice sd : getStudyDevices()){
    		if(!sd.isRetired()) devices.add(sd);
    	}
    	return devices;
    }

    /**
     * Gets the active other interventions.
     *
     * @return the active other interventions
     */
    @Transient
    public List<OtherIntervention> getActiveOtherInterventions() {
    	List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
    	for(OtherIntervention oi : getOtherInterventions()){
    		if(!oi.isRetired()) ois.add(oi);
    	}
    	return ois;
    }

    /**
     * Will return the {@link StudyDevice}s that are not retired.
     * @return the active study radiations
     */
   @Transient
   public List<OtherIntervention> getActiveStudyRadiations() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.RADIATION)) ois.add(oi);
       }
       return ois;
   }

   @Transient
   public List<OtherIntervention> getActiveStudyBehavioralInterventions() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.BEHAVIORAL)) ois.add(oi);
       }
       return ois;
   }

   @Transient
   public List<OtherIntervention> getActiveStudyBiologicalInterventions() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.BIOLOGICAL_VACCINE)) ois.add(oi);
       }
       return ois;
   }

   @Transient
   public List<OtherIntervention> getActiveStudyGeneticInterventions() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.GENETIC)) ois.add(oi);
       }
       return ois;
   }

   @Transient
   public List<OtherIntervention> getActiveStudyDietaryInterventions() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.DIETARY_SUPPLEMENT)) ois.add(oi);
       }
       return ois;
   }

   @Transient
   public List<OtherIntervention> getActiveStudyOtherInterventions() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.OTHER)) ois.add(oi);
       }
       return ois;
   }

    public StudyAgent findStudyAgentByNscOrName(String nsc){
        for(StudyAgent studyAgent : getStudyAgents()){
            if(studyAgent.isRetired()) continue;
            if(studyAgent.getAgent() != null && ObjectUtils.equals(studyAgent.getAgent().getNscNumber(), nsc)) return studyAgent;
            if(ObjectUtils.equals(studyAgent.getOtherAgent(),  nsc )) return studyAgent;
        }
        return null;
    }

    public StudyAgent findStudyAgentById(Integer id){
        for(StudyAgent studyAgent : getStudyAgents()){
            if(ObjectUtils.equals(id, studyAgent.getId())) return studyAgent;
        }
        return null;
    }

    public StudyDevice findStudyDeviceById(Integer id){
        for(StudyDevice studyDevice : getStudyDevices()){
            if(ObjectUtils.equals(id, studyDevice.getId())) return studyDevice;
        }
        return null;
    }

    public OtherIntervention findOtherInterventionById(Integer id){
        for(OtherIntervention otherIntervention : getOtherInterventions()){
            if(ObjectUtils.equals(id, otherIntervention.getId())) return otherIntervention;
        }
        return null;
    }


    public StudySite findActiveStudySiteByNciInstituteCode(String nciCode){
        for(StudySite ss : getActiveStudySites()) {
            if(StringUtils.equals(ss.getOrganization().getNciInstituteCode(), nciCode)) return ss;
        }
        return null;
    }

    public TreatmentAssignment findActiveTreatmentAssignment(String tac){
        for(TreatmentAssignment ta : getActiveTreatmentAssignments()){
            if(StringUtils.equals(ta.getCode(), tac)) return ta;
        }
        return null;
    }

    public Epoch findActiveEpoch(String epochName){
        for(Epoch e : getActiveEpochs()){
           if(StringUtils.equals(epochName, e.getName())) return e;
        }
        return null;
    }

    /**
     * Will return the {@link StudyDevice}s that are not retired.
     *
     * @return the active study surgeries
     */
   @Transient
   public List<OtherIntervention> getActiveStudySurgeries() {
       List<OtherIntervention> ois = new ArrayList<OtherIntervention>();
       for(OtherIntervention oi : getOtherInterventions()){
           if(!oi.isRetired() &&  oi.getStudyTherapyType().equals(StudyTherapyType.SURGERY)) ois.add(oi);
       }
       return ois;
   }
    
    /**
     * Will return the {@link StudySite}s that are not retired.
     *
     * @return the active study sites
     */
    @Transient
    public List<StudySite> getActiveStudySites(){
    	List<StudySite> sites = new ArrayList<StudySite>();
    	for(StudySite site : getStudySites()){
    		if(!site.isRetired()) sites.add(site);
    	}
    	return sites;
    }
    
    /**
     * Will return the {@link StudyOrganization}s that are not retired.
     *
     * @return the active study organizations
     */
    @Transient
    public List<StudyOrganization> getActiveStudyOrganizations(){
    	List<StudyOrganization> studyOrgs = new ArrayList<StudyOrganization>();
    	for(StudyOrganization studyOrg : getStudyOrganizations()){
    		if(!studyOrg.isRetired()) studyOrgs.add(studyOrg);
    	}
    	return studyOrgs;
    }
    
    /**
     * Will return the {@link TreatmentAssignment}s that are not retired.
     *
     * @return the active treatment assignments
     */
    @Transient
    public List<TreatmentAssignment> getActiveTreatmentAssignments(){
    	List<TreatmentAssignment> tacs = new ArrayList<TreatmentAssignment>();
    	for(TreatmentAssignment tac : getTreatmentAssignments()){
    		if(!tac.isRetired()) tacs.add(tac);
    	}
    	return tacs;
    }
    
    /**
     * Will return the {@link AbstractStudyDisease}s that are not retired.
     *
     * @return the active study diseases
     */
    @Transient
    public List<? extends AbstractStudyDisease<? extends DomainObject>> getActiveStudyDiseases(){

        if (diseaseTerminology != null && diseaseTerminology.getDiseaseCodeTerm() != null && diseaseTerminology.getDiseaseCodeTerm().equals(DiseaseCodeTerm.CTEP)) {
            List<CtepStudyDisease> diseases = new ArrayList<CtepStudyDisease>();
            if (CollectionUtils.isNotEmpty(ctepStudyDiseases)) {
                for (CtepStudyDisease disease : ctepStudyDiseases) {
                    if (!disease.isRetired()) diseases.add(disease);
                }
            }
            return diseases;
        }

        if (diseaseTerminology != null && diseaseTerminology.getDiseaseCodeTerm() != null && diseaseTerminology.getDiseaseCodeTerm().equals(DiseaseCodeTerm.MEDDRA)) {
            List<MeddraStudyDisease> diseases = new ArrayList<MeddraStudyDisease>();
            if (CollectionUtils.isNotEmpty(meddraStudyDiseases)) {
                for (MeddraStudyDisease disease : meddraStudyDiseases) {
                    if (!disease.isRetired()) diseases.add(disease);
                }
            }
            return diseases;
        }

        if (diseaseTerminology != null && diseaseTerminology.getDiseaseCodeTerm() != null && diseaseTerminology.getDiseaseCodeTerm().equals(DiseaseCodeTerm.OTHER)) {
            List<StudyCondition> diseases = new ArrayList<StudyCondition>();
            if (CollectionUtils.isNotEmpty(studyConditions)) {
                for (StudyCondition disease : studyConditions) {
                    if (!disease.isRetired()) diseases.add(disease);
                }
            }
            return diseases;
        }

        return null;

    }

    /**
     * Checks for therapy of type.
     *
     * @param therapyType the therapy type
     * @return true, if successful
     */
    public boolean hasTherapyOfType(StudyTherapyType therapyType) {
        switch (therapyType.getCode().intValue()) {
            case 1: return getActiveStudyAgents().size() > 0;  
            case 4: return getActiveStudyDevices().size() > 0;
            default:
                for (OtherIntervention oi : getActiveOtherInterventions()) {
                    if (oi.getStudyTherapyType().equals(therapyType)) return true;
                }
        }
        return false;
    }
    
    /**
     * Will remove all the {@link StudyTherapy} of the specific {@link StudyTherapyType}.
     *
     * @param therapyType the therapy type
     */
    @Deprecated
    public void removeTherapiesOfType(StudyTherapyType therapyType){
    	ArrayList<StudyTherapy> therapies = new ArrayList<StudyTherapy>(getStudyTherapies());
    	for(StudyTherapy therapy : therapies){
    		if(therapy.getStudyTherapyType() == therapyType) getStudyTherapies().remove(therapy);
    	}
    }

    /**
     * Checks if is surgery present.
     *
     * @return true, if is surgery present
     */
    @Transient
    public boolean isSurgeryPresent() {
        return this.hasTherapyOfType(StudyTherapyType.SURGERY);
    }

    /**
     * Checks if is device present.
     *
     * @return true, if is device present
     */
    @Transient
    public boolean isDevicePresent() {
        return this.hasTherapyOfType(StudyTherapyType.DEVICE);
    }

    /**
     * Checks if is radiation present.
     *
     * @return true, if is radiation present
     */
    @Transient
    public boolean isRadiationPresent() {
        return this.hasTherapyOfType(StudyTherapyType.RADIATION);
    }

    /**
     * Checks if is behavioral present.
     *
     * @return true, if is behavioral present
     */
    @Transient
    public boolean isBehavioralInterventionPresent() {
        return this.hasTherapyOfType(StudyTherapyType.BEHAVIORAL);
    }

    @Transient
    public boolean isBiologicalInterventionPresent() {
        return this.hasTherapyOfType(StudyTherapyType.BIOLOGICAL_VACCINE);
    }

    @Transient
    public boolean isGeneticInterventionPresent() {
        return this.hasTherapyOfType(StudyTherapyType.GENETIC);
    }

    @Transient
    public boolean isDietaryInterventionPresent() {
        return this.hasTherapyOfType(StudyTherapyType.DIETARY_SUPPLEMENT);
    }

    @Transient
    public boolean isOtherInterventionPresent() {
        return this.hasTherapyOfType(StudyTherapyType.OTHER);
    }

    /**
     * Checks if is drug administration present.
     *
     * @return true, if is drug administration present
     */
    @Transient
    public boolean isDrugAdministrationPresent() {
        return this.hasTherapyOfType(StudyTherapyType.DRUG_ADMINISTRATION);
    }

    // / BEAN PROPERTIES

    // TODO: this stuff should really, really not be in here. It's web-view/entry specific.

    /**
     * Gets the ctc version.
     *
     * @return the ctc version
     */
    @Deprecated
    @Transient
    public Ctc getCtcVersion() {
        return getAeTerminology().getCtcVersion();
    }

    /**
     * Sets the ctc version.
     *
     * @param ctcVersion the new ctc version
     */
    @Deprecated
    @Transient
    public void setCtcVersion(final Ctc ctcVersion) {
        AeTerminology t = getAeTerminology();
        t.setTerm(Term.CTC);
        t.setCtcVersion(ctcVersion);
    }

    /**
     * Gets the disease terminology.
     *
     * @return the disease terminology
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "study", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public DiseaseTerminology getDiseaseTerminology() {
        if (diseaseTerminology == null) {
            diseaseTerminology = new DiseaseTerminology();
            diseaseTerminology.setStudy(this);
        }
        return diseaseTerminology;
    }

    /**
     * Sets the disease terminology.
     *
     * @param diseaseTerminology the new disease terminology
     */
    public void setDiseaseTerminology(final DiseaseTerminology diseaseTerminology) {
        this.diseaseTerminology = diseaseTerminology;
    }

    /**
     * Gets the ae terminology.
     *
     * @return the ae terminology
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "study", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public AeTerminology getAeTerminology() {
        if (aeTerminology == null) {
            aeTerminology = new AeTerminology();
            aeTerminology.setStudy(this);
        }
        return aeTerminology;
    }

    /**
     * Sets the ae terminology.
     *
     * @param aeTerminology the new ae terminology
     */
    public void setAeTerminology(final AeTerminology aeTerminology) {
        this.aeTerminology = aeTerminology;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#getIdentifiers()
     */
    @Override
    @OneToMany(orphanRemoval = true)
    @Cascade({CascadeType.ALL})
    @JoinColumn(name = "STU_ID")
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<Identifier> getIdentifiers() {
        return lazyListHelper.getInternalList(Identifier.class);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.AbstractIdentifiableDomainObject#setIdentifiers(java.util.List)
     */
    @Override
    public void setIdentifiers(final List<Identifier> identifiers) {
        lazyListHelper.setInternalList(Identifier.class, identifiers);
    }

    /**
     * Gets the identifiers lazy.
     *
     * @return the identifiers lazy
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicates found in Identifiers list")
    public List<Identifier> getIdentifiersLazy() {
        return lazyListHelper.getLazyList(Identifier.class);
    }

    /**
     * Sets the identifiers lazy.
     *
     * @param identifiers the new identifiers lazy
     */
    @Transient
    public void setIdentifiersLazy(final List<Identifier> identifiers) {
        setIdentifiers(identifiers);
    }

    /**
     * Gets the study agents internal.
     *
     * @return the study agents internal
     */
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyAgent> getStudyAgentsInternal() {
        return lazyListHelper.getInternalList(StudyAgent.class);
    }

    /**
     * Sets the study agents internal.
     *
     * @param studyAgents the new study agents internal
     */
    public void setStudyAgentsInternal(final List<StudyAgent> studyAgents) {
        lazyListHelper.setInternalList(StudyAgent.class, studyAgents);
    }

    /**
     * Gets the ctep study diseases.
     *
     * @return the ctep study diseases
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "term_type = 'ctep'")
    @OrderBy
    @UniqueObjectInCollection(message = "Duplicates found in CtepStudyDiseases list")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<CtepStudyDisease> getCtepStudyDiseases() {
        return ctepStudyDiseases;
    }

    /**
     * Gets the active ctep study diseases.
     *
     * @return the active ctep study diseases
     */
    @Transient
    public List<CtepStudyDisease> getActiveCtepStudyDiseases() {
        return CollectionUtil.getActiveObjects(getCtepStudyDiseases());
    }

    /**
     * Sets the ctep study diseases.
     *
     * @param ctepStudyDiseases the new ctep study diseases
     */
    public void setCtepStudyDiseases(final List<CtepStudyDisease> ctepStudyDiseases) {
        this.ctepStudyDiseases = ctepStudyDiseases;
    }

    /**
     * Gets the meddra study diseases.
     *
     * @return the meddra study diseases
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "term_type = 'meddra'")
    @OrderBy
    @UniqueObjectInCollection(message = "Duplicates found in MeddraStudyDiseases list")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<MeddraStudyDisease> getMeddraStudyDiseases() {
        return meddraStudyDiseases;
    }

    /**
     * Gets the active meddra study diseases.
     *
     * @return the active meddra study diseases
     */
    @Transient
    public List<MeddraStudyDisease> getActiveMeddraStudyDiseases() {
        return CollectionUtil.getActiveObjects(getMeddraStudyDiseases());
    }

    /**
     * Sets the meddra study diseases.
     *
     * @param meddraStudyDiseases the new meddra study diseases
     */
    public void setMeddraStudyDiseases(final List<MeddraStudyDisease> meddraStudyDiseases) {
        this.meddraStudyDiseases = meddraStudyDiseases;
    }

    /**
     * Gets the blinded indicator.
     *
     * @return the blinded indicator
     */
    public Boolean getBlindedIndicator() {
        return blindedIndicator;
    }

    /**
     * Sets the blinded indicator.
     *
     * @param blindedIndicator the new blinded indicator
     */
    public void setBlindedIndicator(final Boolean blindedIndicator) {
        this.blindedIndicator = blindedIndicator;
    }

    /**
     * Gets the multi institution indicator.
     *
     * @return the multi institution indicator
     */
    @Deprecated
    public Boolean getMultiInstitutionIndicator() {
        return multiInstitutionIndicator;
    }

    /**
     * Sets the multi institution indicator.
     *
     * @param multiInstitutionIndicator the new multi institution indicator
     */
    @Deprecated
    public void setMultiInstitutionIndicator(final Boolean multiInstitutionIndicator) {
        this.multiInstitutionIndicator = multiInstitutionIndicator;
    }

    /**
     * Gets the randomized indicator.
     *
     * @return the randomized indicator
     */
    public Boolean getRandomizedIndicator() {
        return randomizedIndicator;
    }

    /**
     * Sets the randomized indicator.
     *
     * @param randomizedIndicator the new randomized indicator
     */
    public void setRandomizedIndicator(final Boolean randomizedIndicator) {
        this.randomizedIndicator = randomizedIndicator;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    @Transient @Deprecated
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param descriptionText the new description
     */
    @Deprecated
    public void setDescription(final String descriptionText) {
        description = descriptionText;
    }

    /**
     * Gets the disease code.
     *
     * @return the disease code
     */
    public String getDiseaseCode() {
        return diseaseCode;
    }

    /**
     * Sets the disease code.
     *
     * @param diseaseCode the new disease code
     */
    public void setDiseaseCode(final String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    /**
     * Gets the long title.
     *
     * @return the long title
     */
    @Transient @Deprecated
    public String getLongTitle() {
        return longTitle;
    }

    /**
     * Sets the long title.
     *
     * @param longTitleText the new long title
     */
    @Deprecated
    public void setLongTitle(final String longTitleText) {
        longTitle = longTitleText;
    }

    /**
     * Gets the monitor code.
     *
     * @return the monitor code
     */
    public String getMonitorCode() {
        return monitorCode;
    }

    /**
     * Sets the monitor code.
     *
     * @param monitorCode the new monitor code
     */
    public void setMonitorCode(final String monitorCode) {
        this.monitorCode = monitorCode;
    }

    /**
     * Gets the phase code.
     *
     * @return the phase code
     */
    @Transient
    public String getPhaseCode() {
        return phaseCode;
    }

    /**
     * Sets the phase code.
     *
     * @param phaseCode the new phase code
     */
    public void setPhaseCode(final String phaseCode) {
        this.phaseCode = phaseCode;
    }

    /**
     * Gets the precis.
     *
     * @return the precis
     */
    @Deprecated
    public String getPrecis() {
        return precis;
    }

    /**
     * Sets the precis.
     *
     * @param precisText the new precis
     */
    @Deprecated
    public void setPrecis(final String precisText) {
        precis = precisText;
    }

    /**
     * Gets the short title.
     *
     * @return the short title
     */
    @Transient
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * Sets the short title.
     *
     * @param shortTitleText the new short title
     */
    public void setShortTitle(final String shortTitleText) {
        shortTitle = shortTitleText;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    @Transient @Deprecated
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    @Deprecated
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Gets the target accrual number.
     *
     * @return the target accrual number
     */
    public Integer getTargetAccrualNumber() {
        return targetAccrualNumber;
    }

    /**
     * Sets the target accrual number.
     *
     * @param targetAccrualNumber the new target accrual number
     */
    public void setTargetAccrualNumber(final Integer targetAccrualNumber) {
        this.targetAccrualNumber = targetAccrualNumber;
    }
    
    /**
     * Gets the external id.
     *
     * @return the external id
     */
    @Transient
    public String getExternalId() {
		return externalId;
	}
    
	/**
	 * Sets the external id.
	 *
	 * @param externalId the new external id
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

    /**
     * Gets the study organizations.
     *
     * @return the study organizations
     */
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @UniqueObjectInCollection(message = "Duplicates found in StudyOrganizations list")
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyOrganization> getStudyOrganizations() {
        return studyOrganizations;
    }

    /**
     * Sets the study organizations.
     *
     * @param studyOrganizations the new study organizations
     */
    public void setStudyOrganizations(final List<StudyOrganization> studyOrganizations) {
        this.studyOrganizations = studyOrganizations;
        // initialize projected list for StudySite, StudyFundingSponsor and StudyCoordinatingCenter
        lazyListHelper.setInternalList(StudySite.class, new ProjectedList<StudySite>(
                this.studyOrganizations, StudySite.class));
        lazyListHelper.setInternalList(StudyFundingSponsor.class,
                new ProjectedList<StudyFundingSponsor>(this.studyOrganizations,
                        StudyFundingSponsor.class));
    }

    /**
     * Gets the treatment assignments internal.
     *
     * @return the treatment assignments internal
     */
    @OneToMany(mappedBy = "study", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<TreatmentAssignment> getTreatmentAssignmentsInternal() {
        return lazyListHelper.getInternalList(TreatmentAssignment.class);
    }

    /**
     * Sets the treatment assignments internal.
     *
     * @param treatmentAssignments the new treatment assignments internal
     */
    public void setTreatmentAssignmentsInternal(final List<TreatmentAssignment> treatmentAssignments) {
        lazyListHelper.setInternalList(TreatmentAssignment.class, treatmentAssignments);
    }

    /**
     * Gets the treatment assignments.
     *
     * @return the treatment assignments
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate Treatment Assignments found. A Study cannot have two Treatment Assignments with same Code")
    public List<TreatmentAssignment> getTreatmentAssignments() {
        return lazyListHelper.getLazyList(TreatmentAssignment.class);
    }

    /**
     * Sets the treatment assignments.
     *
     * @param treatmentAssignments the new treatment assignments
     */
    public void setTreatmentAssignments(final List<TreatmentAssignment> treatmentAssignments) {
        setTreatmentAssignmentsInternal(treatmentAssignments);
    }

    // TODO Why rules is still using primarySponsorCode... (check)
    /**
     * Gets the primary sponsor code.
     *
     * @return the primary sponsor code
     */
    @Transient
    public String getPrimarySponsorCode() {
        Organization sponsorOrg = getPrimaryFundingSponsorOrganization();
        if (sponsorOrg != null) {
            return sponsorOrg.getNciInstituteCode();
        }
        return null;
    }

    /**
     * Gets the load status.
     *
     * @return the load status
     */
    public Integer getLoadStatus() {
        return loadStatus;
    }

    /**
     * Sets the load status.
     *
     * @param loadStatus the new load status
     */
    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    // ------------------------------------------------------------------------------------------------------------

    // TODO Below methods are to be removed.....

    // TODO check how to get rid of this???? (Admin module require this method)

    /**
     * Sets the primary sponsor code.
     *
     * @param sponsorCode the new primary sponsor code
     */
    public void setPrimarySponsorCode(final String sponsorCode) {
        throw new UnsupportedOperationException(
                "'setPrimarySponsorCode', one should not access this method!");
    }

    // ToDo - this should be removed
    /**
     * Gets the study therapies.
     *
     * @return the study therapies
     */
    @Transient
    @Deprecated
    public List<StudyTherapy> getStudyTherapies() {

        List<StudyTherapy> therapies = new ArrayList<StudyTherapy>();
        
        for(StudyTherapyType t : StudyTherapyType.values())    {
            if(hasTherapyOfType(t)) therapies.add(new StudyTherapy(this, t));
        }
        return therapies;
    }


    /**
     * Adds the study therapy.
     *
     * @param studyTherapy the study therapy
     */
    @Transient
    @Deprecated
    public void addStudyTherapy(final StudyTherapy studyTherapy) {
        studyTherapies.add(studyTherapy);
    }

    /**
     * Removes the identifier.
     *
     * @param identifier the identifier
     */
    public void removeIdentifier(final Identifier identifier) {
        getIdentifiers().remove(identifier);
    }

    /**
     * Gets the study therapy.
     *
     * @param studyTherapyType the study therapy type
     * @return the study therapy
     */
    @Transient
    @Deprecated
    public StudyTherapy getStudyTherapy(final StudyTherapyType studyTherapyType) {

        for (StudyTherapy studyTherapy : studyTherapies) {
            if (studyTherapy.getStudyTherapyType().equals(studyTherapyType)) {
                return studyTherapy;
            }

        }
        return null;
    }

    /**
     * Gets the system assigned identifiers.
     *
     * @return the system assigned identifiers
     */
    @Transient
    public List<SystemAssignedIdentifier> getSystemAssignedIdentifiers() {
        return new ProjectedList<SystemAssignedIdentifier>(getIdentifiersLazy(),
                SystemAssignedIdentifier.class);
    }

    /**
     * Gets the organization assigned identifiers.
     *
     * @return the organization assigned identifiers
     */
    @Transient
    public List<OrganizationAssignedIdentifier> getOrganizationAssignedIdentifiers() {
        return new ProjectedList<OrganizationAssignedIdentifier>(getIdentifiersLazy(),
                OrganizationAssignedIdentifier.class);
    }

    /**
     * Gets the coordinating center.
     *
     * @return the coordinating center
     */
    @Transient
    public CoordinatingCenter getCoordinatingCenter() {
        return coordinatingCenter;
    }

    /**
     * Gets the funding sponsor.
     *
     * @return the funding sponsor
     */
    @Transient
    public FundingSponsor getFundingSponsor() {
        return fundingSponsor;
    }

    /**
     * Sets the coordinating center.
     *
     * @param coordinatingCenter the new coordinating center
     */
    public void setCoordinatingCenter(CoordinatingCenter coordinatingCenter) {
        this.coordinatingCenter = coordinatingCenter;
    }

    /**
     * Sets the funding sponsor.
     *
     * @param fundingSponsor the new funding sponsor
     */
    public void setFundingSponsor(FundingSponsor fundingSponsor) {
        this.fundingSponsor = fundingSponsor;
    }



    /**
     * Gets the design.
     *
     * @return the design
     */
    @Column(name = "design_code")
    @Type(type = "designCode")
    @Deprecated
    public Design getDesign() {
        return design;
    }

    /**
     * Sets the design.
     *
     * @param design the new design
     */
    @Deprecated
    public void setDesign(Design design) {
        this.design = design;
    }

    /**
     * Gets the report formats.
     *
     * @return the report formats
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Deprecated
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportFormat> getReportFormats() {
        return reportFormats;
    }

    /**
     * Sets the report formats.
     *
     * @param reportFormats the new report formats
     */
    @Deprecated
    public void setReportFormats(final List<ReportFormat> reportFormats) {
        this.reportFormats = reportFormats;
    }

    /**
     * Adds the report format.
     *
     * @param reportFormat the report format
     */
    @Transient
    @Deprecated
    public void addReportFormat(final ReportFormat reportFormat) {
    	reportFormat.setStudy(this);
        reportFormats.add(reportFormat);
    }

    /**
     * Adds the report format type.
     *
     * @param reportFormatType the report format type
     */
    @Transient
    @Deprecated
    public void addReportFormatType(ReportFormatType reportFormatType) {
        ReportFormat rf = new ReportFormat();
        rf.setReportFormatType(reportFormatType);
        rf.setStudy(this);
        reportFormats.add(rf);
    }

    /**
     * Gets the report format.
     *
     * @param reportFormatType the report format type
     * @return the report format
     */
    @Transient
    @Deprecated
    public ReportFormat getReportFormat(final ReportFormatType reportFormatType) {
        for (ReportFormat reportFormat : reportFormats) {
            if (reportFormat.getReportFormatType().equals(reportFormatType)) {
                return reportFormat;
            }

        }
        return null;
    }

    /**
     * Update report formats.
     *
     * @param selected the selected
     * @param type the type
     */
    @Deprecated
    public void updateReportFormats(Boolean selected, ReportFormatType type) {
        if (selected == null) return;
        ReportFormat reportFormat = getReportFormat(type);
        if (selected && reportFormat == null) {
            ReportFormat adeersPDFReportFormat = new ReportFormat();
            adeersPDFReportFormat.setStudy(this);
            adeersPDFReportFormat.setReportFormatType(type);
            getReportFormats().add(adeersPDFReportFormat);
        } else if (!selected && reportFormat != null) {
            getReportFormats().remove(reportFormat);
        }
    }

    /**
     * Gets the adeers pdf type.
     *
     * @return the adeers pdf type
     */
    @Transient
    public Boolean getAdeersPDFType() {
        return getReportFormat(ReportFormatType.ADEERSPDF) != null;
    }

    /**
     * Sets the adeers pdf type.
     *
     * @param value the new adeers pdf type
     */
    @Deprecated
    public void setAdeersPDFType(final Boolean value) {
        updateReportFormats(value, ReportFormatType.ADEERSPDF);
    }

    /**
     * Gets the caaers xml type.
     *
     * @return the caaers xml type
     */
    @Transient @Deprecated
    public Boolean getCaaersXMLType() {
        return getReportFormat(ReportFormatType.CAAERSXML) != null;
    }

    /**
     * Sets the caaers xml type.
     *
     * @param value the new caaers xml type
     */
    @Deprecated
    public void setCaaersXMLType(final Boolean value) {
    	updateReportFormats(value, ReportFormatType.CAAERSXML);
    }

    /**
     * Gets the cioms pdf type.
     *
     * @return the cioms pdf type
     */
    @Transient @Deprecated
    public Boolean getCiomsPDFType() {
        return getReportFormat(ReportFormatType.CIOMSFORM) != null;
    }

    /**
     * Sets the cioms pdf type.
     *
     * @param value the new cioms pdf type
     */
    @Deprecated
    public void setCiomsPDFType(final Boolean value) {
        updateReportFormats(value, ReportFormatType.CIOMSFORM);
    }

    /**
     * Gets the cioms sae pdf type.
     *
     * @return the cioms sae pdf type
     */
    @Transient @Deprecated
    public Boolean getCiomsSaePDFType() {
        return getReportFormat(ReportFormatType.CIOMSSAEFORM) != null;
    }

    /**
     * Sets the cioms sae pdf type.
     *
     * @param value the new cioms sae pdf type
     */
    @Deprecated
    public void setCiomsSaePDFType(final Boolean value) {
        updateReportFormats(value, ReportFormatType.CIOMSSAEFORM);
    }

    /**
     * Gets the dcp saepdf type.
     *
     * @return the dcp saepdf type
     */
    @Transient @Deprecated
    public Boolean getDcpSAEPDFType() {
        return getReportFormat(ReportFormatType.DCPSAEFORM) != null;
    }

    /**
     * Sets the dcp saepdf type.
     *
     * @param dcpSAEPDFType the new dcp saepdf type
     */
    @Deprecated
    public void setDcpSAEPDFType(final Boolean dcpSAEPDFType) {
        updateReportFormats(dcpSAEPDFType, ReportFormatType.DCPSAEFORM);
    }

    /**
     * Gets the medwatch pdf type.
     *
     * @return the medwatch pdf type
     */
    @Transient @Deprecated
    public Boolean getMedwatchPDFType() {
        return getReportFormat(ReportFormatType.MEDWATCHPDF) != null;
    }

    /**
     * Sets the medwatch pdf type.
     *
     * @param medwatchPDFType the new medwatch pdf type
     */
    @Deprecated
    public void setMedwatchPDFType(final Boolean medwatchPDFType) {
        updateReportFormats(medwatchPDFType, ReportFormatType.MEDWATCHPDF);
    }

    /**
     * Adds the study therapy.
     *
     * @param studyTherapyType the study therapy type
     */
    @Transient
    public void addStudyTherapy(final StudyTherapyType studyTherapyType) {
        StudyTherapy studyTherapy = new StudyTherapy();
        studyTherapy.setStudy(this);
        studyTherapy.setStudyTherapyType(studyTherapyType);
        this.addStudyTherapy(studyTherapy);
    }

    /**
     * This method will find the email address of people associated with the role.
     *
     * @param roleName the role name
     * @return the list
     */
    public List<String> findEmailAddressByRole(String roleName){
        List<String> emails = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(getActiveStudyOrganizations())){
        	for(StudyOrganization studyOrg : studyOrganizations){
            	emails.addAll(studyOrg.findEmailAddressByRole(roleName));
            }
        }
        return emails;
    }

    public String getParticipationType() {
        return participationType;
    }

    public void setParticipationType(String participationType) {
        this.participationType = participationType;
    }

    /**
     * Gets the epochs.
     *
     * @return the epochs
     */
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @JoinColumn(name="study_id", nullable = false)
    @OrderBy("epochOrder")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<Epoch> getEpochs() {
		return epochs;
	}
    
	/**
	 * Sets the epochs.
	 *
	 * @param epochs the new epochs
	 */
	public void setEpochs(List<Epoch> epochs) {
		this.epochs = epochs;
	}
	
	/**
	 * Adds the epoch.
	 *
	 * @param epoch the epoch
	 * @return true, if successful
	 */
	public boolean addEpoch(Epoch epoch){
		  return epochs.add(epoch);
	}	
		
	/**
	 * Removes the epoch.
	 *
	 * @param epoch the epoch
	 * @return true, if successful
	 */
	public boolean removeEpoch(Epoch epoch){
		  return epochs.remove(epoch);
	}
	
	/**
	 * This method will list all the {@link Epoch}s that are not retired.
	 *
	 * @return the active epochs
	 */
	@Transient
	public List<Epoch> getActiveEpochs(){
		List<Epoch> epochs = new ArrayList<Epoch>();
		List<Epoch> allEpochs = getEpochs();
		if(allEpochs != null ){
			for(Epoch epoch : allEpochs){
				if(epoch.isRetired()) continue;
				epochs.add(epoch);
			}
		}
		return epochs;
	}
	
	//this method is added to satisfy the UI requirements, so to be moved to command classs
	/**
	 * Gets the term code.
	 *
	 * @return the term code
	 */
	@Transient
	public Integer getTermCode(){
		return null;
	}
	//this method is added to satisfy the UI requirements, so to be moved to the command class
	/**
	 * Sets the term code.
	 *
	 * @param ignore the new term code
	 */
	public void setTermCode(Integer ignore){}
	
	/**
	 * Contains solicited ae.
	 *
	 * @param termID the term id
	 * @return true, if successful
	 */
	public boolean containsSolicitedAE(Integer termID)
	{
        for(Epoch epoch : getEpochs())
        {
        	List<SolicitedAdverseEvent> listOfSolicitedAEs = epoch.getArms().get(0).getSolicitedAdverseEvents();
        	
        	for(SolicitedAdverseEvent solicitedAE : listOfSolicitedAEs)
        	{
        		if( solicitedAE.getCtcterm() != null)
        		{
        			
        			if( solicitedAE.getCtcterm().getId().equals( termID) )
        				return true;
        		}
        		else
        		{
        			if( solicitedAE.getLowLevelTerm().getId().equals( termID) )
        				return true;
        		}
        		
        	}
        		
        	
        }
		
		return false;
		
	}

	/**
	 * Gets the ctc categories.
	 *
	 * @return the ctc categories
	 */
	@Transient
	public List<CtcCategory> getCtcCategories(){
		if(ctcCategories.size() != 0)
			return ctcCategories;
		else
			if(aeTerminology != null && aeTerminology.getCtcVersion() != null)
				setCtcCategories(aeTerminology.getCtcVersion().getCategories());
		return ctcCategories;
	}
	
	/**
	 * Sets the ctc categories.
	 *
	 * @param ctcCategories the new ctc categories
	 */
	public void setCtcCategories(List<CtcCategory> ctcCategories) {
		this.ctcCategories = ctcCategories;
	}
	
	/**
	 * Gets the other meddra.
	 *
	 * @return the other meddra
	 */
	@OneToOne
    @JoinColumn(name = "other_meddra_id")
	public MeddraVersion getOtherMeddra() {
		return otherMeddra;
	}
	
	/**
	 * Sets the other meddra.
	 *
	 * @param otherMeddra the new other meddra
	 */
	public void setOtherMeddra(MeddraVersion otherMeddra) {
		this.otherMeddra = otherMeddra;
	}

    /**
     * Gets the study conditions.
     *
     * @return the study conditions
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "term_type = 'dcp'")
    @OrderBy
    @UniqueObjectInCollection(message = "Duplicate - Same condition is associated to the study more than ones")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyCondition> getStudyConditions() {
        return studyConditions;
    }

    /**
     * Gets the active study conditions.
     *
     * @return the active study conditions
     */
    @Transient
    public List<StudyCondition> getActiveStudyConditions() {
        return CollectionUtil.getActiveObjects(getStudyConditions());
    }

    /**
     * Sets the study conditions.
     *
     * @param studyConditions the new study conditions
     */
    public void setStudyConditions(List<StudyCondition> studyConditions) {
        this.studyConditions = studyConditions;
    }

    /**
     * Gets the expected ae ctc terms.
     *
     * @return the expected ae ctc terms
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "term_type = 'ctep'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ExpectedAECtcTerm> getExpectedAECtcTerms() {
        return expectedAECTCTerms;
    }

    /**
     * Sets the expected ae ctc terms.
     *
     * @param expectedAECTCTerms the new expected ae ctc terms
     */
    public void setExpectedAECtcTerms(List<ExpectedAECtcTerm> expectedAECTCTerms) {
        this.expectedAECTCTerms = expectedAECTCTerms;
    }

    /**
     * Adds the expected ae ctc term.
     *
     * @param expectedAECtcTerm the expected ae ctc term
     */
    public void addExpectedAECtcTerm(final ExpectedAECtcTerm expectedAECtcTerm) {
        expectedAECtcTerm.setStudy(this);
        expectedAECTCTerms.add(expectedAECtcTerm);
    }

    /**
     * Gets the expected ae meddra low level terms.
     *
     * @return the expected ae meddra low level terms
     */
    @OneToMany (orphanRemoval = true)
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @UniqueObjectInCollection(message = "Duplicate - Same term is associated to the study more than once")
    @Where(clause = "term_type = 'meddra'")
    public List<ExpectedAEMeddraLowLevelTerm> getExpectedAEMeddraLowLevelTerms() {
        return expectedAEMeddraTerms;
    }

    /**
     * Sets the expected ae meddra low level terms.
     *
     * @param expectedAEMeddraTerms the new expected ae meddra low level terms
     */
    public void setExpectedAEMeddraLowLevelTerms(List<ExpectedAEMeddraLowLevelTerm> expectedAEMeddraTerms) {
        this.expectedAEMeddraTerms = expectedAEMeddraTerms;
    }
    
    /**
     * Adds the expected ae meddra low level term.
     *
     * @param expectedAEMeddraLowLevelTerm the expected ae meddra low level term
     */
    public void addExpectedAEMeddraLowLevelTerm(final ExpectedAEMeddraLowLevelTerm expectedAEMeddraLowLevelTerm) {
        expectedAEMeddraLowLevelTerm.setStudy(this);
        expectedAEMeddraTerms.add(expectedAEMeddraLowLevelTerm);
    }
    
    /**
     * Gets the data entry status.
     *
     * @return the data entry status
     */
    @Column(name="data_entry_status")
    public Boolean getDataEntryStatus(){
    	return dataEntryStatus;
    }
    
    /**
     * Sets the data entry status.
     *
     * @param dataEntryStatus the new data entry status
     */
    public void setDataEntryStatus(Boolean dataEntryStatus) {
		this.dataEntryStatus = dataEntryStatus;
	}
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (shortTitle == null ? 0 : shortTitle.hashCode());
        result = prime * result + (longTitle == null ? 0 : longTitle.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (precis == null ? 0 : precis.hashCode());
        result = prime * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	boolean found = false;
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Study other = (Study) obj;
        if (getIdentifiers() == null) {
            if (other.getIdentifiers() != null) {
                return false;
            }
        } else {
        	for(Identifier identifier : getIdentifiers()){
        		for(Identifier otherIdentifier : other.getIdentifiers()){
        			if(identifier.equals(otherIdentifier)){
        				found = true;
        				break;
        			}
        		}
        	}
        	return found;
        }
        	
        return true;
    }
    
    /**
     * This method checks against the ASAEL, and tells whether the AE term is
     * expected.
     *
     * @param aeTerm the ae term
     * @return true , if expected term, false otherwise.
     */
    public boolean isExpectedAdverseEventTerm(DomainObject aeTerm) {
    	//CTC terminology is only supported currently
    	if(aeTerm != null && aeTerm instanceof CtcTerm){
    		List<ExpectedAECtcTerm> expectedAECtcTerms = this.getExpectedAECtcTerms();
            for (ExpectedAECtcTerm expectedAECtcTerm : expectedAECtcTerms) {
            	if(expectedAECtcTerm.getTerm().getId().equals(aeTerm.getId())) return true;
            }
    	}
    	//not expected.
    	return false; 
    }

    /**
     * Check expected ae uniqueness.
     *
     * @return the abstract expected ae
     */
    @SuppressWarnings("unchecked")
	public AbstractExpectedAE checkExpectedAEUniqueness() {
        List expectedAEs = null;
        if (this.getAeTerminology().getMeddraVersion() != null) expectedAEs = this.getExpectedAEMeddraLowLevelTerms();
        else if (this.getAeTerminology().getCtcVersion() != null) expectedAEs = this.getExpectedAECtcTerms();

        if (expectedAEs == null || expectedAEs.size() == 0) return null;

        Iterator it = expectedAEs.iterator();
        List aes = new ArrayList();
        while (it.hasNext()) {
            AbstractExpectedAE expectedAE = (AbstractExpectedAE)it.next();
            StringBuffer key = new StringBuffer(expectedAE.getTerm().getId().toString());
            if (expectedAE.isOtherRequired()) {
                if (((ExpectedAECtcTerm)expectedAE).getOtherMeddraTerm() == null) continue;
                key.append(((ExpectedAECtcTerm)expectedAE).getOtherMeddraTerm().getId().toString());
            }
            if (aes.contains(key.toString())) return expectedAE;
            aes.add(key.toString());
        }

        return null;
    }
    
    
    /**
     * Gets the unique study organizations.
     *
     * @return the unique study organizations
     */
    @Transient
    public List<StudyOrganization> getUniqueStudyOrganizations() {
        Set<Organization> set = new HashSet<Organization>();
        List<StudyOrganization> list = new ArrayList<StudyOrganization>();
        
        for (StudyOrganization so : getStudyOrganizations()) {
            if (set.add(so.getOrganization())) {
                list.add(so);
            }
        }
        return list;
    }

    /**
     * Synchronize expected a es.
     */
    @Transient
    /*
    *
    * @author Ion C. Olaru
    * This methods retrieves the ASAEL and update/add it to Study Expected AE list.
    * This is NOT removing terms from Study Expected AE list,
    * since the user may have Expected AEs added from the Study flow UI.  
    *
    * */
    public void synchronizeExpectedAEs() {
        // todo
    }

    /**
     * Gets the verbatim first.
     *
     * @return the verbatim first
     */
    public Boolean getVerbatimFirst() {
        return verbatimFirst;
    }

    /**
     * Sets the verbatim first.
     *
     * @param verbatimFirst the new verbatim first
     */
    public void setVerbatimFirst(Boolean verbatimFirst) {
        this.verbatimFirst = verbatimFirst;
    }

    /**
     * Gets the other treatment assignment.
     *
     * @return the other treatment assignment
     */
    public String getOtherTreatmentAssignment() {
        return otherTreatmentAssignment;
    }

    /**
     * Sets the other treatment assignment.
     *
     * @param otherTreatmentAssignment the new other treatment assignment
     */
    public void setOtherTreatmentAssignment(String otherTreatmentAssignment) {
        this.otherTreatmentAssignment = otherTreatmentAssignment;
    }

    public Boolean getAeTermUnique() {
        return aeTermUnique;
    }

    public void setAeTermUnique(Boolean aeTermUnique) {
        this.aeTermUnique = aeTermUnique;
    }

    public String getStudyPurpose() {
        return studyPurpose;
    }

    public void setStudyPurpose(String studyPurpose) {
        this.studyPurpose = studyPurpose;
    }
    
    @Transient
    public boolean getNciIndStudy(){
    	for(StudyAgent sa : getActiveStudyAgents()){
            if(sa.getHasIndHeldByNci()) return true;
    	}
    	return false;
    }
    
    
    @Transient
    public boolean getNciIdeStudy(){
        for(StudyDevice sd : getActiveStudyDevices()){
    		if(sd.getHasIdeHeldByNci()) return true;
    	}
    	
    	return false;
    }
    
    
    

    /**
     * Will initialize the Epochs.
     */
    public void initializeEpocsIfNecessary(){
        if (getEpochs() == null || getEpochs().isEmpty()) {
            addEpoch(new Epoch(Epoch.NAME_BASELINE, 0));
            addEpoch(new Epoch(Epoch.NAME_TREATMENT, 1));
            addEpoch(new Epoch(Epoch.NAME_POSTTREATMENT, 2));
        }
    }
    
    public boolean hasLeadCTEPInds(){
        for (StudyAgent sa : getActiveStudyAgents()) {
            if (sa.isCTEPLead())
                return true;
        }
        return false;
    }
    
    public boolean hasInvestigationalNewDrugs(){
        for(StudyAgent sa : getActiveStudyAgents()) {
            if(sa.getInvestigationalNewDrugIndicator()) return true;
        }
        return false;
    }
    
    public boolean hasInvestigationalNewDevices(){
        for(StudyDevice sd : getActiveStudyDevices()) {
            if(sd.getInvestigationalNewDrugIndicator()) return true;
        }
        return false;
    }
    
    @Transient
    public boolean isHavingTreatmentLevelInterventions(){
    	for(TreatmentAssignment ta: getTreatmentAssignments()){
    		if(ta.isHavingInterventions())
    			return true;
    	}
    	return false;
    }

    @Transient
    public String getDisplayName(){
      if(StringUtils.isNotEmpty(getPrimaryIdentifierValue()))  return "(" + getPrimaryIdentifierValue() + ") " + getShortTitle();
      return getShortTitle();
    }
    
}
