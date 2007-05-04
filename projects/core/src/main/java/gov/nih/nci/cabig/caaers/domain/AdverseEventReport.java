package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import org.apache.commons.collections.list.LazyList;
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
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
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
    private Date detectionDate;
    // TODO: consider creating an aspect which handles the internal/external thing
    private List<AdverseEvent> adverseEventsInternal;
    private List<AdverseEvent> adverseEvents;

    private List<Lab> labsInternal;
    private List<Lab> labs;

    private List<ConcomitantMedication> concomitantMedicationsInternal;
    private List<ConcomitantMedication> concomitantMedications;

    private List<OtherCause> otherCausesInternal;
    private List<OtherCause> otherCauses;
    
    private List<AdverseEventPriorTherapy> adverseEventPriorTherapiesInternal;
    private List<AdverseEventPriorTherapy> adverseEventPriorTherapies;

    private TreatmentInformation treatmentInformation;

    private Reporter reporter;
    private Physician physician;
    private ParticipantHistory participantHistory;
    private DiseaseHistory diseaseHistory;

    // TODO
    // private MedicalInformation medicalInformation;
    // private List<PriorTherapy> priorTherapies;
    // private List<MedicalDevice> medicalDevices;
    // private ReporterInfo reporterInfo;    

    public AdverseEventReport() {
        setAdverseEventsInternal(new ArrayList<AdverseEvent>());
        setLabsInternal(new ArrayList<Lab>());
        setConcomitantMedicationsInternal(new ArrayList<ConcomitantMedication>());
        setOtherCausesInternal(new ArrayList<OtherCause>());
        setAdverseEventPriorTherapiesInternal(new ArrayList<AdverseEventPriorTherapy>());
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
        this.adverseEvents = LazyList.decorate(getAdverseEventsInternal(),
            new AdverseEventReportChildFactory(AdverseEvent.class, this));
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
        this.labs = LazyList.decorate(getLabsInternal(),
            new AdverseEventReportChildFactory(Lab.class, this));
    }

    public void addConcomitantMedication(ConcomitantMedication concomitantMedication) {
        getConcomitantMedicationsInternal().add(concomitantMedication);
        if (concomitantMedication != null) concomitantMedication.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<ConcomitantMedication> getConcomitantMedications() {
        return concomitantMedications;
    }

    @SuppressWarnings("unchecked")
    private void createLazyConcomitantMedications() {
        this.concomitantMedications = LazyList.decorate(getConcomitantMedicationsInternal(),
            new AdverseEventReportChildFactory(ConcomitantMedication.class, this));
    }

    public void addAdverseEventPriorTherapies(AdverseEventPriorTherapy adverseEventPriorTherapy) {
        getAdverseEventPriorTherapiesInternal().add(adverseEventPriorTherapy);
        if (adverseEventPriorTherapy != null) adverseEventPriorTherapy.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<AdverseEventPriorTherapy> getAdverseEventPriorTherapies() {
        return adverseEventPriorTherapies;
    }

    @SuppressWarnings("unchecked")
    private void createLazyAdverseEventPriorTherapies() {
        this.adverseEventPriorTherapies = LazyList.decorate(getAdverseEventPriorTherapiesInternal(),
            new AdverseEventReportChildFactory(AdverseEventPriorTherapy.class, this));
    }
    
    public void addOtherCause(OtherCause otherCause) {
        getOtherCausesInternal().add(otherCause);
        if (otherCause != null) otherCause.setReport(this);
    }

    /** @return a wrapped list which will never throw an {@link IndexOutOfBoundsException} */
    @Transient
    public List<OtherCause> getOtherCauses() {
        return otherCauses;
    }

    @SuppressWarnings("unchecked")
    private void createLazyOtherCauses() {
        this.otherCauses = LazyList.decorate(getOtherCausesInternal(),
            new AdverseEventReportChildFactory(OtherCause.class, this));
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

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<ConcomitantMedication> getConcomitantMedicationsInternal() {
        return concomitantMedicationsInternal;
    }

    protected void setConcomitantMedicationsInternal(List<ConcomitantMedication> concomitantMedicationsInternal) {
        this.concomitantMedicationsInternal = concomitantMedicationsInternal;
        createLazyConcomitantMedications();
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    protected List<OtherCause> getOtherCausesInternal() {
        return otherCausesInternal;
    }

    protected void setOtherCausesInternal(List<OtherCause> otherCausesInternal) {
        this.otherCausesInternal = otherCausesInternal;
        createLazyOtherCauses();
    }
    
    
    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name="report_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public List<AdverseEventPriorTherapy> getAdverseEventPriorTherapiesInternal() {
		return adverseEventPriorTherapiesInternal;
	}

	public void setAdverseEventPriorTherapiesInternal(
			List<AdverseEventPriorTherapy> adverseEventPriorTherapiesInternal) {
		this.adverseEventPriorTherapiesInternal = adverseEventPriorTherapiesInternal;
		createLazyAdverseEventPriorTherapies();
	}  
   

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public TreatmentInformation getTreatmentInformation() {
        //if (treatmentInformation == null) treatmentInformation = new TreatmentInformation();
        return treatmentInformation;
    }

    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
        if (treatmentInformation != null) treatmentInformation.setReport(this);
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public DiseaseHistory getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(DiseaseHistory diseaseHistory) {
        if (diseaseHistory == null) diseaseHistory = new DiseaseHistory();
        this.diseaseHistory = diseaseHistory;
    }

    @OneToOne(mappedBy = "report")
    @Cascade(value = { CascadeType.ALL })
    public ParticipantHistory getParticipantHistory() {
        if (participantHistory == null) participantHistory = new ParticipantHistory();
        return participantHistory;
    }

    public void setParticipantHistory(ParticipantHistory participantHistory) {
        this.participantHistory = participantHistory;
    }
}
