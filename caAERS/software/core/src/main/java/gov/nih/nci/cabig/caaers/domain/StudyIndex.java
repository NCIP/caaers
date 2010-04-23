package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

@Entity
@Table(name = "study_index")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_study_index_id") })
public class StudyIndex extends AbstractMutableDomainObject{
	
	private String loginId;
	private Study study;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
    @ManyToOne
    @JoinColumn(name = "study_id")
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	
}
