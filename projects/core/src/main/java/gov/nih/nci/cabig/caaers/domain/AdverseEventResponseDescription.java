package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * This class represents the AdverseEventResponseDescription domain object associated with the
 * Adverse event report.
 * 
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "ae_report_descriptions")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_ae_report_descriptions_id") })
public class AdverseEventResponseDescription extends AbstractExpeditedReportSingleChild {
    private String eventDescription;

    private PostAdverseEventStatus presentStatus;

    private Date recoveryDate;

    private Boolean retreated;

    private Date dateRemovedFromProtocol;

    private Boolean blindBroken;

    private Boolean studyDrugInterrupted;

    private String reducedDose;

    private Date reducedDate;

    private Integer daysNotGiven;

    private Boolean eventAbate;

    private Boolean eventReappear;
    
    private Date eventApproximateTime;
    
    private String eventLocation;
    
    private Boolean autopsyPerformed;
    
    private String causeOfDeath;
    
    
	public AdverseEventResponseDescription() {
    	retreated = Boolean.FALSE;
    }

    // //// LOGIC

    @Transient
    public boolean isRemovedFromProtocol() {
        return getDateRemovedFromProtocol() != null;
    }

    // //// BEAN PROPERTIES

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Column(name = "present_status_code")
    @Type(type = "postAdverseEventStatus")
    public PostAdverseEventStatus getPresentStatus() {
        return presentStatus;
    }

    public void setPresentStatus(PostAdverseEventStatus presentStatus) {
        this.presentStatus = presentStatus;
    }

    public Date getRecoveryDate() {
        return recoveryDate;
    }

    public void setRecoveryDate(Date recoveryDate) {
        this.recoveryDate = recoveryDate;
    }

    public Boolean getRetreated() {
        return retreated;
    }

    public void setRetreated(Boolean retreated) {
        this.retreated = retreated;
    }

    public Date getDateRemovedFromProtocol() {
        return dateRemovedFromProtocol;
    }

    public void setDateRemovedFromProtocol(Date dateRemovedFromProtocol) {
        this.dateRemovedFromProtocol = dateRemovedFromProtocol;
    }

    public Boolean getBlindBroken() {
        return blindBroken;
    }

    public void setBlindBroken(Boolean blindBroken) {
        this.blindBroken = blindBroken;
    }

    public Boolean getEventAbate() {
        return eventAbate;
    }

    public void setEventAbate(Boolean eventAbate) {
        this.eventAbate = eventAbate;
    }

    public Boolean getEventReappear() {
        return eventReappear;
    }

    public void setEventReappear(Boolean eventReappear) {
        this.eventReappear = eventReappear;
    }

    public Date getReducedDate() {
        return reducedDate;
    }

    public void setReducedDate(Date reducedDate) {
        this.reducedDate = reducedDate;
    }

    public String getReducedDose() {
        return reducedDose;
    }

    public void setReducedDose(String reducedDose) {
        this.reducedDose = reducedDose;
    }

    public Boolean getStudyDrugInterrupted() {
        return studyDrugInterrupted;
    }

    public void setStudyDrugInterrupted(Boolean studyDrugInterrupted) {
        this.studyDrugInterrupted = studyDrugInterrupted;
    }

    public Integer getDaysNotGiven() {
        return daysNotGiven;
    }
    
    public void setDaysNotGiven(Integer daysNotGiven) {
        this.daysNotGiven = daysNotGiven;
    }
    
    @Temporal(value = TemporalType.TIMESTAMP)
	public Date getEventApproximateTime() {
		return eventApproximateTime;
	}
	public void setEventApproximateTime(Date eventApproximateTime) {
		this.eventApproximateTime = eventApproximateTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public Boolean getAutopsyPerformed() {
		return autopsyPerformed;
	}

	public void setAutopsyPerformed(Boolean autopsyPerformed) {
		this.autopsyPerformed = autopsyPerformed;
	}

	public String getCauseOfDeath() {
		return causeOfDeath;
	}

	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
    
    
}
