package gov.nih.nci.cabig.caaers.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
/* NOTES:
    - MedDRA code mentioned in use case not yet implemented
    */
@Entity
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
    private Hospitalization hospitalization;
    private Boolean expected;

    private AdverseEventReport report;
    private Date detectionDate;

    ////// BOUND PROPERTIES

    @ManyToOne
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }

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

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    @Type(type = "hospitalization")
    @Column(name = "hospitalization_code")
    public Hospitalization getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;
    }

    public Boolean getExpected() {
        return expected;
    }

    public void setExpected(Boolean expected) {
        this.expected = expected;
    }
}
