package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.sql.Timestamp;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_reports")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_ae_reports_id")
    }
)
public class AdverseEventReport extends AbstractMutableDomainObject {
    private StudyParticipantAssignment assignment;
    private Date detectionDate;
    private Timestamp createdAt;
    private LazyListHelper lazyListHelper;

    private AdverseEventResponseDescription responseDescription;
    private TreatmentInformation treatmentInformation;

    private Reporter reporter;
    private Physician physician;
    private ParticipantHistory participantHistory;
    private DiseaseHistory diseaseHistory;

    private ReportSchedule reportSchedule;

    private ReportStatus status;

    // TODO
    // private List<MedicalDevice> medicalDevices;

    public AdverseEventReport() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(AdverseEvent.class);
        addReportChildLazyList(Lab.class);
        addReportChildLazyList(ConcomitantMedication.class);
        addReportChildLazyList(OtherCause.class);
        addReportChildLazyList(AdverseEventPriorTherapy.class);
        status = ReportStatus.PENDING;
    }

    private <T extends AdverseEventReportChild> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass,
            new AdverseEventReportChildFactory<T>(klass, this));
    }

    ////// LOGIC

    @Transient
    public String getNotificationMessage() {
        if (isNotificationMessagePossible()) {
            AdverseEvent firstAe = getAdverseEventsInternal().get(0);
            CtcTerm term = firstAe.getCtcTerm();
            String other = term.isOtherRequired()
                ? String.format(" (%s)", firstAe.getDetailsForOther()) : "";
            return String.format("Grade %d adverse event with term %s%s",
                firstAe.getGrade().getCode(),
                term.getFullName(), other
            );
        } else {
            throw new CaaersSystemException(
                "Cannot create notification message until primary AE is filled in");
        }
    }

    @Transient
    public boolean isNotificationMessagePossible() {
        if (getAdverseEventsInternal().size() < 1) return false;
        AdverseEvent ae = getAdverseEventsInternal().get(0);
        return ae != null && ae.getGrade() != null && ae.getCtcTerm() != null;
    }

    @Transient
    public Participant getParticipant() {
        return getAssignment() == null ? null : getAssignment().getParticipant();
    }

    @Transient
    public Study getStudy() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss == null ? null : ss.getStudy();
    }

    @Transient
    public Map<String, String> getSummary() {
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Participant", summaryLine(getParticipant()));
        summary.put("Study", summaryLine(getStudy()));
        summary.put("Report created at", getCreatedAt() == null ? null : getCreatedAt().toString());
        String primaryAeLine = null;
        if (getAdverseEvents().size() > 0 && getAdverseEvents().get(0).getCtcTerm() != null) {
            primaryAeLine = getAdverseEvents().get(0).getCtcTerm().getCtepTerm();
        }
        summary.put("Primary AE", primaryAeLine);
        summary.put("Adverse event count", Integer.toString(getAdverseEvents().size()));

        // TODO: placeholders
        summary.put("Ticket number", null);
        summary.put("Next report due", null);

        return summary;
    }

    private String summaryLine(Participant participant) {
        if (participant == null) return null;
        StringBuilder sb = new StringBuilder(participant.getFullName());
        appendPrimaryIdentifier(participant, sb);
        return sb.toString();
    }

    private String summaryLine(Study study) {
        if (study == null) return null;
        StringBuilder sb = new StringBuilder(study.getShortTitle());
        appendPrimaryIdentifier(study, sb);
        return sb.toString();
    }

    private void appendPrimaryIdentifier(IdentifiableByAssignedIdentifers ided, StringBuilder sb) {
        if (ided.getPrimaryIdentifier() != null) {
            sb.append(" (").append(ided.getPrimaryIdentifier().getValue()).append(')');
        }
    }

    public void addAdverseEvent(AdverseEvent adverseEvent) {
        getAdverseEventsInternal().add(adverseEvent);
        if (adverseEvent != null) adverseEvent.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<AdverseEvent> getAdverseEvents() {
        return lazyListHelper.getLazyList(AdverseEvent.class);
    }

    public void addLab(Lab lab) {
        getLabsInternal().add(lab);
        if (lab != null) lab.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<Lab> getLabs() {
        return lazyListHelper.getLazyList(Lab.class);
    }

    public void addConcomitantMedication(ConcomitantMedication concomitantMedication) {
        getConcomitantMedicationsInternal().add(concomitantMedication);
        if (concomitantMedication != null) concomitantMedication.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<ConcomitantMedication> getConcomitantMedications() {
        return lazyListHelper.getLazyList(ConcomitantMedication.class);
    }

    public void addAdverseEventPriorTherapies(AdverseEventPriorTherapy adverseEventPriorTherapy) {
        getAdverseEventPriorTherapiesInternal().add(adverseEventPriorTherapy);
        if (adverseEventPriorTherapy != null) adverseEventPriorTherapy.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<AdverseEventPriorTherapy> getAdverseEventPriorTherapies() {
        return lazyListHelper.getLazyList(AdverseEventPriorTherapy.class);
    }

    public void addOtherCause(OtherCause otherCause) {
        getOtherCausesInternal().add(otherCause);
        if (otherCause != null) otherCause.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<OtherCause> getOtherCauses() {
        return lazyListHelper.getLazyList(OtherCause.class);
    }

    ////// BEAN PROPERTIES

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = {
        // Manually-managing PERSIST cascades due to cascade ordering issue
        CascadeType.DELETE, CascadeType.EVICT, CascadeType.LOCK, CascadeType.MERGE,
        CascadeType.REFRESH, CascadeType.DELETE_ORPHAN })
    protected List<AdverseEvent> getAdverseEventsInternal() {
        return lazyListHelper.getInternalList(AdverseEvent.class);
    }

    @SuppressWarnings("unchecked")
    protected void setAdverseEventsInternal(List<AdverseEvent> adverseEvents) {
        lazyListHelper.setInternalList(AdverseEvent.class, adverseEvents);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<Lab> getLabsInternal() {
        return lazyListHelper.getInternalList(Lab.class);
    }

    protected void setLabsInternal(List<Lab> labsInternal) {
        lazyListHelper.setInternalList(Lab.class, labsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<ConcomitantMedication> getConcomitantMedicationsInternal() {
        return lazyListHelper.getInternalList(ConcomitantMedication.class);
    }

    protected void setConcomitantMedicationsInternal(List<ConcomitantMedication> concomitantMedicationsInternal) {
        lazyListHelper.setInternalList(ConcomitantMedication.class, concomitantMedicationsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<OtherCause> getOtherCausesInternal() {
        return lazyListHelper.getInternalList(OtherCause.class);
    }

    protected void setOtherCausesInternal(List<OtherCause> otherCausesInternal) {
        lazyListHelper.setInternalList(OtherCause.class, otherCausesInternal);
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<AdverseEventPriorTherapy> getAdverseEventPriorTherapiesInternal() {
        return lazyListHelper.getInternalList(AdverseEventPriorTherapy.class);
    }

    public void setAdverseEventPriorTherapiesInternal(
        List<AdverseEventPriorTherapy> adverseEventPriorTherapiesInternal) {
        lazyListHelper.setInternalList(AdverseEventPriorTherapy.class, adverseEventPriorTherapiesInternal);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public TreatmentInformation getTreatmentInformation() {
        if (treatmentInformation == null) setTreatmentInformation(new TreatmentInformation());
        return treatmentInformation;
    }

    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
        if (treatmentInformation != null) treatmentInformation.setReport(this);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public AdverseEventResponseDescription getResponseDescription() {
        if (responseDescription == null) setResponseDescription(new AdverseEventResponseDescription());
        return responseDescription;
    }

    public void setResponseDescription(AdverseEventResponseDescription responseDescription) {
        this.responseDescription = responseDescription;
        if (responseDescription != null) responseDescription.setReport(this);
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public Reporter getReporter() {
        if (reporter == null) setReporter(AdverseEventReportPerson.createEmptyPerson(Reporter.class));
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
        if (reporter != null) reporter.setReport(this);
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public Physician getPhysician() {
        if (physician == null) setPhysician(AdverseEventReportPerson.createEmptyPerson(Physician.class));
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
        if (physician != null) physician.setReport(this);
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public DiseaseHistory getDiseaseHistory() {
        if (diseaseHistory == null) setDiseaseHistory(new DiseaseHistory());
        return diseaseHistory;
    }

    public void setDiseaseHistory(DiseaseHistory diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
        if (diseaseHistory != null) diseaseHistory.setReport(this);
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public ParticipantHistory getParticipantHistory() {
        if (participantHistory == null) setParticipantHistory(new ParticipantHistory());
        return participantHistory;
    }

    public void setParticipantHistory(ParticipantHistory participantHistory) {
        this.participantHistory = participantHistory;
        if (participantHistory != null) participantHistory.setReport(this);
    }

    /**
     * @return the reportSchedule
     */
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "aeReport")
//    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Transient
    public ReportSchedule getReportSchedule() {
        return reportSchedule;
    }

    /**
     * @param reportSchedule the reportSchedule to set
     */
    public void setReportSchedule(ReportSchedule reportSchedule) {
        this.reportSchedule = reportSchedule;
    }

    /**
     * @return the status
     */
//	@Column(name="status_code")
//	@Type(type="reportStatus")
    @Transient
    public ReportStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
