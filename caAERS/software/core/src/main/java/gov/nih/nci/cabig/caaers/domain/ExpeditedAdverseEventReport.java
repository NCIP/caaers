package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.annotation.UniqueObjectInCollection;
import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the ExpeditedAdverseEventReport domain object.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@Entity
@Table(name = "ae_reports")
@GenericGenerator(name = "id-generator", strategy = "native",
        parameters = {
                @Parameter(name = "sequence", value = "seq_ae_reports_id")
        }
)
public class ExpeditedAdverseEventReport extends AbstractMutableDomainObject implements  Serializable{
   
	private static final long serialVersionUID = -3747213703166595074L;
	private Timestamp createdAt;
    private LazyListHelper lazyListHelper;

    private AdverseEventResponseDescription responseDescription;
    private TreatmentInformation treatmentInformation;
    private AdditionalInformation additionalInformation;


    private Reporter reporter;
    private Physician physician;
    private ParticipantHistory participantHistory;
    private DiseaseHistory diseaseHistory;
    private AdverseEventReportingPeriod reportingPeriod;

    private List<Report> reports;
    private static final Log log = LogFactory.getLog(ExpeditedAdverseEventReport.class);

    
    // TODO
    // private List<MedicalDevice> medicalDevices;

    public ExpeditedAdverseEventReport() {
        lazyListHelper = new LazyListHelper();
        addReportChildLazyList(AdverseEvent.class);
        addReportChildLazyList(Lab.class);
        addReportChildLazyList(MedicalDevice.class);
        addReportChildLazyList(RadiationIntervention.class);
        addReportChildLazyList(SurgeryIntervention.class);
        addReportChildLazyList(ConcomitantMedication.class);
        addReportChildLazyList(OtherCause.class);
        addReportChildLazyList(SAEReportPriorTherapy.class);
        addReportChildLazyList(SAEReportPreExistingCondition.class);
    }

    private <T extends ExpeditedAdverseEventReportChild> void addReportChildLazyList(Class<T> klass) {
        lazyListHelper.add(klass, new ExpeditedAdverseEventReportChildFactory<T>(klass, this));
    }

