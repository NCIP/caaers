package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.AdverseEventGroup;
import gov.nih.nci.cabig.caaers.validation.fields.validators.NotNullConstraint;
import gov.nih.nci.cabig.caaers.validation.fields.validators.NumberRangeConstraint;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
/**
 * This class represents the Adverse Event domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 * @author Ion C. Olaru
 *
 */

@Entity
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_adverse_events_id")})
public class AdverseEvent extends AbstractMutableRetireableDomainObject implements ExpeditedAdverseEventReportChild , Serializable{
   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 782543033828114683L;

	/** The adverse event term. */
	private AbstractAdverseEventTerm adverseEventTerm;

    /** The details for other. */
    private String detailsForOther;

    /** The grade. */
    private Grade grade;

    /** The hospitalization. */
    private Hospitalization hospitalization;

    /** The expected. */
    private Boolean expected; // false assignment removed cause that is not default for RoutineAEs

    /** The attribution summary. */
    private Attribution attributionSummary;

    /** The comments. */
    private String comments;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The low level term. */
    private LowLevelTerm lowLevelTerm;

    /** The report. */
    private ExpeditedAdverseEventReport report;

    /** The course agent attributions. */
    private List<CourseAgentAttribution> courseAgentAttributions;

    /** The concomitant medication attributions. */
    private List<ConcomitantMedicationAttribution> concomitantMedicationAttributions;

    /** The other cause attributions. */
    private List<OtherCauseAttribution> otherCauseAttributions;

    /** The disease attributions. */
    private List<DiseaseAttribution> diseaseAttributions;

    /** The surgery attributions. */
    private List<SurgeryAttribution> surgeryAttributions;

    /** The radiation attributions. */
    private List<RadiationAttribution> radiationAttributions;

    /** The device attributions. */
    private List<DeviceAttribution> deviceAttributions;

    private List<OtherInterventionAttribution> otherInterventionAttributions;
    private List<BiologicalInterventionAttribution> biologicalInterventionAttributions;
    private List<BehavioralInterventionAttribution> behavioralInterventionAttributions;
    private List<GeneticInterventionAttribution> geneticInterventionAttributions;
    private List<DietarySupplementInterventionAttribution> dietarySupplementInterventionAttributions;

    /** The outcomes. */
    private List<Outcome> outcomes;

    /** The reporting period. */
    private AdverseEventReportingPeriod reportingPeriod;

    /** The solicited. */
    private Boolean solicited;

    /** The serious. */
    private OutcomeType serious;

    /** The requires reporting. */
    private Boolean requiresReporting;

    /** The display expected. */
    private String displayExpected;

    /** The event approximate time. */
    private TimeValue eventApproximateTime;

    /** The event location. */
    private String eventLocation;
    
    public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/** The graded date. */
    private Date gradedDate;
    
    /** The post submission updated date. */
    private Date postSubmissionUpdatedDate;
    
    /** The signature. */
    private String signature;
    
    /** The reported. */
    private Boolean reported;
    
    /** The participant at risk. */
    private Boolean participantAtRisk;
    
    private String externalId;
    
    /**
     * Instantiates a new adverse event.
     */
    public AdverseEvent() {
        solicited = false;
    }

    ///LOGIC
    /**
     * Adds the outcome.
     *
     * @param o the o
     */
    public void addOutcome(Outcome o) {
        this.getOutcomes().add(o);
    }
    
    /**
     * This method will clear off all the attributions, specified for this adverse event.
     */
    public void clearAttributions(){
    	if(courseAgentAttributions != null) courseAgentAttributions.clear();
    	if(concomitantMedicationAttributions != null) concomitantMedicationAttributions.clear();
    	if(otherCauseAttributions != null) otherCauseAttributions.clear();
    	if(diseaseAttributions != null) diseaseAttributions.clear();
    	if(surgeryAttributions != null) surgeryAttributions.clear();
    	if(radiationAttributions != null) radiationAttributions.clear();
    	if(deviceAttributions != null) deviceAttributions.clear();
    	if(otherInterventionAttributions != null) otherInterventionAttributions.clear();
    	if(biologicalInterventionAttributions != null) biologicalInterventionAttributions.clear();
    	if(behavioralInterventionAttributions != null) behavioralInterventionAttributions.clear();
    	if(geneticInterventionAttributions != null) geneticInterventionAttributions.clear();
    	if(dietarySupplementInterventionAttributions != null) dietarySupplementInterventionAttributions.clear();
    }

