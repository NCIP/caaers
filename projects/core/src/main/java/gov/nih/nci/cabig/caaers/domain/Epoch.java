package gov.nih.nci.cabig.caaers.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

@Entity
@Table(name = "epochs")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_epochs_id") })
public class Epoch  extends AbstractMutableDomainObject {
	
	private String name;
	
	private String descriptionText;
	
//	private Study study;
	
	private Integer epochOrder;
	
	private List<Arm> arms=new ArrayList<Arm>();
	
//	private List<SolicitedAdverseEvent> solicitedAdverseEvents;

	public Epoch()
	{}
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
	
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name="epoch_id", nullable=false)
	public List<Arm> getArms() {
		return arms;
	}

	public void setArms(List<Arm> arms) {
		this.arms = arms;
	}
/*    @Transient
	public List<SolicitedAdverseEvent> getSolicitedAdverseEvents() {
		return solicitedAdverseEvents;
	}

	public void setSolicitedAdverseEvents(List<SolicitedAdverseEvent> solicitedAdverseEvents) {
		this.solicitedAdverseEvents = solicitedAdverseEvents;
	}
	*/
	public boolean addArm(Arm arm)
	{
		return arms.add( arm );
	}

	public boolean removeArm(Arm arm)
	{
		if( arms != null && arms.size() != 0 )
		  return arms.remove( arm );
		return false;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description")
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}

	@Column(name="order_no")
	public Integer getEpochOrder() {
		return epochOrder;
	}
	public void setEpochOrder(Integer epochOrder) {
		this.epochOrder = epochOrder;
	}

}
