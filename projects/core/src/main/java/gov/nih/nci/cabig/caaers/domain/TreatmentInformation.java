package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.collections.functors.InstantiateFactory;
import org.apache.commons.collections.list.LazyList;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the TreatmentInformation domain object associated with the Adverse event
 * report.
 *
 * @author Rhett Sutphin
 */
@Entity
@Table(name = "treatments")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_treatments_id")})
public class TreatmentInformation extends AbstractExpeditedReportSingleChild {
    private List<CourseAgent> courseAgentsInternal;

    private List<CourseAgent> courseAgents;

    private Date firstCourseDate;

    private CourseDate adverseEventCourse;

    private Integer totalCourses;

    private TreatmentAssignment treatmentAssignment;

    private String treatmentDescription;



    public TreatmentInformation() {
        setCourseAgentsInternal(new LinkedList<CourseAgent>());
    }

    // //// LOGIC

    @Transient
    public boolean isInvestigationalAgentAdministered() {
        for (CourseAgent courseAgent : getCourseAgents()) {
            Boolean indicator = courseAgent.getStudyAgent().getInvestigationalNewDrugIndicator();
            if (indicator != null && indicator) return true;
        }
        return false;
    }

    public void addCourseAgent(CourseAgent courseAgent) {
        courseAgent.setTreatmentInformation(this);
        getCourseAgents().add(courseAgent);
    }

    /**
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<CourseAgent> getCourseAgents() {
        return courseAgents;
    }

    @SuppressWarnings("unchecked")
    private void createLazyCourseAgents() {
        this.courseAgents = LazyList.decorate(getCourseAgentsInternal(), new InstantiateFactory(
                CourseAgent.class));
    }

    // //// BEAN PROPERTIES

    public Date getFirstCourseDate() {
        return firstCourseDate;
    }

    public void setFirstCourseDate(Date firstCourse) {
        this.firstCourseDate = firstCourse;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "adverse_event_course_date")),
            @AttributeOverride(name = "number", column = @Column(name = "adverse_event_course_number"))})
    public CourseDate getAdverseEventCourse() {
        if (adverseEventCourse == null) adverseEventCourse = new CourseDate();
        return adverseEventCourse;
    }

    public void setAdverseEventCourse(CourseDate adverseEventCourse) {
        this.adverseEventCourse = adverseEventCourse;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    @OneToMany
    @JoinColumn(name = "treatment_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    public List<CourseAgent> getCourseAgentsInternal() {
        return courseAgentsInternal;
    }

    public void setCourseAgentsInternal(List<CourseAgent> courseAgentsInternal) {
        this.courseAgentsInternal = courseAgentsInternal;
        createLazyCourseAgents();
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_assignment_id")
    @Cascade(value = {CascadeType.LOCK})
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    public Integer getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    @Transient
    public String getTreatmentAssignmentDescription() {
        if (treatmentAssignment != null) return treatmentAssignment.getDescription();
        return null;
    }

    @Transient
    public void setTreatmentAssignmentDescription(String desc) {
        // do nothing.
    }


    public TreatmentInformation copy() {
        TreatmentInformation treatmentInformation = new TreatmentInformation();
        BeanUtils.copyProperties(this, treatmentInformation,
                new String[]{"id", "gridId", "version"
                        , "courseAgentsInternal", "report", "courseAgents"});


        for (CourseAgent courseAgent : getCourseAgentsInternal()) {
            treatmentInformation.addCourseAgent(courseAgent.copy());
        }

        return treatmentInformation;
    }
}