    // //// BOUND PROPERTIES
    // annotated to EAGER as LAZY was not working properly in oracle , 
    // dont make it LAZY , oracle is giving problems.
    /**
     * Gets the low level term.
     *
     * @return the low level term
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "low_level_term_id")
    public LowLevelTerm getLowLevelTerm() {
        return lowLevelTerm;
    }

    /**
     * Sets the low level term.
     *
     * @param lowLevelTerm the new low level term
     */
    public void setLowLevelTerm(LowLevelTerm lowLevelTerm) {
        this.lowLevelTerm = lowLevelTerm;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReportChild#getReport()
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = true)
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReportChild#setReport(gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport)
     */
    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }


    /**
     * Gets the reporting period.
     *
     * @return the reporting period
     */
    @ManyToOne
    @JoinColumn(name = "reporting_period_id", nullable = true)
    @Cascade(value = {CascadeType.LOCK, CascadeType.DETACH})
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

    /**
     * Gets the course agent attributions.
     *
     * @return the course agent attributions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'CA'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<CourseAgentAttribution> getCourseAgentAttributions() {
        if (courseAgentAttributions == null) courseAgentAttributions = new ArrayList<CourseAgentAttribution>();
        return courseAgentAttributions;
    }

    /**
     * Sets the course agent attributions.
     *
     * @param courseAgentAttributions the new course agent attributions
     */
    public void setCourseAgentAttributions(List<CourseAgentAttribution> courseAgentAttributions) {
        this.courseAgentAttributions = courseAgentAttributions;
    }

    /**
     * Gets the concomitant medication attributions.
     *
     * @return the concomitant medication attributions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'CM'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<ConcomitantMedicationAttribution> getConcomitantMedicationAttributions() {
        if (concomitantMedicationAttributions == null) {
            concomitantMedicationAttributions = new ArrayList<ConcomitantMedicationAttribution>();
        }
        return concomitantMedicationAttributions;
    }

    /**
     * Sets the concomitant medication attributions.
     *
     * @param concomitantMedicationAttributions the new concomitant medication attributions
     */
    public void setConcomitantMedicationAttributions(
            List<ConcomitantMedicationAttribution> concomitantMedicationAttributions) {
        this.concomitantMedicationAttributions = concomitantMedicationAttributions;
    }

    /**
     * Gets the other cause attributions.
     *
     * @return the other cause attributions
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'OC'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<OtherCauseAttribution> getOtherCauseAttributions() {
        if (otherCauseAttributions == null) {
            otherCauseAttributions = new ArrayList<OtherCauseAttribution>();
        }
        return otherCauseAttributions;
    }

    /**
     * Sets the other cause attributions.
     *
     * @param otherCauseAttributions the new other cause attributions
     */
    public void setOtherCauseAttributions(List<OtherCauseAttribution> otherCauseAttributions) {
        this.otherCauseAttributions = otherCauseAttributions;
    }

    /**
     * Gets the disease attributions.
     *
     * @return the disease attributions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'DH'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<DiseaseAttribution> getDiseaseAttributions() {
        if (diseaseAttributions == null) {
            diseaseAttributions = new ArrayList<DiseaseAttribution>();
        }
        return diseaseAttributions;
    }

    /**
     * Sets the disease attributions.
     *
     * @param diseaseAttributions the new disease attributions
     */
    public void setDiseaseAttributions(List<DiseaseAttribution> diseaseAttributions) {
        this.diseaseAttributions = diseaseAttributions;
    }

    /**
     * Gets the surgery attributions.
     *
     * @return the surgery attributions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'SI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<SurgeryAttribution> getSurgeryAttributions() {
        if (surgeryAttributions == null) {
            surgeryAttributions = new ArrayList<SurgeryAttribution>();
        }
        return surgeryAttributions;
    }

    /**
     * Sets the surgery attributions.
     *
     * @param surgeryAttributions the new surgery attributions
     */
    public void setSurgeryAttributions(List<SurgeryAttribution> surgeryAttributions) {
        this.surgeryAttributions = surgeryAttributions;
    }

    /**
     * Gets the radiation attributions.
     *
     * @return the radiation attributions
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'RI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<RadiationAttribution> getRadiationAttributions() {
        if (radiationAttributions == null) {
            radiationAttributions = new ArrayList<RadiationAttribution>();
        }
        return radiationAttributions;
    }

    /**
     * Sets the radiation attributions.
     *
     * @param radiationAttributions the new radiation attributions
     */
    public void setRadiationAttributions(List<RadiationAttribution> radiationAttributions) {
        this.radiationAttributions = radiationAttributions;
    }

    /**
     * Gets the device attributions.
     *
     * @return the device attributions
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'DV'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<DeviceAttribution> getDeviceAttributions() {
        if (deviceAttributions == null) {
            deviceAttributions = new ArrayList<DeviceAttribution>();
        }
        return deviceAttributions;
    }

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'OI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<OtherInterventionAttribution> getOtherInterventionAttributions() {
        if (otherInterventionAttributions == null) {
            otherInterventionAttributions = new ArrayList<OtherInterventionAttribution>();
        }
        return otherInterventionAttributions;
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'BI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<BiologicalInterventionAttribution> getBiologicalInterventionAttributions() {
        if (biologicalInterventionAttributions == null) {
            biologicalInterventionAttributions = new ArrayList<BiologicalInterventionAttribution>();
        }
        return biologicalInterventionAttributions;
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'HI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<BehavioralInterventionAttribution> getBehavioralInterventionAttributions() {
        if (behavioralInterventionAttributions == null) {
            behavioralInterventionAttributions = new ArrayList<BehavioralInterventionAttribution>();
        }
        return behavioralInterventionAttributions;
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'GI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<GeneticInterventionAttribution> getGeneticInterventionAttributions() {
        if (geneticInterventionAttributions == null) {
            geneticInterventionAttributions = new ArrayList<GeneticInterventionAttribution>();
        }
        return geneticInterventionAttributions;
    }

    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL})
    @Where(clause = "cause_type = 'DI'")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    // it is pretty lame that this is necessary
    public List<DietarySupplementInterventionAttribution> getDietarySupplementInterventionAttributions() {
        if (dietarySupplementInterventionAttributions == null) {
            dietarySupplementInterventionAttributions = new ArrayList<DietarySupplementInterventionAttribution>();
        }
        return dietarySupplementInterventionAttributions;
    }

    /**
     * Sets the device attributions.
     *
     * @param deviceAttributions the new device attributions
     */
    public void setDeviceAttributions(List<DeviceAttribution> deviceAttributions) {
        this.deviceAttributions = deviceAttributions;
    }

    public void setOtherInterventionAttributions(List<OtherInterventionAttribution> otherInterventionAttributions) {
        this.otherInterventionAttributions = otherInterventionAttributions;
    }

    public void setBiologicalInterventionAttributions(List<BiologicalInterventionAttribution> biologicalInterventionAttributions) {
        this.biologicalInterventionAttributions = biologicalInterventionAttributions;
    }

    public void setBehavioralInterventionAttributions(List<BehavioralInterventionAttribution> behavioralInterventionAttributions) {
        this.behavioralInterventionAttributions = behavioralInterventionAttributions;
    }

    public void setGeneticInterventionAttributions(List<GeneticInterventionAttribution> geneticInterventionAttributions) {
        this.geneticInterventionAttributions = geneticInterventionAttributions;
    }

    public void setDietarySupplementInterventionAttributions(List<DietarySupplementInterventionAttribution> dietarySupplementInterventionAttributions) {
        this.dietarySupplementInterventionAttributions = dietarySupplementInterventionAttributions;
    }

    /**
     * Gets the ctc term.
     *
     * @return the ctc term
     */
    @Deprecated
    @Transient
    public CtcTerm getCtcTerm() {
        if (this.adverseEventTerm == null) {
            return null;
        } else if (this.adverseEventTerm instanceof AdverseEventCtcTerm) {
            return getAdverseEventCtcTerm().getCtcTerm();
        } else {
            throw new CaaersSystemException("Cannot Return a Ctc Term you are probably using a Terminology different than Ctc");
        }
    }

    /**
     * Sets the ctc term.
     *
     * @param ctcTerm the new ctc term
     */
    @Deprecated
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    }

    /**
     * Gets the meddra term.
     *
     * @return the meddra term
     */
    @Transient
    public LowLevelTerm getMeddraTerm() {
        if (this.adverseEventTerm == null) {
            return null;
        } else if (this.adverseEventTerm instanceof AdverseEventMeddraLowLevelTerm) {
            return getAdverseEventMeddraLowLevelTerm().getLowLevelTerm();
        } else {
            throw new CaaersSystemException("Cannot Return a Meddra Term you are probably using a Terminology different than Ctc");
        }
    }

    /**
     * Sets the meddra term.
     *
     * @param meddraTerm the new meddra term
     */
    @Transient
    public void setMeddraTerm(LowLevelTerm meddraTerm) {
        getAdverseEventMeddraLowLevelTerm().setLowLevelTerm(meddraTerm);
    }

    /**
     * Gets the adverse event ctc term.
     *
     * @return the adverse event ctc term
     */
    @Transient
    public AdverseEventCtcTerm getAdverseEventCtcTerm() {
        if (adverseEventTerm == null) {
            this.adverseEventTerm = new AdverseEventCtcTerm();
            adverseEventTerm.setAdverseEvent(this);
        } else if (!adverseEventTerm.getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm")) {
            return new AdverseEventCtcTerm();
        }

        return (AdverseEventCtcTerm) adverseEventTerm;
    }

    /**
     * Sets the adverse event ctc term.
     *
     * @param adverseEventCtcTerm the new adverse event ctc term
     */
    @Transient
    public void setAdverseEventCtcTerm(AdverseEventCtcTerm adverseEventCtcTerm) {
        // this.adverseEventCtcTerm = adverseEventCtcTerm;
        setAdverseEventTerm(adverseEventCtcTerm);
    }

    /**
     * Gets the adverse event meddra low level term.
     *
     * @return the adverse event meddra low level term
     */
    @Transient
    public AdverseEventMeddraLowLevelTerm getAdverseEventMeddraLowLevelTerm() {
        if (adverseEventTerm == null) {
            this.adverseEventTerm = new AdverseEventMeddraLowLevelTerm();
            adverseEventTerm.setAdverseEvent(this);
        } else if (!adverseEventTerm.getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
            return new AdverseEventMeddraLowLevelTerm();
        }
        return (AdverseEventMeddraLowLevelTerm) adverseEventTerm;
    }

    /**
     * Sets the adverse event meddra low level term.
     *
     * @param adverseEventMeddraLowLevelTerm the new adverse event meddra low level term
     */
    @Transient
    public void setAdverseEventMeddraLowLevelTerm(AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm) {
        // this.adverseEventCtcTerm = adverseEventCtcTerm;
        setAdverseEventTerm(adverseEventMeddraLowLevelTerm);
    }

    /**
     * Gets the adverse event term.
     *
     * @return the adverse event term
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].adverseEventCtcTerm")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adverseEvent", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public AbstractAdverseEventTerm getAdverseEventTerm() {
        return adverseEventTerm;
    }

    /**
     * Sets the adverse event term.
     *
     * @param adverseEventTerm the new adverse event term
     */
    public void setAdverseEventTerm(AbstractAdverseEventTerm adverseEventTerm) {
        this.adverseEventTerm = adverseEventTerm;
    }

    /**
     * Gets the details for other.
     *
     * @return the details for other
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].detailsForOther")
    public String getDetailsForOther() {
        return detailsForOther;
    }

    /**
     * Sets the details for other.
     *
     * @param detailsForOther the new details for other
     */
    public void setDetailsForOther(String detailsForOther) {
        this.detailsForOther = detailsForOther;
    }

    /**
     * Gets the grade.
     *
     * @return the grade
     */
    @Type(type = "grade")
    @Column(name = "grade_code")
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].grade")
    @NumberRangeConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].grade",begin=1, end=5)
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets the grade.
     *
     * @param grade the new grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].startDate")
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Gets the start date as string.
     *
     * @return the start date as string
     */
    @Transient
    public String getStartDateAsString(){
    	try {
			return DateUtils.formatDate(startDate);
		} catch (Exception e) {
			return null;
		}
    }
    
    /**
     * Sets the start date as string.
     *
     * @param startDate the new start date as string
     */
    @Transient
    public void setStartDateAsString(String startDate) {
    }
    

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].endDate")
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the hospitalization.
     *
     * @return the hospitalization
     */
    @Type(type = "hospitalization")
    @Column(name = "hospitalization_code")
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].hospitalization")
    public Hospitalization getHospitalization() {
        return hospitalization;
    }

    /**
     * Sets the hospitalization.
     *
     * @param hospitalization the new hospitalization
     */
    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;
    }

    /**
     * Gets the expected.
     *
     * @return the expected
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].expected")
    public Boolean getExpected() {
        return expected;
    }

    /**
     * Sets the expected.
     *
     * @param expected the new expected
     */
    public void setExpected(Boolean expected) {
        this.expected = expected;
    }

    /**
     * This function is a purely fabricated one, which is used by Rules, to identify the expectedness.
     *
     * @return FALSE if null. Othwise the getExpected().
     */
    @Transient
    public Boolean getExpectedness() {
        if (expected == null) return Boolean.FALSE;
        return expected;
    }

    /**
     * Gets the attribution summary.
     *
     * @return the attribution summary
     */
    @Type(type = "attribution")
    @Column(name = "attribution_summary_code")
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].attributionSummary")
    public Attribution getAttributionSummary() {
        return attributionSummary;
    }

    /**
     * Sets the attribution summary.
     *
     * @param attributionSummary the new attribution summary
     */
    public void setAttributionSummary(Attribution attributionSummary) {
        this.attributionSummary = attributionSummary;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].comments")
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * Will return all the attributions.
     *
     * @return the adverse event attributions
     */
    @Transient
    public List<AdverseEventAttribution<?>> getAdverseEventAttributions() {
        List<AdverseEventAttribution<?>> attributions = new LinkedList<AdverseEventAttribution<?>>();
        attributions.addAll(getDiseaseAttributions());
        attributions.addAll(getConcomitantMedicationAttributions());
        attributions.addAll(getCourseAgentAttributions());
        attributions.addAll(getOtherCauseAttributions());
        attributions.addAll(getRadiationAttributions());
        attributions.addAll(getSurgeryAttributions());
        attributions.addAll(getDeviceAttributions());
        attributions.addAll(getOtherInterventionAttributions());
        attributions.addAll(getBiologicalInterventionAttributions());
        attributions.addAll(getBehavioralInterventionAttributions());
        attributions.addAll(getGeneticInterventionAttributions());
        attributions.addAll(getDietarySupplementInterventionAttributions());
        return attributions;
    }

    /**
     * This method will tell if the specified attributions exists.
     *
     * @param attributions the attributions
     * @return true, if is attributed with
     */
    @Transient
    public boolean isAttributedWith(Attribution... attributions) {
        return containsAttribution(getAdverseEventAttributions(), attributions);
    }

    /**
     * Contains attribution.
     *
     * @param attributionList the attribution list
     * @param attributionsToFind the attributions
     * @return true, if successful
     */
    private  boolean containsAttribution(List<? extends AdverseEventAttribution<? extends DomainObject>> attributionList,
            Attribution... attributionsToFind) {
        if (attributionList == null || attributionList.isEmpty()) return false;
        for (AdverseEventAttribution<? extends DomainObject> aea : attributionList) {
        	if(aea.getAttribution() == null) return false;
            for (Attribution att : attributionsToFind) {
                if (aea.isAttributedWith(att)) return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if is all course agent attribution provided.
     *
     * @return true, if is all course agent attribution provided
     */
    @Transient
    public boolean isAllCourseAgentAttributionProvided(){
    	if(courseAgentAttributions == null || courseAgentAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : courseAgentAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    
    /**
     * Checks if is all con med attribution provided.
     *
     * @return true, if is all con med attribution provided
     */
    @Transient
    public boolean isAllConMedAttributionProvided(){
    	if(concomitantMedicationAttributions == null || concomitantMedicationAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : concomitantMedicationAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if is all other cause attribution provided.
     *
     * @return true, if is all other cause attribution provided
     */
    @Transient
    public boolean isAllOtherCauseAttributionProvided(){
    	if(otherCauseAttributions == null || otherCauseAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : otherCauseAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if is all device attribution provided.
     *
     * @return true, if is all device attribution provided
     */
    @Transient
    public boolean isAllDeviceAttributionProvided(){
    	if(deviceAttributions == null || deviceAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : deviceAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if is all surgery attribution provided.
     *
     * @return true, if is all surgery attribution provided
     */
    @Transient
    public boolean isAllSurgeryAttributionProvided(){
    	if(surgeryAttributions == null || surgeryAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : surgeryAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if is all radiation attribution provided.
     *
     * @return true, if is all radiation attribution provided
     */
    @Transient
    public boolean isAllRadiationAttributionProvided(){
    	if(radiationAttributions == null || radiationAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : radiationAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;
    }
    
    /**
     * Checks if is all disease attribution provided.
     *
     * @return true, if is all disease attribution provided
     */
    @Transient
    public boolean isAllDiseaseAttributionProvided(){
    	if(diseaseAttributions == null || diseaseAttributions.isEmpty()) return false;
    	for(AdverseEventAttribution<? extends DomainObject> aea : diseaseAttributions){
    		if(aea.getAttribution() == null) return false;
    	}
    	return true;

    }

    /**
     * Gets the display grade.
     *
     * @return the display grade
     */
    @Transient
    public String getDisplayGrade() {
        if (grade == null) return "";
        if (adverseEventTerm.getTerm() == null) return (grade.getCode() + ": " + grade.getName());
        
        //MedDRA or CTC{not evaluated , normal}
        if (grade.getCode() <= 0 || ( adverseEventTerm != null && adverseEventTerm.isMedDRA()))
            return grade.getCode().intValue() + ":  " + grade.getDisplayName();

        //CTC ( > Normal), so check contextual grades
        List<CtcGrade> contextualGrades = this.getAdverseEventCtcTerm().getTerm().getContextualGrades();
        if (contextualGrades.isEmpty()) return grade.getCode().intValue() + ":  " + grade.getDisplayName();

        //find the grade from contextual grades and return that.
        for (CtcGrade contextualGrade : contextualGrades) {
            if (contextualGrade.getCode().equals(grade.getCode()))
                return grade.getCode().intValue() + ":  " + contextualGrade.getDisplayName();
        }

        return "";
    }

    /**
     * Gets the display expected.
     *
     * @return the display expected
     */
    @Transient
    public String getDisplayExpected() {
        displayExpected = "";
        if (expected != null)
            displayExpected = expected.equals(Boolean.TRUE) ? "Yes" : "No";
        return displayExpected;
    }

    /**
     * Sets the display expected.
     */
    public void setDisplayExpected() {
        this.displayExpected = displayExpected;
    }

    /**
     * Gets the display serious.
     *
     * @return the display serious
     */
    @Transient
    public String getDisplaySerious() {
        if (serious != null) return serious.getDisplayName();
        return "";
    }

    /**
     * Sets the display serious.
     *
     * @param igonre the new display serious
     */
    public void setDisplaySerious(String igonre) {

    }

    /**
     * Sets the solicited.
     *
     * @param solicited the new solicited
     */
    public void setSolicited(Boolean solicited) {
        this.solicited = solicited;
    }
    
    /**
     * This method will return the display name of this adverse event.
     *
     * @return the display name
     */
    @Transient
    public String getDisplayName(){
    	StringBuilder name = new StringBuilder(this.getAdverseEventCtcTerm().getFullName());
    	if(this.getAdverseEventCtcTerm().isOtherRequired()){
    		if(this.getLowLevelTerm() != null) {
    			name.append(", " + this.getLowLevelTerm().getMeddraTerm());
    		}else if(this.getDetailsForOther() != null){
    			name.append(", " + this.getDetailsForOther());
    		}
    	}
    	return name.toString();
    }

    /**
     * Gets the solicited.
     *
     * @return the solicited
     */
    public Boolean getSolicited() {
        return solicited;
    }

    /**
     * Gets the requires reporting.
     *
     * @return the requires reporting
     */
    public Boolean getRequiresReporting() {
        return requiresReporting;
    }

    /**
     * Sets the requires reporting.
     *
     * @param requiresReporting the new requires reporting
     */
    public void setRequiresReporting(Boolean requiresReporting) {
        this.requiresReporting = requiresReporting;
    }


    /**
     * Gets the event approximate time.
     *
     * @return the event approximate time
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "hour", column = @Column(name = "event_time_hour")),
            @AttributeOverride(name = "minute", column = @Column(name = "event_time_minute")),
            @AttributeOverride(name = "type", column = @Column(name = "event_time_zone"))
    })
    public TimeValue getEventApproximateTime() {
        if (eventApproximateTime == null) eventApproximateTime = new TimeValue();
        return eventApproximateTime;
    }

    /**
     * Sets the event approximate time.
     *
     * @param eventApproximateTime the new event approximate time
     */
    public void setEventApproximateTime(TimeValue eventApproximateTime) {
        this.eventApproximateTime = eventApproximateTime;
    }

    /**
     * Gets the event location.
     *
     * @return the event location
     */
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].eventLocation")
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Sets the event location.
     *
     * @param eventLocation the new event location
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the outcomes.
     *
     * @return the outcomes
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "adverse_event_id", nullable=false)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    @NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].outcomes")
    public List<Outcome> getOutcomes() {
        if (outcomes == null) outcomes = new ArrayList<Outcome>();
        return outcomes;
    }

    /**
     * Sets the outcomes.
     *
     * @param outcomes the new outcomes
     */
    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    /**
     * Gets the serious.
     *
     * @return the serious
     */
    @Transient
    public OutcomeType getSerious() {
        return serious;
    }

    /**
     * Sets the serious.
     *
     * @param serious the new serious
     */
    public void setSerious(OutcomeType serious) {
        this.serious = serious;
    }
    
    
    /**
     * Gets the graded date.
     *
     * @return the graded date
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getGradedDate() {
		return gradedDate;
	}

	/**
	 * Sets the graded date.
	 *
	 * @param gradedDate the new graded date
	 */
	public void setGradedDate(Date gradedDate) {
		this.gradedDate = gradedDate;
	}
	
	/**
	 * Gets the post submission updated date.
	 *
	 * @return the post submission updated date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPostSubmissionUpdatedDate() {
		return postSubmissionUpdatedDate;
	}
	
	/**
	 * Sets the post submission updated date.
	 *
	 * @param postSubmissionUpdatedDate the new post submission updated date
	 */
	public void setPostSubmissionUpdatedDate(Date postSubmissionUpdatedDate) {
		this.postSubmissionUpdatedDate = postSubmissionUpdatedDate;
	}
	
	/**
	 * This method will set the graded, date if gradedDate is empty, and grade is set, and is not {@link Grade#NOT_EVALUATED} or.
	 *
	 * {@link Grade#NORMAL}.
	 */
	public void initailzeGradedDate(){
		if(gradedDate == null && this.isPopulated()){
			gradedDate = new Date();
		}
	}
	
	/**
	 * Initialize post submission updated date.
	 */
	public void initializePostSubmissionUpdatedDate(){
		if(postSubmissionUpdatedDate == null && grade != null && grade.getCode() > Grade.NORMAL.getCode() && isModified() ){
			postSubmissionUpdatedDate = new Date();
		}
	}

	/**
	 * Copy.
	 *
	 * @return the adverse event
	 */
	public AdverseEvent copy() {
        AdverseEvent adverseEvent = new AdverseEvent();
        org.springframework.beans.BeanUtils.copyProperties(this, adverseEvent,
                new String[]{"id", "gridId", "outcomes", "version", "report",
                        "deviceAttributions", "biologicalInterventionAttributions", "behavioralInterventionAttributions", "geneticInterventionAttributions", "dietarySupplementInterventionAttributions", "otherInterventionAttributions", "otherCauseAttributions", "courseAgentAttributions", "diseaseAttributions" ,
                        "surgeryAttributions", "concomitantMedicationAttributions", "radiationAttributions",
                        "adverseEventTerm", "adverseEventCtcTerm", "adverseEventMeddraLowLevelTerm", "ctcTerm", "startDateAsString", "meddraTerm"});

        //outcomes object must not be same. i.e. they should refer to different objects;
        for (Outcome outcome : getOutcomes()) {
            adverseEvent.addOutcome(outcome.copy());
        }

        for (AdverseEventAttribution adverseEventAttribution : getAdverseEventAttributions()) {
            adverseEvent.addAdverseEventAttribution(adverseEventAttribution.copy());
        }
        if (getAdverseEventTerm() != null) {
            AbstractAdverseEventTerm copiedAbstractAdverseEventTerm = getAdverseEventTerm().copy();
            copiedAbstractAdverseEventTerm.setAdverseEvent(adverseEvent);
            adverseEvent.setAdverseEventTerm(copiedAbstractAdverseEventTerm);
        }

        return adverseEvent;
    }


    /**
     * Adds the adverse event attribution.
     *
     * @param adverseEventAttribution the adverse event attribution
     */
    public void addAdverseEventAttribution(AdverseEventAttribution adverseEventAttribution) {
        if (adverseEventAttribution != null) {

            adverseEventAttribution.setAdverseEvent(this);
            if (adverseEventAttribution instanceof RadiationAttribution) {
                getRadiationAttributions().add((RadiationAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof DeviceAttribution) {
                getDeviceAttributions().add((DeviceAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof SurgeryAttribution) {
                getSurgeryAttributions().add((SurgeryAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof OtherCauseAttribution) {
                getOtherCauseAttributions().add((OtherCauseAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof ConcomitantMedicationAttribution) {
                getConcomitantMedicationAttributions().add((ConcomitantMedicationAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof DiseaseAttribution) {
                getDiseaseAttributions().add((DiseaseAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof CourseAgentAttribution) {
                getCourseAgentAttributions().add((CourseAgentAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof OtherInterventionAttribution) {
                getOtherInterventionAttributions().add((OtherInterventionAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof BehavioralInterventionAttribution) {
                getBehavioralInterventionAttributions().add((BehavioralInterventionAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof BiologicalInterventionAttribution) {
                getBiologicalInterventionAttributions().add((BiologicalInterventionAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof GeneticInterventionAttribution) {
                getGeneticInterventionAttributions().add((GeneticInterventionAttribution) adverseEventAttribution);
            } else if (adverseEventAttribution instanceof DietarySupplementInterventionAttribution) {
                getDietarySupplementInterventionAttributions().add((DietarySupplementInterventionAttribution) adverseEventAttribution);
            }
        }
    }
    
    /**
     * This method returns true if any of the reports associated to the data-collection this ae
     * belongs to was submitted successfully.
     * @return Boolean
     */
    @Transient
    public Boolean getSubmitted(){
    	Boolean submitted = false;
    	if(report != null && report.getHasSubmittedAmendableReport())
    		submitted = true;
    	return submitted;
    }
    
    /**
     * This method returns true, if this AE is part of a submitted Amendable report.
     *
     * @return true, if is submitted on an amendable report
     */
    @Transient
    public boolean isSubmittedOnAnAmendableReport(){
    	if(report == null) return false;
    	return report.getHasSubmittedAmendableReport();
    }
    
    /**
     * This method generates a signature for the AE. It uses the values of all the attributes of AEs that can be accessed and modified
     * on the captureAE page. It appends all these values in a specific order (so that it can be compared to the signature generated on
     * the client side and then saved in the html as a hidden field.
     * @return String
     */
    @Transient
    public String getCurrentSignature(){
    	StringBuffer sb = new StringBuffer("");

        // verbatim value
    	sb.append(detailsForOther == null ? "" : detailsForOther.toString());
    	sb.append("$$");
    	
    	// grade value
    	sb.append(grade == null ? "" : grade.getName()); // grade value
    	sb.append("$$");
    	
    	// attribution value
    	sb.append(attributionSummary == null ? "" : attributionSummary.getName());
    	sb.append("$$");
    	
    	// hospitalization value
    	if(hospitalization != null)
    		sb.append(hospitalization.getName().equals("NONE") ? "" : hospitalization.getName());
    	sb.append("$$");
    	
    	// expected
    	sb.append(expected == null ? "" : expected);
    	sb.append("$$");
    	
    	if(!getSolicited() && getAdverseEventCtcTerm() != null && getAdverseEventCtcTerm().getTerm() != null){
    		if(getAdverseEventCtcTerm().getTerm().isOtherRequired()){
    			sb.append(lowLevelTerm == null ? "" : lowLevelTerm.getFullName());
    		}
    	}
    	sb.append("$$");
        
    	// risk CAAERS-3281
    	sb.append(participantAtRisk == null ? "" : participantAtRisk);
    	sb.append("$$");
    	
    	//startdate
    	sb.append(startDate == null ? "" : DateUtils.formatDate(startDate)).append("$$");
    	
    	//enddate
    	sb.append(endDate == null ? "" : DateUtils.formatDate(endDate)).append("$$");
    	
    	//eventTime
    	if(eventApproximateTime == null){
    		sb.append("$$$$"); //for hour and minute
    	}else{
    		sb.append(eventApproximateTime.getHour() == null ? "" : eventApproximateTime.getHourString()).append("$$");
    		sb.append(eventApproximateTime.getMinute() == null ? "" : eventApproximateTime.getMinuteString()).append("$$");
    	}
    	    	
    	//eventLocation
    	sb.append(eventLocation == null ? "" : eventLocation ).append("$$");
    	
    	//retired indicator
    	if (isRetired()){
    		sb.append("deleted");
    	}
        sb.append("$$");
    	
        sb.append(getOutcomesSignature()).append("$$");    	

        return sb.toString();
    }

    /*
    *
    * @author Ion C. Olaru
    * Rerurn the value of the field from the signature
    * @param field - field we look fo
    * @param signature - the AE siganture
    * @return value of the field or empty string if not found
    * 
    * */
    /**
     * Signature field value.
     *
     * @param field the field
     * @param signature the signature
     * @return the string
     */
    @Transient
    public String signatureFieldValue(String field, String signature) {
        String[] s = signature.split("(\\$\\$){1}");

        if (s.length == 0) return "";

        if (field.equals("grade")) {
            if (s.length < 2) return ""; else return s[1];
        }

        if (field.equals("attributionSummary")) {
            if (s.length < 3) return ""; else return s[2];
        }

        if (field.equals("hospitalization")) {
            if (s.length < 4) return ""; else return s[3];
        }

        if (field.equals("expectedness")) {
            if (s.length < 5) return ""; else return s[4];
        }

        if (field.equals("participantAtRisk")) {
            if (s.length < 7) return ""; else return s[6];
        }

        if (field.equals("outcomeIdentifier")) {
            if (s.length < 14) return ""; else return s[13];
        }

        return "";
    }

    /*
    *
    * @author Ion C. Olaru
    * Rerurn true if any of the ruleable fields has a modified value
    * @param fields - list of ruleable fields
    *  
    * */
    /**
     * Checks if is ruleable fields modified.
     *
     * @param fields the fields
     * @return true, if is ruleable fields modified
     */
    @Transient
    public boolean isRuleableFieldsModified(List<String> fields) {
        String currentSignature = getCurrentSignature();
        for (String f : fields) {
            if (!signatureFieldValue(f, signature).equals(signatureFieldValue(f, currentSignature))) return true;
        }
        return false;
    }

    /**
     * Gets the signature.
     *
     * @return the signature
     */
    public String getSignature() {
		return signature;
	}

	/**
	 * Sets the signature.
	 *
	 * @param signature the new signature
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	/**
	 * Gets the reported.
	 *
	 * @return the reported
	 */
	public Boolean getReported() {
		return reported;
	}
	
	/**
	 * Sets the reported.
	 *
	 * @param reported the new reported
	 */
	public void setReported(Boolean reported) {
		this.reported = reported;
	}
	
	/**
	 * Checks whether the signature persisted is different than the one newly calculated.
	 *
	 * @return true, if is modified
	 */
	@Transient
	public boolean isModified(){
		return !StringUtils.equals(signature, getCurrentSignature());
	}
	
	/**
	 * This method checks whether the attributes of adverse events that can be involved in a rule are populated
	 * with some value. These attributes are grade, expected, attribution, participantAtRisk, hospitalization
	 * and outcomes. If the grade is not null or Not-evaluated or normal its considered to be populated. On a similar
	 * note for hospitalization to be considered as populated it shouldnt be null or NONE.
	 * @return boolean
	 */
	@Transient
	public boolean isPopulated(){
		if(this.isRetired())
			return false;
		// Check for grade
		if(this.getGrade() != null && !this.getGrade().equals(Grade.NOT_EVALUATED) && !this.getGrade().equals(Grade.NORMAL)){
			return true;
		}
		
		// Check for hospitalization (or prolonged hospitalization
		if(this.getHospitalization() != null && !this.getHospitalization().equals(Hospitalization.NONE)){
			return true;
		}
		
		// Check for expected
		if(this.getExpected() != null){
			return true;
		}
		
		// Check for participant at risk
		if(this.getParticipantAtRisk() != null){
			return true;
		}
		
		// Check for attribution
		if(this.getAttributionSummary() != null){
			return true;
		}
		
		// Check for outcome identifier
		if(this.getOutcomes() != null && !this.getOutcomes().isEmpty()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method will list the names of all active reports that are associated to this expeidted data collection.
	 *
	 * @return the associated report names
	 */
	@Transient
	public List<String> getAssociatedReportNames(){
		List<String> reportNames = new ArrayList<String>();
		if(report != null){
			for(Report r: report.getActiveReports()){
				reportNames.add(r.getName());
			}
		}
		return reportNames;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("{")
    	.append("id :" + String.valueOf(getId()))
    	.append(", Grade : " + String.valueOf(grade))
    	.append(", Hospitalization: " + String.valueOf(hospitalization))
    	.append(", attribution : " + String.valueOf(attributionSummary))
    	.append(", expected : " + String.valueOf(expected))
    	.append("}");
    	return sb.toString();
    }
    
    public static String toReadableString(AdverseEvent ae){
        if(ae == null) return "";
        StringBuilder sb = new StringBuilder();
        if(ae.getId() != null) sb.append(" ID : ").append(ae.getId())  ;
        if(ae.getAdverseEventTerm() != null) sb.append(" Term : ").append(ae.getAdverseEventTerm().getFullName());
        if(ae.getGrade() != null)sb.append(" Grade : ").append(ae.getGrade().name());
        if(ae.getStartDate() != null) sb.append(" Start date : ").append(ae.getStartDateAsString()) ;
        return sb.toString();
    }

	/**
	 * Sets the participant at risk.
	 *
	 * @param participantAtRisk the new participant at risk
	 */
	public void setParticipantAtRisk(Boolean participantAtRisk) {
		this.participantAtRisk = participantAtRisk;
	}

	/**
	 * Gets the participant at risk.
	 *
	 * @return the participant at risk
	 */
	@NotNullConstraint(groups=AdverseEventGroup.class, fieldPath="adverseEvents[].participantAtRisk")
	public Boolean getParticipantAtRisk() {
		return participantAtRisk;
	}

    /**
     * Gets the outcomes signature.
     *
     * @return the outcomes signature
     */
    @Transient
    public String getOutcomesSignature() {
        StringBuffer sb = new StringBuffer("");
        for (Outcome o : getOutcomes()) {
            sb.append(o.getOutcomeType().getName());
        }
        return sb.toString();
    }

}
