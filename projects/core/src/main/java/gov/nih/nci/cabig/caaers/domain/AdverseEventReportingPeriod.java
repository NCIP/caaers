package gov.nih.nci.cabig.caaers.domain;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.collections.LazyListHelper;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

/**
 * This class represents the Reporting Period associated to StudyParticipant Associations
 *
 * @author Sameer Sawant
 */

@Entity
@Table(name = "ae_reporting_periods")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_") })
public class AdverseEventReportingPeriod extends AbstractMutableDomainObject{
	private String description;
	
	private Integer cycleNumber;
	
	//private ExpeditedAdverseEventReport expeditedAdverseEventReport;
	
	private TreatmentAssignment treatmentAssignment;
	
	private Epoch epoch;
	
	private Date startDate;
	
	private Date endDate;
	
	private StudyParticipantAssignment assignment;
	
	private List<AdverseEvent> adverseEvents;
	
	public AdverseEventReportingPeriod() {
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
   
    
    // @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
    @Transient
    @OneToMany(mappedBy = "reportingPeriod")
    public List<AdverseEvent> getAdverseEvents() {
    	if (adverseEvents == null) adverseEvents = new ArrayList<AdverseEvent>();
        return adverseEvents;
    }

    @Transient
    public void setAdverseEvents(final List<AdverseEvent> adverseEvents) {
        this.adverseEvents = adverseEvents;
    }
    
    // BEAN PROPERTIES
    
    public void addAdverseEvent(AdverseEvent adverseEvent){
    	getAdverseEvents().add(adverseEvent);
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    public Epoch getEpoch(){
    	return epoch;
    }
    
    public void setEpoch(Epoch epoch){
    	this.epoch = epoch;
    }
    
    public String fetchName(){
    	return startDate + "-" + endDate;
    }
}