    ////// LOGIC

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
    public boolean isNotificationMessagePossible() {
        if (getAdverseEventsInternal().size() < 1) return false;
        AdverseEvent ae = getAdverseEventsInternal().get(0);
        return ae != null && ae.getGrade() != null && ae.getAdverseEventTerm().getTerm() != null;
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
    public StudySite getStudySite() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss;
    }

    
    @Transient
    public Map<String, String> getSummary() {
        Map<String, String> summary = new LinkedHashMap<String, String>();
        summary.put("Study", summaryLine(getStudy()));
        summary.put("Participant", summaryLine(getParticipant()));
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

    private String summaryLine(Participant participant) {
        if (participant == null) return null;
        StringBuilder sb = new StringBuilder();
        appendPrimaryIdentifier(participant, sb);
        sb.append(" ").append(participant.getFullName());
        return sb.toString();
    }

    private String summaryLine(Study study) {
        if (study == null) return null;
        StringBuilder sb = new StringBuilder();
        appendPrimaryIdentifier(study, sb);
        sb.append(" ").append(study.getShortTitle());
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

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<AdverseEvent> getAdverseEvents() {
        return lazyListHelper.getLazyList(AdverseEvent.class);
    }
    
    /**
     * List of adverse events that are not retired
     * @return
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
     * @return
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
     * @return
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
    
    
    public void addLab(Lab lab) {
        getLabsInternal().add(lab);
        if (lab != null) lab.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<Lab> getLabs() {
        return lazyListHelper.getLazyList(Lab.class);
    }

    public void addMedicalDevice(MedicalDevice medicalDevice) {
        getMedicalDevicesInternal().add(medicalDevice);
        if (medicalDevice != null) medicalDevice.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<MedicalDevice> getMedicalDevices() {
        return lazyListHelper.getLazyList(MedicalDevice.class);
    }


    public void addRadiationIntervention(RadiationIntervention radiationIntervention) {
        getRadiationInterventionsInternal().add(radiationIntervention);
        if (radiationIntervention != null) radiationIntervention.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<RadiationIntervention> getRadiationInterventions() {
        return lazyListHelper.getLazyList(RadiationIntervention.class);
    }

    public void addSurgeryIntervention(SurgeryIntervention surgeryIntervention) {
        getSurgeryInterventionsInternal().add(surgeryIntervention);
        if (surgeryIntervention != null) surgeryIntervention.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<SurgeryIntervention> getSurgeryInterventions() {
        return lazyListHelper.getLazyList(SurgeryIntervention.class);
    }


    public void addConcomitantMedication(ConcomitantMedication concomitantMedication) {
        getConcomitantMedicationsInternal().add(concomitantMedication);
        if (concomitantMedication != null) concomitantMedication.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<ConcomitantMedication> getConcomitantMedications() {
        return lazyListHelper.getLazyList(ConcomitantMedication.class);
    }

    public void addSaeReportPreExistingCondition(SAEReportPreExistingCondition sAEReportPreExistingCondition) {
        getSaeReportPreExistingConditionsInternal().add(sAEReportPreExistingCondition);
        if (sAEReportPreExistingCondition != null) sAEReportPreExistingCondition.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
//    @UniqueObjectInCollection(message = "Duplicate pre existing condition")
    public List<SAEReportPreExistingCondition> getSaeReportPreExistingConditions() {
        return lazyListHelper.getLazyList(SAEReportPreExistingCondition.class);
    }

    public void addSaeReportPriorTherapies(SAEReportPriorTherapy saeReportPriorTherapy) {
        getSaeReportPriorTherapiesInternal().add(saeReportPriorTherapy);
        if (saeReportPriorTherapy != null) saeReportPriorTherapy.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate prior therapy")
    public List<SAEReportPriorTherapy> getSaeReportPriorTherapies() {
        return lazyListHelper.getLazyList(SAEReportPriorTherapy.class);
    }


    public void addOtherCause(OtherCause otherCause) {
        getOtherCausesInternal().add(otherCause);
        if (otherCause != null) otherCause.setReport(this);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    @UniqueObjectInCollection(message = "Duplicate other cause")
    public List<OtherCause> getOtherCauses() {
        return lazyListHelper.getLazyList(OtherCause.class);
    }


    ////// BEAN PROPERTIES

    @Transient
    public StudyParticipantAssignment getAssignment() {
        return reportingPeriod.getAssignment();
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.reportingPeriod.setAssignment(assignment);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = true)
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = {
            // Manually-managing PERSIST cascades due to cascade ordering issue
            CascadeType.DELETE, CascadeType.EVICT, CascadeType.LOCK, CascadeType.MERGE,
            CascadeType.REFRESH})
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
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<Lab> getLabsInternal() {
        return lazyListHelper.getInternalList(Lab.class);
    }

    protected void setLabsInternal(List<Lab> labsInternal) {
        lazyListHelper.setInternalList(Lab.class, labsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<MedicalDevice> getMedicalDevicesInternal() {
        return lazyListHelper.getInternalList(MedicalDevice.class);
    }

    protected void setMedicalDevicesInternal(List<MedicalDevice> medicalDevicesInternal) {
        lazyListHelper.setInternalList(MedicalDevice.class, medicalDevicesInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<RadiationIntervention> getRadiationInterventionsInternal() {
        return lazyListHelper.getInternalList(RadiationIntervention.class);
    }

    protected void setRadiationInterventionsInternal(List<RadiationIntervention> radiationInterventionsInternal) {
        lazyListHelper.setInternalList(RadiationIntervention.class, radiationInterventionsInternal);
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<SurgeryIntervention> getSurgeryInterventionsInternal() {
        return lazyListHelper.getInternalList(SurgeryIntervention.class);
    }

    protected void setSurgeryInterventionsInternal(List<SurgeryIntervention> surgeryInterventionsInternal) {
        lazyListHelper.setInternalList(SurgeryIntervention.class, surgeryInterventionsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<ConcomitantMedication> getConcomitantMedicationsInternal() {
        return lazyListHelper.getInternalList(ConcomitantMedication.class);
    }

    protected void setConcomitantMedicationsInternal(List<ConcomitantMedication> concomitantMedicationsInternal) {
        lazyListHelper.setInternalList(ConcomitantMedication.class, concomitantMedicationsInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<SAEReportPreExistingCondition> getSaeReportPreExistingConditionsInternal() {
        return lazyListHelper.getInternalList(SAEReportPreExistingCondition.class);
    }

    protected void setSaeReportPreExistingConditionsInternal(List<SAEReportPreExistingCondition> saeReportPreExistingConditionInternal) {
        lazyListHelper.setInternalList(SAEReportPreExistingCondition.class, saeReportPreExistingConditionInternal);
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    protected List<OtherCause> getOtherCausesInternal() {
        return lazyListHelper.getInternalList(OtherCause.class);
    }

    protected void setOtherCausesInternal(List<OtherCause> otherCausesInternal) {
        lazyListHelper.setInternalList(OtherCause.class, otherCausesInternal);
    }

    //  This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "report_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<SAEReportPriorTherapy> getSaeReportPriorTherapiesInternal() {
        return lazyListHelper.getInternalList(SAEReportPriorTherapy.class);
    }

    public void setSaeReportPriorTherapiesInternal(
            List<SAEReportPriorTherapy> saeReportPriorTherapiesInternal) {
        lazyListHelper.setInternalList(SAEReportPriorTherapy.class, saeReportPriorTherapiesInternal);
    }


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public TreatmentInformation getTreatmentInformation() {
        if (treatmentInformation == null) setTreatmentInformation(new TreatmentInformation());
        return treatmentInformation;
    }

    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
        if (treatmentInformation != null) treatmentInformation.setReport(this);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public AdditionalInformation getAdditionalInformation() {
        if (additionalInformation == null) setAdditionalInformation(new AdditionalInformation());
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
        if (additionalInformation != null) additionalInformation.setReport(this);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "report")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public AdverseEventResponseDescription getResponseDescription() {
        if (responseDescription == null) setResponseDescription(new AdverseEventResponseDescription());
        return responseDescription;
    }

    public void setResponseDescription(AdverseEventResponseDescription responseDescription) {
        this.responseDescription = responseDescription;
        if (responseDescription != null) responseDescription.setReport(this);
    }

    // non-total cascade allows us to skip saving if the reporter hasn't been filled in yet
    @OneToOne(mappedBy = "expeditedReport",  fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.DELETE, CascadeType.EVICT, CascadeType.LOCK, CascadeType.REMOVE})
    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
        if (reporter != null) reporter.setExpeditedReport(this);
    }
    


    // non-total cascade allows us to skip saving if the physician hasn't been filled in yet
    @OneToOne(mappedBy = "expeditedReport", fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.DELETE, CascadeType.EVICT, CascadeType.LOCK, CascadeType.REMOVE})
    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
        if (physician != null) physician.setExpeditedReport(this);
    }

    @OneToOne(mappedBy = "report", fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL})
    public DiseaseHistory getDiseaseHistory() {
        if (diseaseHistory == null) setDiseaseHistory(new DiseaseHistory());
        return diseaseHistory;
    }

    public void setDiseaseHistory(DiseaseHistory diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
        if (diseaseHistory != null) diseaseHistory.setReport(this);
    }

    @OneToOne(mappedBy = "report", fetch=FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public ParticipantHistory getParticipantHistory() {
        if (participantHistory == null) setParticipantHistory(new ParticipantHistory());
        return participantHistory;
    }

    public void setParticipantHistory(ParticipantHistory participantHistory) {
        this.participantHistory = participantHistory;
        if (participantHistory != null) participantHistory.setReport(this);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aeReport")
    @Cascade(value = {CascadeType.DELETE, CascadeType.EVICT,
            CascadeType.LOCK, CascadeType.REMOVE})
    // Manually manage update-style reassociates and saves
    public List<Report> getReports() {
        if (reports == null) reports = new ArrayList<Report>();
        return reports;
    }
    
    /**
     * True,when at least one Report is active
     * @return
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
     * @return
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
     * Returns all the pending reports, that are in PENDING
     * @return
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
     * @param reportType
     * @return
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
     * @return
     */
    public List<Report> findCompletedAmendableReports(){
    	List<Report> completedReports = listReportsHavingStatus(ReportStatus.COMPLETED);
    	List<Report> amendableReports = new ArrayList<Report>();
    	for(Report report : completedReports){
    		if(report.isAmendable()) amendableReports.add(report);
    	}
    	return amendableReports;
    }
    
    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public void addReport(Report report) {
        getReports().add(report);
        report.setAeReport(this);
    }
    
   

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "reporting_period_id")
    @Cascade(value = {CascadeType.LOCK})
    public AdverseEventReportingPeriod getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }


