package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.functors.InstantiateFactory;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.springframework.beans.BeanUtils;

 
/**
 * This class represents the TreatmentInformation domain object associated with the Adverse event
 * report.
 *
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
@Entity
@Table(name = "treatments")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_treatments_id")})
public class TreatmentInformation extends AbstractExpeditedReportSingleChild implements Serializable {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6642120445894121790L;
	
	/** The course agents internal. */
	private List<CourseAgent> courseAgentsInternal;
    
    /** The course agents. */
    private List<CourseAgent> courseAgents;
    
    /** The first course date. */
    private Date firstCourseDate;
    
    /** The adverse event course. */
    private CourseDate adverseEventCourse;
    
    /** The total courses. */
    private Integer totalCourses;
    
    /** The treatment assignment. */
    private TreatmentAssignment treatmentAssignment;
    
    /** The treatment description. */
    private String treatmentDescription;
    
    /** The investigational agent administered. */
    private Boolean investigationalAgentAdministered;

    /**
     * Instantiates a new treatment information.
     */
    public TreatmentInformation() {
        setCourseAgentsInternal(new LinkedList<CourseAgent>());
    }

    // //// LOGIC

    /**
     * Checks if is agent administered.
     *
     * @return true, if is agent administered
     */
    @Transient
    //changed method name as it is clashing with investigationalAgentAdministered , this method needs to be deleted as it is not used 
    public boolean isAgentAdministered() {
        for (CourseAgent courseAgent : getCourseAgents()) {
            Boolean indicator = courseAgent.getStudyAgent().getInvestigationalNewDrugIndicator();
            if (indicator != null && indicator) return true;
        }
        return false;
    }

    /**
     * Adds the course agent.
     *
     * @param courseAgent the course agent
     */
    public void addCourseAgent(CourseAgent courseAgent) {
        courseAgent.setTreatmentInformation(this);
        getCourseAgents().add(courseAgent);
    }

    /**
     * Gets the course agents.
     *
     * @return a wrapped list which will never throw an {@link IndexOutOfBoundsException}
     */
    @Transient
    public List<CourseAgent> getCourseAgents() {
        return courseAgents;
    }

    /**
     * Creates the lazy course agents.
     */
    @SuppressWarnings("unchecked")
    private void createLazyCourseAgents() {
        this.courseAgents = LazyList.decorate(getCourseAgentsInternal(), new InstantiateFactory(
                CourseAgent.class));
    }

    // //// BEAN PROPERTIES

    /**
     * Gets the first course date.
     *
     * @return the first course date
     */
    public Date getFirstCourseDate() {
        return firstCourseDate;
    }

    /**
     * Sets the first course date.
     *
     * @param firstCourse the new first course date
     */
    public void setFirstCourseDate(Date firstCourse) {
        this.firstCourseDate = firstCourse;
    }

    /**
     * Gets the adverse event course.
     *
     * @return the adverse event course
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "date", column = @Column(name = "adverse_event_course_date")),
            @AttributeOverride(name = "number", column = @Column(name = "adverse_event_course_number")) ,
            @AttributeOverride(name = "code", column = @Column(name = "adverse_event_course_dcode"))
    })
    public CourseDate getAdverseEventCourse() {
        if (adverseEventCourse == null) adverseEventCourse = new CourseDate();
        return adverseEventCourse;
    }

    /**
     * Sets the adverse event course.
     *
     * @param adverseEventCourse the new adverse event course
     */
    public void setAdverseEventCourse(CourseDate adverseEventCourse) {
        this.adverseEventCourse = adverseEventCourse;
    }

    // This is annotated this way so that the IndexColumn will work with
    // the bidirectional mapping. See section 2.4.6.2.3 of the hibernate annotations docs.
    /**
     * Gets the course agents internal.
     *
     * @return the course agents internal
     */
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "treatment_id", nullable = false)
    @IndexColumn(name = "list_index")
    @Cascade(value = {CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<CourseAgent> getCourseAgentsInternal() {
        return courseAgentsInternal;
    }

    /**
     * Sets the course agents internal.
     *
     * @param courseAgentsInternal the new course agents internal
     */
    public void setCourseAgentsInternal(List<CourseAgent> courseAgentsInternal) {
        this.courseAgentsInternal = courseAgentsInternal;
        createLazyCourseAgents();
    }

    /**
     * Gets the treatment assignment.
     *
     * @return the treatment assignment
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_assignment_id")
    @Cascade(value = {CascadeType.LOCK})
    public TreatmentAssignment getTreatmentAssignment() {
        return treatmentAssignment;
    }

    /**
     * Sets the treatment assignment.
     *
     * @param treatmentAssignment the new treatment assignment
     */
    public void setTreatmentAssignment(TreatmentAssignment treatmentAssignment) {
        this.treatmentAssignment = treatmentAssignment;
    }

    /**
     * Gets the treatment description.
     *
     * @return the treatment description
     */
    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    /**
     * Sets the treatment description.
     *
     * @param treatmentDescription the new treatment description
     */
    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    /**
     * Gets the total courses.
     *
     * @return the total courses
     */
    public Integer getTotalCourses() {
        return totalCourses;
    }

    /**
     * Sets the total courses.
     *
     * @param totalCourses the new total courses
     */
    public void setTotalCourses(Integer totalCourses) {
        this.totalCourses = totalCourses;
    }

    /**
     * Gets the treatment assignment description.
     *
     * @return the treatment assignment description
     */
    @Transient
    public String getTreatmentAssignmentDescription() {
        if (treatmentAssignment != null) return treatmentAssignment.getDescription();
        return null;
    }

    /**
     * Sets the treatment assignment description.
     *
     * @param desc the new treatment assignment description
     */
    @Transient
    public void setTreatmentAssignmentDescription(String desc) {
        // do nothing.
    }
   

    /**
     * Copy.
     *
     * @return the treatment information
     */
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
    
    /**
     * Gets the investigational agent administered.
     *
     * @return the investigational agent administered
     */
    @Column(name="inv_agent_adminstrd")
	public Boolean getInvestigationalAgentAdministered() {
		return investigationalAgentAdministered;
	}

	/**
	 * Sets the investigational agent administered.
	 *
	 * @param investigationalAgentAdministered the new investigational agent administered
	 */
	public void setInvestigationalAgentAdministered(
			Boolean investigationalAgentAdministered) {
		this.investigationalAgentAdministered = investigationalAgentAdministered;
	}
	
	/**
	 * Checks if is other.
	 *
	 * @return true, if is other
	 */
	@Transient
	public boolean isOther(){
		return StringUtils.isNotEmpty(getTreatmentDescription());
	}
}
