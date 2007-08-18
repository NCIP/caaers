package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

@Entity
@Table(name = "study_amendments")
@GenericGenerator(name = "id-generator",
		strategy = "native",
		parameters = { @Parameter(name = "sequence", value = "seq_study_amendments_id") }
)

public class StudyAmendment extends AbstractMutableDomainObject{

	private int amendmentVersion;
	private Date amendmentDate;
	private String comments;
	private Date irbApprovalDate;


	/// Mutators
	public int getAmendmentVersion() {
		return amendmentVersion;
	}
	public void setAmendmentVersion(int amendmentVersion) {
		this.amendmentVersion = amendmentVersion;
	}
	public Date getAmendmentDate() {
		return amendmentDate;
	}
	public void setAmendmentDate(Date amendmentDate) {
		this.amendmentDate = amendmentDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getIrbApprovalDate() {
		return irbApprovalDate;
	}
	public void setIrbApprovalDate(Date irbApprovalDate) {
		this.irbApprovalDate = irbApprovalDate;
	}

}
