package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Rhett Sutphin
 */

@Entity
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="seq_adverse_events_id")
    }
)
public class AdverseEvent extends AbstractMutableDomainObject implements ExpeditedAdverseEventReportChild, RoutineAdverseEventReportChild {
	private AbstractAdverseEventTerm adverseEventTerm;
    private String detailsForOther;
    private Grade grade;
    private Hospitalization hospitalization;
    private Boolean expected; // false assignment removed cause that is not default for Routine AEs
    private Attribution attributionSummary;
    private String comments;
    private Date startDate;
    private Date endDate;

    private ExpeditedAdverseEventReport report;
    private RoutineAdverseEventReport routineReport;
    private List<CourseAgentAttribution> courseAgentAttributions;
    private List<ConcomitantMedicationAttribution> concomitantMedicationAttributions;
    private List<OtherCauseAttribution> otherCauseAttributions;
    private List<DiseaseAttribution> diseaseAttributions;
    private List<SurgeryAttribution> surgeryAttributions;
    private List<RadiationAttribution> radiationAttributions;

    ////// BOUND PROPERTIES

    // This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false, nullable=true)
    public ExpeditedAdverseEventReport getReport() {
        return report;
    }

    public void setReport(ExpeditedAdverseEventReport report) {
        this.report = report;
    }

     //  This is annotated this way so that the IndexColumn in the parent
    // will work with the bidirectional mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false, nullable=true)
    public RoutineAdverseEventReport getRoutineReport() {
        return routineReport;
    }

    public void setRoutineReport(RoutineAdverseEventReport routineReport) {
        this.routineReport = routineReport;
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

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'DH'") // it is pretty lame that this is necessary
    public List<DiseaseAttribution> getDiseaseAttributions() {
        if (diseaseAttributions == null) {
            diseaseAttributions = new ArrayList<DiseaseAttribution>();
        }
        return diseaseAttributions;
    }

    public void setDiseaseAttributions(List<DiseaseAttribution> diseaseAttributions) {
        this.diseaseAttributions = diseaseAttributions;
    }

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'SI'") // it is pretty lame that this is necessary
    public List<SurgeryAttribution> getSurgeryAttributions() {
        if (surgeryAttributions == null) {
            surgeryAttributions = new ArrayList<SurgeryAttribution>();
        }
        return surgeryAttributions;
    }

    public void setSurgeryAttributions(List<SurgeryAttribution> surgeryAttributions) {
        this.surgeryAttributions = surgeryAttributions;
    }

    @OneToMany
    @JoinColumn(name="adverse_event_id", nullable=false)
    @IndexColumn(name="list_index")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Where(clause = "cause_type = 'RI'") // it is pretty lame that this is necessary
    public List<RadiationAttribution> getRadiationAttributions() {
        if (radiationAttributions == null) {
            radiationAttributions = new ArrayList<RadiationAttribution>();
        }
        return radiationAttributions;
    }

    public void setRadiationAttributions(List<RadiationAttribution> radiationAttributions) {
        this.radiationAttributions = radiationAttributions;
    }


    @Deprecated
    @Transient
    public CtcTerm getCtcTerm() {
        if (this.adverseEventTerm == null) {
            return null;
        } else if (this.adverseEventTerm instanceof AdverseEventCtcTerm ){
            return getAdverseEventCtcTerm().getCtcTerm();
        } else {
        	 throw new CaaersSystemException(
             "Cannot Return a Ctc Term you are probably using a Terminology different than Ctc");
        }
    }

    @Deprecated
    @Transient
    public void setCtcTerm(CtcTerm ctcTerm) {
        getAdverseEventCtcTerm().setCtcTerm(ctcTerm);
    }

    @Transient
    public AdverseEventCtcTerm getAdverseEventCtcTerm() {
    	if ( adverseEventTerm == null ) {
    		this.adverseEventTerm = new AdverseEventCtcTerm();
    		adverseEventTerm.setAdverseEvent(this);
    	} else if (!adverseEventTerm.getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm")) {
    		return new AdverseEventCtcTerm();
    	}

		return (AdverseEventCtcTerm)adverseEventTerm;
	}

    @Transient
	public void setAdverseEventCtcTerm(AdverseEventCtcTerm adverseEventCtcTerm) {
		//this.adverseEventCtcTerm = adverseEventCtcTerm;
    	setAdverseEventTerm(adverseEventCtcTerm);
	}

    @Transient
    public AdverseEventMeddraLowLevelTerm getAdverseEventMeddraLowLevelTerm() {
    	if ( adverseEventTerm == null ) {
    		this.adverseEventTerm = new AdverseEventMeddraLowLevelTerm();
    		adverseEventTerm.setAdverseEvent(this);
    	} else if (!adverseEventTerm.getClass().getName().equals("gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm")) {
    		return new AdverseEventMeddraLowLevelTerm();
    	}
		return (AdverseEventMeddraLowLevelTerm)adverseEventTerm;
	}

    @Transient
	public void setAdverseEventMeddraLowLevelTerm(AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm) {
		//this.adverseEventCtcTerm = adverseEventCtcTerm;
    	setAdverseEventTerm(adverseEventMeddraLowLevelTerm);
	}



    @OneToOne(fetch = FetchType.LAZY, mappedBy = "adverseEvent")
    @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    public AbstractAdverseEventTerm getAdverseEventTerm() {
        return adverseEventTerm;
    }

    public void setAdverseEventTerm(AbstractAdverseEventTerm adverseEventTerm) {
        this.adverseEventTerm = adverseEventTerm;
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

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

    @Type(type = "attribution")
    @Column(name = "attribution_summary_code")
    public Attribution getAttributionSummary() {
        return attributionSummary;
    }

    public void setAttributionSummary(Attribution attributionSummary) {
        this.attributionSummary = attributionSummary;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
