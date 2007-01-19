package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.util.List;
import java.util.LinkedList;

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
    private AdverseEvent primaryAdverseEvent;

    private List<Lab> labs = new LinkedList<Lab>();

    // TODO
    // private List<AdverseEvent> otherAdverseEvents;
    // private MedicalInformation medicalInformation;
    // private TreatmentInformation treatmentInformation;
    // private List<PriorTherapy> priorTherapies;
    // private List<ConcomitantMedication> concomitantMedications;
    // private List<Agent> agents;
    // private List<MedicalDevice> medicalDevices;
    // private ReporterInfo reporterInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public AdverseEvent getPrimaryAdverseEvent() {
        return primaryAdverseEvent;
    }

    public void setPrimaryAdverseEvent(AdverseEvent primaryAdverseEvent) {
        this.primaryAdverseEvent = primaryAdverseEvent;
        if (primaryAdverseEvent != null) primaryAdverseEvent.setReport(this);
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
