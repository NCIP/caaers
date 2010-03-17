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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "participant_assignments")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_participant_assignments_id")})
@Where(clause = "load_status > 0")
public class StudyParticipantAssignment extends AbstractMutableDomainObject {

    private Participant participant;
    private StudySite studySite;

    private Date dateOfEnrollment;


    //private List<RoutineAdverseEventReport> aeRoutineReports;

    private List<LabLoad> labLoads;

    private Integer loadStatus = LoadStatus.COMPLETE.getCode();

    private String studySubjectIdentifier;

    private Date startDateOfFirstCourse;

    private List<AdverseEventReportingPeriod> reportingPeriods;

    private List<StudyParticipantPreExistingCondition> preExistingConditions;
    private List<StudyParticipantConcomitantMedication> concomitantMedications;
    private List<StudyParticipantPriorTherapy> priorTherapies;
    private StudyParticipantDiseaseHistory diseaseHistory;
    private String baselinePerformance;

    public StudyParticipantAssignment(Participant participant, StudySite studySite) {
        this.participant = participant;
        this.studySite = studySite;
        this.dateOfEnrollment = new Date();
    }

    public StudyParticipantAssignment() {
    }

    // //// LOGIC


    public void addRoutineReport(RoutineAdverseEventReport routineReport) {
        routineReport.setAssignment(this);
        getAeRoutineReports().add(routineReport);
    }

    public void addReportingPeriod(AdverseEventReportingPeriod reportingPeriod) {
        if (reportingPeriods == null) reportingPeriods = new ArrayList<AdverseEventReportingPeriod>();
        if (reportingPeriod != null) {
            reportingPeriod.setAssignment(this);
            reportingPeriods.add(reportingPeriod);
        }
    }

    public void addPreExistingCondition(StudyParticipantPreExistingCondition preExistingCondition) {
        if (preExistingConditions == null)
            preExistingConditions = new ArrayList<StudyParticipantPreExistingCondition>();
        if (preExistingCondition != null) {
            preExistingCondition.setAssignment(this);
            preExistingConditions.add(preExistingCondition);
        }
    }

    public void addConcomitantMedication(StudyParticipantConcomitantMedication concomitantMedication) {
        if (concomitantMedications == null)
            concomitantMedications = new ArrayList<StudyParticipantConcomitantMedication>();
        if (concomitantMedication != null) {
            concomitantMedication.setAssignment(this);
            concomitantMedications.add(concomitantMedication);
        }
    }

    public void addPriorTherapy(StudyParticipantPriorTherapy priorTherapy) {
        if (priorTherapies == null) priorTherapies = new ArrayList<StudyParticipantPriorTherapy>();
        if (priorTherapy != null) {
            priorTherapy.setAssignment(this);
            priorTherapies.add(priorTherapy);
        }
    }

    // //// BEAN PROPERTIES

