package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

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
public class AdverseEventReport extends AbstractDomainObject {
    private StudyParticipantAssignment assignment;
    private Date detectionDate;
    private List<AdverseEvent> adverseEventsInternal;
    private List<AdverseEvent> adverseEvents;

    private List<Lab> labsInternal;
    private List<Lab> labs;

    // TODO
    // private MedicalInformation medicalInformation;
    // private TreatmentInformation treatmentInformation;
    // private List<PriorTherapy> priorTherapies;
    // private List<ConcomitantMedication> concomitantMedications;
    // private List<Agent> agents;
    // private List<MedicalDevice> medicalDevices;
    // private ReporterInfo reporterInfo;

    public AdverseEventReport() {
        setAdverseEventsInternal(new ArrayList<AdverseEvent>());
        setLabsInternal(new ArrayList<Lab>());
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

    public void addAdverseEvent(AdverseEvent adverseEvent) {
        getAdverseEventsInternal().add(adverseEvent);
        if (adverseEvent != null) adverseEvent.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<AdverseEvent> getAdverseEvents() {
        return adverseEvents;
    }

    @SuppressWarnings("unchecked")
    private void createLazyAdverseEvents() {
        this.adverseEvents = LazyList.decorate(getAdverseEventsInternal(), new Factory() {
            public Object create() {
                AdverseEvent ae = new AdverseEvent();
                ae.setReport(AdverseEventReport.this);
                return ae;
            }
        });
    }

    public void addLab(Lab lab) {
        getLabsInternal().add(lab);
        if (lab != null) lab.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<Lab> getLabs() {
        return labs;
    }

    @SuppressWarnings("unchecked")
    private void createLazyLabs() {
        this.labs = LazyList.decorate(getLabsInternal(), new Factory() {
            public Object create() {
                Lab lab = new Lab();
                lab.setReport(AdverseEventReport.this);
                return lab;
            }
        });
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
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<AdverseEvent> getAdverseEventsInternal() {
        return adverseEventsInternal;
    }

    @SuppressWarnings("unchecked")
    protected void setAdverseEventsInternal(List<AdverseEvent> adverseEvents) {
        this.adverseEventsInternal = adverseEvents;
        createLazyAdverseEvents();
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<Lab> getLabsInternal() {
        return labsInternal;
    }

    protected void setLabsInternal(List<Lab> labsInternal) {
        this.labsInternal = labsInternal;
        createLazyLabs();
    }
}