    @Transient
    public String getPublicIdentifier() {
        String id = getAssignment().getStudySite().getOrganization().getNciInstituteCode() + "-" + getAssignment().getStudySite().getOrganization().getNciInstituteCode();
        id = (id.indexOf("null") > -1) ? "None" : id;
        return id;
    }

    @Transient
    public void setPublicIdentifier(String strId) {
    }

 
    @Transient
    List<String> findPhoneNumbers(String role) {
        assert false : "Not implemented";
        return null;
    }

    @Transient
    List<String> findFaxNumbers(String role) {
        assert false : "Not implemented";
        return null;
    }

    @Transient
    public int getNumberOfAes() {
        int count = (this.getAdverseEvents() != null) ? this.getAdverseEvents().size() : 0;
        return count;
    }

    @Transient
    public Report getPrimaryReport() {
        return getReports().get(0);
    }

    public ExpeditedAdverseEventReport copy() {
        ExpeditedAdverseEventReport expeditedAdverseEventReport = new ExpeditedAdverseEventReport();
        BeanUtils.copyProperties(this, expeditedAdverseEventReport,
                new String[]{"id", "gridId", "version",
                        "publicIdentifier", "status"
                        , "treatmentInformation", "assignment",
                        "adverseEventsInternal",
                        "saeReportPriorTherapiesInternal", "saeReportPriorTherapies"
                        , "medicalDevicesInternal", "medicalDevices",
                        "labsInternal", "labs",
                        "physician", "additionalInformation", "reporter",
                        "radiationInterventionsInternal", "radiationInterventions",
                        "surgeryInterventionsInternal", "surgeryInterventions",
                        "otherCausesInternal", "otherCauses",
                        "concomitantMedicationsInternal", "concomitantMedications",
                        "saeReportPreExistingConditionsInternal", "saeReportPreExistingConditions",
                        "responseDescription", "diseaseHistory", "participantHistory", "reports"});

        for (AdverseEvent adverseEvent : getAdverseEvents()) {
            expeditedAdverseEventReport.addAdverseEvent(adverseEvent.copy());

        }
        for (SAEReportPriorTherapy saeReportPriorTherapy : getSaeReportPriorTherapies()) {
            expeditedAdverseEventReport.addSaeReportPriorTherapies(saeReportPriorTherapy.copy());
        }
        if (getTreatmentInformation() != null) {
            expeditedAdverseEventReport.setTreatmentInformation(getTreatmentInformation().copy());
        }
        for (MedicalDevice medicalDevice : getMedicalDevices()) {
            expeditedAdverseEventReport.addMedicalDevice(medicalDevice);
        }
        for (Lab lab : getLabs()) {
            expeditedAdverseEventReport.addLab(lab.copy());
        }
        if (getPhysician() != null) {
            expeditedAdverseEventReport.setPhysician(getPhysician().copy());
        }
        if (getReporter() != null) {
            expeditedAdverseEventReport.setReporter(getReporter().copy());
        }
        if (getAdditionalInformation() != null) {
            expeditedAdverseEventReport.setAdditionalInformation(getAdditionalInformation().copy());
        }
        for (RadiationIntervention radiationIntervention : getRadiationInterventions()) {
            expeditedAdverseEventReport.addRadiationIntervention(radiationIntervention.copy());
        }
        for (SurgeryIntervention surgeryIntervention : getSurgeryInterventions()) {
            expeditedAdverseEventReport.addSurgeryIntervention(surgeryIntervention.copy());
        }
        for (OtherCause otherCause : getOtherCauses()) {
            expeditedAdverseEventReport.addOtherCause(otherCause.copy());
        }

        for (ConcomitantMedication concomitantMedication : getConcomitantMedications()) {
            expeditedAdverseEventReport.addConcomitantMedication(concomitantMedication.copy());
        }

        for (SAEReportPreExistingCondition saeReportPreExistingCondition : getSaeReportPreExistingConditions()) {
            expeditedAdverseEventReport.addSaeReportPreExistingCondition(saeReportPreExistingCondition.copy());
        }

        if (getResponseDescription() != null) {
            expeditedAdverseEventReport.setResponseDescription(getResponseDescription().copy());
        }
        if (getDiseaseHistory() != null) {
            expeditedAdverseEventReport.setDiseaseHistory(getDiseaseHistory().copy());
        }
        if (getParticipantHistory() != null) {
            expeditedAdverseEventReport.setParticipantHistory(getParticipantHistory().copy());
        }

        return expeditedAdverseEventReport;


    }