    public void setStudySite(StudySite studySite) {
        this.studySite = studySite;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_site_id")
    @Cascade({CascadeType.LOCK})
    public StudySite getStudySite() {
        return studySite;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    @Cascade({CascadeType.LOCK})
    public Participant getParticipant() {
        return participant;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    @Column(name = "date_of_enrollment")
    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

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

    @Transient
    public List<RoutineAdverseEventReport> getAeRoutineReports() {
//        if (aeRoutineReports == null) aeRoutineReports = new ArrayList<RoutineAdverseEventReport>();
        return new ArrayList<RoutineAdverseEventReport>();
    }

    public void setAeRoutineReports(List<RoutineAdverseEventReport> aeRoutineReports) {
//        this.aeRoutineReports = aeRoutineReports;
    }

    @OneToMany(mappedBy = "assignment")
    @OrderBy(clause = "start_date desc")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<AdverseEventReportingPeriod> getReportingPeriods() {
        return reportingPeriods;
    }

    public void setReportingPeriods(List<AdverseEventReportingPeriod> reportingPeriods) {
        this.reportingPeriods = reportingPeriods;
    }

    @OneToMany(mappedBy = "assignment")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudyParticipantPreExistingCondition> getPreExistingConditions() {
        return preExistingConditions;
    }

    public void setPreExistingConditions(List<StudyParticipantPreExistingCondition> preExistingConditions) {
        this.preExistingConditions = preExistingConditions;
    }

    @OneToMany(mappedBy = "assignment")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudyParticipantConcomitantMedication> getConcomitantMedications() {
        return concomitantMedications;
    }

    public void setConcomitantMedications(List<StudyParticipantConcomitantMedication> concomitantMedications) {
        this.concomitantMedications = concomitantMedications;
    }

    @OneToMany(mappedBy = "assignment")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<StudyParticipantPriorTherapy> getPriorTherapies() {
        return priorTherapies;
    }

    public void setPriorTherapies(List<StudyParticipantPriorTherapy> priorTherapies) {
        this.priorTherapies = priorTherapies;
    }

    @OneToOne(mappedBy = "assignment")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public StudyParticipantDiseaseHistory getDiseaseHistory() {
        return diseaseHistory;
    }

    public void setDiseaseHistory(StudyParticipantDiseaseHistory diseaseHistory) {
        this.diseaseHistory = diseaseHistory;
    }

    @OneToMany(mappedBy = "assignment")
    @OrderBy(clause = "lab_date desc")
    public List<LabLoad> getLabLoads() {

        if (labLoads == null) labLoads = new ArrayList<LabLoad>();

        return labLoads;

    }

    public void setLabLoads(List<LabLoad> labLoads) {

        this.labLoads = labLoads;

    }

    public Integer getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(Integer loadStatus) {
        this.loadStatus = loadStatus;
    }

    @Column(name = "first_course_date")
    public Date getStartDateOfFirstCourse() {
        return startDateOfFirstCourse;
    }

    public void setStartDateOfFirstCourse(Date startDateOfFirstCourse) {
        this.startDateOfFirstCourse = startDateOfFirstCourse;
    }

    public String getBaselinePerformance() {
        return baselinePerformance;
    }

    public void setBaselinePerformance(String baselinePerformance) {
        this.baselinePerformance = baselinePerformance;
    }

    // //// OBJECT METHODS

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

    @Override
    public int hashCode() {
        int result;
        result = (studySite != null ? studySite.hashCode() : 0);
        result = 29 * result + (participant != null ? participant.hashCode() : 0);
        result = 29 * result + (dateOfEnrollment != null ? dateOfEnrollment.hashCode() : 0);
        return result;
    }

    public String getStudySubjectIdentifier() {
        return studySubjectIdentifier;
    }

    public void setStudySubjectIdentifier(final String studySubjectIdentifier) {
        this.studySubjectIdentifier = studySubjectIdentifier;
    }


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

    private void synchronizeBaselinePerformance(String baselinePerformanceStatus) {
        if (StringUtils.isNotEmpty(baselinePerformanceStatus))
            this.setBaselinePerformance(baselinePerformanceStatus);
    }
    
    /**
     * Will return the Prior therapy equal to the one provided, if any
      Accordingly to business rules, 2 prior therapies are equals if they have the same name, othername, startDate.YEAR, startDate.MONTH
     * @param priorTherapy
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
    private void syncrhonizePriorTherapies(final List<SAEReportPriorTherapy> saeReportPriorTherapies) {
        for (SAEReportPriorTherapy saeReportPriorTherapy : saeReportPriorTherapies) {
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
     * Will return true, if the {@link ConcomitantMedication} is associated to this assignment via {@link StudyParticipantConcomitantMedication}
     * @param concomitantMedication
     * @return
     */
    public boolean containsConcomitantMedication(ConcomitantMedication concomitantMedication){
    	if(concomitantMedication == null || concomitantMedication.getAgentName() == null) return true;
        for(StudyParticipantConcomitantMedication spaConMed : getConcomitantMedications()){
            if (spaConMed == null || spaConMed.getAgentName() == null) continue;
            if (spaConMed.getAgentName().equals(concomitantMedication.getAgentName())) return true;
    	}
    	return false;
    }
    private void syncrhonizeConcomitantMedication(final List<ConcomitantMedication> saeReportConcomitantMedications) {

        for (ConcomitantMedication saeReportConcomitantMedication : saeReportConcomitantMedications) {
            if (!containsConcomitantMedication(saeReportConcomitantMedication)) {
                StudyParticipantConcomitantMedication studyParticipantConcomitantMedication = StudyParticipantConcomitantMedication.createAssignmentConcomitantMedication(saeReportConcomitantMedication);
                addConcomitantMedication(studyParticipantConcomitantMedication);
            }
        }

    }
    /**
     * Will return true, if the {@link MetastaticDiseaseSite} is associated to the assignment via {@link StudyParticipantMetastaticDiseaseSite}
     * @param metastaticDiseaseSite
     * @return
     */
    public boolean containsMetastaticDiseaseSite(MetastaticDiseaseSite metastaticDiseaseSite){
    	if(metastaticDiseaseSite == null ) return true;
    	if(getDiseaseHistory() == null) return true;
    	for(StudyParticipantMetastaticDiseaseSite spaSite : getDiseaseHistory().getMetastaticDiseaseSites()){
    		if(metastaticDiseaseSite.equals(spaSite.getCodedSite(), spaSite.getOtherSite())) return true;
    	}
    	return false;
    }
    
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
     * Will return true, if the {@link PreExistingCondition} is associated to this assignment via, {@link StudyParticipantPreExistingCondition}
     * @param saePreCond
     * @return
     */
    public boolean containsPreExistingCondition(SAEReportPreExistingCondition saePreCond){
    	if(saePreCond == null) return true;
    	for(StudyParticipantPreExistingCondition spaPreCond : getPreExistingConditions()){
    		if(saePreCond.equals(spaPreCond.getPreExistingCondition(),spaPreCond.getOther())) return true;
    	}
    	return false;
    }
    private void syncrhonizePreExistingCondition(final List<SAEReportPreExistingCondition> saeReportPreExistingConditions) {

        for (SAEReportPreExistingCondition saeReportPreExistingCondition : saeReportPreExistingConditions) {
            if (!containsPreExistingCondition(saeReportPreExistingCondition)) {
                StudyParticipantPreExistingCondition studyParticipantPreExistingCondition = StudyParticipantPreExistingCondition.createAssignmentPreExistingCondition(saeReportPreExistingCondition);
                addPreExistingCondition(studyParticipantPreExistingCondition);
            }
        }

    }
    
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

}
