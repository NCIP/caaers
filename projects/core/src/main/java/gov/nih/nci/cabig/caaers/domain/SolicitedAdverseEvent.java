package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

@Entity
@Table(name = "solicited_events")//solicited_events
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_solicited_events_id") })
public class SolicitedAdverseEvent  extends AbstractMutableDomainObject {
	private CtcTerm ctcterm;
	private LowLevelTerm medraterm;
    /*private Arm arm;
    
	public Arm getArm() {
		return arm;
	}
	public void setArm(Arm arm) {
		this.arm = arm;
	}
	*/
	@OneToOne
	@JoinColumn(name="ctc_term_id")
	public CtcTerm getCtcterm() {
		return ctcterm;
	}
	public void setCtcterm(CtcTerm ctcterm) {
		this.ctcterm = ctcterm;
	}
	@OneToOne
	@JoinColumn(name="lowlevel_term_id")
	public LowLevelTerm getLowLevelTerm() {
		return medraterm;
	}
	public void setLowLevelTerm(LowLevelTerm medraterm) {
		this.medraterm = medraterm;
	}
	
}
