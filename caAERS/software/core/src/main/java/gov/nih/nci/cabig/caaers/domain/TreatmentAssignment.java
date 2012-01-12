package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;


/**
 * This class represents the TreatmentAssignment domain object associated with the Adverse event
 * report. Domain object representing Study Therapy
 *
 * @author Saurabh Agrawal
 */
@Entity
@Table(name = "treatment_assignment")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_treatment_assignment_id") })
public class TreatmentAssignment extends AbstractMutableRetireableDomainObject implements StudyChild, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3837235599935227241L;

	/** The study. */
	private Study study;

    /** The code. */
    private String code;

    /** The dose level order. */
    private Integer doseLevelOrder;

    /** The description. */
    private String description;

    /** The comments. */
    private String comments;

    protected List<TreatmentAssignmentStudyIntervention> treatmentAssignmentStudyInterventions = new ArrayList<TreatmentAssignmentStudyIntervention>();

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#getStudy()
     */
    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.StudyChild#setStudy(gov.nih.nci.cabig.caaers.domain.Study)
     */
    public void setStudy(final Study study) {
        this.study = study;
    }

    /**
     * Instantiates a new treatment assignment.
     */
    public TreatmentAssignment() {
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the dose level order.
     *
     * @return the dose level order
     */
    public Integer getDoseLevelOrder() {
        return doseLevelOrder;
    }

    /**
     * Sets the dose level order.
     *
     * @param doseLevelOrder the new dose level order
     */
    public void setDoseLevelOrder(Integer doseLevelOrder) {
        this.doseLevelOrder = doseLevelOrder;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the escaped description.
     *
     * @return the escaped description
     */
    @Transient
    /**
     * The below function is only used for UI purpose
     */
    public String getEscapedDescription() {
        return StringEscapeUtils.escapeJavaScript(description);
    }

    /**
     * Gets the html escaped description.
     *
     * @return the html escaped description
     */
    @Transient
    public String getHtmlEscapedDescription() {
        String descriptionHtml = StringUtils.replace(description, "\r\n", "<br>" );
        return StringEscapeUtils.escapeJavaScript(descriptionHtml);
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

    @OneToMany(mappedBy = "treatmentAssignment", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<TreatmentAssignmentStudyIntervention> getTreatmentAssignmentStudyInterventions() {
        return treatmentAssignmentStudyInterventions;
    }

    public void setTreatmentAssignmentStudyInterventions(List<TreatmentAssignmentStudyIntervention> treatmentAssignmentStudyInterventions) {
        this.treatmentAssignmentStudyInterventions = treatmentAssignmentStudyInterventions;
    }

    public void addInterventionToTreatmentAssignment(StudyIntervention ti) {
        TreatmentAssignmentStudyIntervention tasi = null;
        switch (ti.getStudyTherapyType()) {
            case DRUG_ADMINISTRATION:{
                TreatmentAssignmentAgent taa = new TreatmentAssignmentAgent();
                taa.setStudyAgent((StudyAgent)ti);
                tasi = taa;
            }; break;
            case DEVICE:{
                TreatmentAssignmentDevice tad = new TreatmentAssignmentDevice();
                tad.setStudyDevice((StudyDevice)ti);
                tasi = tad;
            }; break;
            case OTHER:{
                TreatmentAssignmentOtherIntervention tao = new TreatmentAssignmentOtherIntervention();
                tao.setOtherIntervention((OtherIntervention)ti);
                tasi = tao;
            }; break;
        }
        if (tasi == null) return;
        tasi.setTreatmentAssignment(this);
        getTreatmentAssignmentStudyInterventions().add(tasi);
    }

    public void addTreatmentAssignmentStudyIntervention(TreatmentAssignmentStudyIntervention tasi) {
        tasi.setTreatmentAssignment(this);
        getTreatmentAssignmentStudyInterventions().add(tasi);
    }

   /**
     * This method returns true if this TreatmentAssignment object is associated with the StudyIntervention through
    * a TreatmentAssignmentStudyIntervention object.
     * @param i - StudyIntervention
     * @return boolean
     */
    @Transient
    public boolean hasIntervention(StudyIntervention i) {
        List<TreatmentAssignmentStudyIntervention> tasis = getTreatmentAssignmentStudyInterventions();
        for (TreatmentAssignmentStudyIntervention tasi : tasis) {
            if (tasi.getStudyIntervention().equals(i)) return true;
        }
        return false;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (code == null ? 0 : code.hashCode());
        result = prime * result + (comments == null ? 0 : comments.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (study.getId() == null ? 0 : study.getId().hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreatmentAssignment other = (TreatmentAssignment) obj;
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }

        if (study.getId() == null) {
            if (other.study.getId() != null) {
                return false;
            }
        } else if (!study.getId().equals(other.study.getId())) {
            return false;
        }
        return true;
    }

}
