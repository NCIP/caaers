package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * This class encapsulates all types of organizations associated with a Study
 * 
 * @author Ram Chilukuri
 * 
 */
@Entity
@Table(name = "study_organizations")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_study_organizations_id") })
public abstract class StudyOrganization extends AbstractMutableDomainObject implements StudyChild {

	private Study study;

	private Organization organization;

	@ManyToOne
	@JoinColumn(name = "site_id", nullable = false)
	@Cascade(value = { CascadeType.LOCK })
	public Organization getOrganization() {
		return organization;
	}

	@ManyToOne
	@JoinColumn(name = "study_id", nullable = false)
	@Cascade(value = { CascadeType.LOCK })
	public Study getStudy() {
		return study;
	}

	public void setOrganization(final Organization organization) {
		this.organization = organization;
	}

	public void setStudy(final Study study) {
		this.study = study;
	}

}
