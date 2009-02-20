package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.axis.utils.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * This class represents the Reporting Period associated to StudyParticipant Associations
 *
 * @author Sameer Sawant
 */
@Entity
@Table(name = "ae_reporting_periods")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ae_reporting_periods_id") })
public class AdverseEventReportingPeriod extends AbstractMutableDomainObject implements WorkflowAware{
	private static final String BASELINE_REPORTING_TYPE = "Baseline";
	
	private String description;
	
	private Integer cycleNumber;
	
	private Integer workflowId;
	
	private ReviewStatus reviewStatus;
	
	//private ExpeditedAdverseEventReport expeditedAdverseEventReport;
	
	private TreatmentAssignment treatmentAssignment;
	
	private Epoch epoch;
	
	private Date startDate;
	
	private Date endDate;
	
	private StudyParticipantAssignment assignment;
	
	private List<AdverseEvent> adverseEvents;
	
	private List<ExpeditedAdverseEventReport> aeReports;
	
	private String name;
	
	private SimpleDateFormat formatter;
	
	private boolean baselineReportingType;
	
	// This holds the total number of reports within all the ExpeditedReport generated in this reporting period
	private int numberOfReports;
	
	// This gives the Data Entry Status for ths reporing Period
	private String dataEntryStatus;
	
	// This gives the Report Status for the reporting Period
	private String reportStatus;
	
	// Evaluated adverse Events
	private List<AdverseEvent> evaluatedAdverseEvents;
	
	// Reportable adverse Events (not associated to any report)
	private List<AdverseEvent> reportableAdverseEvents;
	
	private List<ReportingPeriodReviewComment> reviewComments;
	
	
	public AdverseEventReportingPeriod() {
		formatter = new SimpleDateFormat("MM/dd/yy");
    }
	
	//LOGIC
	public void addAdverseEvent(AdverseEvent adverseEvent){
    	getAdverseEvents().add(adverseEvent);
    	adverseEvent.setReportingPeriod(this);
    }
	
	// BEAN PROPERTIES
    
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
        summary.put("Participant", getParticipantSummaryLine());
        summary.put("Study", getStudySummaryLine());
        summary.put("Adverse event count", Integer.toString(getAdverseEvents().size()));

