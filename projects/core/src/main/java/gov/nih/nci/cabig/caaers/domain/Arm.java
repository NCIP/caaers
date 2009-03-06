package gov.nih.nci.cabig.caaers.domain;
/**
 * @author ArunKumarK
 * @author Biju Joseph
 */

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "arms")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_arms_id") })
public class Arm  extends AbstractMutableDomainObject {
	private String name;
	private String descriptionText;
//	private Epoch epoch;
	private List<SolicitedAdverseEvent> solicitedAdverseEvents=new ArrayList<SolicitedAdverseEvent>();
	
	public Arm()
	{
		this(null, null);
	}
	public Arm(String name)
	{
      	this(name, null);
	}
	public Arm(String name, String descriptionText)
	{
      	this.name = name;	
      	this.descriptionText = descriptionText;
    }
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
	
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "arm_id", nullable=false)
	public List<SolicitedAdverseEvent> getSolicitedAdverseEvents() {
		return solicitedAdverseEvents;
	}
    
	public void setSolicitedAdverseEvents(List<SolicitedAdverseEvent> solicitedAdverseEvents) {
		this.solicitedAdverseEvents = solicitedAdverseEvents;
	}
	
}
