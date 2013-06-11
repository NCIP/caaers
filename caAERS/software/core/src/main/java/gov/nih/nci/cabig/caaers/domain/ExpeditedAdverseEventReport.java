/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.utils.ObjectUtils;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;

 
/**
 * This class represents the ExpeditedAdverseEventReport domain object.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion C. Olaru
 * 
 */
@Entity
@Table(name = "ae_reports")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_ae_reports_id")})
public class ExpeditedAdverseEventReport extends AbstractMutableDomainObject implements  Serializable{
   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3747213703166595074L;
    
    private Boolean investigationalDeviceAdministered;
	
	/** The created at. */
	private Timestamp createdAt;
    
    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;

    /** The response description. */
    private AdverseEventResponseDescription responseDescription;
    
    /** The treatment information. */
    private TreatmentInformation treatmentInformation;
    
    /** The additional information. */
    private AdditionalInformation additionalInformation;

    /** The reporter. */
    private Reporter reporter;
    
    /** The physician. */
    private Physician physician;
    
    /** The participant history. */
    private ParticipantHistory participantHistory;
    
    /** The disease history. */
    private DiseaseHistory diseaseHistory;
    
    /** The reporting period. */
    private AdverseEventReportingPeriod reportingPeriod;

    /** The reports. */
    private List<Report> reports;
    
    //transient field to be used in adding reviewer information in report generation
    private Reporter reviewer;
    
    /** The Constant log. */
    private static final Log log = LogFactory.getLog(ExpeditedAdverseEventReport.class);

    private String externalId;

    // TODO
    // private List<MedicalDevice> medicalDevices;

