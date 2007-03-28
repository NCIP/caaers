package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private List<AdverseEvent> adverseEvents = new ArrayList<AdverseEvent>();

    private List<Lab> labs = new LinkedList<Lab>();

    // TODO
    // private MedicalInformation medicalInformation;
    // private TreatmentInformation treatmentInformation;
    // private List<PriorTherapy> priorTherapies;
    // private List<ConcomitantMedication> concomitantMedications;
    // private List<Agent> agents;
    // private List<MedicalDevice> medicalDevices;
    // private ReporterInfo reporterInfo;

    ////// LOGIC

    @Transient
    public String getNotificationMessage() {
        if (isNotificationMessagePossible()) {
            AdverseEvent firstAe = getAdverseEvents().get(0);
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
        if (getAdverseEvents().size() < 1) return false;
        AdverseEvent ae = getAdverseEvents().get(0);
        return ae != null && ae.getGrade() != null && ae.getCtcTerm() != null;
    }

    public void addAdverseEvent(AdverseEvent adverseEvent) {
        getAdverseEvents().add(adverseEvent);
        if (adverseEvent != null) adverseEvent.setReport(this);
    }

    ////// BEAN PROPERTIES

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
    public List<AdverseEvent> getAdverseEvents() {
        return adverseEvents;
    }

    public void setAdverseEvents(List<AdverseEvent> adverseEvents) {
        this.adverseEvents = adverseEvents;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<Lab> getLabs() {
        return labs;
    }

    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }
}
