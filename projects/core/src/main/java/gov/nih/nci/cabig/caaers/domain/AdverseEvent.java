package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the Adverse Event domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 */

@Entity
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_adverse_events_id")})
public class AdverseEvent extends AbstractMutableDomainObject implements
        ExpeditedAdverseEventReportChild, RoutineAdverseEventReportChild {
    private AbstractAdverseEventTerm adverseEventTerm;

    private String detailsForOther;

    private Grade grade;

    private Hospitalization hospitalization;

    private Boolean expected; // false assignment removed cause that is not default for RoutineAEs

    private Attribution attributionSummary;

    private String comments;

    private Date startDate;

    private Date endDate;

    private LowLevelTerm lowLevelTerm;

    private ExpeditedAdverseEventReport report;

    private RoutineAdverseEventReport routineReport;

    private List<CourseAgentAttribution> courseAgentAttributions;

    private List<ConcomitantMedicationAttribution> concomitantMedicationAttributions;

    private List<OtherCauseAttribution> otherCauseAttributions;

    private List<DiseaseAttribution> diseaseAttributions;

    private List<SurgeryAttribution> surgeryAttributions;

    private List<RadiationAttribution> radiationAttributions;

    private List<DeviceAttribution> deviceAttributions;

    private List<Outcome> outcomes;

    private AdverseEventReportingPeriod reportingPeriod;

    private Boolean solicited;

    private OutcomeType serious;

    private Boolean requiresReporting;

    private String displayExpected;

    private TimeValue eventApproximateTime;

    private String eventLocation;
    
    public AdverseEvent() {
        solicited = false;
    }

    ///LOGIC
    public void addOutcome(Outcome o) {
        this.getOutcomes().add(o);
    }

    // //// BOUND PROPERTIES

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "low_level_term_id")
    public LowLevelTerm getLowLevelTerm() {
        return lowLevelTerm;
    }

    public void setLowLevelTerm(LowLevelTerm lowLevelTerm) {
        this.lowLevelTerm = lowLevelTerm;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = true)
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, nullable = true)
    public RoutineAdverseEventReport getRoutineReport() {
        return routineReport;
    }

    public void setRoutineReport(RoutineAdverseEventReport routineReport) {
        this.routineReport = routineReport;
    }

    @ManyToOne
    @JoinColumn(name = "reporting_period_id", nullable = true)
    @Cascade(value = {CascadeType.LOCK, CascadeType.EVICT})
    public AdverseEventReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'CA'")
    // it is pretty lame that this is necessary
    public List<CourseAgentAttribution> getCourseAgentAttributions() {
        if (courseAgentAttributions == null) courseAgentAttributions = new ArrayList<CourseAgentAttribution>();
        return courseAgentAttributions;
    }

    public void setCourseAgentAttributions(List<CourseAgentAttribution> courseAgentAttributions) {
        this.courseAgentAttributions = courseAgentAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'CM'")
    // it is pretty lame that this is necessary
    public List<ConcomitantMedicationAttribution> getConcomitantMedicationAttributions() {
        if (concomitantMedicationAttributions == null) {
            concomitantMedicationAttributions = new ArrayList<ConcomitantMedicationAttribution>();
        }
        return concomitantMedicationAttributions;
    }

    public void setConcomitantMedicationAttributions(
            List<ConcomitantMedicationAttribution> concomitantMedicationAttributions) {
        this.concomitantMedicationAttributions = concomitantMedicationAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'OC'")
    // it is pretty lame that this is necessary
    public List<OtherCauseAttribution> getOtherCauseAttributions() {
        if (otherCauseAttributions == null) {
            otherCauseAttributions = new ArrayList<OtherCauseAttribution>();
        }
        return otherCauseAttributions;
    }

    public void setOtherCauseAttributions(List<OtherCauseAttribution> otherCauseAttributions) {
        this.otherCauseAttributions = otherCauseAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'DH'")
    // it is pretty lame that this is necessary
    public List<DiseaseAttribution> getDiseaseAttributions() {
        if (diseaseAttributions == null) {
            diseaseAttributions = new ArrayList<DiseaseAttribution>();
        }
        return diseaseAttributions;
    }

    public void setDiseaseAttributions(List<DiseaseAttribution> diseaseAttributions) {
        this.diseaseAttributions = diseaseAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'SI'")
    // it is pretty lame that this is necessary
    public List<SurgeryAttribution> getSurgeryAttributions() {
        if (surgeryAttributions == null) {
            surgeryAttributions = new ArrayList<SurgeryAttribution>();
        }
        return surgeryAttributions;
    }

    public void setSurgeryAttributions(List<SurgeryAttribution> surgeryAttributions) {
        this.surgeryAttributions = surgeryAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'RI'")
    // it is pretty lame that this is necessary
    public List<RadiationAttribution> getRadiationAttributions() {
        if (radiationAttributions == null) {
            radiationAttributions = new ArrayList<RadiationAttribution>();
        }
        return radiationAttributions;
    }

    public void setRadiationAttributions(List<RadiationAttribution> radiationAttributions) {
        this.radiationAttributions = radiationAttributions;
    }

    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @Where(clause = "cause_type = 'DV'")
    // it is pretty lame that this is necessary
    public List<DeviceAttribution> getDeviceAttributions() {
        if (deviceAttributions == null) {
            deviceAttributions = new ArrayList<DeviceAttribution>();
        }
        return deviceAttributions;
    }

    public void setDeviceAttributions(List<DeviceAttribution> deviceAttributions) {
        this.deviceAttributions = deviceAttributions;
    }


    @Deprecated
    @Transient
    public CtcTerm getCtcTerm() {
        if (this.adverseEventTerm == null) {
            return null;
        } else if (this.adverseEventTerm instanceof AdverseEventCtcTerm) {
            return getAdverseEventCtcTerm().getCtcTerm();
        } else {
            throw new CaaersSystemException(
                    "Cannot Return a Ctc Term you are probably using a Terminology different than Ctc");
        }
    }

    @Deprecated
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    }

    @Transient
    public AdverseEventCtcTerm getAdverseEventCtcTerm() {
        if (adverseEventTerm == null) {
            this.adverseEventTerm = new AdverseEventCtcTerm();
            adverseEventTerm.setAdverseEvent(this);
        } else if (!adverseEventTerm.getClass().getName().equals(
                "gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm")) {
            return new AdverseEventCtcTerm();
        }

        return (AdverseEventCtcTerm) adverseEventTerm;
    }

    @Transient
    public void setAdverseEventCtcTerm(AdverseEventCtcTerm adverseEventCtcTerm) {
        // this.adverseEventCtcTerm = adverseEventCtcTerm;
        setAdverseEventTerm(adverseEventCtcTerm);
    }

    @Transient
    public AdverseEventMeddraLowLevelTerm getAdverseEventMeddraLowLevelTerm() {
        if (adverseEventTerm == null) {
            this.adverseEventTerm = new AdverseEventMeddraLowLevelTerm();
            adverseEventTerm.setAdverseEvent(this);
        } else if (!adverseEventTerm.getClass().getName().equals(
                "gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
            return new AdverseEventMeddraLowLevelTerm();
        }
        return (AdverseEventMeddraLowLevelTerm) adverseEventTerm;
    }

    @Transient
    public void setAdverseEventMeddraLowLevelTerm(
            AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm) {
        // this.adverseEventCtcTerm = adverseEventCtcTerm;
        setAdverseEventTerm(adverseEventMeddraLowLevelTerm);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adverseEvent")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public AbstractAdverseEventTerm getAdverseEventTerm() {
        return adverseEventTerm;
    }

    public void setAdverseEventTerm(AbstractAdverseEventTerm adverseEventTerm) {
        this.adverseEventTerm = adverseEventTerm;
    }

    public String getDetailsForOther() {
        return detailsForOther;
    }

    public void setDetailsForOther(String detailsForOther) {
        this.detailsForOther = detailsForOther;
    }

    @Type(type = "grade")
    @Column(name = "grade_code")
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Type(type = "hospitalization")
    @Column(name = "hospitalization_code")
    public Hospitalization getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;
    }

    public Boolean getExpected() {
        return expected;
    }

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

    @Type(type = "attribution")
    @Column(name = "attribution_summary_code")
    public Attribution getAttributionSummary() {
        return attributionSummary;
    }

    public void setAttributionSummary(Attribution attributionSummary) {
        this.attributionSummary = attributionSummary;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * Will return all the attributions.
     *
     * @return
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
        return attributions;
    }

    /**
     * This method will tell if the specified attributions exists
     */
    @Transient
    public boolean isAttributedWith(Attribution... attributions) {
        return containsAttribution(courseAgentAttributions, attributions)
                || containsAttribution(concomitantMedicationAttributions, attributions)
                || containsAttribution(otherCauseAttributions, attributions)
                || containsAttribution(diseaseAttributions, attributions)
                || containsAttribution(surgeryAttributions, attributions)
                || containsAttribution(radiationAttributions, attributions)
                || containsAttribution(deviceAttributions, attributions);

    }

    private boolean containsAttribution(
            List<? extends AdverseEventAttribution<? extends DomainObject>> attributionList,
            Attribution... attributions) {
        if (attributionList == null || attributionList.isEmpty()) return false;
        for (AdverseEventAttribution<? extends DomainObject> aea : attributionList) {
        	if(aea.getAttribution() == null) return false;
            for (Attribution att : attributions) {
                if (aea.getAttribution().equals(att)) return true;
            }
        }
        return false;
    }

    @Transient
    public String getDisplayGrade() {
        if (grade == null) return "";

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

    @Transient
    public String getDisplayExpected() {
        displayExpected = "";
        if (expected != null)
            displayExpected = expected.equals(Boolean.TRUE) ? "Yes" : "No";
        return displayExpected;
    }

    public void setDisplayExpected() {
        this.displayExpected = displayExpected;
    }

    @Transient
    public String getDisplaySerious() {
        if (serious != null) return serious.getDisplayName();
        return "";
    }

    public void setDisplaySerious(String igonre) {

    }

    public void setSolicited(Boolean solicited) {
        this.solicited = solicited;
    }

    public Boolean getSolicited() {
        return solicited;
    }

    public Boolean getRequiresReporting() {
        return requiresReporting;
    }

    public void setRequiresReporting(Boolean requiresReporting) {
        this.requiresReporting = requiresReporting;
    }


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

    public void setEventApproximateTime(TimeValue eventApproximateTime) {
        this.eventApproximateTime = eventApproximateTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "adverse_event_id", nullable=false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<Outcome> getOutcomes() {
        if (outcomes == null) outcomes = new ArrayList<Outcome>();
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    @Transient
    public OutcomeType getSerious() {
        return serious;
    }

    public void setSerious(OutcomeType serious) {
        this.serious = serious;
    }

    public AdverseEvent copy() {
        AdverseEvent adverseEvent = new AdverseEvent();
        org.springframework.beans.BeanUtils.copyProperties(this, adverseEvent,
                new String[]{"id", "gridId", "outcomes", "version", "report",
                        "deviceAttributions", "otherCauseAttributions", "courseAgentAttributions", "diseaseAttributions"
                        , "surgeryAttributions", "concomitantMedicationAttributions", "radiationAttributions",
                        "adverseEventTerm", "adverseEventCtcTerm", "adverseEventMeddraLowLevelTerm", "ctcTerm"});

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
    	if(report != null && report.getHasSubmittedReport())
    		submitted = true;
    	return submitted;
    }
    
    /**
     * This method generates a signature for the AE. It uses the values of all the attributes of AEs that can be accessed and modified
     * on the captureAE page. It appends all these values in a specific order (so that it can be compared to the signature generated on
     * the client side and then saved in the html as a hidden field.
     * @return String
     */
    @Transient
    public String getSignature(){
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
    	sb.append(hospitalization == null ? "" : hospitalization.getName());
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
    	sb.append(serious == null ? "" : serious.getName());
    	return sb.toString();
    }
}
