package gov.nih.nci.cabig.caaers.domain;

import java.util.Set;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

public class SolicitedAdverseEvent<T extends DomainObject>  extends AbstractMutableDomainObject {
	private String instruction;
	private Arm arm;
	private AbstractAdverseEventTerm<T> term;
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Arm getArm() {
		return arm;
	}
	public void setArm(Arm arm) {
		this.arm = arm;
	}
	public AbstractAdverseEventTerm<T> getTerm() {
		return term;
	}
	public void setTerm(AbstractAdverseEventTerm<T> term) {
		this.term = term;
	}
	
}
