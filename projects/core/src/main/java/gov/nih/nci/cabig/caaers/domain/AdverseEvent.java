package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * @author Rhett Sutphin
 */
/* NOTES:
    - Expectedness mentioned in use case not yet implemented (need more info)
    - MedDRA code mentioned in use case not yet implemented
    */
@Entity
@Table
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_adverse_events_id")
    }
)
public class AdverseEvent extends AbstractDomainObject {
    private CtcTerm ctcTerm;
    private String detailsForOther;
    private Grade grade;
    private Attribution attribution;
    private Boolean hospitalization;

    private StudyParticipantAssignment assignment;
    private Timestamp detectionDate;

    ////// BOUND PROPERTIES

    @ManyToOne
    public CtcTerm getCtcTerm() {
        return ctcTerm;
    }

    public void setCtcTerm(CtcTerm ctcTerm) {
        this.ctcTerm = ctcTerm;
    }

    public String getDetailsForOther() {
        return detailsForOther;
    }

    public void setDetailsForOther(String detailsForOther) {
        this.detailsForOther = detailsForOther;
    }

    @Type(type = "grade")
    @Column(name = "grade_code")
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Type(type = "attribution")
    @Column(name = "attribution_code")
    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    @ManyToOne
    public StudyParticipantAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(StudyParticipantAssignment assignment) {
        this.assignment = assignment;
    }

    public Timestamp getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Timestamp detectionDate) {
        this.detectionDate = detectionDate;
    }

    public Boolean getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(Boolean hospitalization) {
        this.hospitalization = hospitalization;
    }
}
