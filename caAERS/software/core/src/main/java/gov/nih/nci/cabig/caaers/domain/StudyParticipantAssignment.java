package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObjectTools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.annotations.*;

 
/**
 * The Class StudyParticipantAssignment.
 *
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "participant_assignments")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participant_assignments_id")})
@Where(clause = "load_status > 0")
public class StudyParticipantAssignment extends AbstractMutableDomainObject {

    /** The participant. */
    private Participant participant;
    
    /** The study site. */
    private StudySite studySite;

    /** The date of enrollment. */
    private Date dateOfEnrollment;


    //private List<RoutineAdverseEventReport> aeRoutineReports;

    /** The lab loads. */
    private List<LabLoad> labLoads;

    /** The load status. */
    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    /** The study subject identifier. */
    private String studySubjectIdentifier;

    /** The start date of first course. */
    private Date startDateOfFirstCourse;

    /** The reporting periods. */
    private List<AdverseEventReportingPeriod> reportingPeriods;

    /** The pre existing conditions. */
    private List<StudyParticipantPreExistingCondition> preExistingConditions;
    
    /** The concomitant medications. */
    private List<StudyParticipantConcomitantMedication> concomitantMedications;
    
    /** The prior therapies. */
    private List<StudyParticipantPriorTherapy> priorTherapies;
    
    /** The disease history. */
    private StudyParticipantDiseaseHistory diseaseHistory;
    
    /** The baseline performance. */
    private String baselinePerformance;

    /**
     * Instantiates a new study participant assignment.
     *
     * @param participant the participant
     * @param studySite the study site
     */
    public StudyParticipantAssignment(Participant participant, StudySite studySite) {
        this.participant = participant;
        this.studySite = studySite;
        this.dateOfEnrollment = new Date();
    }

    /**
     * Instantiates a new study participant assignment.
     */
    public StudyParticipantAssignment() {
    }

    // //// LOGIC


    /**
     * Adds the routine report.
     *
     * @param routineReport the routine report
     */
    public void addRoutineReport(RoutineAdverseEventReport routineReport) {
        routineReport.setAssignment(this);
        getAeRoutineReports().add(routineReport);
    }

    /**
     * Adds the reporting period.
     *
     * @param reportingPeriod the reporting period
     */
    public void addReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        if (reportingPeriods == null) reportingPeriods = new ArrayList<AdverseEventReportingPeriod>();
        if (reportingPeriod != null) {
            reportingPeriod.setAssignment(this);
            reportingPeriods.add(reportingPeriod);
        }
    }

    /**
     * Adds the pre existing condition.
     *
     * @param preExistingCondition the pre existing condition
     */
    public void addPreExistingCondition(StudyParticipantPreExistingCondition preExistingCondition) {
        if (preExistingConditions == null)
            preExistingConditions = new ArrayList<StudyParticipantPreExistingCondition>();
        if (preExistingCondition != null) {
            preExistingCondition.setAssignment(this);
            preExistingConditions.add(preExistingCondition);
        }
    }

    /**
     * Adds the concomitant medication.
     *
     * @param concomitantMedication the concomitant medication
     */
    public void addConcomitantMedication(StudyParticipantConcomitantMedication concomitantMedication) {
        if (concomitantMedications == null)
            concomitantMedications = new ArrayList<StudyParticipantConcomitantMedication>();
        if (concomitantMedication != null) {
            concomitantMedication.setAssignment(this);
            concomitantMedications.add(concomitantMedication);
        }
    }

    /**
     * Adds the prior therapy.
     *
     * @param priorTherapy the prior therapy
     */
    public void addPriorTherapy(StudyParticipantPriorTherapy priorTherapy) {
        if (priorTherapies == null) priorTherapies = new ArrayList<StudyParticipantPriorTherapy>();
        if (priorTherapy != null) {
            priorTherapy.setAssignment(this);
            priorTherapies.add(priorTherapy);
        }
    }

    // //// BEAN PROPERTIES

    /**
     * Sets the study site.
     *
     * @param studySite the new study site
     */
    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    /**
     * Gets the study site.
     *
     * @return the study site
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_site_id")
    @Cascade({CascadeType.LOCK})
    public StudySite getStudySite() {
        return studySite;
    }

    /**
     * Sets the participant.
     *
     * @param participant the new participant
     */
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    /**
     * Gets the participant.
     *
     * @return the participant
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    @Cascade({CascadeType.LOCK})
    public Participant getParticipant() {
        return participant;
    }

    /**
     * Sets the date of enrollment.
     *
     * @param dateOfEnrollment the new date of enrollment
     */
    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    /**
     * Gets the date of enrollment.
     *
     * @return the date of enrollment
     */
    @Column(name = "date_of_enrollment")
    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    /**
     * Gets the ae reports.
     *
     * @return the ae reports
     */
    @Transient
    public List<ExpeditedAdverseEventReport> getAeReports() {
        ArrayList<ExpeditedAdverseEventReport> aeReports = new ArrayList<ExpeditedAdverseEventReport>();
        if (reportingPeriods != null) {
            for (AdverseEventReportingPeriod reportingPeriod : reportingPeriods) {
                for (ExpeditedAdverseEventReport aeReport : reportingPeriod.getAeReports()) {
                    aeReports.add(aeReport);
                }
            }
        }
        return aeReports;
    }

    /**
     * Gets the ae routine reports.
     *
     * @return the ae routine reports
     */
    @Transient
    public List<RoutineAdverseEventReport> getAeRoutineReports() {
//        if (aeRoutineReports == null) aeRoutineReports = new ArrayList<RoutineAdverseEventReport>();
        return new ArrayList<RoutineAdverseEventReport>();
    }

    /**
     * Sets the ae routine reports.
     *
     * @param aeRoutineReports the new ae routine reports
     */
    public void setAeRoutineReports(List<RoutineAdverseEventReport> aeRoutineReports) {
//        this.aeRoutineReports = aeRoutineReports;
    }

    /**
     * Gets the reporting periods.
     *
     * @return the reporting periods
     */
    @OneToMany(mappedBy = "assignment", orphanRemoval = true)
    @OrderBy(clause = "start_date desc")
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<AdverseEventReportingPeriod> getReportingPeriods() {
    	return reportingPeriods;
    }
    
    /**
     * Gets the active reporting periods.
     *
     * @return the active reporting periods
     */
    @Transient
    public List<AdverseEventReportingPeriod> getActiveReportingPeriods(){
    	List<AdverseEventReportingPeriod> activeReportingPeriods = new ArrayList<AdverseEventReportingPeriod>();
    	for(AdverseEventReportingPeriod reportingPeriod: getReportingPeriods()){
    		if(!reportingPeriod.isRetired())
    			activeReportingPeriods.add(reportingPeriod);
    	}
    	return activeReportingPeriods;
    }

    /**
     * Sets the reporting periods.
     *
     * @param reportingPeriods the new reporting periods
     */
    public void setReportingPeriods(List<AdverseEventReportingPeriod> reportingPeriods) {
        this.reportingPeriods = reportingPeriods;
    }

    /**
     * Gets the pre existing conditions.
     *
     * @return the pre existing conditions
     */
    @OneToMany(mappedBy = "assignment", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantPreExistingCondition> getPreExistingConditions() {
        return preExistingConditions;
    }

    /**
     * Sets the pre existing conditions.
     *
     * @param preExistingConditions the new pre existing conditions
     */
    public void setPreExistingConditions(List<StudyParticipantPreExistingCondition> preExistingConditions) {
        this.preExistingConditions = preExistingConditions;
    }

    /**
     * Gets the concomitant medications.
     *
     * @return the concomitant medications
     */
    @OneToMany(mappedBy = "assignment", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantConcomitantMedication> getConcomitantMedications() {
        return concomitantMedications;
    }

    /**
     * Sets the concomitant medications.
     *
     * @param concomitantMedications the new concomitant medications
     */
    public void setConcomitantMedications(List<StudyParticipantConcomitantMedication> concomitantMedications) {
        this.concomitantMedications = concomitantMedications;
    }

    /**
     * Gets the prior therapies.
     *
     * @return the prior therapies
     */
    @OneToMany(mappedBy = "assignment", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<StudyParticipantPriorTherapy> getPriorTherapies() {
        return priorTherapies;
    }

    /**
     * Sets the prior therapies.
     *
     * @param priorTherapies the new prior therapies
     */
    public void setPriorTherapies(List<StudyParticipantPriorTherapy> priorTherapies) {
        this.priorTherapies = priorTherapies;
    }

    /**
     * Gets the disease history.
     *
     * @return the disease history
     */
    @OneToOne(mappedBy = "assignment", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    public StudyParticipantDiseaseHistory getDiseaseHistory() {
        return diseaseHistory;
    }

    /**
     * Sets the disease history.
     *
     * @param diseaseHistory the new disease history
     */
    public void setDiseaseHistory(StudyParticipantDiseaseHistory diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    /**
     * Gets the lab loads.
     *
     * @return the lab loads
     */
    @OneToMany(mappedBy = "assignment")
    @OrderBy(clause = "lab_date desc")
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<LabLoad> getLabLoads() {

        if (labLoads == null) labLoads = new ArrayList<LabLoad>();

        return labLoads;

    }

    /**
     * Sets the lab loads.
     *
     * @param labLoads the new lab loads
     */
    public void setLabLoads(List<LabLoad> labLoads) {

        this.labLoads = labLoads;

    }

    /**
     * Gets the load status.
     *
     * @return the load status
     */
    public Integer getLoadStatus() {
        return loadStatus;
    }

    /**
     * Sets the load status.
     *
     * @param loadStatus the new load status
     */
    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    /**
     * Gets the start date of first course.
     *
     * @return the start date of first course
     */
    @Column(name = "first_course_date")
    public Date getStartDateOfFirstCourse() {
        return startDateOfFirstCourse;
    }

    /**
     * Sets the start date of first course.
     *
     * @param startDateOfFirstCourse the new start date of first course
     */
    public void setStartDateOfFirstCourse(Date startDateOfFirstCourse) {
        this.startDateOfFirstCourse = startDateOfFirstCourse;
    }

    /**
     * Gets the baseline performance.
     *
     * @return the baseline performance
     */
    public String getBaselinePerformance() {
        return baselinePerformance;
    }

    /**
     * Sets the baseline performance.
     *
     * @param baselinePerformance the new baseline performance
     */
    public void setBaselinePerformance(String baselinePerformance) {
        this.baselinePerformance = baselinePerformance;
    }

    // //// OBJECT METHODS

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StudyParticipantAssignment that = (StudyParticipantAssignment) o;

        if (dateOfEnrollment != null ? !dateOfEnrollment.equals(that.dateOfEnrollment)
                : that.dateOfEnrollment != null) return false;
        if (studySite != null ? !studySite.equals(that.studySite) : that.studySite != null) return false;
        // Participant#equals calls this method, so we can't use it here
        if (!DomainObjectTools.equalById(participant, that.participant)) return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result;
        result = (studySite != null ? studySite.hashCode() : 0);
        result = 29 * result + (participant != null ? participant.hashCode() : 0);
        result = 29 * result + (dateOfEnrollment != null ? dateOfEnrollment.hashCode() : 0);
        return result;
    }

    /**
     * Gets the study subject identifier.
     *
     * @return the study subject identifier
     */
    public String getStudySubjectIdentifier() {
        return studySubjectIdentifier;
    }

    /**
     * Sets the study subject identifier.
     *
     * @param studySubjectIdentifier the new study subject identifier
     */
    public void setStudySubjectIdentifier(final String studySubjectIdentifier) {
        this.studySubjectIdentifier = studySubjectIdentifier;
    }


    /**
     * Synchronize medical history from report to assignment.
     *
     * @param expeditedAdverseEventReport the expedited adverse event report
     */
    public void synchronizeMedicalHistoryFromReportToAssignment(ExpeditedAdverseEventReport expeditedAdverseEventReport) {
        if (expeditedAdverseEventReport == null) {
            return;
        }
        boolean reportExists = false;
        if (expeditedAdverseEventReport.getAssignment() != null && expeditedAdverseEventReport.getAssignment().getId().equals(this.getId())) {
            reportExists = true;
        }

        if (!reportExists) {
            throw new CaaersSystemException(String.format("Wrong uses of synchronizeMedicalHistoryFromReportToAssignment. " +
                    "This report %s does not belong to this assigment %s ", expeditedAdverseEventReport.getId(), this.getParticipant().getFullName()));
        }
        
        //now synchronize from report to assignment
        syncrhonizePriorTherapies(expeditedAdverseEventReport.getSaeReportPriorTherapies());
        syncrhonizeConcomitantMedication(expeditedAdverseEventReport.getConcomitantMedications());
        syncrhonizeDiseaseHistory(expeditedAdverseEventReport.getDiseaseHistory());
        syncrhonizePreExistingCondition(expeditedAdverseEventReport.getSaeReportPreExistingConditions());
        synchronizeBaselinePerformance(expeditedAdverseEventReport.getParticipantHistory().getBaselinePerformanceStatus());
    }

    /**
     * Synchronize baseline performance.
     *
     * @param baselinePerformanceStatus the baseline performance status
     */
    private void synchronizeBaselinePerformance(String baselinePerformanceStatus) {
        if (StringUtils.isNotEmpty(baselinePerformanceStatus))
            this.setBaselinePerformance(baselinePerformanceStatus);
    }
    
    /**
     * Will return the Prior therapy equal to the one provided, if any
     * Accordingly to business rules, 2 prior therapies are equals if they have the same name, othername, startDate.YEAR, startDate.MONTH
     *
     * @param priorTherapy the prior therapy
     * @return the study participant prior therapy
     */
    public StudyParticipantPriorTherapy containsPriorTherapy(SAEReportPriorTherapy priorTherapy) {
        if (priorTherapy == null) return null;
        if (priorTherapy.getPriorTherapy() == null && priorTherapy.getOther() == null) return null;

        for (StudyParticipantPriorTherapy spaPriorTherapy : getPriorTherapies()) {
            boolean n1 = spaPriorTherapy.getName() == null;
            boolean n2 = priorTherapy.getName() == null;

            if (!(n1 & n2) && (n1 | n2)) continue;

            if ((n1 && n2) || (spaPriorTherapy.getName().equals(priorTherapy.getName()))) {
                boolean a = spaPriorTherapy.getStartDate() == null;
                boolean b = priorTherapy.getStartDate() == null;

                // both are null
                if (a && b) return spaPriorTherapy;

                // exactly one is null
                if (!(a & b) && (a | b)) continue;

                // both dates are not null
                Integer y1 = spaPriorTherapy.getStartDate().getYear();
                Integer y2 = priorTherapy.getStartDate().getYear();
                Integer m1 = spaPriorTherapy.getStartDate().getMonth();
                Integer m2 = priorTherapy.getStartDate().getMonth();

                if (y1 == null) y1 = new Integer(0);
                if (y2 == null) y2 = new Integer(0);
                if (m1 == null) m1 = new Integer(0);
                if (m2 == null) m2 = new Integer(0);
                
                if (y1.equals(y2) && m1.equals(m2)) return spaPriorTherapy;
            } 
        }
        return null;
    }

    /*
    * Synchronizes the PriorTherapy from ExpeditedFlow(Report) to ParticipantFlow(Assignment).
    * Accordingly to business rules, 2 prior therapies are equals if they have the same name, othername, startDate.YEAR, startDate.MONTH
    * In the process of synchronization only non-repeating objects are cloned from AE Flow to Participant Flow. The existing PTs are updated copying
    * all other information which is not included in the equal rule.   
    * */
    /**
     * Syncrhonize prior therapies.
     *
     * @param saeReportPriorTherapies the sae report prior therapies
     */
    private void syncrhonizePriorTherapies(final List<SAEReportPriorTherapy> saeReportPriorTherapies) {
        for (SAEReportPriorTherapy saeReportPriorTherapy : saeReportPriorTherapies) {
            if(saeReportPriorTherapy.getPriorTherapy().isRetired() ) continue;

            StudyParticipantPriorTherapy spaPT = containsPriorTherapy(saeReportPriorTherapy);

            if (spaPT == null) {
                StudyParticipantPriorTherapy priorTherapy = StudyParticipantPriorTherapy.createAssignmentPriorTherapy(saeReportPriorTherapy);
                addPriorTherapy(priorTherapy);
            } else {
                spaPT.setEndDate(saeReportPriorTherapy.getEndDate());
                spaPT.getStartDate().setDay(saeReportPriorTherapy.getStartDate().getDay());
                spaPT.setOther(saeReportPriorTherapy.getOther());
                for (PriorTherapyAgent priorTherapyAgent : saeReportPriorTherapy.getPriorTherapyAgents()) {
                    spaPT.addUniquePriorTherapyAgent(StudyParticipantPriorTherapyAgent.createAssignmentPriorTherapyAgent(priorTherapyAgent));
                }
            }
        }
    }
    
    /**
     * Will return true, if the {@link ConcomitantMedication} is associated to this assignment via {@link StudyParticipantConcomitantMedication}.
     *
     * @param concomitantMedication the concomitant medication
     * @return true, if successful
     */
    public boolean containsConcomitantMedication(ConcomitantMedication concomitantMedication){
    	if(concomitantMedication == null || concomitantMedication.getAgentName() == null) return true;
        for(StudyParticipantConcomitantMedication spaConMed : getConcomitantMedications()){
            if (spaConMed == null || spaConMed.getAgentName() == null) continue;
            if (spaConMed.getAgentName().equals(concomitantMedication.getAgentName())) return true;
    	}
    	return false;
    }
    
    /**
     * Syncrhonize concomitant medication.
     *
     * @param saeReportConcomitantMedications the sae report concomitant medications
     */
    private void syncrhonizeConcomitantMedication(final List<ConcomitantMedication> saeReportConcomitantMedications) {

        for (ConcomitantMedication saeReportConcomitantMedication : saeReportConcomitantMedications) {
            if (!containsConcomitantMedication(saeReportConcomitantMedication)) {
                StudyParticipantConcomitantMedication studyParticipantConcomitantMedication = StudyParticipantConcomitantMedication.createAssignmentConcomitantMedication(saeReportConcomitantMedication);
                addConcomitantMedication(studyParticipantConcomitantMedication);
            }
        }

    }
    
    /**
     * Will return true, if the {@link MetastaticDiseaseSite} is associated to the assignment via {@link StudyParticipantMetastaticDiseaseSite}.
     *
     * @param metastaticDiseaseSite the metastatic disease site
     * @return true, if successful
     */
    public boolean containsMetastaticDiseaseSite(MetastaticDiseaseSite metastaticDiseaseSite){
    	if(metastaticDiseaseSite == null ) return true;
    	if(getDiseaseHistory() == null) return true;
    	for(StudyParticipantMetastaticDiseaseSite spaSite : getDiseaseHistory().getMetastaticDiseaseSites()){
    		if(metastaticDiseaseSite.equals(spaSite.getCodedSite(), spaSite.getOtherSite())) return true;
    	}
    	return false;
    }
    
    /**
     * Syncrhonize disease history.
     *
     * @param saeReportDiseaseHistory the sae report disease history
     */
    private void syncrhonizeDiseaseHistory(final DiseaseHistory saeReportDiseaseHistory) {

        // Disease name
        if (this.getDiseaseHistory() == null) return;
        DiseaseCodeTerm dct = saeReportDiseaseHistory.getReport().getStudy().getDiseaseTerminology().getDiseaseCodeTerm();
        if (dct == DiseaseCodeTerm.MEDDRA) {
            this.getDiseaseHistory().setMeddraStudyDisease(saeReportDiseaseHistory.getMeddraStudyDisease());
        }
        if (dct == DiseaseCodeTerm.CTEP) {
            this.getDiseaseHistory().setCtepStudyDisease(saeReportDiseaseHistory.getCtepStudyDisease());
        }
        if (dct == DiseaseCodeTerm.OTHER) {
            this.getDiseaseHistory().setOtherCondition(saeReportDiseaseHistory.getOtherCondition());
        }

        // Primary site of disease
        this.getDiseaseHistory().setCodedPrimaryDiseaseSite(saeReportDiseaseHistory.getCodedPrimaryDiseaseSite());

        // Date of initial diagnosis
        this.getDiseaseHistory().setDiagnosisDate(saeReportDiseaseHistory.getDiagnosisDate());

        //
        if (saeReportDiseaseHistory != null && getDiseaseHistory() != null) {
            for (MetastaticDiseaseSite metastaticDiseaseSite : saeReportDiseaseHistory.getMetastaticDiseaseSites()) {
                if (!containsMetastaticDiseaseSite(metastaticDiseaseSite)) {
                    StudyParticipantMetastaticDiseaseSite assignmentMetastaticDiseaseSite = StudyParticipantMetastaticDiseaseSite.createAssignmentMetastaticDiseaseSite(metastaticDiseaseSite);
                    getDiseaseHistory().addMetastaticDiseaseSite(assignmentMetastaticDiseaseSite);
                }
            }
        }


    }
    
    /**
     * Will return true, if the {@link PreExistingCondition} is associated to this assignment via, {@link StudyParticipantPreExistingCondition}.
     *
     * @param saePreCond the sae pre cond
     * @return true, if successful
     */
    public boolean containsPreExistingCondition(SAEReportPreExistingCondition saePreCond){
    	if(saePreCond == null) return true;
    	for(StudyParticipantPreExistingCondition spaPreCond : getPreExistingConditions()){
    		if(saePreCond.equals(spaPreCond.getPreExistingCondition(),spaPreCond.getOther())) return true;
    	}
    	return false;
    }
    
    /**
     * Syncrhonize pre existing condition.
     *
     * @param saeReportPreExistingConditions the sae report pre existing conditions
     */
    private void syncrhonizePreExistingCondition(final List<SAEReportPreExistingCondition> saeReportPreExistingConditions) {

        for (SAEReportPreExistingCondition saeReportPreExistingCondition : saeReportPreExistingConditions) {
            if(saeReportPreExistingCondition.getPreExistingCondition().isRetired()) continue;

            if (!containsPreExistingCondition(saeReportPreExistingCondition)) {
                StudyParticipantPreExistingCondition studyParticipantPreExistingCondition = StudyParticipantPreExistingCondition.createAssignmentPreExistingCondition(saeReportPreExistingCondition);
                addPreExistingCondition(studyParticipantPreExistingCondition);
            }
        }

    }
    
    /**
     * Gets the max cycle number.
     *
     * @return the max cycle number
     */
    @Transient
    public Integer getMaxCycleNumber() {
    	Integer maxCycleNumber = null;
    	if(reportingPeriods != null){
    		for(AdverseEventReportingPeriod reportingPeriod : reportingPeriods){
    			Integer rpCycle = reportingPeriod.getCycleNumber();
    			if(rpCycle == null) continue;
    			if(maxCycleNumber == null || rpCycle > maxCycleNumber) maxCycleNumber = rpCycle;
        	}	
    	}
    	return maxCycleNumber;
    }

	/**
	 * Gets the reporting period.
	 *
	 * @param courseStartDate the course start date
	 * @return the reporting period
	 */
	@Transient
	public AdverseEventReportingPeriod getReportingPeriod(Date courseStartDate) {
		for (AdverseEventReportingPeriod p : getActiveReportingPeriods()) {
			if (p.getStartDate()!=null && DateUtils.isSameDay(courseStartDate, p.getStartDate())) {
				return p;
			}
		}
		return null;
	}

}
