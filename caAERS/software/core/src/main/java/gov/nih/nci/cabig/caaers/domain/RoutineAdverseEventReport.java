package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.*;

 
/**
 * This class represents the RoutineAdverseEventReport domain object.
 * 
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "ae_routine_reports")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ae_routine_reports_id") })
public class RoutineAdverseEventReport extends AbstractMutableDomainObject {
    
    /** The assignment. */
    private StudyParticipantAssignment assignment;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;


    /** The treatment assignment. */
    private TreatmentAssignment treatmentAssignment;

    /** The lazy list helper. */
    private LazyListHelper lazyListHelper;

    /**
     * Instantiates a new routine adverse event report.
     */
    public RoutineAdverseEventReport() {
        lazyListHelper = new LazyListHelper();

    }

    /**
     * Adds the report child lazy list.
     *
     * @param <T> the generic type
     * @param klass the klass
     */
    private <T extends RoutineAdverseEventReportChild> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass, new RoutineAdverseEventReportChildFactory<T>(klass, this));
    }

    // //// LOGIC

    /**
     * Gets the notification message.
     *
     * @return the notification message
     */
    @Transient
    public String getNotificationMessage() {
        if (isNotificationMessagePossible()) {
            AdverseEvent firstAe = getAdverseEventsInternal().get(0);
            CtcTerm term = firstAe.getAdverseEventCtcTerm().getCtcTerm();
            String other = term.isOtherRequired() ? String.format(" (%s)", firstAe
                            .getDetailsForOther()) : "";
            return String.format("Grade %d adverse event with term %s%s", firstAe.getGrade()
                            .getCode(), term.getFullName(), other);
        } else {
            throw new CaaersSystemException(
                            "Cannot create notification message until primary AE is filled in");
        }
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
        return ae != null && ae.getGrade() != null
                        && ae.getAdverseEventCtcTerm().getCtcTerm() != null;
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
     * Gets the summary.
     *
     * @return the summary
     */
    @Transient
    public Map<String, String> getSummary() {
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Participant", getParticipantSummaryLine());
        summary.put("Study", getStudySummaryLine());
        summary.put("Adverse event count", Integer.toString(getAdverseEvents().size()));

        return summary;
    }

    /**
     * Gets the participant summary line.
     *
     * @return the participant summary line
     */
    @Transient
    public String getParticipantSummaryLine() {
        Participant participant = getParticipant();
        if (participant == null) return null;
        StringBuilder sb = new StringBuilder(participant.getFullName());
        appendPrimaryIdentifier(participant, sb);
        return sb.toString();
    }

    /**
     * Gets the study summary line.
     *
     * @return the study summary line
     */
    @Transient
    public String getStudySummaryLine() {
        Study study = getStudy();
        if (study == null) return null;
        StringBuilder sb = new StringBuilder(study.getShortTitle());
        appendPrimaryIdentifier(study, sb);
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
     * Sets the adverse events.
     *
     * @param adverseEvents the new adverse events
     */
    @Transient
    public void setAdverseEvents(final List<AdverseEvent> adverseEvents) {
        setAdverseEventsInternal(adverseEvents);
    }

    // //// BEAN PROPERTIES

    //@ManyToOne(fetch = FetchType.LAZY)
    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @Transient
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    /**
     * Sets the assignment.
     *
     * @param assignment the new assignment
     */
    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the adverse events internal.
     *
     * @return the adverse events internal
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "routine_report_id", nullable = true)
    @IndexColumn(name = "routine_list_index")
    @Cascade(value = {
            // Manually-managing PERSIST cascades due to cascade ordering issue
            CascadeType.DELETE,CascadeType.DETACH, CascadeType.LOCK, CascadeType.MERGE,
            CascadeType.REFRESH })
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

    /**
     * Gets the end date.
     *
     * @return the end date
     */
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
     * Gets the start date.
     *
     * @return the start date
     */
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
     * Gets the treatment assignment.
     *
     * @return the treatment assignment
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_assignment_id")
    @Cascade(value = { CascadeType.LOCK })
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    /**
     * Sets the treatment assignment.
     *
     * @param treatmentAssignment the new treatment assignment
     */
    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

}
