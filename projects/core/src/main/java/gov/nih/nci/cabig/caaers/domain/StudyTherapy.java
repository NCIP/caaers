package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * Domain object representing Study Therapy
 * 
 * @author Saurabh Agrawal
 */
@Entity
@Table(name = "study_therapy")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_therapy_id") })
public class StudyTherapy extends AbstractMutableDomainObject implements StudyChild {

	private Study study;

	private StudyTherapyType studyTherapyType;

	@ManyToOne
	@JoinColumn(name = "study_id", nullable = false)
	public Study getStudy() {
		return study;
	}

	public void setStudy(final Study study) {
		this.study = study;
	}

	@Column(name = "STUDY_THERAPY_TYPE")
	@Type(type = "studyTherapyType")
	public StudyTherapyType getStudyTherapyType() {
		return studyTherapyType;
	}

	public void setStudyTherapyType(final StudyTherapyType studyTherapyType) {
		this.studyTherapyType = studyTherapyType;
	}

}