    /**
     * Instantiates a new expedited adverse event report.
     */
    public ExpeditedAdverseEventReport() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(AdverseEvent.class);
        addReportChildLazyList(Lab.class);
        addReportChildLazyList(MedicalDevice.class);
        addReportChildLazyList(RadiationIntervention.class);
        addReportChildLazyList(SurgeryIntervention.class);
        addReportChildLazyList(BehavioralIntervention.class);
        addReportChildLazyList(GeneticIntervention.class);
        addReportChildLazyList(BiologicalIntervention.class);
        addReportChildLazyList(DietarySupplementIntervention.class);
        addReportChildLazyList(OtherAEIntervention.class);
        addReportChildLazyList(ConcomitantMedication.class);
        addReportChildLazyList(OtherCause.class);
        addReportChildLazyList(SAEReportPriorTherapy.class);
        addReportChildLazyList(SAEReportPreExistingCondition.class);
    }

    /**
     * Adds the report child lazy list.
     *
     * @param <T> the generic type
     * @param klass the klass
     */
    private <T extends ExpeditedAdverseEventReportChild> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass, new ExpeditedAdverseEventReportChildFactory<T>(klass, this));
    }

    ////// LOGIC

    /**
     * Gets the notification message.
     *
     * @return the notification message
     */
    @Transient
    public String getNotificationMessage() {
        if (isNotificationMessagePossible()) {
            String other = "";
            String fullName = "";
            AdverseEvent firstAe = getAdverseEventsInternal().get(0);
            if (firstAe.getAdverseEventCtcTerm().getCtcTerm() != null) {
                CtcTerm term = firstAe.getAdverseEventCtcTerm().getCtcTerm();
                fullName = term.getFullName();
                other = term.isOtherRequired()
                        ? String.format(" (%s)", firstAe.getDetailsForOther()) : "";
            } else {
                fullName = firstAe.getAdverseEventTerm().getUniversalTerm();
            }

            return String.format("Grade %d adverse event with term %s%s",
                    firstAe.getGrade().getCode(),
                    fullName, other
            );
        } else {
            throw new CaaersSystemException(
                    "Cannot create notification message until primary AE is filled in");
        }
    }
    
    @Transient
    public Reporter getReviewer() {
		return reviewer;
	}

	public void setReviewer(Reporter reviewer) {
		this.reviewer = reviewer;
	}

	/**
     * Checks if is notification message possible.
     *
     * @return true, if is notification message possible
     */
    @Transient
    public boolean isNotificationMessagePossible() {
        if (getAdverseEventsInternal().size() < 1) return false;
        AdverseEvent ae = getAdverseEventsInternal().get(0);
        return ae != null && ae.getGrade() != null && ae.getAdverseEventTerm().getTerm() != null;
    }

    /**
     * Gets the participant.
     *
     * @return the participant
     */
    @Transient
    public Participant getParticipant() {
        return getAssignment() == null ? null : getAssignment().getParticipant();
    }

    /**
     * Gets the study.
     *
     * @return the study
     */
    @Transient
    public Study getStudy() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss == null ? null : ss.getStudy();
    }
    
    /**
     * Gets the study site.
     *
     * @return the study site
     */
    @Transient
    public StudySite getStudySite() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss;
    }

    @Transient
    public Map<String, String> getSummary() {
        return getSummary(true);
    }
    
    /**
     * Gets the summary.
     *
     * @return the summary
     */
    @Transient
    public Map<String, String> getSummary(boolean unIdentifiedMode) {
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Study", summaryLine(getStudy()));
        if(unIdentifiedMode) {
            summary.put("Participant", summaryLine(getAssignment()));
        }else {
            summary.put("Participant", summaryLine(getParticipant()));
        }
        summary.put("Report created at", getCreatedAt() == null ? null : getCreatedAt().toString());
        String primaryAeLine = null;
        if (getAdverseEvents().size() > 0 && getAdverseEvents().get(0).getAdverseEventTerm() != null && getAdverseEvents().get(0).getAdverseEventTerm().getUniversalTerm() != null) {
            primaryAeLine = getAdverseEvents().get(0).getAdverseEventTerm().getUniversalTerm();
        }

        summary.put("Primary AE", primaryAeLine);
        summary.put("AE count", Integer.toString(getAdverseEvents().size()));
        summary.put("Public identifier", getPublicIdentifier());

        // TODO: placeholders
        summary.put("Ticket number", null);
        summary.put("Next report due", null);
        summary.put("Course", getReportingPeriod().getName());

        return summary;
    }

    /**
     * Summary line.
     *
     * @param participant the participant
     * @return the string
     */
    private String summaryLine(Participant participant) {
        if (participant == null) return null;
        StringBuilder sb = new StringBuilder();
        appendPrimaryIdentifier(participant, sb);
        sb.append(" ").append(participant.getFullName());
        return sb.toString();
    }

     private String summaryLine(StudyParticipantAssignment assignment) {
        if (assignment == null) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(assignment.getStudySubjectIdentifier());
        return sb.toString();
    }

    /**
     * Summary line.
     *
     * @param study the study
     * @return the string
     */
    private String summaryLine(Study study) {
        if (study == null) return null;
        StringBuilder sb = new StringBuilder();
        appendPrimaryIdentifier(study, sb);
        sb.append(" ").append(study.getShortTitle());
        return sb.toString();
    }

    /**
     * Append primary identifier.
     *
     * @param ided the ided
     * @param sb the sb
     */
    private void appendPrimaryIdentifier(IdentifiableByAssignedIdentifers ided, StringBuilder sb) {
        if (ided.getPrimaryIdentifier() != null) {
            sb.append(" (").append(ided.getPrimaryIdentifier().getValue()).append(')');
        }
    }

    /**
     * Adds the adverse event.
     *
     * @param adverseEvent the adverse event
     */
    public void addAdverseEvent(AdverseEvent adverseEvent) {
        getAdverseEventsInternal().add(adverseEvent);
        if (adverseEvent != null) adverseEvent.setReport(this);
    }

    /**
     * To cover cases when we do not need the AdverseEvent to have a referrence to ExpeditedAdverseEventReport
     * @param adverseEvent
     */
    public void addAdverseEventUnidirectional(AdverseEvent adverseEvent) {
        getAdverseEventsInternal().add(adverseEvent);
    }

	/**
	 * This method will remove the {@link AdverseEvent} from the list and will
	 * reset the {@link AdverseEvent#getReport()} association to null. However,
	 * you're still responsible for persisting the updated {@link AdverseEvent}
	 * instance, because the removal operation will not cascade. 
	 * 
	 * @param adverseEvent
	 */
	public void removeAdverseEvent(AdverseEvent adverseEvent) {
        adverseEvent.deleteAttributions();
		getAdverseEventsInternal().remove(adverseEvent);
        adverseEvent.setReport(null);
	}
    
    /**
     * @param aeId
     * @return
     */
    @Transient
    public AdverseEvent getAdverseEvent(int aeId) {
    	for (AdverseEvent ae: getAdverseEventsInternal()) {
    		if (ae.getId()!=null && ae.getId()==aeId) {
    			return ae;
    		}
    	}
    	return null;
    }

    /**
     * Gets the adverse events.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<AdverseEvent> getAdverseEvents() {
        return lazyListHelper.getLazyList(AdverseEvent.class);
    }
    
    /**
     * List of adverse events that are not retired.
     *
     * @return the active adverse events
     */
    @Transient
    public List<AdverseEvent> getActiveAdverseEvents(){
    	List<AdverseEvent> activeEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getAdverseEvents()){
    		if(ae.isRetired()) continue;
    		activeEvents.add(ae);
    	}
    	return activeEvents;
    }
    
    /**
     * List of active adverse events, that are modified.
     *
     * @return the active modified adverse events
     */
    @Transient
    public List<AdverseEvent> getActiveModifiedAdverseEvents(){
    	List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getActiveAdverseEvents()){
    		if(ae.isModified()){
    			adverseEvents.add(ae);
    		}
    	}
    	return adverseEvents;
    }
    
    /**
     * This method will return all the adverse events,which got modified.
     * It is obtained by comparing the saved signature and newly calculated signature.
     *
     * @return the modified adverse events
     */
    @Transient
    public List<AdverseEvent> getModifiedAdverseEvents(){
    	List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getAdverseEvents()){
    		if(ae.isModified()){
    			adverseEvents.add(ae);
    		}
    	}
    	return adverseEvents;
    }
    
    /**
     * Gets the modified adverse events.
     *
     * @param ruleableFields the ruleable fields
     * @return the modified adverse events
     * @author Ion C. Olaru
     * This method will return all the adverse events, which have at least one ruleable field modified
     */
    @Transient
    public List<AdverseEvent> getModifiedAdverseEvents(List<String> ruleableFields) {
        List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getAdverseEvents()){
    		if (ae.isRuleableFieldsModified(ruleableFields)) {
    			adverseEvents.add(ae);
    		}
    	}
    	return adverseEvents;
    }


    /**
     * Adds the lab.
     *
     * @param lab the lab
     */
    public void addLab(Lab lab) {
        getLabsInternal().add(lab);
        if (lab != null) lab.setReport(this);
    }

    /**
     * Adds the lab.
     *
     * @param lab the lab
     */
    public void removeLab(Lab lab) {
        getLabsInternal().remove(lab);
        lab.setReport(null);
    }

    /**
     * Gets the labs.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<Lab> getLabs() {
        return lazyListHelper.getLazyList(Lab.class);
    }

    /**
     * Adds the medical device.
     *
     * @param medicalDevice the medical device
     */
    public void addMedicalDevice(MedicalDevice medicalDevice) {
        getMedicalDevicesInternal().add(medicalDevice);
        if (medicalDevice != null) medicalDevice.setReport(this);
    }

    /**
     * Gets the medical devices.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<MedicalDevice> getMedicalDevices() {
        return lazyListHelper.getLazyList(MedicalDevice.class);
    }


    /**
     * Adds the radiation intervention.
     *
     * @param radiationIntervention the radiation intervention
     */
    public void addRadiationIntervention(RadiationIntervention radiationIntervention) {
        getRadiationInterventionsInternal().add(radiationIntervention);
        if (radiationIntervention != null) radiationIntervention.setReport(this);
    }

    /**
     * Gets the radiation interventions.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<RadiationIntervention> getRadiationInterventions() {
        return lazyListHelper.getLazyList(RadiationIntervention.class);
    }

    /**
     * Adds the surgery intervention.
     *
     * @param surgeryIntervention the surgery intervention
     */
    public void addSurgeryIntervention(SurgeryIntervention surgeryIntervention) {
        getSurgeryInterventionsInternal().add(surgeryIntervention);
        if (surgeryIntervention != null) surgeryIntervention.setReport(this);
    }

    public void addBehavioralIntervention(BehavioralIntervention i) {
        getBehavioralInterventionsInternal().add(i);
        if (i != null) i.setReport(this);
    }

    public void addBilogicalIntervention(BiologicalIntervention i) {
        getBiologicalInterventionsInternal().add(i);
        if (i != null) i.setReport(this);
    }

    public void addGeneticIntervention(GeneticIntervention i) {
        getGeneticInterventionsInternal().add(i);
        if (i != null) i.setReport(this);
    }

    public void addDietarySupplementalIntervention(DietarySupplementIntervention i) {
        getDietarySupplementInterventionsInternal().add(i);
        if (i != null) i.setReport(this);
    }

    public void addOtherAEIntervention(OtherAEIntervention i) {
        getOtherAEInterventionsInternal().add(i);
        if (i != null) i.setReport(this);
    }

    /**
     * Gets the surgery interventions.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<SurgeryIntervention> getSurgeryInterventions() {
        return lazyListHelper.getLazyList(SurgeryIntervention.class);
    }

    @Transient
    public List<BehavioralIntervention> getBehavioralInterventions() {
        return lazyListHelper.getLazyList(BehavioralIntervention.class);
    }

    @Transient
    public List<BiologicalIntervention> getBiologicalInterventions() {
        return lazyListHelper.getLazyList(BiologicalIntervention.class);
    }

    @Transient
    public List<GeneticIntervention> getGeneticInterventions() {
        return lazyListHelper.getLazyList(GeneticIntervention.class);
    }

    @Transient
    public List<DietarySupplementIntervention> getDietaryInterventions() {
        return lazyListHelper.getLazyList(DietarySupplementIntervention.class);
    }

    @Transient
    public List<OtherAEIntervention> getOtherAEInterventions() {
        return lazyListHelper.getLazyList(OtherAEIntervention.class);
    }

    /**
     * Adds the concomitant medication.
     *
     * @param concomitantMedication the concomitant medication
     */
    public void addConcomitantMedication(ConcomitantMedication concomitantMedication) {
        getConcomitantMedicationsInternal().add(concomitantMedication);
        if (concomitantMedication != null) concomitantMedication.setReport(this);
    }

    /**
     * Gets the concomitant medications.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<ConcomitantMedication> getConcomitantMedications() {
        return lazyListHelper.getLazyList(ConcomitantMedication.class);
    }

    /**
     * Adds the sae report pre existing condition.
     *
     * @param sAEReportPreExistingCondition the s ae report pre existing condition
     */
    public void addSaeReportPreExistingCondition(SAEReportPreExistingCondition sAEReportPreExistingCondition) {
        getSaeReportPreExistingConditionsInternal().add(sAEReportPreExistingCondition);
        if (sAEReportPreExistingCondition != null) sAEReportPreExistingCondition.setReport(this);
    }

    /**
     * Gets the sae report pre existing conditions.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
//    @UniqueObjectInCollection(message = "Duplicate pre existing condition")
    public List<SAEReportPreExistingCondition> getSaeReportPreExistingConditions() {
        return lazyListHelper.getLazyList(SAEReportPreExistingCondition.class);
    }

    /**
     * Adds the sae report prior therapies.
     *
     * @param saeReportPriorTherapy the sae report prior therapy
     */
    public void addSaeReportPriorTherapies(SAEReportPriorTherapy saeReportPriorTherapy) {
        getSaeReportPriorTherapiesInternal().add(saeReportPriorTherapy);
        if (saeReportPriorTherapy != null) saeReportPriorTherapy.setReport(this);
    }

    /**
     * Gets the sae report prior therapies.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate prior therapy")
    public List<SAEReportPriorTherapy> getSaeReportPriorTherapies() {
        return lazyListHelper.getLazyList(SAEReportPriorTherapy.class);
    }


    /**
     * Adds the other cause.
     *
     * @param otherCause the other cause
     */
    public void addOtherCause(OtherCause otherCause) {
        getOtherCausesInternal().add(otherCause);
        if (otherCause != null) otherCause.setReport(this);
    }

    /**
     * Gets the other causes.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate other cause")
    public List<OtherCause> getOtherCauses() {
        return lazyListHelper.getLazyList(OtherCause.class);
    }


    ////// BEAN PROPERTIES
    @Column(name="ide_administered")
    public Boolean getInvestigationalDeviceAdministered() {
        return investigationalDeviceAdministered;
    }

    public void setInvestigationalDeviceAdministered(Boolean investigationalDeviceAdministered) {
        this.investigationalDeviceAdministered = investigationalDeviceAdministered;
    }

    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @Transient
    public StudyParticipantAssignment getAssignment() {
        return getReportingPeriod().getAssignment();
    }

    /**
     * Sets the assignment.
     *
     * @param assignment the new assignment
     */
    public void setAssignment(StudyParticipantAssignment assignment) {
        this.reportingPeriod.setAssignment(assignment);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the adverse events internal.
     *
     * @return the adverse events internal
     */
    @OneToMany
    @JoinColumn(name = "report_id", nullable = true)
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = {
            // Manually-managing PERSIST cascades due to cascade ordering issue
            CascadeType.DELETE, CascadeType.DETACH, CascadeType.LOCK, CascadeType.MERGE,
            CascadeType.REFRESH})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<AdverseEvent> getAdverseEventsInternal() {
        return lazyListHelper.getInternalList(AdverseEvent.class);
    }

    /**
     * Sets the adverse events internal.
     *
     * @param adverseEvents the new adverse events internal
     */
    @SuppressWarnings("unchecked")
    protected void setAdverseEventsInternal(List<AdverseEvent> adverseEvents) {
        lazyListHelper.setInternalList(AdverseEvent.class, adverseEvents);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the labs internal.
     *
     * @return the labs internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<Lab> getLabsInternal() {
        return lazyListHelper.getInternalList(Lab.class);
    }

    /**
     * Sets the labs internal.
     *
     * @param labsInternal the new labs internal
     */
    protected void setLabsInternal(List<Lab> labsInternal) {
        lazyListHelper.setInternalList(Lab.class, labsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the medical devices internal.
     *
     * @return the medical devices internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<MedicalDevice> getMedicalDevicesInternal() {
        return lazyListHelper.getInternalList(MedicalDevice.class);
    }

    /**
     * Sets the medical devices internal.
     *
     * @param medicalDevicesInternal the new medical devices internal
     */
    protected void setMedicalDevicesInternal(List<MedicalDevice> medicalDevicesInternal) {
        lazyListHelper.setInternalList(MedicalDevice.class, medicalDevicesInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the radiation interventions internal.
     *
     * @return the radiation interventions internal
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<RadiationIntervention> getRadiationInterventionsInternal() {
        return lazyListHelper.getInternalList(RadiationIntervention.class);
    }

    /**
     * Sets the radiation interventions internal.
     *
     * @param radiationInterventionsInternal the new radiation interventions internal
     */
    protected void setRadiationInterventionsInternal(List<RadiationIntervention> radiationInterventionsInternal) {
        lazyListHelper.setInternalList(RadiationIntervention.class, radiationInterventionsInternal);
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the surgery interventions internal.
     *
     * @return the surgery interventions internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<SurgeryIntervention> getSurgeryInterventionsInternal() {
        return lazyListHelper.getInternalList(SurgeryIntervention.class);
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<BehavioralIntervention> getBehavioralInterventionsInternal() {
        return lazyListHelper.getInternalList(BehavioralIntervention.class);
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<BiologicalIntervention> getBiologicalInterventionsInternal() {
        return lazyListHelper.getInternalList(BiologicalIntervention.class);
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<GeneticIntervention> getGeneticInterventionsInternal() {
        return lazyListHelper.getInternalList(GeneticIntervention.class);
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<DietarySupplementIntervention> getDietarySupplementInterventionsInternal() {
        return lazyListHelper.getInternalList(DietarySupplementIntervention.class);
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<OtherAEIntervention> getOtherAEInterventionsInternal() {
        return lazyListHelper.getInternalList(OtherAEIntervention.class);
    }

    /**
     * Sets the surgery interventions internal.
     *
     * @param surgeryInterventionsInternal the new surgery interventions internal
     */
    protected void setSurgeryInterventionsInternal(List<SurgeryIntervention> surgeryInterventionsInternal) {
        lazyListHelper.setInternalList(SurgeryIntervention.class, surgeryInterventionsInternal);
    }

    protected void setBehavioralInterventionsInternal(List<BehavioralIntervention> l) {
        lazyListHelper.setInternalList(BehavioralIntervention.class, l);
    }

    protected void setBiologicalInterventionsInternal(List<BiologicalIntervention> l) {
        lazyListHelper.setInternalList(BiologicalIntervention.class, l);
    }

    protected void setGeneticInterventionsInternal(List<GeneticIntervention> l) {
        lazyListHelper.setInternalList(GeneticIntervention.class, l);
    }

    protected void setDietarySupplementInterventionsInternal(List<DietarySupplementIntervention> l) {
        lazyListHelper.setInternalList(DietarySupplementIntervention.class, l);
    }

    protected void setOtherAEInterventionsInternal(List<OtherAEIntervention> l) {
        lazyListHelper.setInternalList(OtherAEIntervention.class, l);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the concomitant medications internal.
     *
     * @return the concomitant medications internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<ConcomitantMedication> getConcomitantMedicationsInternal() {
        return lazyListHelper.getInternalList(ConcomitantMedication.class);
    }

    /**
     * Sets the concomitant medications internal.
     *
     * @param concomitantMedicationsInternal the new concomitant medications internal
     */
    protected void setConcomitantMedicationsInternal(List<ConcomitantMedication> concomitantMedicationsInternal) {
        lazyListHelper.setInternalList(ConcomitantMedication.class, concomitantMedicationsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the sae report pre existing conditions internal.
     *
     * @return the sae report pre existing conditions internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<SAEReportPreExistingCondition> getSaeReportPreExistingConditionsInternal() {
        return lazyListHelper.getInternalList(SAEReportPreExistingCondition.class);
    }

    /**
     * Sets the sae report pre existing conditions internal.
     *
     * @param saeReportPreExistingConditionInternal the new sae report pre existing conditions internal
     */
    protected void setSaeReportPreExistingConditionsInternal(List<SAEReportPreExistingCondition> saeReportPreExistingConditionInternal) {
        lazyListHelper.setInternalList(SAEReportPreExistingCondition.class, saeReportPreExistingConditionInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the other causes internal.
     *
     * @return the other causes internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    protected List<OtherCause> getOtherCausesInternal() {
        return lazyListHelper.getInternalList(OtherCause.class);
    }

    /**
     * Sets the other causes internal.
     *
     * @param otherCausesInternal the new other causes internal
     */
    protected void setOtherCausesInternal(List<OtherCause> otherCausesInternal) {
        lazyListHelper.setInternalList(OtherCause.class, otherCausesInternal);
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the sae report prior therapies internal.
     *
     * @return the sae report prior therapies internal
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<SAEReportPriorTherapy> getSaeReportPriorTherapiesInternal() {
        return lazyListHelper.getInternalList(SAEReportPriorTherapy.class);
    }

    /**
     * Sets the sae report prior therapies internal.
     *
     * @param saeReportPriorTherapiesInternal the new sae report prior therapies internal
     */
    public void setSaeReportPriorTherapiesInternal(
            List<SAEReportPriorTherapy> saeReportPriorTherapiesInternal) {
        lazyListHelper.setInternalList(SAEReportPriorTherapy.class, saeReportPriorTherapiesInternal);
    }


    /**
     * Gets the treatment information.
     *
     * @return the treatment information
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public TreatmentInformation getTreatmentInformation() {
        if (treatmentInformation == null) setTreatmentInformation(new TreatmentInformation());
        return treatmentInformation;
    }

    /**
     * Sets the treatment information.
     *
     * @param treatmentInformation the new treatment information
     */
    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
        if (treatmentInformation != null) treatmentInformation.setReport(this);
    }

    /**
     * Gets the additional information.
     *
     * @return the additional information
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public AdditionalInformation getAdditionalInformation() {
        if (additionalInformation == null) setAdditionalInformation(new AdditionalInformation());
        return additionalInformation;
    }

    /**
     * Sets the additional information.
     *
     * @param additionalInformation the new additional information
     */
    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        if (additionalInformation != null) additionalInformation.setReport(this);
    }

    /**
     * Gets the response description.
     *
     * @return the response description
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public AdverseEventResponseDescription getResponseDescription() {
        if (responseDescription == null) setResponseDescription(new AdverseEventResponseDescription());
        return responseDescription;
    }

    /**
     * Sets the response description.
     *
     * @param responseDescription the new response description
     */
    public void setResponseDescription(AdverseEventResponseDescription responseDescription) {
        this.responseDescription = responseDescription;
        if (responseDescription != null) responseDescription.setReport(this);
    }

    // non-total cascade allows us to skip saving if the reporter hasn't been filled in yet
    /**
     * Gets the reporter.
     *
     * @return the reporter
     */
    @OneToOne(mappedBy = "expeditedReport",  fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.DELETE, CascadeType.DETACH, CascadeType.LOCK, CascadeType.REMOVE})
    public Reporter getReporter() {
        return reporter;
    }

    /**
     * Sets the reporter.
     *
     * @param reporter the new reporter
     */
    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
        if (reporter != null) reporter.setExpeditedReport(this);
    }
    


    // non-total cascade allows us to skip saving if the physician hasn't been filled in yet
    /**
     * Gets the physician.
     *
     * @return the physician
     */
    @OneToOne(mappedBy = "expeditedReport", fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.DELETE, CascadeType.DETACH, CascadeType.LOCK, CascadeType.REMOVE, CascadeType.MERGE})
    public Physician getPhysician() {
        return physician;
    }

    /**
     * Sets the physician.
     *
     * @param physician the new physician
     */
    public void setPhysician(Physician physician) {
        this.physician = physician;
        if (physician != null) physician.setExpeditedReport(this);
    }

    /**
     * Gets the disease history.
     *
     * @return the disease history
     */
    @OneToOne(mappedBy = "report", fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL})
    public DiseaseHistory getDiseaseHistory() {
        if (diseaseHistory == null) setDiseaseHistory(new DiseaseHistory());
        return diseaseHistory;
    }

    /**
     * Sets the disease history.
     *
     * @param diseaseHistory the new disease history
     */
    public void setDiseaseHistory(DiseaseHistory diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
        if (diseaseHistory != null) diseaseHistory.setReport(this);
    }

    /**
     * Gets the participant history.
     *
     * @return the participant history
     */
    @OneToOne(mappedBy = "report", fetch=FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL })
    public ParticipantHistory getParticipantHistory() {
        if (participantHistory == null) setParticipantHistory(new ParticipantHistory());
        return participantHistory;
    }

    /**
     * Sets the participant history.
     *
     * @param participantHistory the new participant history
     */
    public void setParticipantHistory(ParticipantHistory participantHistory) {
        this.participantHistory = participantHistory;
        if (participantHistory != null) participantHistory.setReport(this);
    }

    /**
     * Gets the reports.
     *
     * @return the reports
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aeReport")
    @Cascade(value = {CascadeType.DELETE, CascadeType.DETACH,
            CascadeType.LOCK, CascadeType.REMOVE})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // Manually manage update-style reassociates and saves
    public List<Report> getReports() {
        if (reports == null) reports = new ArrayList<Report>();
        return reports;
    }
    
    /**
     * True,when at least one Report is active.
     *
     * @return true, if is active
     */
    @Transient
    public boolean isActive(){
    	for(Report report : getReports()){
    		if(report.isActive()) return true;
    	}
    	return false;
    }

    /**
     * This method returns all the reports that are not in {@link ReportStatus}.WITHDRAWN or {@link ReportStatus}.REPLACED.
     *
     * @return the active reports
     */
    @Transient
    public List<Report> getActiveReports() {
        List<Report> reports = getReports();
        if (reports.isEmpty()) return reports;
        List<Report> activeReports = new ArrayList<Report>();
        for (Report report : reports) {
            if (report.isActive()) activeReports.add(report);
        }
        return activeReports;
    }
    
    /**
     * Returns all the pending reports, that are in PENDING.
     *
     * @return the pending reports
     */
    @Transient
    public List<Report> getPendingReports(){
    	List<Report> pendingReports = new ArrayList<Report>();
    	for(Report report: getReports()){
    		if(ReportStatus.PENDING.equals(report.getStatus())) pendingReports.add(report);
    	}
    	return pendingReports;
    }
    
    /**
     * Tells whether an active report (ie. in PENDING, INPROCESS, FAILED) status, beloing to the same report definition is present.
     *
     * @param reportType the report type
     * @return true, if is an active report present
     */
    @Transient
    public boolean isAnActiveReportPresent(ReportDefinition reportType){
    	for(Report report : getActiveReports()){
    		if(report.getReportDefinition().getId().equals(reportType.getId())) return true;
    	}
    	return false;
    }
    
    /**
     * Lists the reports that are completed and is amendable.
     *
     * @return the list
     */
    public List<Report> findCompletedAmendableReports(){
    	List<Report> completedReports = listReportsHavingStatus(ReportStatus.COMPLETED);
    	List<Report> amendableReports = new ArrayList<Report>();
    	for(Report report : completedReports){
    		if(report.isAmendable()) amendableReports.add(report);
    	}
    	return amendableReports;
    }
    
    /**
     * Sets the reports.
     *
     * @param reports the new reports
     */
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    /**
     * Adds the report.
     *
     * @param report the report
     */
    public void addReport(Report report) {
        getReports().add(report);
        report.setAeReport(this);
    }
    
   

    /**
     * Gets the created at.
     *
     * @return the created at
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created at.
     *
     * @param createdAt the new created at
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the reporting period.
     *
     * @return the reporting period
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "reporting_period_id")
    @Cascade(value = {CascadeType.LOCK})
    public AdverseEventReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    /**
     * Sets the reporting period.
     *
     * @param reportingPeriod the new reporting period
     */
    public void setReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    /**
     * Gets the public identifier.
     *
     * @return the public identifier
     */
    @Transient
    public String getPublicIdentifier() {
        String id = getAssignment().getStudySite().getOrganization().getNciInstituteCode() + "-" + getAssignment().getStudySite().getOrganization().getNciInstituteCode();
        id = (id.indexOf("null") > -1) ? "None" : id;
        return id;
    }

    /**
     * Sets the public identifier.
     *
     * @param strId the new public identifier
     */
    @Transient
    public void setPublicIdentifier(String strId) {
    }

 
    /**
     * Find phone numbers.
     *
     * @param role the role
     * @return the list
     */
    @Transient
    List<String> findPhoneNumbers(String role) {
        assert false : "Not implemented";
        return null;
    }

    /**
     * Find fax numbers.
     *
     * @param role the role
     * @return the list
     */
    @Transient
    List<String> findFaxNumbers(String role) {
        assert false : "Not implemented";
        return null;
    }

    /**
     * Gets the number of aes.
     *
     * @return the number of aes
     */
    @Transient
    public int getNumberOfAes() {
        int count = (this.getAdverseEvents() != null) ? this.getAdverseEvents().size() : 0;
        return count;
    }

    /**
     * Gets the primary report.
     *
     * @return the primary report
     */
    @Transient
    public Report getPrimaryReport() {
        return getReports().get(0);
    }

    /*
   you should call this method only once
    */
    /**
     * Synchronize medical history from assignment to report.
     */
    public void synchronizeMedicalHistoryFromAssignmentToReport() {
        StudyParticipantAssignment assignment = getAssignment();
        if (assignment == null) {
            throw new CaaersSystemException("Must set assignment before calling synchronizeMedicalHistoryFromAssignmentToReport");
        } else {
            // synchronize from assignment to report
        	getParticipantHistory().setBaselinePerformanceStatus(assignment.getBaselinePerformance());
            syncrhonizePriorTherapies();
            syncrhonizePreExistingConditions();
            syncrhonizeConcomitantMedications();
            syncrhonizeDiseaseHistories();
        }
    }

    /**
     * synchronize prior therapies from assignment to report.
     */
    private void syncrhonizePriorTherapies() {
        HashSet<String> set = new HashSet<String>();
        if (getSaeReportPriorTherapies().isEmpty()) {
            //copy only once
            for (StudyParticipantPriorTherapy studyParticipantPriorTherapy : getAssignment().getPriorTherapies()) {
                if(studyParticipantPriorTherapy.getPriorTherapy() == null) continue;
                if(studyParticipantPriorTherapy.getPriorTherapy().isRetired()) continue;
                if(set.add(studyParticipantPriorTherapy.getDisplayName())){
                    SAEReportPriorTherapy priorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);
                    addSaeReportPriorTherapies(priorTherapy);
                }
            }
        }
    }


    /**
     * Syncrhonize pre existing conditions.
     */
    private void syncrhonizePreExistingConditions() {
        HashSet<String> set = new HashSet<String>();
        if (getSaeReportPreExistingConditions().isEmpty()) {
            //copy only once
            for (StudyParticipantPreExistingCondition studyParticipantPreExistingCondition : getAssignment().getPreExistingConditions()) {
                if(studyParticipantPreExistingCondition.getPreExistingCondition() == null) continue;
                if(studyParticipantPreExistingCondition.getPreExistingCondition().isRetired()) continue;
                if(set.add(studyParticipantPreExistingCondition.getName()))  {
                    SAEReportPreExistingCondition saeReportPreExistingCondition = SAEReportPreExistingCondition.createSAEReportPreExistingCondition(studyParticipantPreExistingCondition);
                    addSaeReportPreExistingCondition(saeReportPreExistingCondition);
                }

            }
        }

    }


    /**
     * Syncrhonize concomitant medications.
     */
    private void syncrhonizeConcomitantMedications() {
        HashSet<String> set = new HashSet<String>();
        if (getConcomitantMedications().isEmpty()) {
            //copy only once
            for (StudyParticipantConcomitantMedication studyParticipantConcomitantMedication : getAssignment().getConcomitantMedications()) {
                if(studyParticipantConcomitantMedication.getAgentName() == null) continue;
                if(set.add(studyParticipantConcomitantMedication.getAgentName())){
                    ConcomitantMedication saeReportConcomitantMedication = ConcomitantMedication.createConcomitantMedication(studyParticipantConcomitantMedication);
                    addConcomitantMedication(saeReportConcomitantMedication);
                }
            }

        }

    }

    /**
     * Syncrhonize disease histories.
     */
    private void syncrhonizeDiseaseHistories() {

        if ((getDiseaseHistory() == null) || (getDiseaseHistory() != null && getDiseaseHistory().getId() == null)) {
            //copy only once
            DiseaseHistory saeReportDiseaseHistory = DiseaseHistory.createDiseaseHistory(getAssignment().getDiseaseHistory());
            setDiseaseHistory(saeReportDiseaseHistory);

        }

    }
    
    
    /**
     * This method returns true if any of the reports associated to this data-collection was submitted
     * successfully.
     *
     * @return the checks for submitted amendable report
     */
    @Transient
    public Boolean getHasSubmittedAmendableReport(){
    	for(Report report : reports){
    		if(report.isSubmitted() && report.isAmendable()) return true;
    	}
    	return false;
    }
    
    /**
     * This method returns true if the data-collection has atleast one amendable report. It returns false otherwise.
     *
     * @return the checks for amendable report
     */
    @Transient
    public Boolean getHasAmendableReport(){
    	Boolean hasAmendableReport = false;
    	for(Report report: reports){
    		if(report.getReportDefinition().getAmendable())
    			hasAmendableReport = true;
    	}
    	return hasAmendableReport;
    }
    
    /**
     * Returns true if all of the active {@link Report} associated to this data collection, says attribution is requried.
     *
     * @return true, if is attribution required
     */
    @Transient
    public boolean isAttributionRequired(){
    	boolean attributionRequired = true;
    	int activeCount = 0; 
    	for(Report report : getReports()){
    		if(!report.isActive()) continue;
    		activeCount++;
    		attributionRequired &= report.isAttributionRequired();
    	}
    	return activeCount > 0 && attributionRequired;
    	
    }
    /**
     * This method will update the signatures in all the adverse events associated to 
     * this expedited data collection.
     */
    public void updateSignatureOfAdverseEvents(){
    	for(AdverseEvent ae: getAdverseEvents()){
    		ae.setSignature(ae.getCurrentSignature());
    	}
    }
    
    
    /**
     * This method will return the earliest graded date, of  adverse events.
     *
     * @return the earliest adverse event graded date
     */
    @Transient
    public Date getEarliestAdverseEventGradedDate(){
    	Date d = null;
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.getGradedDate() == null) continue;
    		if(d == null){
    			d = ae.getGradedDate();
    		}else{
    			d = (DateUtils.compateDateAndTime(ae.getGradedDate(), d) < 0) ? ae.getGradedDate() : d;
    		}
    	}
    	return d;
    }
    

    
    /**
     * This method will set the reported flag on adverse events.
     */
    public void updateReportedFlagOnAdverseEvents(){
    	for(AdverseEvent ae: getAdverseEvents()){
    		ae.setReported(true);
    	}
    }
    
    /**
     * This method will clear the reportedFlag, set on previously reported adverse events, 
     * which got modified.
     */
    public void clearReportedFlagOnModifiedAdverseEvents(){
    	List<AdverseEvent> modifiedAdverseEvents = getModifiedAdverseEvents();
    	for(AdverseEvent modifiedAdverseEvent : modifiedAdverseEvents){
    		modifiedAdverseEvent.setReported(false);
    	}
    }
    
    /**
     * This method will clear the post submission updated date on each of the adverse events. 
     */
    public void clearPostSubmissionUpdatedDate(){
    	for(AdverseEvent ae : getAdverseEvents()){
    		ae.setPostSubmissionUpdatedDate(null);
    	}
    }
    
    /**
     * Checks if is physician sign off required.
     *
     * @return true, if is physician sign off required
     */
    @Transient
    public boolean isPhysicianSignOffRequired(){
    	boolean physicianSignOffRequired = false;
    	for(Report report: getReports()){
    		if(report.isActive())
    			physicianSignOffRequired |= report.getReportDefinition().getPhysicianSignOff();
    	}
    	return physicianSignOffRequired;
    }
    
    /**
     * Gets the physician sign off.
     *
     * @return the physician sign off
     */
    @Transient
    public Boolean getPhysicianSignOff(){
    	Boolean physicianSignOff = true;
    	for(Report report: getReports()){
    		if(report.getPhysicianSignoff() != null)
    			physicianSignOff &= report.getPhysicianSignoff();
    		else
    			physicianSignOff = false;
    	}
    	return physicianSignOff;
    }
    
    /**
     * Sets the physician sign off.
     *
     * @param physicianSignOff the new physician sign off
     */
    @Transient
    public void setPhysicianSignOff(Boolean physicianSignOff){
    	for(Report report: getReports())
    		report.setPhysicianSignoff(physicianSignOff);
    }
    
    /**
     * List all the reports that were created manually. 
     * @return - all {@link Report}s whose manuallySelected flag is set.
     */
    @Transient
    public List<Report> getManuallySelectedReports(){
    	ArrayList<Report> manuallySelectedReports = new ArrayList<Report>();
    	for(Report report : getActiveReports()){
    		if(report.isManuallySelected()) manuallySelectedReports.add(report);
    	}
    	return manuallySelectedReports;
    }
    
    
    
    /**
     * Lists out the report that completed, belonging to the same group and organization
     * of the {@link ReportDefinition} param rd.
     *
     * @param rd the rd
     * @return the list
     */
    public List<Report> findReportsToAmmend(ReportDefinition rd){
    	List<Report> reports = listReportsHavingStatus(ReportStatus.COMPLETED);
    	List<Report> reportsToAmmend = new ArrayList<Report>();
    	//check if the reports are amendable and belongs to same organization & group.
    	for(Report report : reports){
    		ReportDefinition rdOther = report.getReportDefinition();
    		if(!rdOther.getAmendable()) continue;
    		if(!rdOther.getOrganization().getId().equals(rd.getOrganization().getId())) continue;
    		if(!rdOther.getGroup().getCode().equals(rd.getGroup().getCode())) continue;
    		
    		reportsToAmmend.add(report);
    	}
    	return reportsToAmmend;
    }
    
    /**
     * Find reports to witdraw.
     *
     * @param rd the rd
     * @return the list
     */
    public List<Report> findReportsToWitdraw(ReportDefinition rd){
    	List<Report> reports = listReportsHavingStatus(ReportStatus.PENDING, ReportStatus.FAILED, ReportStatus.INPROCESS);
    	List<Report> reportsToWitdraw = new ArrayList<Report>();
    	//check if they belong to same group/organization and rd is less than rdOther
    	for(Report report : reports){
    		ReportDefinition rdOther = report.getReportDefinition();
    		if(rdOther.getId().equals(rd.getId())) continue;
    		if(!rdOther.getOrganization().getId().equals(rd.getOrganization().getId())) continue;
    		if(!rdOther.getGroup().getCode().equals(rd.getGroup().getCode())) continue;
//    		int delta = rd.compareTo(rdOther);
//    		if( delta < 0) continue;
    		reportsToWitdraw.add(report);
    	}
    	return reportsToWitdraw;
    }
    
    /**
     * Find reports to edit.
     *
     * @param rd the rd
     * @return the list
     */
    public List<Report> findReportsToEdit(ReportDefinition rd){
    	List<Report> reports = listReportsHavingStatus(ReportStatus.PENDING, ReportStatus.FAILED, ReportStatus.INPROCESS);
    	List<Report> reportsToEdit = new ArrayList<Report>();
    	//check if they belong to the same report definition. 
    	for(Report report :reports){
    		if(report.getReportDefinition().getId().equals(rd.getId())){
    			reportsToEdit.add(report);
    		}
    	}
    	return reportsToEdit;
    }
    
    /**
     * This method will find the recently amended report, that belongs to the same group and organization.
     *
     * @param rd the rd
     * @return the report
     */
    public Report findLastAmendedReport(ReportDefinition rd){
    	List<Report> reports = listReportsHavingStatus(ReportStatus.AMENDED);
    	Report theReport = null;
    	for(Report report : reports){
    		if(report.isOfSameOrganizationAndType(rd)){
    			if(theReport == null){
    				theReport = report;
    			}else{
    				if(DateUtils.compateDateAndTime(theReport.getAmendedOn(), report.getAmendedOn()) < 0){
    					theReport = report;
    				}
    			}
    		}
    	}
    	return theReport;
    }
    
    /**
     * The report that is instantiated last, and is belonging to same organization and type.
     *
     * @param rd the rd
     * @return the report
     */
    public Report findLastSubmittedReport(ReportDefinition rd){
    	List<Report> reports = listReportsHavingStatus(ReportStatus.AMENDED, ReportStatus.COMPLETED);
    	Report theReport = null;
    	for(Report report : reports){
    		if(report.isOfSameOrganizationAndType(rd)){
    			if(theReport == null){
    				theReport = report;
    			}else{
    				if(DateUtils.compateDateAndTime(theReport.getSubmittedOn(), report.getSubmittedOn()) < 0){
    					theReport = report;
    				}
    			}
    		}
    	}
    	return theReport;
    }
    
    /**
     * List reports having status.
     *
     * @param statuses the statuses
     * @return the list
     */
    public List<Report> listReportsHavingStatus(ReportStatus... statuses){
    	List<Report> reports = new ArrayList<Report>();
    	for(Report report : getReports()){
    		if(report.isHavingStatus(statuses)) reports.add(report);
    	}
    	return reports;
    }
    
    /**
     * Will return true, if there is an organization of same group and type is already instantiated
     * on this expedited report.
     *
     * @param rd the rd
     * @return true, if successful
     */
    public boolean hasExistingReportsOfSameOrganizationAndType(ReportDefinition rd){
    	for(Report report : getReports()){
    		if(report.isOfSameOrganizationAndType(rd)) return true;
    	}
    	return false;
    }
    
    
    /**
     * This method will return the AdverseEvent that is associated with this data collection, identified by ID.
     *
     * @param id the id
     * @return the adverse event
     */
    public AdverseEvent findAdverseEventById(Integer id){
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.getId().equals(id)) return ae;
    	}
    	return null;
    }

    /**
     * Will find the labs with matching ID or Term otherwise treat it as new lab.
     *
     */

    public Lab findLabsByIDTerm(Lab thatLab) {

       for( Lab l : getLabs()) {
           if ( ObjectUtils.equals(l.getId(), thatLab.getId()) ) return l; // Compare ID.
           if ( ObjectUtils.equals(l.getLabTerm().getTerm(), thatLab.getLabTerm().getTerm()) ) return l; // Compare Term.
       }

       return null;

    }

    /**
     * Will find the Adverse Event that has matching
     *  - externalId
     *  - term
     *  - dates
     * @param thatAe
     * @return
     */
    public AdverseEvent findAdverseEventByIdTermAndDates(AdverseEvent thatAe){
        for(AdverseEvent thisAe : getAdverseEvents()){
            //are Ids matching ?
            if(ObjectUtils.equals(thisAe.getId(), thatAe.getId())) return thisAe;
            if(ObjectUtils.equals(thisAe.getExternalId(), thatAe.getExternalId())) return thisAe;

            AbstractAdverseEventTerm thisTerm = thisAe.getAdverseEventTerm();
            AbstractAdverseEventTerm thatTerm = thatAe.getAdverseEventTerm();
            if(thisTerm != null && thatTerm != null)

            //are dates matching ?
            if(DateUtils.compareDate(thisAe.getStartDate(), thatAe.getStartDate()) != 0)  continue;
            if(DateUtils.compareDate(thisAe.getEndDate(), thatAe.getEndDate()) != 0)  continue;

            DomainObject thisAeTerm = thisAe.getAdverseEventTerm() != null ? thisAe.getAdverseEventTerm().getTerm() : null;
            DomainObject thatAeTerm = thatAe.getAdverseEventTerm() != null ? thatAe.getAdverseEventTerm().getTerm() :  null;
            if((thisAeTerm == null && thatAeTerm != null ) || (thisAeTerm != null && thatAeTerm == null)) continue;

            if(thisAeTerm == null && thatAeTerm == null){
                //verbatim only AE entry
                if(!StringUtils.equals(thisAe.getDetailsForOther(), thatAe.getDetailsForOther())) continue;
            }else {

                //check terms
                if(!ObjectUtils.equals(thisAeTerm.getId(), thatAeTerm.getId())) continue;

                if(thisAeTerm instanceof AdverseEventCtcTerm){
                    //check other specify
                    if(!StringUtils.equals(thisAe.getOtherSpecify(), thatAe.getOtherSpecify())) continue;

                    //check other MedDRA
                    Integer thisLltId = thisAe.getLowLevelTerm() != null ? thisAe.getLowLevelTerm().getId() : Integer.MIN_VALUE;
                    Integer thatLltId = thatAe.getLowLevelTerm() != null ? thatAe.getLowLevelTerm().getId() : Integer.MIN_VALUE;
                    if(!ObjectUtils.equals(thisLltId, thatLltId)) continue;

                }
            }

            //found a match
            return thisAe;
        }
        return null;
    }


    /**
     * This method will return the Report associated to this data collection, identified by ID.
     *
     * @param id the id
     * @return the report
     */
    public Report findReportById(Integer id){
    	for(Report report : getReports()){
    		if(report.getId().equals(id)) return report;
    	}
    	return null;
    }
    
    /**
     * This method returns is used to determine if there are any active reports which are in a workflow.
     *
     * @return boolean
     */
    public boolean hasWorkflowOnActiveReports(){
    	boolean hasWorkflowOnActiveReports = false;
    	for(Report r: getActiveReports())
    		if(r.getWorkflowId() != null)
    			hasWorkflowOnActiveReports = true;
    	return hasWorkflowOnActiveReports;
    }
    
    /**
     * Will create other cause from PreExistingCondition.
     */
    public void autoGenerateOtherCauses(){
        for(SAEReportPreExistingCondition saePreCondition : getSaeReportPreExistingConditions()){
            if(saePreCondition.getLinkedToOtherCause()) continue; //already linked to a cause. 
            String preConditionName = saePreCondition.getName();
            if(preConditionName == null) continue;
            OtherCause otherCause = findOtherCauseByCause(preConditionName);
            if(otherCause == null){
                addOtherCause(new OtherCause(preConditionName));
                saePreCondition.setLinkedToOtherCause(true);
            }
        }
    }

    /**
     * Will return the OtherCause matching the cause.
     * @param cause  - The cause to find. 
     * @return  OtherCause if found, otherwise null. 
     */
    public OtherCause findOtherCauseByCause(String cause){
        for(OtherCause otherCause : getOtherCauses()) {
            String otherCauseText = otherCause.getText();
            if(StringUtils.equals(cause, otherCauseText)) return otherCause;
        }
        return null;
    }

    public boolean removeOtherCause(String cause){
        OtherCause otherCause = findOtherCauseByCause(cause);
        if(otherCause != null) return getOtherCauses().remove(otherCause);
        return false;
    }
    
    @Transient
    public List<StudyTherapyType> getInterventionTypes(){
        List<StudyTherapyType> interventionTypes = new ArrayList<StudyTherapyType>();
        if(CollectionUtils.isNotEmpty(getRadiationInterventions())) interventionTypes.add(StudyTherapyType.RADIATION);
        if(CollectionUtils.isNotEmpty(getSurgeryInterventions())) interventionTypes.add(StudyTherapyType.SURGERY);
        if(CollectionUtils.isNotEmpty(getBehavioralInterventions())) interventionTypes.add(StudyTherapyType.BEHAVIORAL);
        if(CollectionUtils.isNotEmpty(getBiologicalInterventionsInternal())) interventionTypes.add(StudyTherapyType.BIOLOGICAL_VACCINE);
        if(CollectionUtils.isNotEmpty(getGeneticInterventions())) interventionTypes.add(StudyTherapyType.GENETIC);
        if(CollectionUtils.isNotEmpty(getDietaryInterventions())) interventionTypes.add(StudyTherapyType.DIETARY_SUPPLEMENT);
        if(CollectionUtils.isNotEmpty(getOtherAEInterventions())) interventionTypes.add(StudyTherapyType.OTHER);
        if(CollectionUtils.isNotEmpty(getMedicalDevices())) interventionTypes.add(StudyTherapyType.DEVICE);
        if(CollectionUtils.isNotEmpty(getTreatmentInformation().getCourseAgents())) interventionTypes.add(StudyTherapyType.DRUG_ADMINISTRATION);
        return interventionTypes;
    }

    @Transient
    public List<AdverseEventAttribution> getAdverseEventAttributions(){
        List<AdverseEventAttribution> attributions = new ArrayList<AdverseEventAttribution>();
        for(AdverseEvent ae: getAdverseEvents())   {
            attributions.addAll(ae.getAdverseEventAttributions());
        }
        return attributions;
    }
    
    @Transient
    public boolean getHasNciIndAgent(){
        if(getTreatmentInformation().getCourseAgents() != null){
           for(CourseAgent ca : getTreatmentInformation().getCourseAgents()){
               if(ca.getStudyAgent() != null && ca.getStudyAgent().getHasIndHeldByNci()) return true;
           }
        }
        return false;
    }

    @Transient
    public boolean getHasNciIdeDevice(){
        if(getMedicalDevices() != null) {
            for(MedicalDevice md : getMedicalDevices()){
                if(md.getStudyDevice() != null && md.getStudyDevice().getHasIdeHeldByNci()) return true;
            }
        }
        return false;
    }


    /**
     * When we delete an element which has been attributed, the attribution also needs to be deleted.
     * @param o
     */
    public boolean cascaeDeleteToAttributions(DomainObject o){
    	for(AdverseEvent ae : getAdverseEvents()){
            if (o instanceof RadiationIntervention) {
                ae.deleteAttribution(o, ae.getRadiationAttributions());
            } else if (o instanceof MedicalDevice) {
                ae.deleteAttribution(o, ae.getDeviceAttributions());
            } else if (o instanceof SurgeryIntervention) {
                ae.deleteAttribution(o, ae.getSurgeryAttributions());
            } else if (o instanceof CourseAgent) {
                ae.deleteAttribution(o, ae.getCourseAgentAttributions());
            } else if (o instanceof ConcomitantMedication) {
                ae.deleteAttribution(o, ae.getConcomitantMedicationAttributions());
            } else if (o instanceof OtherCause) {
                ae.deleteAttribution(o, ae.getOtherCauseAttributions());
            } else if (o instanceof DiseaseHistory) {
                ae.deleteAttribution(o, ae.getDiseaseAttributions());
            } else if (o instanceof OtherAEIntervention) {
                ae.deleteAttribution(o, ae.getOtherInterventionAttributions());
            } else if (o instanceof BehavioralIntervention) {
                ae.deleteAttribution(o, ae.getBehavioralInterventionAttributions());
            } else if (o instanceof BiologicalIntervention) {
                ae.deleteAttribution(o, ae.getBiologicalInterventionAttributions());
            } else if (o instanceof DietarySupplementIntervention) {
                ae.deleteAttribution(o, ae.getDietarySupplementInterventionAttributions());
            } else if (o instanceof GeneticIntervention) {
                ae.deleteAttribution(o, ae.getGeneticInterventionAttributions());
            }
        }
    	return true;
    }


    /**
     * Insert a new Attribution for the a new Object
     *
     * @param o
     * @return
     */
    public boolean addAttributionsToAEs(DomainObject o) {
        for (AdverseEvent ae : getAdverseEvents()) {
            if (o instanceof RadiationIntervention) {
                ae.addAttribution(new RadiationAttribution((RadiationIntervention) o), ae.getRadiationAttributions());
            } else if (o instanceof OtherAEIntervention) {
                ae.addAttribution(new OtherInterventionAttribution((OtherAEIntervention) o), ae.getOtherInterventionAttributions());
            } else if (o instanceof BehavioralIntervention) {
                ae.addAttribution(new BehavioralInterventionAttribution((BehavioralIntervention) o), ae.getBehavioralInterventionAttributions());
            } else if (o instanceof BiologicalIntervention) {
                ae.addAttribution(new BiologicalInterventionAttribution((BiologicalIntervention) o), ae.getBiologicalInterventionAttributions());
            } else if (o instanceof DietarySupplementIntervention) {
                ae.addAttribution(new DietarySupplementInterventionAttribution((DietarySupplementIntervention) o), ae.getDietarySupplementInterventionAttributions());
            } else if (o instanceof GeneticIntervention) {
                ae.addAttribution(new GeneticInterventionAttribution((GeneticIntervention) o), ae.getGeneticInterventionAttributions());
            } else if (o instanceof MedicalDevice) {
                ae.addAttribution(new DeviceAttribution((MedicalDevice) o), ae.getDeviceAttributions());
            } else if (o instanceof SurgeryIntervention) {
                ae.addAttribution(new SurgeryAttribution((SurgeryIntervention) o), ae.getSurgeryAttributions());
            } else if (o instanceof CourseAgent) {
                ae.addAttribution(new CourseAgentAttribution((CourseAgent) o), ae.getCourseAgentAttributions());
            } else if (o instanceof ConcomitantMedication) {
                ae.addAttribution(new ConcomitantMedicationAttribution((ConcomitantMedication) o), ae.getConcomitantMedicationAttributions());
            } else if (o instanceof OtherCause) {
                ae.addAttribution(new OtherCauseAttribution((OtherCause) o), ae.getOtherCauseAttributions());
            } else if (o instanceof DiseaseHistory) {
                ae.addAttribution(new DiseaseAttribution((DiseaseHistory) o), ae.getDiseaseAttributions());
            }
        }
        return true;
    }
}
