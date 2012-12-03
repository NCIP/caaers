package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
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
public class CourseAgent extends AbstractMutableDomainObject implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 383521569507666095L;
	
	/** The treatment information. */
	private TreatmentInformation treatmentInformation;
    
    /** The study agent. */
    private StudyAgent studyAgent;
    
    /** The dose. */
    private Dose dose;
    
    /** The duration and schedule. */
    private String durationAndSchedule;
    
    /** The administration delay amount. */
    private BigDecimal administrationDelayAmount;
    
    /** The administration delay units. */
    private DelayUnits administrationDelayUnits;
    
    /** The agent adjustment. */
    private AgentAdjustment agentAdjustment;
    
    /** The modified dose. */
    private Dose modifiedDose;
    
    /** The last administered date. */
    private Date lastAdministeredDate;

    /** The first administered date. */
    private Date firstAdministeredDate;

    /** The total dose administered this course. */
    private BigDecimal totalDoseAdministeredThisCourse;
    
    /** The comments. */
    private String comments;
    
    /** The lot number. */
    private String lotNumber;
    
    /** The formulation. */
    private String formulation;


    // //// LOGIC

    /**
     * Administration delay is stored in minutes.
     *
     * @return the administration delay
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

    /**
     * Sets the administration delay.
     *
     * @param administrationDelay the new administration delay
     */
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

    /**
     * Gets the display name.
     *
     * @return the display name
     */
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
    
    /**
     * Checks if is dose modified.
     *
     * @return true, if is dose modified
     */
    @Deprecated
    @Transient
    // nee to delete this method . 
    public boolean isDoseModified() {
    	return getModifiedDose().getAmount() != null && !getDose().equals(getModifiedDose());
    }
	
    // //// BEAN PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping

    /**
     * Gets the treatment information.
     *
     * @return the treatment information
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id", insertable = false, updatable = false, nullable = false)
    public TreatmentInformation getTreatmentInformation() {
        return treatmentInformation;
    }

    /**
     * Sets the treatment information.
     *
     * @param treatmentInformation the new treatment information
     */
    public void setTreatmentInformation(TreatmentInformation treatmentInformation) {
        this.treatmentInformation = treatmentInformation;
    }

    /**
     * Gets the study agent.
     *
     * @return the study agent
     */
    @ManyToOne
    public StudyAgent getStudyAgent() {
        return studyAgent;
    }

    /**
     * Sets the study agent.
     *
     * @param studyAgent the new study agent
     */
    public void setStudyAgent(StudyAgent studyAgent) {
        this.studyAgent = studyAgent;
    }

    /**
     * Gets the dose.
     *
     * @return the dose
     */
    @Embedded
    public Dose getDose() {
        if (dose == null) dose = new Dose();
        return dose;
    }

    /**
     * Sets the dose.
     *
     * @param dose the new dose
     */
    public void setDose(Dose dose) {
        this.dose = dose;
    }
    
    /**
     * Gets the modified dose.
     *
     * @return the modified dose
     */
    @Deprecated
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "modified_dose_amount")),
            @AttributeOverride(name = "code", column = @Column(name = "modified_dose_code")),
            @AttributeOverride(name = "units", column = @Column(name = "modified_dose_units")),
            @AttributeOverride(name = "route", column = @Column(name = "modified_dose_route"))})
    public Dose getModifiedDose() {
        if (modifiedDose == null) modifiedDose = new Dose();
        return modifiedDose;
    }
    
    /**
     * Sets the modified dose.
     *
     * @param modifiedDose the new modified dose
     */
    @Deprecated
    public void setModifiedDose(Dose modifiedDose) {
        this.modifiedDose = modifiedDose;
    }
	
    /**
     * Gets the duration and schedule.
     *
     * @return the duration and schedule
     */
    public String getDurationAndSchedule() {
        return durationAndSchedule;
    }

    /**
     * Sets the duration and schedule.
     *
     * @param durationAndSchedule the new duration and schedule
     */
    public void setDurationAndSchedule(String durationAndSchedule) {
        this.durationAndSchedule = durationAndSchedule;
    }

    /**
     * Gets the total dose administered this course.
     *
     * @return the total dose administered this course
     */
    @Column(name = "total_dose_this_course")
    public BigDecimal getTotalDoseAdministeredThisCourse() {
        return totalDoseAdministeredThisCourse;
    }

    /**
     * Sets the total dose administered this course.
     *
     * @param totalDoseAdministeredThisCourse the new total dose administered this course
     */
    public void setTotalDoseAdministeredThisCourse(BigDecimal totalDoseAdministeredThisCourse) {
        this.totalDoseAdministeredThisCourse = totalDoseAdministeredThisCourse;
    }

    /**
     * Gets the last administered date.
     *
     * @return the last administered date
     */
    public Date getLastAdministeredDate() {
        return lastAdministeredDate;
    }

    /**
     * Sets the last administered date.
     *
     * @param lastAdministeredDate the new last administered date
     */
    public void setLastAdministeredDate(Date lastAdministeredDate) {
        this.lastAdministeredDate = lastAdministeredDate;
    }

    public Date getFirstAdministeredDate() {
        return firstAdministeredDate;
    }

    public void setFirstAdministeredDate(Date firstAdministeredDate) {
        this.firstAdministeredDate = firstAdministeredDate;
    }

    /**
     * Gets the administration delay amount.
     *
     * @return the administration delay amount
     */
    @Transient
    public BigDecimal getAdministrationDelayAmount() {
        return administrationDelayAmount;
    }

    /**
     * Sets the administration delay amount.
     *
     * @param administrationDelayAmount the new administration delay amount
     */
    public void setAdministrationDelayAmount(BigDecimal administrationDelayAmount) {
        this.administrationDelayAmount = administrationDelayAmount;
    }

    /**
     * Gets the administration delay units.
     *
     * @return the administration delay units
     */
    @Transient
    public DelayUnits getAdministrationDelayUnits() {
        return administrationDelayUnits;
    }

    /**
     * Sets the administration delay units.
     *
     * @param administrationDelayUnits the new administration delay units
     */
    public void setAdministrationDelayUnits(DelayUnits administrationDelayUnits) {
        this.administrationDelayUnits = administrationDelayUnits;
    }

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the lot number.
     *
     * @return the lot number
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Sets the lot number.
     *
     * @param lotNumber the new lot number
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Gets the formulation.
     *
     * @return the formulation
     */
    public String getFormulation() {
        return formulation;
    }

    /**
     * Sets the formulation.
     *
     * @param formulation the new formulation
     */
    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }
    
    /**
     * Gets the agent adjustment.
     *
     * @return the agent adjustment
     */
    @Column(name = "agent_adjustment_code")
	public AgentAdjustment getAgentAdjustment() {
		return agentAdjustment;
	}

	/**
	 * Sets the agent adjustment.
	 *
	 * @param agentAdjustment the new agent adjustment
	 */
	public void setAgentAdjustment(AgentAdjustment agentAdjustment) {
		this.agentAdjustment = agentAdjustment;
	}

    /**
     * Copy.
     *
     * @return the course agent
     */
    public CourseAgent copy() {
        CourseAgent courseAgent = new CourseAgent();
        BeanUtils.copyProperties(this, courseAgent,
                new String[]{"id", "gridId", "version",
                        "primaryTreatmentApproximateTime", "adverseEventCourse", "courseAgentsInternal", "treatmentInformation","dose"});

        courseAgent.setDose(getDose().copy());
        return courseAgent;

    }


}
