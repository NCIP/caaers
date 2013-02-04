package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.comparator.AdverseEventComprator;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.workflow.ReportingPeriodReviewComment;
import gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * This class represents the Reporting Period associated to StudyParticipant Associations.
 *
 * @author Sameer Sawant
 * @author Biju Joseph
 */
@Entity
@Table(name = "ae_reporting_periods")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ae_reporting_periods_id") })
public class AdverseEventReportingPeriod extends AbstractMutableRetireableDomainObject implements WorkflowAware, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5343583772734352886L;

	/** The Constant BASELINE_REPORTING_TYPE. */
	private static final String BASELINE_REPORTING_TYPE = "Baseline";
	
	/** The description. */
	private String description;
	
	/** The cycle number. */
	private Integer cycleNumber;
	
	/** The workflow id. */
	private Integer workflowId;
	
	/** The review status. */
	private ReviewStatus reviewStatus;
	
	/** The treatment assignment. */
	private TreatmentAssignment treatmentAssignment;
	
	/** The treatment assignment description. */
	private String treatmentAssignmentDescription;
	
	/** The epoch. */
	private Epoch epoch;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The assignment. */
	private StudyParticipantAssignment assignment;
	
	/** The adverse events. */
	private List<AdverseEvent> adverseEvents;
	
	/** The ae reports. */
	private List<ExpeditedAdverseEventReport> aeReports;
	
	/** The name. */
	private String name;

    private String oldAeMapping;
	
	/** The baseline reporting type. */
	private boolean baselineReportingType;
	
	// This holds the total number of reports within all the ExpeditedReport generated in this reporting period
	/** The number of reports. */
	private int numberOfReports;
	
	// This gives the Data Entry Status for ths reporing Period
	/** The data entry status. */
	private String dataEntryStatus;
	
	// This gives the Report Status for the reporting Period
	/** The report status. */
	private String reportStatus;
	
	/** The review comments. */
	private List<ReportingPeriodReviewComment> reviewComments;
	
	/** The active ae reports. */
	private List<ExpeditedAdverseEventReport> activeAeReports;
	
	private String externalId;
	
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	/**
	 * Instantiates a new adverse event reporting period.
	 */
	public AdverseEventReportingPeriod() {

    }
	
	//LOGIC
	/**
	 * Adds the adverse event.
	 *
	 * @param adverseEvent the adverse event
	 */
	public void addAdverseEvent(AdverseEvent adverseEvent){
    	getAdverseEvents().add(adverseEvent);
    	adverseEvent.setReportingPeriod(this);
    }
	
	// BEAN PROPERTIES
    
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
        StudySite ss = getStudySite();
        return ss == null ? null : ss.getStudy();
    }

    /**
     * Gets the StudySite.
     *
     * @return the study site
     */
    @Transient
    public StudySite getStudySite() {
        StudySite ss = getAssignment() == null ? null : getAssignment().getStudySite();
        return ss;
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
   
    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the adverse events.
     *
     * @return the adverse events
     */
    @OneToMany(mappedBy = "reportingPeriod", orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @OrderBy
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<AdverseEvent> getAdverseEvents() {
    	if (adverseEvents == null) adverseEvents = new ArrayList<AdverseEvent>();
        return adverseEvents;
    }

    /**
     * Sets the adverse events.
     *
     * @param adverseEvents the new adverse events
     */
    public void setAdverseEvents(final List<AdverseEvent> adverseEvents) {
        this.adverseEvents = adverseEvents;
    }
    
    /**
     * Gets the evaluated adverse events.
     *
     * @return the evaluated adverse events
     */
    @Transient
    public List<AdverseEvent> getEvaluatedAdverseEvents(){
    	List<AdverseEvent> evaluatedAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: this.getAdverseEvents()){
    		if(ae.isRetired()) continue;
    		if(ae.getGrade() != null && !(ae.getGrade().equals(Grade.NOT_EVALUATED)))
    			evaluatedAdverseEvents.add(ae);
    	}
    	//sort the list
    	Collections.sort(evaluatedAdverseEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
    	
    	return evaluatedAdverseEvents;
    }
    
    /**
     * This method returnes all the adverse events whose attributes are populated with some values. The attributes of adverse events
     * that are checked for some values are the ones that take part in rules. These attributes are namely - grade, Hospitalization,
     * expected, participant at risk, attribution, outcome identifier. If the grade is null or normal or not evaluated then its considered
     * to be not populated. Also if hospitalization has value NONE then its considered to be not populated.
     *
     * @return the populated adverse events
     */
    @Transient
    public List<AdverseEvent> getPopulatedAdverseEvents(){
    	//Grade, Hospitalization (or prolonged hospitalization), expected, participant at increased risk, attribution, outcome identifier
    	List<AdverseEvent> populatedAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getAdverseEvents()){
    		if(ae.isPopulated())
    			populatedAdverseEvents.add(ae);
    	}
    	
    	Collections.sort(populatedAdverseEvents, AdverseEventComprator.DEFAULT_ADVERSE_EVENT_COMPARATOR);
    	return populatedAdverseEvents;
    }
    
    /**
     * This method will return a a sorted list containing the evaluated adverse events + adverse events associated to data collection, that got modified.
     *
     * @return the reportable adverse events
     * @see AdverseEventComprator#compare(AdverseEvent, AdverseEvent)
     */
    @Transient
    public List<AdverseEvent> getReportableAdverseEvents(){
    	List<AdverseEvent> reportableAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getPopulatedAdverseEvents()){
    		if(ae.getAdverseEventTerm().getTerm() != null)
    		    reportableAdverseEvents.add(ae);
    	}
    	return reportableAdverseEvents;
    }
    
    /**
     * This method will return the adverse events that are graded, but not yet associated to any
     * expedited data collection.
     *
     * @return the non expedited adverse events
     */
    @Transient
    public List<AdverseEvent> getNonExpeditedAdverseEvents(){
    	List<AdverseEvent> unReportedAes = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae : getPopulatedAdverseEvents()){
    		if(ae.getReport() == null /*&& ae.getAdverseEventTerm().getTerm() != null*/)
    			unReportedAes.add(ae);
    	}
    	
    	return unReportedAes;
    }
    
    /**
     * This method will return a a sorted list containing the newly added evaluated adverse events + adverse events associated to data collection, that got modified +
     * adverse events associated to data collection that are not reported.
     *
     * @return the modified reportable adverse events
     */
    @Transient
    public List<AdverseEvent> getModifiedReportableAdverseEvents(){
    	List<AdverseEvent> reportableAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getPopulatedAdverseEvents()){
    		if( ae.isModified() || BooleanUtils.isNotTrue(ae.getReported())) reportableAdverseEvents.add(ae);
    	}
    	return reportableAdverseEvents;
    }
    
    /**
     * This return the modified adverse events that are associated to an expedited data collection.
     *
     * @return the modified expedited adverse events
     */
    @Transient
    public List<AdverseEvent> getModifiedExpeditedAdverseEvents(){
    	List<AdverseEvent> modifiedAdverseEvents = new ArrayList<AdverseEvent>();
    	for(AdverseEvent ae: getPopulatedAdverseEvents()){
    		if( ae.getReport() != null && ae.isModified()) modifiedAdverseEvents.add(ae);
    	}
    	return modifiedAdverseEvents;
    }
    
    
    /**
     * Gets the assignment.
     *
     * @return the assignment
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(value={CascadeType.MERGE, CascadeType.LOCK})
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

    public String getOldAeMapping() {
        return oldAeMapping;
    }

    public void setOldAeMapping(String oldAeMapping) {
        this.oldAeMapping = oldAeMapping;
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
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription(){
    	return description;
    }
    
    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description){
    	this.description = description;
    }

    /**
     * Gets the cycle number.
     *
     * @return the cycle number
     */
    public Integer getCycleNumber() {
		return cycleNumber;
	}
    
    /**
     * Sets the cycle number.
     *
     * @param cycleNumber the new cycle number
     */
    public void setCycleNumber(Integer cycleNumber) {
		this.cycleNumber = cycleNumber;
	}
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#getWorkflowId()
     */
    public Integer getWorkflowId() {
    	return workflowId;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#setWorkflowId(java.lang.Integer)
     */
    public void setWorkflowId(Integer workflowId){
    	this.workflowId = workflowId;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#getReviewStatus()
     */
    @Column(name = "review_status_code")
    @Type(type = "reviewStatus")
    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }
    
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.workflow.WorkflowAware#setReviewStatus(gov.nih.nci.cabig.caaers.domain.ReviewStatus)
     */
    public void setReviewStatus(ReviewStatus reviewStatus){
    	this.reviewStatus = reviewStatus;
    }
    
    /**
     * Gets the epoch.
     *
     * @return the epoch
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Epoch getEpoch(){
    	return epoch;
    }
    
    /**
     * Sets the epoch.
     *
     * @param epoch the new epoch
     */
    public void setEpoch(Epoch epoch){
    	this.epoch = epoch;
    }
    
    /**
     * Gets the ae reports.
     *
     * @return the ae reports
     */
    @OneToMany(mappedBy = "reportingPeriod", orphanRemoval = true)
    @Cascade(value = { CascadeType.DELETE})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ExpeditedAdverseEventReport> getAeReports() {
    	if(this.aeReports == null) this.aeReports = new ArrayList<ExpeditedAdverseEventReport>();
    	return aeReports;
	}
    
    /**
     * This method returns a list of expedited aeReports that are active. An Expedited AeReport
     * is active if it has atleast on report in non-withdrawn state.
     *
     * @return the active ae reports
     */
    @Transient
    public List<ExpeditedAdverseEventReport> getActiveAeReports() {
    	activeAeReports = new ArrayList<ExpeditedAdverseEventReport>();
    	if(aeReports != null)
    	{
    		for(ExpeditedAdverseEventReport aeReport: aeReports){
    			if(isAeReportActive(aeReport))
    				activeAeReports.add(aeReport);
    		}
    	}
    	return activeAeReports;
    }
    
    /**
     * If any report associated to expedited AeReport is in non-withdrawn state,
     * the expedited aeReport is considered to be an active report.
     *
     * @param aeReport the ae report
     * @return the boolean
     */
    private Boolean isAeReportActive(ExpeditedAdverseEventReport aeReport){
    	for(Report report: aeReport.getReports()){
    		if(!report.getStatus().equals(ReportStatus.WITHDRAWN) && 
    				!report.getStatus().equals(ReportStatus.REPLACED) && 
    				!report.getStatus().equals(ReportStatus.AMENDED))
    			return true;
    	}
    	return false;
    }
    
    /**
     * Sets the ae reports.
     *
     * @param aeReports the new ae reports
     */
    public void setAeReports(List<ExpeditedAdverseEventReport> aeReports) {
		this.aeReports = aeReports;
	}
    
    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping.  See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the review comments internal.
     *
     * @return the review comments internal
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "rp_id", nullable = true)
    @IndexColumn(name = "list_index", nullable = false)
    @Cascade(value = { CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<ReportingPeriodReviewComment> getReviewCommentsInternal() {
    	if(reviewComments == null) reviewComments = new ArrayList<ReportingPeriodReviewComment>();
		return reviewComments;
	}
    
    /**
     * Sets the review comments internal.
     *
     * @param reviewComments the new review comments internal
     */
    public void setReviewCommentsInternal(List<ReportingPeriodReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}

    //http://opensource.atlassian.com/projects/hibernate/browse/HHH-2802
    /**
     * Gets the review comments.
     *
     * @return the review comments
     */
    @Transient
    public List<ReportingPeriodReviewComment> getReviewComments() {
    	ArrayList<ReportingPeriodReviewComment> comments = new ArrayList<ReportingPeriodReviewComment>(getReviewCommentsInternal());
        Collections.reverse(comments);
        return comments;
	}

    /**
     * Sets the review comments.
     *
     * @param reviewComments the new review comments
     */
    public void setReviewComments(List<ReportingPeriodReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}
    
    /**
     * Adds the ae report.
     *
     * @param aeReport the ae report
     */
    public void addAeReport(ExpeditedAdverseEventReport aeReport){
    	if(aeReport == null) return;
    	aeReport.setReportingPeriod(this);
    	getAeReports().add(aeReport);
    	
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    @Transient
    public String getName() {
    	
    	if(StringUtils.isEmpty(name)){
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
    		StringBuffer sb = new StringBuffer();
    		sb.append((getCycleNumber() != null)? "Cycle #: " + getCycleNumber() + "; " : "")
    		.append((getTreatmentAssignment() != null) ? "TAC: " + getTreatmentAssignment().getCode() + "; " : "Other")
    		.append((startDate != null) ? "Start Date: " + formatter.format(startDate) : "");
    		name = sb.toString();
    	}
    	
		
		return name;
	}
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
		this.name = name;
	}
    
    /**
     * Checks if is baseline reporting type.
     *
     * @return true, if is baseline reporting type
     */
    @Transient
    public boolean isBaselineReportingType(){
    	if(this.getEpoch() != null)
    		return getEpoch().getName().equals(BASELINE_REPORTING_TYPE);
    	return false;
    }
    
    /**
     * Gets the number of reports.
     *
     * @return the number of reports
     */
    @Transient
    public int getNumberOfReports(){
    	int count = 0;
    	for(ExpeditedAdverseEventReport report: this.getAeReports()){
    		for(Report r: report.getReports()){
    			if(!r.getStatus().equals(ReportStatus.REPLACED))
    				count++;
    		}
    	}
    	return count;
    }
        
    /**
     * Gets the number of a es.
     *
     * @return the number of a es
     */
    @Transient
    public int getNumberOfAEs(){
    	int count = 0;
    	for(ExpeditedAdverseEventReport aeReport : getAeReports())
    		count += aeReport.getNumberOfAes();
    	return count;
    }
    
    /**
     * Gets the data entry status.
     *
     * @return the data entry status
     */
    @Transient
    public String getDataEntryStatus(){
    	return "In-progress";
    }
    
    /**
     * Will tell the combined submission status of individual expedited reports.
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
    			
    			if(report.isOverdue()) return "Reports Overdue";
    			
    			ReportStatus status = report.getLastVersion().getReportStatus();
    			if(status == ReportStatus.PENDING   || status == ReportStatus.INPROCESS){
    				return "Reports Due";
    			}else if(status == ReportStatus.FAILED){
    				return "Report Submission Failed";
    			}
    		}	
    	}
    	
    	return "Reports Completed";
    }
    
    /**
     * This returns the string that is used as a name in ProcessInstance and TaskInstance (workflow related tables).
     *
     * @return String
     */
    @Transient
    public String getWorkflowIdentifier(){
    	return "routineFlow-" + getId();
    }
    
    /**
     * Checks whether a different AE with the same term is avilable in this reporting period.
     *
     * @param otherAE the other ae
     * @return true, if successful
     */
    public boolean hasDiffrentAEWithSameTerm(AdverseEvent otherAE){
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.isRetired()) continue;
    		if(ae.getId().equals(otherAE.getId())) continue;
    		if(ae.getAdverseEventTerm() == null || otherAE.getAdverseEventTerm() == null)  continue;
    		if(ae.getAdverseEventTerm().equals(otherAE.getAdverseEventTerm())) return true;
    	}
    	return false;
    }

    /**
     * Gets the treatment assignment description.
     *
     * @return the treatment assignment description
     */
    @Column(name = "treatment_assignment_desc")
    public String getTreatmentAssignmentDescription() {
        return treatmentAssignmentDescription;
    }

    /**
     * Sets the treatment assignment description.
     *
     * @param treatmentAssignmentDescription the new treatment assignment description
     */
    public void setTreatmentAssignmentDescription(String treatmentAssignmentDescription) {
        this.treatmentAssignmentDescription = StringEscapeUtils.unescapeHtml(treatmentAssignmentDescription);
    }
    
    /**
     * This method will return the earliest graded date, of reportable adverse event.
     *
     * @return the earliest adverse event graded date
     */
    @Transient
    public Date getEarliestAdverseEventGradedDate(){
    	return AdverseEventReportingPeriod.findEarliestGradedDate(getReportableAdverseEvents());
    }
    
    /**
     * This method will return the earliest post submission updated date of report-able adverse events.
     *
     * @return the earliest post submission updated date
     */
    @Transient
    public Date getEarliestPostSubmissionUpdatedDate(){
    	return AdverseEventReportingPeriod.findEarliestPostSubmissionUpdatedDate(getReportableAdverseEvents());
    }
    
    /**
     * Find earliest post submission updated date.
     *
     * @param adverseEvents the adverse events
     * @return the date
     */
    public static Date findEarliestPostSubmissionUpdatedDate(List<AdverseEvent> adverseEvents){
    	Date d = null;
    	for(AdverseEvent ae : adverseEvents){
    		if(ae.getPostSubmissionUpdatedDate() == null) continue;
    		
    		if(d == null){
    			d = ae.getPostSubmissionUpdatedDate();
    		}else{
    			d = (DateUtils.compateDateAndTime(ae.getPostSubmissionUpdatedDate(), d) < 0) ? ae.getPostSubmissionUpdatedDate() : d;
    		}
    	}
    	return d;
    }
    
    
    /**
     * Find earliest graded date.
     *
     * @param adverseEvents the adverse events
     * @return the date
     */
    public static Date findEarliestGradedDate(List<AdverseEvent> adverseEvents){
    	Date d = null;
    	for(AdverseEvent ae : adverseEvents){
    		if(ae.getGradedDate() == null) continue;
    		
    		if(d == null){
    			d = ae.getGradedDate();
    		}else{
    			d = (DateUtils.compateDateAndTime(ae.getGradedDate(), d) < 0) ? ae.getGradedDate() : d;
    		}
    	}
    	return d;
    }

    public AdverseEvent findAdverseEventByIdTermAndDates(AdverseEvent thatAe){
        for(AdverseEvent thisAe : getAdverseEvents()){
            //are Ids matching ?
            if(thatAe.getId() != null && thisAe.getId() != null && thisAe.getId().equals(thatAe.getId()) ) return thatAe;
            if(thatAe.getExternalId() != null && thisAe.getExternalId() != null && thisAe.getExternalId().equals(thatAe.getExternalId()) ) return thatAe;

            //are dates matching ?
            if(DateUtils.compareDate(thisAe.getStartDate(), thatAe.getStartDate()) != 0)  continue;
            if(DateUtils.compareDate(thisAe.getEndDate(), thatAe.getEndDate()) != 0)  continue;

            //is the term matching ?
            if(thisAe.getAdverseEventCtcTerm() != null){
                //ctc terminology
                AdverseEventCtcTerm thisCtcTerm = thisAe.getAdverseEventCtcTerm();
                AdverseEventCtcTerm thatCtcTerm = thatAe.getAdverseEventCtcTerm();
                if ( (thisCtcTerm == null && thatCtcTerm != null) || (thatCtcTerm == null && thisCtcTerm != null) ) continue;
                if( (thisCtcTerm != null && thatCtcTerm != null )&& thisCtcTerm.getTerm().getId() != thatCtcTerm.getTerm().getId()) continue;
                if(!StringUtils.equals(thisAe.getOtherSpecify(), thatAe.getOtherSpecify())) continue;

                LowLevelTerm thisLLT = thisAe.getLowLevelTerm();
                LowLevelTerm thatLLT = thatAe.getLowLevelTerm();
                if((thisLLT == null && thatLLT != null ) || (thatLLT == null && thisLLT != null)) continue;
                if((thisLLT != null && thatLLT != null ) && thisLLT.getId() != thatLLT.getId()) continue;

            } else {
                //MedDRA terminology
                AdverseEventMeddraLowLevelTerm thisMedDRATerm = thisAe.getAdverseEventMeddraLowLevelTerm();
                AdverseEventMeddraLowLevelTerm thatMedDRATerm = thatAe.getAdverseEventMeddraLowLevelTerm();
                if((thisMedDRATerm == null && thatMedDRATerm != null) && (thatMedDRATerm == null && thisMedDRATerm != null)) continue;
                if((thisMedDRATerm != null && thatMedDRATerm != null) && thisMedDRATerm.getLowLevelTerm().getId() != thatMedDRATerm.getLowLevelTerm().getId()) continue;
            }
            //found a match
            return thisAe;
        }
        return null;
    }

    
    /**
     * This method finds the adverse event, defined in this reporting period, identified by the ID.
     *
     * @param id the id
     * @return the adverse event
     */
    public AdverseEvent findAdverseEventById(Integer id){
    	for(AdverseEvent ae : getAdverseEvents()){
    		if(ae.getId().equals(id)) return ae;
    	}
    	return null;
    }
    
    /**
     * Will return the {@link ExpeditedAdverseEventReport} associated to this course, identified by id.
     *
     * @param id the id
     * @return the expedited adverse event report
     */
    public ExpeditedAdverseEventReport findExpeditedAdverseEventReport(Integer id){
    	for(ExpeditedAdverseEventReport aeReport : getAeReports()){
    		if(aeReport.getId().equals(id)) return aeReport;
    	}
    	return null;
    }
    
    /**
     * This method sets the retired indicator attribute of the reporting period. It overrides the superclass method so that we can
     * take cascading actions. Incase a reporting period (course) is retired (retired_indicator = true), then all the adverse events in the
     * course should also be retied.
     *
     * @param retiredIndicator the new retired indicator
     */
    @Override
    public void setRetiredIndicator(Boolean retiredIndicator){
    	super.setRetiredIndicator(retiredIndicator);
    	if(retiredIndicator){
    		for(AdverseEvent ae: getAdverseEvents()){
    			ae.setRetiredIndicator(retiredIndicator);
    		}
    	}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdverseEventReportingPeriod)) return false;

        AdverseEventReportingPeriod that = (AdverseEventReportingPeriod) o;

        if (getId() != null && that.getId() != null && that.getId().equals(this.getId())) return true;
        if (externalId != null && that.externalId != null && StringUtils.equals(externalId, that.externalId)) return true;

        if (startDate != null && that.startDate != null && DateUtils.compareDate(this.startDate, that.startDate) != 0) return false;
        if (endDate != null && that.endDate != null && DateUtils.compareDate(this.endDate, that.endDate) != 0) return false;
        if (getEpoch() != null && that.getEpoch() != null && !this.getEpoch().equals(that.getEpoch())) return false;
        if (getTreatmentAssignment() != null && that.getTreatmentAssignment() != null && !this.getTreatmentAssignment().getCode().equals(that.getTreatmentAssignment().getCode())) return false;
        if (getTreatmentAssignmentDescription() != null && that.getTreatmentAssignmentDescription() != null && !this.getTreatmentAssignmentDescription().equals(that.getTreatmentAssignmentDescription())) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = treatmentAssignment != null ? treatmentAssignment.hashCode() : 0;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (epoch != null ? epoch.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        return result;
    }
}
