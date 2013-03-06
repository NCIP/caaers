/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

import org.hibernate.annotations.*;

 
/**
 * The Class Arm.
 */
@Entity
@Table(name = "arms")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_arms_id") })
public class Arm  extends AbstractMutableDomainObject {
	
	/** The name. */
	private String name;
	
	/** The description text. */
	private String descriptionText;
//	private Epoch epoch;
	/** The solicited adverse events. */
private List<SolicitedAdverseEvent> solicitedAdverseEvents=new ArrayList<SolicitedAdverseEvent>();
	
	/**
	 * Instantiates a new arm.
	 */
	public Arm()
	{
		this(null, null);
	}
	
	/**
	 * Instantiates a new arm.
	 *
	 * @param name the name
	 */
	public Arm(String name)
	{
      	this(name, null);
	}
	
	/**
	 * Instantiates a new arm.
	 *
	 * @param name the name
	 * @param descriptionText the description text
	 */
	public Arm(String name, String descriptionText)
	{
      	this.name = name;	
      	this.descriptionText = descriptionText;
    }
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description text.
	 *
	 * @return the description text
	 */
	@Column(name="description")
	public String getDescriptionText() {
		return descriptionText;
	}
	
	/**
	 * Sets the description text.
	 *
	 * @param descriptionText the new description text
	 */
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
    /**
     * Gets the solicited adverse events.
     *
     * @return the solicited adverse events
     */
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(value = {CascadeType.ALL, CascadeType.DELETE_ORPHAN})
    @JoinColumn(name = "arm_id", nullable=false)
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
	public List<SolicitedAdverseEvent> getSolicitedAdverseEvents() {
		return solicitedAdverseEvents;
	}
    
	/**
	 * Sets the solicited adverse events.
	 *
	 * @param solicitedAdverseEvents the new solicited adverse events
	 */
	public void setSolicitedAdverseEvents(List<SolicitedAdverseEvent> solicitedAdverseEvents) {
		this.solicitedAdverseEvents = solicitedAdverseEvents;
	}
	
}
