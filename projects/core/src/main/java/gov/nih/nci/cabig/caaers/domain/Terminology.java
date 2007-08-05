package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * @author Krikor Krumlian
 */
@Entity
@Table(name = "terminologies")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_terminologies_id")
    }
)
public class Terminology extends AbstractMutableDomainObject {
    private Term term;
    private Ctc ctcVersion;
    private Study study;

    ////// BEAN PROPERTIES
    
    @Column(name = "term_code")
	@Type(type = "term")
	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}
	
	@OneToOne
	@JoinColumn(name = "ctc_id")
	public Ctc getCtcVersion() {
		return ctcVersion;
	}

	public void setCtcVersion(Ctc ctcVersion) {
		this.ctcVersion = ctcVersion;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
	
	
}
