package gov.nih.nci.cabig.caaers.domain;

import java.util.Set;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

public class Epoch  extends AbstractMutableDomainObject {
	
	private String name;
	
	private String descriptionText;
	
	private Study study;
	
	private Integer epochOrder;
	
	private Set<Arm> arms;
	
	private Set<SolicitedAdverseEvent<DomainObject>> solicitedAdverseEvents;

	public Epoch( String epochName, String... armNames)
	{
		setName(epochName);
		if (armNames.length == 0 ) {
		  addArm( new Arm(epochName) );
		}
		else {
	  	  for ( String armName : armNames ) {
		    addArm( new Arm(armName) );
		  }
		}

	}
	public Set<Arm> getArms() {
		return arms;
	}

	public void setArms(Set<Arm> arms) {
		this.arms = arms;
	}

	public Set<SolicitedAdverseEvent<DomainObject>> getSolicitedAdverseEvents() {
		return solicitedAdverseEvents;
	}

	public void setSolicitedAdverseEvents(Set<SolicitedAdverseEvent<DomainObject>> solicitedAdverseEvents) {
		this.solicitedAdverseEvents = solicitedAdverseEvents;
	}
	
	public boolean addArm(Arm arm)
	{
		return arms.add( arm );
	}

	public boolean removeArm(Arm arm)
	{
		return arms.remove( arm );
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
	}
	public Integer getEpochOrder() {
		return epochOrder;
	}
	public void setEpochOrder(Integer epochOrder) {
		this.epochOrder = epochOrder;
	}

}
