package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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
public class AdverseEvent extends AbstractMutableDomainObject implements AdverseEventReportChild {
    private CtcTerm ctcTerm;
    private String detailsForOther;
    private Grade grade;
    private Hospitalization hospitalization;
    private Boolean expected = false;
    private String comments;

    private AdverseEventReport report;
    private List<CourseAgentAttribution> courseAgentAttributions;
    private List<ConcomitantMedicationAttribution> concomitantMedicationAttributions;
    private List<OtherCauseAttribution> otherCauseAttributions;

    ////// BOUND PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false, nullable=false)
    public AdverseEventReport getReport() {
        return report;
    }

    public void setReport(AdverseEventReport report) {
        this.report = report;
    }

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'CA'") // it is pretty lame that this is necessary
    public List<CourseAgentAttribution> getCourseAgentAttributions() {
        if (courseAgentAttributions == null) courseAgentAttributions = new ArrayList<CourseAgentAttribution>();
        return courseAgentAttributions;
    }

    public void setCourseAgentAttributions(List<CourseAgentAttribution> courseAgentAttributions) {
        this.courseAgentAttributions = courseAgentAttributions;
    }

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'CM'") // it is pretty lame that this is necessary
    public List<ConcomitantMedicationAttribution> getConcomitantMedicationAttributions() {
        if (concomitantMedicationAttributions == null) {
            concomitantMedicationAttributions = new ArrayList<ConcomitantMedicationAttribution>();
        }
        return concomitantMedicationAttributions;
    }

    public void setConcomitantMedicationAttributions(List<ConcomitantMedicationAttribution> concomitantMedicationAttributions) {
        this.concomitantMedicationAttributions = concomitantMedicationAttributions;
    }

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'OC'") // it is pretty lame that this is necessary
    public List<OtherCauseAttribution> getOtherCauseAttributions() {
        if (otherCauseAttributions == null) {
            otherCauseAttributions = new ArrayList<OtherCauseAttribution>();
        }
        return otherCauseAttributions;
    }

    public void setOtherCauseAttributions(List<OtherCauseAttribution> otherCauseAttributions) {
        this.otherCauseAttributions = otherCauseAttributions;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
