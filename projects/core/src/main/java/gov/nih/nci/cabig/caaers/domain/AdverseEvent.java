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
    - Term is free text (will be from a LUT once I can find an electronic list)
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
    // TODO: it seems like this should be a property of the study
    private Ctc ctc;

    private CtcCategory ctcCategory;
    private String term;
    private String detailsForOther;
    private Grade grade;
    private Attribution attribution;
    private Boolean hospitalization;

    private StudyParticipantAssignment assignment;
    private Timestamp detectionDate;

    ////// BOUND PROPERTIES

    @ManyToOne
    @JoinColumn(name = "ctc_version_id")
    public Ctc getCtc() {
        return ctc;
    }

    public void setCtc(Ctc ctc) {
        this.ctc = ctc;
    }

    @ManyToOne
    public CtcCategory getCtcCategory() {
        return ctcCategory;
    }

    public void setCtcCategory(CtcCategory ctcCategory) {
        this.ctcCategory = ctcCategory;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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
