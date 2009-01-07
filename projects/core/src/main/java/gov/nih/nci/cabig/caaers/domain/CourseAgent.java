package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;

/**
 * This class represents the CourseAgent domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "course_agents")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_course_agents_id")})
public class CourseAgent extends AbstractMutableDomainObject {
    private TreatmentInformation treatmentInformation;

    private StudyAgent studyAgent;

    private Dose dose;

    private String durationAndSchedule;

    private BigDecimal administrationDelayAmount;

    private DelayUnits administrationDelayUnits;

    private AgentAdjustment agentAdjustment;
    
    //private Dose modifiedDose; 

    private Date lastAdministeredDate;

    private BigDecimal totalDoseAdministeredThisCourse;

    private String comments;

    private String lotNumber;
    private String formulation;


    // //// LOGIC

    /**
     * Administration delay is stored in minutes.
     */
    @Column(name = "administration_delay_minutes")
    public BigDecimal getAdministrationDelay() {
        DelayUnits units = getAdministrationDelayUnits();
        BigDecimal amount = getAdministrationDelayAmount();
        if (units == null || amount == null) {
            return null;
        } else {
            return units.toMinutes(amount);
        }
    }

    public void setAdministrationDelay(BigDecimal administrationDelay) {
        DelayUnits units = null;
        if (administrationDelay == null) {
            units = null;
        } else {
            for (int i = DelayUnits.values().length - 1; i >= 0; i--) {
                DelayUnits candidate = DelayUnits.values()[i];
                if (candidate.isExact(administrationDelay)) {
                    units = candidate;
                    break;
                }
            }
            if (units == null) {
                units = DelayUnits.MINUTES;
            }
        }
        setAdministrationDelayAmount(units == null ? null : units.fromMinutes(administrationDelay));
        setAdministrationDelayUnits(units);
    }

    @Transient
    public String getDisplayName() {
        StringBuilder sb = new StringBuilder();
        if (getStudyAgent() == null) {
            sb.append("[no agent]");
        } else {
            sb.append(getStudyAgent().getAgentName());
        }
        if (getDose() != null && getDose().getAmount() != null) {
            sb.append(" (").append(getDose().getDisplayName()).append(')');
        }
        return sb.toString();
    }
    
    @Deprecated
    @Transient
    // nee to delete this method . 
    public boolean isDoseModified() {
        return false;
    	//return getModifiedDose().getAmount() != null && !getDose().equals(getModifiedDose());
    }
	
    // //// BEAN PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id", insertable = false, updatable = false, nullable = false)
    public TreatmentInformation getTreatmentInformation() {
        return treatmentInformation;
    }

    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
    }

    @ManyToOne
    public StudyAgent getStudyAgent() {
        return studyAgent;
    }

    public void setStudyAgent(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }

    @Embedded
    public Dose getDose() {
        if (dose == null) dose = new Dose();
        return dose;
    }

    public void setDose(Dose dose) {
        this.dose = dose;
    }
    /*
    @Deprecated
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "modified_dose_amount")),
            @AttributeOverride(name = "units", column = @Column(name = "modified_dose_units")),
            @AttributeOverride(name = "route", column = @Column(name = "modified_dose_route"))})
    public Dose getModifiedDose() {
        if (modifiedDose == null) modifiedDose = new Dose();
        return modifiedDose;
    }
    @Deprecated
    public void setModifiedDose(Dose modifiedDose) {
        this.modifiedDose = modifiedDose;
    }
	*/
    public String getDurationAndSchedule() {
        return durationAndSchedule;
    }

    public void setDurationAndSchedule(String durationAndSchedule) {
        this.durationAndSchedule = durationAndSchedule;
    }

    @Column(name = "total_dose_this_course")
    public BigDecimal getTotalDoseAdministeredThisCourse() {
        return totalDoseAdministeredThisCourse;
    }

    public void setTotalDoseAdministeredThisCourse(BigDecimal totalDoseAdministeredThisCourse) {
        this.totalDoseAdministeredThisCourse = totalDoseAdministeredThisCourse;
    }

    public Date getLastAdministeredDate() {
        return lastAdministeredDate;
    }

    public void setLastAdministeredDate(Date lastAdministeredDate) {
        this.lastAdministeredDate = lastAdministeredDate;
    }

    @Transient
    public BigDecimal getAdministrationDelayAmount() {
        return administrationDelayAmount;
    }

    public void setAdministrationDelayAmount(BigDecimal administrationDelayAmount) {
        this.administrationDelayAmount = administrationDelayAmount;
    }

    @Transient
    public DelayUnits getAdministrationDelayUnits() {
        return administrationDelayUnits;
    }

    public void setAdministrationDelayUnits(DelayUnits administrationDelayUnits) {
        this.administrationDelayUnits = administrationDelayUnits;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getFormulation() {
        return formulation;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }
    
    @Column(name = "agent_adjustment_code")
	public AgentAdjustment getAgentAdjustment() {
		return agentAdjustment;
	}

	public void setAgentAdjustment(AgentAdjustment agentAdjustment) {
		this.agentAdjustment = agentAdjustment;
	}

    public CourseAgent copy() {
        CourseAgent courseAgent = new CourseAgent();
        BeanUtils.copyProperties(this, courseAgent,
                new String[]{"id", "gridId", "version",
                        "primaryTreatmentApproximateTime", "adverseEventCourse", "courseAgentsInternal", "treatmentInformation","dose"});

        courseAgent.setDose(getDose().copy());
        return courseAgent;

    }


}