        return summary;
    }
    
    @Transient
    public String getParticipantSummaryLine() {
        Participant participant = getParticipant();
        if (participant == null) return null;
        StringBuilder sb = new StringBuilder(participant.getFullName());
        appendPrimaryIdentifier(participant, sb);
        return sb.toString();
    }

    @Transient
    public String getStudySummaryLine() {
        Study study = getStudy();
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
   
    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany(mappedBy = "reportingPeriod")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @OrderBy("grade desc")
    public List<AdverseEvent> getAdverseEvents() {
    	if (adverseEvents == null) adverseEvents = new ArrayList<AdverseEvent>();
        return adverseEvents;
    }

    public void setAdverseEvents(final List<AdverseEvent> adverseEvents) {
        this.adverseEvents = adverseEvents;
    }
    
    @Transient
    public List<AdverseEvent> getEvaluatedAdverseEvents(){
    	evaluatedAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: this.getAdverseEvents()){
    		if(ae.getGrade() != null && !(ae.getGrade().equals(Grade.NOT_EVALUATED)))
    			evaluatedAdverseEvents.add(ae);
    	}
    	return evaluatedAdverseEvents;
    }
    
    @Transient
    public List<AdverseEvent> getReportableAdverseEvents(){
    	reportableAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getEvaluatedAdverseEvents()){
    		if(ae.getReport() == null)
    			reportableAdverseEvents.add(ae);
    	}
    	return reportableAdverseEvents;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value={CascadeType.MERGE, CascadeType.LOCK})
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_assignment_id")
    @Cascade(value = { CascadeType.LOCK })
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }
    
    public String getDescription(){
    	return description;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }

    public Integer getCycleNumber() {
		return cycleNumber;
	}
    
    public void setCycleNumber(Integer cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
    
    public Integer getWorkflowId() {
    	return workflowId;
    }
    
    public void setWorkflowId(Integer workflowId){
    	this.workflowId = workflowId;
    }
    
    @Column(name = "review_status_code")
    @Type(type = "reviewStatus")
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
    
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Epoch getEpoch(){
    	return epoch;
    }
    
    public void setEpoch(Epoch epoch){
    	this.epoch = epoch;
    }
    
    @OneToMany(mappedBy = "reportingPeriod")
    @Cascade(value = { CascadeType.DELETE, CascadeType.DELETE_ORPHAN })
    public List<ExpeditedAdverseEventReport> getAeReports() {
    	if(this.aeReports == null) this.aeReports = new ArrayList<ExpeditedAdverseEventReport>();
    	return aeReports;
	}
    
    public void setAeReports(List<ExpeditedAdverseEventReport> aeReports) {
		this.aeReports = aeReports;
	}
    
    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "rp_id", nullable = true)
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @org.hibernate.annotations.OrderBy(clause="created_date desc")
    public List<ReportingPeriodReviewComment> getReviewComments() {
		return reviewComments;
	}
    
    public void setReviewComments(List<ReportingPeriodReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}
    
    public void addAeReport(ExpeditedAdverseEventReport aeReport){
    	if(aeReport == null) return;
    	aeReport.setReportingPeriod(this);
    	getAeReports().add(aeReport);
    	
    }
    
    @Transient
    public String getName() {
    	
    	if(StringUtils.isEmpty(name)){
    		StringBuffer sb = new StringBuffer();
    		sb.append((getCycleNumber() != null)? "Cycle #: " + getCycleNumber() + "; " : "")
    		// .append((getTreatmentAssignment() != null) ? "TAC: " + getTreatmentAssignment().getCode() + ";" : "")
    		.append((startDate != null) ? "Start Date: " + formatter.format(startDate) : "");
    		name = sb.toString();
    	}
    	
		
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    @Transient
    public boolean isBaselineReportingType(){
    	if(this.getEpoch() != null)
    		return getEpoch().getName().equals(BASELINE_REPORTING_TYPE);
    	return false;
    }
    
    @Transient
    public int getNumberOfReports(){
    	int count = 0;
    	for(ExpeditedAdverseEventReport report: this.getAeReports()){
    		count += report.getReports().size();
    	}
    	return count;
    }
    
    @Transient
    public int getNumberOfAEs(){
    	int count = 0;
    	for(ExpeditedAdverseEventReport aeReport : getAeReports())
    		count += aeReport.getNumberOfAes();
    	return count;
    }
    
    @Transient
    public String getDataEntryStatus(){
    	return "In-progress";
    }
    
    /**
     * Will tell the combined submission status of individual expedited reports
     * 
     * @return {@link ReportStatus}.COMPLETED -When all reports are submitted sucessfully or (withdrawn), {@link ReportStatus}.PENDING when any of the report is pending,inprocess or failed.
     */
    @Transient
    public String getReportStatus(){
    	if(getAeReports().isEmpty()) return "No Reports";
    	
    	// If for any reports associated to all the Data Collection has status other than COMPLETED
    	// or WITHDRAWN then return a status "Report(s) Due" or else return a status "Report(s) Completed"
    	
    	for(ExpeditedAdverseEventReport aeReport: this.getAeReports()){
    		for(Report report: aeReport.getReports()){
    			ReportStatus status = report.getLastVersion().getReportStatus();
    			if(status == ReportStatus.PENDING   || status == ReportStatus.INPROCESS || status == ReportStatus.FAILED){
    				return "Report(s) Due";
    			}
    		}	
    	}
    	
    	return "Report(s) Completed";
    }
    
    /**
     * This returns the string that is used as a name in ProcessInstance and TaskInstance (workflow related tables)
     * 
     * @return String
     */
    @Transient
    public String getWorkflowIdentifier(){
    	return "routineFlow-" + getId();
    }
    
    /**
     *  Checks whether a different AE with the same term is avilable in this reporting period
     * @param ae
     * @return
     */
    public boolean hasDiffrentAEWithSameTerm(AdverseEvent otherAE){
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.getId().equals(otherAE.getId())) continue;
    		if(ae.getAdverseEventCtcTerm() == null || otherAE.getAdverseEventTerm() == null)  continue;
    		if(ae.getAdverseEventTerm().equals(otherAE.getAdverseEventTerm())) return true;
    	}
    	return false;
    }
}