    /*
   you should call this method only once
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
     * synchronize prior therapies from assignment to report
     */
    private void syncrhonizePriorTherapies() {

        if (getSaeReportPriorTherapies().isEmpty()) {
            //copy only once
            for (StudyParticipantPriorTherapy studyParticipantPriorTherapy : getAssignment().getPriorTherapies()) {
                SAEReportPriorTherapy priorTherapy = SAEReportPriorTherapy.createSAEReportPriorTherapy(studyParticipantPriorTherapy);
                addSaeReportPriorTherapies(priorTherapy);
            }
        }


    }


    private void syncrhonizePreExistingConditions() {

        if (getSaeReportPreExistingConditions().isEmpty()) {
            //copy only once
            for (StudyParticipantPreExistingCondition studyParticipantPreExistingCondition : getAssignment().getPreExistingConditions()) {
                SAEReportPreExistingCondition saeReportPreExistingCondition = SAEReportPreExistingCondition.createSAEReportPreExistingCondition(studyParticipantPreExistingCondition);
                addSaeReportPreExistingCondition(saeReportPreExistingCondition);
            }
        }

    }


    private void syncrhonizeConcomitantMedications() {

        if (getConcomitantMedications().isEmpty()) {
            //copy only once
            for (StudyParticipantConcomitantMedication studyParticipantConcomitantMedication : getAssignment().getConcomitantMedications()) {
                ConcomitantMedication saeReportConcomitantMedication = ConcomitantMedication.createConcomitantMedication(studyParticipantConcomitantMedication);
                addConcomitantMedication(saeReportConcomitantMedication);
            }

        }

    }

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
     * @return
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
     * @return
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
     * This method will return the earliest graded date, of  adverse events
     * @return
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
     * This method will set the graded date of adverse events to today.
     */
    public void updateAdverseEventGradedDate(){
    	Date now = new Date();
    	for(AdverseEvent ae: getAdverseEvents()){
    		ae.setGradedDate(now);
    	}
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
    
    @Transient
    public boolean isPhysicianSignOffRequired(){
    	boolean physicianSignOffRequired = false;
    	for(Report report: getReports()){
    		if(report.isActive())
    			physicianSignOffRequired |= report.getReportDefinition().getPhysicianSignOff();
    	}
    	return physicianSignOffRequired;
    }
    
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
     * @param rd
     * @return
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
     * @param rd
     * @return
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
     * @param rd
     * @return
     */
    public boolean hasExistingReportsOfSameOrganizationAndType(ReportDefinition rd){
    	for(Report report : getReports()){
    		if(report.isOfSameOrganizationAndType(rd)) return true;
    	}
    	return false;
    }
    
    
    /**
     * This method will return the AdverseEvent that is associated with this data collection, identified by ID
     * @param id
     * @return
     */
    public AdverseEvent findAdverseEventById(Integer id){
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.getId().equals(id)) return ae;
    	}
    	return null;
    }
    
    /**
     * This method will return the Report associated to this data collection, identified by ID
     * @param id
     * @return
     */
    public Report findReportById(Integer id){
    	for(Report report : getReports()){
    		if(report.getId().equals(id)) return report;
    	}
    	return null;
    }
    
    /**
     * This method returns is used to determine if there are any active reports which are in a workflow
     * @return boolean
     */
    public boolean hasWorkflowOnActiveReports(){
    	boolean hasWorkflowOnActiveReports = false;
    	for(Report r: getActiveReports())
    		if(r.getWorkflowId() != null)
    			hasWorkflowOnActiveReports = true;
    	return hasWorkflowOnActiveReports;
    }
    
}