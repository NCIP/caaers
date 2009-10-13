package gov.nih.nci.cabig.caaers.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.Length;

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

	private static final long serialVersionUID = -3837235599935227241L;

	private Study study;

    private String code;

    private Integer doseLevelOrder;

    private String description;

    private String comments;

    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    @Cascade(value = {CascadeType.EVICT})
    public Study getStudy() {
        return study;
    }

    public void setStudy(final Study study) {
        this.study = study;
    }

    public TreatmentAssignment() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Length(max = 99)
    public Integer getDoseLevelOrder() {
        return doseLevelOrder;
    }

    public void setDoseLevelOrder(Integer doseLevelOrder) {
        this.doseLevelOrder = doseLevelOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    /**
     * The below function is only used for UI purpose
     */
    public String getEscapedDescription() {
        return StringEscapeUtils.escapeJavaScript(description);
    }

    @Transient
    public String getHtmlEscapedDescription() {
        String descriptionHtml = StringUtils.replace(description, "\r\n", "<br>" );
        return StringEscapeUtils.escapeJavaScript(descriptionHtml);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (code == null ? 0 : code.hashCode());
        result = prime * result + (comments == null ? 0 : comments.hashCode());
        result = prime * result + (description == null ? 0 : description.hashCode());
        result = prime * result + (doseLevelOrder == null ? 0 : doseLevelOrder);
        result = prime * result + (study.getId() == null ? 0 : study.getId().hashCode());
        return result;
    }

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
