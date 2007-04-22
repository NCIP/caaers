package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "course_agents")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_course_agents_id")
    }
)
public class CourseAgent extends AbstractDomainObject {
    private TreatmentInformation treatmentInformation;

    private StudyAgent studyAgent;
    private Dose dose;
    private String durationAndSchedule;

    private BigDecimal administrationDelayAmount;
    private DelayUnits administrationDelayUnits;
    private BigDecimal totalDoseAdministeredThisCourse;

    ////// LOGIC

    /**
     * Administration delay is stored in minutes.
     * @return
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

    ////// BEAN PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="treatment_id", insertable=false, updatable=false, nullable=false)
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
}
